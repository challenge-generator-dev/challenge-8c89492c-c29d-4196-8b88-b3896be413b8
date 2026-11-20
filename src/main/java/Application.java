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