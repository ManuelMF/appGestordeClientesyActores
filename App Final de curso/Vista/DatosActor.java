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

public class DatosActor extends JFrame  {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public JTextField textFieldNombre;
	public JTextField textFieldApellidos;
	public JButton btnGuardar;
	public JButton btnEliminar;
	public JButton btnVaciar;
	public JLabel logo;

	public DatosActor() {
		super();
		this.setBounds(500, 300, 382, 227);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Datos Cliente");
		getContentPane().setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatosActor.class.getResource("/Resources/logov1.png")));
		
		logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(DatosActor.class.getResource("/Resources/logov1.png")));
		logo.setBounds(32, 13, 119, 53);
		getContentPane().add(logo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(52, 75, 46, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setBounds(148, 75, 46, 14);
		getContentPane().add(lblApellido);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(52, 90, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setBounds(148, 90, 172, 20);
		getContentPane().add(textFieldApellidos);
		textFieldApellidos.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(DatosActor.class.getResource("/Resources/download_FILL0_wght400_GRAD0_opsz20.png")));
		btnGuardar.setBounds(52, 134, 95, 23);
		getContentPane().add(btnGuardar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(DatosActor.class.getResource("/Resources/delete_FILL0_wght400_GRAD0_opsz20.png")));
		btnEliminar.setBounds(225, 134, 95, 23);
		getContentPane().add(btnEliminar);
		
		btnVaciar = new JButton("");
		btnVaciar.setIcon(new ImageIcon(DatosActor.class.getResource("/Resources/refresh_FILL0_wght400_GRAD0_opsz20.png")));
		btnVaciar.setBounds(168, 134, 34, 23);
		getContentPane().add(btnVaciar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(52, 121, 268, 2);
		getContentPane().add(separator);
		
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
}
