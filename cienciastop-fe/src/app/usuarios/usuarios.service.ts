import { Injectable } from '@angular/core';
import { Observable, catchError, throwError, of } from 'rxjs';
import { HttpClient} from '@angular/common/http';
import { Usuario } from './usuarios';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
  })
  export class UsuarioService {

    private urlEndPointAll: string = 'localhost:10000/usuarios/buscar/todo';
    private urlEndpointNombre = 'localhost:10000/usuarios/buscar';
    private urlEndpointId = 'localhost:10000/usuarios/id';
  

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
  } 