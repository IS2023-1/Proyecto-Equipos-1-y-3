import { Injectable } from '@angular/core';
import { Usuario } from './usuarios';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { UsuariosComponent } from './usuarios.component';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlEndPoint:string = 'http://localhost:10000/usuarios';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

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

}
