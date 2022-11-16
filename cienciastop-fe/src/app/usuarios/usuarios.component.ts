import { Component, OnInit } from '@angular/core';
import { USUARIOS } from './usuarios.json';
import { Usuario } from './usuarios';
import Swal from 'sweetalert2';
import { UsuarioService } from './usuarios.service';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['../productos/productos.component.css', 'usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  usuarios: Usuario[];
  matches: Usuario[];

  searchInput: string = "";
  placeholder: string = "";

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    /*this.usuarioService.getUsuarios().subscribe(
      usuarios => this.usuarios = usuarios
    );*/
    //this.getUsuarios();
    this.usuarios=USUARIOS;
  }

  getUsuarios() : void {
    this.usuarioService.getUsuarios().subscribe(usuarios => this.usuarios = usuarios)
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
    this.matches = []
    var searchInputLower = this.searchInput.trim().toLowerCase();
    if(!this.searchInput || searchInputLower.length == 0) {

    } else {
      document.getElementById("res-bus-usuario").style.display = "none";
      this.usuarioService.getUsuarios().subscribe(u => this.usuarios = u);
      this.usuarioService.lookup(this.searchInput).subscribe(u => this.matches = u);
      console.log(this.matches)
      console.log(this.searchInput);
    }

    if(this.matches.length == 0) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: `No hubo resultados para la búsqueda "${this.searchInput}"`,
      });
    } else {
      this.placeholder = this.searchInput;
      document.getElementById("res-bus-usuario").style.display = "block";
      this.usuarios = this.matches;
    }
  }

  clear(): void {
    document.getElementById("res-bus-usuario").style.display = "none";
    this.searchInput = "";
    this.usuarios = USUARIOS;
  }
}