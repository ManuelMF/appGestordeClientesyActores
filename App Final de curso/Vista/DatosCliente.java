package Vista;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class DatosCliente extends JFrame  {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public JTextField textFieldNombre;
	public JTextField textFieldApellidos;
	public JTextField textFieldEmail;
	public JTextField textFieldTelefono;
	public JTextField textFieldDireccion1;
	public JTextField textFieldDireccion2;
	public JTextField textFieldPoblacion;
	public JTextField textFieldCodigoPostal;
	public JButton btnGuardar;
	public JButton btnEliminar;
	public JButton btnVaciar;
	public JComboBox<String> comboBoxPais;
	public JComboBox<String> comboBoxCiudad;
	public JLabel logo;

	public DatosCliente() {
		super();
		this.setBounds(500, 300, 382, 531);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Datos Cliente");
		getContentPane().setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatosCliente.class.getResource("/Resources/logov1.png")));
		
		logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(DatosCliente.class.getResource("/Resources/logov1.png")));
		logo.setBounds(32, 13, 119, 53);
		getContentPane().add(logo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(52, 75, 46, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setBounds(148, 75, 46, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(52, 119, 46, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setBounds(234, 119, 46, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblDireccion1 = new JLabel("Direcci\u00F3n 1:");
		lblDireccion1.setBounds(51, 164, 56, 14);
		getContentPane().add(lblDireccion1);
		
		JLabel lblDireccion2 = new JLabel("Dirreci\u00F3n 2:");
		lblDireccion2.setBounds(52, 210, 55, 14);
		getContentPane().add(lblDireccion2);

		JLabel lblPoblacion = new JLabel("Poblacion:");
		lblPoblacion.setBounds(52, 256, 56, 14);
		getContentPane().add(lblPoblacion);
		
		JLabel lblCodigoPostal = new JLabel("Cod. Postal:");
		lblCodigoPostal.setBounds(234, 256, 66, 14);
		getContentPane().add(lblCodigoPostal);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(52, 350, 46, 14);
		getContentPane().add(lblCiudad);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(52, 302, 46, 14);
		getContentPane().add(lblPais);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(52, 90, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setBounds(148, 90, 172, 20);
		getContentPane().add(textFieldApellidos);
		textFieldApellidos.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(52, 133, 172, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(234, 133, 86, 20);
		getContentPane().add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		textFieldDireccion1 = new JTextField();
		textFieldDireccion1.setBounds(52, 179, 268, 20);
		getContentPane().add(textFieldDireccion1);
		textFieldDireccion1.setColumns(10);
		
		textFieldDireccion2 = new JTextField();
		textFieldDireccion2.setBounds(52, 225, 268, 20);
		getContentPane().add(textFieldDireccion2);
		textFieldDireccion2.setColumns(10);
		
		textFieldPoblacion = new JTextField();
		textFieldPoblacion.setText(" ");
		textFieldPoblacion.setBounds(52, 271, 172, 20);
		getContentPane().add(textFieldPoblacion);
		textFieldPoblacion.setColumns(10);
		
		textFieldCodigoPostal = new JTextField();
		textFieldCodigoPostal.setBounds(234, 271, 86, 20);
		getContentPane().add(textFieldCodigoPostal);
		textFieldCodigoPostal.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(DatosCliente.class.getResource("/Resources/download_FILL0_wght400_GRAD0_opsz20.png")));
		btnGuardar.setBounds(52, 410, 95, 23);
		getContentPane().add(btnGuardar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(DatosCliente.class.getResource("/Resources/delete_FILL0_wght400_GRAD0_opsz20.png")));
		btnEliminar.setBounds(225, 410, 95, 23);
		getContentPane().add(btnEliminar);
		
		btnVaciar = new JButton("");
		btnVaciar.setIcon(new ImageIcon(DatosCliente.class.getResource("/Resources/refresh_FILL0_wght400_GRAD0_opsz20.png")));
		btnVaciar.setBounds(169, 410, 34, 23);
		getContentPane().add(btnVaciar);
		
		comboBoxPais = new JComboBox<String>();
		comboBoxPais.setBounds(52, 317, 269, 22);
		getContentPane().add(comboBoxPais);
		
		comboBoxCiudad = new JComboBox<String>();
		comboBoxCiudad.setBounds(51, 364, 269, 22);
		getContentPane().add(comboBoxCiudad);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(52, 397, 268, 2);
		getContentPane().add(separator);
		
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
}
