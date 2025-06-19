package vista;

import java.util.Scanner;
import controlador.Controlador;

public class Vista {
    private Controlador controlador;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Clientes");
            System.out.println("2. Productos");
            System.out.println("3. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    controlador.gestionarClientes(scanner);
                    break;
                case 2:
                    controlador.gestionarProductos(scanner);
                    break;
                case 3:
                    controlador.gestionarPedidos(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    public static void main(String[] args) {
        controlador.Controlador controlador = new controlador.Controlador();
        controlador.iniciarMenu();
    }
}
// Persistencia: Conexion.hayStock(...) consulta la base de datos.
//Lógica de negocio: GestionPedido.comprobarStock(...) aplica la regla antes de crear el pedido.
// Test: Llama a la lógica y muestra el resultado.

// Guion de Cambios Realizados
//  Organización y arquitectura
//Reestructuración del proyecto siguiendo el patrón MVC: separación clara entre modelo, vista, controlador, lógica de negocio y persistencia.
// Eliminación de clases duplicadas y conflictos de nombres (por ejemplo, Producto).

//  Modelo de negocio
// Adaptación de la clase Pedido para permitir hasta dos productos por pedido, con campos para IDs y cantidades.
// Añadido constructor vacío y setters/getters para integración con la lógica y la base de datos.

//  Lógica de negocio
// Refactorización de los menús en el controlador para delegar correctamente en las clases de gestión.
// Implementación de la comprobación de stock antes de permitir la creación de un pedido.
// Adaptación del flujo para permitir pedidos con uno o dos productos, validando stock de ambos.

//  Persistencia (base de datos)
// Creación de métodos en ConexionBaseDatos.Conexion para:
// Comprobar stock de productos.
// Validar existencia de clientes.
// Insertar pedidos con uno o dos productos y sus cantidades.
// Adaptación de la tabla Pedido para incluir campos cantidad1 y cantidad2.

// Integración y flujo completo
// Modificación de GestionPedido para:
// Solicitar datos de cliente y productos.
// Validar cliente y stock antes de crear el pedido.
// Guardar el pedido en la base de datos tras la validación.
// Eliminación del método main de Main.java y centralización del punto de entrada en Vista.java.

//  Utilidades y pruebas
// Ejemplo de test de validación de stock y cliente.
// Sugerencias para pruebas de integración y consultas.

// 7. Documentación y apoyo visual
// Generación de diagrama de clases PlantUML del modelo de negocio.
// Sugerencia de estructura de Trello para documentar el avance del proyecto.