import { Injectable } from '@angular/core';
import { Observable, catchError, throwError, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Producto } from './producto';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class RbpService {

    private urlEndpointNombre = 'http://localhost:10000/productos/buscar/nombre';
    private urlEndpointCodigo = 'http://localhost:10000/productos/buscar/codigo';

    constructor(private http: HttpClient, private router: Router) {}

    lookup(searchInput: string): Observable<Producto[]> {
        var matches = []

        var byName = this.http.get<Producto[]>(`${this.urlEndpointNombre}/${searchInput}`).pipe(
            catchError(error => {
                this.router.navigate(['/productos']);
                Swal.fire("Error al buscar producto", error.error.message, 'error');
                return throwError(() => error);
            })
        )

        var byCode = this.http.get<Producto[]>(`${this.urlEndpointCodigo}/${searchInput}`).pipe(
            catchError(error => {
                this.router.navigate(['/productos']);
                Swal.fire("Error al buscar producto", error.error.message, 'error');
                return throwError(() => error);
            })
        )

        byName.subscribe(res => res.forEach(e => matches.push(e)));
        byCode.subscribe(res => res.forEach(e => matches.push(e)));

        return of(matches);
    }
}