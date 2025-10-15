package udistrital.avanzada.veterinaria.vista.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import udistrital.avanzada.veterinaria.modelo.modelo.MascotaVO;

/**
 * Clase VistaPrincipal que representa únicamente la interfaz gráfica.
 * No contiene lógica de negocio, solo componentes visuales y captura de eventos.
 * Sigue el patrón MVC como vista pura sin lógica de aplicación.
 * 
 * @author Estudiantes - Universidad Distrital Francisco José de Caldas
 * @version 1.0
 * @since 2024
 */
public class VistaPrincipal extends JFrame {

    // Componentes de la interfaz
    private DefaultTableModel modeloTabla;
    private JTable tablaMascotas;
    private JTextField txtIdMascota;
    private JTextField txtApodo;
    private JComboBox<String> cmbClasificacion;
    private JTextField txtFamilia;
    private JTextField txtGenero;
    private JTextField txtEspecie;
    private JComboBox<String> cmbTipoAlimento;
    private JTextField txtEdad;
    private JTextField txtPeso;
    private JTextField txtObservaciones;
    private JComboBox<String> cmbCriterioBusqueda;
    private JTextField txtBusqueda;
    private JLabel lblEstado;

    // Botones
    private JButton btnAdicionar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnSerializar;
    private JButton btnSalir;
    private JButton btnBuscar;
    private JButton btnMostrarTodos;

    /**
     * Constructor de la vista principal.
     * Solo inicializa los componentes visuales sin lógica de negocio.
     */
    public VistaPrincipal() {
        inicializarComponentes();
        configurarInterfaz();
    }

