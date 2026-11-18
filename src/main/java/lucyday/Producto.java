package lucyday;

/**
 * Clase abstracta con la información y reglas comunes del inventario.
 * No se instancia directamente: cada producto concreto define su categoría y detalles.
 */
public abstract class Producto implements ProductoInventariable {
    private final String codigo;
    private final String nombre;
    private final double costo;
    private final double precio;
    private int existencia;
    private final int stockMinimo;

    protected Producto(String codigo, String nombre, double costo, double precio,
                       int existencia, int stockMinimo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.precio = precio;
        this.existencia = existencia;
        this.stockMinimo = stockMinimo;
    }

    @Override public final String getCodigo() { return codigo; }
    @Override public final String getNombre() { return nombre; }
    public final double getCosto() { return costo; }
    public final double getPrecio() { return precio; }
    @Override public final int getExistencia() { return existencia; }
    public final int getStockMinimo() { return stockMinimo; }

    /** Registra unidades recibidas; rechaza cantidades iguales o menores que cero. */
    @Override
    public boolean registrarEntrada(int cantidad) {
        if (cantidad <= 0) return false;
        existencia += cantidad;
        return true;
    }

    /** Registra una venta solo cuando la cantidad cabe dentro de las existencias. */
    @Override
    public boolean vender(int cantidad) {
        if (cantidad <= 0 || cantidad > existencia) return false;
        existencia -= cantidad;
        return true;
    }

    @Override
    public boolean necesitaReabastecimiento() {
        return existencia <= stockMinimo;
    }

    @Override
    public double calcularImporte(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        return precio * cantidad;
    }

    /**
     * Compone una descripción común y agrega el detalle de la subclase.
     * La llamada a detalleEspecifico() se resuelve según el tipo real del objeto.
     */
    @Override
    public final String generarDescripcion() {
        return String.format("[%s] %-10s | %-32s | Precio: $%7.2f | Stock: %3d | %s",
                getCategoria(), codigo, nombre, precio, existencia, detalleEspecifico());
    }

    /** Cada subclase proporciona su nombre de categoría. */
    public abstract String getCategoria();

    /** Cada subclase proporciona atributos particulares para la descripción. */
    protected abstract String detalleEspecifico();

    @Override
    public final String toString() {
        return generarDescripcion();
    }
}
