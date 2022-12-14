import { NotExpr } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ReportesService } from './reportes.service';
import {Location} from '@angular/common';

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

  activos: any;
  cincoMasRentados: any;
  inactivos: string = "";
  cincoMasBaratos: any;
  diezMasTarde: any;
  cincoMasRentas: any;

  constructor(private reporteService: ReportesService, private _location: Location) { }

  ngOnInit(): void {
    this.getReportes(); 
  }

  getReportes(): void {
    var notAv = "Esta información no está disponible";

    this.reporteService.getActivos().subscribe(res => {
      if(res === -1) {
        document.getElementById("uno").innerHTML = notAv;
        document.getElementById("uno").style.color = "rgba(0, 0, 0, 0.5)"
      } else {
        this.activos = res;
      }
    })

    this.reporteService.getCincoMasRentados().subscribe(res => {
      if(res === -1) {
        document.getElementById("dos").innerHTML = notAv;
        document.getElementById("dos").style.color = "rgba(0, 0, 0, 0.5)"
      } else {
        this.cincoMasRentados = res;
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
        this.cincoMasBaratos = res;
      }
    })

    this.reporteService.getDiezMasTarde().subscribe(res => {
      if(res === -1) {
        document.getElementById("cinco").innerHTML = notAv;
        document.getElementById("cinco").style.color = "rgba(0, 0, 0, 0.5)"
      } else {
        this.diezMasTarde = res;
      }
    })

    this.reporteService.getCincoMasRentados().subscribe(res => {
      if(res === -1) {
        document.getElementById("seis").innerHTML = notAv;
        document.getElementById("seis").style.color = "rgba(0, 0, 0, 0.5)"
      } else {
        this.cincoMasRentas = res;
      }
    })
  }

  backClick(): void {
    this._location.back();
  }

}
