package com.proyecto.entornos;

import java.util.Date;
import com.proyecto.entornos.util.MetodosAuxiliares;

public class PasarelaDePago {
    double importe;
    long codigoPago = -1;

    public PasarelaDePago(double importe) {
        this.importe = MetodosAuxiliares.redondearDosDecimales(importe);
    }

    public void efectivo() {
        if (importe > 0) {
            this.codigoPago = new Date().getTime();
            this.importe = 0;
            System.out.println("Pago en efectivo realizado. Código de pago generado: " + this.codigoPago);
        } else {
            System.out.println("El importe debe ser mayor a 0 para realizar el pago.");
        }
    }

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
