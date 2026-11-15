package lucyday;

/** Producto derivado para cuidado capilar; comparte los datos comunes de Producto. */
public class ProductoCapilar extends Producto {
    private final String tipoCabello;
    private final String beneficio;

    public ProductoCapilar(String codigo, String nombre, double costo, double precio, int existencia,
                           int stockMinimo, String tipoCabello, String beneficio) {
        super(codigo, nombre, costo, precio, existencia, stockMinimo); // Reutiliza el constructor de la clase base.
        this.tipoCabello = tipoCabello;
        this.beneficio = beneficio;
    }

    public String getTipoCabello() { return tipoCabello; }
    public String getBeneficio() { return beneficio; }

    /** Método exclusivo: muestra el beneficio y el tipo de cabello atendido. */
    public String mostrarBeneficio() {
        return beneficio + " para cabello " + tipoCabello + ".";
    }

    /** Sobrescribe toString para agregar los atributos propios del producto capilar. */
    @Override
    public String toString() {
        return super.toString() + " | Capilar: cabello " + tipoCabello + ", beneficio " + beneficio;
    }
}
