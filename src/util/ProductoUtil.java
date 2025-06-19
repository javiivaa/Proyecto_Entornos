package util;



import java.util.Scanner;

public class ProductoUtil {

    public static void mostrarMenuProducto(Scanner scanner) {
        System.out.println("\n--- Menú Producto ---");
        System.out.println("1. Agregar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Modificar producto");
        System.out.println("4. Eliminar producto");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
              //  System.out.println("Agregar producto");
            	gestion.agregarProducto();
                break;
            case 2:
               // System.out.println("Listar productos");
            	gestion.listarProductos();
                break;
            case 3:
               // System.out.println("Modificar producto");
                gestion.modificarProducto();
                break;
            case 4:
               // System.out.println("Eliminar producto");
                gestion.eliminarProducto();
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
}
