import { Component, OnInit } from '@angular/core';
import { Producto } from '../productos/producto';
import Swal from 'sweetalert2';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router, private activatedRouter: ActivatedRoute) { }

  ngOnInit(): void {
  }

  searchInput: string;
  public onSubmit():void {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: `No hubo resultados para la búsqueda "${this.searchInput}"`,
    })
    //this.router.navigate(['/resBusquedaProductos'])
  }

}
