import { Component, OnInit } from '@angular/core';
import { AuthService } from '../usuarios/auth.service';
import {Router, ActivatedRoute} from '@angular/router';
import Swal from 'sweetalert2'; 
import { PRODUCTOS } from '../productos/productos.json';
import { HeaderService } from './header.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public authService:AuthService, private router: Router ) { }


  logout():void {
    let username = this.authService.usuario.username;
    this.authService.logout();
    Swal.fire('Logout', `Hola ${username}, ha cerrado sesión con éxito!`, 'success');
    this.router.navigate(['/login']);
  }
  
  ngOnInit(): void {
    
  }

  productMatches = [];

  searchInput: string;

  public getMatches(): void {
    if(this.searchInput == "") return;
    this.productMatches = [];
    var ss = require("string-similarity");
    var productos = PRODUCTOS;
    var searchInputLower = this.searchInput.toLowerCase();
    productos.forEach(e => {
      var nombreLower = e.nombre.toLowerCase();
      var codigoLower = e.codigo.toLowerCase();
      if(ss.compareTwoStrings(codigoLower, searchInputLower) > 0.6) {
        this.productMatches.push([e, ss.compareTwoStrings(codigoLower, searchInputLower)])
      } else if(ss.compareTwoStrings(nombreLower, searchInputLower) > 0.6) {
        this.productMatches.push([e, ss.compareTwoStrings(nombreLower, searchInputLower)])
      } else if(nombreLower.includes(searchInputLower) || codigoLower.includes(searchInputLower)) {
        this.productMatches.push([e, 0.6])
      }
    });

    this.productMatches = this.productMatches.sort((e1, e2) => (e1 > e2 ? -1 : 1)).map(e => e[0]);
  }

  public onSubmit(): void {
    this.getMatches();
    console.log(this.productMatches)
    console.log(this.searchInput);
    if(this.productMatches.length == 0) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: `No hubo resultados para la búsqueda "${this.searchInput}"`,
      });
    } else {
      this.sendMessage();
      this.router.navigate(['/productos/rbp']);
    }
  }

}
