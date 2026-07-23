# ♟️ Sistema de Reservas y Gestión de Partidas de Ajedrez

> Una plataforma full-stack para reservar, gestionar y simular partidas de ajedrez con seguimiento de estadísticas en tiempo real.

---

## Descripción del Proyecto

Este sistema permite a los usuarios gestionar y reservar partidas de ajedrez. Aunque la partida en sí se ejecuta mediante simulación por comandos (sin tablero gráfico interactivo en tiempo real), la aplicación gestiona todo el flujo de reservas, persistencia de datos NoSQL y análisis estadístico del rendimiento de los jugadores.

El proyecto está diseñado con una arquitectura cliente-servidor que admite tanto un cliente web interactivo como un cliente de escritorio nativo.

---

## Características Principales

* **Gestión de Reservas:** Creación, cancelación y consulta de reservas para partidas de ajedrez.
* **Simulador de Partidas:** Registro y ejecución de partidas mediante consola/comandos.
* **Estadísticas y Analítica:** Visualización de métricas de usuario (victorias, derrotas, historial de partidas, etc.).
* **Doble Interfaz:** Soporte para entorno Web (Angular) y de Escritorio (JavaFX).

---

## Tecnologías Utilizadas

| Capa | Tecnología | Descripción |
| :--- | :--- | :--- |
| **Backend** | Java 17+, Spring Boot | API RESTful para la lógica de negocio y gestión de datos |
| **Base de Datos** | MongoDB | Base de datos NoSQL para almacenamiento flexible de reservas y partidas |
| **Cliente Web** | Angular | Interfaz web para panel de control, reservas y estadísticas |
| **Cliente Escritorio** | JavaFX | Aplicación de escritorio nativa para gestión rápida |

---

## Arquitectura del Sistema

```text
[ Cliente Web (Angular) ]  ──┐
                             ├──> [ API REST (Spring Boot) ] ──> [ MongoDB ]
[ Cliente Desktop (JavaFX) ] ──┘

````
## Requisitos Previos

Asegúrate de tener instalado lo siguiente antes de ejecutar el proyecto:
* Java JDK 17 o superior.
* Node.js (v18+) y npm.
* Angular CLI (npm install -g @angular/cli).
* MongoDB (ejecutándose en local en el puerto 27017 o una instancia en la nube mediante MongoDB Atlas).

## Instalación y Configuración

