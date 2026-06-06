# TP Unit Testing - Patrones de Diseño

## Descripción

Este proyecto corresponde al Trabajo Práctico de Unit Testing sobre el sistema de transporte realizado en el TP anterior de Patrones de Diseño.

El objetivo principal fue refactorizar la lógica de alertas para separar responsabilidades y luego implementar tests unitarios usando:

* Tests simples sin dependencias.
* Fakes manuales.
* Mocks con Mockito.

## Refactorización realizada

Se extrajo la lógica de decisión de alertas desde `AlertObserver` hacia una nueva interfaz llamada `AlertService`.

La implementación concreta es `ThresholdAlertService`, que decide si debe generarse una alerta según:

* Costo máximo permitido.
* ETA máximo permitido.

De esta forma, `AlertObserver` ya no conoce directamente los umbrales, sino que delega esa responsabilidad en `AlertService`.

## Clases principales

* `AlertService`: interfaz que define los métodos `shouldAlertCost` y `shouldAlertETA`.
* `ThresholdAlertService`: implementación concreta de `AlertService`.
* `AlertObserver`: observer que recibe actualizaciones de transporte y loggea alertas.
* `ILogger`: interfaz para desacoplar el logger.
* `Logger`: implementación Singleton del logger.
* `FakeLogger`: logger falso usado para testing.
* `AlwaysAlertService`: fake que siempre genera alerta.
* `NeverAlertService`: fake que nunca genera alerta.

## Tests implementados

### Tests de `ThresholdAlertService`

Se testea la lógica de decisión de alertas sin usar fakes ni mocks.

Casos probados:

* Costo por debajo del umbral.
* Costo exactamente en el umbral.
* Costo por encima del umbral.
* ETA por debajo del umbral.
* ETA por encima del umbral.

### Tests de `AlertObserver` con Fake

Se testea `AlertObserver` usando implementaciones falsas de `AlertService`:

* `AlwaysAlertService`: verifica que se generen logs.
* `NeverAlertService`: verifica que no se generen logs.

También se utiliza `FakeLogger` para guardar los mensajes loggeados sin depender de la consola.

### Tests de `AlertObserver` con Mock

Se testea `AlertObserver` usando Mockito para verificar interacciones:

* Que se llame a `logWarning` cuando hay alerta de costo.
* Que se llame a `logError` cuando hay alerta de ETA.
* Que no se llame al logger cuando no hay alertas.

## Cómo correr los tests

Desde el IDE, abrir la carpeta del proyecto y ejecutar los archivos de test ubicados en la carpeta de tests.

Archivos principales de test:

* `ThresholdAlertServiceTest.java`
* `AlertObserverTest.java`
* `AlertObserverMockitoTest.java`

También se pueden correr todos los tests desde la opción de testing del IDE.

## Decisiones de diseño

**Comportamiento en el umbral exacto:** `shouldAlertCost` retorna `false` cuando el costo es igual al umbral 
(`cost == maxCost`). La condición implementada es `cost > maxCost` (estricto), por lo que el umbral no es inclusivo. 
Lo mismo aplica para `shouldAlertETA`.

## Tecnologías utilizadas

* Java
* JUnit 5
* Mockit
* Patrones de Diseño




