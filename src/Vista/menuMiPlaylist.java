package Vista;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class menuMiPlaylist extends JFrame {
public FondoPanel fondo = new FondoPanel();
	
	public menuMiPlaylist() {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrameInicioSesion.class.getResource("/Resources/logo.png")));
		this.setContentPane(fondo);
		this.setBounds(500, 300, 513, 417);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Top Music");
		this.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mi playlist");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(143, 106, 46, 14);
		fondo.add(lblNewLabel);
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
}
