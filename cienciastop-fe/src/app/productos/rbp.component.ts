import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { RbpService } from './rbp.service';

@Component({
  selector: 'app-rbp',
  templateUrl: './rbp.component.html',
  styleUrls: ['./productos.component.css']
})
export class RbpComponent implements OnInit {

  public productMatches: Producto[] = [];
  public input: string = ""; //this.headerService.getMessage()[1];

  constructor(private router: Router, 
              private activatedRoute: ActivatedRoute,
              private rbpService: RbpService) { 
  }

  ngOnInit(): void {
    this.lookup();
  }



  lookup(): void {
    this.activatedRoute.params.subscribe(input => {
      this.input = input['input'];
      this.productMatches = [];
      var searchInputTrim = this.input.trim();
      if(!this.input || searchInputTrim.length === 0) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: `No hubo resultados para la búsqueda ""`,
        });
        this.router.navigate(['/productos']);
        input['input'] = "";
      } else {
        this.rbpService.lookup(searchInputTrim).subscribe(res => {
          console.log(res);
          this.productMatches.push(res);
          if(!this.productMatches) {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: `No hubo resultados para la búsqueda "${this.input.trim()}"`,
            });
            this.router.navigate(['/productos']);
          }
        });
      }
    })
  }

}
