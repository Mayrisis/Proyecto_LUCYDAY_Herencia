package lucyday;

/** Producto facial especializado; hereda las operaciones comunes de Producto. */
public class ProductoFacial extends Producto {
    private final String tipoPiel;
    private final String ingredienteActivo;

    public ProductoFacial(String codigo, String nombre, double costo, double precio,
                          int existencia, int stockMinimo, String tipoPiel,
                          String ingredienteActivo) {
        super(codigo, nombre, costo, precio, existencia, stockMinimo);
        this.tipoPiel = tipoPiel;
        this.ingredienteActivo = ingredienteActivo;
    }

    public String getTipoPiel() { return tipoPiel; }
    public String getIngredienteActivo() { return ingredienteActivo; }

    /** Método exclusivo para explicar para qué tipo de piel se recomienda. */
    public String recomendarProducto() {
        return "Recomendado para piel " + tipoPiel + "; contiene " + ingredienteActivo + ".";
    }

    @Override public String getCategoria() { return "FACIAL"; }

    /** Sobrescribe el detalle abstracto con los atributos propios del cuidado facial. */
    @Override
    protected String detalleEspecifico() {
        return "Piel: " + tipoPiel + ", activo: " + ingredienteActivo;
    }
}
