package lucyday;

/** Clase base: reúne los datos y comportamientos comunes de todo producto. */
public class Producto {
    private final String codigo;
    private final String nombre;
    private final double costo;
    private final double precio;
    private int existencia;
    private final int stockMinimo;

    public Producto(String codigo, String nombre, double costo, double precio, int existencia, int stockMinimo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.precio = precio;
        this.existencia = existencia;
        this.stockMinimo = stockMinimo;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getCosto() { return costo; }
    public double getPrecio() { return precio; }
    public int getExistencia() { return existencia; }
    public int getStockMinimo() { return stockMinimo; }

    /** Registra unidades recibidas; false indica una cantidad no válida. */
    public boolean registrarEntrada(int cantidad) {
        if (cantidad <= 0) return false;
        existencia += cantidad;
        return true;
    }

    /** Descuenta unidades solo si hay existencias suficientes. */
    public boolean vender(int cantidad) {
        if (cantidad <= 0 || cantidad > existencia) return false;
        existencia -= cantidad;
        return true;
    }

    public boolean necesitaReabastecimiento() { return existencia <= stockMinimo; }
    public double calcularImporte(int cantidad) { return precio * cantidad; }

    @Override
    public String toString() {
        return String.format("%-10s | %-34s | Precio: $%7.2f | Stock: %3d", codigo, nombre, precio, existencia);
    }
}
