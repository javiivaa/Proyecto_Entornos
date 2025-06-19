package com.proyecto.entornos;

import java.util.Scanner;
import com.proyecto.entornos.modelo.Cliente;
import com.proyecto.entornos.modelo.Producto;

public class MetodosGestionPedidos {
    static Scanner scan = new Scanner(System.in);
    static Cliente cliente1 = null;
    static Cliente cliente2 = null;
    static Cliente cliente3 = null;
    static Producto producto1 = null;
    static Producto producto2 = null;
    static Producto producto3 = null;
    static Producto producto4 = null;
    static Producto producto5 = null;

    public static void mostrarMenuInicial() {
        while (Cliente.getContadorClientes() < 3 || Producto.getContadorProductos() < 5) {
            System.out.println("");
            System.out.println("##### MENÚ INICIAL #####");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Crear Producto");
            System.out.print("Seleccione una opción: ");
            if (scan.hasNextInt()) {
                int opcion = scan.nextInt();
                scan.nextLine();
                switch (opcion) {
                    case 1:
                        if (Cliente.getContadorClientes() < 3) {
                            crearCliente();
                        } else {
                            System.out.println("Ya se han creado los 3 clientes necesarios.");
                        }
                        break;
                    case 2:
                        if (Producto.getContadorProductos() < 5) {
                            crearProducto();
                        } else {
                            System.out.println("Ya se han creado los 5 productos necesarios.");
                        }
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese 1 o 2.");
                scan.nextLine();
            }
        }
    }
    // ...resto de métodos...
}
