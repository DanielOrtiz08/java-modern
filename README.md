# Java Modern Playground 🚀

[![Java 21+](https://img.shields.io/badge/Java-21%2B-red?logo=openjdk)](https://openjdk.org/projects/jdk/21/)
[![Gradle 8.10+](https://img.shields.io/badge/Gradle-8.10%2B-blue?logo=gradle)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-Apache_2.0-green.svg)](https://opensource.org/licenses/Apache-2.0)

Repositorio práctico para dominar features modernos de Java (21+) con enfoque en programación funcional, concurrencia avanzada y reactividad.

## 🚦 Características Principales
- Ejemplos prácticos de Java moderno (21+)
- Programación funcional con Lambdas y Streams
- Manejo avanzado de concurrencia y paralelismo
- Patrones modernos con Virtual Threads (Project Loom)
- Programación reactiva con Reactor Core
- Manejo seguro de nulos con Optional
- Patrones de diseño con sealed classes
- Configuraciones con Gradle moderno

## 📦 Módulos Principales

| Módulo | Descripción | Tecnologías Clave |
|--------|-------------|-------------------|
| [java-lambdas-streams](java-lambdas-streams) | Programación funcional con expresiones lambda y Stream API | Functional Interfaces, Method References |
| [java-optional-nulls](java-optional-nulls) | Manejo seguro de nulos con Optional | Optional API, Null Safety Patterns |
| [java-sealed-patterns](java-sealed-patterns) | Patrones con sealed classes y pattern matching | Sealed Classes, Pattern Matching |
| [java-concurrent-basics](java-concurrent-basics) | Fundamentos de concurrencia en Java | Executors, Synchronization |
| [java-completable-futures](java-completable-futures) | Programación asíncrona con CompletableFuture | Async Programming, Chaining |
| [java-virtual-threads](java-virtual-threads) | Concurrencia a gran escala con Virtual Threads | Project Loom, Structured Concurrency |
| [java-reactive-core](java-reactive-core) | Programación reactiva con Reactor Core | Flux, Mono, Backpressure |
| [java-reactor-loom](java-reactor-loom) | Integración Reactor + Virtual Threads | Reactive Streams, Project Loom |

## 🛠️ Tecnologías Utilizadas
- **Java 21+** (Features modernos: sealed classes, pattern matching, virtual threads)
- **Gradle 8.10+** (Build tool con soporte para Java moderno)
- **Project Reactor** (Para programación reactiva)
- **Lombok** (Para reducir boilerplate code)
- **JUnit 5** (Para pruebas unitarias)

## ⚙️ Configuraciones Requeridas
- JDK 21+ ([Eclipse Temurin](https://adoptium.net/))
- Gradle 8.10+ (`gradle wrapper` incluido)
- Lombok plugin (para tu IDE)

## 🚀 Comenzando

1. Clonar repositorio:
   ```bash
   git clone https://github.com/DanielOrtiz08/java-modern.git