package com.daniel.learning.lambda.sintax;

import java.util.Arrays;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.*;

import static java.time.LocalDate.now;

public class LambdaSyntaxExamples {
    public static void main(String[] args) {
        // Lambda de una sola linea
        BiFunction<Float, Integer, Float> sumar = (a, b) -> (float) (a + b);
        BiFunction<Integer, Float, Integer> restar = (Integer a, Float b) -> (int) (a - b);

        System.out.println("Suma: " + sumar.apply(8f, 10));
        System.out.println("Resta: " + restar.apply(26, 18f));

        // Lambda con bloque de codigo
        BiFunction<Float, Integer, Double> multiplicar = (a, b) -> {
            return (double) (a * b);
        };
        System.out.println("Multiplicar: " + multiplicar.apply(8f, 10));

        // Lambda con un solo parámetro y retorno
        Function<String, Integer> obtenerLongitud = s -> s.length();
        System.out.println("Longitud de 'Lambda': " + obtenerLongitud.apply("Lambda"));
        // Metodo anterior con metodono referente
        Function<String, Integer> obtenerLongitudConMetodoReferente = String::length;
        System.out.println("Longitud de 'Hola mundo!': " + obtenerLongitudConMetodoReferente.apply("Hola mundo!"));

        // Lambda sin parámetros y con retorno
        Supplier<String> saludar = () -> "¡Hola desde un lambda!";
        System.out.println(saludar.get());

        // Lambda con parametros sin retorno
        Consumer<String> printFruta = s -> System.out.println("La fruta es " + s);
        String fruta = "Manzana";
        printFruta.accept(fruta);

        // Lambda sin paarmetros ni retorno
        Runnable printSaludo = () -> System.out.println("Hola mundo!!!");
        printSaludo.run();

        // Lambda con retorno y que puede lanzar una excepcion
        Callable<Double> dividir10EntreRamdom = () -> {
            int numerador = 10;
            int denominador = (int) (Math.random() * 3);

            if (denominador == 0) throw new ArithmeticException("¡División por cero es indefinida!");
            else return (double) (numerador / denominador);
        };
        try { System.out.println("El resultado entre dividir 10 entre un numero aleatorio (0, 1, 2, 3) es: " + dividir10EntreRamdom.call()); }
        catch (Exception e) { System.err.println("Error en Callable " + e.getMessage()); }


        // MICRO-EJERCICIO PRÁCTICO
        System.out.println("\n\nMICRO-EJERCICIO PRÁCTICO");

        // 1) Crea una lambda que reciba dos números double y devuelva el mayor de los dos
        BiFunction<Double, Double, Float> mayor = (a, b) -> Math.max(a.floatValue(), b.floatValue());
        int a = 5, b = 8;
        System.out.printf("El número mayor entre %d y %d es: %.2f\n", a, b, mayor.apply((double) a, (double) b)); // se que pude hacerlo mas sencillo pero quise complicarme y aplicar varias cosas

        // 2) Crea una lambda que no reciba parámetros y devuelva la fecha actual como String usando now().toString()
        Supplier<String> fechaActual = () -> now().toString();
        System.out.println("La fecha actual es: " + fechaActual.get());

        // 3) Crea una lambda que reciba un String y devuelva true o false si su longitud es mayor a 5
        Predicate<String> esLongitudMayorQue5 = s -> s.length() > 5;
        String string = "Lambda";
        System.out.println(esLongitudMayorQue5.test(string) ? "La longitud del String es mayor a 5" : "La longitud del String es menor o igual a 5");


    }

}
