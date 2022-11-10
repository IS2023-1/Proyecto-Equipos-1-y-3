import { Component, OnInit } from '@angular/core';
import { Producto } from '../productos/producto';
import Swal from 'sweetalert2';
import { Router, ActivatedRoute } from '@angular/router';
import { PRODUCTOS } from '../productos/productos.json';

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
    var ss = require("string-similarity");
    var productos = PRODUCTOS;
    var searchInputLower = this.searchInput.toLowerCase();
    var matches = productos.filter(e => {
      var nombreLower = e.nombre.toLowerCase();
      var codigoLower = e.codigo.toLowerCase();
      return ss.compareTwoStrings(codigoLower.toLowerCase(), searchInputLower) > 0.6 || 
      ss.compareTwoStrings(nombreLower.toLowerCase(), searchInputLower) > 0.6 ||
      nombreLower.includes(searchInputLower) ||
      codigoLower.includes(searchInputLower)
    });
    console.log(matches)
    if(matches.length == 0) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: `No hubo resultados para la búsqueda "${this.searchInput}"`,
      });
    } else {
      //this.router.navigate(['/resBusquedaProductos'])
    }
  }

}
