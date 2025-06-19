package modelo;

public class Producto {

    private String nombre; 
    private double precio;
    private int cantidad;
    private static int contadorProductos=0;
    
    // Constructor vacío
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre.toUpperCase();
        // Aseguramos 2 decimales en el precio
       
        this.cantidad = cantidad;
        contadorProductos++;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public static void setContadorProductos(int contadorProductos) {
        Producto.contadorProductos = contadorProductos;
    }

    public static int getContadorProductos() {
        return contadorProductos;
    }
    
    
}
