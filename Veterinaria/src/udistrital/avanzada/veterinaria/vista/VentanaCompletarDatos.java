package udistrital.avanzada.veterinaria.vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import udistrital.avanzada.veterinaria.modelo.Clasificacion;
import udistrital.avanzada.veterinaria.modelo.Mascota;
import udistrital.avanzada.veterinaria.modelo.TipoAlimento;

/**
 * Ventana modal para completar datos incompletos de mascotas.
 * Implementa el principio de responsabilidad única (SRP) al encargarse únicamente
 * de la interfaz para completar datos faltantes.
 * 
 * @author Sistema Veterinaria
 * @version 1.0
 */
public class VentanaCompletarDatos extends JDialog {
    
    private Mascota mascota;
    private boolean datosCompletados = false;
    
    // Componentes de la interfaz
    private JTextField txtNombreComun;
    private JTextField txtApodo;
    private JComboBox<Clasificacion> cmbClasificacion;
    private JTextField txtFamilia;
    private JTextField txtGenero;
    private JTextField txtEspecie;
    private JComboBox<TipoAlimento> cmbTipoAlimento;
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    /**
     * Constructor que inicializa la ventana modal
     * 
     * @param parent La ventana padre
     * @param mascota La mascota con datos incompletos
     */
    public VentanaCompletarDatos(JFrame parent, Mascota mascota) {
        super(parent, "Completar Datos de Mascota", true);
        this.mascota = mascota;
        
        inicializarComponentes();
        configurarVentana();
        organizarComponentes();
        cargarDatosExistentes();
    }
    
    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void inicializarComponentes() {
        txtNombreComun = new JTextField(20);
        txtApodo = new JTextField(20);
        cmbClasificacion = new JComboBox<>(Clasificacion.values());
        txtFamilia = new JTextField(20);
        txtGenero = new JTextField(20);
        txtEspecie = new JTextField(20);
        cmbTipoAlimento = new JComboBox<>(TipoAlimento.values());
        
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        
        // Configurar eventos
        btnAceptar.addActionListener(e -> aceptarDatos());
        btnCancelar.addActionListener(e -> cancelar());
    }
    
    /**
     * Configura las propiedades de la ventana
     */
    private void configurarVentana() {
        setSize(400, 500);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
    }
    
    /**
     * Organiza los componentes en la ventana
     */
    private void organizarComponentes() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));
        
        // Agregar componentes al panel
        panel.add(new JLabel("Nombre Común:"));
        panel.add(txtNombreComun);
        panel.add(new JLabel("Apodo:"));
        panel.add(txtApodo);
        panel.add(new JLabel("Clasificación:"));
        panel.add(cmbClasificacion);
        panel.add(new JLabel("Familia:"));
        panel.add(txtFamilia);
        panel.add(new JLabel("Género:"));
        panel.add(txtGenero);
        panel.add(new JLabel("Especie:"));
        panel.add(txtEspecie);
        panel.add(new JLabel("Tipo Alimento:"));
        panel.add(cmbTipoAlimento);
        
        // Botones
        panel.add(btnAceptar);
        panel.add(btnCancelar);
        
        add(panel);
    }
    
    /**
     * Carga los datos existentes de la mascota en los campos
     */
    private void cargarDatosExistentes() {
        if (mascota.getNombreComun() != null) {
            txtNombreComun.setText(mascota.getNombreComun());
        }
        if (mascota.getApodo() != null) {
            txtApodo.setText(mascota.getApodo());
        }
        if (mascota.getClasificacion() != null) {
            cmbClasificacion.setSelectedItem(mascota.getClasificacion());
        }
        if (mascota.getFamilia() != null) {
            txtFamilia.setText(mascota.getFamilia());
        }
        if (mascota.getGenero() != null) {
            txtGenero.setText(mascota.getGenero());
        }
        if (mascota.getEspecie() != null) {
            txtEspecie.setText(mascota.getEspecie());
        }
        if (mascota.getTipoAlimentoPrincipal() != null) {
            cmbTipoAlimento.setSelectedItem(mascota.getTipoAlimentoPrincipal());
        }
    }
    
    /**
     * Maneja el evento de aceptar datos
     */
    private void aceptarDatos() {
        // Validar que todos los campos estén llenos
        if (txtNombreComun.getText().trim().isEmpty() ||
            txtApodo.getText().trim().isEmpty() ||
            txtFamilia.getText().trim().isEmpty() ||
            txtGenero.getText().trim().isEmpty() ||
            txtEspecie.getText().trim().isEmpty()) {
            
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Por favor, complete todos los campos obligatorios", 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Actualizar la mascota con los datos completos
        mascota.setNombreComun(txtNombreComun.getText().trim());
        mascota.setApodo(txtApodo.getText().trim());
        mascota.setClasificacion((Clasificacion) cmbClasificacion.getSelectedItem());
        mascota.setFamilia(txtFamilia.getText().trim());
        mascota.setGenero(txtGenero.getText().trim());
        mascota.setEspecie(txtEspecie.getText().trim());
        mascota.setTipoAlimentoPrincipal((TipoAlimento) cmbTipoAlimento.getSelectedItem());
        
        datosCompletados = true;
        dispose();
    }
    
    /**
     * Maneja el evento de cancelar
     */
    private void cancelar() {
        mascota.setApodo(null); // Marcar para no incluir
        dispose();
    }
    
    /**
     * Verifica si los datos fueron completados exitosamente
     * 
     * @return true si los datos fueron completados, false en caso contrario
     */
    public boolean datosCompletados() {
        return datosCompletados;
    }
    
    /**
     * Obtiene la mascota con los datos completados
     * 
     * @return La mascota con datos completos o null si se canceló
     */
    public Mascota getMascotaCompletada() {
        return datosCompletados ? mascota : null;
    }
}
