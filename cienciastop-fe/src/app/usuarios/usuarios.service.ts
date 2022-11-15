import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from './usuarios';

@Injectable({
    providedIn: 'root'
  })
  export class UsuarioService {

    private urlEndPoint: string = 'http://localhost:8080/api/productos';

    private temporalUrl: string = '/assets/usuarios.json';

    constructor(private http: HttpClient) {}

    getUsuarios(): Observable<Usuario[]> {
        return this.http.get<Usuario[]>(this.temporalUrl);
    }

    lookup(searchInput: string): Observable<Usuario[]> {
        return this.http.get<Usuario[]>(this.temporalUrl);
    }
  } 