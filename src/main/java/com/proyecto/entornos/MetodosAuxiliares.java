package com.proyecto.entornos.util;

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
        for (int i = 0; i < tarjeta.length(); i++) {
            if (!Character.isDigit(tarjeta.charAt(i)) && tarjeta.charAt(i) != ' ') {
                System.out.println("La tarjeta contiene caracteres incorrectos.");
                System.out.println("");
                return valida;
            }
        }
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
    // ...resto de métodos...
}
