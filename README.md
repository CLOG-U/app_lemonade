# Lemonade

Aplicación Android interactiva desarrollada por **Carlos Ortiz** como práctica de Kotlin y Jetpack Compose.

La aplicación representa el proceso de preparar una limonada mediante cuatro pasos:

1. Seleccionar un limón del árbol.
2. Exprimirlo entre 2 y 4 veces; la cantidad cambia aleatoriamente.
3. Beber la limonada.
4. Tocar el vaso vacío para empezar otra vez.

## Tecnologías

- Kotlin 2.1
- Jetpack Compose
- Material 3
- Gradle Kotlin DSL
- Android SDK mínimo 24
- Pruebas unitarias con JUnit 4

## Ejecutar el proyecto

1. Abrir el repositorio con Android Studio.
2. Esperar a que finalice la sincronización de Gradle.
3. Seleccionar un emulador o dispositivo con Android 7.0 o superior.
4. Ejecutar la configuración `app`.

También se puede compilar desde la terminal:

```bash
./gradlew testDebugUnitTest assembleDebug
```

## Características de implementación

- Interfaz centrada y adaptable creada completamente con Compose.
- Recursos de texto separados de la interfaz.
- Descripciones de contenido para lectores de pantalla.
- Ilustraciones vectoriales propias, compatibles con distintos tamaños de pantalla.
- Lógica de estados separada de la interfaz para facilitar sus pruebas.

El proyecto sigue los requisitos de la práctica **Lemonade** de Android Developers, implementados con una estructura y recursos propios.

