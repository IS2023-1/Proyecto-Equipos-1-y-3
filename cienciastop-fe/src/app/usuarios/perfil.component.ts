import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuarios';
import { Router } from '@angular/router';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  usuario: Usuario = new Usuario();
  nombre: string = this.usuario.nombre;
  apellidoPaterno: string = this.usuario.apellidoPaterno;
  apellidoMaterno: string = this.usuario.apellidoMaterno;
  id: number = this.usuario.id;
  pumapuntos: number = this.usuario.pumapuntos;
  correo: string = this.usuario.correo;

  constructor(private usuarioService : UsuarioService, private router:Router) { }

  ngOnInit(): void {
  }

  public verPerfil() {
    console.log(this.usuario);
  }

  public getUsuario(): void {//Observable <Usuario>{
    this.usuarioService.getUsuario(this.id).subscribe(
      Response => this.router.navigate(['/usuarios/perfil/:id'])
    );
  }
}
