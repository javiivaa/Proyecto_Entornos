package util;



import java.util.Scanner;

public class PedidoUtil {

    public static void mostrarMenuPedido(Scanner scanner) {
        System.out.println("\n--- Menú Pedido ---");
        System.out.println("1. Agregar pedido");
        System.out.println("2. Listar pedidos");
        System.out.println("3. Modificar pedido");
        System.out.println("4. Eliminar pedido");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
               // System.out.println("Agregar pedido");
            	gestion.agregarPedido();
                break;
            case 2:
               // System.out.println("Listar pedidos");
            	gestion.listarPedidos();
                break;
            case 3:
               // System.out.println("Modificar pedido");
            	gestion.modificarPedido();
                break;
            case 4:
              //  System.out.println("Eliminar pedido");
            	gestion.eliminarPedido();
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
}
