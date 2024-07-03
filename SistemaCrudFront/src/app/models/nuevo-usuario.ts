export class NuevoUsuario {
    nombre:string | undefined;
    nombreUsuario:string | undefined;
    email:string | undefined;
    password:string | undefined;
   
    constructor(nombre:string, nombreUsuario:string, email:string, password:string){
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

}
