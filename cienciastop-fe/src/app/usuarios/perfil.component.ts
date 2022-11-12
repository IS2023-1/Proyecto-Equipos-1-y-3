import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuarios';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  @Input() user : Usuario; 

  usuario: Usuario = new Usuario();
  nombre: string = this.usuario.nombre;
  apellidoPaterno: string = this.usuario.apellidoPaterno;
  apellidoMaterno: string = this.usuario.apellidoMaterno;
  id: number;
  carrera: string = this.usuario.carrera;
  pumapuntos: number = this.usuario.pumapuntos;
  correo: string = this.usuario.correo;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
  constructor(private usuarioService : UsuarioService, private router:Router, private activatedRoute:ActivatedRoute) { 
    //this.id = this.activatedRoute.snapshot.params.[id];
  }

  ngOnInit(): void { }

  public verPerfil(usuario) {
    console.log(usuario);
  }

  public getUsuario(): void {//Observable <Usuario>{
    this.usuarioService.getUsuario(this.id).subscribe(
      Response => this.router.navigate(['/usuarios/perfil'])
    );
  }
}
