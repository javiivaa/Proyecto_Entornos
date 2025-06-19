package gestion;

import modelo.Pedido;
import java.util.ArrayList;
import java.util.Scanner;
import ConexionBaseDatos.Conexion;
public class GestionPedido {
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void agregarPedido() {
        System.out.print("Ingrese descripción del pedido: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese ID del pedido: ");
        int id = Integer.parseInt(scanner.nextLine());

        Pedido pedido = new Pedido(id, descripcion);
        pedidos.add(pedido);
        System.out.println("Pedido agregado con éxito.");
    }

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

    public void eliminarPedido() {
        System.out.print("Ingrese ID del pedido a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        pedidos.removeIf(p -> p.getId() == id);
        System.out.println("Pedido eliminado si existía.");
    }
    public boolean comprobarStock(int productoId, int cantidadSolicitada) {
    return Conexion.hayStock(productoId, cantidadSolicitada);
}

    public boolean validarClienteParaPedido(int clienteId) {
        return Conexion.clienteValido(clienteId);
    }
    public void agregarPedido(int productoId1, int cantidad1, int productoId2, int cantidad2) {
        System.out.print("Ingrese ID del cliente: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine();
        if (!Conexion.clienteValido(clienteId)) {
            System.out.println("El cliente no existe. No se puede crear el pedido.");
            return;
        }
        Pedido pedido = new Pedido();
        pedido.setClienteId(clienteId);
        pedido.setProducto1Id(productoId1);
        pedido.setCantidad1(cantidad1);
        if (productoId2 != -1 && cantidad2 > 0) {
            pedido.setProducto2Id(productoId2);
            pedido.setCantidad2(cantidad2);
        }
        pedidos.add(pedido);
        Conexion.insertarPedido(clienteId, productoId1, cantidad1, productoId2, cantidad2);
        System.out.println("Pedido registrado y guardado en la base de datos.");
    }
}
