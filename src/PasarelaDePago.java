import java.util.Date;

/**
 *
 * @author hayun
 */
public  class PasarelaDePago {

    double importe;
    //Se genera al momento del pago, es decir, al ejecutarse uno de los m�todos. Mientras no se haya
    // pagado ser�a -1.
    long codigoPago=-1;

//    public PasarelaDePago() {
//    }

    public PasarelaDePago(double importe) {
        this.importe=MetodosAuxiliares.redondearDosDecimales(importe);
    }

    
    public  void efectivo() {
         if (importe > 0) {
            this.codigoPago = new Date().getTime();
            this.importe = 0; // Importe establecido a cero despu�s del pago
            System.out.println("Pago en efectivo realizado. C�digo de pago generado: " + this.codigoPago);
        } else {
            System.out.println("El importe debe ser mayor a 0 para realizar el pago.");
        }
    }
    

 public void tarjeta(String numeroTarjeta) {
    // Verificar que el importe sea superior a 0.
    if (this.importe > 0) {
        // Verificar que el n�mero de tarjeta sea v�lido reutilizando c�digo del tema 3.
        if (MetodosAuxiliares.verificarTarjeta(numeroTarjeta)) {
            this.codigoPago = new Date().getTime(); // Generar el c�digo de pago
            this.importe = 0; // Establecer el importe a cero despu�s del pago
            System.out.println("Pago con tarjeta verificado con c�digo: " + this.codigoPago);
        } else {
            System.out.println("El n�mero de tarjeta no es v�lido. Pago no realizado.");
        }
    } else {
        System.out.println("El importe debe ser mayor a 0 para realizar el pago.");
    }
}


    


public void cuenta(String cuenta) {
    // Verificar que el importe sea mayor a 0.
    if (this.importe > 0) {
        // Verificar que los caracteres de la cuenta correspondan al formato IBAN.
        if (MetodosAuxiliares.verificarCuenta(cuenta)) {
            this.codigoPago = new Date().getTime(); // Generar el c�digo de pago
            this.importe = 0; // Establecer el importe a cero despu�s del pago
            System.out.println("Pago con cuenta bancaria realizado. C�digo de pago: " + this.codigoPago);
        } else {
            System.out.println("El formato de la cuenta bancaria no es v�lido. Pago no realizado.");
        }
    } else {
        System.out.println("El importe debe ser mayor a 0 para realizar el pago.");
    }
}

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public long getCodigoPago() {
        return codigoPago;
    }

    public void setCodigoPago(long codigoPago) {
        this.codigoPago = codigoPago;
    }





}