    /**
     * Inicializa todos los componentes de la interfaz.
     * Solo configura la parte visual, sin lógica de aplicación.
     */
    private void inicializarComponentes() {
        // Configuración de la ventana principal
        setTitle("Sistema de Gestión de Mascotas Exóticas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior con título
        JPanel panelTitulo = crearPanelTitulo();
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Panel central con formulario y tabla
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(crearPanelFormulario(), BorderLayout.WEST);
        panelCentral.add(crearPanelTabla(), BorderLayout.CENTER);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = crearPanelBotones();
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Panel de estado
        JPanel panelEstado = crearPanelEstado();
        panelPrincipal.add(panelEstado, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    /**
     * Crea el panel con el título de la aplicación.
     * 
     * @return Panel con el título
     */
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(70, 130, 180));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        JLabel titulo = new JLabel("Sistema de Gestión de Mascotas Exóticas");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        panel.add(titulo);

        return panel;
    }

    /**
     * Crea el panel del formulario de datos.
     * 
     * @return Panel con el formulario
     */
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos de la Mascota"));
        panel.setPreferredSize(new Dimension(400, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Campos del formulario
        int fila = 0;
        gbc.gridx = 0;
        gbc.gridy = fila++;
        panel.add(new JLabel("ID Mascota:"), gbc);
        gbc.gridx = 1;
        txtIdMascota = new JTextField(15);
        panel.add(txtIdMascota, gbc);

        gbc.gridx = 0;
        gbc.gridy = fila++;
        panel.add(new JLabel("Apodo:"), gbc);
        gbc.gridx = 1;
        txtApodo = new JTextField(15);
        panel.add(txtApodo, gbc);

        gbc.gridx = 0;
        gbc.gridy = fila++;
        panel.add(new JLabel("Clasificación:"), gbc);
        gbc.gridx = 1;
        cmbClasificacion = new JComboBox<>(new String[]{"Mamífero", "Ave", "Reptil", "Anfibio", "Pez", "Invertebrado"});
        cmbClasificacion.setPreferredSize(new Dimension(150, 25));
        panel.add(cmbClasificacion, gbc);

        gbc.gridx = 0;
        gbc.gridy = fila++;
        panel.add(new JLabel("Familia:"), gbc);
        gbc.gridx = 1;
        txtFamilia = new JTextField(15);
        panel.add(txtFamilia, gbc);

        gbc.gridx = 0;
        gbc.gridy = fila++;
        panel.add(new JLabel("Género:"), gbc);
        gbc.gridx = 1;
        txtGenero = new JTextField(15);
        panel.add(txtGenero, gbc);

        gbc.gridx = 0;
        gbc.gridy = fila++;
        panel.add(new JLabel("Especie:"), gbc);
        gbc.gridx = 1;
        txtEspecie = new JTextField(15);
        panel.add(txtEspecie, gbc);

        gbc.gridx = 0;
        gbc.gridy = fila++;
        panel.add(new JLabel("Tipo Alimento:"), gbc);
        gbc.gridx = 1;
        cmbTipoAlimento = new JComboBox<>(new String[]{"Carnívoro", "Herbívoro", "Omnívoro", "Insectívoro", "Piscívoro", "Frugívoro"});
        cmbTipoAlimento.setPreferredSize(new Dimension(150, 25));
        panel.add(cmbTipoAlimento, gbc);

        gbc.gridx = 0;
        gbc.gridy = fila++;
        panel.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        txtEdad = new JTextField(15);
        panel.add(txtEdad, gbc);

        gbc.gridx = 0;
        gbc.gridy = fila++;
        panel.add(new JLabel("Peso:"), gbc);
        gbc.gridx = 1;
        txtPeso = new JTextField(15);
        panel.add(txtPeso, gbc);

        gbc.gridx = 0;
        gbc.gridy = fila++;
        panel.add(new JLabel("Observaciones:"), gbc);
        gbc.gridx = 1;
        txtObservaciones = new JTextField(15);
        panel.add(txtObservaciones, gbc);

        return panel;
    }

    /**
     * Crea el panel con la tabla de mascotas.
     * 
     * @return Panel con la tabla
     */
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Mascotas"));

        // Configurar tabla
        String[] columnas = {"ID", "Apodo", "Clasificación", "Familia", "Género", "Especie", "Tipo Alimento", "Edad", "Peso", "Observaciones"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla de solo lectura
            }
        };
        tablaMascotas = new JTable(modeloTabla);
        tablaMascotas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaMascotas);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout());
        panelBusqueda.add(new JLabel("Buscar por:"));
        cmbCriterioBusqueda = new JComboBox<>(new String[]{"Apodo", "Clasificación", "Familia", "Tipo Alimento"});
        panelBusqueda.add(cmbCriterioBusqueda);
        txtBusqueda = new JTextField(15);
        panelBusqueda.add(txtBusqueda);
        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);
        btnMostrarTodos = new JButton("Mostrar Todos");
        panelBusqueda.add(btnMostrarTodos);

        panel.add(panelBusqueda, BorderLayout.NORTH);

        return panel;
    }

    /**
     * Crea el panel con los botones de operaciones.
     * 
     * @return Panel con los botones
     */
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBackground(new Color(34, 139, 34));
        btnAdicionar.setForeground(Color.WHITE);

        btnModificar = new JButton("Modificar");
        btnModificar.setBackground(new Color(255, 140, 0));
        btnModificar.setForeground(Color.WHITE);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setForeground(Color.WHITE);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(new Color(105, 105, 105));
        btnLimpiar.setForeground(Color.WHITE);

        btnSerializar = new JButton("Serializar");
        btnSerializar.setBackground(new Color(138, 43, 226));
        btnSerializar.setForeground(Color.WHITE);

        btnSalir = new JButton("Salir");
        btnSalir.setBackground(new Color(139, 0, 0));
        btnSalir.setForeground(Color.WHITE);

        panel.add(btnAdicionar);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);
        panel.add(btnSerializar);
        panel.add(btnSalir);

        return panel;
    }

    /**
     * Crea el panel de estado de la aplicación.
     * 
     * @return Panel de estado
     */
    private JPanel crearPanelEstado() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createLoweredBevelBorder());
        panel.setBackground(new Color(240, 240, 240));

        lblEstado = new JLabel("Sistema iniciado correctamente");
        lblEstado.setFont(new Font("Arial", Font.ITALIC, 12));
        panel.add(lblEstado);

        return panel;
    }

    /**
     * Configura la interfaz y establece los estilos.
     */
    private void configurarInterfaz() {
        // Configurar estilos de botones
        for (java.awt.Component comp : getComponents()) {
            if (comp instanceof JPanel) {
                configurarEstilosPanel((JPanel) comp);
            }
        }
    }

    /**
     * Configura los estilos de un panel y sus componentes.
     * 
     * @param panel Panel a configurar
     */
    private void configurarEstilosPanel(JPanel panel) {
        for (java.awt.Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                JButton boton = (JButton) comp;
                boton.setFont(new Font("Arial", Font.BOLD, 12));
                boton.setPreferredSize(new Dimension(100, 30));
            }
        }
    }

    // ========== MÉTODOS GETTERS PARA ACCESO A COMPONENTES ==========

    /**
     * Obtiene el modelo de la tabla.
     * 
     * @return Modelo de la tabla
     */
    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    /**
     * Obtiene la tabla de mascotas.
     * 
     * @return Tabla de mascotas
     */
    public JTable getTablaMascotas() {
        return tablaMascotas;
    }

    /**
     * Obtiene el campo de ID de mascota.
     * 
     * @return Campo de ID de mascota
     */
    public JTextField getTxtIdMascota() {
        return txtIdMascota;
    }

    /**
     * Obtiene el campo de apodo.
     * 
     * @return Campo de apodo
     */
    public JTextField getTxtApodo() {
        return txtApodo;
    }

    /**
     * Obtiene el combo de clasificación.
     * 
     * @return Combo de clasificación
     */
    public JComboBox<String> getCmbClasificacion() {
        return cmbClasificacion;
    }

    /**
     * Obtiene el campo de familia.
     * 
     * @return Campo de familia
     */
    public JTextField getTxtFamilia() {
        return txtFamilia;
    }

    /**
     * Obtiene el campo de género.
     * 
     * @return Campo de género
     */
    public JTextField getTxtGenero() {
        return txtGenero;
    }

    /**
     * Obtiene el campo de especie.
     * 
     * @return Campo de especie
     */
    public JTextField getTxtEspecie() {
        return txtEspecie;
    }

    /**
     * Obtiene el combo de tipo de alimento.
     * 
     * @return Combo de tipo de alimento
     */
    public JComboBox<String> getCmbTipoAlimento() {
        return cmbTipoAlimento;
    }

    /**
     * Obtiene el campo de edad.
     * 
     * @return Campo de edad
     */
    public JTextField getTxtEdad() {
        return txtEdad;
    }

    /**
     * Obtiene el campo de peso.
     * 
     * @return Campo de peso
     */
    public JTextField getTxtPeso() {
        return txtPeso;
    }

    /**
     * Obtiene el campo de observaciones.
     * 
     * @return Campo de observaciones
     */
    public JTextField getTxtObservaciones() {
        return txtObservaciones;
    }

    /**
     * Obtiene el combo de criterio de búsqueda.
     * 
     * @return Combo de criterio de búsqueda
     */
    public JComboBox<String> getCmbCriterioBusqueda() {
        return cmbCriterioBusqueda;
    }

    /**
     * Obtiene el campo de búsqueda.
     * 
     * @return Campo de búsqueda
     */
    public JTextField getTxtBusqueda() {
        return txtBusqueda;
    }

    /**
     * Obtiene la etiqueta de estado.
     * 
     * @return Etiqueta de estado
     */
    public JLabel getLblEstado() {
        return lblEstado;
    }

    // ========== MÉTODOS GETTERS PARA BOTONES ==========

    /**
     * Obtiene el botón de adicionar.
     * 
     * @return Botón de adicionar
     */
    public JButton getBtnAdicionar() {
        return btnAdicionar;
    }

    /**
     * Obtiene el botón de modificar.
     * 
     * @return Botón de modificar
     */
    public JButton getBtnModificar() {
        return btnModificar;
    }

    /**
     * Obtiene el botón de eliminar.
     * 
     * @return Botón de eliminar
     */
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    /**
     * Obtiene el botón de limpiar.
     * 
     * @return Botón de limpiar
     */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    /**
     * Obtiene el botón de serializar.
     * 
     * @return Botón de serializar
     */
    public JButton getBtnSerializar() {
        return btnSerializar;
    }

    /**
     * Obtiene el botón de salir.
     * 
     * @return Botón de salir
     */
    public JButton getBtnSalir() {
        return btnSalir;
    }

    /**
     * Obtiene el botón de buscar.
     * 
     * @return Botón de buscar
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Obtiene el botón de mostrar todos.
     * 
     * @return Botón de mostrar todos
     */
    public JButton getBtnMostrarTodos() {
        return btnMostrarTodos;
    }

    // ========== MÉTODOS PARA ACTUALIZAR LA VISTA ==========

    /**
     * Actualiza el mensaje de estado de la aplicación.
     * 
     * @param mensaje Mensaje a mostrar
     */
    public void actualizarEstado(String mensaje) {
        lblEstado.setText(mensaje);
    }

    /**
     * Limpia todos los campos del formulario.
     */
    public void limpiarCampos() {
        txtIdMascota.setText("");
        txtApodo.setText("");
        cmbClasificacion.setSelectedIndex(0);
        txtFamilia.setText("");
        txtGenero.setText("");
        txtEspecie.setText("");
        cmbTipoAlimento.setSelectedIndex(0);
        txtEdad.setText("");
        txtPeso.setText("");
        txtObservaciones.setText("");
        txtBusqueda.setText("");
        tablaMascotas.clearSelection();
    }

    /**
     * Carga los datos de una mascota en el formulario.
     * 
     * @param mascota Mascota a cargar
     */
    public void cargarDatosEnFormulario(MascotaVO mascota) {
        if (mascota != null) {
            txtIdMascota.setText(mascota.getIdMascota());
            txtApodo.setText(mascota.getApodo());
            cmbClasificacion.setSelectedItem(mascota.getClasificacion());
            txtFamilia.setText(mascota.getFamilia());
            txtGenero.setText(mascota.getGenero());
            txtEspecie.setText(mascota.getEspecie());
            cmbTipoAlimento.setSelectedItem(mascota.getTipoAlimento());
            txtEdad.setText(String.valueOf(mascota.getEdad()));
            txtPeso.setText(String.valueOf(mascota.getPeso()));
            txtObservaciones.setText(mascota.getObservaciones());
        }
    }

    /**
     * Obtiene los datos del formulario como objeto MascotaVO.
     * 
     * @return Objeto MascotaVO con los datos del formulario
     */
    public MascotaVO obtenerDatosDelFormulario() {
        MascotaVO mascota = new MascotaVO();
        mascota.setIdMascota(txtIdMascota.getText().trim());
        mascota.setApodo(txtApodo.getText().trim());
        mascota.setClasificacion((String) cmbClasificacion.getSelectedItem());
        mascota.setFamilia(txtFamilia.getText().trim());
        mascota.setGenero(txtGenero.getText().trim());
        mascota.setEspecie(txtEspecie.getText().trim());
        mascota.setTipoAlimento((String) cmbTipoAlimento.getSelectedItem());
        
        try {
            mascota.setEdad(Integer.parseInt(txtEdad.getText().trim()));
        } catch (NumberFormatException e) {
            mascota.setEdad(0);
        }
        
        try {
            mascota.setPeso(Double.parseDouble(txtPeso.getText().trim()));
        } catch (NumberFormatException e) {
            mascota.setPeso(0.0);
        }
        
        mascota.setObservaciones(txtObservaciones.getText().trim());
        return mascota;
    }

    /**
     * Valida que los campos obligatorios estén completos.
     * 
     * @return true si todos los campos están completos, false en caso contrario
     */
    public boolean validarCamposObligatorios() {
        return !txtIdMascota.getText().trim().isEmpty() &&
               !txtApodo.getText().trim().isEmpty() &&
               cmbClasificacion.getSelectedItem() != null &&
               !txtFamilia.getText().trim().isEmpty() &&
               !txtGenero.getText().trim().isEmpty() &&
               !txtEspecie.getText().trim().isEmpty() &&
               cmbTipoAlimento.getSelectedItem() != null &&
               !txtEdad.getText().trim().isEmpty() &&
               !txtPeso.getText().trim().isEmpty();
    }

    /**
     * Muestra una lista de mascotas en la tabla.
     * 
     * @param mascotas Lista de mascotas a mostrar
     */
    public void mostrarMascotas(ArrayList<MascotaVO> mascotas) {
        modeloTabla.setRowCount(0);
        for (MascotaVO mascota : mascotas) {
            Object[] fila = {
                mascota.getIdMascota(),
                mascota.getApodo(),
                mascota.getClasificacion(),
                mascota.getFamilia(),
                mascota.getGenero(),
                mascota.getEspecie(),
                mascota.getTipoAlimento(),
                mascota.getEdad(),
                mascota.getPeso(),
                mascota.getObservaciones()
            };
            modeloTabla.addRow(fila);
        }
    }
}