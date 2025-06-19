package com.proyecto.entornos;

/**
 * Clase principal de ejemplo para probar la creación de productos, clientes y pedidos.
 * Muestra el historial de un cliente tras realizar pedidos y pagos.
 */
import com.proyecto.entornos.modelo.Producto;
import com.proyecto.entornos.modelo.Cliente;
import com.proyecto.entornos.modelo.Pedido;

public class App {

    /**
     * Método principal que crea ejemplos de productos, un cliente y realiza pedidos y pagos.
     * Finalmente, muestra el historial del cliente.
     */
    public static void main(String[] args) {
        Producto mango = new Producto("Mango", 3.75, 2); // 2 unidades
        Producto cocacola = new Producto("Coca-Cola", 1.50, 3);      // 3 unidades
        Producto panDeHigo = new Producto("Pan de higo", 2.00, 1);      // 1 unidad

        Cliente cliente = new Cliente("Wellington", "Q", "678123456", "Calle Falsa 123");

        Pedido ped = new Pedido(cliente, panDeHigo, panDeHigo);
        Pedido ped2 = new Pedido(cliente, panDeHigo, mango);
        ped.pagar("efectivo");
        ped2.pagar("efectivo");
        
        System.out.println(cliente.getHistorial());
    }
}
