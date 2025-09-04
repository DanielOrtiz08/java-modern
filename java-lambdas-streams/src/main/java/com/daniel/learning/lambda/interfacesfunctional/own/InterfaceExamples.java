package com.daniel.learning.lambda.interfacesfunctional.own;

import java.util.Arrays;
import java.util.List;

public class InterfaceExamples {
    public static void main(String[] args) {
        OperationMultiple<Integer, Integer> sumarMultiplesValores = (Integer... numeros) -> Arrays.stream(numeros)
                .reduce(0, (c, d) -> c + d);
        System.out.println("Suma: " + sumarMultiplesValores.apply(2, 3, 4, 2));

        OperationMultiple<Float, Double> dividirMultiplesValores = (Float... numeros) -> {
            return Arrays.stream(numeros)
                    .mapToDouble(Float::doubleValue)
                    .reduce(1.0f, (c, d) -> c / d);
        };
        System.out.printf("División: %.4f\n", dividirMultiplesValores.apply(2.5f, 3f, 4.0f, 2f));
        List<Float> valores = List.of(2.5f, 3f, 4.0f, 2f);
        Float[] arrayValores = valores.toArray(new Float[0]);
        System.out.printf("División: %.3f\n", dividirMultiplesValores.apply(arrayValores));
    }
}
