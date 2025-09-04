package com.daniel.learning.lambda.capturevariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class CaptureVarableExample {
    public static void main(String[] args) {
        int base = 10;
        Supplier<Integer> duplicar = () -> base * 2;
        // base = 15; // Aqui, Java te maracaría error (local variables referenced from a lambda expression must be final or effectively final)
        System.out.println("El doble de " + base + " es " + duplicar.get()); // Las lambdas guardan una copia del valor de la variable


        int contador = 0;
        // Supplier<Integer> incrementar = () -> contador++; // (Variable used in lambda expression should be final or effectively final)


        // Lambdas anidadas
        Supplier<Supplier<Integer>> crearMultiplicador = () -> {
            int factor = 2;
            return () ->  base * factor;
        };
        Supplier<Integer> multiplicador = crearMultiplicador.get();
        System.out.println("Resultado: " + multiplicador.get());


        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
        // Version 1
        List<Supplier<Integer>> lista = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Supplier<Integer> multiplicarPor2 = () -> numeros.get(finalI) * 2; // No puedes usar directamente 'i' en get(i) porque cambia en cada iteración
            lista.add(multiplicarPor2);
        }
        // Version 2
        List<Supplier<Integer>> lista2 = new ArrayList<>();
        for (Integer numero : numeros) {
            Supplier<Integer> multiplicarPor10 = () -> numero * 10; // numero sí es efectivamente final
            lista2.add(multiplicarPor10);
        }
        lista.stream().map(Supplier::get).forEach(System.out::println);
        lista2.stream().map((Supplier s) -> s.get()).forEach(System.out::println);

        // Version 3
        List<Supplier<Integer>> lista3 = numeros.stream()
                .map(numero  -> (Supplier<Integer>) () -> numero * 10)
                .toList();
        lista3.forEach(supplier -> System.out.println(supplier.get()));

        // Version 4
        numeros.stream()
                .map(numero  -> (Supplier<Integer>) () -> numero * 10)
                .toList().forEach(supplier -> System.out.println(supplier.get()));


        // DIFERENCIAS RESPECTO A CLASES ANONIMAS
        // En clases anonimas, 'this' apunta a la clase anónima. En Lambdas, 'this' apunta a la clase externa donde se define el lambda
        Runnable claseAnonima = new Runnable() {
            @Override
            public void run() {
                System.out.println(this.getClass().getName()); // Clase anónima
            }
        };
        // Runnable lambda = () -> System.out.println(this.getClass().getName()); // Error: Lambdas no generan clase propia
        // ('path.this' cannot be referenced from a static context)

    }
}
