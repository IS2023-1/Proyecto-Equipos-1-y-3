import { Injectable } from '@angular/core';
import { Observable, catchError, throwError, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { AuthService } from '../usuarios/auth.service';


@Injectable({ providedIn: 'root' })
export class ReportesService {

    private activos = "http://localhost:10000/usuarios/numero_activos";
    private cincoMasRentados = "";
    private inactivos = "http://localhost:10000/usuarios/numero_inactivos";
    private cincoMasBaratos = "http://localhost:10000/productos/productos_baratos";
    private diezMasTarde = "";
    private cincoMasRentas = "";

    private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

    constructor(private http: HttpClient, private router: Router, private authService: AuthService) {}

    private agregarAuthorizationHeader(){
        let token = this.authService.token;
        if(token != null){
          return this.httpHeaders.append('Authorization', 'Bearer ' + token);
        }
        return this.httpHeaders;
    }

    getActivos(): Observable<any> {
        return this.http.get<any>(this.activos,
            {headers: this.agregarAuthorizationHeader()}).pipe(
                catchError(error => {
                    /*this.router.navigate(['/productos']);
                    //Swal.fire("Error al buscar producto", error.error.message, 'error');
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: `Hubo un error de conexión con la BD.`,
                    });
                    return throwError(() => error);*/
                    return of(-1);
                })
            );
    }

    getCincoMasRentados(): Observable<any> {
        return this.http.get<any>(this.cincoMasRentados,
            {headers: this.agregarAuthorizationHeader()}).pipe(
                catchError(error => {
                    /*this.router.navigate(['/productos']);
                    //Swal.fire("Error al buscar producto", error.error.message, 'error');
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: `Hubo un error de conexión con la BD.`,
                    });
                    return throwError(() => error);*/
                    return of(-1);
                })
            );
    }

    getInactivos(): Observable<number> {
        return this.http.get<number>(this.inactivos,
            {headers: this.agregarAuthorizationHeader()}).pipe(
                catchError(error => {
                    /*this.router.navigate(['/productos']);
                    //Swal.fire("Error al buscar producto", error.error.message, 'error');
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: `Hubo un error de conexión con la BD.`,
                    });
                    return throwError(() => error);*/
                    return of(-1);
                })
            );
    }

    getCincoMasBaratos(): Observable<any> {
        return this.http.get<any>(this.cincoMasBaratos,
            {headers: this.agregarAuthorizationHeader()}).pipe(
                catchError(error => {
                    /*this.router.navigate(['/productos']);
                    //Swal.fire("Error al buscar producto", error.error.message, 'error');
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: `Hubo un error de conexión con la BD.`,
                    });
                    return throwError(() => error);*/
                    return of(-1);
                })
            );
    }

    getDiezMasTarde(): Observable<any> {
        return this.http.get<any>(this.diezMasTarde,
            {headers: this.agregarAuthorizationHeader()}).pipe(
                catchError(error => {
                    /*this.router.navigate(['/productos']);
                    //Swal.fire("Error al buscar producto", error.error.message, 'error');
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: `Hubo un error de conexión con la BD.`,
                    });
                    return throwError(() => error);*/
                    return of(-1);
                })
            );
    }

    getCincoMasRentas(): Observable<any> {
        return this.http.get<any>(this.cincoMasRentas,
            {headers: this.agregarAuthorizationHeader()}).pipe(
                catchError(error => {
                    /*this.router.navigate(['/productos']);
                    //Swal.fire("Error al buscar producto", error.error.message, 'error');
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: `Hubo un error de conexión con la BD.`,
                    });
                    return throwError(() => error);*/
                    return of(-1);
                })
            );
    }
}