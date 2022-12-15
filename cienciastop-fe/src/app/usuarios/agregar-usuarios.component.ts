import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuarios';
import Swal from 'sweetalert2';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-agregar-usuarios',
  templateUrl: './agregar-usuarios.component.html',
  styleUrls: ['./agregar-usuarios.component.css']
})
export class AgregarUsuariosComponent implements OnInit {

  titulo: string = "Agrega un usuario";
  user: Usuario = new Usuario();//| undefined;

  constructor(private usuarioService: UsuarioService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    //this.getUsuario();
    //this.cargarUsuario()
  }
 

  public create(): void {
    this.user.penalizaciones =0;
    this.usuarioService.create(this.user)
      .subscribe(cliente => {
        //this.router.navigate(['']);
        Swal.fire('Nuevo cliente', `Cliente ${cliente.nombre} creado con éxito!`, 'success');
      }
      );
  }

  cargarUsuario(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      if (id) {
        this.usuarioService.getUsuario(id).subscribe((usuario) => this.user = usuario)
      }
    })
  }

  public getUsuario(): void {
    const id = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.usuarioService.getUsuario(id).subscribe(u => this.user = u);
  }
  public update():void{
    console.log(this.user)
    this.usuarioService.update(this.user).subscribe((usuario) => 
      {
      this.router.navigate(['/usuarios'])
      Swal.fire('Usuario Actualizado', `${this.user.nombre} actualizado exitosamente`, 'success')
      }
    )
  }
}
