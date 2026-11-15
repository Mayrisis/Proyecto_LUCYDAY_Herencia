package lucyday;

/** Producto derivado para cuidado facial; hereda operaciones comunes de Producto. */
public class ProductoFacial extends Producto {
    private final String tipoPiel;
    private final String ingredienteActivo;

    public ProductoFacial(String codigo, String nombre, double costo, double precio, int existencia,
                          int stockMinimo, String tipoPiel, String ingredienteActivo) {
        super(codigo, nombre, costo, precio, existencia, stockMinimo); // Inicializa los atributos heredados.
        this.tipoPiel = tipoPiel;
        this.ingredienteActivo = ingredienteActivo;
    }

    public String getTipoPiel() { return tipoPiel; }
    public String getIngredienteActivo() { return ingredienteActivo; }

    /** Método exclusivo: describe para qué tipo de piel se recomienda. */
    public String recomendarProducto() {
        return "Recomendado para piel " + tipoPiel + "; contiene " + ingredienteActivo + ".";
    }

    /** Sobrescribe la presentación general e incluye datos propios de esta subclase. */
    @Override
    public String toString() {
        return super.toString() + " | Facial: piel " + tipoPiel + ", activo " + ingredienteActivo;
    }
}
