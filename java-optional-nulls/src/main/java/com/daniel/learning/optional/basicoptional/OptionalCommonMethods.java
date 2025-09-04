package com.daniel.learning.optional.basicoptional;

import java.util.Optional;

public class OptionalCommonMethods {
    public static void main(String[] args) {
        Optional<String> saludo = Optional.of("Hola Daniel");
        Optional<String> vacio = Optional.empty();

        // isPresent()
        if (saludo.isPresent()) {
            System.out.println("Saludo: " + saludo.get());
        }

        // get() sin verificar puede lanzar excepción
        try {
            String valor = vacio.get();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ifPresent()
        saludo.ifPresent(s -> System.out.println("Saludo con ifPresent: " + s));

        // orElse()
        String resultado1 = vacio.orElse("Por defecto");
        System.out.println("Resultado 1 con orElse: " + resultado1);

        // orElseGet() -> diferido (ejecuta lambda solo si está vacío)
        String resultado2 = vacio.orElseGet(() -> "Valor por defecto");
        System.out.println("Resultado 2 con orElseGet: " + resultado2);

        // orElseThrow() -> lanza una excepción NoSuchElementException y orElseThorow(Supplier) lanza una excepción custom
        try {
            String obligatorio = vacio.orElseThrow(() -> new IllegalArgumentException("Dato requerido"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        // CASOS PRACTICOS EXTRA
        // Usar map para transformar el contenido
        Optional<Integer> longitud = saludo.map(String::length);
        System.out.println("Longitud: " + longitud.orElse(-1));

        // Encadenar con filter
        saludo.filter(n -> n.length() > 5)
                .ifPresent(n -> System.out.println("Saludo con más de 5 caracteres: " + n));


    }
}
