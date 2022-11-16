import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ActivatedRoute, Router } from '@angular/router';
import { PRODUCTOS } from './productos.json';
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

  public getMatches(): void {
    this.productMatches = [];
    var searchInputLower = this.input.trim().toLowerCase();
    if(!this.input || searchInputLower.length === 0) return;
    var matches = [];
    matches = [];
    var ss = require("string-similarity");
    var productos = PRODUCTOS;
    productos.forEach(e => {
      var nombreLower = e.nombre.toLowerCase();
      var codigoLower = e.codigo.toLowerCase();
      if(ss.compareTwoStrings(codigoLower, searchInputLower) > 0.6) {
        matches.push([e, ss.compareTwoStrings(codigoLower, searchInputLower)])
      } else if(ss.compareTwoStrings(nombreLower, searchInputLower) > 0.6) {
        matches.push([e, ss.compareTwoStrings(nombreLower, searchInputLower)])
      } else if(nombreLower.includes(searchInputLower) || codigoLower.includes(searchInputLower)) {
        matches.push([e, 0.6])
      }
    });

    matches = matches.sort((e1, e2) => (e1 > e2 ? -1 : 1)).map(e => e[0]);
    this.productMatches = matches;
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
