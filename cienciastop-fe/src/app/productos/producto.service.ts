import { Injectable } from '@angular/core';
import { Producto } from './producto';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError , throwError} from 'rxjs';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { AuthService } from '../usuarios/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private urlEndPointRentar = 'http://localhost:10000/rentar/agregar';

  private urlEndPoint:string = 'http://localhost:10000/productos';

  private urlEndpointAll: string = 'http://localhost:10000/productos/buscar/todo';
  private urlEndpointCodigo = 'http://localhost:10000/productos/buscar/codigo';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }

  rentar(producto: Producto): Observable<Producto>{
    return this.http.post<any>(`${this.urlEndPointRentar}/${this.authService.usuario.id}/${producto.id_producto}`, {headers: this.agregarAuthorizationHeader()} ).pipe(
      
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }
        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError( () => e );
      })
    )  
  }

  private agregarAuthorizationHeader(){
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    return this.httpHeaders;
  }

  private isNoAutorizado(e): boolean{
    if(e.status==401){
      if(this.authService.isAuthenticated()){
        this.authService.logout();
      }
      this.router.navigate(['/login'])
      return true;
    }

    if(e.status==403){
      Swal.fire('Acceso denegado', `Hola ${this.authService.usuario.username} no tienes acceso a este recurso!`, 'warning');
      this.router.navigate(['/productos'])
      return true;
    }
    return false;
  }


  getProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.urlEndpointAll, 
      {headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {
        this.router.navigate(['/productos']);
        Swal.fire('Error al buscar productos', e.error.message, 'error');
        return throwError(() => e);
      })
    )
  }

  create(producto: Producto): Observable<Producto>{
    return this.http.post<any>(this.urlEndPoint+"/agregar", producto, {headers: this.agregarAuthorizationHeader()} ).pipe(
      
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }


        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError( () => e );
      })
    )  }

  getProducto(codigo): Observable<Producto>{
    return this.http.get<Producto>(`${this.urlEndPoint}/buscar/codigo/${codigo}`, { headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {

        if (this.isNoAutorizado(e)) {
          return throwError(() => e);
        }


        this.router.navigate(['/productos']);
        Swal.fire('Error al obtener producto', e.error.mensaje, 'error');
        return throwError(() => e);
      })
    )
  }

    update(producto: Producto): Observable<Producto>{
      return this.http.post<Producto>(`${this.urlEndPoint}/editar/${producto.id_producto}`, producto, {headers: this.agregarAuthorizationHeader()}).pipe(
        catchError(e => {
          if(this.isNoAutorizado(e)){
            return throwError( () => e );
          }
          Swal.fire(e.error.mensaje, e.error.error, 'error');
          return throwError( () => e );
        })
      )  
    }

  delete(id: number): Observable<Producto>{
    return this.http.delete<Producto>(`${this.urlEndPoint}/eliminar/${id}`, {headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {
        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }
        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError( () => e );
      })
    )  
  }

  lookup(searchInput: string): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.urlEndpointCodigo}/${searchInput}`).pipe(
      catchError(error => {
          this.router.navigate(['/usuarios']);
          Swal.fire("Error al buscar usuario", error.error.message, 'error');
          return throwError(() => error);
      })
    );
  }
}
