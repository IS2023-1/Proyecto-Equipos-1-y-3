import { Injectable } from '@angular/core';
import { Observable, catchError, throwError, of, map } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from './usuarios';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { LoginComponent } from './login.component';
import { AuthService } from './auth.service';

@Injectable({
    providedIn: 'root'
  })
  export class UsuarioService {

    private urlEndPointAll: string = 'http://localhost:10000/usuarios/buscar/todo';
    private urlEndpointNombre = 'http://localhost:10000/usuarios/buscar/nombre';
    private urlEndpointId = 'http://localhost:10000/usuarios/buscar/cuenta';
    private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})
  

    constructor(private http: HttpClient, private router: Router, private authService: AuthService) {}

    private agregarAuthorizationHeader(){
      let token = this.authService.token;
      if(token != null){
        return this.httpHeaders.append('Authorization', 'Bearer ' + token);
      }
      return this.httpHeaders;
    }

    getUsuarios(): Observable<Usuario[]> {
      return this.http.get<Usuario[]>(this.urlEndPointAll, 
        {headers: this.agregarAuthorizationHeader()}).pipe(
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
    }
  } 