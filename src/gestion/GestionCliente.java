package gestion;

import modelo.Cliente;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionCliente {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

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

    public void modificarCliente() {
        System.out.print("Ingrese nombre del cliente a modificar: ");
        String nombre = scanner.nextLine();
        for (Cliente c : clientes) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                System.out.print("Nuevo nombre: ");
                c.setNombre(scanner.nextLine());
                System.out.print("Nuevos apellidos: ");
                c.setApellidos(scanner.nextLine());
                System.out.println("Cliente modificado.");
                return;
            }
        }
        System.out.println("Cliente no encontrado.");
    }

    public void eliminarCliente() {
        System.out.print("Ingrese nombre del cliente a eliminar: ");
        String nombre = scanner.nextLine();
        clientes.removeIf(c -> c.getNombre().equalsIgnoreCase(nombre));
        System.out.println("Cliente eliminado si existía.");
    }
}
