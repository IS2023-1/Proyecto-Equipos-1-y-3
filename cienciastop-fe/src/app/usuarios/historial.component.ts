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
  productos: Object[] = [];
  constructor(private usuarioService: UsuarioService,  public authService: AuthService, private productoService: ProductoService) {   }

  ngOnInit(): void {
    this.historial(this.authService.usuario.id)
    /*this.usuarioService.historial(this.authService.usuario.id).subscribe(
      productos => this.productos = productos)
      console.log(this.productos.length);*/
    //this.productoService.getProductos().subscribe(
      //productos => this.productos = productos
    //)
  }

  historial(id) : void{
    this.usuarioService.historial(id).subscribe(
      productos => this.productos = productos)
      console.log(this.productos.length);
  }

}
