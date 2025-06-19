
import java.util.Scanner;

/**

 * Ejemplo tlf valido 966781544
 * //MasterCard valida: 5234 5678 9101 1121
 */
public class GestionPedidos {

    public static void main(String[] args) {
       
        MetodosGestionPedidos.cliente1 = new Cliente("Juan", "P�rez", "612345678", "Calle Falsa 123");
        MetodosGestionPedidos.cliente2 = new Cliente("Ana", "Garc�a", "722345678", "Avenida Siempreviva 456");
        MetodosGestionPedidos.cliente3 = new Cliente("Luis", "Mart�nez", "832345678", "Plaza Mayor 789");

       
        MetodosGestionPedidos.producto1 = new Producto("Producto A", 12.5, 10);
        MetodosGestionPedidos.producto2 = new Producto("Producto B", 8.75, 5);
        MetodosGestionPedidos.producto3 = new Producto("Producto C", 15.0, 8);
        MetodosGestionPedidos.producto4 = new Producto("Producto D", 20.0, 3);
        MetodosGestionPedidos.producto5 = new Producto("Producto E", 5.0, 20);

        MetodosGestionPedidos.mostrarMenuInicial();
        MetodosGestionPedidos.mostrarMenuPedidos();
        
        
    }
}