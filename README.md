# Proyecto LUCYDAY con polimorfismo

Sistema de inventario en Java y Maven que aplica una interfaz, una clase abstracta y tres subclases de productos cosméticos.

## Estructura de clases

- `ProductoInventariable` define el contrato de operaciones compartidas por los productos.
- `Producto` es abstracta; guarda los datos comunes y aplica las reglas generales de inventario.
- `ProductoFacial`, `ProductoCapilar` y `ProductoCorporal` heredan `Producto` e implementan sus detalles particulares.
- `Inventario` administra una lista de objetos del tipo de interfaz `ProductoInventariable`.
- `Main` ejecuta el menú y demuestra el polimorfismo en la opción 6.

El programa llama `generarDescripcion()` mediante referencias de tipo `ProductoInventariable`. La implementación común usa los métodos abstractos `getCategoria()` y `detalleEspecifico()`, que cada subclase sobrescribe. Así la misma llamada produce detalles distintos según el tipo real del objeto.

## Abrir y ejecutar

Abre en Apache NetBeans la carpeta que contiene `pom.xml`. La clase principal es `lucyday.Main`. El proyecto requiere JDK 17 o posterior.

Desde una terminal en la carpeta del proyecto, ejecuta:

```text
mvn clean test
mvn exec:java
```

La opción 6 muestra el polimorfismo. La opción 1 lista los productos y las opciones 3 y 4 permiten probar entradas y ventas.
