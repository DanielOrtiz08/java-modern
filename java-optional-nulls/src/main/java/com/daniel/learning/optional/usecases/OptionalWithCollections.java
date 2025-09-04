package com.daniel.learning.optional.usecases;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OptionalWithCollections {
    public static void main(String[] args) {
        List<String> productos = List.of("Monitor", "Teclado", "Mouse", "Notebook", "Impresora");

        // Buscar un producto especifico
        Optional<String> encontrado = buscarProducto(productos, "Teclado");
        encontrado.ifPresentOrElse(p -> System.out.println("Producto " + p + " encontrado"), () -> System.out.println("Producto no encontrado"));

        // Obtener el primero que empieze con N
        productos.stream()
                .filter(p -> p.startsWith("N"))
                .findFirst().ifPresent(p-> System.out.println("Primero con N: " + p));

        // Buscar en un mapa
        Map<String, Integer> stock = Map.of("Monitor", 10, "Teclado", 20, "Mouse", 30);
        Optional<Integer> stockMouse = Optional.ofNullable(stock.get("Mouse"));
        stockMouse.ifPresentOrElse(s -> System.out.println("Stock del mouse: " + s), () -> System.out.println("No hay stock del mouse"));

        // Combinar búsqueda y validación
        Optional<String> validador = buscarProducto(productos, "Monitor")
                .filter(p -> p.startsWith("M"))
                .filter(p -> p.length() > 6);
        validador.ifPresent(p -> System.out.println("Producto validado: " + p   ));
    }

    private static Optional<String> buscarProducto(List<String> productos, String producto) {
        return productos.stream()
                .filter(p -> p.equalsIgnoreCase(producto))
                .findAny();
    }
}
