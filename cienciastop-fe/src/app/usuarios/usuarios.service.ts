import { Injectable } from '@angular/core';
import { Observable, catchError, throwError, of, map } from 'rxjs';
import { HttpClient} from '@angular/common/http';
import { Usuario } from './usuarios';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { LoginComponent } from './login.component';

@Injectable({
    providedIn: 'root'
  })
  export class UsuarioService {

    private urlEndPointAll: string = 'http://localhost:10000/usuarios/buscar/todo';
    private urlEndpointNombre = 'http://localhost:10000/usuarios/buscar/nombre';
    private urlEndpointId = 'http://localhost:10000/usuarios/buscar/cuenta';
  

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
      return this.http.get<Usuario[]>(`${this.urlEndpointNombre}/${searchInput}`).pipe(
        catchError(error => {
            this.router.navigate(['/usuarios']);
            Swal.fire("Error al buscar usuario", error.error.message, 'error');
            return throwError(() => error);
        })
      );
      /*var inputNumber = Number(searchInput); 
      if(inputNumber){
        return this.http.get<Usuario>(`${this.urlEndpointId}/${inputNumber}`).pipe(
          catchError(error => {
              this.router.navigate(['/usuarios']);
              Swal.fire("Error al buscar usuario", error.error.message, 'error');
              return throwError(() => error);
          })
        );
      } else {
        return this.http.get<Usuario[]>(`${this.urlEndpointNombre}/${searchInput}`).pipe(
          catchError(error => {
              this.router.navigate(['/usuarios']);
              Swal.fire("Error al buscar usuario", error.error.message, 'error');
              return throwError(() => error);
          })
        );
      }*/
    }
  } 