import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root'
})
export class ServicioProductoService {
  [x: string]: any;

  //Url apis
  private urlApi = 'http://localhost:8080/v1/api/producto/';

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Producto[]> {
    return this.httpClient.get<Producto[]>(this.urlApi + 'lista');
  }

  public details(id: number): Observable<Producto> {
    return this.httpClient.get<Producto>(this.urlApi +`details/${id}`);
  }

  public detailName(nombre: string): Observable<Producto> {
    return this.httpClient.get<Producto>(this.urlApi + `detailname/${nombre}`);
  }

  public create(producto: Producto): Observable<any> {
    return this.httpClient.post<Producto>(this.urlApi + 'create', producto);
  }


  public update(id: number, producto: Producto): Observable<any> {
    return this.httpClient.put<any>(this.urlApi + `update/${id}`, producto);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<Producto>(this.urlApi + `delete/${id}`);
  }



}
