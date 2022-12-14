import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuarios';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  u: Usuario = new Usuario();//| undefined;

  constructor(private usuarioService: UsuarioService, private router: Router, private activatedRoute: ActivatedRoute, public authService: AuthService) {
    let params: any = this.activatedRoute.snapshot.params;
  }

  ngOnInit(): void {
    this.getUsuario();
  }


  public getUsuario(): void {
    const id = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.usuarioService.getUsuario(id).subscribe(u => this.u = u);
  }

  delete(usuario: Usuario): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    if (this.authService.usuario.id == usuario.id_usuario){
      Swal.fire('Error al eliminar al usuario', 'No puedes eliminar a este usuario mientras su sesión está activa', 'error');
    }else{
      swalWithBootstrapButtons.fire({
        title: '¿Estás seguro?',
        text: '¿Estás seguro de eliminar al usuario ' + `${usuario.nombre}`+'?',
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
              this.router.navigate(['/usuarios/buscar/todo'])
            }
          )
        }
      })
    }
  }
}
