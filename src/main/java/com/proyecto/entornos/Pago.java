package com.proyecto.entornos;

import java.util.Date;
import com.proyecto.entornos.modelo.Cliente;
import com.proyecto.entornos.modelo.Producto;
import com.proyecto.entornos.util.MetodosAuxiliares;

/**
 * Clase que representa un pago y sus métodos asociados (efectivo, tarjeta, cuenta).
 * Utiliza utilidades para validación y redondeo.
 */
public class Pago {
    double importe;
    long codigoPago = -1;

    /**
     * Constructor que inicializa el pago con el importe redondeado.
     */
    public Pago(double importe) {
        this.importe = MetodosAuxiliares.redondearDosDecimales(importe);
    }

    /**
     * Realiza el pago en efectivo y genera un código de pago si el importe es válido.
     */
    public void efectivo() {
        if (importe > 0) {
            this.codigoPago = new Date().getTime();
            this.importe = 0;
            System.out.println("Pago en efectivo realizado. Código de pago generado: " + this.codigoPago);
        } else {
            System.out.println("El importe debe ser mayor a 0 para realizar el pago.");
        }
    }

    /**
     * Realiza el pago con tarjeta si el número es válido y el importe es mayor a 0.
     */
    public void tarjeta(String numeroTarjeta) {
        if (this.importe > 0) {
            if (MetodosAuxiliares.verificarTarjeta(numeroTarjeta)) {
                this.codigoPago = new Date().getTime();
                this.importe = 0;
                System.out.println("Pago con tarjeta verificado con código: " + this.codigoPago);
            } else {
                System.out.println("El número de tarjeta no es válido. Pago no realizado.");
            }
        } else {
            System.out.println("El importe debe ser mayor a 0 para realizar el pago.");
        }
    }

    /**
     * Realiza el pago con cuenta bancaria si el IBAN es válido y el importe es mayor a 0.
     */
    public void cuenta(String cuenta) {
        if (this.importe > 0) {
            if (MetodosAuxiliares.verificarCuenta(cuenta)) {
                this.codigoPago = new Date().getTime();
                this.importe = 0;
                System.out.println("Pago con cuenta bancaria realizado. Código de pago: " + this.codigoPago);
            } else {
                System.out.println("La cuenta no es válida. Pago no realizado.");
            }
        } else {
            System.out.println("El importe debe ser mayor a 0 para realizar el pago.");
        }
    }
}
