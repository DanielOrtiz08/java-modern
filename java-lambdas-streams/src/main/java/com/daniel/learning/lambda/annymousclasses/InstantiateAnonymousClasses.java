package com.daniel.learning.lambda.annymousclasses;

public class InstantiateAnonymousClasses {
    public static void main(String[] args) throws NoSuchFieldException {
        String comida = "Manzana";

        abstract class Animal {
            private final int atributoPrivado = 0;
            void hacerSonido() {
                System.out.println("Sonido generico");
            }
            void comer() {}
        };
        Animal gato = new Animal() {
            int numVidas = 7;
            @Override
            void hacerSonido() {
                // int intentandoAcceder = atributoPrivado; // No es posible acceder porque es privada
                System.out.println("miauu");
            }
            @Override
            void comer() {
                System.out.println("Comiendo " + comida);
            }
        };
        gato.hacerSonido();
        gato.comer();
        // comida = "Pera"; // error: local variables referenced from an inner class must be final or effectively final
        // gato.numVidas // Error: No se puede acceder porque la referencia es del tipo Animal

        class Gato extends Animal {
            int numVidas;
        }
        Gato gatoCast = (Gato) gato;

        // tambien se puedes instanciar Interfaces y Clases concretas
    }
}

// NOTAS FINALES

// Cuando usar Clases Anonimas en vez de Lambdas
// 1) Cuando necesitas definir mas de un método
// 2) Si necesitas acceder a miembros de la clase padre como atributos privados
// 3) Cuando vas a manejar una logica compleja



