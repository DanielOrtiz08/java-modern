package com.daniel.learning.optional.usecases;

import java.util.Optional;

public class OptionalInServices {
    public static void main(String[] args) {
        Optional<Usuario> encontrado = ServicioUsuario.buscarPorEmail("ddov0811@gmail.com");
        encontrado.ifPresent(usuario -> System.out.println("Usuario: " + usuario.nombre()));

        String nombre = ServicioUsuario.buscarPorEmail("nadie@gmail.com")
                .map(Usuario::nombre)
                .orElse("Invitado");
        System.out.println("Nombre: " + nombre);

        ServicioUsuario.buscarPorEmail("nadie@gmail.com")
                .ifPresentOrElse(
                        usuario -> System.out.println("Usuario: " + usuario.nombre()),
                        () -> System.out.println("Nombre: Invitado")
                );
    }
}
class ServicioUsuario {
    public static Optional<Usuario> buscarPorEmail(String email) {
        return email.equalsIgnoreCase("ddov0811@gmail.com") ?
                Optional.of(new Usuario("Daniel", email)) : Optional.empty();
    }
}

record Usuario(String nombre, String email) { }
