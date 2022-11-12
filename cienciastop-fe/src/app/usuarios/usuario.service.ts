import { Injectable } from '@angular/core';
import { Usuario } from './usuarios';
import { USUARIOS } from './usuarios.json';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlEndPoint:string = 'http://localhost:4200/api/usuarios';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  getUsuarios(): Observable <Usuario[]>{
    return of(USUARIOS);
  }

  /*getUsuario(id:number) : Usuario{
    for (let u in USUARIOS) {
      if (u.id.compare(id)) {
        return u;
      }
    }
    return null;
  }*/

  getUsuario(id): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.urlEndPoint}/${id}`);
  }

}
