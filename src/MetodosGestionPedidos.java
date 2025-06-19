import java.util.Scanner;

/**
 *

 */
public class MetodosGestionPedidos {

//Scanner static (compartido a nivel de clase), para usarlo en todos los m�todos.
    static Scanner scan = new Scanner(System.in);

    //La clase contiene tres Clientes que inicialmente son null
    static Cliente cliente1 = null;
    static Cliente cliente2 = null;
    static Cliente cliente3 = null;

    //La clase contiene tres Productos que inicialmente son null
    static Producto producto1 = null;
    static Producto producto2 = null;
    static Producto producto3 = null;
    static Producto producto4 = null;
    static Producto producto5 = null;

    public static void mostrarMenuInicial() {

        // Mientras no se hayan creado los 3 clientes o los 5 productos
        while (Cliente.getContadorClientes() < 3 || Producto.getContadorProductos() < 5) {
            System.out.println("");
            System.out.println("##### MEN� INICIAL #####");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Crear Producto");
            System.out.print("Seleccione una opci�n: ");

            // Verificar si la entrada es un n�mero entero antes de hacer int opcion = scan.nextInt();
            if (scan.hasNextInt()) {
                int opcion = scan.nextInt(); // Leer la opci�n ingresada
                scan.nextLine(); // Consumir salto de l�nea

                switch (opcion) {
                    case 1: // Comprobamos contador antes de llamar a crearCliente()
                        if (Cliente.getContadorClientes() < 3) {
                            crearCliente();
                        } else {
                            System.out.println("Ya se han creado los 3 clientes necesarios.");
                        }
                        break;

                    case 2: // Comprobamos contador antes de llamar a crearProducto()
                        if (Producto.getContadorProductos() < 5) {
                            crearProducto();
                        } else {
                            System.out.println("Ya se han creado los 5 productos necesarios.");
                        }
                        break;

                    default: // Manejar opci�n no v�lida (entero v�lido pero que no es una opci�n)
                        System.out.println("Opci�n no v�lida. Intente nuevamente.");
                }
            } else {
                System.out.println("Entrada no v�lida. Por favor, ingrese 1 o 2."); // Entrada inv�lida
                scan.nextLine(); // Consumir la entrada no v�lida.
            }
        }

        // Confirmar que se han completado los datos
        System.out.println("");
        System.out.println("Se han completado la creaci�n de 3 clientes y 5 productos.");
    }
    public static void mostrarMenuPedidos() {
        while (true) {
            System.out.println("\n##### MEN� PEDIDOS #####");
            System.out.println("1. Realizar pedido");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opci�n: ");

            if (scan.hasNextInt()) {
                int opcion = scan.nextInt();
                scan.nextLine(); // Consumir salto de l�nea

                switch (opcion) {
                    case 1:
                        realizarPedido();
                        break;
                    case 2:
                        System.out.println("Saliendo del sistema de pedidos.");
                        return;
                    default:
                        System.out.println("Opci�n no v�lida. Intente nuevamente.");
                }
            } else {
                System.out.println("Entrada no v�lida. Por favor, ingrese un n�mero.");
                scan.nextLine(); // Consumir entrada no v�lida
            }
        }
    }
 public static void realizarPedido() {
     System.out.println("metodo realizarPedido()");
    // Seleccionar cliente, seleccionarClientePorTelefono() va a devolver al Cliente cuyo tel�fono coincida.
    Cliente cliente = seleccionarClientePorTelefono();
    if (cliente == null) {
        System.out.println("No se encontr� un cliente con el tel�fono proporcionado.");
        return;
    }

    // Inicializamos las variables para los productos que vayan a ser seleccionados.
    Producto producto1 = null;
    Producto producto2 = null;

    // Seleccionar productos
    while (true) {
        Producto producto = seleccionarProducto(); //Igual que con cliente, devolver� el producto 
        if (producto != null) {
            if (producto1 == null) {
                producto1 = producto;
                System.out.println("Producto 1 agregado: " + producto.getNombre());
            } else if (producto2 == null) {
                producto2 = producto;
                System.out.println("Producto 2 agregado: " + producto.getNombre());
            } else {
                System.out.println("Ya ha seleccionado dos productos. No se pueden agregar m�s.");
                break;
            }
        } else {
            break; // Salir si no se selecciona producto
        }
    }

    if (producto1 == null && producto2 == null) {
        System.out.println("No se seleccionaron productos. Pedido cancelado.");
        return;
    }

    // Crear el pedido con los productos seleccionados en los pasos anteriores.
    Pedido pedido = new Pedido(cliente, producto1, producto2);
    System.out.println("Pedido creado con importe inicial: " + pedido.getImporteTotal());

    // Realizar pago sobre el pedido creado.
    realizarPago(pedido);

    // Agregar pedido al historial del cliente
    if (pedido.getEstado() == Pedido.Estado.PAGADO) {
        cliente.agregarPedido(pedido);
        System.out.println("Pedido realizado con �xito y agregado al historial del cliente.");
    } else {
        System.out.println("El pedido no se pudo completar porque no se realiz� el pago.");
    }
}

