package udistrital.avanzada.veterinaria.controlador;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import udistrital.avanzada.veterinaria.modelo.Clasificacion;
import udistrital.avanzada.veterinaria.modelo.Mascota;
import udistrital.avanzada.veterinaria.modelo.TipoAlimento;
import udistrital.avanzada.veterinaria.modelo.ConexionBD;
import udistrital.avanzada.veterinaria.modelo.MascotaDAO;
import udistrital.avanzada.veterinaria.modelo.MascotaDAOImpl;
import udistrital.avanzada.veterinaria.vista.VentanaPrincipal;

/**
 * Clase de pruebas unitarias para la clase Controlador.
 * Utiliza JUnit 5 para probar las funcionalidades clave del controlador.
 * 
 * @author Sistema Veterinaria
 * @version 1.0
 */
@DisplayName("Pruebas del Controlador")
public class ControladorTest {
    
    private static final String APODO_TEST = "TestMascota";
    private static final String APODO_TEST_2 = "TestMascota2";
    
    private Controlador controlador;
    private VentanaPrincipal vista;
    private MascotaDAO mascotaDAO;
    private Mascota mascotaTest;
    
    /**
     * Configuración inicial que se ejecuta una sola vez antes de todas las pruebas
     */
    @BeforeAll
    static void setUpAll() {
        System.out.println("Iniciando pruebas del Controlador...");
        
        // Crear directorio de datos si no existe
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        
        // Limpiar archivos de prueba anteriores
        limpiarArchivosPrueba();
    }
    
    /**
     * Configuración que se ejecuta antes de cada prueba individual
     */
    @BeforeEach
    void setUp() {
        // Crear vista mock
        vista = new VentanaPrincipal();
        
        // Crear DAO
        mascotaDAO = new MascotaDAOImpl();
        
        // Crear controlador
        controlador = new Controlador(vista);
        
        // Crear mascota de prueba
        mascotaTest = new Mascota(
            "Mascota de Prueba",
            APODO_TEST,
            Clasificacion.MAMIFERO,
            "Testidae",
            "Testus",
            "T. testus",
            TipoAlimento.OMNIVORO
        );
        
        // Limpiar base de datos antes de cada prueba
        limpiarBaseDatos();
    }
    
    /**
     * Limpieza que se ejecuta después de cada prueba individual
     */
    @AfterEach
    void tearDown() {
        // Limpiar base de datos después de cada prueba
        limpiarBaseDatos();
        
        // Cerrar vista
        if (vista != null) {
            vista.dispose();
        }
    }
    
    /**
     * Limpieza final que se ejecuta una sola vez después de todas las pruebas
     */
    @AfterAll
    static void tearDownAll() {
        System.out.println("Finalizando pruebas del Controlador...");
        
        // Limpiar archivos de prueba
        limpiarArchivosPrueba();
        
        // Cerrar conexión a la base de datos
        ConexionBD.getInstance().cerrarConexion();
    }
    
    /**
     * Prueba la adición exitosa de una mascota
     */
    @Test
    @DisplayName("Debería agregar una mascota exitosamente")
    void testAgregarMascotaExitosa() throws Exception {
        // Arrange
        configurarVistaConDatosMascota(mascotaTest);
        
        // Act
        simularClickBoton("Adicionar");
        
        // Assert
        Mascota mascotaEncontrada = mascotaDAO.consultarPorApodo(APODO_TEST);
        assertNotNull(mascotaEncontrada, "La mascota debería haberse agregado a la base de datos");
        assertEquals(mascotaTest.getApodo(), mascotaEncontrada.getApodo());
        assertEquals(mascotaTest.getNombreComun(), mascotaEncontrada.getNombreComun());
    }
    
