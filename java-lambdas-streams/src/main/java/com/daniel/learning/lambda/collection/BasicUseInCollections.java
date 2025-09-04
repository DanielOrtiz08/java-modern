package com.daniel.learning.lambda.collection;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

public class BasicUseInCollections {
    public static void main(String[] args) {
        // Ordenamiento con lambdas
        List<String> nombres = new ArrayList<>(Arrays.asList("Basili", "Daniela", "Margarita", "Vanessa", "Daniel"));
        nombres.sort(String::compareToIgnoreCase);
        System.out.println("Orden natural: " + nombres);

        nombres.sort((a, b) -> b.compareToIgnoreCase(a));
        System.out.println("Orden inverso: " + nombres);

        // Recorrer listas
        System.out.println("Recorriendo la Lista");
        nombres.forEach(System.out::println);

        // Eliminar según condición
        List<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        numeros.removeIf(n -> n % 2 == 0);
        System.out.println("Numeros impares: " + numeros);

        // Modificar todos los elementos
        numeros.replaceAll(n -> ( n * (int) (Math.random() * 2 + 1)));
        System.out.println("Numeros multiplicados por 1, 2 o 3: " + numeros);

        List<String> letras = new ArrayList<>(List.of("a", "b", "c", "d"));
        final int[] i = {0};
        letras.replaceAll(l -> i[0]++ + ":" + l);
        System.out.println("Con índice: " + letras);

        // Opción Combinada
        List<Integer> numeros2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Numeros impares, dividios entre 1, 2 o 3: ");
        numeros2.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> (n / (int) (Math.random() * 2 + 1)))
                .toList().forEach(numero -> System.out.print(numero + ", "));

        // flatMap
        List<String> nombresYApellidos = Arrays.asList("Daniela Margarita", "Daniel Ortiz");
        List<String> nombresYApellidosSeparados = nombresYApellidos
                .stream()
                .flatMap(n -> Arrays.stream(n.split(" ")))
                .toList();

        // COMPOSICIÓN DE FUNCIONES
        Function<Integer, Integer> duplicar = n -> n * 2;
        Function<Integer, Integer> sumarTres = n -> n + 3;

        Function<Integer, Integer> duplicarYSumarTres = duplicar.andThen(sumarTres);
        System.out.println("\nDuplicar y sumar tres a 5: " + duplicarYSumarTres.apply(5));

        Function<Integer, Integer> sumarTresYDuplicar = duplicar.compose(sumarTres);
        System.out.println("Sumar tres y duplicar a 5: " + sumarTresYDuplicar.apply(5));

        // COMPOSICION DE CONDICIONES
        Predicate<String> empiezaConA = s -> s.startsWith("a");
        Predicate<String> terminaConO = s -> s.endsWith("o");

        Predicate<String> empiezaConAOTerminaConO = empiezaConA.or(terminaConO);
        System.out.println("'ajo' Empieza con 'a' o termina con 'o'?: " + empiezaConAOTerminaConO.test("ajo"));
        System.out.println("'ana' Empieza con 'a' o termina con 'o'?: " + empiezaConAOTerminaConO.test("ana"));
        System.out.println("'carro' Empieza con 'a' o termina con 'o'?: " + empiezaConAOTerminaConO.test("carro"));
        System.out.println("'rosa' Empieza con 'a' o termina con 'o'?: " + empiezaConAOTerminaConO.test("rosa"));

        Predicate<String> empiezaConAYTerminaConO = empiezaConA.and(terminaConO);
        System.out.println("'ajo' Empieza con 'a' y termina con 'o'?: " + empiezaConAYTerminaConO.test("ajo"));
        System.out.println("'ana' Empieza con 'a' y termina con 'o'?: " + empiezaConAYTerminaConO.test("ana"));
        System.out.println("'carro' Empieza con 'a' y termina con 'o'?: " + empiezaConAYTerminaConO.test("carro"));
        System.out.println("'rosa' Empieza con 'a' y termina con 'o'?: " + empiezaConAYTerminaConO.test("rosa"));

        Predicate<String> noEmpiezaConA = empiezaConA.negate();
        System.out.println("'ajo' No empieza con a?: " + noEmpiezaConA.test("ajo"));
        System.out.println("'carro' No empieza con a?: " + noEmpiezaConA.test("carro"));

        System.out.println("not 'carro' termina con 'o': " + Predicate.not(terminaConO).test("carro"));
        System.out.println("not 'carro' termina con 'o': " + Predicate.not(terminaConO).test("ana"));


        // Consumer
        Consumer<String> imprimirEnMayusculas = s -> System.out.print(s.toUpperCase() + ", ");
        nombres.forEach(imprimirEnMayusculas);

        // sort con multiples criterios: longitud, alfabetico
        nombres.sort((a, b) -> {
            int porLongitud = Integer.compare(a.length(), b.length());
            return porLongitud != 0 ? porLongitud : a.compareToIgnoreCase(b);
        });
        System.out.print("\nOrden por longitud y alfabeto: " + nombres);

        nombres.sort(Comparator.comparingInt(String::length).thenComparing(String::compareToIgnoreCase));
        System.out.println("\nOrden por longitud y alfabeto: " + nombres);

        // filter
        List<String> palabras = new ArrayList<>(Arrays.asList("agua", "aire", "fuego", "tierra", "éter"));
        Predicate<String> tiene4Letras = s -> s.length() == 4;
        palabras.stream().filter(tiene4Letras).forEach(s -> System.out.print(s + ", "));


        // biPredicate
        BiPredicate<String, Integer> validarLongitud = (s, n) -> s.length() == n;
        System.out.println("\n' hola' tiene 4 letras: " + validarLongitud.test("hola", 4));


        // dado con supplier
        Supplier<Integer> dado = () -> (int) (Math.random() * 6 + 1);
        System.out.println("Lanzando dado: " + dado.get());

        // Lista de listas
        List<List<String>> listaDeListas = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("C", "D"),
                Arrays.asList("E", "F", "G")
        );

        listaDeListas.stream()
                .flatMap(List::stream)
                .forEach(s -> System.out.print(s + ", "));
        System.out.println();

        // Depuración con peek
        List<String> planetas = Arrays.asList("Mercurio", "Venus", "Tierra", "Marte");
        planetas.stream()
                .filter(s -> s.length() > 5)
                .peek(p -> System.out.println("filtro con peek: " + p))
                .map(String::toUpperCase)
                .forEach(s -> System.out.print(s + ", "));

    }

}
