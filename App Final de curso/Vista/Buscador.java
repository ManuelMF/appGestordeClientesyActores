package Vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Buscador extends JFrame {
	public JTextField textFieldNombre;
	public JTextField textFieldApellido;
	public JTextField textFieldTelefono;
	public JButton btnBuscar;
	public JSpinner spinnerid;
	public JLabel lblTelefono;

	public Buscador() {
		super();
		this.setBounds(500, 300, 267, 256);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Buscador");
		getContentPane().setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscador.class.getResource("/Resources/logov1.png")));
		
		JLabel lblTitulo = new JLabel("Filtrar busqueda por:");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitulo.setBounds(25, 23, 158, 22);
		getContentPane().add(lblTitulo);
		
		JLabel lblid = new JLabel("Id:");
		lblid.setBounds(25, 56, 21, 14);
		getContentPane().add(lblid);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setBounds(25, 118, 46, 14);
		getContentPane().add(lblApellido);
		
		lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setBounds(25, 149, 46, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblNombre = new JLabel("Nombre\r\n:");
		lblNombre.setBounds(25, 87, 46, 14);
		getContentPane().add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(84, 84, 151, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(84, 115, 151, 20);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(84, 146, 151, 20);
		getContentPane().add(textFieldTelefono);
		
		spinnerid = new JSpinner();
		spinnerid.setBounds(84, 53, 75, 20);
		getContentPane().add(spinnerid);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(Buscador.class.getResource("/Resources/search_FILL0_wght400_GRAD0_opsz20.png")));
		btnBuscar.setBounds(84, 182, 89, 23);
		getContentPane().add(btnBuscar);
		
		if (Controlador.Menu.comprobadorCliente_Actor()==0) {
			textFieldTelefono.setVisible(true);
			lblTelefono.setVisible(true);
		} else {
			textFieldTelefono.setVisible(false);
			lblTelefono.setVisible(false);
		}
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
}
