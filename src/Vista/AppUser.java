package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import Modelo.Cancion;
import Modelo.Usuario;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;

public class AppUser extends JFrame {

	private static final long serialVersionUID = 1L;
	
	FondoPanel fondo = new FondoPanel();
	
	public JButton btnInicio;
	public JScrollPane scrollPaneMenu;
	public JScrollPane scrollPaneBuscador;
	public JPanel panelTabla;
	public JButton btnBuscar;
	public JButton btnAlbum;
	public JButton btnCrearPlaylist;
	public JButton btnMisPlaylist;
	public JButton btnMeGusta;
	public JPanel panelBuscador;
	private JPanel panel;
	private JLabel lblBuscador;
	public JTextField txtBuqueda;
	public JLabel lblMegusta;
	public static Vista.Tabla mitabla;
	public static Vista.Tabla mitablaBuscador;
	private JMenuBar menuBar;
	public JTextField textFieldNombrePlaylist;
	public JTextField textFieldBuscador;
	private JList list;
	public JButton btnGuardar;
	public JPanel panelCrearTabla;
	public JButton btnbuscarCrearPlaylist;
	public JScrollPane scrollPane_TodasCanciones;
	public JList list_todasCanciones;
	public JScrollPane scrollPane_CancionesPlaylist;
	public JList list_CancionesPlaylist;
	public JButton btnAnyadir;
	public JButton btnBorrar;
	public JButton btnVaciar;
	public JButton btnCancelar;
	public DefaultListModel<Cancion> modeloTodasCanciones;
	public DefaultListModel<Cancion> modeloCancionesElegidas;

	public JLabel lblAdvertencia;
	
	public AppUser() {
		super();
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmAjustesUsuario = new JMenuItem("Ajustes de Usuario");
		mntmAjustesUsuario.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/usuario.png")));
		mnUsuario.add(mntmAjustesUsuario);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercade = new JMenuItem("Acerca de");
		mntmAcercade.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/acerca-de.png")));
		mnAyuda.add(mntmAcercade);
		this.setContentPane(fondo);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(AppUser.class.getResource("/Resources/logo.png")));
		this.setBounds(500, 300, 1046, 645);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Top Music");
		this.getContentPane().setLayout(null);
		
		btnInicio = new JButton("Inicio");
		btnInicio.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnInicio.setForeground(Color.WHITE);
		btnInicio.setBounds(18, 38, 115, 23);
		fondo.add(btnInicio);
		btnInicio.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/icons8-casa-24 (1).png")));
		btnInicio.setOpaque(false);
		btnInicio.setContentAreaFilled(false);
		btnInicio.setBorderPainted(false);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/icons8-b\u00FAsqueda-24.png")));
		btnBuscar.setOpaque(false);
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBounds(21, 72, 115, 30);
		fondo.add(btnBuscar);
		
		btnAlbum = new JButton("Albumes");
		btnAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlbum.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/Album.png")));
		btnAlbum.setOpaque(false);
		btnAlbum.setForeground(Color.WHITE);
		btnAlbum.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnAlbum.setContentAreaFilled(false);
		btnAlbum.setBorderPainted(false);
		btnAlbum.setBounds(15, 202, 143, 30);
		fondo.add(btnAlbum);
		
		btnCrearPlaylist = new JButton("Crear playlist");
		btnCrearPlaylist.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/icons8-a\u00F1adir-24.png")));
		btnCrearPlaylist.setOpaque(false);
		btnCrearPlaylist.setForeground(Color.WHITE);
		btnCrearPlaylist.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnCrearPlaylist.setContentAreaFilled(false);
		btnCrearPlaylist.setBorderPainted(false);
		btnCrearPlaylist.setBounds(20, 137, 162, 30);
		fondo.add(btnCrearPlaylist);
		
		btnMisPlaylist = new JButton("Mis playlist");
		btnMisPlaylist.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/icons8-libros-24.png")));
		btnMisPlaylist.setOpaque(false);
		btnMisPlaylist.setForeground(Color.WHITE);
		btnMisPlaylist.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnMisPlaylist.setContentAreaFilled(false);
		btnMisPlaylist.setBorderPainted(false);
		btnMisPlaylist.setBounds(16, 243, 152, 30);
		fondo.add(btnMisPlaylist);
		
		btnMeGusta = new JButton("Me gusta");
		btnMeGusta.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/icons8-me-gusta-24.png")));
		btnMeGusta.setOpaque(false);
		btnMeGusta.setForeground(Color.WHITE);
		btnMeGusta.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnMeGusta.setContentAreaFilled(false);
		btnMeGusta.setBorderPainted(false);
		btnMeGusta.setBounds(15, 284, 143, 30);
		fondo.add(btnMeGusta);
		
