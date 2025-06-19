package com.proyecto.entornos.negocio;

import com.proyecto.entornos.modelo.Producto;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que gestiona la lógica de negocio de productos.
 * Permite agregar, listar, modificar y eliminar productos.
 */
public class GestionProducto {
    private ArrayList<Producto> productos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Agrega un nuevo producto a la lista.
     */
    public void agregarProducto() {
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID del producto: ");
        int id = Integer.parseInt(scanner.nextLine());

        Producto producto = new Producto(id, nombre);
        productos.add(producto);
        System.out.println("Producto agregado con éxito.");
    }

    /**
     * Lista todos los productos registrados.
     */
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

    /**
     * Modifica un producto existente por ID.
     */
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

    /**
     * Elimina un producto de la lista por ID.
     */
    public void eliminarProducto() {
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        productos.removeIf(p -> p.getId() == id);
        System.out.println("Producto eliminado si existía.");
    }
}
