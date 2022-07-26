package Vista;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;


public class AppCantante extends JFrame {

	private static final long serialVersionUID = 1L;
	
	FondoPanel fondo = new FondoPanel();
	
	
	public AppCantante() {
		super();
		this.setContentPane(fondo);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(AppCantante.class.getResource("/Resources/logo.png")));
		this.setBounds(500, 300, 1046, 645);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Top Music");
		this.getContentPane().setLayout(null);
		
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
	 
}