		// PRIMER PRINCIPAL
		
		panelTabla =  new JPanel();
		panelTabla.setBounds(195, 0, 835, 606);
		fondo.add(panelTabla);
		panelTabla.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel_titulo = new JPanel();
		panelTabla.add(panel_titulo, BorderLayout.NORTH);
		
		lblMegusta = new JLabel("Me Gusta");
		lblMegusta.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		panel_titulo.add(lblMegusta);
		
		JPanel panel_TablaTabla = new JPanel();
		panelTabla.add(panel_TablaTabla, BorderLayout.CENTER);
		
		scrollPaneMenu = new JScrollPane();
        Object[] cabecera= {"Nombre","Duracion","Fecha","Url"};
        Class<?>[] clases= {String.class,String.class, Date.class,String.class};
        Integer[] medidas= {100, 5, 5};
        mitabla = new Vista.Tabla(cabecera, clases, medidas, null,null,null);
        scrollPaneMenu.setViewportView(mitabla.tabla);
        panelTabla.add(scrollPaneMenu);

        // PANEL BUSCADOR
        panelBuscador = new JPanel();
        panelBuscador.setForeground(new Color(0, 0, 0));
        panelBuscador.setBackground(new Color(0, 0, 0));
		panelBuscador.setBounds(195, 0, 835, 606);
		fondo.add(panelBuscador);
		panelBuscador.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 108, 835, 498);
		panelBuscador.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panelBuscador.setVisible(false);
        
		scrollPaneBuscador = new JScrollPane();
        Object[] cabecera1= {"id","Nombre","Fecha"};
        Class<?>[] clases1= {Integer.class, String.class, Date.class};
        Integer[] medidas1= {100, 200, 150};
        mitablaBuscador = new Vista.Tabla(cabecera1, clases1, medidas1, null,null,null);
        scrollPaneBuscador.setViewportView(mitablaBuscador.tabla);
        panel.add(scrollPaneBuscador);
        
        lblBuscador = new JLabel("");
        lblBuscador.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/icons8-b\u00FAsqueda-30.png")));
        lblBuscador.setForeground(Color.WHITE);
        lblBuscador.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
        lblBuscador.setBounds(36, 38, 124, 30);
        panelBuscador.add(lblBuscador);
        
        txtBuqueda = new JTextField();
        txtBuqueda.setMargin(new Insets(2, 15, 2, 2));
        txtBuqueda.setBounds(new Rectangle(8, 0, 0, 0));
        txtBuqueda.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
        txtBuqueda.setBounds(80, 34, 702, 39);
        panelBuscador.add(txtBuqueda);
        txtBuqueda.setColumns(10);
        
       
       // mitabla.alinear('l', 0);
