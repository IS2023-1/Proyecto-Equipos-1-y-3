import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { Observable } from 'rxjs';
import { AuthService } from '../usuarios/auth.service';


@Component({
  selector: 'app-form',
  templateUrl: './form-editar.component.html',
  styleUrls: ['./form-editar.component.css']
})
export class FormEditarComponent implements OnInit {

  titulo: string = "Editar Producto"
  producto: Producto = new Producto()

  constructor(private productoService: ProductoService, private router: Router, private activateRoute: ActivatedRoute, public authService: AuthService) { }

  ngOnInit(): void {
    this.getProducto();
  }

  cargarProducto(): void{
    this.activateRoute.params.subscribe(params => {
      let codigo = params['codigo']
      if(codigo){
        this.productoService.getProducto(this.producto.codigo).subscribe((producto)=>this.producto = producto)
      }
    })
  }

  public getProducto(): void {
    const codigo = Number(this.activateRoute.snapshot.paramMap.get('codigo'));
    this.productoService.getProducto(codigo).subscribe(p => this.producto = p);
  }


  public update():void{
    this.productoService.update(this.producto).subscribe(producto => {
        swal.fire('Producto Actualizado', `${producto.nombre} actualizado exitosamente`, 'success')
        this.router.navigate(['/productos'])
      }
    )
  }
}
