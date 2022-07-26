package Vista;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class FrameInicioSesion extends JFrame {
 
	public FondoPanel fondo = new FondoPanel();
	public JTextField txtCorreoNick;
	public JPasswordField passwordField;
	public JButton btnCrearCuenta;
	public JButton btnInicioSesion;
	public JLabel lblFalloInicioSesion;
	public JLabel lblFalloInicioSesionContraIncorrecta;
	public JLabel lblContrasenya;
	public JButton btnEntrarInvitado;
	
	public FrameInicioSesion() {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrameInicioSesion.class.getResource("/Resources/logo.png")));
		this.setContentPane(fondo);
		this.setBounds(500, 300, 414, 538);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Top Music");
		this.getContentPane().setLayout(null);
		
		
		JLabel lbltextoinicial = new JLabel("Bienvenido a Top Music");
		lbltextoinicial.setForeground(Color.WHITE);
		lbltextoinicial.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 24));
		lbltextoinicial.setBounds(72, 101, 272, 42);
		getContentPane().add(lbltextoinicial);
		
		
		JLabel lblInicioSesiontext = new JLabel("Iniciar Sesion");
		lblInicioSesiontext.setForeground(Color.WHITE);
		lblInicioSesiontext.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 19));
		lblInicioSesiontext.setBounds(96, 173, 122, 20);
		fondo.add(lblInicioSesiontext);
		
		txtCorreoNick = new JTextField();
		txtCorreoNick.setForeground(Color.GRAY);
		txtCorreoNick.setText("Correo, nick");
		txtCorreoNick.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCorreoNick.setBounds(96, 204, 218, 42);
		fondo.add(txtCorreoNick);
		txtCorreoNick.setColumns(10);
		
		lblContrasenya = new JLabel("Contrase\u00F1a");
		lblContrasenya.setForeground(Color.WHITE);
		lblContrasenya.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 19));
		lblContrasenya.setBounds(96, 257, 122, 20);
		fondo.add(lblContrasenya);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(96, 288, 218, 42);
		fondo.add(passwordField);
		
		btnInicioSesion = new JButton("Siguiente");
		btnInicioSesion.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnInicioSesion.setBounds(215, 350, 99, 23);
		fondo.add(btnInicioSesion);
		
		btnCrearCuenta = new JButton("Crear Cuenta");
		
		btnCrearCuenta.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btnCrearCuenta.setBounds(96, 351, 99, 23);
		fondo.add(btnCrearCuenta);
		
		
		
		lblFalloInicioSesion = new JLabel("No se a podido encontrar tu cuenta en Top Music");
		lblFalloInicioSesion.setIcon(new ImageIcon(FrameInicioSesion.class.getResource("/resources/info.png")));
		lblFalloInicioSesion.setForeground(Color.RED);
		lblFalloInicioSesion.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblFalloInicioSesion.setBounds(66, 412, 297, 20);
		fondo.add(lblFalloInicioSesion);
		lblFalloInicioSesion.setVisible(false);
		
		lblFalloInicioSesionContraIncorrecta = new JLabel("Contraseña incorrecta");
		lblFalloInicioSesionContraIncorrecta.setIcon(new ImageIcon(FrameInicioSesion.class.getResource("/resources/info.png")));
		lblFalloInicioSesionContraIncorrecta.setForeground(Color.RED);
		lblFalloInicioSesionContraIncorrecta.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		lblFalloInicioSesionContraIncorrecta.setBounds(96, 412, 223, 20);
		fondo.add(lblFalloInicioSesionContraIncorrecta);
		
		btnEntrarInvitado = new JButton("Click para entrar como invitado");
		btnEntrarInvitado.setForeground(Color.WHITE);
		btnEntrarInvitado.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnEntrarInvitado.setBounds(85, 384, 245, 23);
		fondo.add(btnEntrarInvitado);
		lblFalloInicioSesionContraIncorrecta.setVisible(false);
		btnEntrarInvitado.setOpaque(false);
		btnEntrarInvitado.setContentAreaFilled(false);
		btnEntrarInvitado.setBorderPainted(false);
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
}
