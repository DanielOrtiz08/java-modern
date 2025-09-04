package com.daniel.learning.threadsbasics;

// Dos formas de crear hilos

// Metodo 1: Extendiendo Thread
class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("Hilo ejecutándose: " + Thread.currentThread().getName());
    }
    public MyThread() {
        super();
    }
    public MyThread(Runnable runnable) {
        super(runnable);
    }
}

// Metodo 2: Implementando Runnable
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable ejecutándose: " + Thread.currentThread().getName());
    }
}

public class ThreadCreation {
    public static void main(String[] args) {
        System.out.println(" Creación basica de hilos");

        Thread thread1 = new MyThread();
        thread1.start();

        Thread thread2 = new MyThread(new MyRunnable());
        thread2.start();

        // Usando lambda
        Thread thread3 = new Thread(() -> {
            System.out.println("Lambda thread: " + Thread.currentThread().getName());
        });
        thread3.start();

        Thread thread4 = new MyThread(() -> {
            System.out.println("Lambda thread con MyThread: " + Thread.currentThread().getName());
        });

        Runnable runnable = () -> new MyRunnable().run();
        Thread thread5 = new Thread(runnable);
        thread5.start();

        // Ejercicio 1: Retorno de valores usando array
        final int[] result = new int[1];

        Thread calculationThread = new Thread(() -> {
           int sum = 0;
            for (int i = 1; i <= 5; i++) {
                sum += i;
            }
            result[0] = sum;
        });
        calculationThread.start();

        try {
            calculationThread.join();
            System.out.println("Suma total: " + result[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ejercicio 2: Multiples hilos

        Thread thread_A = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hilo A - Iteración " + i);
            }
        });
        Thread thread_B = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hilo B - Iteración " + i);
            }
        });
        Thread thread_priority = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hilo con prioridad - Iteración " + i);
            }
        });
        thread_priority.setPriority(Thread.MAX_PRIORITY);

        thread_A.start();
        thread_B.start();
        thread_priority.start();
    }
}
