package com.proyecto.entornos.negocio;

import com.proyecto.entornos.modelo.Cliente;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que gestiona la lógica de negocio de clientes.
 * Permite agregar, listar, modificar y eliminar clientes.
 */
public class GestionCliente {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Agrega un nuevo cliente a la lista.
     */
    public void agregarCliente() {
        System.out.print("Ingrese nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese apellidos del cliente: ");
        String apellidos = scanner.nextLine();
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        clientes.add(cliente);
        System.out.println("Cliente agregado con éxito.");
    }

    /**
     * Lista todos los clientes registrados.
     */
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        System.out.println("Listado de clientes:");
        for (Cliente c : clientes) {
            System.out.println("Nombre: " + c.getNombre() + " " + c.getApellidos());
        }
    }

    /**
     * Modifica un cliente existente por nombre.
     */
    public void modificarCliente() {
        System.out.print("Ingrese nombre del cliente a modificar: ");
        String nombre = scanner.nextLine();
        for (Cliente c : clientes) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                System.out.print("Nuevo nombre: ");
                c.setNombre(scanner.nextLine());
                System.out.println("Cliente modificado.");
                return;
            }
        }
        System.out.println("Cliente no encontrado.");
    }

    /**
     * Elimina un cliente de la lista por nombre.
     */
    public void eliminarCliente() {
        System.out.print("Ingrese nombre del cliente a eliminar: ");
        String nombre = scanner.nextLine();
        clientes.removeIf(c -> c.getNombre().equalsIgnoreCase(nombre));
        System.out.println("Cliente eliminado si existía.");
    }
}
