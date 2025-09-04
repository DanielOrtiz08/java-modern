package com.daniel.learning.lambda.annymousclasses;

public class MicroExercice {
    
    public static void main(String[] args) {
        
        @FunctionalInterface
        interface Tarea<T> {
            void ejecutar(T t);
        }
        class Evento<T> {
            public void disparar(Tarea<T> tarea, T arg) {
                tarea.ejecutar(arg);
            }
        }
        
        
        Tarea<String> mensaje = System.out::println;
        Tarea<Double> areaCirculo = new Tarea<>() {
            final double PI = Math.PI;
            @Override
            public void ejecutar(Double radio) {
                double area = PI * (radio * radio);
                System.out.printf("Area del circulo: %.3f\n", area);
            }
        };

        String saludo = "Hola";
        Evento<String> evento1 = new Evento<>();
        evento1.disparar(mensaje, saludo);
        
        double radio = 5;
        Evento<Double> evento2 = new Evento<>();
        evento2.disparar(areaCirculo, radio);


    }
}
