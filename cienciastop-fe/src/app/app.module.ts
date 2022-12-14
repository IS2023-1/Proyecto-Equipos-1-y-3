import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ProductosComponent } from './productos/productos.component';
import { RouterModule, Routes } from '@angular/router';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { HttpClientModule } from '@angular/common/http';
import { FormComponent } from './productos/agregar-producto.component';
import { ProductoComponent } from './productos/producto.component';
import { FormEditarComponent } from './productos/form-editar.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './usuarios/login.component';
import { RbpComponent } from './productos/rbp.component';
import { PerfilComponent } from './usuarios/perfil.component';
import { FormUsuarioComponent } from './usuarios/form-usuario.component';
import { AgregarUsuariosComponent } from './usuarios/agregar-usuarios.component';
import { RecuperarComponent } from './usuarios/recuperar.component';
import { HistorialComponent } from './usuarios/historial.component';

const routes: Routes = [
  { path: '', redirectTo: '/productos', pathMatch: 'full' },
  { path: 'usuarios', component: UsuariosComponent },
  { path: 'usuarios/buscar/todo', component: UsuariosComponent },
  { path: 'usuarios/agregar-usuarios', component: AgregarUsuariosComponent },
  //{ path: 'usuarios/perfil', component: PerfilComponent },
  { path: 'usuarios/buscar/', component: PerfilComponent },
  { path: 'usuarios/buscar/:id', component: PerfilComponent },
  { path: 'productos/buscar/', component: ProductoComponent },
  { path: 'productos/buscar/codigo/:codigo', component: ProductoComponent },
  //{ path: 'usuarios/perfil/:id', component: PerfilComponent },
  { path: 'usuarios/buscar/:id/editar/:id', component: FormUsuarioComponent },
  { path: 'productos', component: ProductosComponent },
  { path: 'productos/agregar-producto', component: FormComponent },
  { path: 'productos/buscar/codigo/:codigo/editar/:codigo', component: FormEditarComponent },
  { path: 'productos/form/:id', component: FormEditarComponent },
  { path: 'login', component: LoginComponent },
  { path: 'productos/rbp/:input', component: RbpComponent },
  { path: 'recuperar', component: RecuperarComponent },
  { path: 'historial/:id', component: HistorialComponent },];


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ProductosComponent,
    UsuariosComponent,
    FormComponent,
    FormEditarComponent,
    LoginComponent,
    PerfilComponent,
    ProductoComponent,
    RbpComponent,
    FormUsuarioComponent,
    AgregarUsuariosComponent,
    RecuperarComponent,
    HistorialComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [HeaderComponent, RbpComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
