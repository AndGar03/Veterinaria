

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import udistrital.avanzada.veterinaria.control.logica.Gestor;
import udistrital.avanzada.veterinaria.modelo.modelo.MascotaVO;

/**
 * Clase de pruebas unitarias para el sistema de gestión de mascotas exóticas.
 * Incluye pruebas para el Gestor y las operaciones CRUD.
 * 
 * @author Estudiantes - Universidad Distrital Francisco José de Caldas
 * @version 1.0
 * @since 2024
 */
@DisplayName("Pruebas Unitarias - Sistema de Gestión de Mascotas Exóticas")
public class PruebasMascotasExoticas {

    private static Gestor gestor;
    private static ArrayList<MascotaVO> mascotasPrueba;
    private MascotaVO mascotaTest;

    /**
     * Configuración inicial antes de todas las pruebas.
     * Se ejecuta una sola vez al inicio de la suite de pruebas.
     */
    @BeforeAll
    static void configuracionInicial() {
        System.out.println("=== INICIANDO SUITE DE PRUEBAS ===");
        gestor = new Gestor();
        mascotasPrueba = new ArrayList<>();
        
        // Crear datos de prueba
        crearDatosPrueba();
        System.out.println("Configuración inicial completada");
    }

    /**
     * Limpieza final después de todas las pruebas.
     * Se ejecuta una sola vez al final de la suite de pruebas.
     */
    @AfterAll
    static void limpiezaFinal() {
        System.out.println("=== FINALIZANDO SUITE DE PRUEBAS ===");
        // Limpiar datos de prueba si es necesario
        System.out.println("Limpieza final completada");
    }

    /**
     * Configuración antes de cada prueba individual.
     * Se ejecuta antes de cada método de prueba.
     */
    @BeforeEach
    void configuracionPrueba() {
        mascotaTest = new MascotaVO();
        mascotaTest.setIdMascota("TEST001");
        mascotaTest.setApodo("TestPet");
        mascotaTest.setClasificacion("Mamífero");
        mascotaTest.setFamilia("Felidae");
        mascotaTest.setGenero("Panthera");
        mascotaTest.setEspecie("leo");
        mascotaTest.setTipoAlimento("Carnívoro");
        mascotaTest.setEdad(3);
        mascotaTest.setPeso(150.0);
        mascotaTest.setObservaciones("Mascota de prueba");
    }

    /**
     * Limpieza después de cada prueba individual.
     * Se ejecuta después de cada método de prueba.
     */
    @AfterEach
    void limpiezaPrueba() {
        // Limpiar datos específicos de la prueba si es necesario
        mascotaTest = null;
    }

    /**
     * Crea datos de prueba para las pruebas unitarias.
     */
    private static void crearDatosPrueba() {
        // Mascota 1 - León
        MascotaVO mascota1 = new MascotaVO();
        mascota1.setIdMascota("PRUEBA001");
        mascota1.setApodo("Simba");
        mascota1.setClasificacion("Mamífero");
        mascota1.setFamilia("Felidae");
        mascota1.setGenero("Panthera");
        mascota1.setEspecie("leo");
        mascota1.setTipoAlimento("Carnívoro");
        mascota1.setEdad(5);
        mascota1.setPeso(180.5);
        mascota1.setObservaciones("León africano macho");
        mascotasPrueba.add(mascota1);

        // Mascota 2 - Iguana
        MascotaVO mascota2 = new MascotaVO();
        mascota2.setIdMascota("PRUEBA002");
        mascota2.setApodo("Spike");
        mascota2.setClasificacion("Reptil");
        mascota2.setFamilia("Iguanidae");
        mascota2.setGenero("Iguana");
        mascota2.setEspecie("iguana");
        mascota2.setTipoAlimento("Herbívoro");
        mascota2.setEdad(3);
        mascota2.setPeso(2.8);
        mascota2.setObservaciones("Iguana verde");
        mascotasPrueba.add(mascota2);

        // Mascota 3 - Guacamayo
        MascotaVO mascota3 = new MascotaVO();
        mascota3.setIdMascota("PRUEBA003");
        mascota3.setApodo("Ruby");
        mascota3.setClasificacion("Ave");
        mascota3.setFamilia("Psittacidae");
        mascota3.setGenero("Ara");
        mascota3.setEspecie("macao");
        mascota3.setTipoAlimento("Omnívoro");
        mascota3.setEdad(8);
        mascota3.setPeso(1.2);
        mascota3.setObservaciones("Guacamayo rojo");
        mascotasPrueba.add(mascota3);
    }

