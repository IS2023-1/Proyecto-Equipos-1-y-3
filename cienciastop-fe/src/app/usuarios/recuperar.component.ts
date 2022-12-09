import { Component, OnInit } from '@angular/core';
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
    id_usuario: number;
    password1: string;
    password2: string;
  
    constructor(private usuarioService: UsuarioService) { }
  
    ngOnInit(): void {
    }

    public resetPassword() :void {
      /*if(this.usuario.username == null || this.usuario.password == null){
        Swal.fire('Error Login', 'Username o password vacías!', 'error');
        return;
      }
      */
    }
  }