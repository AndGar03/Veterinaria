package udistrital.avanzada.veterinaria.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.List;
import udistrital.avanzada.veterinaria.modelo.Clasificacion;
import udistrital.avanzada.veterinaria.modelo.Mascota;
import udistrital.avanzada.veterinaria.modelo.MascotaDAO;
import udistrital.avanzada.veterinaria.modelo.MascotaDAOImpl;
import udistrital.avanzada.veterinaria.modelo.ServicioMascota;
import udistrital.avanzada.veterinaria.modelo.TipoAlimento;
import udistrital.avanzada.veterinaria.vista.VentanaCompletarDatos;
import udistrital.avanzada.veterinaria.vista.VentanaPrincipal;

/**
 * Controlador principal de la aplicación de gestión de mascotas exóticas.
 * Implementa el patrón MVC como intermediario entre la Vista y el Modelo.
 * Maneja todos los eventos de la interfaz de usuario y coordina las operaciones
 * de negocio utilizando el DAO.
 * 
 * @author Sistema Veterinaria
 * @version 1.0
 */
public class Controlador implements ActionListener {
    
    private VentanaPrincipal vista;
    private MascotaDAO mascotaDAO;
    private ServicioMascota servicioMascota;
    
    /**
     * Constructor que inicializa el controlador con la vista y el DAO
     * 
     * @param vista La ventana principal de la aplicación
     */
    public Controlador(VentanaPrincipal vista) {
        this.vista = vista;
        this.mascotaDAO = new MascotaDAOImpl();
        this.servicioMascota = new ServicioMascota(mascotaDAO);
        
        // Registrar este controlador como listener de todos los botones
        vista.registrarActionListener(this);
        
        // Cargar todas las mascotas al iniciar
        cargarTodasLasMascotas();
    }
    
    /**
     * Maneja todos los eventos de los botones de la interfaz
     * 
     * @param e El evento de acción
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        
        try {
            switch (comando) {
                case "Adicionar":
                    adicionarMascota();
                    break;
                case "Consultar":
                    consultarMascotas();
                    break;
                case "Modificar":
                    modificarMascota();
                    break;
                case "Eliminar":
                    eliminarMascota();
                    break;
                case "Limpiar":
                    limpiarCampos();
                    break;
                case "Serializar a IDPYBA":
                    serializarMascotas();
                    break;
                case "Salir":
                    salirAplicacion();
                    break;
                default:
                    vista.mostrarMensaje("Acción no reconocida: " + comando);
            }
        } catch (Exception ex) {
            vista.mostrarMensaje("Error: " + ex.getMessage());
        }
    }
    
    /**
     * Adiciona una nueva mascota a la base de datos
     */
    private void adicionarMascota() throws Exception {
        // Validar que todos los campos estén llenos
        if (!validarCamposObligatorios()) {
            vista.mostrarMensaje("Por favor, complete todos los campos obligatorios");
            return;
        }
        
        // Crear objeto Mascota con los datos de la vista
        Mascota mascota = crearMascotaDesdeVista();
        
        // Verificar si la mascota ya existe
        if (mascotaDAO.existeMascota(mascota)) {
            vista.mostrarMensaje("Ya existe una mascota con el apodo: " + mascota.getApodo());
            return;
        }
        
        // Agregar la mascota a la base de datos
        mascotaDAO.agregarMascota(mascota);
        vista.mostrarMensaje("Mascota agregada exitosamente");
        
        // Actualizar la tabla y limpiar campos
        cargarTodasLasMascotas();
        vista.limpiarCampos();
    }
    
    /**
     * Consulta mascotas según los criterios especificados
     */
    private void consultarMascotas() throws Exception {
        List<Mascota> mascotas = null;
        
        // Determinar el tipo de consulta basado en los campos llenos
        if (!vista.getConsultaApodo().isEmpty()) {
            Mascota mascota = mascotaDAO.consultarPorApodo(vista.getConsultaApodo());
            if (mascota != null) {
                mascotas = List.of(mascota);
            } else {
                mascotas = List.of(); // Lista vacía
            }
        } else if (vista.getConsultaClasificacion() != null && 
                   vista.getConsultaClasificacion() != Clasificacion.values()[0]) {
            mascotas = mascotaDAO.consultarPorClasificacion(vista.getConsultaClasificacion());
        } else if (!vista.getConsultaFamilia().isEmpty()) {
            mascotas = mascotaDAO.consultarPorFamilia(vista.getConsultaFamilia());
        } else if (vista.getConsultaTipoAlimento() != null && 
                   vista.getConsultaTipoAlimento() != TipoAlimento.values()[0]) {
            mascotas = mascotaDAO.consultarPorTipoAlimento(vista.getConsultaTipoAlimento());
        } else {
            // Si no hay criterios específicos, mostrar todas
            mascotas = mascotaDAO.obtenerTodasLasMascotas();
        }
        
        // Actualizar la tabla con los resultados
        vista.actualizarTabla(mascotas);
        vista.mostrarMensaje("Consulta realizada. Se encontraron " + mascotas.size() + " mascotas");
    }
    
    /**
     * Modifica una mascota existente
     */
    private void modificarMascota() throws Exception {
        Mascota mascotaSeleccionada = vista.getMascotaSeleccionada();
        
        if (mascotaSeleccionada == null) {
            vista.mostrarMensaje("Por favor, seleccione una mascota de la tabla para modificar");
            return;
        }
        
        // Validar que los campos obligatorios estén llenos
        if (!validarCamposObligatorios()) {
            vista.mostrarMensaje("Por favor, complete todos los campos obligatorios");
            return;
        }
        
        // Crear objeto Mascota con los datos actualizados de la vista
        Mascota mascotaActualizada = crearMascotaDesdeVista();
        
        // Verificar que el apodo no haya cambiado (no se puede modificar)
        if (!mascotaActualizada.getApodo().equals(mascotaSeleccionada.getApodo())) {
            vista.mostrarMensaje("No se puede modificar el apodo de una mascota existente");
            return;
        }
        
        // Actualizar la mascota en la base de datos
        mascotaDAO.modificarMascota(mascotaActualizada);
        vista.mostrarMensaje("Mascota modificada exitosamente");
        
        // Actualizar la tabla
        cargarTodasLasMascotas();
    }
    
