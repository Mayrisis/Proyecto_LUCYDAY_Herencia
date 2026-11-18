package lucyday;

/** Producto corporal que amplía la familia LUCYDAY con una tercera categoría. */
public class ProductoCorporal extends Producto {
    private final String zonaAplicacion;
    private final String beneficio;

    public ProductoCorporal(String codigo, String nombre, double costo, double precio,
                            int existencia, int stockMinimo, String zonaAplicacion,
                            String beneficio) {
        super(codigo, nombre, costo, precio, existencia, stockMinimo);
        this.zonaAplicacion = zonaAplicacion;
        this.beneficio = beneficio;
    }

    public String getZonaAplicacion() { return zonaAplicacion; }
    public String getBeneficio() { return beneficio; }

    /** Método exclusivo para explicar el uso del producto corporal. */
    public String explicarUso() {
        return beneficio + " en " + zonaAplicacion + ".";
    }

    @Override public String getCategoria() { return "CORPORAL"; }

    /** Sobrescribe el detalle abstracto con los atributos propios de esta categoría. */
    @Override
    protected String detalleEspecifico() {
        return "Zona: " + zonaAplicacion + ", beneficio: " + beneficio;
    }
}