     private static Cliente seleccionarClientePorTelefono() {
        System.out.print("Ingrese el tel�fono del cliente: ");
        String telefono = scan.nextLine();
//Vamos comparando lo le�do por consola con los tel�fonos de los clientes, retornamos el cliente cuyo tel�fono coincida.
        if (cliente1 != null && cliente1.getTelefono().equals(telefono)) {
            return cliente1;
        } else if (cliente2 != null && cliente2.getTelefono().equals(telefono)) {
            return cliente2;
        } else if (cliente3 != null && cliente3.getTelefono().equals(telefono)) {
            return cliente3;
        }

        return null;
    }

    private static Producto seleccionarProducto() {
        //Imprimimos un n�mero asociado a cada producto.
        System.out.println("\n### Seleccione el producto que desea: ###");
        if (producto1 != null) {
            System.out.println("1. " + producto1.getNombre());
        }
        if (producto2 != null) {
            System.out.println("2. " + producto2.getNombre());
        }
        if (producto3 != null) {
            System.out.println("3. " + producto3.getNombre());
        }
        if (producto4 != null) {
            System.out.println("4. " + producto4.getNombre());
        }
        if (producto5 != null) {
            System.out.println("5. " + producto5.getNombre());
        }
        System.out.println("6. Finalizar selecci�n de productos");

        if (scan.hasNextInt()) {
            //El entero introducido con el usuario se "asocia" al producto, y lo devolvemos.
            int opcion = scan.nextInt();
            scan.nextLine(); // Consumir salto de l�nea, evita problemas.

            switch (opcion) {
                case 1:
                    return producto1;
                case 2:
                    return producto2;
                case 3:
                    return producto3;
                case 4:
                    return producto4;
                case 5:
                    return producto5;
                case 6:
                    return null;
                default:
                    System.out.println("Opci�n no v�lida. Intente nuevamente.");
                    return null;
            }
        } else {
            System.out.println("Entrada no v�lida. Por favor, ingrese un n�mero.");
            scan.nextLine(); // Consumir salto de l�nea, evita problemas.s
            return null;
        }
    }

    private static void realizarPago(Pedido pedido) {
        while (true) {
            System.out.println("\nSeleccione el m�todo de pago: ");
            System.out.println("1. Efectivo");
            System.out.println("2. Tarjeta");
            System.out.println("3. Cuenta bancaria");
            System.out.print("Ingrese una opci�n: ");

            if (scan.hasNextInt()) {
                int opcion = scan.nextInt();
                scan.nextLine(); // Consumir salto de l�nea

                switch (opcion) {
                    //return en lugar de break para salir completamente del m�todo actual, terminando su ejecuci�n.
                    //de usar break,  break solo nos sacar�a del bloque del switch, no del bucle exterior (while (true))
                    case 1:
                        pedido.pagar("efectivo");
                        return;
                    case 2:
                        pedido.pagar("tarjeta");
                        return;
                    case 3:
                        pedido.pagar("cuenta");
                        return;
                    default:
                        System.out.println("Opci�n no v�lida. Intente nuevamente.");
                }
            } else {
                System.out.println("Entrada no v�lida. Por favor, ingrese un n�mero.");
                scan.nextLine(); // Consumir entrada no v�lida
            }
        }
    }