    /**
     * Prueba que no se permita agregar una mascota duplicada
     */
    @Test
    @DisplayName("Debería rechazar mascota duplicada")
    void testAgregarMascotaDuplicada() throws Exception {
        // Arrange
        mascotaDAO.agregarMascota(mascotaTest);
        configurarVistaConDatosMascota(mascotaTest);
        
        // Act
        simularClickBoton("Adicionar");
        
        // Assert
        String mensaje = vista.lblMensaje.getText();
        assertTrue(mensaje.contains("Ya existe una mascota"), 
            "Debería mostrar mensaje de mascota duplicada");
    }
    
    /**
     * Prueba la consulta de mascota por apodo
     */
    @Test
    @DisplayName("Debería consultar mascota por apodo")
    void testConsultarMascotaPorApodo() throws Exception {
        // Arrange
        mascotaDAO.agregarMascota(mascotaTest);
        vista.txtConsultaApodo.setText(APODO_TEST);
        
        // Act
        simularClickBoton("Consultar");
        
        // Assert
        List<Mascota> mascotas = mascotaDAO.consultarPorApodo(APODO_TEST);
        assertNotNull(mascotas, "Debería encontrar la mascota consultada");
        assertEquals(APODO_TEST, mascotas.getApodo());
    }
    
    /**
     * Prueba la consulta de mascotas por clasificación
     */
    @Test
    @DisplayName("Debería consultar mascotas por clasificación")
    void testConsultarMascotasPorClasificacion() throws Exception {
        // Arrange
        mascotaDAO.agregarMascota(mascotaTest);
        vista.cmbConsultaClasificacion.setSelectedItem(Clasificacion.MAMIFERO);
        
        // Act
        simularClickBoton("Consultar");
        
        // Assert
        List<Mascota> mascotas = mascotaDAO.consultarPorClasificacion(Clasificacion.MAMIFERO);
        assertFalse(mascotas.isEmpty(), "Debería encontrar mascotas de la clasificación MAMIFERO");
        assertTrue(mascotas.stream().allMatch(m -> m.getClasificacion() == Clasificacion.MAMIFERO),
            "Todas las mascotas encontradas deberían ser MAMIFERO");
    }
    
    /**
     * Prueba la modificación de una mascota existente
     */
    @Test
    @DisplayName("Debería modificar una mascota existente")
    void testModificarMascota() throws Exception {
        // Arrange
        mascotaDAO.agregarMascota(mascotaTest);
        
        // Seleccionar mascota en la tabla
        simularSeleccionEnTabla(mascotaTest);
        
        // Modificar datos en la vista
        vista.txtNombreComun.setText("Mascota Modificada");
        vista.cmbClasificacion.setSelectedItem(Clasificacion.REPTIL);
        
        // Act
        simularClickBoton("Modificar");
        
        // Assert
        Mascota mascotaModificada = mascotaDAO.consultarPorApodo(APODO_TEST);
        assertEquals("Mascota Modificada", mascotaModificada.getNombreComun());
        assertEquals(Clasificacion.REPTIL, mascotaModificada.getClasificacion());
    }
    
    /**
     * Prueba la eliminación de una mascota
     */
    @Test
    @DisplayName("Debería eliminar una mascota")
    void testEliminarMascota() throws Exception {
        // Arrange
        mascotaDAO.agregarMascota(mascotaTest);
        simularSeleccionEnTabla(mascotaTest);
        
        // Act
        simularClickBoton("Eliminar");
        
        // Assert
        Mascota mascotaEliminada = mascotaDAO.consultarPorApodo(APODO_TEST);
        assertNull(mascotaEliminada, "La mascota debería haber sido eliminada");
    }
    
    /**
     * Prueba la validación de campos obligatorios
     */
    @Test
    @DisplayName("Debería validar campos obligatorios")
    void testValidarCamposObligatorios() {
        // Arrange - dejar campos vacíos
        vista.limpiarCampos();
        
        // Act
        simularClickBoton("Adicionar");
        
        // Assert
        String mensaje = vista.lblMensaje.getText();
        assertTrue(mensaje.contains("complete todos los campos"), 
            "Debería mostrar mensaje de campos obligatorios");
    }
    
