package com.daniel.learning.optional.basicoptional;

import java.util.Optional;

public class OptionalCreation {
    public static void main(String[] args) {
        // Optional.of(value) -> solo si estás 100% seguro de que no es null
        Optional<String> nombreSeguro = Optional.of("Daniel");
        System.out.println("Nombre: " + nombreSeguro); // igual a toString()
        System.out.println("Nombre: " + nombreSeguro.get());

        // Optional.of(null) -> lanza NullPointerException
        try {
            Optional<String> nombreNulo = Optional.of(null); // evitarlo
        } catch (NullPointerException e) {
            System.out.println("Error");
        }

        // Optional.ofNullable(value) -> acepta nulls sin reventar
        Optional<String> apellidoNulo = Optional.ofNullable(null);
        System.out.println("Apellido: " + apellidoNulo); // igual a toString()
        System.out.println("Apellido: " + apellidoNulo.orElse("Sin apellido"));
        // System.out.println("Apellido: " + apellidoNulo.get()); Explota por ser null

        // Optional.empty() -> explícitamente vacío
        Optional<String> vacio = Optional.empty();
        System.out.println("Empty: " + vacio);
    }
    // Usar Optional.of() cuando se tenga el control total del valor y se sepa que jamás será null
    // Usar Optional.ofNullable() cual el valor venga de 'fuera de tu control' (APIs, DB, JSON, etc.).
}
