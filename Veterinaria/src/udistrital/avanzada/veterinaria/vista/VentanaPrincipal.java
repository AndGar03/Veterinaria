package udistrital.avanzada.veterinaria.vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import udistrital.avanzada.veterinaria.modelo.Clasificacion;
import udistrital.avanzada.veterinaria.modelo.Mascota;
import udistrital.avanzada.veterinaria.modelo.TipoAlimento;

/**
 * Clase que representa la ventana principal de la aplicación de gestión de mascotas exóticas.
 * Implementa la vista del patrón MVC, conteniendo únicamente la interfaz gráfica de usuario.
 * No contiene lógica de negocio, solo se encarga de mostrar datos y capturar entrada del usuario.
 * 
 * @author Sistema Veterinaria
 * @version 1.0
 */
public class VentanaPrincipal extends JFrame {
    
    // Componentes de entrada de datos
    private JTextField txtNombreComun;
    private JTextField txtApodo;
    private JComboBox<Clasificacion> cmbClasificacion;
    private JTextField txtFamilia;
    private JTextField txtGenero;
    private JTextField txtEspecie;
    private JComboBox<TipoAlimento> cmbTipoAlimento;
    
    // Componentes de consulta
    private JTextField txtConsultaApodo;
    private JComboBox<Clasificacion> cmbConsultaClasificacion;
    private JTextField txtConsultaFamilia;
    private JComboBox<TipoAlimento> cmbConsultaTipoAlimento;
    
    // Componentes de acción
    private JButton btnAdicionar;
    private JButton btnConsultar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnSerializar;
    private JButton btnSalir;
    
    // Componentes de visualización
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;
    private JLabel lblMensaje;
    
    /**
     * Constructor que inicializa la ventana principal
     */
    public VentanaPrincipal() {
        inicializarComponentes();
        configurarVentana();
        organizarComponentes();
    }
    
    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void inicializarComponentes() {
        // Campos de entrada de datos
        txtNombreComun = new JTextField(20);
        txtApodo = new JTextField(20);
        cmbClasificacion = new JComboBox<>(new DefaultComboBoxModel<>(Clasificacion.values()));
        txtFamilia = new JTextField(20);
        txtGenero = new JTextField(20);
        txtEspecie = new JTextField(20);
        cmbTipoAlimento = new JComboBox<>(new DefaultComboBoxModel<>(TipoAlimento.values()));
        
        // Campos de consulta
        txtConsultaApodo = new JTextField(15);
        cmbConsultaClasificacion = new JComboBox<>(new DefaultComboBoxModel<>(Clasificacion.values()));
        txtConsultaFamilia = new JTextField(15);
        cmbConsultaTipoAlimento = new JComboBox<>(new DefaultComboBoxModel<>(TipoAlimento.values()));
        
        // Botones
        btnAdicionar = new JButton("Adicionar");
        btnConsultar = new JButton("Consultar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnSerializar = new JButton("Serializar a IDPYBA");
        btnSalir = new JButton("Salir");
        
        // Tabla de resultados
        String[] columnas = {"Nombre Común", "Apodo", "Clasificación", "Familia", "Género", "Especie", "Tipo Alimento"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla de solo lectura
            }
        };
        tablaMascotas = new JTable(modeloTabla);
        
        // Etiqueta de mensajes
        lblMensaje = new JLabel(" ");
    }
    
