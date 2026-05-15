# 🗺️ Mapeo de Requisitos Académicos a Arquitectura Profesional

Este documento explica cómo el proyecto satisface las Historias de Usuario (HU) solicitadas por el ITM utilizando una **Arquitectura Hexagonal** y un enfoque **Web (Spring Boot + JS)** en lugar de consola.

## HU1: Configuración inicial del proyecto
*   **Requisito**: Main con un único método, limpio, que llame métodos externos.
*   **Cumplimiento**: El `main` solo inicia el contexto de Spring Boot. Toda la lógica de "Menú" y navegación se traslada al **Frontend Web** y a los **Controladores REST**.
*   **Recursividad**: Se implementa en la capa de `infrastructure/util` para validación de datos antes de entrar al dominio.

## HU2, HU3, HU4: Modelado de Negocio (Clientes, Vehículos, Renting)
*   **Requisito**: POO, Herencia, Encapsulamiento, Polimorfismo.
*   **Cumplimiento**: Se implementan como clases de dominio puras en `domain/model`. La herencia de `Vehiculo` -> `Sedan`/`SUV` se mantiene estrictamente. Todos los atributos son privados con Getters/Setters.

## HU5: Menú Principal
*   **Requisito**: Un menú centralizado para gestionar todo.
*   **Cumplimiento**: El menú se transforma en una **Interfaz Web (Navbar/Sidebar)**. Cada opción del menú académico corresponde a un módulo del Frontend que consume un Endpoint específico de la API.

## HU6: Estructuras Dinámicas
*   **Requisito**: Listas (Vectores), Pilas y Colas objetuales.
*   **Cumplimiento**:
    *   **Listas**: Usadas en los servicios de aplicación para gestionar colecciones.
    *   **Colas (Queues)**: Implementada para la **HU de Fila de Espera** cuando no hay vehículos.
    *   **Pilas (Stacks)**: Implementada para el **Historial de Acciones (Deshacer)**.

## HU7: Informes y Consultas
*   **Requisito**: Listar datos, ingresos y sobrecarga de métodos.
*   **Cumplimiento**: Se crean endpoints de reporte en la API que devuelven cálculos complejos. La sobrecarga de métodos se usa en los servicios para imprimir/formatear diferentes tipos de objetos.

## HU8: Validaciones y Control de Flujo
*   **Requisito**: Validaciones recursivas, try-catch, control de errores.
*   **Cumplimiento**:
    *   **Validación Recursiva**: Se usa para sanitizar strings y verificar formatos de entrada.
    *   **Excepciones**: Uso de `@ControllerAdvice` para manejar errores de forma global y profesional.
