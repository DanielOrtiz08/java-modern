package com.daniel.learning.optional.advanced;

import java.util.Optional;

public class OptionalFlatMapExamples {

    public static void main(String[] args) {
        Usuario usuario = new Usuario("Carlos",
                new Direccion(new Ciudad("Bogotá")));

        String ciudad = Optional.of(usuario)
                .flatMap(Usuario::getDireccion)
                .flatMap(Direccion::getCiudad)
                .map(Ciudad::getNombre)
                .orElse("Ciudad desconocida");

        System.out.println("Ciudad del usuario: " + ciudad);
    }

    static class Usuario {
        private final String nombre;
        private final Direccion direccion;

        public Usuario(String nombre, Direccion direccion) {
            this.nombre = nombre;
            this.direccion = direccion;
        }

        public Optional<Direccion> getDireccion() {
            return Optional.ofNullable(direccion);
        }
    }

    static class Direccion {
        private final Ciudad ciudad;

        public Direccion(Ciudad ciudad) {
            this.ciudad = ciudad;
        }

        public Optional<Ciudad> getCiudad() {
            return Optional.ofNullable(ciudad);
        }
    }

    static class Ciudad {
        private final String nombre;

        public Ciudad(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }
}
