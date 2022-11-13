import { Injectable } from '@angular/core';
import { Usuario } from './usuarios';
import { USUARIOS } from './usuarios.json';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { UsuariosComponent } from './usuarios.component';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlEndPoint:string = 'http://localhost:4200/api/usuarios';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  getUsuarios(): Observable <Usuario[]>{
    const usuarios= of(USUARIOS);
    return usuarios;
    //return this.http.get<Usuario[]>(this.urlEndPoint);
    //return of(USUARIOS);
  }

  getUsuario(id:number): Observable<Usuario>{
    const usuario =USUARIOS.find(u=> u.id ===id)!;
    return of(usuario);
    //return this.http.get<Usuario>(`${this.urlEndPoint}/${id}`);
  }

  update(usuario: Usuario): void{//Observable<Usuario>{
    //return this.http.put<Usuario>(`${this.urlEndPoint}/${usuario.id}`, usuario, {headers: this.httpHeaders})
    const usuarios= of(USUARIOS);
    var aEditar = USUARIOS.find(u => u.id === usuario.id);
    if (aEditar != null) {
      aEditar.nombre = usuario.nombre;
    }
  }

}
