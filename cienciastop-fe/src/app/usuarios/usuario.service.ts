import { Injectable } from '@angular/core';
import { Usuario } from './usuario';
import { Observable, catchError } from 'rxjs';
import { of } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { UsuariosComponent } from './usuarios.component';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlEndPoint: string = 'http://localhost:10000/usuarios';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient, private router: Router) {}

  getUsuarios(): Observable <Usuario[]>{
    return this.http.get<Usuario[]>(this.urlEndPoint + '/buscar/todo');
    //return of(USUARIOS);
  }

  getUsuario(id_usuario:number): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.urlEndPoint}/buscar/${id_usuario}`);
  }

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

  lookup(searchInput: string): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.urlEndPoint}/buscar/nombre/${searchInput}`);
  }
}
