package com.proyecto.entornos.negocio;

import com.proyecto.entornos.modelo.Pedido;
import java.util.ArrayList;
import java.util.Scanner;
import com.proyecto.entornos.ConexionBaseDatos.Conexion;

/**
 * Clase que gestiona la lógica de negocio de pedidos.
 * Permite agregar, listar, modificar y eliminar pedidos.
 */
public class GestionPedido {
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Agrega un nuevo pedido a la lista.
     */
    public void agregarPedido() {
        System.out.print("Ingrese descripción del pedido: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese ID del pedido: ");
        int id = Integer.parseInt(scanner.nextLine());

        Pedido pedido = new Pedido(id, descripcion);
        pedidos.add(pedido);
        System.out.println("Pedido agregado con éxito.");
    }

    /**
     * Lista todos los pedidos registrados.
     */
    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        System.out.println("Listado de pedidos:");
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }

    /**
     * Modifica un pedido existente por ID.
     */
    public void modificarPedido() {
        System.out.print("Ingrese ID del pedido a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                System.out.print("Nueva descripción: ");
                p.setDescripcion(scanner.nextLine());
                System.out.println("Pedido modificado.");
                return;
            }
        }
        System.out.println("Pedido no encontrado.");
    }

    /**
     * Elimina un pedido de la lista por ID.
     */
    public void eliminarPedido() {
        System.out.print("Ingrese ID del pedido a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        pedidos.removeIf(p -> p.getId() == id);
        System.out.println("Pedido eliminado si existía.");
    }
}
