package Vista;

import java.awt.Toolkit;

import javax.swing.JFrame;

import Modelo.Usuario;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class AppInvitado extends JFrame {

	private static final long serialVersionUID = 1L;
	
	FondoPanel fondo = new FondoPanel();

	public JButton btnInicio;
	
	public AppInvitado() {
		super();
		this.setContentPane(fondo);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(AppInvitado.class.getResource("/Resources/logo.png")));
		this.setBounds(500, 300, 1046, 645);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Top Music");
		this.getContentPane().setLayout(null);
		
		btnInicio = new JButton("Inicio");
		btnInicio.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnInicio.setForeground(Color.WHITE);
		btnInicio.setBounds(23, 38, 89, 23);
		fondo.add(btnInicio);
		btnInicio.setOpaque(false);
		btnInicio.setContentAreaFilled(false);
		btnInicio.setBorderPainted(false);
		
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
}