    public static void crearCliente() {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scan.nextLine();
        System.out.print("Ingrese los apellidos del cliente: ");
        String apellidos = scan.nextLine();
        /*Obtener String tel�fono, con verificaci�n para que no est� repetido. Adicionalmente el constructor de cliente verifica que el primer d�gito 
        sea 6, 7 8 o 9 */
        String telefono;
        while (true) {
            System.out.print("Ingrese el tel�fono del cliente: ");
            telefono = scan.nextLine();
            if (!telefonoRepetido(telefono)) { // Verificar duplicado con el m�todo auxiliar. Si el tel�fono no est� repetido, sale del bucle.
                break; // Salir del bucle si el tel�fono no est� en uso
            } else {   //Si el tel�fono se repite, seguimos en el while (true)
                System.out.println("El tel�fono ya est� registrado. Intente nuevamente.");
            }
        }

        System.out.print("Ingrese la direcci�n del cliente: ");
        String direccion = scan.nextLine();

        // Crear el cliente, llamamos al objeto cliente pero lo asignatremos a cliente1, 2 o 3 seg�n los que hay haya creados.
        Cliente cliente = new Cliente(nombre, apellidos, telefono, direccion);

        // Asignar al cliente1, cliente2, o cliente3 en ese orden seg�n "disponibilidad"
        if (cliente1 == null) {
            cliente1 = cliente; // Asignar al cliente1 si est� disponible, es decir, si es null.
        } else if (cliente2 == null) {
            cliente2 = cliente; // Asignar al cliente2 si est� disponible, es decir, si es null.
        } else if (cliente3 == null) {
            cliente3 = cliente; // Asignar al cliente3 si est� disponible, es decir, si es null.
        }

        // Confirmar la creaci�n del cliente a trav�s del contador de clientes.
        System.out.println("Clientes creados: " + Cliente.getContadorClientes());
    }

// M�todo para crear un producto con validaci�n a�adida.
    public static void crearProducto() {
        // Solicitar el nombre del producto con verificaci�n para evitar repetidos
        String nombreProducto;
        while (true) {
            System.out.print("Ingrese el nombre del producto: ");
            nombreProducto = scan.nextLine();
            if (!nombreProductoRepetido(nombreProducto)) { // Verificar si el nombre no est� repetido
                break; // Salir del bucle si el nombre del producto no est� en uso
            } else {
                System.out.println("El nombre del producto ya est� registrado. Intente nuevamente.");
            }
        }

        double precio;
        while (true) { // Validar que el precio sea un double v�lido y positivo
            System.out.print("Ingrese el precio del producto: ");
            if (scan.hasNextDouble()) { // Verificar si la entrada es un n�mero
                precio = scan.nextDouble(); // Leer el precio ingresado
                scan.nextLine(); // Consumir el salto de l�nea
                if (precio > 0) { // Verificar si el precio es mayor a 0
                    break; // Salir del bucle si el precio es v�lido
                } else {
                    System.out.println("El precio debe ser mayor a 0. Intente nuevamente.");
                }
            } else {
                System.out.println("Entrada no v�lida. Por favor, ingrese un n�mero v�lido.");
                scan.nextLine(); // Consumir la entrada no v�lida
            }
        }

        // Solicitar la cantidad del producto con validaci�n
        int cantidad;
        while (true) { // Validar que la cantidad sea un n�mero entero v�lido y positivo
            System.out.print("Ingrese la cantidad del producto: ");
            if (scan.hasNextInt()) { // Verificar si la entrada es un n�mero entero
                cantidad = scan.nextInt(); // Leer la cantidad ingresada
                scan.nextLine(); // Consumir el salto de l�nea
                if (cantidad > 0) { // Verificar si la cantidad es mayor a 0
                    break; // Salir del bucle si la cantidad es v�lida
                } else {
                    System.out.println("La cantidad debe ser mayor a 0. Intente nuevamente.");
                }
            } else {
                System.out.println("Entrada no v�lida. Por favor, ingrese un n�mero entero.");
                scan.nextLine(); // Consumir el token no v�lido
            }
        }

        // Crear el producto con los valores v�lidos
        Producto producto = new Producto(nombreProducto, precio, cantidad);

        // Confirmar que el producto fue creado correctamente
        System.out.println("Producto creado correctamente.");
        System.out.println("Productos creados: " + Producto.getContadorProductos());
    }
    //       Para verificar si el tel�fono ya est� asignado a cliente1, cliente2 o cliente3 de la clase MetodosGestionPedidos
//        Este m�todo devuelve true si el tel�fono ya est� registrado (es repetido) y false si el tel�fono no est� registrado.

    public static boolean telefonoRepetido(String telefono) {

        if (cliente1 != null && cliente1.getTelefono().equals(telefono)) {
            return true; // El tel�fono ya est� en cliente1
        }
        if (cliente2 != null && cliente2.getTelefono().equals(telefono)) {
            return true; // El tel�fono ya est� en cliente2
        }
        if (cliente3 != null && cliente3.getTelefono().equals(telefono)) {
            return true; // El tel�fono ya est� en cliente3
        }
        return false; // El tel�fono no est� registrado
    }

    /**
     * M�todo auxiliar para verificar si un nombre de producto ya est�
     * registrado. Este m�todo devuelve true si el nombre ya est� registrado y
     * false si no lo est�.
     */
    public static boolean nombreProductoRepetido(String nombreProducto) {
        if (producto1 != null && producto1.getNombre().equalsIgnoreCase(nombreProducto)) {
            return true; // El nombre ya est� en producto1
        }
        if (producto2 != null && producto2.getNombre().equalsIgnoreCase(nombreProducto)) {
            return true; // El nombre ya est� en producto2
        }
        if (producto3 != null && producto3.getNombre().equalsIgnoreCase(nombreProducto)) {
            return true; // El nombre ya est� en producto3
        }
        if (producto4 != null && producto4.getNombre().equalsIgnoreCase(nombreProducto)) {
            return true; // El nombre ya est� en producto4
        }
        if (producto5 != null && producto5.getNombre().equalsIgnoreCase(nombreProducto)) {
            return true; // El nombre ya est� en producto5
        }
        return false; // El nombre no est� registrado
    }

}
