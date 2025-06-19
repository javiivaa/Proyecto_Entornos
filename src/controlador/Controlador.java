package controlador;

import vista.Vista;
import gestion.GestionCliente;
import gestion.GestionProducto;
import gestion.GestionPedido;
import java.util.Scanner;

public class Controlador {
    private Vista vista;
    private GestionCliente gestionCliente;
    private GestionProducto gestionProducto;
    private GestionPedido gestionPedido;

    public Controlador() {
        this.gestionCliente = new GestionCliente();
        this.gestionProducto = new GestionProducto();
        this.gestionPedido = new GestionPedido();
        this.vista = new Vista(this);
    }

    public void iniciarMenu() {
        vista.mostrarMenu();
    }

    public void gestionarClientes(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n--- Menú Cliente ---");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Modificar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1:
                    gestionCliente.agregarCliente();
                    break;
                case 2:
                    gestionCliente.listarClientes();
                    break;
                case 3:
                    gestionCliente.modificarCliente();
                    break;
                case 4:
                    gestionCliente.eliminarCliente();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    public void gestionarProductos(Scanner scanner) {
        // Lógica similar para productos
    }

    public void gestionarPedidos(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n--- Menú Pedido ---");
            System.out.println("1. Agregar pedido");
            System.out.println("2. Listar pedidos");
            System.out.println("3. Modificar pedido");
            System.out.println("4. Eliminar pedido");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID del primer producto: ");
                    int productoId1 = scanner.nextInt();
                    System.out.print("Ingrese cantidad del primer producto: ");
                    int cantidad1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("¿Desea añadir un segundo producto? (s/n): ");
                    String respuesta = scanner.nextLine();
                    int productoId2 = -1;
                    int cantidad2 = 0;
                    boolean segundoProducto = respuesta.equalsIgnoreCase("s");
                    if (segundoProducto) {
                        System.out.print("Ingrese ID del segundo producto: ");
                        productoId2 = scanner.nextInt();
                        System.out.print("Ingrese cantidad del segundo producto: ");
                        cantidad2 = scanner.nextInt();
                        scanner.nextLine();
                    }
                    boolean stock1 = gestionPedido.comprobarStock(productoId1, cantidad1);
                    boolean stock2 = true;
                    if (segundoProducto) {
                        stock2 = gestionPedido.comprobarStock(productoId2, cantidad2);
                    }
                    if (stock1 && stock2) {
                        gestionPedido.agregarPedido(productoId1, cantidad1, productoId2, cantidad2);
                        System.out.println("Pedido agregado correctamente.");
                    } else {
                        System.out.println("No hay stock suficiente para uno o ambos productos.");
                    }
                    break;
                case 2:
                    gestionPedido.listarPedidos();
                    break;
                case 3:
                    gestionPedido.modificarPedido();
                    break;
                case 4:
                    gestionPedido.eliminarPedido();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
