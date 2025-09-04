package com.daniel.learning.optional.usecases;

/*
Aquí exploraremos todas las combinaciones posibles de Optional con operaciones de Stream, divididas en secciones:

1) Búsquedas seguras y uso de findFirst, findAny
2) Composición de Optional con map, flatMap, filter
3) Extracción condicional y flujos con fallback
4) Integración en pipelines complejos
5) Validaciones encadenadas con múltiples Optional
6) Filtros anidados usando .flatMap()
 */

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OptionalInStreams {
    public static void main(String[] args) {
        //  Parte 1: Búsquedas seguras con findFirst, findAny
        List<String> nombres = List.of("Andrea", "Daniel", "Basili", "Sofia", "David");

        Optional<String> nombreConD = nombres.stream()
                .filter(nombre -> nombre.startsWith("D"))
                .findFirst();
        nombreConD.ifPresentOrElse(
                nombre -> System.out.println("Primero nombre en la lista que empieza con D: " + nombre),
                () -> System.out.println("Ninguna conincidencia en la lista")
        );

        Optional<String> nombreConCincoLetras = nombres
                .stream().filter(nombre -> nombre.length() > 5)
                .findAny();
        System.out.println("Nombre con 5 letras: " + nombreConCincoLetras.orElse("Ninguno"));


        // Parte 2: Uso de map, flatMap, filter sobre Optional
        Optional<String> nombre = Optional.of("Daniel");

        nombre.map(String::toUpperCase).ifPresent(n -> System.out.println("Nombre en mayuscula: " + n));

        nombre.filter(n -> n.length() > 13)
                .ifPresent(n -> System.out.println("Filtrado por longitud >3: " + n));

        Optional<String> procesado = nombre.flatMap(OptionalInStreams::procesarNombre);
        System.out.println("Procesando: " + procesado.orElse("No procesado"));


        // Parte 3: Fallback y encadenamiento condicional
        List<String> correos = nombres.stream().map(n -> n + "@gmail.com").toList();
        Function<? super String, ? extends Stream<? extends String>> nombreMasDominio = n -> Stream.of(n + "@gmail.com"); // Function<String, Stream<String>> lo mismo sin carreta
        List<String> correos2 = nombres.stream().flatMap(nombreMasDominio).toList();

        Optional<String> correoPrincipal = buscarCorreo(correos, "Soporte@gmail.com");
        String resultado = correoPrincipal.
                or(() -> buscarCorreo(correos, "Admin@gmail.com"))
                .orElse(correos2.getFirst());
        System.out.println("Correo final: " + resultado);

        // Parte 4: Validaciones encadenadas con múltiples Optional
        Optional<String> usuario = Optional.of("  Andres ");
        usuario.map(String::trim)
                .filter(u -> !u.isEmpty())
                .filter(u -> u.length() >= 3)
                .map(String::toUpperCase)
                .ifPresent(u -> System.out.println("Usuario valido: " + u));


        // Parte 5: IntStream o Stream.iterate(), luego Streams anidados + flatMap de Optional
        List<User> usuarios = new ArrayList<>(IntStream.range(0, nombres.size())
                .mapToObj(i ->
                        new User(nombres.get(i), correos.get(i))
                ).toList());
        List<User> usuarios2 = Stream
                .iterate(0, i -> i < nombres.size(), i -> i + 1)
                .map(i -> new User(nombres.get(i), correos.get(i))).toList();
        List<User> usuario3 = Stream.iterate(0, i -> i + 1)
                .limit(nombres.size()) // bucle infinito si no está o está despues de mapear: IndexOutOfBoundsException
                .flatMap(i -> Stream.of(new User(nombres.get(i), correos.get(i)))) // es como usar una motociera para cortar una hoja
                .toList();
        usuarios.add(new User("Usuario con correo null", null));
        usuarios.stream()
                .map(User::getEmailOptional)
                .flatMap(Optional::stream) // de Optional<String> a Stream<String> con solo los presentes
                // Optional::stream devuelve 1 elemento si Optional tiene valor y 0 elementos si está vacío (Optional.empty()).
                .forEach(c -> System.out.println("Correo: " + c));


        // Parte 6: Optional como resultado de operaciones stream intermedias
        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);
        OptionalDouble promedio = numeros
                .stream()
                .mapToInt(Integer::intValue)
                .average();
        promedio.ifPresentOrElse(avg -> System.out.println("Promedio: " + avg), () -> System.out.println("No hay promedio"));

    }

    private static Optional<String> buscarCorreo(List<String> correos, String mail) {
        return correos.stream()
                .filter(c -> c.equalsIgnoreCase(mail))
                .findFirst();
    }
    private static @NotNull Optional<? extends String> procesarNombre(@NotNull String nombre) {
        return nombre.equalsIgnoreCase("Daniel") ? Optional.of("Aprendiendo Optional") : Optional.empty();
    }
}
@Entity
class User {
    private final String nombre;
    private final String email;
    public User(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
    public Optional<String> getEmailOptional() {
        return Optional.ofNullable(email);
    }
}































