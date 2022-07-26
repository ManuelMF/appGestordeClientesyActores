package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JScrollPane;

import Modelo.Album;
import Modelo.Cancion;
import Modelo.Cantante;
import Modelo.Playlist;
import Modelo.Usuario;
import Vista.Tabla;

public class AppUser {
	
	static Scanner sc=new Scanner(System.in);
	Vista.AppUser VentanaUser;
	String busqueda;
	public static Integer IdUser;
	
	public AppUser(Usuario user) {
		VentanaUser = new Vista.AppUser();
		
		LinkedList<Cancion> lista = user.listaMgSong();
		
		reyenarJlist("");
		
		IdUser = user.getId();
		
        VentanaUser.btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		guardarPlaylist(user);
        	}
        });
        
        VentanaUser.btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VentanaUser.panelBuscador.setVisible(false);
				VentanaUser.panelTabla.setVisible(true);
				VentanaUser.panelCrearTabla.setVisible(false);
        	}
        });
        
		VentanaUser.btnBorrar.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
        		for (Cancion i : (ArrayList<Cancion>) VentanaUser.list_CancionesPlaylist.getSelectedValuesList() ) {
        			VentanaUser.modeloCancionesElegidas.removeElement(i);
        		}
        	}
        });
		
		VentanaUser.btnVaciar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		VentanaUser.modeloCancionesElegidas.removeAllElements();
	        	}
	        });
		
		 VentanaUser.list_CancionesPlaylist.addMouseListener(new MouseAdapter() {
	        	public void mouseClicked(MouseEvent e) {
	        		VentanaUser.list_todasCanciones.setSelectedValue(null, true);
	        	}
	        });
		
		VentanaUser.list_todasCanciones.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		VentanaUser.list_CancionesPlaylist.setSelectedValue(null, true);
        	}
        });
		
		VentanaUser.btnAnyadir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		anyadirCancionALaPlaylist();
        	}
        });
		
		VentanaUser.textFieldBuscador.addKeyListener(new KeyAdapter() {
	        	public void keyPressed(KeyEvent e) {
	        		if (e.getKeyCode()==10) {
	        			String busqueda = VentanaUser.textFieldBuscador.getText();
	        			reyenarJlist(busqueda);
	        		}
	        	}
	        });
		
		VentanaUser.btnbuscarCrearPlaylist.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		String busqueda = VentanaUser.textFieldBuscador.getText();
        			reyenarJlist(busqueda);
	        	}
	        });
		
		VentanaUser.btnCrearPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaUser.panelBuscador.setVisible(false);
				VentanaUser.panelTabla.setVisible(false);
				VentanaUser.panelCrearTabla.setVisible(true);
				VentanaUser.textFieldNombrePlaylist.setEditable(true);
				VentanaUser.textFieldBuscador.setEditable(true);
			}
		});
		
		 VentanaUser.txtBuqueda.addKeyListener(new KeyAdapter() {
	        	public void keyPressed(KeyEvent e) {
	        		KeyListener(e);
	        	}
	        });
		
		
		VentanaUser.createTable();
		for (Cancion c : lista) {
			Object[] datos = { c.getNombre(),c.getDuracion(),c.getFechaPublicacion() ,c.getUrl()};
			Vista.AppUser.mitabla.modelo.addRow(datos);
		}
		
		
		VentanaUser.btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaUser.panelBuscador.setVisible(true);
				VentanaUser.panelTabla.setVisible(false);
				VentanaUser.panelCrearTabla.setVisible(false);
				Vista.AppUser.mitablaBuscador.vaciar();
				
				VentanaUser.createTableBuscador();
				
				busqueda = VentanaUser.txtBuqueda.getText();
				
				int x = 0;
				System.out.println(busqueda);
				LinkedList<Cancion> listCan = Cancion.loadtodo(busqueda);
				if (!listCan.isEmpty()) {
					Object[] encabezado = { "Canciones",null,null,null };
					Object[] encabezadonulo = { null,null,null,null };
					
					Vista.AppUser.mitablaBuscador.modelo.addRow(encabezado);
					//Vista.AppUser.mitablaBuscador.modelo.addRow(encabezadonulo);
					
					for (Cancion c : listCan) {
						Object[] datos = { c.getNombre(), c.getDuracion(), c.getFechaPublicacion(), c.getUrl() };
						Vista.AppUser.mitablaBuscador.modelo.addRow(datos);
					}
				}
				
				LinkedList<Cantante> listartistas = Cantante.loadTodo(busqueda);
				if (listartistas != null) {
				if (!listartistas.isEmpty()) {

					Object[] encabezado = { "Cantantes",null,null,null };
					Object[] encabezadonulo = { null,null,null,null };
					
					if (!listCan.isEmpty()) Vista.AppUser.mitablaBuscador.modelo.addRow(encabezadonulo);
					Vista.AppUser.mitablaBuscador.modelo.addRow(encabezado);
					
					for (Cantante c : listartistas) {
						Object[] datos = { c.getNombre(), null,null,null };
						Vista.AppUser.mitablaBuscador.modelo.addRow(datos);
					}					
				}}
				
				LinkedList<Album> listaAlbum = Album.load(null,busqueda);
				if (!listaAlbum.isEmpty()) {
					
					Object[] encabezado = { "Albumes",null,null,null };
					Object[] encabezadonulo = { null,null,null,null };
					
					if (!listCan.isEmpty() || listartistas != null) Vista.AppUser.mitablaBuscador.modelo.addRow(encabezadonulo);
					Vista.AppUser.mitablaBuscador.modelo.addRow(encabezado);
					
					for (Album a : listaAlbum) {
						Object[] datos = { a.getNombre(), null,a.getFechaCreacion(),null };
						Vista.AppUser.mitablaBuscador.modelo.addRow(datos);
					}
				}
				
				LinkedList<Playlist> listaPlaylist = Playlist.load(null,busqueda,null);
				if (!listaPlaylist.isEmpty()) {

					Object[] encabezado = { "Playlist",null,null,null };
					Object[] encabezadonulo = { null,null,null,null };
					
					if (!listCan.isEmpty() || listartistas != null || !listaAlbum.isEmpty()) Vista.AppUser.mitablaBuscador.modelo.addRow(encabezadonulo);
					Vista.AppUser.mitablaBuscador.modelo.addRow(encabezado);
					
					for (Playlist a : listaPlaylist) {
						Object[] datos = { a.getNombre(), null,a.getFechaCreacion(),null };
						Vista.AppUser.mitablaBuscador.modelo.addRow(datos);
					}
				}
			}
			
			
		});
		
		VentanaUser.btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaUser.panelBuscador.setVisible(false);
				VentanaUser.panelCrearTabla.setVisible(false);
				VentanaUser.panelTabla.setVisible(true);
				
				VentanaUser.lblMegusta.setText("Me gusta");
				LinkedList<Cancion> lista = user.listaMgSong();
				//VentanaUser.mitabla.vaciar();
				VentanaUser.createTable();
				for (Cancion c : lista) {
					Object[] datos = { c.getNombre(),c.getDuracion(),c.getFechaPublicacion() ,c.getUrl()};
					Vista.AppUser.mitabla.modelo.addRow(datos);
				}
			}
		});
		
		VentanaUser.btnAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaUser.panelBuscador.setVisible(false);
				VentanaUser.panelCrearTabla.setVisible(false);
				VentanaUser.panelTabla.setVisible(true);
				
				VentanaUser.lblMegusta.setText("Albumes");
				Vista.AppUser.mitabla.vaciar();
				LinkedList<Album> lista = user.listaMgAlbum();
				VentanaUser.createTable();
				Vista.AppUser.mitabla.hideColumn(1);
				Vista.AppUser.mitabla.hideColumn(2);
				for (Album p : lista) {
					Object[] datos = { p.getNombre(),null, p.getFechaCreacion(),null};
					Vista.AppUser.mitabla.modelo.addRow(datos);
				}
			}
		});
		
		VentanaUser.btnMisPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaUser.panelBuscador.setVisible(false);
				VentanaUser.panelCrearTabla.setVisible(false);
				VentanaUser.panelTabla.setVisible(true);
				
				VentanaUser.lblMegusta.setText("Mis Playlist");
				LinkedList<Playlist> listaMisPlaylist = Modelo.Playlist.load(null, null, user.getId());
				LinkedList<Playlist> lista = user.listaMgPlaylist();
				
				//VentanaUser.mitabla.vaciar();
				VentanaUser.createTable();
				Vista.AppUser.mitabla.hideColumn(1);
				Vista.AppUser.mitabla.hideColumn(2);
				for (Playlist p : listaMisPlaylist) {
					Object[] datos = { p.getNombre(),null, p.getFechaCreacion(),null};
					Vista.AppUser.mitabla.modelo.addRow(datos);
				}
				for (Playlist p : lista) {
					Object[] datos = { p.getNombre(),null, p.getFechaCreacion(),null};
					Vista.AppUser.mitabla.modelo.addRow(datos);
				}
				// cuando haga click en una playlist se me cambiara el texto del titulo al nombre de la playlis (lo mimo en album)y me saldran las canciones donde le podre  dar click y hacer cosas
				
			}
		});
		
		VentanaUser.btnMeGusta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaUser.panelBuscador.setVisible(false);
				VentanaUser.panelCrearTabla.setVisible(false);
				VentanaUser.panelTabla.setVisible(true);
				
				VentanaUser.lblMegusta.setText("Me gusta");
				LinkedList<Cancion> lista = user.listaMgSong();
				//VentanaUser.mitabla.vaciar();
				VentanaUser.createTable();
				for (Cancion c : lista) {
					Object[] datos = { c.getNombre(),c.getDuracion(),c.getFechaPublicacion() ,c.getUrl()};
					Vista.AppUser.mitabla.modelo.addRow(datos);
				}
			}
		});
		
	}
	
	public  void KeyListener(KeyEvent e) {
		if((e.getKeyCode())==10) {
			//busqueda = VentanaUser.txtBuqueda.getText();
			Vista.AppUser.mitablaBuscador.vaciar();
			
			VentanaUser.createTableBuscador();
			
			busqueda = VentanaUser.txtBuqueda.getText();
			
			int x = 0;
			System.out.println(busqueda);
			LinkedList<Cancion> listCan = Cancion.loadtodo(busqueda);
			if (!listCan.isEmpty()) {
				Object[] encabezado = { "Canciones",null,null,null };
				Object[] encabezadonulo = { null,null,null,null };
				
				Vista.AppUser.mitablaBuscador.modelo.addRow(encabezado);
				//Vista.AppUser.mitablaBuscador.modelo.addRow(encabezadonulo);
				
				for (Cancion c : listCan) {
					Object[] datos = { c.getNombre(), c.getDuracion(), c.getFechaPublicacion(), c.getUrl() };
					Vista.AppUser.mitablaBuscador.modelo.addRow(datos);
				}
			}
			
			LinkedList<Cantante> listartistas = Cantante.loadTodo(busqueda);
			if (listartistas != null) {
			if (!listartistas.isEmpty()) {

				Object[] encabezado = { "Cantantes",null,null,null };
				Object[] encabezadonulo = { null,null,null,null };
				
				if (!listCan.isEmpty()) Vista.AppUser.mitablaBuscador.modelo.addRow(encabezadonulo);
				Vista.AppUser.mitablaBuscador.modelo.addRow(encabezado);
				
				for (Cantante c : listartistas) {
					Object[] datos = { c.getNombre(), null,null,null };
					Vista.AppUser.mitablaBuscador.modelo.addRow(datos);
				}					
			}}
			
			LinkedList<Album> listaAlbum = Album.load(null,busqueda);
			if (!listaAlbum.isEmpty()) {
				
				Object[] encabezado = { "Albumes",null,null,null };
				Object[] encabezadonulo = { null,null,null,null };
				
				if (!listCan.isEmpty() || listartistas != null) Vista.AppUser.mitablaBuscador.modelo.addRow(encabezadonulo);
				Vista.AppUser.mitablaBuscador.modelo.addRow(encabezado);
				
				for (Album a : listaAlbum) {
					Object[] datos = { a.getNombre(), null,a.getFechaCreacion(),null };
					Vista.AppUser.mitablaBuscador.modelo.addRow(datos);
				}
			}
			
			LinkedList<Playlist> listaPlaylist = Playlist.load(null,busqueda,null);
			if (!listaPlaylist.isEmpty()) {

				Object[] encabezado = { "Playlist",null,null,null };
				Object[] encabezadonulo = { null,null,null,null };
				
				if (!listCan.isEmpty() || listartistas != null || !listaAlbum.isEmpty()) Vista.AppUser.mitablaBuscador.modelo.addRow(encabezadonulo);
				Vista.AppUser.mitablaBuscador.modelo.addRow(encabezado);
				
				for (Playlist a : listaPlaylist) {
					Object[] datos = { a.getNombre(), null,a.getFechaCreacion(),null };
					Vista.AppUser.mitablaBuscador.modelo.addRow(datos);
				}
			}
		}
	}
	
	public void reyenarJlist(String busqueda) {
		
		
		VentanaUser.modeloTodasCanciones.removeAllElements();
		
		LinkedList<Cancion> listCan = Cancion.loadtodo(busqueda);
		if (!listCan.isEmpty()) {
			for (Cancion c : listCan) {
				VentanaUser.modeloTodasCanciones.addElement(c);
			}
		}
		
		if(!busqueda.equals("")) {
			
			LinkedList<Cantante> listartistas = Cantante.loadTodo(busqueda);
			if (listartistas!= null) {
			if (!listartistas.isEmpty()) {
				
				for (Cantante c : listartistas) {
					LinkedList<Cancion> listCanciCan = Cancion.load(c.getId(),null);
					for (Cancion cc : listCanciCan) {
						boolean esta = false;

						for (int x=0 ;  x<VentanaUser.modeloTodasCanciones.getSize() ; x++) {
							if (VentanaUser.modeloTodasCanciones.getElementAt(x).getNombre().equals(cc.getNombre())) esta=true;
						}
						if (esta==false) VentanaUser.modeloTodasCanciones.addElement(cc);
					}
				}					
			}
			}
		}	
	}
	
	@SuppressWarnings("unchecked")
	public void anyadirCancionALaPlaylist() {
		for (Cancion c : (ArrayList<Cancion>)VentanaUser.list_todasCanciones.getSelectedValuesList()) {
			boolean esta = false;
			for (int x=0 ;  x<VentanaUser.modeloCancionesElegidas.getSize() ; x++) {
				if (VentanaUser.modeloCancionesElegidas.getElementAt(x).getNombre().equals(c.getNombre())) esta=true;
			}
			if (esta==false) VentanaUser.modeloCancionesElegidas.addElement(c);
			
		}
		
	}
	
	private void guardarPlaylist(Usuario user) {
		
		if (VentanaUser.textFieldNombrePlaylist.getText().equals("")) VentanaUser.lblAdvertencia.setVisible(true);
		else {
		Modelo.Playlist.Create(VentanaUser.textFieldNombrePlaylist.getText(), user.getId());
		VentanaUser.panelBuscador.setVisible(false);
		VentanaUser.panelTabla.setVisible(true);
		VentanaUser.panelCrearTabla.setVisible(false);
		}
	}
}
