package com.daniel.learning.lambda.interfacesfunctional.jdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.*;

public class JdkInterfacesExamples {
    public static void main(String[] args) {
        runnableExample();
        callableExample();
        supplierExample();
        consumerExample();
        predicateExample();
        funtionExample();
        biFunctionExample();
        comparatorExample();
        unaryOperatorExample();
        binaryOperatorExample();
    }

    private static void runnableExample() {
        Runnable tarea = () -> System.out.println("[Runnable] Ejecutando tarea simple.");

        // Más realista: simulando un procesamiento
        new Thread(() -> {
            System.out.println("[Runnable] Inicio de la tarea pesada...");
            try { Thread.sleep(1000); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            System.out.println("[Runnable] Fin de la tarea pesada.");
        }).start();
    }

    private static void callableExample() {
        Callable<String> tareaConResultado = () -> {
            Thread.sleep(500);
            return "[Callable] Resultado de la tarea después de esperar.";
        };
        FutureTask<String> future = new FutureTask<>(tareaConResultado);
        new Thread(future).start();

        try {
            String resultado = future.get();
            System.out.println(resultado);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error en Callable: " + e.getMessage());
        }
    }

    private static void supplierExample() {
        Supplier<String> mensaje = () -> "Hola, soy un Supplier generando datos.";
        Supplier<Double> numeroAleatorio = () -> {
            return Math.random();
        };

        System.out.println(mensaje.get());
        System.out.printf("Número aleatorio: %.4f\n", numeroAleatorio.get());
    }

    private static void consumerExample() {
        Consumer<String> imprimir = s -> System.out.println("[Consumer] Procesando: " + s);
        Consumer<Integer> calcularDoble = n -> System.out.println("[Consumer] El doble es: " + (n * 2));

        imprimir.accept("Aprendiendo Java Lambdas");
        calcularDoble.accept(8);
    }

    private static void predicateExample() {
        Predicate<String> empiezaConA = s -> s.startsWith("A");
        System.out.println("¿'Ave' empieza con A? " + empiezaConA.test("Ave"));
        System.out.println("¿'Perro' empieza con A?" + empiezaConA.test("Perro"));
    }

    private static void funtionExample() {
        Function<String, String> generarIniciales = nombreCompleto -> {
            String[] partes = nombreCompleto.trim().split("\\s+");
            StringBuilder iniciales = new StringBuilder();
            for (String parte: partes) {
                if (!parte.isEmpty()) iniciales.append(Character.toUpperCase(parte.charAt(0)));
            }
            return iniciales.toString();
        };
        System.out.println("Iniciales de 'Daniel David Ortiz Villanueva': " + generarIniciales.apply("Daniel David Ortiz Villanueva"));
    }

    private static void biFunctionExample() {
        BiFunction<List<Integer>, List<Integer>, List<Integer>> sumarListas = (lista1, lista2) -> {
            List<Integer> resultado = new ArrayList<>();
            int size = Math.min(lista1.size(), lista2.size());
            for (int i = 0; i < size; i++) {
                resultado.add(lista1.get(i) + lista2.get(i));
            }
            return resultado;
        };
        System.out.println("Suma de listas: " + sumarListas.apply(List.of(1, 2, 3, 4), Arrays.asList(10, 15, 20, 25)));
    }

    private static void comparatorExample() {
        Comparator<Integer> compararNumeros = (a, b) -> a - b;
        List<Integer> numeros = Arrays.asList(5, 2, 9, 1, 7);
        numeros.sort(compararNumeros);
        System.out.println("Lista ordenada: " + numeros);
    }

    private static void unaryOperatorExample() {
        UnaryOperator<String> pasarAMayusculas = String::toUpperCase;
        System.out.println("Pasar 'Manzana' a mayuscula: " + pasarAMayusculas.apply("Manzana"));
    }

    private static void binaryOperatorExample() {
        BinaryOperator<Integer> multiplicar = (a, b) -> a * b;
        System.out.println("Multiplicación: " + multiplicar.apply(4, 6));
    }
}
