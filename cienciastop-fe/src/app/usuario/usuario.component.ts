import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {


  // Arreglo de usuarios
  usuariosList: Usuario[] = [
    new Usuario("Jhone", "Standford",	"Jeffcoate",	895-288-6737,	"sjeffcoate0@blogtalkradio.com",	"Matematicas",	"admin1",	"VTe1sG9YBq",	5,	true),
    new Usuario("Kennith",	"Terry", 	"Orman",	310-145-7820,	"torman0@last.fm",	"Computacion",	"Admin1",	"Ax88xvrBsb",	19,	false),
    new Usuario("Helenka",	"Boy",	"Greasley",	508-391-8315,	"bgreasley1@dmoz.org",	"Biologia",	"Admin2",	"SOAOVLN3",	82,	false),
    new Usuario("Irena",	"Juliette",	"Orta",	341-947-1460,	"jorta2@seattletimes.com",	"Derecho",	"admin2",	"do1jg2l6yKKN",	41,	false),
    new Usuario("Nonah",	"Inez",	"Loud",	590-337-4676,	"iloud3@pcworld.com",	"Fisica",	"Admin2",	"mhn5ajZsK8R",	35,	true),
    new Usuario("Tobe",	"Katerine",	"Philip",	181-157-1167,	"kphilip4@stanford.edu",	"Fisica",	"admin3",	"Tt2GhyIy8JvS",	9,	true)
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