    // ========== PRUEBAS DE REGISTRO ==========

    @Test
    @DisplayName("Registrar mascota nueva - Caso exitoso")
    void testRegistrarMascotaNueva() {
        // Arrange
        String idNuevo = "NUEVA001";
        mascotaTest.setIdMascota(idNuevo);

        // Act
        boolean resultado = gestor.registrarMascota(mascotaTest);

        // Assert
        assertTrue(resultado, "La mascota debería registrarse exitosamente");
        assertTrue(gestor.existeMascota(idNuevo), "La mascota debería existir en el sistema");
    }

    @Test
    @DisplayName("Registrar mascota duplicada - Caso de error")
    void testRegistrarMascotaDuplicada() {
        // Arrange
        String idExistente = "EXISTENTE001";
        mascotaTest.setIdMascota(idExistente);
        
        // Primero registrar la mascota
        gestor.registrarMascota(mascotaTest);

        // Act - Intentar registrar la misma mascota nuevamente
        boolean resultado = gestor.registrarMascota(mascotaTest);

        // Assert
        assertFalse(resultado, "No debería permitir registrar una mascota duplicada");
    }

    // ========== PRUEBAS DE CONSULTA ==========

    @Test
    @DisplayName("Consultar mascota por ID - Caso exitoso")
    void testConsultarMascotaPorId() {
        // Arrange
        String idMascota = "CONSULTA001";
        mascotaTest.setIdMascota(idMascota);
        gestor.registrarMascota(mascotaTest);

        // Act
        MascotaVO mascotaEncontrada = gestor.consultarMascotaPorId(idMascota);

        // Assert
        assertNotNull(mascotaEncontrada, "La mascota debería encontrarse");
        assertEquals(idMascota, mascotaEncontrada.getIdMascota(), "El ID debería coincidir");
        assertEquals("TestPet", mascotaEncontrada.getApodo(), "El apodo debería coincidir");
    }

    @Test
    @DisplayName("Consultar mascota inexistente - Caso de error")
    void testConsultarMascotaInexistente() {
        // Arrange
        String idInexistente = "INEXISTENTE001";

        // Act
        MascotaVO mascotaEncontrada = gestor.consultarMascotaPorId(idInexistente);

        // Assert
        assertNull(mascotaEncontrada, "No debería encontrar una mascota inexistente");
    }

    @Test
    @DisplayName("Buscar mascotas por apodo")
    void testBuscarMascotasPorApodo() {
        // Arrange
        String apodo = "Simba";
        mascotaTest.setIdMascota("BUSCAR001");
        mascotaTest.setApodo(apodo);
        gestor.registrarMascota(mascotaTest);

        // Act
        ArrayList<MascotaVO> mascotasEncontradas = gestor.buscarMascotasPorApodo(apodo);

        // Assert
        assertNotNull(mascotasEncontradas, "La lista no debería ser null");
        assertTrue(mascotasEncontradas.size() > 0, "Debería encontrar al menos una mascota");
        assertEquals(apodo, mascotasEncontradas.get(0).getApodo(), "El apodo debería coincidir");
    }

