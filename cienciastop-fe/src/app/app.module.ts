import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ProductosComponent } from './productos/productos.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { FormComponent } from './productos/form.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { RbpComponent } from './productos/rbp.component';
import { PerfilComponent } from './usuarios/perfil.component';

const routes: Routes = [
  {path: "", redirectTo: "/productos", pathMatch: "full"},
  {path: '', redirectTo: '/productos', pathMatch: 'full'},
  {path: 'usuarios', component: UsuariosComponent },
  {path: 'usuarios/perfil', component: PerfilComponent },
  {path: 'usuarios/perfil/:id', component: PerfilComponent },
  {path: 'productos', component: ProductosComponent},
  {path: 'productos/form', component: FormComponent},
  {path: 'productos/form/:id', component: FormComponent},
  {path: 'productos/rbp', component: RbpComponent}]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ProductosComponent,
    PerfilComponent,
    UsuariosComponent,
    FormComponent,
    RbpComponent,
    PerfilComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule
  ],
  providers: [HeaderComponent, RbpComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
