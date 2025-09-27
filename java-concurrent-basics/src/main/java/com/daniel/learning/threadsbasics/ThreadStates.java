package com.daniel.learning.threadsbasics;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class ThreadStates {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Estados de hilos");

        //demonstrateNewState();
        //demonstrateTerminatedState();
        //demonstrateRunnableState();
        //demonstrateWaitingState();
        //demonstrateBlockedState();
        //demonstrateTimedWaitingState();
        //stateMonitorDemo();
        complexStateTransitions();
    }

    private static void demonstrateNewState() {
        System.out.println("Estado al crear (new)");
        Thread thread = new Thread(() -> {
            // ejemplo de hilo
        });

        System.out.println("Estado después de creación: " + thread.getState());
        System.out.println("¿Está vivo? " + thread.isAlive() + ".\nPrioridad: " + thread.getPriority());
    }

    // Estado Runnable, iniciado pero esperando CPU
    private static void demonstrateRunnableState() throws InterruptedException {
        System.out.println("Estado Runnable");

        Thread thread = new Thread(() -> {
            System.out.println("Simulando trabajo...");
        });

        System.out.println("Antes de start(): " + thread.getState());
        thread.start();
        System.out.println("Despues de start() y antes de sleep(): " + thread.getState());
        Thread.sleep(200);
        System.out.println("Después de start() y sleep(): " + thread.getState());
    }

    private static void demonstrateTerminatedState() {
        System.out.println("Estado Terminated");

        Thread shortThread = new Thread(() -> {
            System.out.println("Simulando tarea");
        });
        System.out.println("¿Estás vivo antes de iniciar? " + shortThread.isAlive());
        shortThread.start();
        System.out.println("¿Estás vivo despues de iniciar? " + shortThread.isAlive());
        try {
            shortThread.join(); // esperar que el hilo termine
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Estado despues de terminar: " + shortThread.getState());
        System.out.println("¿Estás vivo despues de terminar? " + shortThread.isAlive());
    }

    // Estado Waiting - hilo esperando indefinidamente
    private static void demonstrateWaitingState() throws InterruptedException {
        System.out.println("Estado Waiting");

        final Object lock = new Object();
        Thread waitingThread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Hilo entrando en wait");
                    lock.wait();
                    System.out.println("Hilo despertado");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        waitingThread.start();
        System.out.println("Estado despues start, antes de wait: " + waitingThread.getState());
        Thread.sleep(100); // Dar tiempo para que entre en lock
        System.out.println("Estado durante wait: " + waitingThread.getState());

        synchronized (lock) {
            lock.notify(); // despertar al hilo
        }
        waitingThread.join();
    }

    private static void demonstrateTimedWaitingState() {
        System.out.println("Estado Time Waiting");

        Thread sleepingThread = new Thread(() -> {
            try {
                System.out.println("Hilo durmiendo durante 2 segundos");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        sleepingThread.start();
        System.out.println("hilo despues de start antes de sleep: " + sleepingThread.getState());
        try {
            Thread.sleep(100); // Dar tiempo para que entre a sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Estado del hilo durante sleep: "+ sleepingThread.getState());
        try {
            sleepingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Estado Blocked - hilo esperando un monitor lock
    private static void demonstrateBlockedState() {
        System.out.println("\nEstado Blocked");

        final Object lock = new Object();

        // Hilo 1: Adquiere el lock y lo mantiene
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Hilo 1 adquirió el lock");
                try {
                    Thread.sleep(2000); // Mantener el lock por 2 segundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Hilo 2: Intenta adquirir el lock (se bloqueará)
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(100); // Esperar para que thread1 adquiera el lock primero
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                System.out.println("Hilo 2 adquirió el lock");
            }
        });

        thread1.start();
        thread2.start();
        System.out.println("Estado de thread1 despues de start antes de sleep: " + thread1.getState());
        System.out.println("Estado de thread2 despues de start antes de sleep: " + thread2.getState());
        try {
            Thread.sleep(150); // Esperar para que thread2 intente adquirir el lock
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Estado de thread1: " + thread1.getState());
        System.out.println("Estado de thread2 (debería ser BLOCKED): " + thread2.getState());

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Monitor de estados en tiempo real
    private static void stateMonitorDemo() {
        System.out.println("Monitor de estados");

        Thread workerThread = new Thread(() -> {
            try {
                System.out.println("Simulando trabajo...");
                Thread.sleep(1500);

                synchronized (ThreadStates.class) {
                    System.out.println("Trabajando dentro de lock");
                    Thread.sleep(750);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread monitorThread = new Thread(() -> {
           while (workerThread.isAlive()) {
               Thread.State state = workerThread.getState();
               System.out.println("Estado actual de workerThread monitoreado por monitorThread: " + state);

               try {
                   Thread.sleep(300);
               } catch (InterruptedException e) {
                   System.out.println("monitorThread interrumpido");
                    break;
               }
           }
        });
        workerThread.start();
        monitorThread.start();

        try {workerThread.join();} catch (InterruptedException e) {e.printStackTrace();}
        monitorThread.interrupt();
    }

    private static void complexStateTransitions() throws InterruptedException {
        System.out.println("Transiciones complejas");

        final Object lock1 = new Object();
        final Object lock2 = new Object();

        Thread threadA = new Thread(() -> {
           synchronized (lock1) {
               try {
                   System.out.println("ThreadA esperando en lock1");
                   lock1.wait(1000);

                   synchronized (lock2) {
                       System.out.println("ThreadA adquirió ambos locks");
                   }
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        Thread threadB = new Thread(() -> {
            synchronized (lock2) {
                try {
                    System.out.println("ThreadB esperando en lock2");
                    Thread.sleep(1000);

                    synchronized (lock2) {
                        System.out.println("ThreadB adquirió ambos locks");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("ThreadA: " + threadA.getState() + " y ThreadB: " + threadB.getState());
            if (threadA.getState() == Thread.State.BLOCKED && threadB.getState() == Thread.State.BLOCKED) {
                System.out.println("⚠️  POSIBLE DEADLOCK DETECTADO!");
            }
            Thread.sleep(300);
        }

        threadA.interrupt();
        threadB.interrupt();
        threadA.join();
        threadB.join();
    }

}
