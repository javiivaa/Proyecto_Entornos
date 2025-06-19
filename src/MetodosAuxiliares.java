/**
 *
 * 
 */
public class MetodosAuxiliares {

    public static double redondearDosDecimales(double cifra) {
        cifra *= 100;
        cifra = Math.round(cifra);
        cifra /= 100;
        return cifra;
    }

    public static boolean verificarTarjeta(String tarjeta) {

        boolean visa = false;
        boolean masterCard = false;
        boolean americanExpress = false;
        boolean valida = false;

        // Comprobar que la cadena tarjeta se compone solo de números y espacios
        for (int i = 0; i < tarjeta.length(); i++) {
            if (!Character.isDigit(tarjeta.charAt(i)) && tarjeta.charAt(i) != ' ') {
                System.out.println("La tarjeta contiene caracteres incorrectos.");
                System.out.println("");
                return valida;  // Retorna falso si contiene caracteres no válidos
            }
        }

        // Verificar la estructura específica de cada tipo de tarjeta
        if (tarjeta.length() == 17 && tarjeta.charAt(4) == ' ' && tarjeta.charAt(11) == ' ' && tarjeta.charAt(0) == '3') {
            americanExpress = true;
            valida = true;
        } else if (tarjeta.length() == 19 && tarjeta.charAt(4) == ' ' && tarjeta.charAt(9) == ' ' && tarjeta.charAt(14) == ' ') {
            if (tarjeta.charAt(0) == '4') {
                visa = true;
                valida = true;
            } else if (tarjeta.charAt(0) == '5') {
                masterCard = true;
                valida = true;
            }
        }

        // Mostrar el tipo de tarjeta verificado o indicar que es inválida
        if (americanExpress) {
            System.out.println("Tarjeta American Express VERIFICADA");
            System.out.println("");
        } else if (masterCard) {
            System.out.println("Tarjeta MasterCard VERIFICADA");
            System.out.println("");
        } else if (visa) {
            System.out.println("Tarjeta Visa VERIFICADA ");
            System.out.println("");
        } else {
            System.out.println("Tarjeta no valida");
            System.out.println("");
        }
        return valida;
    }

    public static boolean verificarCuenta(String cuenta) {
        boolean valida = false;

        // Comprobar que la cadena cuenta se compone solo de letras, números y espacios. (Debe contener letras para el IBAN)
        for (int i = 0; i < cuenta.length(); i++) {
            if (!Character.isLetterOrDigit(cuenta.charAt(i)) && cuenta.charAt(i) != ' ') {
                System.out.println("La cuenta contiene caracteres no válidos.");
                return false; // Retorna falso si contiene caracteres no válidos, es decir, que no sean letras, dígitos ni espacios.
            }
        }

        // Verificar que la cuenta tiene un formato válido
        if (cuenta.length() >= 24 && cuenta.length() <= 32) { // Un IBAN tiene entre 24 y 32 caracteres
            String pais = cuenta.substring(0, 2).toUpperCase(); // Los dos primeros caracteres son el código del país
            if (Character.isLetter(pais.charAt(0)) && Character.isLetter(pais.charAt(1))) {
                valida = true;
                System.out.println("Cuenta bancaria VERIFICADA: " + cuenta);
            } else {
                System.out.println("El código de país no es válido.(" + pais + ")");
            }
        } else {
            System.out.println("La longitud de la cuenta no es válida. Debe tener entre 24 y 32 caracteres.");
        }

        // Retornar si la cuenta es válida o no
        return valida;
    }

    


}
