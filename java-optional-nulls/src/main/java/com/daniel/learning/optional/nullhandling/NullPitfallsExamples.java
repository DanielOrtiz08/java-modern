package com.daniel.learning.optional.nullhandling;

public class NullPitfallsExamples {
    public static void main(String[] args) {
        String texto = getTextoFromService(); // Puede ser null
        System.out.println("Longitud: " + texto.length());
    }

    private static String getTextoFromService() {
        return Math.random() < 0.5 ? "Menor que 0.5" : null;
    }
}

/*
1. ¿Qué es el NullPointerException y por qué es tan común?
Un NullPointerException(NPE) ocurre cuando el código intenta acceder a un metodo o campo de una referencia que vale null.

Es la excepción más frecuente en Java: cerca del 37 % de los fallos en producción en apps Java se relacionan con NPE¹.

Suele darse por:

Variables no inicializadas.

Retornos de métodos que devuelven null por defecto.

Falta de contratos claros (¿puede un metodo devolver null o no?).

Impacto real: un NPE en un servicio crítico puede tumbar peticiones HTTP enteras, provocar respuestas 500 y mala
experiencia de usuario.
 */
