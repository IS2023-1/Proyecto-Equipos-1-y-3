import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { HttpClient} from '@angular/common/http';
import { Usuario } from './usuarios';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
  })
  export class UsuarioService {

    private urlEndPoint: string = 'http://localhost:8080/api/productos';

    constructor(private http: HttpClient, private router: Router) {}

    lookup(searchInput: string): Observable<Usuario[]> {
        return this.http.get<Usuario[]>(this.urlEndPoint).pipe(
          catchError(e => {
            this.router.navigate(['/usuarios']);
            Swal.fire('Error al buscar usuarios', e.error.meesage, 'error');
            return throwError(() => e);
          })
        )
    }
  } 