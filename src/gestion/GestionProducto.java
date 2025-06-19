package gestion;


import modelo.Producto;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionProducto {
    private ArrayList<Producto> productos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void agregarProducto() {
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID del producto: ");
        int id = Integer.parseInt(scanner.nextLine());

        Producto producto = new Producto(id, nombre);
        productos.add(producto);
        System.out.println("Producto agregado con éxito.");
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("Listado de productos:");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public void modificarProducto() {
        System.out.print("Ingrese ID del producto a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (Producto p : productos) {
            if (p.getId() == id) {
                System.out.print("Nuevo nombre: ");
                p.setNombre(scanner.nextLine());
                System.out.println("Producto modificado.");
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    public void eliminarProducto() {
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        productos.removeIf(p -> p.getId() == id);
        System.out.println("Producto eliminado si existía.");
    }
}
