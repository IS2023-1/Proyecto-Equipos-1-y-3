import { Injectable } from '@angular/core';
import { Observable, catchError, throwError, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Producto } from './producto';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { AuthService } from '../usuarios/auth.service';


@Injectable({ providedIn: 'root' })
export class RbpService {

    private urlEndpointAll = 'http://localhost:10000/productos/buscar/todo';
    private urlEndpointNombre = 'http://localhost:10000/productos/buscar/nombre';
    private urlEndpointCodigo = 'http://localhost:10000/productos/buscar/codigo';
    private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

    constructor(private http: HttpClient, private router: Router, private authService: AuthService) {}

    private agregarAuthorizationHeader(){
        let token = this.authService.token;
        if(token != null){
          return this.httpHeaders.append('Authorization', 'Bearer ' + token);
        }
        return this.httpHeaders;
    }

    lookup(searchInput: string): Observable<any> {
        var searchInputNumber = Number(searchInput);
        if(searchInputNumber) {
            return this.http.get<Producto[]>(`${this.urlEndpointCodigo}/${searchInputNumber}`,
            {headers: this.agregarAuthorizationHeader()} ).pipe(
                catchError(error => {
                    this.router.navigate(['/productos']);
                    //Swal.fire("Error al buscar producto", error.error.message, 'error');
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: `No hubo resultados para la búsqueda "${searchInput}"`,
                    });
                    return throwError(() => error);
                })
            );
        } else {
            return this.http.get<Producto[]>(`${this.urlEndpointNombre}/${searchInput}`,
            {headers: this.agregarAuthorizationHeader()} ).pipe(
                catchError(error => {
                    this.router.navigate(['/productos']);
                    //Swal.fire("Error al buscar producto", error.error.message, 'error');
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: `No hubo resultados para la búsqueda "${searchInput}"`,
                    });
                    return throwError(() => error);
                })
            );
        }
    }
}