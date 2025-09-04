package com.daniel.learning.optional.nullhandling;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NullAbilityAnnotations {
    // @Nullable -> puede ser null
    public @Nullable String getNullableString(int id) {
        return id > 0 ? "id > 0" : null;
    }

    // @NotNull -> nunca debería ser null
    public @NotNull String getNotNullString(int id) {
        return id > 0 ? "id > 0" : "id <= 0";
    }

    public static void main(String[] args) {
        String nullableString = new NullAbilityAnnotations().getNullableString(-1);
        System.out.println(nullableString.toUpperCase()); // El IDE debería advertir sobre posible null

        String notNullString = new NullAbilityAnnotations().getNotNullString(-1);
        System.out.println(notNullString.toUpperCase());
    }
}
