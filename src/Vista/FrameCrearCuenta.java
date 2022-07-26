package Vista;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class FrameCrearCuenta extends JFrame {
 
	public FondoPanel fondo = new FondoPanel();
	public JTextField txtCorreo;
	public JButton btnIrIniciarSesion;
	public JButton btnCrearCuenta;
	public JLabel lblFalloInicioSesion;
	private JLabel Logo;
	public JButton btnEntrarInvitado;
	public JTextField textField_nick;
	public JTextField textField_Nombre;
	public JTextField textField_Apellido;
	public JTextField textFieldDate;
	public JPasswordField passwordField;
	
	public FrameCrearCuenta() {
		super();
		this.setContentPane(fondo);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameCrearCuenta.class.getResource("/Resources/logo.png")));
		this.setBounds(500, 300, 416, 668);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Top Music");
		this.getContentPane().setLayout(null);
		
		// Label Textos
		
		JLabel lbltextoinicial = new JLabel("Bienvenido a Top Music");
		lbltextoinicial.setForeground(Color.WHITE);
		lbltextoinicial.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 24));
		lbltextoinicial.setBounds(66, 51, 272, 42);
		getContentPane().add(lbltextoinicial);
		
		JLabel lblCorreoElectronico = new JLabel("Correo electronico");
		lblCorreoElectronico.setForeground(Color.WHITE);
		lblCorreoElectronico.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblCorreoElectronico.setBounds(96, 104, 181, 20);
		fondo.add(lblCorreoElectronico);
		
		JLabel lblnick = new JLabel("Nick del usuario\r\n");
		lblnick.setForeground(Color.WHITE);
		lblnick.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblnick.setBounds(96, 171, 150, 20);
		fondo.add(lblnick);
		
		JLabel lbnombre = new JLabel("Nombre");
		lbnombre.setForeground(Color.WHITE);
		lbnombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lbnombre.setBounds(96, 239, 150, 20);
		fondo.add(lbnombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setForeground(Color.WHITE);
		lblApellido.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblApellido.setBounds(96, 306, 150, 20);
		fondo.add(lblApellido);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha nacimiento");
		lblFechaNacimiento.setForeground(Color.WHITE);
		lblFechaNacimiento.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblFechaNacimiento.setBounds(96, 372, 150, 20);
		fondo.add(lblFechaNacimiento);
		
		JLabel lblContrasenya = new JLabel("Contrase\u00F1a");
		lblContrasenya.setForeground(Color.WHITE);
		lblContrasenya.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblContrasenya.setBounds(96, 437, 150, 20);
		fondo.add(lblContrasenya);
		
		// TextField 
	
		txtCorreo = new JTextField();
		txtCorreo.setForeground(Color.BLACK);
		txtCorreo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCorreo.setBounds(96, 135, 181, 25);
		fondo.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		textField_nick = new JTextField();
		textField_nick.setForeground(Color.BLACK);
		textField_nick.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_nick.setColumns(10);
		textField_nick.setBounds(96, 202, 181, 25);
		fondo.add(textField_nick);

		textField_Nombre = new JTextField();
		textField_Nombre.setForeground(Color.BLACK);
		textField_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_Nombre.setColumns(10);
		textField_Nombre.setBounds(96, 270, 181, 25);
		fondo.add(textField_Nombre);

		textField_Apellido = new JTextField();
		textField_Apellido.setForeground(Color.BLACK);
		textField_Apellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_Apellido.setColumns(10);
		textField_Apellido.setBounds(96, 337, 181, 25);
		fondo.add(textField_Apellido);

		textFieldDate = new JTextField();
		textFieldDate.setForeground(Color.BLACK);
		textFieldDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(96, 402, 99, 25);
		fondo.add(textFieldDate);
		
		// Botones
		
		btnCrearCuenta = new JButton("Siguiente");
		btnCrearCuenta.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnCrearCuenta.setBounds(205, 511, 99, 23);
		fondo.add(btnCrearCuenta);
		
		btnIrIniciarSesion = new JButton("Iniciar Sesion");
		btnIrIniciarSesion.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btnIrIniciarSesion.setBounds(96, 511, 99, 23);
		fondo.add(btnIrIniciarSesion);
		
		btnEntrarInvitado = new JButton("Click para entrar como invitado");
		btnEntrarInvitado.setForeground(Color.WHITE);
		btnEntrarInvitado.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnEntrarInvitado.setBounds(77, 545, 245, 23);
		fondo.add(btnEntrarInvitado);
		btnEntrarInvitado.setOpaque(false);
		btnEntrarInvitado.setContentAreaFilled(false);
		btnEntrarInvitado.setBorderPainted(false);
		
		// label de errores
		
		lblFalloInicioSesion = new JLabel("");
		lblFalloInicioSesion.setIcon(new ImageIcon(FrameCrearCuenta.class.getResource("/resources/info.png")));
		lblFalloInicioSesion.setForeground(Color.RED);
		lblFalloInicioSesion.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblFalloInicioSesion.setBounds(96, 579, 242, 20);
		fondo.add(lblFalloInicioSesion);
		lblFalloInicioSesion.setVisible(false);
		
		
		
		// Logo
		/*
		Logo = new JLabel("");
		Logo.setHorizontalAlignment(SwingConstants.CENTER);
		Logo.setIcon(new ImageIcon(FrameInicioSesion.class.getResource("/resources/icone-d-information-rouge.png")));
		Logo.setBounds(101, 11, 256, 200);
		getContentPane().add(Logo);
		*/

		// Contras
	
		passwordField = new JPasswordField();
		passwordField.setBounds(96, 468, 181, 25);
		fondo.add(passwordField);
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
}
