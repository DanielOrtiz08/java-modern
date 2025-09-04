package com.daniel.learning.lambda.annymousclasses;

public class ThisInAnonymousClasses {

    String mensaje = "Mensaje desde la clase exterior";

    void ejecutar() {
        Runnable runnable = new Runnable() {
            String mensaje = "Mensaje desde la clase anonima";

            @Override
            public void run() {
                System.out.println(this.mensaje); // se refiere a 'mensaje' en la clase anonima
                System.out.println(ThisInAnonymousClasses.this.mensaje);
            }
        };

        runnable.run();
    };

    public static void main(String[] args) {
        new ThisInAnonymousClasses().ejecutar();
    }

}   
