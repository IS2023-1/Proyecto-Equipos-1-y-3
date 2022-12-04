import { Injectable } from '@angular/core';
import { Usuario } from './usuarios';
import { catchError, Observable, throwError } from 'rxjs';
import { of } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { UsuariosComponent } from './usuarios.component';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlEndPoint:string = 'http://localhost:10000/usuarios';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) {}

  private agregarAuthorizationHeader(){
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    return this.httpHeaders;
  }

  private isNoAutorizado(e): boolean{
    if(e.status==401){
      if(this.authService.isAuthenticated()){
        this.authService.logout();
      }
      this.router.navigate(['/login'])
      return true;
    }

    if(e.status==403){
      Swal.fire('Acceso denegado', `Hola ${this.authService.usuario.username} no tienes acceso a este recurso!`, 'warning');
      this.router.navigate(['/productos'])
      return true;
    }
    return false;
  }

  getUsuarios(): Observable <Usuario[]>{
    return this.http.get<Usuario[]>(this.urlEndPoint + '/buscar/todo');
    //return of(USUARIOS);
  }

  getUsuario(id_usuario): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.urlEndPoint}/buscar/${id_usuario}`, {headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }


        this.router.navigate(['/usuarios']);
        Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError( () => e );
      })
    )  }

  /*update(usuario: Usuario): void{//Observable<Usuario>{
    //return this.http.put<Usuario>(`${this.urlEndPoint}/${usuario.id}`, usuario, {headers: this.httpHeaders})
    const usuarios= of(USUARIOS);
    var aEditar = USUARIOS.find(u => u.id === usuario.id);
    if (aEditar != null) {
      aEditar.nombre = usuario.nombre;
      aEditar.apellidoPaterno = usuario.apellidoPaterno;
      aEditar.apellidoMaterno = usuario.apellidoMaterno;
    }
  }*/
 
  update(usuario: Usuario): Observable<Usuario>{
    return this.http.post<Usuario>(`${this.urlEndPoint}/editar/${usuario.id_usuario}`, usuario, {headers: this.httpHeaders})
  }

  delete(id_usuario:number):Observable<Usuario>{
    return this.http.delete<Usuario>(`${this.urlEndPoint}/eliminar/${id_usuario}`, {headers: this.httpHeaders})
  }

}
