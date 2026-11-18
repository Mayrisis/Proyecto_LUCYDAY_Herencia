package lucyday;

import java.util.Scanner;

/** Punto de entrada; instancia subclases y demuestra llamadas polimórficas. */
public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Inventario inventario = new Inventario();

        ProductoFacial serum = new ProductoFacial("SER001", "Sérum ácido hialurónico",
                105, 239, 12, 3, "todo tipo", "ácido hialurónico");
        ProductoCapilar shampoo = new ProductoCapilar("CAP001", "Shampoo de romero",
                80, 159, 4, 5, "graso", "limpieza y frescura");
        ProductoCorporal crema = new ProductoCorporal("COR001", "Crema corporal de avena",
                65, 129, 9, 2, "cuerpo", "hidratación");
        inventario.agregarProducto(serum);
        inventario.agregarProducto(shampoo);
        inventario.agregarProducto(crema);

        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE INVENTARIO LUCYDAY =====");
            System.out.println("1. Mostrar productos");
            System.out.println("2. Buscar producto por código");
            System.out.println("3. Registrar entrada de productos");
            System.out.println("4. Registrar venta");
            System.out.println("5. Mostrar alertas de stock bajo");
            System.out.println("6. Demostrar polimorfismo");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            while (!teclado.hasNextInt()) {
                System.out.print("Ingresa un número válido: ");
                teclado.next();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> inventario.listarProductos();
                case 2 -> {
                    System.out.print("Código del producto: ");
                    ProductoInventariable p = inventario.buscarPorCodigo(teclado.nextLine());
                    System.out.println(p == null ? "No se encontró el producto." : p.generarDescripcion());
                }
                case 3 -> {
                    System.out.print("Código del producto: ");
                    ProductoInventariable p = inventario.buscarPorCodigo(teclado.nextLine());
                    if (p == null) {
                        System.out.println("No se encontró el producto.");
                    } else {
                        System.out.print("Cantidad que ingresa: ");
                        int cantidad = leerCantidad(teclado);
                        System.out.println(p.registrarEntrada(cantidad)
                                ? "Entrada registrada correctamente."
                                : "La cantidad debe ser mayor que cero.");
                    }
                }
                case 4 -> {
                    System.out.print("Código del producto: ");
                    ProductoInventariable p = inventario.buscarPorCodigo(teclado.nextLine());
                    if (p == null) {
                        System.out.println("No se encontró el producto.");
                    } else {
                        System.out.print("Cantidad a vender: ");
                        int cantidad = leerCantidad(teclado);
                        if (p.vender(cantidad)) {
                            System.out.printf("Venta registrada. Total: $%.2f%n", p.calcularImporte(cantidad));
                        } else {
                            System.out.println("Venta no realizada: revisa cantidad y existencias.");
                        }
                    }
                }
                case 5 -> inventario.mostrarStockBajo();
                case 6 -> demostrarPolimorfismo(inventario);
                case 0 -> System.out.println("Gracias por utilizar el sistema LUCYDAY.");
                default -> System.out.println("Opción no válida. Intenta nuevamente.");
            }
        } while (opcion != 0);
        teclado.close();
    }

    /**
     * Llama el mismo método mediante una referencia de interfaz.
     * Cada subclase aporta categoría y detalle distintos en tiempo de ejecución.
     */
    private static void demostrarPolimorfismo(Inventario inventario) {
        System.out.println("\n--- POLIMORFISMO: una llamada, varias descripciones ---");
        for (ProductoInventariable producto : inventario.getProductos()) {
            System.out.println(producto.generarDescripcion());
        }
    }

    /** Lee una cantidad entera y vuelve a solicitarla si la entrada no es numérica. */
    private static int leerCantidad(Scanner teclado) {
        while (!teclado.hasNextInt()) {
            System.out.print("Ingresa una cantidad entera: ");
            teclado.next();
        }
        int cantidad = teclado.nextInt();
        teclado.nextLine();
        return cantidad;
    }
}
