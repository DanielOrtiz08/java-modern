Perfecto, Daniel. Cerramos con broche de oro este **Módulo 6: Buenas prácticas, ventajas y limitaciones de las lambdas en Java**. Acá es donde de verdad empezás a pensar como desarrollador con criterio, no solo como alguien que sabe usar una herramienta.

---

## 🔍 1. **Cuándo *NO* usar lambdas**
Aunque las lambdas son poderosas, hay casos donde **su uso no es recomendable**, por claridad o diseño.

### ❌ a) Cuando la lógica es compleja o extensa
Si una lambda tiene múltiples operaciones, estructuras de control (if, loops), excepciones, etc., pierde completamente la legibilidad.

**Evitá esto:**
```java
list.forEach(item -> {
    if (item.isActive()) {
        System.out.println("Activo: " + item.getName());
    } else {
        System.out.println("Inactivo");
        if (item.getErrorCode() != null) {
            log(item.getErrorCode());
        }
    }
});
```

**Mejor:**
Extraé la lógica a un método con nombre descriptivo:
```java
list.forEach(LambdasConCriterio::procesarItem);
```

---

### ❌ b) Cuando necesitás acceso a `this` y hay ambigüedad
En clases anónimas, `this` se refiere a la clase anónima.  
En lambdas, `this` **se refiere a la instancia de la clase contenedora**.

Esto puede llevar a confusión si no lo entendés bien:

```java
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println(this); // se refiere al objeto Runnable
    }
};

Runnable r2 = () -> {
    System.out.println(this); // se refiere a la clase que contiene esta lambda
};
```

---

### ❌ c) Cuando vas a reutilizar el código
Las lambdas son funciones **sin nombre**. Si ves que esa lógica puede repetirse o querés reutilizarla, creá un método con nombre en su lugar.

---

### ❌ d) Cuando hay necesidad de depuración detallada
Depurar lambdas con varios pasos es tedioso. Las lambdas no tienen nombres, por ende no puedes poner breakpoints fácilmente, ni ver claramente en el stack trace qué lambda falló (a menos que uses herramientas como `StackWalker` en Java 9+).

---

## 🚀 2. **Consideraciones de performance**
Aunque las lambdas parecen mágicas, **internamente se traducen a clases anónimas** (más o menos).

### 🧠 ¿Cómo se implementan?

- Desde Java 8, **lambdas usan `invokedynamic`** y se generan usando `LambdaMetafactory`.
- Se crea una **clase interna oculta** (pero **no** con `new` como en clases anónimas), que se instancia una vez si la lambda no captura nada (por eso es más eficiente).
- Si la lambda **captura variables**, **se crea una instancia por cada ejecución**.

### 🧩 Ejemplo:
```java
Runnable r = () -> System.out.println("Hola");
```

→ Esto es singleton si no captura variables.

```java
int factor = 5;
Function<Integer, Integer> f = x -> x * factor;
```

→ Esto **crea una nueva instancia** con cada llamada si `factor` es externo y diferente.

### ⚠️ ¿Qué tener en cuenta?

- Las lambdas **pueden ser menos eficientes** si capturan muchas variables o acceden al contexto externo.
- No uses lambdas en lugares de alto rendimiento crítico (loops intensivos), a menos que tengas claridad sobre su coste.

---

## 🆚 3. **Diferencias importantes con clases anónimas**

| Aspecto                   | Clases Anónimas                    | Lambdas                              |
|--------------------------|-------------------------------------|--------------------------------------|
| `this`                   | Se refiere a la instancia anónima   | Se refiere a la clase contenedora    |
| `super`                  | Se refiere al padre del anónimo     | No se puede usar directamente        |
| Instancia                | Se instancia cada vez con `new`     | Usa `LambdaMetafactory`              |
| Variables locales        | Pueden acceder a final o efectivamente final | Igual que lambdas                   |
| `instanceof` y tipo      | Se puede hacer `instanceof`         | No se puede (tipo funcional)         |
| Nombres                  | Tiene nombre interno `Outer$1.class` | No tiene nombre                      |
| Herencia de métodos      | Se puede sobrescribir métodos extra | Solo 1 método: SAM                   |

---

## 👁️‍🗨️ 4. **Cómo mantener la legibilidad**

> *"Una lambda debería ser más corta que el café que tomás mientras la leés."* ☕

### ✅ Buenas prácticas:

1. **Nombrá funciones compuestas**
   En lugar de hacer esto:
   ```java
   list.stream()
       .map(x -> x.trim().toLowerCase().substring(0, 3))
   ```
   Mejor:
   ```java
   Function<String, String> limpiar = s -> s.trim().toLowerCase();
   Function<String, String> acortar = s -> s.substring(0, 3);
   list.stream().map(limpiar.andThen(acortar))
   ```

2. **Extraé a métodos con nombres descriptivos**
   Si tu lambda no se entiende en menos de 2 segundos, **extraéla** a un método.

3. **Usá `var` en lambdas cuando lo permita Java 11+**
   Permite agregar anotaciones o hacer código más limpio:
   ```java
   Predicate<String> p = (@NonNull var s) -> s.length() > 3;
   ```

4. **Evitá nesting profundo**
   Nada de:
   ```java
   list.stream().map(x -> anotherList.stream().map(...))
   ```
   ¡Eso es un infierno! Usá `flatMap` o métodos auxiliares.

---

## ✅ Ventajas reales de las lambdas

- Eliminan boilerplate.
- Fomentan el uso de funciones puras.
- Permiten programación funcional (composición, inmutabilidad).
- Facilitan la paralelización con streams.

---

## ❗ Limitaciones

- Difíciles de depurar.
- No ideales para lógica compleja.
- Pueden llevar a errores sutiles si no entendés el alcance de variables.
- Si las usás sin pensar, pueden hacer tu código ilegible.

---

Con esto, **cerramos el módulo de lambdas**.  
