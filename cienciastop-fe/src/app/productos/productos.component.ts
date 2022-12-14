import { Component, OnInit, OnDestroy } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import Swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../usuarios/auth.service';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {
  producto : Producto
  productos: Producto[] = [];

  matches: Producto[] = [];

  searchInput: string = "";
  placeholder: string = "";


  constructor(private productoService: ProductoService,  private router: Router, private activatedRoute: ActivatedRoute, public authService: AuthService) { }

  ngOnInit(): void {
    this.getProductos()
  }

  public getProductos(): void {
    this.productoService.getProductos().subscribe(productos => this.productos = productos)
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
      title: 'Estas seguro?',
      text: `¿Seguro que desea eliminar el producto ${producto.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.productoService.delete(producto.id_producto).subscribe(
          Response => {
            this.productos =  this.productos.filter(prod => prod !== producto)
            swalWithBootstrapButtons.fire(
              'Producto Eliminado!',
              'Producto eliminado con éxito.',
              'success'
            )
          }
        )

      }
    })
    
  }
  

  getMatches(input: string): Producto[] {
    var matches = []
    this.productos.forEach(e => {
      if(e.nombre.toLowerCase().includes(input)) {
        matches.push(e);
      } else if (Number(input)) {
        if (e.id_producto === Number(input)) {
          matches.push(e);
        }
      }
    })
    
    return matches;
  }

  onSubmit(): void {
    this.matches = [];
    this.productoService.getProductos().subscribe(p => {
      this.productos = p;
      var input = this.searchInput.trim().toLowerCase();
      if(!input || input.length == 0) {
        
      } else {
        this.matches = this.getMatches(input);
        document.getElementById("res-bus-producto").style.display = "none";
        this.productos = this.matches;
      }

      if(this.matches.length == 0) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: `No hubo resultados para la búsqueda "${this.searchInput.trim()}"`,
        });
        this.clear();
      } else {
        this.placeholder = this.searchInput;
        document.getElementById("res-bus-producto").style.display = "block";
        this.productos = this.matches;
      }
    });
  }

  clear(): void {
    document.getElementById("res-bus-producto").style.display = "none";
    this.searchInput = "";
    this.productoService.getProductos().subscribe(p => this.productos = p);
  }
}
