import { Component, OnInit } from '@angular/core';
import { Producto } from '../productos/producto';
import { ProductoService } from '../productos/producto.service';
import { AuthService } from './auth.service';
import { UsuarioService } from './usuario.service';

@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html',
  styleUrls: ['./historial.component.css']
})
export class HistorialComponent implements OnInit {
  productos: Producto[] = [];
  constructor(private usuarioService: UsuarioService,  public authService: AuthService, private productoService: ProductoService) {   }

  ngOnInit(): void {
    //this.usuarioService.getProductosRentados().subscribe(
      //productos => this.productos = productos
    //)

    this.productoService.getProductos().subscribe(
      productos => this.productos = productos
    )
  }

}
