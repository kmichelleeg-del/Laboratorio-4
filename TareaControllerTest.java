import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TareaControllerTest {
    private TareaController controlador;

    @BeforeEach
    void setUp() {
        AlmacenarTareas almacenamiento = new AlmacenamientoMemoria();
        controlador = new TareaController(almacenamiento);
    }

    @Test
    void testAgregarVariosElementosYRevisarIds() {
        Tarea tarea1 = controlador.agregarTarea("atender estudiantes", "Ximena");
        Tarea tarea2 = controlador.agregarTarea("atender estudiantes", "Luis Diego");
        Tarea tarea3 = controlador.agregarTarea("atender estudiantes", "William");

        assertEquals(0, tarea1.getId());
        assertEquals(1, tarea2.getId());
        assertEquals(2, tarea3.getId());
    }

    @Test
    void validarCambioDeEstado() {
        Tarea tarea = controlador.agregarTarea("atender estudiantes", "William");

        assertFalse(tarea.getCompletada());

        controlador.completarTarea(tarea.getId());

        assertTrue(tarea.getCompletada());
    }

    @Test
    void testCompletarTareaInexistenteLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.completarTarea(99);
        });
    }

    @Test
    void testCompletarTareaYaCompletadaLanzaExcepcion() {
        Tarea tarea = controlador.agregarTarea("atender estudiantes", "William");
        controlador.completarTarea(tarea.getId());

        assertThrows(IllegalStateException.class, () -> {
            controlador.completarTarea(tarea.getId());
        });
    }

    @Test
    void testEliminarTareaExistente() {
        Tarea tarea = controlador.agregarTarea("hacer tarea", "para clase");

        controlador.eliminarTarea(tarea.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            controlador.eliminarTarea(tarea.getId());
        });
    }

    @Test
    void testEliminarTareaInexistenteLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.eliminarTarea(99);
        });
    }
}
