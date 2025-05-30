import javax.swing.*;
import java.awt.*;
import java.io.*;

public class VentanaPrincipal extends JFrame {
    // Campos del formulario
    private JTextField txtNombre, txtEmail, txtTelefono;
    private JComboBox<String> cmbTipoUsuario;
    private JCheckBox chkTerminos;
    private JButton btnGuardar, btnLimpiar;
    private static final String ARCHIVO = "datos_usuario.txt";

    public VentanaPrincipal() {
        super("Registro de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);  // Centra la ventana

        // === Parte 2: Barra de menú ===
        JMenuBar menuBar = new JMenuBar();
        JMenu mArchivo = new JMenu("Archivo");
        JMenuItem miNuevo = new JMenuItem("Nuevo");
        JMenuItem miGuardar = new JMenuItem("Guardar");
        JMenuItem miSalir = new JMenuItem("Salir");
        mArchivo.add(miNuevo);
        mArchivo.add(miGuardar);
        mArchivo.addSeparator();
        mArchivo.add(miSalir);
        menuBar.add(mArchivo);
        setJMenuBar(menuBar);

        // Salir al hacer clic
        miSalir.addActionListener(e -> System.exit(0));

        // === Parte 4: Formulario ===
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);

        // Ejercicio 6.1: JComboBox de tipo de usuario
        panel.add(new JLabel("Tipo de usuario:"));
        cmbTipoUsuario = new JComboBox<>(new String[] {
            "-- Seleccione --", "Cliente", "Empleado", "Administrador"
        });
        panel.add(cmbTipoUsuario);

        // Ejercicio 6.2: JCheckBox de términos
        panel.add(new JLabel("Aceptar términos:"));
        chkTerminos = new JCheckBox("Acepto");
        panel.add(chkTerminos);

        // Botones
        btnGuardar = new JButton("Guardar");
        btnLimpiar = new JButton("Limpiar");
        panel.add(btnGuardar);
        panel.add(btnLimpiar);

        getContentPane().add(panel, BorderLayout.CENTER);

        // === Parte 5 y ejercicios: Funcionalidad de botones ===

        // Guardar datos
        btnGuardar.addActionListener(e -> {
            // Validaciones: tipo seleccionado y chk marcado
            if (cmbTipoUsuario.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un tipo de usuario.");
                return;
            }
            if (!chkTerminos.isSelected()) {
                JOptionPane.showMessageDialog(this, "Debe aceptar términos y condiciones.");
                return;
            }
            // Guardar en archivo
            try (FileWriter fw = new FileWriter(ARCHIVO);
                 BufferedWriter bw = new BufferedWriter(fw)) {

                bw.write(txtNombre.getText());
                bw.newLine();
                bw.write(txtEmail.getText());
                bw.newLine();
                bw.write(txtTelefono.getText());
                bw.newLine();
                bw.write(cmbTipoUsuario.getSelectedItem().toString());
                bw.newLine();
                bw.write(Boolean.toString(chkTerminos.isSelected()));
                bw.flush();
                JOptionPane.showMessageDialog(this, "Datos guardados en " + ARCHIVO);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        });

        // Limpiar campos
        btnLimpiar.addActionListener(e -> {
            txtNombre.setText("");
            txtEmail.setText("");
            txtTelefono.setText("");
            cmbTipoUsuario.setSelectedIndex(0);
            chkTerminos.setSelected(false);
        });

        // === Ejercicio 6.5: Leer el archivo al iniciar ===
        File f = new File(ARCHIVO);
        if (f.exists()) {
            try (FileReader fr = new FileReader(f);
                 BufferedReader br = new BufferedReader(fr)) {

                txtNombre.setText(br.readLine());
                txtEmail.setText(br.readLine());
                txtTelefono.setText(br.readLine());
                String tipo = br.readLine();
                cmbTipoUsuario.setSelectedItem(tipo != null ? tipo : "-- Seleccione --");
                String termino = br.readLine();
                chkTerminos.setSelected(Boolean.parseBoolean(termino));
            } catch (IOException ex) {
                // Si hay error al leer, no interrumpir
            }
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        // Ejecuta la GUI en el hilo de eventos
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