    /**
     * Configura las propiedades de la ventana
     */
    private void configurarVentana() {
        setTitle("Sistema de Gestión de Mascotas Exóticas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);
    }
    
    /**
     * Organiza los componentes en la ventana usando GridBagLayout
     */
    private void organizarComponentes() {
        setLayout(new BorderLayout());
        
        // Panel principal con scroll
        JScrollPane scrollPane = new JScrollPane(crearPanelPrincipal());
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel de mensajes en la parte inferior
        JPanel panelMensaje = new JPanel();
        panelMensaje.add(lblMensaje);
        add(panelMensaje, BorderLayout.SOUTH);
    }
    
    /**
     * Crea el panel principal con todos los componentes organizados
     * 
     * @return El panel principal
     */
    private JPanel crearPanelPrincipal() {
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Panel de entrada de datos
        JPanel panelEntrada = crearPanelEntradaDatos();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(panelEntrada, gbc);
        
        // Panel de consulta
        JPanel panelConsulta = crearPanelConsulta();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panelPrincipal.add(panelConsulta, gbc);
        
        // Panel de botones
        JPanel panelBotones = crearPanelBotones();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelPrincipal.add(panelBotones, gbc);
        
        // Panel de resultados
        JPanel panelResultados = crearPanelResultados();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelPrincipal.add(panelResultados, gbc);
        
        return panelPrincipal;
    }
    
    /**
     * Crea el panel de entrada de datos
     * 
     * @return El panel de entrada de datos
     */
    private JPanel crearPanelEntradaDatos() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Mascota"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Fila 1
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre Común:"), gbc);
        gbc.gridx = 1;
        panel.add(txtNombreComun, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Apodo:"), gbc);
        gbc.gridx = 3;
        panel.add(txtApodo, gbc);
        
        // Fila 2
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Clasificación:"), gbc);
        gbc.gridx = 1;
        panel.add(cmbClasificacion, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Familia:"), gbc);
        gbc.gridx = 3;
        panel.add(txtFamilia, gbc);
        
        // Fila 3
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Género:"), gbc);
        gbc.gridx = 1;
        panel.add(txtGenero, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Especie:"), gbc);
        gbc.gridx = 3;
        panel.add(txtEspecie, gbc);
        
        // Fila 4
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Tipo Alimento:"), gbc);
        gbc.gridx = 1;
        panel.add(cmbTipoAlimento, gbc);
        
        return panel;
    }
    
    /**
     * Crea el panel de consulta
     * 
     * @return El panel de consulta
     */
    private JPanel crearPanelConsulta() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultar Mascotas"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Fila 1
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Por Apodo:"), gbc);
        gbc.gridx = 1;
        panel.add(txtConsultaApodo, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Por Clasificación:"), gbc);
        gbc.gridx = 3;
        panel.add(cmbConsultaClasificacion, gbc);
        
        // Fila 2
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Por Familia:"), gbc);
        gbc.gridx = 1;
        panel.add(txtConsultaFamilia, gbc);
        gbc.gridx = 2;
        panel.add(new JLabel("Por Tipo Alimento:"), gbc);
        gbc.gridx = 3;
        panel.add(cmbConsultaTipoAlimento, gbc);
        
        return panel;
    }
    
    /**
     * Crea el panel de botones
     * 
     * @return El panel de botones
     */
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel();
        panel.add(btnAdicionar);
        panel.add(btnConsultar);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);
        panel.add(btnSerializar);
        panel.add(btnSalir);
        return panel;
    }
    
    /**
     * Crea el panel de resultados
     * 
     * @return El panel de resultados
     */
    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados"));
        
        JScrollPane scrollTabla = new JScrollPane(tablaMascotas);
        panel.add(scrollTabla, BorderLayout.CENTER);
        
        return panel;
    }
    
    // Métodos para obtener datos de los campos de entrada
    
    /**
     * Obtiene el nombre común ingresado
     * 
     * @return El nombre común
     */
    public String getNombreComun() {
        return txtNombreComun.getText().trim();
    }
    
    /**
     * Obtiene el apodo ingresado
     * 
     * @return El apodo
     */
    public String getApodo() {
        return txtApodo.getText().trim();
    }
    
    /**
     * Obtiene la clasificación seleccionada
     * 
     * @return La clasificación seleccionada
     */
    public Clasificacion getClasificacion() {
        return (Clasificacion) cmbClasificacion.getSelectedItem();
    }
    
    /**
     * Obtiene la familia ingresada
     * 
     * @return La familia
     */
    public String getFamilia() {
        return txtFamilia.getText().trim();
    }
    
    /**
     * Obtiene el género ingresado
     * 
     * @return El género
     */
    public String getGenero() {
        return txtGenero.getText().trim();
    }
    
    /**
     * Obtiene la especie ingresada
     * 
     * @return La especie
     */
    public String getEspecie() {
        return txtEspecie.getText().trim();
    }
    
    /**
     * Obtiene el tipo de alimento seleccionado
     * 
     * @return El tipo de alimento seleccionado
     */
    public TipoAlimento getTipoAlimento() {
        return (TipoAlimento) cmbTipoAlimento.getSelectedItem();
    }
    
    // Métodos para obtener datos de los campos de consulta
    
    /**
     * Obtiene el apodo de consulta
     * 
     * @return El apodo de consulta
     */
    public String getConsultaApodo() {
        return txtConsultaApodo.getText().trim();
    }
    
