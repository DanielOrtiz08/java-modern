package com.daniel.learning.lambda.interfacesfunctional.own;

@FunctionalInterface
interface OperationMultiple<T, R> {
    R apply(T... args);
}
