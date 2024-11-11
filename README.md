# Trabajo Práctico N°2 - Magos y Mortífagos - Resumen Informe

## Integrantes
- Federico Ariel Martucci
- Franco Ruggieri
- Zois Andres Uziel Ruggiero Bellone
**Grupo:** Beta  
**Comisión:** 02-2900  
**Fecha:** 10/11/2024  

## Índice
1. [Desarrollo y Justificación](#desarrollo-y-justificación)
2. [Paquetes](#paquetes)
3. [Clase Batallón](#clase-batallón)
4. [El Combate](#el-combate)
5. [Conclusión](#conclusión)
6. [Bibliografía](#bibliografía)

## Desarrollo y Justificación
Este proyecto desarrolla un sistema de combate entre personajes magos y mortífagos en Java, aplicando patrones de diseño para una arquitectura modular y escalable.

## Paquetes

- **Hechizos:** Usa el patrón **Strategy** para manejar distintos tipos de hechizos. Cada clase de hechizo implementa `ejecutar()` para personalizar su efecto.
- **Personajes:** Contiene `Personaje` y sus subclases (`Mago`, `Mortífago`, etc.). Se usa el patrón **Factory** para crear personajes y hechizos.
- **Pociones (BONUS):** Paquete adicional que emplea **Strategy**. Cada poción tiene un efecto específico al ser utilizada.

## Clase Batallón
`Batallón` gestiona una colección de personajes, controlando su salud y ataques. Integra **Prolog** para definir reglas de uso de hechizos según el nivel de magia y otros factores.

## El Combate
La clase `Main` inicia el combate entre dos batallones, alternando ataques por rondas entre los equipos, con lógica para balancear el juego.

## Conclusión
El proyecto integró varios patrones de diseño, haciendo el código flexible y escalable. La colaboración entre paradigmas, usando Prolog para reglas complejas, enriqueció la experiencia de desarrollo.

## Bibliografía
- [Echeverría, G. R. - Interacción Java Prolog](http://www.cs.uns.edu.ar/~grs/Logica/InteracJavaProlog.pdf)
- [Refactoring Guru - Patrones de diseño](https://refactoring.guru/es/design-patterns/what-is-pattern)
- [JavaTutoriales - Patrón de diseño Strategy](https://www.javatutoriales.com/2022/01/patron-de-diseno-strategy.html)
