package com.proyecto.entornos;

import java.util.Scanner;
import com.proyecto.entornos.modelo.Cliente;
import com.proyecto.entornos.modelo.Producto;

/**
 * Clase de ejemplo para gestionar clientes y productos desde consola.
 * Inicializa datos de ejemplo y muestra menús de gestión.
 */
public class GestionPedidos {
    public static void main(String[] args) {
        MetodosGestionPedidos.cliente1 = new Cliente("Juan", "Pérez", "612345678", "Calle Falsa 123");
        MetodosGestionPedidos.cliente2 = new Cliente("Ana", "García", "722345678", "Avenida Siempreviva 456");
        MetodosGestionPedidos.cliente3 = new Cliente("Luis", "Martínez", "832345678", "Plaza Mayor 789");
        MetodosGestionPedidos.producto1 = new Producto("Producto A", 12.5, 10);
        MetodosGestionPedidos.producto2 = new Producto("Producto B", 8.75, 5);
        MetodosGestionPedidos.producto3 = new Producto("Producto C", 15.0, 8);
        MetodosGestionPedidos.producto4 = new Producto("Producto D", 20.0, 3);
        MetodosGestionPedidos.producto5 = new Producto("Producto E", 5.0, 20);
        MetodosGestionPedidos.mostrarMenuInicial();
        MetodosGestionPedidos.mostrarMenuPedidos();
    }
}