    @Test
    @DisplayName("Buscar mascotas por clasificación")
    void testBuscarMascotasPorClasificacion() {
        // Arrange
        String clasificacion = "Mamífero";
        mascotaTest.setIdMascota("CLASIF001");
        mascotaTest.setClasificacion(clasificacion);
        gestor.registrarMascota(mascotaTest);

        // Act
        ArrayList<MascotaVO> mascotasEncontradas = gestor.buscarMascotasPorClasificacion(clasificacion);

        // Assert
        assertNotNull(mascotasEncontradas, "La lista no debería ser null");
        assertTrue(mascotasEncontradas.size() > 0, "Debería encontrar al menos una mascota");
        assertEquals(clasificacion, mascotasEncontradas.get(0).getClasificacion(), "La clasificación debería coincidir");
    }

    @Test
    @DisplayName("Buscar mascotas por familia")
    void testBuscarMascotasPorFamilia() {
        // Arrange
        String familia = "Felidae";
        mascotaTest.setIdMascota("FAMILIA001");
        mascotaTest.setFamilia(familia);
        gestor.registrarMascota(mascotaTest);

        // Act
        ArrayList<MascotaVO> mascotasEncontradas = gestor.buscarMascotasPorFamilia(familia);

        // Assert
        assertNotNull(mascotasEncontradas, "La lista no debería ser null");
        assertTrue(mascotasEncontradas.size() > 0, "Debería encontrar al menos una mascota");
        assertEquals(familia, mascotasEncontradas.get(0).getFamilia(), "La familia debería coincidir");
    }

    @Test
    @DisplayName("Buscar mascotas por tipo de alimento")
    void testBuscarMascotasPorTipoAlimento() {
        // Arrange
        String tipoAlimento = "Carnívoro";
        mascotaTest.setIdMascota("ALIMENTO001");
        mascotaTest.setTipoAlimento(tipoAlimento);
        gestor.registrarMascota(mascotaTest);

        // Act
        ArrayList<MascotaVO> mascotasEncontradas = gestor.buscarMascotasPorTipoAlimento(tipoAlimento);

        // Assert
        assertNotNull(mascotasEncontradas, "La lista no debería ser null");
        assertTrue(mascotasEncontradas.size() > 0, "Debería encontrar al menos una mascota");
        assertEquals(tipoAlimento, mascotasEncontradas.get(0).getTipoAlimento(), "El tipo de alimento debería coincidir");
    }

    // ========== PRUEBAS DE MODIFICACIÓN ==========

    @Test
    @DisplayName("Modificar mascota existente - Caso exitoso")
    void testModificarMascotaExistente() {
        // Arrange
        String idMascota = "MODIFICAR001";
        mascotaTest.setIdMascota(idMascota);
        gestor.registrarMascota(mascotaTest);

        // Modificar datos
        mascotaTest.setApodo("TestPetModificado");
        mascotaTest.setEdad(4);
        mascotaTest.setPeso(160.0);

        // Act
        boolean resultado = gestor.modificarMascota(mascotaTest);

        // Assert
        assertTrue(resultado, "La modificación debería ser exitosa");
        MascotaVO mascotaModificada = gestor.consultarMascotaPorId(idMascota);
        assertEquals("TestPetModificado", mascotaModificada.getApodo(), "El apodo debería estar modificado");
        assertEquals(4, mascotaModificada.getEdad(), "La edad debería estar modificada");
        assertEquals(160.0, mascotaModificada.getPeso(), "El peso debería estar modificado");
    }

    @Test
    @DisplayName("Modificar mascota inexistente - Caso de error")
    void testModificarMascotaInexistente() {
        // Arrange
        String idInexistente = "INEXISTENTE002";
        mascotaTest.setIdMascota(idInexistente);

        // Act
        boolean resultado = gestor.modificarMascota(mascotaTest);

        // Assert
        assertFalse(resultado, "No debería poder modificar una mascota inexistente");
    }

    // ========== PRUEBAS DE ELIMINACIÓN ==========

    @Test
    @DisplayName("Eliminar mascota existente - Caso exitoso")
    void testEliminarMascotaExistente() {
        // Arrange
        String idMascota = "ELIMINAR001";
        mascotaTest.setIdMascota(idMascota);
        gestor.registrarMascota(mascotaTest);

        // Act
        boolean resultado = gestor.eliminarMascota(idMascota);

        // Assert
        assertTrue(resultado, "La eliminación debería ser exitosa");
        assertFalse(gestor.existeMascota(idMascota), "La mascota no debería existir después de eliminar");
    }

