package com.daniel.learning.lambda.reference;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/*
1) Filtrar nombres que empiecen con letra mayúscula usando un Predicate<String>.
2) Convertir los nombres a mayúscula usando:
    una lambda
    y una referencia a metodo estático
3) Imprimir los nombres usando Consumer<String> como referencia a System.out::println
4) Crear objetos Persona (con nombre) a partir de cada string usando Function<String, Persona> con constructor.
5) Mostrar las personas.
 */

public class ExerciceNameProcessor {
    static class Persona {
        private final String nombre;
        public Persona(String nombre) { this.nombre = nombre; }
        @Override
        public String toString() { return "Persona: " + nombre;}
    }
    public static void main(String[] args) {
        List<String> nombres = List.of("Daniel", "luis", "Carlos", "andrea", "Zoe");

        Predicate<String> empiezaConMayuscula = s -> s.charAt(0) >= 'A' && s.charAt(0) <= 'Z'; // Character.isUpperCase(s.charAt(0))
        System.out.println("Nombres en la lista que empiezan con mayuscula: ");
        nombres.stream().filter(empiezaConMayuscula).forEach(s -> System.out.print(s + ", "));

        UnaryOperator<String> aMayuscula = String::toUpperCase;
        System.out.println("\nNombres en la lsita pasados a mayuscula: ");
        nombres.stream().map(aMayuscula).forEach(s -> System.out.println(s + ", "));

        Consumer <String> imprimir = System.out::println;
        System.out.println("\nNombres en la lista: ");
        nombres.forEach(imprimir);

        System.out.println("Lista de personas:");
        Function<String, Persona> constructorPersona = Persona::new;
        nombres.stream().map(constructorPersona).forEach(System.out::println);
    }
}
