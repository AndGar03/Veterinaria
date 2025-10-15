package udistrital.avanzada.veterinaria.control.vista;

import udistrital.avanzada.veterinaria.vista.vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import udistrital.avanzada.veterinaria.control.logica.Gestor;
import udistrital.avanzada.veterinaria.modelo.modelo.MascotaVO;

/**
 * Controlador de la vista principal que maneja todos los eventos y la lógica
 * de interacción entre la vista y el modelo. Implementa el patrón MVC como
 * controlador desacoplado de la vista y el modelo.
 * 
 * @author Estudiantes - Universidad Distrital Francisco José de Caldas
 * @version 1.0
 * @since 2024
 */
public class ControladorVistaPrincipal {

    private VistaPrincipal vista;
    private Gestor gestor;

    /**
     * Constructor del controlador de vista.
     * 
     * @param vista Vista principal a controlar
     */
    public ControladorVistaPrincipal(VistaPrincipal vista) {
        this.vista = vista;
        this.gestor = new Gestor();
        configurarEventos();
        cargarDatosIniciales();
    }

    /**
     * Configura todos los eventos de la interfaz.
     * Desacopla completamente los eventos de la vista.
     */
    private void configurarEventos() {
        // Eventos de botones principales
        vista.getBtnAdicionar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarMascota();
            }
        });

        vista.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarMascota();
            }
        });

        vista.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarMascota();
            }
        });

        vista.getBtnLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        vista.getBtnSerializar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serializarMascotas();
            }
        });

        vista.getBtnSalir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });

        // Eventos de búsqueda
        vista.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarMascotas();
            }
        });

        vista.getBtnMostrarTodos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTodasLasMascotas();
            }
        });

        // Evento de selección en tabla
        vista.getTablaMascotas().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarDatosSeleccionados();
            }
        });
    }

    /**
     * Carga los datos iniciales en la vista.
     */
    private void cargarDatosIniciales() {
        mostrarTodasLasMascotas();
        vista.actualizarEstado("Datos cargados correctamente");
    }

    /**
     * Muestra todas las mascotas en la tabla.
     */
    private void mostrarTodasLasMascotas() {
        ArrayList<MascotaVO> mascotas = gestor.obtenerTodasLasMascotas();
        vista.mostrarMascotas(mascotas);
        vista.actualizarEstado("Mostrando " + mascotas.size() + " mascotas");
    }

    /**
     * Busca mascotas según el criterio seleccionado.
     */
    private void buscarMascotas() {
        String criterio = (String) vista.getCmbCriterioBusqueda().getSelectedItem();
        String valor = vista.getTxtBusqueda().getText().trim();

        if (valor.isEmpty()) {
            mostrarTodasLasMascotas();
            return;
        }

        ArrayList<MascotaVO> mascotas = new ArrayList<>();

        switch (criterio) {
            case "Apodo":
                mascotas = gestor.buscarMascotasPorApodo(valor);
                break;
            case "Clasificación":
                mascotas = gestor.buscarMascotasPorClasificacion(valor);
                break;
            case "Familia":
                mascotas = gestor.buscarMascotasPorFamilia(valor);
                break;
            case "Tipo Alimento":
                mascotas = gestor.buscarMascotasPorTipoAlimento(valor);
                break;
        }

        vista.mostrarMascotas(mascotas);
        vista.actualizarEstado("Encontradas " + mascotas.size() + " mascotas con criterio: " + criterio);
    }

    /**
     * Carga los datos de la mascota seleccionada en el formulario.
     */
    private void cargarDatosSeleccionados() {
        int filaSeleccionada = vista.getTablaMascotas().getSelectedRow();
        if (filaSeleccionada >= 0) {
            String idMascota = (String) vista.getModeloTabla().getValueAt(filaSeleccionada, 0);
            MascotaVO mascota = gestor.consultarMascotaPorId(idMascota);
            vista.cargarDatosEnFormulario(mascota);
        }
    }

    /**
     * Adiciona una nueva mascota al sistema.
     */
    private void adicionarMascota() {
        if (vista.validarCamposObligatorios()) {
            MascotaVO mascota = vista.obtenerDatosDelFormulario();
            if (gestor.registrarMascota(mascota)) {
                mostrarTodasLasMascotas();
                vista.limpiarCampos();
                vista.actualizarEstado("Mascota adicionada exitosamente");
            } else {
                vista.actualizarEstado("Error: La mascota ya existe o hubo un problema al guardar");
            }
        } else {
            vista.actualizarEstado("Error: Complete todos los campos obligatorios");
        }
    }

    /**
     * Modifica una mascota existente.
     */
    private void modificarMascota() {
        if (vista.validarCamposObligatorios() && !vista.getTxtIdMascota().getText().trim().isEmpty()) {
            MascotaVO mascota = vista.obtenerDatosDelFormulario();
            if (gestor.modificarMascota(mascota)) {
                mostrarTodasLasMascotas();
                vista.actualizarEstado("Mascota modificada exitosamente");
            } else {
                vista.actualizarEstado("Error: No se pudo modificar la mascota");
            }
        } else {
            vista.actualizarEstado("Error: Seleccione una mascota y complete todos los campos");
        }
    }

    /**
     * Elimina una mascota del sistema.
     */
    private void eliminarMascota() {
        String idMascota = vista.getTxtIdMascota().getText().trim();
        if (!idMascota.isEmpty()) {
            if (gestor.eliminarMascota(idMascota)) {
                mostrarTodasLasMascotas();
                vista.limpiarCampos();
                vista.actualizarEstado("Mascota eliminada exitosamente");
            } else {
                vista.actualizarEstado("Error: No se pudo eliminar la mascota");
            }
        } else {
            vista.actualizarEstado("Error: Seleccione una mascota para eliminar");
        }
    }

    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarCampos() {
        vista.limpiarCampos();
        vista.actualizarEstado("Campos limpiados");
    }

    /**
     * Serializa todas las mascotas a un archivo.
     */
    private void serializarMascotas() {
        String nombreArchivo = "data/mascotas_serializadas.dat";
        if (gestor.serializarMascotas(nombreArchivo)) {
            vista.actualizarEstado("Mascotas serializadas exitosamente en: " + nombreArchivo);
        } else {
            vista.actualizarEstado("Error al serializar las mascotas");
        }
    }

    /**
     * Sale de la aplicación creando el archivo de acceso aleatorio.
     */
    private void salir() {
        String nombreArchivo = "data/estado_final.dat";
        if (gestor.crearArchivoAccesoAleatorio(nombreArchivo)) {
            vista.actualizarEstado("Archivo de estado final creado: " + nombreArchivo);
        }
        System.exit(0);
    }
}
