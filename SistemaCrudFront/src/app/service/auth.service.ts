import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { LoginUsuario } from '../models/login-usuario';
import { JwtDto } from '../models/jwt-dto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  //url
  authUrl = 'http://localhost:8080/auth/';
  
  constructor(private httpClient:HttpClient) { }

  /***
   * Servicio para registrar nuevo usuario en la base de datos
   */
  public nuevo(nuevoUsuario: NuevoUsuario):Observable<any>{
     return this.httpClient.post<any>(this.authUrl+'nuevo',nuevoUsuario);
  }

  /***
   * Servicio para iniciar sesion en el aplicativo
   */
  public login(loginUsuario: LoginUsuario):Observable<JwtDto>{
    return this.httpClient.post<JwtDto>(this.authUrl+'login',loginUsuario);
  }  


}
