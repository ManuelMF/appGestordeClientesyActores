package Vista;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;

public class AcercaDe extends JFrame {
	
	public JLabel logo;

	public AcercaDe() {
		super();
		this.setBounds(500, 300, 568, 252);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Acerca de Nosotros");
		getContentPane().setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AcercaDe.class.getResource("/Resources/logov1.png")));
		
		logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(DatosCliente.class.getResource("/Resources/logov1.png")));
		logo.setBounds(21, 21, 120, 55);
		getContentPane().add(logo);
		
		JLabel lblGestorDeCliente = new JLabel("Gestor de trabajadores y clientes");
		lblGestorDeCliente.setFont(new Font("Arial", Font.BOLD, 15));
		lblGestorDeCliente.setBounds(135, 21, 255, 14);
		getContentPane().add(lblGestorDeCliente);
		
		JLabel lblContacto = new JLabel("Para mirar nuestras tarifas entre en: www.Metamask.com");
		lblContacto.setBounds(135, 87, 288, 14);
		getContentPane().add(lblContacto);
		
		JLabel lblContacto2 = new JLabel("Contacte con nosotros en:");
		lblContacto2.setBounds(135, 112, 134, 14);
		getContentPane().add(lblContacto2);
		
		JLabel lblgmail = new JLabel("Gmail: Metemask.antecionAlCliente@gmail.com");
		lblgmail.setIcon(new ImageIcon(AcercaDe.class.getResource("/Resources/Gmail_29991.png")));
		lblgmail.setBounds(135, 137, 286, 23);
		getContentPane().add(lblgmail);
		
		JLabel lbltel = new JLabel("Tel\u00E9fono: 601784512");
		lbltel.setIcon(new ImageIcon(AcercaDe.class.getResource("/Resources/llamada-telefonica.png")));
		lbltel.setBounds(135, 171, 170, 34);
		getContentPane().add(lbltel);
		
		JTextPane txtpnNosEncargamosDe = new JTextPane();
		txtpnNosEncargamosDe.setEditable(false);
		txtpnNosEncargamosDe.setBackground(Color.LIGHT_GRAY);
		txtpnNosEncargamosDe.setText("Nos encargamos de desarrollar todo tipo de aplicaciones moviles y web a buen precio y en un tiempo competente.");
		txtpnNosEncargamosDe.setBounds(135, 42, 385, 34);
		getContentPane().add(txtpnNosEncargamosDe);
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
}