//        for (int i = 0; i < 100; i++) {
//            Object[] datos = { 23, "Manolo", new Date() };
//            mitabla1.modelo.addRow(datos);
//        }
      
		// PANEL CREAR PLAYLIST
        panelCrearTabla = new JPanel();
        panelCrearTabla.setBackground(Color.WHITE);
        panelCrearTabla.setBounds(192, 0, 838, 584);
        fondo.add(panelCrearTabla);
        panelCrearTabla.setLayout(null);
       
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(135, 88, 69, 14);
        panelCrearTabla.add(lblNombre);
        
        JLabel lblTitulo = new JLabel("Nueva playlist");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(345, 31, 147, 34);
        panelCrearTabla.add(lblTitulo);
        
        JLabel lblNTuPlaylist = new JLabel("Tu playlist");
        lblNTuPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNTuPlaylist.setBounds(484, 169, 69, 22);
        panelCrearTabla.add(lblNTuPlaylist);

        JLabel lblanyadecanciones = new JLabel("A\u00F1ade canciones a tu playlist");
        lblanyadecanciones.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblanyadecanciones.setBounds(135, 151, 180, 14);
        panelCrearTabla.add(lblanyadecanciones);
        
        textFieldNombrePlaylist = new JTextField();
        textFieldNombrePlaylist.setEditable(false);
        textFieldNombrePlaylist.setBounds(135, 108, 131, 22);
        panelCrearTabla.add(textFieldNombrePlaylist);
        textFieldNombrePlaylist.setColumns(10);
        
        textFieldBuscador = new JTextField();
        textFieldBuscador.setEditable(false);
        textFieldBuscador.setBounds(135, 171, 162, 22);
        panelCrearTabla.add(textFieldBuscador);
        textFieldBuscador.setColumns(10);
        
        scrollPane_TodasCanciones = new JScrollPane();
        scrollPane_TodasCanciones.setBounds(135, 204, 219, 151);
        panelCrearTabla.add(scrollPane_TodasCanciones);
        
        modeloTodasCanciones = new DefaultListModel<Cancion>();
        
        list_todasCanciones = new JList<Cancion>(modeloTodasCanciones);
        scrollPane_TodasCanciones.setViewportView(list_todasCanciones);
        
        scrollPane_CancionesPlaylist = new JScrollPane();
        scrollPane_CancionesPlaylist.setBounds(484, 204, 219, 151);
        panelCrearTabla.add(scrollPane_CancionesPlaylist);
        
        modeloCancionesElegidas = new DefaultListModel<Cancion>();
        
        list_CancionesPlaylist = new JList<Cancion>(modeloCancionesElegidas);
        scrollPane_CancionesPlaylist.setViewportView(list_CancionesPlaylist);
        
        btnbuscarCrearPlaylist = new JButton("\r\n");
        btnbuscarCrearPlaylist.setHideActionText(true);
        btnbuscarCrearPlaylist.setBackground(Color.WHITE);
        btnbuscarCrearPlaylist.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/lupa.png")));
        btnbuscarCrearPlaylist.setBounds(293, 170, 61, 24);
        panelCrearTabla.add(btnbuscarCrearPlaylist);
        
        btnAnyadir = new JButton("");
        btnAnyadir.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/anadir.png")));
        btnAnyadir.setBounds(396, 204, 46, 36);
        panelCrearTabla.add(btnAnyadir);
        
        btnBorrar = new JButton("");
        btnBorrar.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/eliminar.png")));
        btnBorrar.setBounds(396, 262, 46, 34);
        panelCrearTabla.add(btnBorrar);
        
        btnVaciar = new JButton("");
        btnVaciar.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/limpiar.png")));
        btnVaciar.setBounds(396, 319, 46, 36);
        panelCrearTabla.add(btnVaciar);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnGuardar.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/disquete (1).png")));
        btnGuardar.setBounds(293, 393, 103, 34);
        panelCrearTabla.add(btnGuardar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancelar.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/cancelar.png")));
        btnCancelar.setBounds(442, 393, 106, 34);
        panelCrearTabla.add(btnCancelar);
        
        lblAdvertencia = new JLabel("Introduce un nombre por favor.");
        lblAdvertencia.setVisible(false);
        lblAdvertencia.setForeground(Color.RED);
        lblAdvertencia.setIcon(new ImageIcon(AppUser.class.getResource("/Resources/info.png")));
        lblAdvertencia.setBounds(328, 438, 189, 14);
        panelCrearTabla.add(lblAdvertencia);
        
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
	
	public void createTable() {
        Object[] cabecera= {"Nombre","Duracion","Fecha","Url"};
        Class<?>[] clases= {String.class,String.class, Date.class,String.class};
        Integer[] medidas= {100, 5, 5};
        mitabla = new Vista.Tabla(cabecera, clases, medidas, null,null,null);
        scrollPaneMenu.setViewportView(mitabla.tabla);
        panelTabla.add(scrollPaneMenu);
        mitabla.alinear('c', 0);
        mitabla.alinear('c', 1);
        mitabla.alinear('c', 2);
        mitabla.alinear('c', 3);
        mitabla.alinearHeader('c', 0);
        mitabla.alinearHeader('c', 1);
        mitabla.alinearHeader('c', 2);
        mitabla.alinearHeader('c', 3);

        this.repaint();
		this.revalidate();
	}
	public void createTableBuscador() {
		Object[] cabecera= {"Nombre","Duracion","Fecha","Url"};
        Class<?>[] clases= {String.class,String.class, Date.class,String.class};
        Integer[] medidas= {100, 5, 5};
        mitablaBuscador = new Vista.Tabla(cabecera, clases, medidas, null,null,null);
        scrollPaneBuscador.setViewportView(mitablaBuscador.tabla);
        panel.add(scrollPaneBuscador);
        mitablaBuscador.alinear('c', 0);
        mitablaBuscador.alinear('c', 1);
        mitablaBuscador.alinear('c', 2);
        mitablaBuscador.alinear('c', 3);
        mitablaBuscador.alinearHeader('c', 0);
        mitablaBuscador.alinearHeader('c', 1);
        mitablaBuscador.alinearHeader('c', 2);
        mitablaBuscador.alinearHeader('c', 3);

        this.repaint();
		this.revalidate();
	}
}
