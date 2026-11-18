package lucyday;

/** Producto capilar especializado; hereda las operaciones comunes de Producto. */
public class ProductoCapilar extends Producto {
    private final String tipoCabello;
    private final String beneficio;

    public ProductoCapilar(String codigo, String nombre, double costo, double precio,
                           int existencia, int stockMinimo, String tipoCabello,
                           String beneficio) {
        super(codigo, nombre, costo, precio, existencia, stockMinimo);
        this.tipoCabello = tipoCabello;
        this.beneficio = beneficio;
    }

    public String getTipoCabello() { return tipoCabello; }
    public String getBeneficio() { return beneficio; }

    /** Método exclusivo que explica el beneficio para el tipo de cabello. */
    public String mostrarBeneficio() {
        return beneficio + " para cabello " + tipoCabello + ".";
    }

    @Override public String getCategoria() { return "CAPILAR"; }

    /** Sobrescribe el detalle abstracto con los atributos propios del cuidado capilar. */
    @Override
    protected String detalleEspecifico() {
        return "Cabello: " + tipoCabello + ", beneficio: " + beneficio;
    }
}