    @Test
    @DisplayName("Eliminar mascota inexistente - Caso de error")
    void testEliminarMascotaInexistente() {
        // Arrange
        String idInexistente = "INEXISTENTE003";

        // Act
        boolean resultado = gestor.eliminarMascota(idInexistente);

        // Assert
        assertFalse(resultado, "No debería poder eliminar una mascota inexistente");
    }

    // ========== PRUEBAS DE SERIALIZACIÓN ==========

    @Test
    @DisplayName("Serializar mascotas - Caso exitoso")
    void testSerializarMascotas() {
        // Arrange
        String nombreArchivo = "data/test_serializacion.dat";

        // Act
        boolean resultado = gestor.serializarMascotas(nombreArchivo);

        // Assert
        assertTrue(resultado, "La serialización debería ser exitosa");
    }

    // ========== PRUEBAS DE ARCHIVO DE ACCESO ALEATORIO ==========

    @Test
    @DisplayName("Crear archivo de acceso aleatorio - Caso exitoso")
    void testCrearArchivoAccesoAleatorio() {
        // Arrange
        String nombreArchivo = "data/test_acceso_aleatorio.dat";

        // Act
        boolean resultado = gestor.crearArchivoAccesoAleatorio(nombreArchivo);

        // Assert
        assertTrue(resultado, "La creación del archivo debería ser exitosa");
    }

    // ========== PRUEBAS DE VALIDACIÓN ==========

    @Test
    @DisplayName("Verificar existencia de mascota")
    void testVerificarExistenciaMascota() {
        // Arrange
        String idMascota = "EXISTENCIA001";
        mascotaTest.setIdMascota(idMascota);
        gestor.registrarMascota(mascotaTest);

        // Act & Assert
        assertTrue(gestor.existeMascota(idMascota), "La mascota debería existir");
        assertFalse(gestor.existeMascota("NOEXISTE001"), "La mascota no debería existir");
    }

    @Test
    @DisplayName("Obtener todas las mascotas")
    void testObtenerTodasLasMascotas() {
        // Act
        ArrayList<MascotaVO> todasLasMascotas = gestor.obtenerTodasLasMascotas();

        // Assert
        assertNotNull(todasLasMascotas, "La lista no debería ser null");
        assertTrue(todasLasMascotas.size() >= 0, "La lista debería tener al menos 0 elementos");
    }

    // ========== PRUEBAS DE INTEGRACIÓN ==========

    @Test
    @DisplayName("Flujo completo CRUD - Integración")
    void testFlujoCompletoCRUD() {
        // Arrange
        String idMascota = "INTEGRACION001";
        mascotaTest.setIdMascota(idMascota);

        // Act & Assert - Crear
        assertTrue(gestor.registrarMascota(mascotaTest), "Debería crear la mascota");
        assertTrue(gestor.existeMascota(idMascota), "La mascota debería existir");

        // Act & Assert - Leer
        MascotaVO mascotaLeida = gestor.consultarMascotaPorId(idMascota);
        assertNotNull(mascotaLeida, "Debería poder leer la mascota");
        assertEquals(idMascota, mascotaLeida.getIdMascota(), "El ID debería coincidir");

        // Act & Assert - Actualizar
        mascotaTest.setApodo("ApodoModificado");
        assertTrue(gestor.modificarMascota(mascotaTest), "Debería poder modificar la mascota");
        MascotaVO mascotaModificada = gestor.consultarMascotaPorId(idMascota);
        assertEquals("ApodoModificado", mascotaModificada.getApodo(), "El apodo debería estar modificado");

        // Act & Assert - Eliminar
        assertTrue(gestor.eliminarMascota(idMascota), "Debería poder eliminar la mascota");
        assertFalse(gestor.existeMascota(idMascota), "La mascota no debería existir después de eliminar");
    }
}
