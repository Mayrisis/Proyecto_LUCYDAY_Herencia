package lucyday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Administra productos usando su interfaz común, sin depender de una subclase concreta. */
public class Inventario {
    private final List<ProductoInventariable> productos = new ArrayList<>();

    public boolean agregarProducto(ProductoInventariable producto) {
        if (producto == null || buscarPorCodigo(producto.getCodigo()) != null) return false;
        productos.add(producto);
        return true;
    }

    public ProductoInventariable buscarPorCodigo(String codigo) {
        for (ProductoInventariable producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) return producto;
        }
        return null;
    }

    /** Devuelve una vista de solo lectura para evitar cambios fuera de este inventario. */
    public List<ProductoInventariable> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    /** La misma llamada de interfaz genera detalles distintos según cada objeto concreto. */
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        for (ProductoInventariable producto : productos) {
            System.out.println(producto.generarDescripcion());
        }
    }

    public void mostrarStockBajo() {
        boolean encontrado = false;
        for (ProductoInventariable producto : productos) {
            if (producto.necesitaReabastecimiento()) {
                System.out.println("ALERTA: " + producto.getNombre()
                        + " (existencia: " + producto.getExistencia() + ")");
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No hay productos con stock bajo.");
    }
}
