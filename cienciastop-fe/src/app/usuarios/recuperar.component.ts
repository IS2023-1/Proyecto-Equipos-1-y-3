import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuario';
import swal from 'sweetalert2';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
import { AuthService } from './auth.service';

@Component({
    selector: 'app-recuperar',
    templateUrl: './recuperar.component.html',
    styleUrls: ['./recuperar.component.css']
  })
  export class RecuperarComponent implements OnInit {
    usuario: Usuario;
  
    constructor(private usuarioService: UsuarioService, private router: Router, private activatedRoute: ActivatedRoute) { 
      this.usuario = new Usuario();
    }
  
    ngOnInit(): void {
    }

    public resetPassword( ) :void {
      if(this.usuario.id == null || this.usuario.password1 == null || this.usuario.password2 == null){
        Swal.fire('Error al recuperar contraseña', 'Username o password vacías!', 'error');
        return;
      }
      if(this.usuario.password1 == this.usuario.password2){
        const swalWithBootstrapButtons = Swal.mixin({
          customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
          },
          buttonsStyling: false
        })
        swalWithBootstrapButtons.fire({
          title: '¿Estás seguro?',
          text: '¿Estás seguro de cambiar tu contraseña?',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonText: 'Sí, cambiar',
          cancelButtonText: 'No, cancelar!',
          reverseButtons: true
        }).then((result) => {
          if (result.isConfirmed) {
            this.usuarioService.resetPassword(this.usuario.password1, this.usuario.id).subscribe(
              Response => {
                swalWithBootstrapButtons.fire(
                  'Contraseña cambiada',
                  'Tu contraseña ha cambiado, inicia sesión de nuevo.',
                  'success'
                )
                this.router.navigate(['/login'])
              }
            )
          }
        })
      }else{
        Swal.fire('Error al recuperar contraseña', 'Las contraseñas no coinciden', 'error');
      }
    }
  }