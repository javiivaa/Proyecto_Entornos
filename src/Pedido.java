import java.util.Date;
import java.util.Scanner;

/**
 * La clase {@code Pedido} representa un pedido realizado por un cliente en el
 * establecimiento de comidas. Gestiona los productos incluidos en el pedido, el
 * importe total, el estado del pedido y el proceso de pago.
 * <p>
 * Un pedido puede contener hasta dos productos y pasa por varios estados:
 * PAGADO, PREPARANDO, LISTO y SERVIDO. Solo se puede comenzar a preparar un
 * pedido si ha sido pagado, y no se puede pagar un pedido m�s de una vez.
 * </p>
 *
 * @author
 */
public class Pedido {

    // Atributos principales
    /**
     * El cliente que realiza el pedido.
     */
    private Cliente cliente;

    /**
     * El primer producto incluido en el pedido.
     */
    private Producto producto1;

    /**
     * El segundo producto incluido en el pedido, si es que hay uno.
     */
    private Producto producto2;

    /**
     * El importe total del pedido, calculado en funci�n de los productos
     * agregados.
     */
    private double importeTotal;

    /**
     * El estado actual del pedido.
     */
    private Estado estado;

    /**
     * La pasarela de pago utilizada para procesar el pago del pedido.
     */
    private PasarelaDePago pago;

    /**
     * Enum que representa los posibles estados de un pedido.
     */
    public enum Estado {
        GENERADO,
        PAGADO,
        PREPARANDO,
        LISTO,
        SERVIDO
    }

    /**
     * Constructor de la clase {@code Pedido}.
     * <p>
     * Al crear un nuevo pedido, se asigna el cliente y los productos, se
     * calcula el importe total, se inicializa la pasarela de pago con dicho
     * importe y se establece el estado inicial como GENERADO.
     * </p>
     *
     * @param cliente el cliente que realiza el pedido
     * @param producto1 el primer producto del pedido
     * @param producto2 el segundo producto del pedido, puede ser {@code null}
     * si solo hay un producto
     */
    public Pedido(Cliente cliente, Producto producto1, Producto producto2) {
        this.cliente = cliente;
        this.producto1 = producto1;
        this.producto2 = producto2;
        // Inicializa la pasarela de pago con el importe total del pedido, no se le pasa la pasarela como par�metro porque primero debemos
        // calcular el importe total del pedido.
        this.importeTotal = MetodosAuxiliares.redondearDosDecimales(calcularImporteTotal());
        this.pago = new PasarelaDePago(this.importeTotal);
        // El estado por defecto se establece en 'GENERADO'
        this.estado = Estado.GENERADO;
    }

    /**
     * Calcula el importe total del pedido sumando el precio de cada producto
     * multiplicado por su cantidad.
     * <p>
     * Este m�todo se define aqu� para evitar pasar los productos como
     * par�metros en cada llamada.
     * </p>
     *
     * @return el importe total calculado del pedido
     */
    public double calcularImporteTotal() {
        double Importetotal = 0;
        
        if (this.producto1 != null) {
            Importetotal += this.producto1.getCantidad() * this.producto1.getPrecio();
        }
        if (this.producto2 != null) {
            Importetotal += this.producto2.getCantidad() * this.producto2.getPrecio();
        }
        
        return Importetotal;
    }

    // Getters y setters
    /**
     * Obtiene el cliente del pedido.
     *
     * @return el cliente que realiz� el pedido
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Asigna un cliente al pedido.
     *
     * @param cliente el cliente que realizar� el pedido
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene el primer producto del pedido.
     *
     * @return el primer producto incluido en el pedido
     */
    public Producto getProducto1() {
        return producto1;
    }

    /**
     * Asigna el primer producto al pedido y recalcula el importe total.
     *
     * @param producto1 el producto a asignar como producto1
     */
    public void setProducto1(Producto producto1) {
        this.producto1 = producto1;
        this.importeTotal = MetodosAuxiliares.redondearDosDecimales(calcularImporteTotal());
    }

