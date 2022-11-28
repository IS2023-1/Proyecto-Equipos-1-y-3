import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuario';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit { 
  u : Usuario | undefined;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
  constructor(private usuarioService : UsuarioService, private router:Router, private activatedRoute:ActivatedRoute) { 
    //const id = this.activatedRoute.data.subscribe( v=> console.log(v));
    let params: any = this.activatedRoute.snapshot.params;
    //console
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
  delete(usuario: Usuario) : void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    
    swalWithBootstrapButtons.fire({
      title: '¿Estás seguro?',
      text: '¿Estás seguro de eliminar al usuario ${usuario.nombre}?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.usuarioService.delete(usuario.id_usuario).subscribe(
          Response => {
            swalWithBootstrapButtons.fire(
              'Usuario eliminado!',
              '¡Usuario eliminado con éxito!.',
              'success'
            )
            this.router.navigate(['/usuarios'])
          }
        ) 
      } 
    })
  }
}
