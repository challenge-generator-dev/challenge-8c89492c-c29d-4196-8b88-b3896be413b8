# Implementación de patrones de resiliencia en la integración con el proveedor de pagos

El sistema de procesamiento de pagos de una entidad financiera necesita comunicarse con un proveedor externo que ha demostrado ser inestable. El objetivo es diseñar y aplicar patrones de resiliencia para minimizar los incidentes causados por caídas del proveedor. Los patrones a implementar incluyen reintentos con backoff, circuit breaker, timeouts e idempotencia de las operaciones. El dominio es la banca, con actores como el Sistema de Pagos, el Proveedor Externo de Pagos y el Cliente Bancario.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Diseño de microservicios resilientes |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 15 horas |

## Stack

- **Lenguaje:** Java
- **Framework:** Spring Boot 3.5
- **Version:** Java 21

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Tener el proyecto base del reto funcionando en tu máquina: clonar el repositorio, instalar las herramientas y verificar que compila y arranca antes de empezar a practicar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Instala las herramientas para ejecutar el proyecto: JDK 17+ y Maven 3.9+.
- Clona el repositorio del reto (el enlace viene en la entrega del reto y en la sección "Repositorio del Proyecto" del PDF) y ábrelo en tu IDE.
- Instala las dependencias y compila ejecutando `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` en la raíz del repositorio.
- Confirma que el comando termina sin errores: el proyecto arranca y sus pruebas corren.
- Ubica los archivos de práctica: son los que contienen `TODO(reto)` (búscalos en el repositorio). Lee el README.md y el AGENTS.md antes de empezar.

**Entregable:** El proyecto compila/arranca sin errores con `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package`.

<details>
<summary>Pistas de conocimiento</summary>

- Los archivos con `TODO(reto)` son el ejercicio: el resto del repositorio ya está resuelto y no hace falta tocarlo.
- Si el comando de verificación falla, revisa la versión de las herramientas instaladas antes de modificar código.
- AGENTS.md describe el reto, el comando de verificación y las reglas del repositorio.

</details>

### Fase 1: Implementación de reintentos con backoff

**Objetivo:** Minimizar el impacto de fallos temporales en la comunicación con el proveedor externo.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Diseña un mecanismo de reintentos con backoff para manejar fallos temporales en la comunicación con el proveedor de pagos.
- Define los umbrales de tiempo y el factor de backoff apropiados para el dominio de la banca.

**Entregable:** Mecanismo de reintentos con backoff implementado y documentado.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el impacto en la latencia y la carga del proveedor al definir los tiempos de espera y el backoff.

</details>

### Fase 2: Implementación de circuit breaker

**Objetivo:** Proteger el sistema de fallos prolongados en el proveedor externo.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Diseña e implementa un circuit breaker para la comunicación con el proveedor de pagos.
- Define las condiciones bajo las cuales el circuit breaker debe abrirse y cerrarse.

**Entregable:** Circuit breaker implementado y documentado.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el equilibrio entre la disponibilidad del servicio y la protección contra fallos prolongados.

</details>

### Fase 3: Implementación de timeouts e idempotencia

**Objetivo:** Asegurar que las operaciones sean seguras y no causen efectos secundarios indeseados en caso de fallos.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Implementa timeouts para las operaciones de comunicación con el proveedor de pagos.
- Asegura la idempotencia de las operaciones para evitar efectos secundarios indeseados en caso de fallos.

**Entregable:** Timeouts e idempotencia implementados y documentados.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el impacto de los timeouts en la latencia y la consistencia de las operaciones.

</details>

## Dimensiones Evaluadas

- **queEs**: En fase 1, ¿qué es un mecanismo de reintentos con backoff y cómo funciona?
- **paraQueSirve**: En fase 2, ¿para qué sirve un circuit breaker y en qué situaciones se debe usar?
- **comoSeUsa**: En fase 3, ¿cómo se implementan timeouts e idempotencia en las operaciones de comunicación con el proveedor de pagos?
- **erroresComunes**: En fase 1, ¿cuáles son los errores comunes al implementar reintentos con backoff y cómo se pueden evitar?
- **queDecisionesImplica**: En fase 2, ¿qué decisiones implica la implementación de un circuit breaker y cómo se pueden justificar?

## Criterios de Evaluacion

- Implementación de reintentos con backoff.
- Implementación de circuit breaker.
- Implementación de timeouts.
- Aseguramiento de la idempotencia de las operaciones.

## Mapa de fases

La **Fase 0** es clonar el repositorio, instalar dependencias y correr las pruebas de aceptacion: todas fallan porque la logica de cada pieza esta pendiente.

| Fase | Pieza (skill) | Archivo:simbolo | Criterios | Prueba | Comando |
|------|---------------|-----------------|-----------|--------|---------|
| f1 — Implementación de reintentos con backoff | Implementación de reintentos con backoff exponencial usando Resilience4j | `src/main/java/infrastructure/adapter/PaymentServiceClient.java:PaymentServiceClient` | El sistema reintenta operaciones fallidas con backoff exponencial antes de fallar.; Los reintentos se detienen después de un número configurable de intentos.; Se registran los intentos fallidos para diagnóstico.; El sistema maneja correctamente fallos temporales y permanentes. | — | — |
| f2 — Implementación de circuit breaker | Implementación de circuit breaker usando Resilience4j | `src/main/java/infrastructure/adapter/PaymentServiceCircuitBreaker.java:PaymentServiceCircuitBreaker` | El circuito se abre tras un número configurable de fallos consecutivos.; El circuito pasa a estado semi-abierto tras un tiempo configurable.; Las llamadas se rechazan inmediatamente cuando el circuito está abierto.; El sistema se recupera automáticamente cuando el proveedor vuelve a estar disponible. | — | — |
| f3 — Implementación de timeouts e idempotencia | Implementación de timeouts para operaciones críticas | `src/main/java/infrastructure/adapter/PaymentTimeoutHandler.java:PaymentTimeoutHandler` | Las operaciones tienen un timeout configurable y fallan si se excede.; Las operaciones idempotentes no causan efectos secundarios al repetirse.; El sistema valida claves de idempotencia para operaciones críticas.; Los timeouts se registran para diagnóstico. | — | — |
| f3 — Implementación de timeouts e idempotencia | Validación de claves de idempotencia para operaciones críticas | `src/main/java/infrastructure/adapter/IdempotencyKeyValidator.java:IdempotencyKeyValidator` | Las operaciones tienen un timeout configurable y fallan si se excede.; Las operaciones idempotentes no causan efectos secundarios al repetirse.; El sistema valida claves de idempotencia para operaciones críticas.; Los timeouts se registran para diagnóstico. | — | — |

## Pruebas de aceptacion

Las pruebas de aceptacion (columna "Prueba" de la tabla) son de **solo lectura**: definen que significa "listo". No las edites, no las borres y no les agregues `skip`; lo que se resuelve es el codigo de cada pieza.

## Restricciones del pedido

| Restriccion | Donde se cumple | Estado |
|-------------|-----------------|--------|
| Lidera un equipo y quiere fortalecer patrones de resiliencia antes de una migración | chequeo estatico (code): exige `@Retry`, `@CircuitBreaker`, `@TimeLimiter`, `IdempotencyKey` | Restriccion no verificada en chequeo estatico (code): exige `@Retry`, `@CircuitBreaker`, `@TimeLimiter`, `IdempotencyKey` |

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
