package lucyday;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Pruebas de contrato, inventario y despacho polimórfico de descripciones. */
class PolimorfismoTest {
    @Test
    void unaLlamadaDeInterfazProduceDetallesDeCadaSubclase() {
        List<ProductoInventariable> productos = List.of(
                new ProductoFacial("F1", "Sérum", 50, 100, 5, 1, "seca", "niacinamida"),
                new ProductoCapilar("C1", "Shampoo", 40, 80, 4, 1, "rizado", "nutrición"),
                new ProductoCorporal("B1", "Crema", 30, 60, 3, 1, "cuerpo", "hidratación")
        );
        List<String> descripciones = productos.stream()
                .map(ProductoInventariable::generarDescripcion).toList();
        assertTrue(descripciones.get(0).contains("FACIAL"));
        assertTrue(descripciones.get(0).contains("niacinamida"));
        assertTrue(descripciones.get(1).contains("CAPILAR"));
        assertTrue(descripciones.get(1).contains("rizado"));
        assertTrue(descripciones.get(2).contains("CORPORAL"));
        assertTrue(descripciones.get(2).contains("hidratación"));
    }

    @Test
    void operacionesComunesFuncionanATravésDeLaInterfaz() {
        ProductoInventariable producto = new ProductoFacial(
                "F2", "Tónico", 20, 50, 2, 1, "mixta", "aloe vera");
        assertTrue(producto.registrarEntrada(3));
        assertEquals(5, producto.getExistencia());
        assertTrue(producto.vender(2));
        assertEquals(3, producto.getExistencia());
        assertEquals(100.0, producto.calcularImporte(2));
    }

    @Test
    void rechazaVentasMayoresAlStockYCantidadesNoPositivas() {
        ProductoInventariable producto = new ProductoCapilar(
                "C2", "Acondicionador", 30, 70, 2, 1, "seco", "suavidad");
        assertFalse(producto.vender(3));
        assertFalse(producto.vender(0));
        assertFalse(producto.registrarEntrada(-1));
        assertEquals(2, producto.getExistencia());
    }

    @Test
    void inventarioAceptaSubclasesComoElementosComunesYEvitaCodigoDuplicado() {
        Inventario inventario = new Inventario();
        ProductoInventariable facial = new ProductoFacial(
                "DUP", "Gel limpiador", 25, 55, 5, 1, "mixta", "té verde");
        ProductoInventariable capilar = new ProductoCapilar(
                "DUP", "Mascarilla", 35, 75, 4, 1, "rizado", "brillo");
        assertTrue(inventario.agregarProducto(facial));
        assertFalse(inventario.agregarProducto(capilar));
        assertEquals("FACIAL", ((Producto) inventario.buscarPorCodigo("DUP")).getCategoria());
    }
}
