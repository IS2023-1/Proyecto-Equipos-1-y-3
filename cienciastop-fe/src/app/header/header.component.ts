import { Component, OnInit } from '@angular/core';
import { AuthService } from '../usuarios/auth.service';
import {Router} from '@angular/router';
import Swal from 'sweetalert2'; 

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public authService:AuthService, private router: Router ) { }

  logout():void {
    let username = this.authService.usuario.username;
    this.authService.logout();
    Swal.fire('Logout', `Hola ${username}, ha cerrado sesión con éxito!`, 'success');
    this.router.navigate(['/login']);
  }
  
  ngOnInit(): void {

  }

  searchInput: string = "";
}
