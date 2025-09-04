
## 📂 `integration/` — Cuándo NO usar `Optional`

### 🛑 **Uso de `Optional` en entidades JPA y DTOs**
No debes usar `Optional` en las entidades de JPA o en DTOs (Data Transfer Objects), ya que puede interferir con la serialización, el acceso a la base de datos o las consultas.

**Por qué no usarlo:**
- `Optional` no es serializable por defecto, lo que podría causar problemas en servicios REST o al manipular datos a través de frameworks como Jackson.
- La forma en que JPA maneja las entidades no es compatible con `Optional` en sus campos, ya que JPA utiliza `null` para representar valores ausentes en las bases de datos.

**Ejemplo incorrecto (malo):**
```java
@Entity
public class Usuario {
    @Id
    private Long id;

    private Optional<String> nombre; // ❌ No usar Optional aquí

    // getters y setters
}
```

**Solución:**
Usa `Optional` solo en el código de lógica de negocio (servicios, capas de aplicación) y no en las entidades.

```java
@Entity
public class Usuario {
    @Id
    private Long id;

    private String nombre;  // ✅ Mejor utilizar tipo estándar aquí

    // getters y setters
}
```

---

### 🛑 **`Optional` en parámetros de métodos públicos**
No debes utilizar `Optional` como parámetro en métodos públicos. Si tienes un método que recibe un `Optional` como argumento, estás forzando al consumidor de tu API a crear un `Optional` innecesario.

**Por qué no usarlo:**
- Es confuso para los desarrolladores que llaman al método. Usar `Optional` en parámetros no es intuitivo ni es la forma correcta de manejar valores ausentes. Se espera que un método reciba un valor o `null`, y `Optional` debe ser usado como el resultado de la función, no como entrada.

**Ejemplo incorrecto (malo):**
```java
public void actualizarUsuario(Optional<String> nombre) {  // ❌ No usar Optional como parámetro
    nombre.ifPresent(n -> {
        // lógica de actualización
    });
}
```

**Solución:**
Mejor pasar el valor directamente (o `null` si es necesario) y usar `Optional` en el cuerpo del método.

```java
public void actualizarUsuario(String nombre) {  // ✅ Usar el tipo esperado como parámetro
    Optional.ofNullable(nombre)
            .ifPresent(n -> {
                // lógica de actualización
            });
}
```

---

### 🛑 **`Optional` y serialización (problemas con Jackson)**
Si utilizas `Optional` en tus DTOs, puede que no se serialice correctamente a JSON con bibliotecas como Jackson. De hecho, Jackson no sabe cómo manejar `Optional` de forma predeterminada.

**Por qué no usarlo:**
- Jackson, al serializar, no convierte el `Optional` en algo útil para el formato JSON. En cambio, produce valores adicionales que podrían ser innecesarios o confusos.

**Ejemplo incorrecto (malo):**
```java
public class UsuarioDTO {
    private Long id;
    private Optional<String> nombre;  // ❌ No usar Optional en DTOs para serialización

    // getters y setters
}
```

**Solución:**
Mejor usar el tipo adecuado (`String`, `Integer`, etc.), y si el valor es opcional, manejarlo explícitamente.

```java
public class UsuarioDTO {
    private Long id;
    private String nombre;  // ✅ Usa el tipo primitivo o su tipo de referencia

    // getters y setters
}
```

Si es necesario indicar que el valor puede ser ausente, usa `null`.

---

### 🛑 **No usar `Optional` en colecciones de tipo primitivo**
Si tienes colecciones de tipo primitivo (por ejemplo, `List<Integer>`), usar `Optional` dentro de esas colecciones generalmente no tiene sentido y es innecesario. Si un valor en una colección es opcional, simplemente se usa `null` o `Optional` a nivel de elemento, no dentro de la colección.

**Ejemplo incorrecto (malo):**
```java
List<Optional<Integer>> numeros = List.of(Optional.of(1), Optional.empty(), Optional.of(3));  // ❌ Evitar esto
```

**Solución:**
Usa simplemente `List<Integer>` y maneja los valores `null` si es necesario.

```java
List<Integer> numeros = List.of(1, null, 3);  // ✅ Mejor sin Optional en colecciones
```

---

### 🛑 **`Optional` en métodos de acceso (getters)**
Usar `Optional` en un getter no es ideal. Los getters deben devolver directamente el valor que representa, y si el valor es opcional, es más apropiado devolver el valor directamente y envolverlo en un `Optional` a nivel de la lógica de negocio.

**Por qué no usarlo:**
- Un getter debe ser sencillo y claro. Hacer que devuelva un `Optional` agrega complejidad innecesaria y puede inducir a confusión.

**Ejemplo incorrecto (malo):**
```java
public class Producto {
    private Optional<String> nombre;

    public Optional<String> getNombre() {  // ❌ No usar Optional en getters
        return nombre;
    }
}
```

**Solución:**
Devuelve el tipo directamente y maneja la lógica de nullabilidad fuera del getter.

```java
public class Producto {
    private String nombre;

    public String getNombre() {  // ✅ Mejor con el tipo directamente
        return Optional.ofNullable(nombre).orElse("Desconocido");
    }
}
```

---

### 🛑 **`Optional` y tipos primitivos (`int`, `long`, etc.)**
No uses `Optional` con tipos primitivos (`int`, `long`, `boolean`, etc.), ya que estos tipos ya tienen valores por defecto, y no es necesario envolverlos en un `Optional`.

**Por qué no usarlo:**
- Usar `Optional` con tipos primitivos solo agrega complejidad innecesaria. Si necesitas un valor opcional, usa el tipo de referencia envolvente (`Integer`, `Long`, etc.) en lugar de los tipos primitivos.

**Ejemplo incorrecto (malo):**
```java
Optional<Integer> cantidad = Optional.of(5);  // ❌ Aunque esto está bien, no uses Optional para tipos primitivos
```

**Solución:**
Utiliza el tipo envolvente adecuado (`Integer`, `Long`, etc.), y deja que el tipo primitivo se utilice cuando sea necesario.
