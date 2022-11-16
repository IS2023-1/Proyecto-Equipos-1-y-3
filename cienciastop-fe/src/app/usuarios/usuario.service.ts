import { Injectable } from '@angular/core';
import { Usuario } from './usuarios';
import { USUARIOS } from './usuarios.json';
import { Observable, catchError, throwError,} from 'rxjs';
import { of } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { UsuariosComponent } from './usuarios.component';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlEndPoint:string = 'http://localhost:4200/api/usuarios';
  private urlEndPointAll: string = 'localhost:10000/usuarios/buscar/todo';
  private urlEndpointNombre = 'localhost:10000/usuarios/buscar';
  private urlEndpointId = 'localhost:10000/usuarios/id';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient, private router: Router) {}

  getUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.urlEndPointAll).pipe(
      catchError(e => {
        this.router.navigate(['/usuarios']);
        Swal.fire('Error al buscar usuarios', e.error.message, 'error');
        return throwError(() => e);
      })
    )
  }

  lookup(searchInput: string): Observable<Usuario[]> {
    var matches = []

    var byName = this.http.get<Usuario[]>(`${this.urlEndpointNombre}/${searchInput}`).pipe(
        catchError(error => {
            this.router.navigate(['/usuarios']);
            Swal.fire("Error al buscar usuario", error.error.message, 'error');
            return throwError(() => error);
        })
    )

    var byCode = this.http.get<Usuario[]>(`${this.urlEndpointId}/${searchInput}`).pipe(
        catchError(error => {
            this.router.navigate(['/usuarios']);
            Swal.fire("Error al buscar usuario", error.error.message, 'error');
            return throwError(() => error);
        })
    )

    byName.forEach(e => matches.push(e));
    byCode.forEach(e => matches.push(e));

    return of(matches);
  }

  
  getUsuario(id:number): Observable<Usuario>{
    const usuario =USUARIOS.find(u=> u.id ===id)!;
    return of(usuario);
    //return this.http.get<Usuario>(`${this.urlEndPoint}/${id}`);
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
    return this.http.put<Usuario>(`${this.urlEndPoint}/${usuario.id}`, usuario, {headers: this.httpHeaders})
  }

  delete(id:number):Observable<Usuario>{
    return this.http.delete<Usuario>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders})
  }

}
