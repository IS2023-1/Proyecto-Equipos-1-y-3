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
  //nombre : String = this.u.nombre;

  constructor(private usuarioService: UsuarioService, private router: Router, private activatedRoute: ActivatedRoute, public authService: AuthService) {
    //const id = this.activatedRoute.data.subscribe( v=> console.log(v));
    let params: any = this.activatedRoute.snapshot.params;
    //console
  }

  ngOnInit(): void {
    this.getUsuario();
  }


  public getUsuario(): void {//Observable <Usuario>{
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

  public update():void{
    this.usuarioService.update(this.u).subscribe((usuario) => 
      {
      this.router.navigate(['/usuarios/editar'])
      Swal.fire('Usuario Actualizado', `${usuario.nombre} actualizado exitosamente`, 'success')
      }
    )
  }
}
