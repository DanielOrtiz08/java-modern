package com.daniel.learning.lambda.reference;

import java.util.function.Function;
import java.util.function.Supplier;

public class ReferenceToMethodStatic {
    public static String aMayuscula(String s) {
        return  s.toUpperCase();
    }

    public static void main(String[] args) {
        Function<String, String> sinReference = s -> aMayuscula(s);
        Function<String, String> conReference = ReferenceToMethodStatic::aMayuscula;

        System.out.println("Sin referencia a metodo estatico: " + sinReference.apply("hola"));
        System.out.println("Con referencia a metodo estatico: " + conReference.apply("mundo"));


        class Persona {
            private String nombre;
            public Persona() { this.nombre = "por defecto"; }
            public Persona(String nombre) {this.nombre = nombre; }
            @Override
            public String toString() { return "nombre: " + nombre; }
        }
        Supplier<Persona> constructorSinArgs = Persona::new;
        Supplier<Persona> constructorConArgs = () -> new Persona("Daniel");
        Function<String, Persona> constructorConArgsString = Persona::new;

        Persona p1 = constructorSinArgs.get();
        Persona p2 = constructorConArgs.get();
        Persona p3 = constructorConArgsString.apply("Daniel");

        System.out.println("Persona 1: " + p1);
        System.out.println("Persona 2: " + p2);
        System.out.println("Persona 3: " + p3);

    }
}
