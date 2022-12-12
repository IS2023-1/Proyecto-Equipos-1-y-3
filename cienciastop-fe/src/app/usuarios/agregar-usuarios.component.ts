import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';

@Component({
  selector: 'app-agregar-usuarios',
  templateUrl: './agregar-usuarios.component.html',
  styleUrls: ['./agregar-usuarios.component.css']
})
export class AgregarUsuariosComponent implements OnInit {

  public usuario: Usuario = new Usuario();
  public titulo:string = "Agregar Usuario"

  constructor() { }

  ngOnInit(): void {
  }

  public create(): void {
    console.log("Clicked!")
    console.log(this.usuario)
  }

}