    /**
     * Obtiene la clasificación de consulta
     * 
     * @return La clasificación de consulta
     */
    public Clasificacion getConsultaClasificacion() {
        return (Clasificacion) cmbConsultaClasificacion.getSelectedItem();
    }
    
    /**
     * Obtiene la familia de consulta
     * 
     * @return La familia de consulta
     */
    public String getConsultaFamilia() {
        return txtConsultaFamilia.getText().trim();
    }
    
    /**
     * Obtiene el tipo de alimento de consulta
     * 
     * @return El tipo de alimento de consulta
     */
    public TipoAlimento getConsultaTipoAlimento() {
        return (TipoAlimento) cmbConsultaTipoAlimento.getSelectedItem();
    }
    
    // Métodos para configurar los campos con datos
    
    /**
     * Establece los datos de una mascota en los campos de entrada
     * 
     * @param mascota La mascota con los datos a mostrar
     */
    public void setDatosMascota(Mascota mascota) {
        txtNombreComun.setText(mascota.getNombreComun());
        txtApodo.setText(mascota.getApodo());
        cmbClasificacion.setSelectedItem(mascota.getClasificacion());
        txtFamilia.setText(mascota.getFamilia());
        txtGenero.setText(mascota.getGenero());
        txtEspecie.setText(mascota.getEspecie());
        cmbTipoAlimento.setSelectedItem(mascota.getTipoAlimentoPrincipal());
    }
    
    /**
     * Limpia todos los campos de entrada
     */
    public void limpiarCampos() {
        txtNombreComun.setText("");
        txtApodo.setText("");
        cmbClasificacion.setSelectedIndex(0);
        txtFamilia.setText("");
        txtGenero.setText("");
        txtEspecie.setText("");
        cmbTipoAlimento.setSelectedIndex(0);
        txtConsultaApodo.setText("");
        cmbConsultaClasificacion.setSelectedIndex(0);
        txtConsultaFamilia.setText("");
        cmbConsultaTipoAlimento.setSelectedIndex(0);
        lblMensaje.setText(" ");
    }
    
    /**
     * Actualiza la tabla con la lista de mascotas
     * 
     * @param mascotas Lista de mascotas a mostrar
     */
    public void actualizarTabla(List<Mascota> mascotas) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        
        for (Mascota mascota : mascotas) {
            Object[] fila = {
                mascota.getNombreComun(),
                mascota.getApodo(),
                mascota.getClasificacion(),
                mascota.getFamilia(),
                mascota.getGenero(),
                mascota.getEspecie(),
                mascota.getTipoAlimentoPrincipal()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    /**
     * Obtiene la mascota seleccionada en la tabla
     * 
     * @return La mascota seleccionada o null si no hay selección
     */
    public Mascota getMascotaSeleccionada() {
        int filaSeleccionada = tablaMascotas.getSelectedRow();
        if (filaSeleccionada >= 0) {
            Mascota mascota = new Mascota();
            mascota.setNombreComun((String) modeloTabla.getValueAt(filaSeleccionada, 0));
            mascota.setApodo((String) modeloTabla.getValueAt(filaSeleccionada, 1));
            mascota.setClasificacion((Clasificacion) modeloTabla.getValueAt(filaSeleccionada, 2));
            mascota.setFamilia((String) modeloTabla.getValueAt(filaSeleccionada, 3));
            mascota.setGenero((String) modeloTabla.getValueAt(filaSeleccionada, 4));
            mascota.setEspecie((String) modeloTabla.getValueAt(filaSeleccionada, 5));
            mascota.setTipoAlimentoPrincipal((TipoAlimento) modeloTabla.getValueAt(filaSeleccionada, 6));
            return mascota;
        }
        return null;
    }
    
    /**
     * Muestra un mensaje en la etiqueta de mensajes
     * 
     * @param mensaje El mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }
    
    // Métodos para registrar listeners
    
    /**
     * Registra el ActionListener para todos los botones
     * 
     * @param listener El ActionListener a registrar
     */
    public void registrarActionListener(ActionListener listener) {
        btnAdicionar.addActionListener(listener);
        btnConsultar.addActionListener(listener);
        btnModificar.addActionListener(listener);
        btnEliminar.addActionListener(listener);
        btnLimpiar.addActionListener(listener);
        btnSerializar.addActionListener(listener);
        btnSalir.addActionListener(listener);
    }
}
