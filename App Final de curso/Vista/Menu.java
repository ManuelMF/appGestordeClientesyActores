package Vista;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton btnCrear;
	public JScrollPane scrollPane;
	public static Tabla mitabla;
	public JMenuItem mntmSalir;
	public JButton btnBuscar;
	public JButton btnQuitarFiltros;
	public JMenuItem mntmAcercaDe;
	public static JLabel lblBusqueda;
	public JButton btnBorrar;
	public JMenuItem mntmActores;
	public JMenuItem mntmClientes;

	public Menu() {
		
		super();
		this.setBounds(500, 300, 893, 532);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Gestor de clientes");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Resources/logo.png")));
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.setIcon(new ImageIcon(Menu.class.getResource("/Resources/android_contacts_FILL0_wght400_GRAD0_opsz20.png")));
		mnMantenimiento.add(mntmClientes);
		
		mntmActores = new JMenuItem("Actores");
		mntmActores.setIcon(new ImageIcon(Menu.class.getResource("/Resources/face_FILL0_wght400_GRAD0_opsz20.png")));
		mnMantenimiento.add(mntmActores);
		
		JMenu mnConfiguracion = new JMenu("Configuracion");
		menuBar.add(mnConfiguracion);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(Menu.class.getResource("/Resources/close_FILL0_wght400_GRAD0_opsz20.png")));
		mnMantenimiento.add(mntmSalir);
		
		mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.setIcon(new ImageIcon(Menu.class.getResource("/Resources/contact_mail_FILL0_wght400_GRAD0_opsz20.png")));
		mnAyuda.add(mntmAcercaDe);
		
		
		scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
       
        Object[] cabecera= {"Id","Nombre","Apellidos","Teléfono","Fecha Creacion"};
        Class<?>[] clases= {Integer.class, String.class, String.class, Integer.class, Date.class};
        Integer[] medidas= {1, 100, 200};
       
        mitabla = new Tabla(cabecera, clases, medidas, null,null,null);
       
        scrollPane.setViewportView(mitabla.tabla);
        
        mitabla.alinear('c', 0);
        mitabla.alinear('c', 1);
        mitabla.alinear('c', 2);
        mitabla.alinear('c', 3);
        mitabla.alinear('c', 4);
        
        mitabla.alinearHeader('c', 0);
        mitabla.alinearHeader('c', 1);
        mitabla.alinearHeader('c', 2);
        mitabla.alinearHeader('c', 3);
        mitabla.alinearHeader('c', 4);
        
        int repeticiones = Vista.Tabla.loadObjetos();
        
        lblBusqueda = new JLabel("  Filas: "+repeticiones);
        lblBusqueda.setEnabled(false);
        getContentPane().add(lblBusqueda, BorderLayout.SOUTH);
        
        btnCrear = new JButton("Crear");
        btnCrear.setIcon(new ImageIcon(Menu.class.getResource("/Resources/add_circle_FILL0_wght400_GRAD0_opsz20.png")));
        toolBar.add(btnCrear);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setIcon(new ImageIcon(Menu.class.getResource("/Resources/delete_FILL0_wght400_GRAD0_opsz20.png")));
		toolBar.add(btnBorrar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(Menu.class.getResource("/Resources/search_FILL0_wght400_GRAD0_opsz20.png")));
		toolBar.add(btnBuscar);
		
		btnQuitarFiltros = new JButton("Quitar Filtros");
		btnQuitarFiltros.setIcon(new ImageIcon(Menu.class.getResource("/Resources/filter_alt_off_FILL0_wght400_GRAD0_opsz20.png")));
		toolBar.add(btnQuitarFiltros);
        
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
}
