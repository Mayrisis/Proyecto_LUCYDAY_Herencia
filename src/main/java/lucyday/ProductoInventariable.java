package lucyday;

/**
 * Contrato común de los artículos que el inventario puede administrar.
 * Las clases concretas deben permitir consultar, describir y actualizar existencias.
 */
public interface ProductoInventariable {
    String getCodigo();
    String getNombre();
    int getExistencia();
    boolean registrarEntrada(int cantidad);
    boolean vender(int cantidad);
    boolean necesitaReabastecimiento();
    double calcularImporte(int cantidad);
    String generarDescripcion();
}
