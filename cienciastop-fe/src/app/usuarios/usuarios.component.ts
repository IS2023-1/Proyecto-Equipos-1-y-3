import { Component, OnInit } from '@angular/core';
import { USUARIOS } from './usuarios.json';
import { Usuario } from './usuarios';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['../productos/productos.component.css']
})
export class UsuariosComponent implements OnInit {

  usuarios: Usuario[];

  constructor() { }

  ngOnInit(): void {
    this.usuarios = USUARIOS;
  }

}
