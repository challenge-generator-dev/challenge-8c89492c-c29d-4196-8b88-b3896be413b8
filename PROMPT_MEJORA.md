# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener el proyecto completo y arrancable. Guarda los archivos que te devuelva en la
carpeta del repo, respetando exactamente las rutas.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/infrastructure/adapter/PaymentServiceClient.java` — La pieza p1 (Implementación de reintentos con backoff exponencial usando Resilience4j, fase f1) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/infrastructure/adapter/PaymentServiceCircuitBreaker.java` — La pieza p2 (Implementación de circuit breaker usando Resilience4j, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/infrastructure/adapter/PaymentTimeoutHandler.java` — La pieza p3 (Implementación de timeouts para operaciones críticas, fase f3) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/infrastructure/adapter/IdempotencyKeyValidator.java` — La pieza p4 (Validación de claves de idempotencia para operaciones críticas, fase f3) vive aqui: este archivo es su unico dueno (blueprint v2).

## Como saber que terminaste

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollo backend, Tecnología Java, Senior L2

### Brecha de conocimiento
Diseña la comunicación entre servicios tolerante a fallos: reintentos con backoff, circuit breaker, timeouts e idempotencia de las operaciones.

### Misión / candidato
Reducir los incidentes por caídas del proveedor de pagos

### Reto
- Tema: Diseño de microservicios resilientes
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de patrones de resiliencia en la integración con el proveedor de pagos
- Tiempo estimado: 15 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Implementación de reintentos con backoff — objetivo: Minimizar el impacto de fallos temporales en la comunicación con el proveedor externo. — entregable (NO resolver): Mecanismo de reintentos con backoff implementado y documentado.
- Fase 2: Implementación de circuit breaker — objetivo: Proteger el sistema de fallos prolongados en el proveedor externo. — entregable (NO resolver): Circuit breaker implementado y documentado.
- Fase 3: Implementación de timeouts e idempotencia — objetivo: Asegurar que las operaciones sean seguras y no causen efectos secundarios indeseados en caso de fallos. — entregable (NO resolver): Timeouts e idempotencia implementados y documentados.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (entrega de archivos).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Entrega de archivos
Entregá cada archivo con su ruta completa, respetando exactamente la estructura de rutas
indicada por los marcadores: un bloque por archivo, precedido por su ruta. No generes ZIPs
ni archivos adjuntos.
La entrega debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: Entregá los archivos completos al finalizar. No preguntes si el usuario
quiere recibirlos. Simplemente entregalos; No debes desplegar en el chat el resumen de lo que arreglaste, solo entregá los archivos.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del último archivo entregado
No preguntes si el usuario quiere los archivos: simplemente entregalos siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: src/main/java/Application.java ===
package com.pragma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.time.Clock;

// Arquitectura hexagonal: domain (modelo y puertos), application (casos de uso) e infrastructure (adaptadores).
// Este archivo solo arranca el contexto y expone infraestructura transversal. La logica de resiliencia
// (reintentos, circuit breaker, timeouts) vive en sus piezas dueñas dentro de infrastructure/adapter.
// Patrones de resiliencia exigidos por el pedido: @Retry, @CircuitBreaker, @TimeLimiter e IdempotencyKey.
@SpringBootApplication(scanBasePackages = {"com.pragma", "domain", "application", "infrastructure"})
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // Reloj inyectable: los adaptadores reciben el tiempo por constructor, lo que permite pruebas deterministas.
    @Configuration(proxyBeanMethods = false)
    static class TimeConfiguration {

        @Bean
        public Clock systemClock() {
            return Clock.systemUTC();
        }
    }

    // Traza de arranque: confirma que el contexto completo quedo listo para recibir peticiones.
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        log.info("Servicio de procesamiento de pagos listo para recibir peticiones");
    }
}

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.13</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>reto</artifactId>
    <version>1.0.0</version>
    <name>reto</name>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-micrometer</artifactId>
            <version>2.4.0</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>2.4.0</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>2.4.0</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/domain/model/PaymentRequest.java ===
package domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record PaymentRequest(
    @NotNull(message = "Transaction ID cannot be null")
    @Size(min = 1, max = 64, message = "Transaction ID must be between 1 and 64 characters")
    String transactionId,

    @NotNull(message = "Account ID cannot be null")
    @Size(min = 1, max = 64, message = "Account ID must be between 1 and 64 characters")
    String accountId,

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    BigDecimal amount,

    @NotNull(message = "Currency cannot be null")
    @Size(min = 3, max = 3, message = "Currency must be a 3-character code")
    String currency,

    @NotNull(message = "Idempotency key cannot be null")
    @Size(min = 1, max = 64, message = "Idempotency key must be between 1 and 64 characters")
    String idempotencyKey,

    @NotNull(message = "Timestamp cannot be null")
    LocalDateTime timestamp,

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    String description
) {
    public PaymentRequest {
        Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        Objects.requireNonNull(accountId, "Account ID cannot be null");
        Objects.requireNonNull(amount, "Amount cannot be null");
        Objects.requireNonNull(currency, "Currency cannot be null");
        Objects.requireNonNull(idempotencyKey, "Idempotency key cannot be null");
        Objects.requireNonNull(timestamp, "Timestamp cannot be null");

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (currency.length() != 3) {
            throw new IllegalArgumentException("Currency must be a 3-character code");
        }
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }
}

// === ARCHIVO: src/main/java/domain/model/PaymentResponse.java ===
package domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

