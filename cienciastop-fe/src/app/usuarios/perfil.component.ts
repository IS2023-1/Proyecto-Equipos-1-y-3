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
  u : Usuario | undefined;
  //currentUser : Usuario = u;

  /*usuario: Usuario = new Usuario();
  nombre: string = this.usuario.nombre;
  apellidoPaterno: string = this.usuario.apellidoPaterno;
  apellidoMaterno: string = this.usuario.apellidoMaterno;*/
  //id: number;
  /*carrera: string = this.usuario.carrera;
  pumapuntos: number = this.usuario.pumapuntos;
  correo: string = this.usuario.correo;*/
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
  constructor(private usuarioService : UsuarioService, private router:Router, private activatedRoute:ActivatedRoute) { 
    //const id = this.activatedRoute.data.subscribe( v=> console.log(v));
    let params: any = this.activatedRoute.snapshot.params;
    console
  }

  ngOnInit(): void {
    this.getUsuario();
   }

  public getUsuario(): void {//Observable <Usuario>{
    /*this.usuarioService.getUsuario(this.id).subscribe(
      Response => this.router.navigate(['/usuarios/perfil'])
    );*/
    const id = Number( this.activatedRoute.snapshot.paramMap.get('id'));
          this.usuarioService.getUsuario(id).subscribe(u => this.u = u);
  }
}
