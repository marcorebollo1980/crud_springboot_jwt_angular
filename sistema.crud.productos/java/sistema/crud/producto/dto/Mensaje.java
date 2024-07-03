package sistema.crud.producto.dto;

public class Mensaje {
	
	public  String mensaje;

    public Mensaje(String contenido) {
        this.mensaje = contenido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
