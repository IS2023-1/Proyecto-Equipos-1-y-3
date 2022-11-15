import { Injectable } from '@angular/core';
import { Observable, catchError, throwError, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Producto } from './producto';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class RbpService {

    private urlEndpoint = 'http://localhost:8082/producto';

    constructor(private http: HttpClient, private router: Router) {}

    lookup(searchInput: string): Observable<Producto[]> {
        return this.http.get<Producto[]>(`${this.urlEndpoint}/${searchInput}`).pipe(
            catchError(error => {
                this.router.navigate(['/productos']);
                Swal.fire("Error al buscar producto", error.error.message, 'error');
                return throwError(() => error);
            })
        )
    }
}