public record PaymentResponse(
    @NotNull(message = "Transaction ID cannot be null")
    @Size(min = 1, max = 64, message = "Transaction ID must be between 1 and 64 characters")
    String transactionId,

    @NotNull(message = "Status cannot be null")
    PaymentStatus status,

    @NotNull(message = "Timestamp cannot be null")
    LocalDateTime timestamp,

    @Size(max = 255, message = "Message cannot exceed 255 characters")
    String message,

    @Size(max = 64, message = "Provider reference cannot exceed 64 characters")
    String providerReference
) {
    public PaymentResponse {
        Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");
        Objects.requireNonNull(timestamp, "Timestamp cannot be null");
    }

    public enum PaymentStatus {
        SUCCESS,
        FAILED,
        PENDING,
        REJECTED
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getProviderReference() {
        return providerReference;
    }
}

// === ARCHIVO: src/main/java/domain/model/PaymentFailedException.java ===
package domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class PaymentFailedException extends RuntimeException {
    private final String transactionId;
    private final LocalDateTime failureTime;
    private final String providerMessage;

    public PaymentFailedException(String transactionId, String message, String providerMessage) {
        super(message);
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.failureTime = LocalDateTime.now();
        this.providerMessage = Objects.requireNonNull(providerMessage, "Provider message cannot be null");
    }

    public PaymentFailedException(String transactionId, String message, String providerMessage, Throwable cause) {
        super(message, cause);
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.failureTime = LocalDateTime.now();
        this.providerMessage = Objects.requireNonNull(providerMessage, "Provider message cannot be null");
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getFailureTime() {
        return failureTime;
    }

    public String getProviderMessage() {
        return providerMessage;
    }

    @Override
    public String toString() {
        return "PaymentFailedException{" +
                "transactionId='" + transactionId + '\'' +
                ", failureTime=" + failureTime +
                ", providerMessage='" + providerMessage + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/domain/model/CircuitBreakerOpenException.java ===
package domain.model;

import java.time.Duration;
import java.util.Objects;

public class CircuitBreakerOpenException extends RuntimeException {
    private final String circuitBreakerName;
    private final Duration remainingDelay;
    private final int failureCount;
    private final double failureRate;

    public CircuitBreakerOpenException(String circuitBreakerName, Duration remainingDelay,
                                      int failureCount, double failureRate) {
        super(String.format("Circuit breaker '%s' is open. Remaining delay: %d ms, " +
                "failure count: %d, failure rate: %.2f%%",
                circuitBreakerName, remainingDelay.toMillis(), failureCount, failureRate));
        this.circuitBreakerName = Objects.requireNonNull(circuitBreakerName, "Circuit breaker name cannot be null");
        this.remainingDelay = Objects.requireNonNull(remainingDelay, "Remaining delay cannot be null");
        this.failureCount = failureCount;
        this.failureRate = failureRate;

        if (remainingDelay.isNegative()) {
            throw new IllegalArgumentException("Remaining delay cannot be negative");
        }
        if (failureCount < 0) {
            throw new IllegalArgumentException("Failure count cannot be negative");
        }
        if (failureRate < 0 || failureRate > 100) {
            throw new IllegalArgumentException("Failure rate must be between 0 and 100");
        }
    }

    public String getCircuitBreakerName() {
        return circuitBreakerName;
    }

    public Duration getRemainingDelay() {
        return remainingDelay;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public double getFailureRate() {
        return failureRate;
    }

    public boolean isRetryAllowed() {
        return remainingDelay.isZero();
    }

    public String getFormattedMessage() {
        return String.format("Circuit breaker '%s' is currently open. " +
                "Please retry after %d milliseconds. Current failure rate: %.2f%% (%d failures)",
                circuitBreakerName, remainingDelay.toMillis(), failureRate, failureCount);
    }
}

// === ARCHIVO: src/main/java/domain/model/PaymentTimeoutException.java ===
package domain.model;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

public class PaymentTimeoutException extends RuntimeException {
    private final String transactionId;
    private final Duration timeoutDuration;
    private final String operationDescription;
    private final long elapsedTimeMillis;

    public PaymentTimeoutException(String transactionId, Duration timeoutDuration,
                                 String operationDescription, long elapsedTimeMillis) {
        super(String.format("Payment operation '%s' for transaction '%s' timed out after %d ms (configured timeout: %d ms)",
                operationDescription, transactionId, elapsedTimeMillis, timeoutDuration.toMillis()));
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.timeoutDuration = Objects.requireNonNull(timeoutDuration, "Timeout duration cannot be null");
        this.operationDescription = Objects.requireNonNull(operationDescription, "Operation description cannot be null");
        this.elapsedTimeMillis = elapsedTimeMillis;

        if (timeoutDuration.isNegative() || timeoutDuration.isZero()) {
            throw new IllegalArgumentException("Timeout duration must be positive");
        }
        if (elapsedTimeMillis < 0) {
            throw new IllegalArgumentException("Elapsed time cannot be negative");
        }
    }

    public PaymentTimeoutException(String transactionId, Duration timeoutDuration,
                                 String operationDescription, long elapsedTimeMillis, Throwable cause) {
        super(String.format("Payment operation '%s' for transaction '%s' timed out after %d ms (configured timeout: %d ms)",
                operationDescription, transactionId, elapsedTimeMillis, timeoutDuration.toMillis()), cause);
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.timeoutDuration = Objects.requireNonNull(timeoutDuration, "Timeout duration cannot be null");
        this.operationDescription = Objects.requireNonNull(operationDescription, "Operation description cannot be null");
        this.elapsedTimeMillis = elapsedTimeMillis;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Duration getTimeoutDuration() {
        return timeoutDuration;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public long getElapsedTimeMillis() {
        return elapsedTimeMillis;
    }

    public boolean isTimeoutExceeded() {
        return elapsedTimeMillis >= timeoutDuration.toMillis();
    }

    public static PaymentTimeoutException fromTimeoutException(String transactionId,
                                                              Duration timeoutDuration,
                                                              String operationDescription,
                                                              TimeoutException cause) {
        return new PaymentTimeoutException(
                transactionId,
                timeoutDuration,
                operationDescription,
                timeoutDuration.toMillis(),
                cause
        );
    }
}

// === ARCHIVO: src/main/java/domain/model/DuplicateOperationException.java ===
package domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class DuplicateOperationException extends RuntimeException {
    private final String idempotencyKey;
    private final String transactionId;
    private final LocalDateTime originalOperationTime;
    private final String originalStatus;

    public DuplicateOperationException(String idempotencyKey, String transactionId,
                                      LocalDateTime originalOperationTime, String originalStatus) {
        super(String.format("Duplicate operation detected for idempotency key '%s'. " +
                "Original transaction '%s' was processed at %s with status '%s'",
                idempotencyKey, transactionId, originalOperationTime, originalStatus));
        this.idempotencyKey = Objects.requireNonNull(idempotencyKey, "Idempotency key cannot be null");
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.originalOperationTime = Objects.requireNonNull(originalOperationTime, "Original operation time cannot be null");
        this.originalStatus = Objects.requireNonNull(originalStatus, "Original status cannot be null");
    }

    public DuplicateOperationException(String idempotencyKey, String transactionId,
                                      LocalDateTime originalOperationTime, String originalStatus,
                                      Throwable cause) {
        super(String.format("Duplicate operation detected for idempotency key '%s'. " +
                "Original transaction '%s' was processed at %s with status '%s'",
                idempotencyKey, transactionId, originalOperationTime, originalStatus), cause);
        this.idempotencyKey = Objects.requireNonNull(idempotencyKey, "Idempotency key cannot be null");
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.originalOperationTime = Objects.requireNonNull(originalOperationTime, "Original operation time cannot be null");
        this.originalStatus = Objects.requireNonNull(originalStatus, "Original status cannot be null");
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getOriginalOperationTime() {
        return originalOperationTime;
    }

    public String getOriginalStatus() {
        return originalStatus;
    }

    public boolean isSafeToRetry() {
        return "PENDING".equals(originalStatus) || "FAILED".equals(originalStatus);
    }

    @Override
    public String toString() {
        return "DuplicateOperationException{" +
                "idempotencyKey='" + idempotencyKey + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", originalOperationTime=" + originalOperationTime +
                ", originalStatus='" + originalStatus + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/infrastructure/adapter/PaymentServiceClient.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapter;

import domain.model.PaymentFailedException;
import domain.model.PaymentRequest;
import domain.model.PaymentResponse;
import domain.port.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceClient {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceClient.class);
    private static final int DEFAULT_MAX_RETRIES = 3;
    private static final long INITIAL_INTERVAL_MS = 500;
    private static final double MULTIPLIER = 2.0;
    private static final long MAX_INTERVAL_MS = 5000;

    private final Retry retry;

    public PaymentServiceClient(Retry retry) {
        this.retry = retry;
    }

    public PaymentResponse processPayment(PaymentRequest request) throws PaymentFailedException {
      throw new UnsupportedOperationException("TODO(reto): processPayment");
    }

    private PaymentResponse executePayment(PaymentRequest request) {
      throw new UnsupportedOperationException("TODO(reto): executePayment");
    }

    private PaymentResponse callPaymentProvider(PaymentRequest request) {
      throw new UnsupportedOperationException("TODO(reto): callPaymentProvider");
    }

    private boolean isPermanentFailure(PaymentFailedException e) {
      throw new UnsupportedOperationException("TODO(reto): isPermanentFailure");
    }
}

// === ARCHIVO: src/main/java/infrastructure/adapter/PaymentServiceCircuitBreaker.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapter;

import domain.model.CircuitBreakerOpenException;
import domain.model.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@Component
public class PaymentServiceCircuitBreaker {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceCircuitBreaker.class);
    private static final int DEFAULT_FAILURE_THRESHOLD = 3;
    private static final int DEFAULT_SUCCESS_THRESHOLD = 2;
    private static final Duration DEFAULT_WAIT_DURATION_IN_OPEN_STATE = Duration.ofSeconds(10);
    private static final double DEFAULT_FAILURE_RATE_THRESHOLD = 50.0;

    private final domain.port.CircuitBreaker circuitBreaker;

    private enum State {
        CLOSED,
        OPEN,
        HALF_OPEN
    }

    private volatile State currentState = State.CLOSED;
    private final AtomicInteger failureCount = new AtomicInteger(0);
    private final AtomicInteger successCount = new AtomicInteger(0);
    private Instant lastFailureTime;
    private final Object stateLock = new Object();

    public PaymentServiceCircuitBreaker(domain.port.CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    public PaymentResponse executeWithCircuitBreaker(Supplier<PaymentResponse> operation)
            throws CircuitBreakerOpenException {
              throw new UnsupportedOperationException("TODO(reto): executeWithCircuitBreaker");
            }

    private void onSuccess() {
      throw new UnsupportedOperationException("TODO(reto): onSuccess");
    }

    private void onFailure() {
      throw new UnsupportedOperationException("TODO(reto): onFailure");
    }

    private boolean shouldAttemptRecovery() {
      throw new UnsupportedOperationException("TODO(reto): shouldAttemptRecovery");
    }

    private Duration calculateRemainingDelay() {
      throw new UnsupportedOperationException("TODO(reto): calculateRemainingDelay");
    }

    private double calculateFailureRate() {
      throw new UnsupportedOperationException("TODO(reto): calculateFailureRate");
    }

    public State getCurrentState() {
        return currentState;
    }

    public int getFailureCount() {
      throw new UnsupportedOperationException("TODO(reto): getFailureCount");
    }

    public void reset() {
      throw new UnsupportedOperationException("TODO(reto): reset");
    }
}

// === ARCHIVO: src/main/java/infrastructure/adapter/PaymentTimeoutHandler.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapter;

import domain.model.PaymentResponse;
import domain.model.PaymentTimeoutException;
import domain.port.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

@Component
public class PaymentTimeoutHandler {

    private static final Logger log = LoggerFactory.getLogger(PaymentTimeoutHandler.class);
    private static final String DEFAULT_OPERATION_DESCRIPTION = "Payment processing";

    private final TimeLimiter timeLimiter;
    private final ExecutorService executorService;

    public PaymentTimeoutHandler(TimeLimiter timeLimiter) {
        this.timeLimiter = timeLimiter;
        this.executorService = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r, "payment-timeout-handler");
            t.setDaemon(true);
            return t;
        });
    }

    public PaymentResponse executeWithTimeout(Supplier<PaymentResponse> operation, Duration timeout)
            throws PaymentTimeoutException {
              throw new UnsupportedOperationException("TODO(reto): executeWithTimeout");
            }

    public PaymentResponse executeWithTimeout(Supplier<PaymentResponse> operation) {
      throw new UnsupportedOperationException("TODO(reto): executeWithTimeout");
    }

    private String extractTransactionId(Supplier<PaymentResponse> operation) {
      throw new UnsupportedOperationException("TODO(reto): extractTransactionId");
    }

    public void shutdown() {
      throw new UnsupportedOperationException("TODO(reto): shutdown");
    }

    /**
     * Registra el proveedor de TimeLimiter que PaymentTimeoutHandler necesita por constructor.
     * Sin este bean el contenedor no puede resolver la dependencia y la aplicación no arranca.
     */
    @Configuration
    static class TimeLimiterConfiguration {

        @Bean
        TimeLimiter paymentTimeLimiter() {
            return new ExecutorTimeLimiter(Duration.ofSeconds(5));
        }
    }

    /**
     * Implementación de infraestructura del puerto TimeLimiter basada en un pool de hilos demonio.
     */
    static final class ExecutorTimeLimiter implements TimeLimiter {

        private final ExecutorService pool = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r, "time-limiter");
            t.setDaemon(true);
            return t;
        });
        private volatile Duration defaultTimeout;

        ExecutorTimeLimiter(Duration defaultTimeout) {
            this.defaultTimeout = defaultTimeout;
        }

        @Override
        public <T> T executeWithTimeout(Callable<T> operation, Duration timeout) throws Exception {
            return execute(operation, timeout);
        }

        @Override
        public <T> T executeWithTimeout(Supplier<T> operation, Duration timeout) {
            try {
                return execute((Callable<T>) operation::get, timeout);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public void executeWithTimeout(Runnable operation, Duration timeout) throws Exception {
            execute((Callable<Void>) () -> {
                operation.run();
                return null;
            }, timeout);
        }

        @Override
        public boolean tryExecuteWithTimeout(Supplier<Boolean> operation, Duration timeout) {
            try {
                Boolean result = execute((Callable<Boolean>) operation::get, timeout);
                return Boolean.TRUE.equals(result);
            } catch (Exception e) {
                log.debug("Operación no completada dentro del tiempo límite: {}", e.getMessage());
                return false;
            }
        }

        @Override
        public Duration getDefaultTimeout() {
            return defaultTimeout;
        }

        @Override
        public void setDefaultTimeout(Duration timeout) {
            this.defaultTimeout = timeout;
        }

        private <T> T execute(Callable<T> operation, Duration timeout) throws Exception {
            Future<T> future = pool.submit(operation);
            try {
                return future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                future.cancel(true);
                throw e;
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause instanceof Exception ex) {
                    throw ex;
                }
                throw new IllegalStateException(cause);
            }
        }
    }
}

// === ARCHIVO: src/main/java/infrastructure/adapter/IdempotencyKeyValidator.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapter;

import domain.model.DuplicateOperationException;
import domain.port.IdempotencyKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class IdempotencyKeyValidator {

    private static final Logger log = LoggerFactory.getLogger(IdempotencyKeyValidator.class);

    private final IdempotencyKeyRepository repository;
    private final ReentrantLock lock = new ReentrantLock();

    public IdempotencyKeyValidator(IdempotencyKeyRepository repository) {
        this.repository = repository;
    }

    public void validateIdempotencyKey(String key, String operationId) throws DuplicateOperationException {
      throw new UnsupportedOperationException("TODO(reto): validateIdempotencyKey");
    }

    public boolean isKeyValid(String key) {
      throw new UnsupportedOperationException("TODO(reto): isKeyValid");
    }

    public void markAsCompleted(String key, String status) {
      throw new UnsupportedOperationException("TODO(reto): markAsCompleted");
    }

    public void removeIdempotencyKey(String key) {
      throw new UnsupportedOperationException("TODO(reto): removeIdempotencyKey");
    }
}

// === ARCHIVO: src/main/java/domain/port/Retry.java ===
package domain.port;

import java.time.Duration;
import java.util.function.Supplier;

public interface Retry {

    <T> T executeWithRetry(Supplier<T> operation, Class<? extends Exception>... transientExceptions);

    int getMaxAttempts();

    Duration getInitialInterval();

    double getMultiplier();

    Duration getMaxInterval();

    boolean isRetryEnabled();
}

// === ARCHIVO: src/main/java/domain/port/CircuitBreaker.java ===
package domain.port;

import java.time.Duration;
import java.util.function.Supplier;

public interface CircuitBreaker {

    enum State {
        CLOSED,
        OPEN,
        HALF_OPEN
    }

    String getName();

    State getCurrentState();

    void onSuccess();

    void onFailure();

    boolean shouldAttemptRecovery();

    Duration calculateRemainingDelay();

    double calculateFailureRate();

    int getFailureCount();

    void reset();

    <T> T execute(Supplier<T> operation);
}

// === ARCHIVO: src/main/java/domain/port/TimeLimiter.java ===
package domain.port;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public interface TimeLimiter {

    <T> T executeWithTimeout(Callable<T> operation, Duration timeout) throws Exception;

    <T> T executeWithTimeout(Supplier<T> operation, Duration timeout);

    void executeWithTimeout(Runnable operation, Duration timeout) throws Exception;

    boolean tryExecuteWithTimeout(Supplier<Boolean> operation, Duration timeout);

    Duration getDefaultTimeout();

    void setDefaultTimeout(Duration timeout);
}

// === ARCHIVO: src/main/java/domain/port/IdempotencyKeyRepository.java ===
package domain.port;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IdempotencyKeyRepository {

    boolean existsById(String idempotencyKey);

    Optional<IdempotencyKeyRecord> findById(String idempotencyKey);

    void save(IdempotencyKeyRecord record);

    void deleteById(String idempotencyKey);

    record IdempotencyKeyRecord(
        String idempotencyKey,
        String transactionId,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        public IdempotencyKeyRecord {
            if (idempotencyKey == null || idempotencyKey.isBlank()) {
                throw new IllegalArgumentException("Idempotency key cannot be null or blank");
            }
            if (transactionId == null || transactionId.isBlank()) {
                throw new IllegalArgumentException("Transaction ID cannot be null or blank");
            }
            if (status == null || status.isBlank()) {
                throw new IllegalArgumentException("Status cannot be null or blank");
            }
            if (createdAt == null) {
                throw new IllegalArgumentException("Created at cannot be null");
            }
            if (updatedAt == null) {
                throw new IllegalArgumentException("Updated at cannot be null");
            }
        }

        public boolean isCompleted() {
            return "SUCCESS".equalsIgnoreCase(status) || "REJECTED".equalsIgnoreCase(status);
        }

        public boolean isPending() {
            return "PENDING".equalsIgnoreCase(status);
        }

        public boolean canRetry() {
            return "FAILED".equalsIgnoreCase(status) || "PENDING".equalsIgnoreCase(status);
        }
    }
}

// === ARCHIVO: src/main/java/infrastructure/adapter/IdempotencyKeyRepositoryImpl.java ===
package infrastructure.adapter;

import domain.port.IdempotencyKeyRepository;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class IdempotencyKeyRepositoryImpl implements IdempotencyKeyRepository {

    private static final Logger log = LoggerFactory.getLogger(IdempotencyKeyRepositoryImpl.class);

    private final Map<String, IdempotencyKeyRecord> storage;
    private final Map<String, ReentrantLock> locks;

    public IdempotencyKeyRepositoryImpl() {
        this.storage = new ConcurrentHashMap<>();
        this.locks = new ConcurrentHashMap<>();
    }

    @Override
    public boolean existsById(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return false;
        }
        boolean exists = storage.containsKey(idempotencyKey);
        log.debug("Checking existence of idempotency key '{}': {}", idempotencyKey, exists);
        return exists;
    }

    @Override
    public Optional<IdempotencyKeyRecord> findById(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return Optional.empty();
        }
        IdempotencyKeyRecord record = storage.get(idempotencyKey);
        log.debug("Finding idempotency key '{}': {}", idempotencyKey, record != null ? "found" : "not found");
        return Optional.ofNullable(record);
    }

    @Override
    public void save(IdempotencyKeyRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Record cannot be null");
        }

        ReentrantLock lock = locks.computeIfAbsent(record.idempotencyKey(), k -> new ReentrantLock());
        lock.lock();
        try {
            LocalDateTime now = LocalDateTime.now();
            IdempotencyKeyRecord recordToSave = new IdempotencyKeyRecord(
                record.idempotencyKey(),
                record.transactionId(),
                record.status(),
                record.createdAt() != null ? record.createdAt() : now,
                now
            );
            storage.put(record.idempotencyKey(), recordToSave);
            log.info("Saved idempotency key '{}' for transaction '{}' with status '{}'",
                record.idempotencyKey(), record.transactionId(), record.status());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void deleteById(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return;
        }

        ReentrantLock lock = locks.computeIfAbsent(idempotencyKey, k -> new ReentrantLock());
        lock.lock();
        try {
            IdempotencyKeyRecord removed = storage.remove(idempotencyKey);
            if (removed != null) {
                log.info("Deleted idempotency key '{}' for transaction '{}'",
                    idempotencyKey, removed.transactionId());
            } else {
                log.debug("Attempted to delete non-existent idempotency key '{}'", idempotencyKey);
            }
        } finally {
            lock.unlock();
        }
    }

    public int getStorageSize() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
        locks.clear();
        log.info("Cleared all idempotency keys from storage");
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080

spring:
  application:
    name: payment-resilience-service
  profiles:
    active: default

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,circuitbreakers
  endpoint:
    health:
      show-details: always
  health:
    circuitbreakers:
      enabled: true
    retries:
      enabled: true

resilience4j:
  retry:
    instances:
      paymentRetry:
        maxAttempts: 3
        waitDuration: 1000
        retryExceptions:
          - java.io.IOException
          - java.util.concurrent.TimeoutException
          - org.springframework.web.client.ResourceAccessException
        ignoreExceptions:
          - domain.model.PaymentFailedException
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2.0
        maxRetryInterval: 10000
        retryPolicy:
          maxAttempts: 3
          waitDuration: 1000
        eventConsumerBufferSize: 10
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 30s
        failureRateThreshold: 50
        slowCallRateThreshold: 100
        slowCallDurationThreshold: 2s

  circuitbreaker:
    instances:
      paymentCircuitBreaker:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 30s
        failureRateThreshold: 50
        slowCallRateThreshold: 100
        slowCallDurationThreshold: 2s
        permittedNumberOfCallsInHalfOpenState: 3
        slidingWindowType: COUNT_BASED
        recordExceptions:
          - java.io.IOException
          - java.util.concurrent.TimeoutException
          - org.springframework.web.client.ResourceAccessException
          - domain.model.PaymentFailedException
        ignoreExceptions:
          - domain.model.CircuitBreakerOpenException

  timelimiter:
    instances:
      paymentTimeLimiter:
        timeoutDuration: 5s
        cancelRunningFuture: true
        eventConsumerBufferSize: 100

  bulkhead:
    instances:
      paymentBulkhead:
        maxConcurrentCalls: 10
        maxWaitDuration: 0ms
        bulkheadType: SEMAPHORE

logging:
  level:
    root: INFO
    com.example: DEBUG
    io.github.resilience4j: DEBUG
    org.springframework.retry: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"

payment:
  provider:
    base-url: "http://localhost:9090"
    connection-timeout: 5000
    read-timeout: 10000
    max-connections: 50
  retry:
    max-attempts: 3
    initial-interval: 1000
    multiplier: 2.0
    max-interval: 10000
  circuit-breaker:
    failure-threshold: 50
    success-threshold: 2
    wait-duration: 30000
    half-open-max-calls: 3
  timeout:
    default-timeout: 5000
    critical-timeout: 3000
  idempotency:
    key-expiry-hours: 24
    storage-type: in-memory

info:
  app:
    name: "Payment Resilience Service"
    description: "Microservicio de procesamiento de pagos con patrones de resiliencia"
    version: "1.0.0"
    author: "Pragma Engineering"
  features:
    - "Retry with exponential backoff"
    - "Circuit breaker pattern"
    - "Timeout handling"
    - "Idempotency key validation"
    - "Health monitoring via Actuator"
    - "Metrics collection"
  dependencies:
    - "Spring Boot 3.4"
    - "Resilience4j 2.4"
    - "Java 21"

// === ARCHIVO: src/main/java/resources/application.yml ===
spring:
  application:
    name: payment-resilience-service
  profiles:
    active: ${SPRING_PROFILES_ACTIVE:default}
  main:
    allow-bean-definition-overriding: false
    banner-mode: console

server:
  port: ${SERVER_PORT:8080}
  shutdown: graceful

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,circuitbreakers
      base-path: /actuator
  endpoint:
    health:
      show-details: always
      show-components: always
    info:
      enabled: true
    metrics:
      enabled: true
  health:
    circuitbreakers:
      enabled: true
    livenessstate:
      enabled: true
    readinessstate:
      enabled: true
  metrics:
    export:
      prometheus:
        enabled: true
    tags:
      application: ${spring.application.name}

resilience4j:
  retry:
    instances:
      paymentRetry:
        maxAttempts: 3
        waitDuration: 1000
        retryExceptions:
          - java.io.IOException
          - java.util.concurrent.TimeoutException
          - org.springframework.web.client.ResourceAccessException
        ignoreExceptions:
          - domain.model.PaymentFailedException
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2.0
        maxRetryInterval: 10000
        retryPolicy:
          maxAttempts: 3
          waitDuration: 1000
        eventConsumerBufferSize: 100
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 30s
        failureRateThreshold: 50
        slowCallRateThreshold: 100
        slowCallDurationThreshold: 2s

  circuitbreaker:
    instances:
      paymentCircuitBreaker:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 30s
        failureRateThreshold: 50
        slowCallRateThreshold: 100
        slowCallDurationThreshold: 2s
        eventConsumerBufferSize: 100
        recordExceptions:
          - java.io.IOException
          - java.util.concurrent.TimeoutException
          - org.springframework.web.client.ResourceAccessException
          - domain.model.PaymentFailedException
        ignoreExceptions:
          - domain.model.CircuitBreakerOpenException

  timelimiter:
    instances:
      paymentTimeLimiter:
        timeoutDuration: 5s
        cancelRunningFuture: true
        eventConsumerBufferSize: 100

  bulkhead:
    instances:
      paymentBulkhead:
        maxConcurrentCalls: 10
        maxWaitDuration: 0ms
        eventConsumerBufferSize: 100
        registerHealthIndicator: true

logging:
  level:
    root: INFO
    com.example: DEBUG
    io.github.resilience4j: DEBUG
    org.springframework.web: INFO
    org.springframework.retry: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/application.log
    max-size: 10MB
    max-history: 30

payment:
  provider:
    base-url: ${PAYMENT_PROVIDER_URL:http://localhost:9090}
    connect-timeout: 5000
    read-timeout: 10000
    max-connections: 50
  retry:
    max-attempts: 3
    initial-interval: 1000
    multiplier: 2.0
    max-interval: 10000
  circuit-breaker:
    failure-threshold: 50
    success-threshold: 3
    wait-duration: 30000
  timeout:
    default-timeout: 5000
    critical-timeout: 3000
  idempotency:
    key-prefix: "PAY"
    ttl-days: 7

info:
  app:
    name: Payment Resilience Service
    description: Microservice implementing resilience patterns for payment processing
    version: 1.0.0
  java:
    version: ${java.version}
  spring:
    version: ${spring.boot.version}
```
