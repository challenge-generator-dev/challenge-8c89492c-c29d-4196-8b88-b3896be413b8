# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de patrones de resiliencia en la integración con el proveedor de pagos**.

| | |
|---|---|
| Tema | Diseño de microservicios resilientes |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 15 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web
- org.springframework.boot:spring-boot-starter-test
- io.github.resilience4j:resilience4j-micrometer 2.4.0
- io.github.resilience4j:resilience4j-reactor 2.4.0
- io.github.resilience4j:resilience4j-spring-boot3 2.4.0
- org.projectlombok:lombok
- org.springframework.boot:spring-boot-starter-actuator
- org.springframework.boot:spring-boot-starter-aop
- org.springframework.boot:spring-boot-starter-validation

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Implementación de reintentos con backoff**: Mecanismo de reintentos con backoff implementado y documentado.
- **Fase 2 — Implementación de circuit breaker**: Circuit breaker implementado y documentado.
- **Fase 3 — Implementación de timeouts e idempotencia**: Timeouts e idempotencia implementados y documentados.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/infrastructure/adapter/PaymentServiceClient.java` — La pieza p1 (Implementación de reintentos con backoff exponencial usando Resilience4j, fase f1) vive aqui: este archivo es su unico dueno (blueprint v2).
- [ ] `src/main/java/infrastructure/adapter/PaymentServiceCircuitBreaker.java` — La pieza p2 (Implementación de circuit breaker usando Resilience4j, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).
- [ ] `src/main/java/infrastructure/adapter/PaymentTimeoutHandler.java` — La pieza p3 (Implementación de timeouts para operaciones críticas, fase f3) vive aqui: este archivo es su unico dueno (blueprint v2).
- [ ] `src/main/java/infrastructure/adapter/IdempotencyKeyValidator.java` — La pieza p4 (Validación de claves de idempotencia para operaciones críticas, fase f3) vive aqui: este archivo es su unico dueno (blueprint v2).

## Mapa de fases

La **Fase 0** es clonar el repositorio, instalar dependencias y correr las pruebas de aceptacion: todas fallan porque la logica de cada pieza esta pendiente.

| Fase | Pieza (skill) | Archivo:simbolo | Criterios | Prueba | Comando |
|------|---------------|-----------------|-----------|--------|---------|
| f1 — Implementación de reintentos con backoff | Implementación de reintentos con backoff exponencial usando Resilience4j | `src/main/java/infrastructure/adapter/PaymentServiceClient.java:PaymentServiceClient` | El sistema reintenta operaciones fallidas con backoff exponencial antes de fallar.; Los reintentos se detienen después de un número configurable de intentos.; Se registran los intentos fallidos para diagnóstico.; El sistema maneja correctamente fallos temporales y permanentes. | — | — |
| f2 — Implementación de circuit breaker | Implementación de circuit breaker usando Resilience4j | `src/main/java/infrastructure/adapter/PaymentServiceCircuitBreaker.java:PaymentServiceCircuitBreaker` | El circuito se abre tras un número configurable de fallos consecutivos.; El circuito pasa a estado semi-abierto tras un tiempo configurable.; Las llamadas se rechazan inmediatamente cuando el circuito está abierto.; El sistema se recupera automáticamente cuando el proveedor vuelve a estar disponible. | — | — |
| f3 — Implementación de timeouts e idempotencia | Implementación de timeouts para operaciones críticas | `src/main/java/infrastructure/adapter/PaymentTimeoutHandler.java:PaymentTimeoutHandler` | Las operaciones tienen un timeout configurable y fallan si se excede.; Las operaciones idempotentes no causan efectos secundarios al repetirse.; El sistema valida claves de idempotencia para operaciones críticas.; Los timeouts se registran para diagnóstico. | — | — |
| f3 — Implementación de timeouts e idempotencia | Validación de claves de idempotencia para operaciones críticas | `src/main/java/infrastructure/adapter/IdempotencyKeyValidator.java:IdempotencyKeyValidator` | Las operaciones tienen un timeout configurable y fallan si se excede.; Las operaciones idempotentes no causan efectos secundarios al repetirse.; El sistema valida claves de idempotencia para operaciones críticas.; Los timeouts se registran para diagnóstico. | — | — |

## Pruebas de aceptacion y ubicacion de la logica

Las pruebas de aceptacion (columna "Prueba" de la tabla) son de **solo lectura**: definen que significa "listo". No las edites, no las borres y no les agregues `skip`; lo que se resuelve es el codigo de cada pieza.

La logica de cada pieza va en su archivo; no la muevas a otro.

## Restricciones del pedido

| Restriccion | Donde se cumple | Estado |
|-------------|-----------------|--------|
| Lidera un equipo y quiere fortalecer patrones de resiliencia antes de una migración | chequeo estatico (code): exige `@Retry`, `@CircuitBreaker`, `@TimeLimiter`, `IdempotencyKey` | Restriccion no verificada en chequeo estatico (code): exige `@Retry`, `@CircuitBreaker`, `@TimeLimiter`, `IdempotencyKey` |

## Lo que falta y tenes que completar

No se detectaron huecos: estan los archivos declarados, el boilerplate del stack y ninguna referencia quedo colgando. Igual corre el comando de verificacion — que los archivos existan no garantiza que compilen.

### Presentes (19)

- `src/main/java/Application.java`
- `pom.xml`
- `src/main/java/domain/model/PaymentRequest.java`
- `src/main/java/domain/model/PaymentResponse.java`
- `src/main/java/domain/model/PaymentFailedException.java`
- `src/main/java/domain/model/CircuitBreakerOpenException.java`
- `src/main/java/domain/model/PaymentTimeoutException.java`
- `src/main/java/domain/model/DuplicateOperationException.java`
- `src/main/java/infrastructure/adapter/PaymentServiceClient.java`
- `src/main/java/infrastructure/adapter/PaymentServiceCircuitBreaker.java`
- `src/main/java/infrastructure/adapter/PaymentTimeoutHandler.java`
- `src/main/java/infrastructure/adapter/IdempotencyKeyValidator.java`
- `src/main/java/domain/port/Retry.java`
- `src/main/java/domain/port/CircuitBreaker.java`
- `src/main/java/domain/port/TimeLimiter.java`
- `src/main/java/domain/port/IdempotencyKeyRepository.java`
- `src/main/java/infrastructure/adapter/IdempotencyKeyRepositoryImpl.java`
- `src/main/resources/application.yml`
- `src/main/java/resources/application.yml`

### Capas del patron declarado

Son las capas que la propuesta arquitectonica plantea, marcadas segun existan hoy en el repo. Orientan donde poner el codigo nuevo; no son una condicion para compilar.

- `src/main/java/com/pragma/payments` (ausente)
- `src/main/java/com/pragma/payments/application` (ausente)
- `src/main/java/com/pragma/payments/domain` (ausente)
- `src/main/java/com/pragma/payments/domain/model` (ausente)
- `src/main/java/com/pragma/payments/domain/port` (ausente)
- `src/main/java/com/pragma/payments/infrastructure/adapter` (ausente)
- `src/main/java/com/pragma/payments/infrastructure/config` (ausente)
- `src/main/java/com/pragma/payments/infrastructure/exception` (ausente)
- `src/test/java/com/pragma/payments` (ausente)

## Verificacion

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollo backend, Tecnología Java, Senior L2
- Brecha que el reto ataca: Diseña la comunicación entre servicios tolerante a fallos: reintentos con backoff, circuit breaker, timeouts e idempotencia de las operaciones.
- Mision: Reducir los incidentes por caídas del proveedor de pagos

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
