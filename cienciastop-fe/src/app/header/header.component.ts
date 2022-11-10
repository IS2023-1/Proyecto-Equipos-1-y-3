import { Component, OnInit } from '@angular/core';
import { Producto } from '../productos/producto';
import Swal from 'sweetalert2';
import { Router, ActivatedRoute } from '@angular/router';
import { PRODUCTOS } from '../productos/productos.json';
import { SelectorMatcher } from '@angular/compiler';

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

  public onSubmit(): void {
    var ss = require("string-similarity");
    var productos = PRODUCTOS;
    var searchInputLower = this.searchInput.toLowerCase();
    var matches = [];
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

    matches = matches.sort((e1, e2) => (e1 > e2 ? -1 : 1)).map(e => e[0])

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
