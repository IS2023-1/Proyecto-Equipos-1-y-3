import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductoService } from './producto.service';
import { Producto } from './producto';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AuthService } from '../usuarios/auth.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit {
  p: Producto = new Producto();//| undefined;

  constructor(private productoService: ProductoService, private router: Router, private activatedRoute: ActivatedRoute, public authService: AuthService) {
    let params: any = this.activatedRoute.snapshot.params;
  }

  ngOnInit(): void {
    this.getProducto();
  }


  public getProducto(): void {
    const codigo = Number(this.activatedRoute.snapshot.paramMap.get('codigo'));
    this.productoService.getProducto(codigo).subscribe(p => this.p = p);
  }

  delete(producto: Producto): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
      swalWithBootstrapButtons.fire({
        title: '¿Estás seguro?',
        text: '¿Estás seguro de eliminar el producto ' + `${producto.nombre}`+'?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'No, cancelar!',
        reverseButtons: true
      }).then((result) => {
        if (result.isConfirmed) {
          this.productoService.delete(producto.id_producto).subscribe(
            Response => {
              swalWithBootstrapButtons.fire(
                'Producto eliminado!',
                '¡Producto eliminado con éxito!.',
                'success'
              )
              this.router.navigate(['/productos'])
            }
          )
        }
      })
  }
}
