import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// external
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ListProductoComponent } from './producto/list-producto/list-producto.component';
import { DetalleProductoComponent } from './producto/detalle-producto/detalle-producto.component';
import { CrearProductoComponent } from './producto/crear-producto/crear-producto.component';
import { ActualizarProductoComponent } from './producto/actualizar-producto/actualizar-producto.component';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegistroComponent } from './auth/login/registro.component';
import { IndexComponent } from './index/index.component';
import { MenuComponent } from './menu/menu.component';

@NgModule({
  declarations: [
    AppComponent,
    ListProductoComponent,
    DetalleProductoComponent,
    CrearProductoComponent,
    ActualizarProductoComponent,
    LoginComponent,
    RegistroComponent,
    IndexComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