    /**
     * Prueba la funcionalidad de limpiar campos
     */
    @Test
    @DisplayName("Debería limpiar todos los campos")
    void testLimpiarCampos() {
        // Arrange
        configurarVistaConDatosMascota(mascotaTest);
        
        // Act
        simularClickBoton("Limpiar");
        
        // Assert
        assertTrue(vista.getNombreComun().isEmpty(), "Nombre común debería estar vacío");
        assertTrue(vista.getApodo().isEmpty(), "Apodo debería estar vacío");
        assertTrue(vista.getFamilia().isEmpty(), "Familia debería estar vacía");
    }
    
    /**
     * Prueba la serialización de mascotas
     */
    @Test
    @DisplayName("Debería serializar mascotas a archivo")
    void testSerializarMascotas() throws Exception {
        // Arrange
        mascotaDAO.agregarMascota(mascotaTest);
        
        // Act
        simularClickBoton("Serializar a IDPYBA");
        
        // Assert
        File archivoSerializado = new File("data/idpyba_data.ser");
        assertTrue(archivoSerializado.exists(), "Debería haberse creado el archivo de serialización");
        
        String mensaje = vista.lblMensaje.getText();
        assertTrue(mensaje.contains("serializadas exitosamente"), 
            "Debería mostrar mensaje de serialización exitosa");
    }
    
    /**
     * Configura la vista con los datos de una mascota
     * 
     * @param mascota La mascota con los datos a configurar
     */
    private void configurarVistaConDatosMascota(Mascota mascota) {
        vista.txtNombreComun.setText(mascota.getNombreComun());
        vista.txtApodo.setText(mascota.getApodo());
        vista.cmbClasificacion.setSelectedItem(mascota.getClasificacion());
        vista.txtFamilia.setText(mascota.getFamilia());
        vista.txtGenero.setText(mascota.getGenero());
        vista.txtEspecie.setText(mascota.getEspecie());
        vista.cmbTipoAlimento.setSelectedItem(mascota.getTipoAlimentoPrincipal());
    }
    
    /**
     * Simula el click en un botón
     * 
     * @param comando El comando del botón
     */
    private void simularClickBoton(String comando) {
        ActionEvent evento = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, comando);
        controlador.actionPerformed(evento);
    }
    
    /**
     * Simula la selección de una mascota en la tabla
     * 
     * @param mascota La mascota a seleccionar
     */
    private void simularSeleccionEnTabla(Mascota mascota) {
        // Agregar la mascota a la tabla
        vista.actualizarTabla(List.of(mascota));
        
        // Seleccionar la primera fila
        vista.tablaMascotas.setRowSelectionInterval(0, 0);
    }
    
    /**
     * Limpia la base de datos eliminando todas las mascotas de prueba
     */
    private void limpiarBaseDatos() {
        try {
            // Eliminar mascotas de prueba
            try {
                mascotaDAO.eliminarMascota(APODO_TEST);
            } catch (Exception e) {
                // Ignorar si no existe
            }
            
            try {
                mascotaDAO.eliminarMascota(APODO_TEST_2);
            } catch (Exception e) {
                // Ignorar si no existe
            }
        } catch (Exception e) {
            System.err.println("Error al limpiar base de datos: " + e.getMessage());
        }
    }
    
    /**
     * Limpia los archivos de prueba
     */
    private static void limpiarArchivosPrueba() {
        // Eliminar archivos de serialización
        File archivoSerializado = new File("data/idpyba_data.ser");
        if (archivoSerializado.exists()) {
            archivoSerializado.delete();
        }
        
        // Eliminar archivo de estado final
        File archivoEstado = new File("data/estado_final.raf");
        if (archivoEstado.exists()) {
            archivoEstado.delete();
        }
    }
}
