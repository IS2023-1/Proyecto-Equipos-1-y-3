import { Component, OnInit } from '@angular/core';
// import { USUARIOS } from './usuarios.json';
import { Usuario } from './usuarios';
import Swal from 'sweetalert2';
import { UsuarioService } from './usuarios.service';
import { delay } from 'rxjs';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['../productos/productos.component.css', 'usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  usuarios: Usuario[];
  matches: Usuario[] = [];

  searchInput: string = "";
  placeholder: string = "";

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.getUsuarios()
  }

  getUsuarios() : void {
    this.usuarioService.getUsuarios().subscribe(usuarios => this.usuarios = usuarios)
  }

  getMatches(input: string): Usuario[] {
    var matches = []
    this.usuarios.forEach(e => {
      if(e.nombre.toLowerCase().includes(input)) {
        matches.push(e);
      } else if (Number(input)) {
        if (e.id_usuario === Number(input)) {
          matches.push(e);
        }
      }
    })
    
    return matches;
  }

  onSubmit(): void {
    this.matches = [];
    this.usuarioService.getUsuarios().subscribe(u => {
      this.usuarios = u;
      var input = this.searchInput.trim().toLowerCase();
      if(!input || input.length == 0) {
        
      } else {
        this.matches = this.getMatches(input);
        document.getElementById("res-bus-usuario").style.display = "none";
        this.usuarios = this.matches;
      }

      if(this.matches.length == 0) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: `No hubo resultados para la búsqueda "${this.searchInput.trim()}"`,
        });
        this.clear();
      } else {
        this.placeholder = this.searchInput;
        document.getElementById("res-bus-usuario").style.display = "block";
        this.usuarios = this.matches;
      }
    });
  }

  clear(): void {
    document.getElementById("res-bus-usuario").style.display = "none";
    this.searchInput = "";
    this.usuarioService.getUsuarios().subscribe(u => this.usuarios = u);
  }
  
}