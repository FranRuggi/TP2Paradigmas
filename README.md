# Trabajo Práctico N°2 - Magos y Mortífagos - Resumen Informe

## Integrantes
- Federico Ariel Martucci
- Franco Ruggieri
- Zois Andres Uziel
- Ruggiero Bellone

**Grupo:** Beta  
**Comisión:** 02-2900  
**Fecha:** 10/11/2024

## Índice
1. [Desarrollo y Justificación](#desarrollo-y-justificación)
2. [Paquetes](#paquetes)
   - Hechizos
   - Main
   - Personajes
   - Pociones
3. [Clase Batallón](#clase-batallón)
4. [El Combate](#el-combate)
5. [Conclusión](#conclusión)
6. [Bibliografía](#bibliografía)

## Desarrollo y Justificación
En este proyecto, se desarrollaron clases y paquetes que representan un sistema de combate entre personajes magos y mortífagos, aplicando patrones de diseño en Java para lograr una arquitectura modular y escalable.

## Paquetes

### Hechizos
Incluye todos los hechizos implementados como clases individuales. Usamos el patrón **Strategy** para manejar los diferentes hechizos, donde cada clase de hechizo implementa su propia versión del método `ejecutar()`. Esto permite una fácil extensión y modificación de los hechizos.

### Personajes
Contiene la clase abstracta `Personaje`, que es heredada por `Mago` y `Mortífago`, y a su vez es base para clases específicas como `Profesor` y `Estudiante` para los magos y `Comandante` y `Seguidor` para los mortífagos. Se utiliza el patrón **Factory** para la creación de personajes y hechizos.

### Pociones (BONUS)
Se incluye un paquete adicional de pociones implementando nuevamente el patrón **Strategy**. Cada poción tiene su propia clase que define su efecto específico al ser utilizada en el combate.

## Clase Batallón
La clase `Batallón` representa una colección de personajes y proporciona métodos para gestionar el estado de salud y el ataque de sus miembros. A través de la integración con **Prolog**, definimos reglas de uso de hechizos en función del nivel de magia y otros factores.

## El Combate
La clase `Main` inicia el combate entre dos batallones, uno de magos y otro de mortífagos. Se alternan ataques entre los equipos y se aplica lógica de rondas para mantener el equilibrio en el combate.

## Conclusión
Este trabajo integró múltiples patrones de diseño, haciendo el código más flexible y ampliable. La integración de Prolog fue clave para manejar reglas complejas de combate, y la colaboración entre diferentes paradigmas de programación nos ayudó a profundizar en la modularidad y eficiencia del código.

## Bibliografía
- [Echeverría, G. R. - Interacción Java Prolog](http://www.cs.uns.edu.ar/~grs/Logica/InteracJavaProlog.pdf)
- [Refactoring Guru - Patrones de diseño](https://refactoring.guru/es/design-patterns/what-is-pattern)
- [JavaTutoriales - Patrón de diseño Strategy](https://www.javatutoriales.com/2022/01/patron-de-diseno-strategy.html)