    /**
     * Obtiene el segundo producto del pedido.
     *
     * @return el segundo producto incluido en el pedido, puede ser {@code null}
     */
    public Producto getProducto2() {
        return producto2;
    }

    /**
     * Asigna el segundo producto al pedido y recalcula el importe total.
     *
     * @param producto2 el producto a asignar como producto2
     */
    public void setProducto2(Producto producto2) {
        this.producto2 = producto2;
        this.importeTotal = MetodosAuxiliares.redondearDosDecimales(calcularImporteTotal());
    }

    /**
     * Obtiene el importe total del pedido.
     *
     * @return el importe total del pedido
     */
    public double getImporteTotal() {
        return importeTotal;
    }

    /**
     * Asigna el importe total del pedido, redonde�ndolo a dos decimales.
     *
     * @param importeTotal el importe total a asignar
     */
    public void setImporteTotal(double importeTotal) {
        this.importeTotal = MetodosAuxiliares.redondearDosDecimales(importeTotal);
    }

    /**
     * Obtiene el estado actual del pedido.
     *
     * @return el estado del pedido
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Asigna un nuevo estado al pedido.
     *
     * @param estado el nuevo estado a asignar
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la pasarela de pago asociada al pedido.
     *
     * @return la pasarela de pago utilizada para el pedido
     */
    public PasarelaDePago getPago() {
        return pago;
    }

    /**
     * Asigna una nueva pasarela de pago al pedido.
     *
     * @param pago la pasarela de pago a asignar
     */
    public void setPago(PasarelaDePago pago) {
        this.pago = pago;
    }

