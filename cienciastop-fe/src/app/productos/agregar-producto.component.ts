import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { Observable } from 'rxjs';
import { AuthService } from '../usuarios/auth.service';


@Component({
  selector: 'app-form',
  templateUrl: './agregar-producto.component.html',
  styleUrls: ['./agregar-producto.component.css']
})
export class FormComponent implements OnInit {

  titulo: string = "Añadir Producto"
  producto: Producto = new Producto()

  constructor(private productoService: ProductoService, private router: Router, private activateRoute: ActivatedRoute, public authService: AuthService) { }

  ngOnInit(): void {
  }

  cargarProducto(): void{
    this.activateRoute.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.productoService.getProducto(this.producto.id_producto).subscribe((producto)=>this.producto = producto)
      }
    })
  }

  public getProducto(): void {
    const id = Number(this.activateRoute.snapshot.paramMap.get('id'));
    this.productoService.getProducto(id).subscribe(u => this.producto = u);
  }

  public create():void{
    this.productoService.create(this.producto).subscribe(producto =>
      {
        this.router.navigate(['/productos'])
        swal.fire('Nuevo Producto', `Producto ${producto.nombre} creado con éxito`, 'success')
      }
    )
  }

  public update():void{
    this.productoService.update(this.producto).subscribe(producto => {
        swal.fire('Producto Actualizado', `${producto.nombre} actualizado exitosamente`, 'success')
        this.router.navigate(['/productos'])
      }
    )
  }
}
