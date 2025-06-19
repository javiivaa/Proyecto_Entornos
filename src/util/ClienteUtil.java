package util;



import java.util.Scanner;

public class ClienteUtil {

    public static void mostrarMenuCliente(Scanner scanner) {
        System.out.println("\n--- Menú Cliente ---");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Modificar cliente");
        System.out.println("4. Eliminar cliente");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                //System.out.println("Agregar cliente");
            	gestion.agregarCliente();
                break;
            case 2:
               // System.out.println("Listar clientes");
            	gestion.listarCliente();
                break;
            case 3:
                // System.out.println("Modificar cliente");
            	gestion.modificarCliente();
                break;
            case 4:
                // System.out.println("Eliminar cliente");
            	gestion.eliminarCliente();
                break;
            default:
                System.out.println("Opción no válida");
            	
        }
    }
}
