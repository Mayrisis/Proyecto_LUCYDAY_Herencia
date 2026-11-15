package lucyday;

import java.util.ArrayList;
import java.util.List;

/** Administra productos base y derivados mediante una colección polimórfica. */
public class Inventario {
    private final List<Producto> productos = new ArrayList<>();

    public boolean agregarProducto(Producto producto) {
        if (buscarPorCodigo(producto.getCodigo()) != null) return false;
        productos.add(producto);
        return true;
    }

    public Producto buscarPorCodigo(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) return producto;
        }
        return null;
    }

    public void listarProductos() {
        if (productos.isEmpty()) { System.out.println("No hay productos registrados."); return; }
        for (Producto producto : productos) System.out.println(producto); // Java usa el toString sobrescrito según el tipo real.
    }

    public void mostrarStockBajo() {
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.necesitaReabastecimiento()) {
                System.out.println("ALERTA: " + producto.getNombre() + " (existencia: " + producto.getExistencia() + ")");
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No hay productos con stock bajo.");
    }
}