    // M�todos para el manejo de estados:
    /**
     * Procesa el pago del pedido utilizando el m�todo de pago especificado.
     * <p>
     * Una vez realizado el pago exitosamente, el estado del pedido cambia a
     * PAGADO. No se puede pagar un pedido que ya ha sido pagado previamente.
     * </p>
     *
     * @param tipoPago el tipo de pago a realizar ("efectivo", "tarjeta" o
     * "cuenta")
     */
    public void pagar(String tipoPago) {
        // Verificar si el pedido ya est� pagado
        if (this.estado == Estado.PAGADO) {
            System.out.println("El pedido ya est� pagado. No se puede volver a pagar.");
            return;
        }

        // Seg�n el tipo de pago, llamamos al m�todo correspondiente de PasarelaDePago
        if (tipoPago.equalsIgnoreCase("efectivo")) {
            this.pago.efectivo();
        } else if (tipoPago.equalsIgnoreCase("tarjeta")) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Ingrese el n�mero de tarjeta: ");
            String numeroTarjeta = scan.nextLine();
            this.pago.tarjeta(numeroTarjeta);
        } else if (tipoPago.equalsIgnoreCase("cuenta")) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Ingrese el n�mero de cuenta (IBAN): ");
            String numeroCuenta = scan.nextLine();
            this.pago.cuenta(numeroCuenta);
        } else {
            System.out.println("Tipo de pago no reconocido. Use: efectivo / tarjeta / cuenta.");
            return;
        }

        // Si el pago se ha hecho correctamente (codigoPago != -1), cambiamos el estado a PAGADO
        if (this.pago.getCodigoPago() != -1) {
            this.estado = Estado.PAGADO;
            System.out.println("El pedido ha pasado al estado: PAGADO");
            //Metemos el c�digo de pago al historial. Primero lo pasamos a String, luego lo concatenamos precedido de un espacio.
            String codigoPago = String.valueOf(this.pago.codigoPago);
            this.cliente.setHistorial(this.cliente.getHistorial() + " " + codigoPago);
        } else {
            System.out.println("No se ha podido completar el pago. El pedido sigue en estado: " + this.estado);
        }
    }

    /**
     * Inicia la preparaci�n del pedido.
     * <p>
     * Solo se puede preparar un pedido si su estado actual es PAGADO.
     * </p>
     */
    public void preparar() {
        if (this.estado == Estado.PAGADO) {
            this.estado = Estado.PREPARANDO;
            System.out.println("El pedido est� ahora en estado PREPARANDO.");
        } else {
            System.out.println("No se puede preparar un pedido que no est� pagado.");
        }
    }

    /**
     * Marca el pedido como LISTO.
     * <p>
     * Solo se puede marcar como LISTO un pedido que est� en estado PREPARANDO.
     * </p>
     */
    public void marcarListo() {
        if (this.estado == Estado.PREPARANDO) {
            this.estado = Estado.LISTO;
            System.out.println("El pedido est� ahora en estado LISTO.");
        } else {
            System.out.println("No se puede marcar como LISTO un pedido que no est� en PREPARANDO.");
        }
    }

    /**
     * Sirve el pedido.
     * <p>
     * Solo se puede servir un pedido que est� en estado LISTO.
     * </p>
     */
    public void servir() {
        if (this.estado == Estado.LISTO) {
            this.estado = Estado.SERVIDO;
            System.out.println("El pedido ha sido SERVIDO.");
        } else {
            System.out.println("No se puede servir un pedido que no est� LISTO.");
        }
    }

    // M�todos para agregar productos y cliente:
    /**
     * Agrega un cliente al pedido.
     *
     * @param cliente el cliente a agregar al pedido
     */
    public void agregarCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Agrega el primer producto al pedido.
     *
     * @param producto el producto a agregar como producto1
     */
    public void agregarProducto1(Producto producto) {
        if (producto == null) {
            System.out.println("El producto 1 no puede ser nulo.");
        } else {
            this.producto1 = producto;
            this.importeTotal = MetodosAuxiliares.redondearDosDecimales(calcularImporteTotal());
            System.out.println("Producto 1 agregado: " + producto.getNombre());
        }
    }

    /**
     * Agrega el segundo producto al pedido, siempre que ya exista un producto1.
     *
     * @param producto el producto a agregar como producto2
     */
    public void agregarProducto2(Producto producto) {
        if (this.producto1 != null) {
            this.producto2 = producto;
            System.out.println("Producto 2 agregado: " + this.producto2.getNombre());
            this.importeTotal = MetodosAuxiliares.redondearDosDecimales(calcularImporteTotal());
        } else {
            System.out.println("Agregue primero el producto 1.");
        }
    }

    /**
     * Elimina un producto del pedido.
     * <p>
     * Si el producto a eliminar es producto1 o producto2, se establece como
     * {@code null} y se recalcula el importe total.
     * </p>
     *
     * @param producto el producto a eliminar del pedido
     * @return {@code null} siempre, ya que seg�n el enunciado se devuelve
     * {@code null} tras eliminar
     */
    public Producto eliminarProducto(Producto producto) {
        if (producto == null) {
            System.out.println("El producto proporcionado no existe en el pedido.");
            return null; // Esto evita procesar un producto inexistente.
        }
        if (producto.equals(this.producto1)) {
            System.out.println("Se elimin� producto1 (" + producto1.getNombre() + ") del pedido.");
            this.producto1 = null; // Eliminar el producto1 despu�s del mensaje para acceder a su nombre.
            this.importeTotal = MetodosAuxiliares.redondearDosDecimales(calcularImporteTotal());
            return null; // Devolvemos null
        } else if (producto.equals(this.producto2)) {
            System.out.println("Se elimin� producto2 (" + producto2.getNombre() + ") del pedido.");
            this.producto2 = null; // Eliminar el producto2 despu�s del mensaje para acceder a su nombre.
            this.importeTotal = MetodosAuxiliares.redondearDosDecimales(calcularImporteTotal());
            return null; // Devolvemos null
        } else {
            System.out.println("El producto no se encuentra en este pedido.");
            return null; // Caso de que el producto pasado por par�metro no sea producto1 ni producto2.
        }
    }

    /**
     * Imprime los detalles del pedido en la consola de manera formateada.
     * <p>
     * La salida incluye la cantidad, el nombre del producto, el precio unitario
     * y el total por producto. Al final, se muestra el importe total del
     * pedido.
     * </p>
     */
    public void imprimirPedido() {
        // Encabezado de la tabla con anchos fijos utilizando printf para formateo
        // %-6s : Cadena de caracteres alineada a la izquierda en un espacio de 6 caracteres
        // %-20s : Cadena de caracteres alineada a la izquierda en un espacio de 20 caracteres
        // %-12s : Cadena de caracteres alineada a la izquierda en un espacio de 12 caracteres
        // %-10s : Cadena de caracteres alineada a la izquierda en un espacio de 10 caracteres
        // %n     : Salto de l�nea (equivalente a \n)
        System.out.printf("%-6s %-20s %-12s %-10s%n", "CANT.", "PRODUCTO", "PRECIO UD.", "TOTAL");

        // L�nea de separaci�n para el encabezado, usando los mismos anchos que el encabezado
        System.out.printf("%-6s %-20s %-12s %-10s%n", "======", "========", "==========", "=====");

        // Variable para acumular el total del pedido
        double totalPedido = 0.0;

        // Verifica si existe Producto 1 y lo imprime si es as�, existe la posibilidad de que sea null. (Tenemos el m�todo eliminarProducto())
        if (producto1 != null) {
            // Calcula el total del Producto 1 multiplicando la cantidad por el precio unitario
            // Luego redondea el resultado a dos decimales para mantener consistencia
            double totalProducto1 = MetodosAuxiliares.redondearDosDecimales(producto1.getCantidad() * producto1.getPrecio());

            // Imprime los detalles del Producto 1 utilizando printf con formateo espec�fico
            // %-6d    : N�mero entero alineado a la izquierda en un espacio de 6 caracteres (cantidad)
            // %-20s   : Cadena de caracteres alineada a la izquierda en un espacio de 20 caracteres (nombre del producto)
            // %-12.2fEUR : N�mero de punto flotante alineado a la izquierda en un espacio de 12 caracteres con 2 decimales, seguido de "EUR" (precio unitario)
            // %-10.2fEUR : N�mero de punto flotante alineado a la izquierda en un espacio de 10 caracteres con 2 decimales, seguido de "EUR" (total del producto)
            System.out.printf("%-6d %-20s %-12.2fEUR %-10.2fEUR%n",
                    producto1.getCantidad(), // Cantidad del Producto 1
                    producto1.getNombre(), // Nombre del Producto 1
                    MetodosAuxiliares.redondearDosDecimales(producto1.getPrecio()), // Precio unitario del Producto 1
                    totalProducto1);         // Total del Producto 1

            // A�ade el total del Producto 1 al total del pedido
            totalPedido += totalProducto1;
        }

        // Verifica si existe Producto 2 y lo imprime si es as�, existe la posibilidad de que sea null. (Tenemos el m�todo eliminarProducto())
        if (producto2 != null) {
            // Calcula el total del Producto 2 multiplicando la cantidad por el precio unitario
            // Luego redondea el resultado a dos decimales para mantener consistencia
            double totalProducto2 = MetodosAuxiliares.redondearDosDecimales(producto2.getCantidad() * producto2.getPrecio());

            // Imprime los detalles del Producto 2 utilizando printf con formateo espec�fico
            System.out.printf("%-6d %-20s %-12.2fEUR %-10.2fEUR%n",
                    producto2.getCantidad(), // Cantidad del Producto 2
                    producto2.getNombre(), // Nombre del Producto 2
                    MetodosAuxiliares.redondearDosDecimales(producto2.getPrecio()), // Precio unitario del Producto 2
                    totalProducto2);         // Total del Producto 2

            // A�ade el total del Producto 2 al total del pedido
            totalPedido += totalProducto2;
        }

        // Actualiza el importe total del pedido sumando los totales de los productos
        // Y redondea el resultado a dos decimales para mantener consistencia
        this.importeTotal = MetodosAuxiliares.redondearDosDecimales(totalPedido);

        // Imprime la l�nea del total del pedido
        // "TOTAL ------------------------------------------------> " es una cadena fija para indicar el total
        // %.2fEUR : N�mero de punto flotante con 2 decimales seguido de "EUR"
        // %n      : Salto de l�nea
        System.out.printf("TOTAL ------------------------------------------------> %.2fEUR%n", this.importeTotal);
    }
    
}
