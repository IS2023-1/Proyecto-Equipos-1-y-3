import { NotExpr } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ReportesService } from './reportes.service';
import {Location} from '@angular/common';
import { RbpService } from '../productos/rbp.service';
import { Producto } from '../productos/producto';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.css']
})
export class ReportesComponent implements OnInit {

  meses: string[] = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
           "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];

  mes = this.meses[new Date().getMonth()]
  anio = new Date().getFullYear().toString();

  activos: any = [];
  cincoMasRentados: any = [];
  inactivos: string = "";
  cincoMasBaratos: any = [];
  diezMasTarde: any = [];
  cincoMasRentas: any = [];

  productos: Producto[] = [];

  pr: Producto[] = [];

  constructor(private reporteService: ReportesService, private _location: Location,
              private authService: AuthService) { }

  ngOnInit(): void {
    this.getReportes(); 
  }

  getReportes(): void {

    this.reporteService.getProductos().subscribe(p => this.pr = p);

    var notAv = "Esta información no está disponible";

    this.reporteService.getActivos().subscribe(res => {
      if(res === -1) {
        document.getElementById("uno").innerHTML = notAv;
        document.getElementById("uno").style.color = "rgba(0, 0, 0, 0.5)"
      } else {
        this.activos = res;
        console.log(this.activos);
      }
    })

    this.reporteService.getCincoMasRentados().subscribe(res => {
      if(res === -1) {
        document.getElementById("dos").innerHTML = notAv;
        document.getElementById("dos").style.color = "rgba(0, 0, 0, 0.5)"
      } else {
        console.log(res);
        res.forEach(e => {
          this.reporteService.lookup(e).subscribe(prod => {
            if(prod !== -1)
            this.cincoMasRentados.push(prod)
          });
        });
        if(this.productos.length === 0) {
          document.getElementById("dos").innerHTML = `No se han rentado suficientes productos durante ${this.mes}`;
          document.getElementById("dos").style.color = "rgba(0, 0, 0, 0.5)"
        }
      }
    })

    this.reporteService.getInactivos().subscribe(res => {
      if(res === -1) {
        document.getElementById("tres").style.display = "none";
        document.getElementById("alt-tres").style.color = "rgba(0, 0, 0, 0.5)"
        document.getElementById("alt-tres").innerHTML = notAv;
      } else {
        this.inactivos = res.toString();
      }
    })

    this.reporteService.getCincoMasBaratos().subscribe(res => {
      if(res === -1) {
        document.getElementById("cuatro").innerHTML = notAv;
        document.getElementById("cuatro").style.color = "rgba(0, 0, 0, 0.5)"
      } else {
        res.forEach(e => {
          this.reporteService.lookup(e[0]).subscribe(prod => {
            if(prod !== -1)
              this.cincoMasBaratos.push(prod)
          });
        });
        if(res.length < 1) {
          document.getElementById("cuatro").innerHTML = `No hay suficientes productos registrados en la base de datos`;
          document.getElementById("cuatro").style.color = "rgba(0, 0, 0, 0.5)"
        }
      }

    })

    this.reporteService.getDiezMasTarde().subscribe(res => {
      if(res === -1) {
        document.getElementById("cinco").innerHTML = notAv;
        document.getElementById("cinco").style.color = "rgba(0, 0, 0, 0.5)"
      } else {
        res.forEach(u => {
          this.reporteService.getUsuario(u.nombre).subscribe(usu => {
            if(usu !== -1)
              this.diezMasTarde.push(u);
          });
        });
        if(res.length < 10) {
          document.getElementById("cinco").innerHTML = `No hay suficientes usuarios que hayan devuelto algún producto`;
          document.getElementById("cinco").style.color = "rgba(0, 0, 0, 0.5)"
        }
      }
    })

    this.reporteService.getCincoMasRentas().subscribe(res => {
      if(res === -1) {
        document.getElementById("seis").innerHTML = notAv;
        document.getElementById("seis").style.color = "rgba(0, 0, 0, 0.5)"
      } else {
        res.forEach(u => {
          this.reporteService.getUsuario(u.nombre).subscribe(usu => {
            if(usu !== -1)
              this.cincoMasRentas.push(u);
          });
        });
        if(res.length < 5) {
          document.getElementById("seis").innerHTML = `No hay suficientes usuarios que hayan rentado algún producto esta semana`;
          document.getElementById("seis").style.color = "rgba(0, 0, 0, 0.5)"
        }
      }
    })
  }

  backClick(): void {
    this._location.back();
  }

}
