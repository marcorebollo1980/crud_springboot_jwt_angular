import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Producto } from 'src/app/models/producto';
import { ServicioProductoService } from 'src/app/service/servicio-producto.service';

@Component({
  selector: 'app-list-producto',
  templateUrl: './list-producto.component.html',
  styleUrls: ['./list-producto.component.css']
})
export class ListProductoComponent {

   //atributos de la clase
   producto: Producto[] = [];

   constructor(
    private productoService: ServicioProductoService,
    private toastr: ToastrService,
    private router: Router
    ){}

    ngOnInit(): void {
       this.cargarProductos(); 
    }

    /**
     * Metodo que permite recuperar todos los productos
     */
    cargarProductos(): void {
       this.productoService.lista().subscribe(
          data => {
             this.producto = data;   
          }, err => {
             console.log(err); 
          }
       );
    }

    borrar(id: any) {
      this.productoService.delete(id).subscribe(
        data => {
          this.toastr.success('Producto Eliminado', 'OK', {
            timeOut: 3000, positionClass: 'toast-top-center'
          });
          this.cargarProductos();
        },
        err => {
          this.toastr.error(err.error.mensaje, 'Fail', {
            timeOut: 3000,  positionClass: 'toast-top-center',
          });
        }
      );
    }

    abrirDetalleProducto(p: any){

    }
   
    abrirUpdateProducto(p:any){

    }





}