    /**
     * Elimina una mascota de la base de datos
     */
    private void eliminarMascota() throws Exception {
        Mascota mascotaSeleccionada = vista.getMascotaSeleccionada();
        
        if (mascotaSeleccionada == null) {
            vista.mostrarMensaje("Por favor, seleccione una mascota de la tabla para eliminar");
            return;
        }
        
        // Eliminar la mascota de la base de datos
        mascotaDAO.eliminarMascota(mascotaSeleccionada.getApodo());
        vista.mostrarMensaje("Mascota eliminada exitosamente");
        
        // Actualizar la tabla y limpiar campos
        cargarTodasLasMascotas();
        vista.limpiarCampos();
    }
    
    /**
     * Limpia todos los campos de la interfaz
     */
    private void limpiarCampos() {
        vista.limpiarCampos();
        cargarTodasLasMascotas();
    }
    
    /**
     * Serializa todas las mascotas a un archivo
     */
    private void serializarMascotas() throws Exception {
        List<Mascota> mascotas = mascotaDAO.obtenerTodasLasMascotas();
        
        if (mascotas.isEmpty()) {
            vista.mostrarMensaje("No hay mascotas para serializar");
            return;
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/idpyba_data.ser"))) {
            oos.writeObject(mascotas);
            vista.mostrarMensaje("Mascotas serializadas exitosamente en data/idpyba_data.ser");
        } catch (IOException e) {
            throw new Exception("Error al serializar mascotas: " + e.getMessage());
        }
    }
    
    /**
     * Guarda el estado final y cierra la aplicación
     */
    private void salirAplicacion() throws Exception {
        List<Mascota> mascotas = mascotaDAO.obtenerTodasLasMascotas();
        
        try (RandomAccessFile raf = new RandomAccessFile("data/estado_final.raf", "rw")) {
            // Escribir el número de mascotas
            raf.writeInt(mascotas.size());
            
            // Escribir cada mascota
            for (Mascota mascota : mascotas) {
                escribirMascotaEnRAF(raf, mascota);
            }
            
            vista.mostrarMensaje("Estado guardado en data/estado_final.raf");
        } catch (IOException e) {
            throw new Exception("Error al guardar estado final: " + e.getMessage());
        }
        
        // Cerrar la aplicación
        System.exit(0);
    }
    
    /**
     * Carga todas las mascotas en la tabla
     */
    private void cargarTodasLasMascotas() {
        try {
            List<Mascota> mascotas = mascotaDAO.obtenerTodasLasMascotas();
            vista.actualizarTabla(mascotas);
        } catch (Exception e) {
            vista.mostrarMensaje("Error al cargar mascotas: " + e.getMessage());
        }
    }
    
    /**
     * Valida que todos los campos obligatorios estén llenos
     * 
     * @return true si todos los campos están llenos, false en caso contrario
     */
    private boolean validarCamposObligatorios() {
        return !vista.getNombreComun().isEmpty() &&
               !vista.getApodo().isEmpty() &&
               !vista.getFamilia().isEmpty() &&
               !vista.getGenero().isEmpty() &&
               !vista.getEspecie().isEmpty();
    }
    
    /**
     * Crea un objeto Mascota con los datos de la vista
     * 
     * @return Un objeto Mascota con los datos de la vista
     */
    private Mascota crearMascotaDesdeVista() {
        Mascota mascota = new Mascota();
        mascota.setNombreComun(vista.getNombreComun());
        mascota.setApodo(vista.getApodo());
        mascota.setClasificacion(vista.getClasificacion());
        mascota.setFamilia(vista.getFamilia());
        mascota.setGenero(vista.getGenero());
        mascota.setEspecie(vista.getEspecie());
        mascota.setTipoAlimentoPrincipal(vista.getTipoAlimento());
        return mascota;
    }
    
    /**
     * Escribe una mascota en un RandomAccessFile
     * 
     * @param raf El RandomAccessFile donde escribir
     * @param mascota La mascota a escribir
     * @throws IOException Si ocurre un error de escritura
     */
    private void escribirMascotaEnRAF(RandomAccessFile raf, Mascota mascota) throws IOException {
        // Escribir cada campo con su longitud
        escribirStringRAF(raf, mascota.getNombreComun());
        escribirStringRAF(raf, mascota.getApodo());
        escribirStringRAF(raf, mascota.getClasificacion().name());
        escribirStringRAF(raf, mascota.getFamilia());
        escribirStringRAF(raf, mascota.getGenero());
        escribirStringRAF(raf, mascota.getEspecie());
        escribirStringRAF(raf, mascota.getTipoAlimentoPrincipal().name());
    }
    
    /**
     * Escribe una cadena en un RandomAccessFile con su longitud
     * 
     * @param raf El RandomAccessFile donde escribir
     * @param str La cadena a escribir
     * @throws IOException Si ocurre un error de escritura
     */
    private void escribirStringRAF(RandomAccessFile raf, String str) throws IOException {
        if (str == null) {
            raf.writeInt(0);
        } else {
            byte[] bytes = str.getBytes("UTF-8");
            raf.writeInt(bytes.length);
            raf.write(bytes);
        }
    }
}
