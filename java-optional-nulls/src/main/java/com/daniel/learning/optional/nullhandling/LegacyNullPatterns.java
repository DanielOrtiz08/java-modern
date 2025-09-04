package com.daniel.learning.optional.nullhandling;

// Patrones legados de manejo de null
public class LegacyNullPatterns {
    // Patron 1: chequeo manual
    public String process(String input) { // Validamos si input es null por condición
        if (input != null)
            return input.trim().toUpperCase();
        return "DEFAULT";
    }
    public String processWithTryCatch(String input) { // Si input es null, aqui atrapamos NPE
        try {
            return input.trim().toUpperCase();
        } catch (NullPointerException e) {
            return "DEFAULT";
        }
    }
    public String handleWithTernary(String input) { // Ternario (puede ser igual de malo)
        return input != null ? input.trim().toUpperCase(): "DEFAULT";
    }
    public String getUsernameById(int id) {
        return id < 0 ? null : "usuario " + id;
    }

    public static void main(String[] args) {
        System.out.println(new LegacyNullPatterns().process(null));
        System.out.println(new LegacyNullPatterns().processWithTryCatch(null));
        System.out.println(new LegacyNullPatterns().handleWithTernary(null));
        System.out.println(new LegacyNullPatterns().getUsernameById(-1).length());
    }
}


