import { Component, OnInit } from '@angular/core';
import { USUARIOS } from './usuarios.json';
import { Usuario } from './usuarios';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['../productos/productos.component.css']
})
export class UsuariosComponent implements OnInit {

  usuarios: Usuario[];

  searchInput: string = "";
  placeholder: string = "";

  constructor() { }

  ngOnInit(): void {
    this.usuarios = USUARIOS;
  }

  getMatches(): Usuario[] {
    console.log(this.searchInput);
    var matches = []
    
    this.usuarios.forEach(e => {
      if((`${e.nombre} ${e.apellidoPaterno} ${e.apellidoMaterno}`).toLowerCase().includes(this.searchInput.toLowerCase())) {
        matches.push(e);
      }
    })

    return matches;
  }

  onSubmit(): void {
    this.usuarios = USUARIOS;
    document.getElementById("res-bus-usuario").style.display = "none";
    var matches = this.getMatches();
    console.log(matches)
    console.log(this.searchInput);
    if(matches.length == 0) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: `No hubo resultados para la búsqueda "${this.searchInput}"`,
      });
    } else {
      this.placeholder = this.searchInput;
      document.getElementById("res-bus-usuario").style.display = "block";
      this.usuarios = matches;
    }
  }
}