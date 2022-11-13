import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuarios';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form-usuario',
  templateUrl: './form-usuario.component.html',
  styleUrls: ['./form-usuario.component.css']
})
export class FormUsuarioComponent implements OnInit {
  titulo:string ="Modo edicion";
  user : Usuario = new Usuario();//| undefined;

  constructor(private usuarioService:UsuarioService, private router:Router, private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.getUsuario();
    //this.cargarProducto()
  }

  cargarProducto(): void{
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.usuarioService.getUsuario(id).subscribe((usuario)=>this.user = usuario)
      }
    })
  }

  public getUsuario(): void {
    const id = Number( this.activatedRoute.snapshot.paramMap.get('id'));
          this.usuarioService.getUsuario(id).subscribe(u => this.user = u);
  }

  public update():void{
    console.log(this.user);
    //this.usuarioService.update(this.user);
    /*this.usuarioService.update(this.user).subscribe(user => 
      {
      this.router.navigate(['/usuarios'])
      swal.fire('Campos Actualizados', `usuario ${user.id} actualizado exitosamente`, 'success')
      }
    )*/
  }



}
