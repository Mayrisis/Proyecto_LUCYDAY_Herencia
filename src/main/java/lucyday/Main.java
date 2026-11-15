package lucyday;

import java.util.Scanner;

/** Menú principal; crea objetos derivados y permite probar la herencia. */
public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Inventario inventario = new Inventario();

        // Las variables se guardan como Producto, aunque los objetos son de subclases.
        ProductoFacial serum = new ProductoFacial("SER001", "Sérum ácido hialurónico", 105, 239, 12, 3,
                "todo tipo", "ácido hialurónico");
        ProductoCapilar shampoo = new ProductoCapilar("CAP001", "Shampoo de romero", 80, 159, 4, 5,
                "graso", "limpieza y frescura");
        inventario.agregarProducto(serum);
        inventario.agregarProducto(shampoo);
        inventario.agregarProducto(new Producto("LAB001", "Bálsamo labial cereza", 26, 49, 8, 2));

        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE INVENTARIO LUCYDAY =====");
            System.out.println("1. Mostrar productos\n2. Buscar producto por código\n3. Registrar entrada de productos");
            System.out.println("4. Registrar venta\n5. Mostrar alertas de stock bajo\n6. Demostrar herencia\n7. Mostrar categorías\n0. Salir");
            System.out.print("Selecciona una opción: ");
            while (!teclado.hasNextInt()) { System.out.print("Ingresa un número válido: "); teclado.next(); }
            opcion = teclado.nextInt(); teclado.nextLine();
            switch (opcion) {
                case 1 -> inventario.listarProductos();
                case 2 -> {
                    System.out.print("Código del producto: ");
                    Producto encontrado = inventario.buscarPorCodigo(teclado.nextLine());
                    System.out.println(encontrado == null ? "No se encontró el producto." : encontrado);
                }
                case 3 -> {
                    System.out.print("Código del producto: "); Producto producto = inventario.buscarPorCodigo(teclado.nextLine());
                    if (producto == null) System.out.println("No se encontró el producto.");
                    else { System.out.print("Cantidad que ingresa: "); int cantidad = leerCantidad(teclado);
                        System.out.println(producto.registrarEntrada(cantidad) ? "Entrada registrada." : "La cantidad debe ser mayor que cero."); }
                }
                case 4 -> {
                    System.out.print("Código del producto: "); Producto producto = inventario.buscarPorCodigo(teclado.nextLine());
                    if (producto == null) System.out.println("No se encontró el producto.");
                    else { System.out.print("Cantidad a vender: "); int cantidad = leerCantidad(teclado);
                        if (producto.vender(cantidad)) System.out.printf("Venta registrada. Total: $%.2f%n", producto.calcularImporte(cantidad));
                        else System.out.println("Venta no realizada: revisa cantidad y existencias."); }
                }
                case 5 -> inventario.mostrarStockBajo();
                case 6 -> {
                    System.out.println("Método propio facial: " + serum.recomendarProducto());
                    System.out.println("Método propio capilar: " + shampoo.mostrarBeneficio());
                    System.out.println("toString sobrescrito facial: " + serum);
                }
                case 7 -> {
                    for (Producto producto : new Producto[]{serum, shampoo}) {
                        if (producto instanceof ProductoFacial facial) System.out.println("Facial: " + facial.recomendarProducto());
                        else if (producto instanceof ProductoCapilar capilar) System.out.println("Capilar: " + capilar.mostrarBeneficio());
                    }
                }
                case 0 -> System.out.println("Gracias por utilizar el sistema LUCYDAY.");
                default -> System.out.println("Opción no válida. Intenta nuevamente.");
            }
        } while (opcion != 0);
        teclado.close();
    }

    /** Lee una cantidad numérica para las operaciones del inventario. */
    private static int leerCantidad(Scanner teclado) {
        while (!teclado.hasNextInt()) { System.out.print("Ingresa una cantidad entera: "); teclado.next(); }
        int cantidad = teclado.nextInt(); teclado.nextLine(); return cantidad;
    }
}
