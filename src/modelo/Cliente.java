package modelo;
import java.sql.Date;

public class Cliente {

    private String nombre;
    private String apellidos;
    private Date fechaDeAlta;
    private String telefono;
    private String direccion;
    private String historial; // aqu�� guardaremos los c�digos de pedidos pagados
    private static int contadorClientes=0;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(Date fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    // Constructor vac��o
    public Cliente() {
    }

    // Constructor con fecha de alta específica. Validaci�n de tel�fono inclu�da.
    public Cliente(String nombre, String apellidos, Date fechaDeAlta, String telefono, String direccion) {
          if (telefono == null || 
            telefono.length() != 9 || 
            !(telefono.startsWith("6") || telefono.startsWith("7") || 
              telefono.startsWith("8") || telefono.startsWith("9"))) {
            System.out.println("El tel�fono proporcionado no es v�lido. No se puede crear el cliente.");
            return; // Cliente inv�lido, el objeto queda sin inicializar al no cunplirse las reestricciones del tel�fono.
        }
        this.nombre = nombre.toLowerCase();
        this.apellidos = apellidos.toUpperCase();
        this.fechaDeAlta = fechaDeAlta;
        this.telefono = telefono;
        this.direccion = direccion;
        this.historial = "";
        contadorClientes++;
    }

    // Constructor que inicializa la fecha de alta al momento.Validaci�n de tel�fono inclu�da.
    public Cliente(String nombre, String apellidos, String telefono, String direccion) {
          if (telefono == null || 
            telefono.length() != 9 || 
            !(telefono.startsWith("6") || telefono.startsWith("7") || 
              telefono.startsWith("8") || telefono.startsWith("9"))) {
            System.out.println("El tel�fono proporcionado no es v�lido. No se puede crear el cliente.");
            return; // Cliente inv�lido, el objeto queda sin inicializar al no cunplirse las reestricciones del tel�fono.
        }
        this.nombre = nombre.toLowerCase();
        this.apellidos = apellidos.toUpperCase();
        this.fechaDeAlta = new Date();
        this.telefono = telefono;
        this.direccion = direccion;
        this.historial = "";
         contadorClientes++;
    }

  
   public void agregarPedido(Pedido pedido) {
        if (pedido != null && pedido.getPago() != null && pedido.getPago().getCodigoPago() > 0) {
            this.historial += pedido.getPago().getCodigoPago() + " ";
        } else {
            System.out.println("El pedido no est� pagado. No se puede agregar al historial.");
        }
    }

    public static int getContadorClientes() {
        return contadorClientes;
    }

    public static void setContadorClientes(int contadorClientes) {
        Cliente.contadorClientes = contadorClientes;
    }

}
