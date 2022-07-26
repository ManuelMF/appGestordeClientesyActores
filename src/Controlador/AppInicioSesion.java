package Controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Modelo.Album;
import Modelo.Cancion;
import Modelo.Cantante;
import Modelo.Playlist;
import Modelo.Usuario;
import Vista.FrameInicioSesion;

public class AppInicioSesion {
	
	static Scanner sc=new Scanner(System.in);	
	Vista.FrameInicioSesion VentanainicioSesion;
	
	public AppInicioSesion() {
		
		VentanainicioSesion = new FrameInicioSesion();
		
		VentanainicioSesion.txtCorreoNick.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quitarletras();
			}
		});	
		VentanainicioSesion.btnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicioSesion();
			}
		});	
		VentanainicioSesion.btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanainicioSesion.dispose();
				new Controlador.AppCrearCuenta();
				
			}
		});		
		VentanainicioSesion.btnEntrarInvitado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanainicioSesion.dispose();
				new Controlador.AppInvitado(Usuario.load("Invitado"));
			}
		});
		
		
		System.out.println("     Music top");
		System.out.println("Bienvenido a Music top");
		
		
		boolean exit=true;
		Cantante userCa=null;
		Usuario user=null;
		
		do {
			user = Registro();
			if (Cantante.load(user.getNick()) != null)  {
				userCa = Cantante.load(user.getNick());
				user=null;
			} 
			
			if (user!=null) {
				if (!user.getNick().equals("Invitado")) {
					do {
						
						MenuUsuario();
						
						switch (sc.nextLine()) {
						case "1": {
							
							Buscador(user);
							break;
						}
						
						case "2": {
							
							CancionesMeGusta(user);
							break;
						}
						case "3": {
							
							PlaylistMeGusta(user);
							break;
						}
							
						case "4": {
							
							AlbumesMeGusta(user);				
							break;
						}
						
						case "5":{
							
							CantantesMeGusta(user);
							break;
						}
						case "c":{
							addPlaylistUser(user);
							break;
						}
						case "m":{
							misPlaylist(user);
							break;
						}						
						case "z":{
	
							userCa = AjusteUsuario(user);
							if (userCa!=null) user = null;	
							break;
						}					
						}
						
					} while (user!=null);
					
					
				} else {
					MenuInvitado();
				}
				
			}
			while(userCa!=null) {
				user=Usuario.load(userCa.getNick());
				
				MenuCantante();
					
				switch (sc.nextLine()) {
					case "1": {
						
						Buscador(user);
						break;
					}
					case "2": {
						
						CancionesMeGusta(user);
						break;
					}
					case "3": {
						
						PlaylistMeGusta(user);
						break;
					}
					case "4": {
						
						AlbumesMeGusta(user);				
						break;
					}
					case "5":{
						
						CantantesMeGusta(user);
						break;
					}
					case "z":{
						
						AjusteCantante(user);
						break;
					}
					case "a":{
						
						AddCancion(userCa);
						break;
					}
					case "b":{
						
						addAlbum(userCa);
						break;
					}
					case "c":{
						
						addPlaylist(userCa);
						break;
					}
					case "d":{
						
						Discografia(userCa);
						break;
					}
				}
				
			}
		} while (exit==false);	
	}
	public void inicioSesion() {
		
		Cantante userCa = null; Usuario user = null; int x=0;
		
		String v = VentanainicioSesion.txtCorreoNick.getText();
		
		char[] arrayC = VentanainicioSesion.passwordField.getPassword();
		String pass = new String(arrayC);
		 
		if (Cantante.load(v)!=null) { userCa=Cantante.load(v);x=1;}
		else user = Usuario.load(v);
		if (user==null&&x==0) {
			
			VentanainicioSesion.lblFalloInicioSesion.setVisible(true);
			VentanainicioSesion.lblFalloInicioSesionContraIncorrecta.setVisible(false);
		} else {
			if (user==null&&pass.equals(userCa.getContrasenya())) {
				new Controlador.AppCantante (userCa);
				VentanainicioSesion.dispose();
				
			} else if (userCa==null && pass.equals(user.getContrasenya())) {
				new Controlador.AppUser(user);
				
				VentanainicioSesion.dispose();
			} else {
				VentanainicioSesion.lblFalloInicioSesionContraIncorrecta.setVisible(true);
				VentanainicioSesion.lblFalloInicioSesion.setVisible(false);
			}
		}
	}
	
	public void quitarletras() {
		if (VentanainicioSesion.txtCorreoNick.getText().equals("Correo, nick")) {
			VentanainicioSesion.txtCorreoNick.setText("");
			VentanainicioSesion.txtCorreoNick.setForeground(Color.BLACK);
		} 
	}
	
	public Usuario Registro() {
		
		String v = null;
		Usuario user = null;
		
		Cantante userCa=null;
		
		do {
			int x=1;String resp;
			do {
				x=1;
				System.out.println("Estas registrado");
				System.out.println("Si  /  No");
				resp = sc.nextLine();
			if (!resp.equals("Si")&&!resp.equals("si")&&!resp.equals("no")&&!resp.equals("No")) {
				System.out.println("Si o No!!"); x=0;
			} 
			}while (x==0);
			if (resp.equals("si")||resp.equals("Si") ) {
				System.out.println("Inicia Sesion         (v para volver atras)");
				x=0;
				do {
					System.out.println("Introduce tu nombre de usuario o el correo:");
					v=sc.nextLine();
				
					if (Cantante.load(v)!=null) { userCa=Cantante.load(v);x=1;}
					else user = Usuario.load(v);
					if (user==null&&x==0) System.out.println("Datos incorrectos");
				} while(user==null&&userCa==null&& !v.equals("v"));
				
				
				
				if (user!=null ) {
					x=0;
					
					do {
						System.out.println("Introduce la contraseña");
						v=sc.nextLine();
						if (user.loadContra(v)){ x=1; System.out.println("Te logueaste correctamente");}
						else if (!v.equals("v")) System.out.println("Contraseña incorrecta");
					} while(x==0 && !v.equals("v"));	
				}
				else if (userCa!=null) {
					do {x=0;
						user= Usuario.load(userCa.getNick());
						System.out.println("Introduce la contraseña");
						v=sc.nextLine();
						if (user.loadContra(v)){ x=1; System.out.println("Te logueaste correctamente"); user=null;}
						else if (!v.equals("v")) System.out.println("Contraseña incorrecta");
					} while(x==0 && !v.equals("v"));
				}
			} else if (resp.equals("no")||resp.equals("No") ) { 
				System.out.println("Deseas crearte una cuenta    (v para volver atras)");
				System.out.println("Si / No");
				v = sc.nextLine();
				if (v.equals("si")||v.equals("Si") ) {
					
					String nick = null;
					
					// nick
					do {
					System.out.println("Introduce tu nombre de usuario");
					nick = sc.nextLine();
					if (nick.length()<3) System.out.println("Nombre invalido");
					if (Usuario.load(nick)!=null) System.out.println("Este nombre de usuario no esta disponible");
					} while (Usuario.load(nick)!=null || nick.length()<3);
					
					// nom y apel
					String nombre,apellidos;
					do {
					System.out.println("Introduce tu nombre");
					nombre=sc.nextLine();
					} while (nick.length()<3);
					do {
						System.out.println("Introduce tus apellidos");
						apellidos=sc.nextLine();
					} while (nick.length()<3);
					SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
					boolean error=true;
					
					int dia=0,mes=0,año=0;
					SimpleDateFormat formatoaño = new SimpleDateFormat("yyyy");
					
//					fecha
					Date miFecha = null;
					Date Fecha18 = new Date();
					int diamin=1,diamax=31,mesmin=1,mesmax=12, añomin=Integer.parseInt(formatoaño.format(Fecha18)),añomax=1900;
					
					do {
						error=true;
						System.out.println("Introduce tu decha de nacimiento");
							System.out.println("Día en que naciste");
							dia=Integer.parseInt(sc.nextLine());
							
							System.out.println("Mes en que naciste");
							mes=Integer.parseInt(sc.nextLine());
							
							System.out.println("Año en que naciste");
							año=Integer.parseInt(sc.nextLine());
							
							if (dia<diamin||diamax<dia||mesmin>mes||mesmax<mes||añomin<año||añomax>año) {
								error = false;
								System.out.println("Edad erronea");
							}
							
						
					} while (!error);
					try {
						miFecha=formato1.parse(dia+"/"+mes+"/"+año);
					} catch (ParseException e) {
						System.out.println("Formato incorrecto");
					}
					String email;
					x=0;
					do {
						System.out.println("Introduce tu email");
						email=sc.nextLine();
						if (email.contains("@")) {
							if (email.contains(".")) x=1;
							else System.out.println("El correo tiene que tener .com .es ...");
						} else System.out.println("El correo debe contener @");
					} while(x==0);
					
					String contrasenya;
					String r; 
					x=0;
					do {
						do {
							x=0;
						System.out.println("Introduce una contraseña");
						contrasenya = sc.nextLine();
						if (contrasenya.length()<4) System.out.println("Contaseña invalida");
						} while(contrasenya.length()<4);
						do {
						System.out.println("Repite la contrasenya (v para poner otra contraseña)");
						r = sc.nextLine();
						if (r.equals("v")) x=1;
						} while (x==0 && !contrasenya.equals(r));
						
					} while(r.equals("v"));
					
					user = Usuario.create(nick, nombre, apellidos, miFecha, email, contrasenya);
					
				} else if (!v.equals("v")){
					System.out.println("Entrando como invitado"); 
					user = Usuario.load("Invitado");
					}
			}
			
		}while (v.equals("v"));
		
		if (user==null) return Usuario.load(userCa.getNick());
		else return user;
	}
	
	public void Buscador(Usuario user) {
		System.out.println("Buscar:");
		String busqueda = sc.nextLine();
		
		int x=1,can=0,art=0,alb=0;
		LinkedList<Cancion> listCan = Cancion.loadtodo(busqueda);
		if (!listCan.isEmpty()) {
			System.out.println("Canciones");
			for (Cancion c : listCan) {
				System.out.println(x++ +") "+c.getNombre()+" - "+c.getUrl());
				can++;
			}
		}
		
		LinkedList<Cantante> listartistas = Cantante.loadTodo(busqueda);
		if (listartistas != null) {
		if (!listartistas.isEmpty()) {
			System.out.println("Cantantes");
			for (Cantante c : listartistas) {
				System.out.println(x++ +") "+c.getNick());
				art++;
			}
		}}
		
		LinkedList<Album> listaAlbum = Album.load(null,busqueda);
		if (!listaAlbum.isEmpty()) {
			System.out.println("Albumes");
			for (Album a : listaAlbum) {
				System.out.println(x++ +") "+a.getNombre()+" - "+a.getFechaCreacion());
				alb++;
			}
		}
		
		LinkedList<Playlist> listaPlaylist = Playlist.load(null,busqueda,null);
		if (!listaPlaylist.isEmpty()) {
			System.out.println("Playlist");
			for (Playlist a : listaPlaylist) {
				System.out.println(x++ +") "+a.getNombre()+" - "+a.getFechaCreacion());
				
			}
		}
		int num = 0;
		System.out.println("-----------------------------");
		System.out.println("Pulsa enter para salir");
		String res2=sc.nextLine();
		if (res2.equals("")){
			
		} else {
			
			boolean errorc=false;
			int repite=0;
			do {
				errorc=false;
				try {
				if (repite==1) res2=sc.nextLine();
				
				num = Integer.parseInt(res2);
				if (num>x-1) throw new Exception();
				errorc = true;
				} catch (Exception e) {
					
					System.out.println("Error introduciendo un numero");
					repite=1;
				}
				
			}while (errorc==false);
		
		//hay cantantes
		int ir=0;
		
			if (num>(art+can+alb)) { num=num-art-can-alb;ir=4; }
			// el num es mas grande quue el de art can
			else if (num>(art+can)) { num=num-art-can; ir=1;}
			// es mas grande que can 
			else if (num>can) { num-=can; ir=2; }
			// es un can
			else { ir=3; }
			
		
		Album a = null; Cantante c = null; Cancion d = null;Playlist p = null;
		
		if (ir==1) a = listaAlbum.get(num-1);
		else if (ir==2) c = listartistas.get(num-1);
		else if (ir==3) d = listCan.get(num-1);
		else if (ir==4) p = listaPlaylist.get(num-1);
		
		boolean exit1 = true; 
		do {
			x=1;
			
			if (p!=null) {
				System.out.println(p.getNombre());
				LinkedList<Cancion> lista = p.CancionesPlaylist(null);
				for (Cancion can1 : lista) {
					System.out.println(x++ + ") "+can1.getNombre()+" - "+can1.getFechaPublicacion()+" - "+can1.getDuracion()+" --- "+can1.getUrl());
				}
				System.out.println("M) Marcar como me gusta");
				System.out.println("D) Borrar de me gusta");
				System.out.println("V) Volver al menu");
				String res= sc.nextLine();
				if (!res.equals("M") && !res.equals("m") && !res.equals("V") && !res.equals("v")&& !res.equals("D") && !res.equals("d")) {
					int xx = Integer.parseInt (res);
					d=lista.get(xx-1);
					p=null;
				}
				if (res.equals("M") || res.equals("m")) {
					if (user.mgPlaylist(p)) System.out.println("Playlist añadida correctamente");
					else System.out.println("Ya esta añadido");
				}
				if (res.equals("d") || res.equals("D")) {
					if (user.deleteMgPlaylist(p.getId())) System.out.println("Playlist borrada correctamente");
					else System.out.println("Fallo al borrar la playlist");
				}
				if (res.equals("V") || res.equals("v")) exit1=false;
			}
			
			if (a!=null) {
				System.out.println(a.getNombre());
				LinkedList<Cancion> lista = Album.loadSong(null, a.getNombre());
				for (Cancion can1 : lista) {
					System.out.println(x++ + ") "+can1.getNombre()+" - "+can1.getFechaPublicacion()+" - "+can1.getDuracion()+" --- "+can1.getUrl());
				}
				System.out.println("M) Marcar como me gusta");
				System.out.println("D) Borrar de me gusta");
				System.out.println("A) Añadir canciones a una playlist");
				System.out.println("V) Volver al menu");
				String res= sc.nextLine();
				
				if (!res.equals("M") && !res.equals("m") && !res.equals("A") && !res.equals("a") && !res.equals("V") && !res.equals("v")&& !res.equals("D") && !res.equals("d")) {
					int xx = Integer.parseInt (res);
					d=lista.get(xx-1);
					a=null;
				}
				if (res.equals("M") || res.equals("m")) {
					if (user.mgalbum(a)) System.out.println("Album añadido correctamente");
					else System.out.println("Ya esta añadido");
				}
				if (res.equals("d") || res.equals("D")) {
					if (user.deleteMgAlbum(a.getId())) System.out.println("Album borrado correctamente");
					else System.out.println("Fallo al borrar el album");
				}
				
				if (res.equals("a") || res.equals("A")) {
					System.out.println("A que Playlist quieres añadir las canciones");
					LinkedList<Playlist> lista1 = Playlist.load(null, null, user.getId());
					
					int x1=1;
					for (Playlist p1 : lista1) {
						System.out.println(x1+++") "+p1.getNombre()+" - "+p1.getFechaCreacion());
					}
					System.out.println("-----------------------");
					System.out.println("pulsa enter para salir");
					boolean error=true;
					int res1 = 0;
					String resString = sc.nextLine();
					if (resString.equals("")) {}
					else {
					
						do {
							try {
								res1= Integer.parseInt(resString);
								error=false;
							}catch (Exception e) {
								System.out.println("Introduce un numero");
							}
						} while (error);
						
						Playlist p1 = lista1.get(res1-1);
						for (Cancion c1 : lista) c1.updatePlaylist(p1.getId());
					}
				}
				if (res.equals("V") || res.equals("v")) exit1=false;
				
			}
			if (c!=null) {
		
				LinkedList<Cancion> lista = c.BuscarCancion(null);
				x=1;
				System.out.println(c.getNick());
				
				for (Cancion c1: lista) {
					System.out.println(x++ +") "+c1.getNombre()+" - "+c1.getFechaPublicacion()+" - "+ c1.getDuracion()+" --- "+c1.getUrl());
				}
				System.out.println("S) Seguir al cantante");
				System.out.println("D) Dejar de seguir");
				System.out.println("V) Volver");
				String res = sc.nextLine();
				
				if (!res.equals("S") && !res.equals("s") && !res.equals("V") && !res.equals("v")&& !res.equals("d") && !res.equals("D")) {
					int xx = Integer.parseInt (res);
					d=lista.get(xx-1);
					c=null;
				}
				if (res.equals("M") || res.equals("m")) {
					if (user.mgCantante(c)) System.out.println("Cantante añadido correctamente");
					else System.out.println("Ya Sigues a este cantante");
				}
				if (res.equals("D") || res.equals("d")) {
					if (user.deleteMgCantante(c.getId())) System.out.println("Cantante borrado correctamente");
					else System.out.println("Fallo borrando cantante");;
				}
				if (res.equals("S") || res.equals("s")) {
					if (user.mgCantante(c)) System.out.println("Cantante añadido correctamente");
					else System.out.println("Fallo borrando cantante");;
				}
				if (res.equals("v") || res.equals("V")) {
					c=null;exit1=false;
				}
			}
			
			if (d!=null) {
				System.out.println(d.getNombre()+" - "+d.getFechaPublicacion()+" - "+ d.getDuracion()+" --- "+d.getUrl());
				System.out.println("1) Marcar como me gusta");
				System.out.println("2) Borrar de me gusta");
				System.out.println("3) Añadir cancion a una playlist");
				System.out.println("4) Volver al menu");
				boolean error = true;
				int res=0;
				do {
					try {
						res= Integer.parseInt(sc.nextLine());
						error=false;
					}catch (Exception e) {
						System.out.println("Introduce un numero");
					}
				} while (error);
				
				if (res==1) {
					if (user.mgsong(d)) System.out.println("Cancion añadida correctamente");
					else System.out.println("Ya esta añadida");
				}
				if (res==2) {
					if (user.deleteMgCancion(d.getId())) System.out.println("Cancion borrada correctamente");
					else System.out.println("Fallo borrando cancion");
				}
				
				if (res==3) {
					System.out.println("A que Playlist quieres añadir la canciones");
					LinkedList<Playlist> lista1 = Playlist.load(null, null, user.getId());
					
					int x1=1;
					for (Playlist p1 : lista1) {
						System.out.println(x1+++") "+p1.getNombre()+" - "+p1.getFechaCreacion());
					}
					System.out.println("-----------------------");
					System.out.println("pulsa enter para salir");
					boolean error1=true;
					int res1 = 0;
					String resString = sc.nextLine();
					if (resString.equals("")) {}
					else {
					
						do {
							try {
								res1= Integer.parseInt(resString);
								error1=false;
							}catch (Exception e) {
								System.out.println("Introduce un numero");
							}
						} while (error1);
						
						Playlist p1 = lista1.get(res1-1);
						if(d.updatePlaylist(p1.getId())) System.out.println("Añadido correctamente");
					}
					
				}
				
				if (res==4) exit1=false;
			}
			
		} while (exit1);
		
	}
	}
		
	public Cancion BuscadorCanciones(Usuario user) {
		
		String busqueda = sc.nextLine();
		
		int x=1;
		LinkedList<Cancion> listCan = Cancion.loadtodo(busqueda);
		if (!listCan.isEmpty()) {
			System.out.println("Canciones");
			for (Cancion c : listCan) {
				System.out.println(x++ +") "+c.getNombre()+" - "+c.getUrl());
				
		}
		System.out.println("-----------------------------");
		System.out.println("Pulsa enter para salir");
		String res2=sc.nextLine();
		int num=0;
		if (res2.equals("")){
			return null;
		} else {
			boolean errorc=false;
			int repite=0;
			do {
				errorc=false;
				try {
				if (repite==1) res2=sc.nextLine();
				
				num = Integer.parseInt(res2);
				if (num>x-1) throw new Exception();
				errorc = true;
				} catch (Exception e) {
					
					System.out.println("Error introduciendo un numero");
					repite=1;
				}
				
			}while (errorc==false);
			
	
			Cancion d = null;
	
			d = listCan.get(num-1);
			return d;
		}
		}
		return null;
	}
	
	public void CancionesMeGusta(Usuario user) {
		
		boolean exit1=false;Cancion d = null;;
		do {
			exit1=false;
			LinkedList<Cancion> lista = user.listaMgSong();
			int x=1;
			
			System.out.println("Canciones que me gustan");
			for(Cancion c : lista) {
				System.out.println(x++ +") "+c.getNombre()+" - "+c.getFechaPublicacion()+ " - "+c.getDuracion() + " --- "+c.getUrl());
			}
			System.out.println("V) Volver");
			String res = sc.nextLine();
			
			if (!res.equals("V") && !res.equals("v")) {
				int xx = Integer.parseInt (res);
				d=lista.get(xx-1);
			}
			if (res.equals("V") || res.equals("v")) { exit1=true; } 
			
			while (d!=null) {
				
					System.out.println(d.getNombre()+" - "+d.getFechaPublicacion()+" - "+ d.getDuracion()+" --- "+d.getUrl());
					System.out.println("1) Marcar como me gusta");
					System.out.println("2) Borrar de me gusta");
					System.out.println("3) Añadir cancion a una playlist");
					System.out.println("4) Volver al menu");
					int res1= Integer.parseInt(sc.nextLine());
					if (res1==1) {
						if (user.mgsong(d)) System.out.println("Cancion añadida correctamente");
						else System.out.println("Ya esta añadida");
					}
					if (res1==2) {
						if (user.deleteMgCancion(d.getId())) System.out.println("Cancion borrada correctamente");
						else System.out.println("Fallo borrando cancion");
					}
					if (res1==3) {
						System.out.println("A que Playlist quieres añadir la cancion");
						LinkedList<Playlist> lista1 = Playlist.load(null, null, user.getId());
						
						int x1=1;
						for (Playlist p1 : lista1) {
							System.out.println(x1+++") "+p1.getNombre()+" - "+p1.getFechaCreacion());
						}
						System.out.println("-----------------------");
						System.out.println("pulsa enter para salir");
						boolean error1=true;
						int res11 = 0;
						String resString = sc.nextLine();
						if (resString.equals("")) {}
						else {
							
							do {
								try {
									res11= Integer.parseInt(resString);
									error1=false;
								}catch (Exception e) {
									System.out.println("Introduce un numero");
								}
							} while (error1);
							
							Playlist p1 = lista1.get(res11-1);
							if(d.updatePlaylist(p1.getId())) System.out.println("Añadido correctamente");
						}
						
					}
					if (res1==4) d=null;
				
			}
			
		} while(exit1==false); 
		
	}

	public void PlaylistMeGusta(Usuario user) {
		
		LinkedList<Playlist> lista = user.listaMgPlaylist();
		
		int x=1;
		System.out.println("Playlist");
		for (Playlist p : lista) {
			System.out.println(x+++") "+p.getNombre());
		}
		System.out.println("-----------------------");
		System.out.println("pulsa enter para salir");
		boolean error=true;
		int res = 0;
		String resString = sc.nextLine();
		if (resString.equals("")) {}
		else {
			do {
				try {
					res= Integer.parseInt(resString);
					error=false;
				}catch (Exception e) {
					System.out.println("Introduce un numero");
				}
			} while (error);
			
			Playlist p = lista.get(res-1);
			
			do {
			System.out.println(p.getNombre() + " - " + p.getFechaCreacion());
			LinkedList<Cancion> listac = p.CancionesPlaylist(null);
			x=1;
			
				for (Cancion c : listac) {
					System.out.println(x+++") " + c.getNombre() + " - " + c.getFechaPublicacion() + " - " + c.getUrl());
				}
				System.out.println("M) Marcar como me gusta");
				System.out.println("B) Borrar de me gusta");
				System.out.println("P) Borrar playlist");
				System.out.println("V) Volver al menu");
				String resStr = sc.nextLine();
				Cancion d = null;
				if (resStr.equals("m") || resStr.equals("M")) {
					if (user.mgPlaylist(p)) System.out.println("Añadido a me gusta");
					else System.out.println("Ya esta añadida");
				}
				if (resStr.equals("b") || resStr.equals("B")) {
					if (user.deleteMgPlaylist(p.getId())) System.out.println("Borrado de me gusta");
					else System.out.println("No hay nada que borrar");
				}
				if (resStr.equals("p") || resStr.equals("P")) {
					if (p.delete()) System.out.println("Playlist borrada");
					else System.out.println("error borrando playlist");
				}
				if (resStr.equals("v") || resStr.equals("V")) {
					
				}
				if (!resStr.equals("m") && !resStr.equals("M") && !resStr.equals("B") && !resStr.equals("b") && !resStr.equals("p") && !resStr.equals("P") && !resStr.equals("v") && !resStr.equals("V")) {
				res= Integer.parseInt(sc.nextLine());
					
				d = listac.get(res-1);
				}
				while(d!=null) {
					System.out.println(d.getNombre()+" - "+d.getFechaPublicacion()+" - "+ d.getDuracion()+" --- "+d.getUrl());
					System.out.println("1) Marcar como me gusta");
					System.out.println("2) Borrar de me gusta");
					System.out.println("3) Añadir cancion a una playlist");
					System.out.println("4) Volver al menu");
					boolean error1 = true;
					int res1=0;
					do {
						try {
							res1= Integer.parseInt(sc.nextLine());
							error1=false;
						}catch (Exception e) {
							System.out.println("Introduce un numero");
						}
					} while (error1);
					
					if (res1==1) {
						if (user.mgsong(d)) System.out.println("Cancion añadida correctamente");
						else System.out.println("Ya esta añadida");
					}
					if (res1==2) {
						if (user.deleteMgCancion(d.getId())) System.out.println("Cancion borrada correctamente");
						else System.out.println("Fallo borrando cancion");
					}
					
					if (res1==3) {
						System.out.println("A que Playlist quieres añadir la cancione");
						LinkedList<Playlist> lista1 = Playlist.load(null, null, user.getId());
						
						int x1=1;
						for (Playlist p1 : lista1) {
							System.out.println(x1+++") "+p1.getNombre()+" - "+p1.getFechaCreacion());
						}
						System.out.println("-----------------------");
						System.out.println("pulsa enter para salir");
						boolean error11=true;
						int res11 = 0;
						String resString1 = sc.nextLine();
						if (resString1.equals("")) {}
						else {
						
							do {
								try {
									res11= Integer.parseInt(sc.nextLine());
									error11=false;
								}catch (Exception e) {
									System.out.println("Introduce un numero");
								}
							} while (error11);
							
							Playlist p1 = lista1.get(res11-1);
							if(d.updatePlaylist(p1.getId())) System.out.println("Añadido correctamente");
						}
						
					}
					if (res1==4) d=null;
				}
			} while (p!=null);
		}
	}

	public void AlbumesMeGusta(Usuario user) {
		
		boolean exit1=false;Album d = null;;
		do {
			exit1=false;
			LinkedList<Album> lista = user.listaMgAlbum();
			int x=1;
			
			System.out.println("Albumes que me gustan");
			for(Album c : lista) {
				System.out.println(x++ +") "+c.getNombre()+" - "+c.getFechaCreacion());
			}
			System.out.println("V) Volver");
			String res = sc.nextLine();
			
			if (!res.equals("V") && !res.equals("v")) {
				int xx = Integer.parseInt (res);
				d=lista.get(xx-1);
			}
			if (res.equals("V") || res.equals("v")) { exit1=true; } 
			
			Cancion c = null;
			while (d!=null) {
				x=1;
				System.out.println(d.getNombre() );
				LinkedList<Cancion> lista1 = Album.loadSong(null, d.getNombre());
				for (Cancion can1 : lista1) {
					System.out.println(x++ + ") "+can1.getNombre()+" - "+can1.getFechaPublicacion()+" - "+can1.getDuracion()+" --- "+can1.getUrl());
				}
				System.out.println("M) Marcar como me gusta");
				System.out.println("D) Borrar de me gusta");
				System.out.println("A) Añadir canciones a una playlist");
				System.out.println("V) Volver al menu");
				String res1= sc.nextLine();
				
				if (!res1.equals("M") && !res1.equals("m") && !res1.equals("A") && !res1.equals("a") && !res1.equals("V") && !res1.equals("v")&& !res1.equals("D") && !res1.equals("d")) {
					int xx = Integer.parseInt (res);
					c=lista1.get(xx-1);
					d=null;
				}
				
				if (res1.equals("a") || res1.equals("A")) {
					System.out.println("A que Playlist quieres añadir las canciones");
					LinkedList<Playlist> lista11 = Playlist.load(null, null, user.getId());
					
					int x1=1;
					for (Playlist p1 : lista11) {
						System.out.println(x1+++") "+p1.getNombre()+" - "+p1.getFechaCreacion());
					}
					System.out.println("-----------------------");
					System.out.println("pulsa enter para salir");
					boolean error=true;
					int res11 = 0;
					String resString = sc.nextLine();
					if (resString.equals("")) {}
					else {
					
						do {
							try {
								res11= Integer.parseInt(resString);
								error=false;
							}catch (Exception e) {
								System.out.println("Introduce un numero");
							}
						} while (error);
						
						Playlist p1 = lista11.get(res11-1);
						
						for (Cancion c1 : lista1) { c1.updatePlaylist(p1.getId());}
					}
				}
				if (res.equals("V") || res.equals("v")) exit1=false;
				
				if (res1.equals("M") || res1.equals("m")) {
					if (user.mgalbum(d)) System.out.println("Album añadido correctamente");
					else System.out.println("Ya esta añadido");
				}
				if (res.equals("d") || res.equals("D")) {
					if (user.deleteMgAlbum(d.getId())) System.out.println("Album borrado correctamente");
					else System.out.println("Fallo al borrar el album");
				}									
				if (res1.equals("V") || res1.equals("v")) d=null;
				}
				while (c!=null) {
					System.out.println(c.getNombre()+" - "+c.getFechaPublicacion()+" - "+ c.getDuracion()+" --- "+c.getUrl());
					System.out.println("1) Marcar como me gusta");
					System.out.println("2) Borrar de me gusta");
					System.out.println("3) Añadir cancion a una playlist");
					System.out.println("4) Volver al menu");
					int res1= Integer.parseInt(sc.nextLine());
					if (res1==1) {
						if (user.mgsong(c)) System.out.println("Cancion añadida correctamente");
						else System.out.println("Ya esta añadida");
					}
					if (res1==2){
					if (user.deleteMgCancion(c.getId())) System.out.println("Cancion borrada correctamente");
					else System.out.println("Fallo borrando cancion");
					}
					if (res1==4) c=null;
				
				}
			
											
			} while(exit1==false); 
						
	}

	public void CantantesMeGusta(Usuario user) {
		
		Cantante can = null;Cancion d = null;
		boolean exit1 = false;
		do {
			LinkedList<Cantante> lista = user.listaMgCantantes();
			int x=1;
			
			
			System.out.println("Cantantes");
			for (Cantante c : lista) {
				System.out.println(x+++") "+c.getNick());
			}
			System.out.println("V) volver al menu");
			
			
			String res=sc.nextLine();
			
			if (res.equals("V") || res.equals("v")) exit1=true;
			else {
				int resI = Integer.parseInt(res);
				can = lista.get(resI-1);
			}
			while(can!=null) {
				LinkedList<Cancion> lista1 = can.BuscarCancion(null);
				x=1;
				System.out.println(can.getNick());
				
				for (Cancion c1: lista1) {
					System.out.println(x++ +") "+c1.getNombre()+" - "+c1.getFechaPublicacion()+" - "+ c1.getDuracion()+" --- "+c1.getUrl());
				}
				System.out.println("S) Seguir al cantante");
				System.out.println("D) Dejar de seguir");
				System.out.println("V) Volver");
				String res1 = sc.nextLine();
				
				if (!res1.equals("s") && !res1.equals("S") && !res1.equals("V") && !res1.equals("v")&& !res1.equals("d") && !res1.equals("D")) {
					int xx = Integer.parseInt (res1);
					d=lista1.get(xx-1);
					//can=null;
				}
				if (res1.equals("M") || res1.equals("m")) {
					if (user.mgCantante(can)) System.out.println("Cantante añadido correctamente");
					else System.out.println("Ya Sigues a este cantante");
				}
				if (res1.equals("V") || res1.equals("v")) {
					can=null;
				}
				if (res.equals("D") || res.equals("d")) {
					if (user.deleteMgCantante(can.getId())) System.out.println("Cantante borrado correctamente");
					else System.out.println("Fallo borrando cantante");;
				}
				while (d!=null) {
					
					System.out.println(d.getNombre()+" - "+d.getFechaPublicacion()+" - "+ d.getDuracion()+" --- "+d.getUrl());
					System.out.println("1) Marcar como me gusta");
					System.out.println("2) Borrar de me gusta");
					System.out.println("3) Añadir cancion a una playlist");
					System.out.println("4) Volver al menu");
					int res11= Integer.parseInt(sc.nextLine());
					if (res11==1) {
						if (user.mgsong(d)) System.out.println("Cancion añadida correctamente");
						else System.out.println("Ya esta añadida");
					}
					if (res11==2){
					if (user.deleteMgCancion(d.getId())) System.out.println("Cancion borrada correctamente");
					else System.out.println("Fallo borrando cancion");
					}
					if (res11==3) {
						System.out.println("A que Playlist quieres añadir la cancion");
						LinkedList<Playlist> lista11 = Playlist.load(null, null, user.getId());
						
						int x1=1;
						for (Playlist p1 : lista11) {
							System.out.println(x1+++") "+p1.getNombre()+" - "+p1.getFechaCreacion());
						}
						System.out.println("-----------------------");
						System.out.println("pulsa enter para salir");
						boolean error1=true;
						int res111 = 0;
						String resString = sc.nextLine();
						if (resString.equals("")) {}
						else {
							
							do {
								try {
									res111= Integer.parseInt(resString);
									error1=false;
								}catch (Exception e) {
									System.out.println("Introduce un numero");
								}
							} while (error1);
							
							Playlist p1 = lista11.get(res111-1);
							if(d.updatePlaylist(p1.getId())) System.out.println("Añadido correctamente");
						}
						
					}
					if (res11==4) d=null;
				
				}
			}
		
		} while(exit1==false);
	}

	public Cantante AjusteUsuario(Usuario user) {

		boolean exit1=false;
		do {
		System.out.println("-------------------------");
		System.out.println(user.getNick());
		System.out.println(user.getEmail());
		System.out.println(user.getNombre()+" "+user.getApellidos());
		System.out.println("-------------------------");
		System.out.println("1) Cambiar nick");
		System.out.println("2) Cambiar contraseña");
		System.out.println("3) Hazte cantante");
		System.out.println("4) Salir");
		System.out.println("-------------------------");
		int res = Integer.parseInt(sc.nextLine());
		
		if(res==1) {
			System.out.println("Introduce la contraseña");
			if (user.loadContra(sc.nextLine())) {
				int y=1;
				do {
					System.out.println("Introduce tu nuevo nick");
					String n = sc.nextLine();
					if (user.updatenick(n)) {System.out.println("Nick cambiado a "+n); y=2; user=Usuario.load(n);}
					else System.out.println("Este nick ya esta en uso, prueba otro");
				}while(y==1);
			} else { System.out.println("Contraseña incorrecta  (pulsa alguna tecla)");sc.nextLine();}
		}
		if(res==2) {
			System.out.println("Introduce tu contraseña");
			if (user.loadContra(sc.nextLine())) {
				int y=1;
				do {
					System.out.println("Introduce tu nueva Contraseña");
					String n = sc.nextLine();
					if (user.updateContra(n)) {System.out.println("Tu contraseña a sido cambiada correctamente"); y=2; }
					
				}while(y==1);
			} else {System.out.println("Contraseña incorrecta (pulsa alguna tecla)");sc.nextLine();}
		}
		if(res==3) {
			if (user.pasarCantante()!=null) {
			System.out.println("Ahora eres cantante");
			System.out.println("Siendo cantante podrás subir canciones, albumes y playlist");
			System.out.println("------------------------------------------------------------");
			System.out.println("Pulsa alguna tecla");
			sc.nextLine();
			Cantante userCa= Cantante.load(user.getNick());
			user=null;
			exit1=true;
			return userCa;
			} else System.out.println("Ya eres cantante   (pulsa)");
			
		}
		if (res==4) exit1=true;
		
		
		} while (exit1==false);
		return null;
	}

	public void AjusteCantante(Usuario user) {

		boolean exit1=false;
		do {
		System.out.println("-------------------------");
		System.out.println(user.getNick());
		System.out.println(user.getEmail());
		System.out.println(user.getNombre()+" "+user.getApellidos());
		System.out.println("-------------------------");
		System.out.println("1) Cambiar nick");
		System.out.println("2) Cambiar contraseña");
		System.out.println("3) Salir");
		System.out.println("-------------------------");
		int res = Integer.parseInt(sc.nextLine());
		
		if(res==1) {
			System.out.println("Introduce la contraseña");
			if (user.loadContra(sc.nextLine())) {
				int y=1;
				do {
					System.out.println("Introduce tu nuevo nick");
					String n = sc.nextLine();
					if (user.updatenick(n)) {System.out.println("Nick cambiado a "+n); y=2; user=Usuario.load(n);}
					else System.out.println("Este nick ya esta en uso, prueba otro");
				}while(y==1);
			} else { System.out.println("Contraseña incorrecta  (pulsa alguna tecla)");sc.nextLine();}
		}
		if(res==2) {
			System.out.println("Introduce tu contraseña");
			if (user.loadContra(sc.nextLine())) {
				int y=1;
				do {
					System.out.println("Introduce tu nueva Contraseña");
					String n = sc.nextLine();
					if (user.updateContra(n)) {System.out.println("Tu contraseña a sido cambiada correctamente"); y=2; }
					
				}while(y==1);
			} else {System.out.println("Contraseña incorrecta (pulsa alguna tecla)");sc.nextLine();}
		}
		   
		if (res==3) exit1=true;
		
		
		} while (exit1==false);
	}
	
	public void AddCancion(Cantante userCa) {

		System.out.println("Nombre de la cancion");
		String nombre= sc.nextLine();
		String duracion = null;
		boolean error= false;
		do {error=false;
			try {
				System.out.println("Introduce la duracion de la cancion (mm:ss)");
				duracion = sc.nextLine();
				if (duracion.length()!=5 || !duracion.contains(":")) throw new Exception();
			} catch(Exception e) {
				System.out.println("Introduce el siguiente formato (mm:ss)"); error = true;
			}
		}while(error);
		
		System.out.println("Introduce el URL de la cancion");
		String URL = sc.nextLine();
		userCa.anyadirCancion(nombre, duracion, null, URL);
		
	}
	
	public void addAlbum(Cantante userCa) {
		
		System.out.println("Nombre del Album");
		Album a = userCa.anyadirAlbum(sc.nextLine(), new Date());
		String res=null;
		do {
			System.out.println("Quieres añadir una cancion al album (si/no)");
			res=sc.nextLine();
			if (res.equals("si")) {
				int id =a.getId();
				
				System.out.println("1) Añadir nueva cancion");
				System.out.println("2) Añadir cancion existente");
				int r = Integer.parseInt(sc.nextLine());
				if (r==1) {
					
					System.out.println("Nombre de la cancion");
					String nombre= sc.nextLine();
					String duracion = null;
					boolean error= false;
					do {error=false;
						try {
							System.out.println("Introduce la duracion de la cancion (mm:ss)");
							duracion = sc.nextLine();
							if (duracion.length()!=5 || !duracion.contains(":")) throw new Exception();
						} catch(Exception e) {
							System.out.println("Introduce el siguiente formato (mm:ss)"); error = true;
						}
					}while(error);
					
					System.out.println("Introduce el URL de la cancion");
					String URL = sc.nextLine();
					userCa.anyadirCancion(nombre, duracion, id, URL);
					
				} else {
					int x=0; Cancion c=null;
					do {
						System.out.println("Introduce el nombre de la cancion");
						String n = sc.nextLine();
						if(userCa.BuscarCancion(n).isEmpty()) {
								System.out.println("Error añadiendo una cancion");
								
						} else {
							c = userCa.BuscarCancion(n).getFirst();
							if (c==null) { System.out.println("Introduce un nombre valido");x=1;}
							else x=2;
						}
					}while(x==1);
					
					if (x==2) c.updateA(id,userCa.getId());
				}
				
			}	 
		}while(!res.equals("no"));
	}
	
	public void addPlaylist(Cantante userCa) {
		
		System.out.println("Introduce el nombre de la playlist");
		Playlist p = userCa.anyadirPlaylist(sc.nextLine(), Usuario.load(userCa.getNick()).getId());
		
		String res=null;
		do {
			System.out.println("Quieres añadir una cancion al playlist (si/no)");
			res=sc.nextLine();
			if (res.equals("si")) {
			
				
				boolean error=true;int r = 0;
				do {
					try {
						System.out.println("1) Añadir nueva cancion");
						System.out.println("2) Añadir cancion existente");
						r = Integer.parseInt(sc.nextLine());
						error=false;
					}catch (Exception e) {
						System.out.println("Introduce un numero");
						error=true;
					}
				} while(error);
				
				if (r==1) {
					
					System.out.println("Nombre de la cancion");
					String nombre= sc.nextLine();
					String duracion = null;
					error= false;
					do {error=false;
						try {
							System.out.println("Introduce la duracion de la cancion (mm:ss)");
							duracion = sc.nextLine();
							if (duracion.length()!=5 || !duracion.contains(":")) throw new Exception();
						} catch(Exception e) {
							System.out.println("Introduce el siguiente formato (mm:ss)"); error = true;
						}
					}while(error);
					
					System.out.println("Introduce el URL de la cancion");
					String URL = sc.nextLine();
					
					Cancion song = userCa.anyadirCancion(nombre, duracion, null, URL);
					p.addSong(song);
					
				} else {
					Cancion c=null;
					
					System.out.println("Introduce el nombre de la cancion");
					AppInicioSesion con = new AppInicioSesion();
					c = con.BuscadorCanciones(Usuario.load(userCa.getNick()));
					 p.addSong(c);
				}
				
			}	 
		}while(!res.equals("no"));
		
	}
	
	public void addPlaylistUser(Usuario user) {
		
		System.out.println("Introduce el nombre de la playlist");
		Playlist p = user.anyadirPlaylist(sc.nextLine());
		
		String res=null;
		do {
			System.out.println("Quieres añadir una cancion al playlist (si/no)");
			res=sc.nextLine();
			if (res.equals("si")) {		
				Cancion c=null;	
				System.out.println("Introduce el nombre de la cancion");
				AppInicioSesion con = new AppInicioSesion();
				c = con.BuscadorCanciones(user);
				p.addSong(c);
			}	 
		}while(!res.equals("no"));
		
	}

	public void misPlaylist(Usuario user) {
		int x=1;
		LinkedList<Playlist> listaPlaylist = Playlist.load(null, null, user.getId());
		if (!listaPlaylist.isEmpty()) {
			System.out.println("Playlist");
			for (Playlist a : listaPlaylist) {
				System.out.println(x++ +") "+a.getNombre()+" - "+a.getFechaCreacion());
			}
		}
		System.out.println("-----------------------------");
		System.out.println("Pulsa enter para salir");
		String res2=sc.nextLine();
		int num = 0;
		if (res2.equals("")) {} else {
			boolean error=false;
			int repite=0;
			do {
				error=false;
				try {
				if (repite==1) res2=sc.nextLine();
				
				num = Integer.parseInt(res2);
				if (num>x-1) throw new Exception();
				error = true;
				} catch (Exception e) {
					
					System.out.println("Error introduciendo un numero");
					repite=1;
				}
				
			}while (error==false);
		}
		boolean exit1=true;
		Playlist p = listaPlaylist.get(num-1);
		Cancion d = null;
		do {
			x=1;
			if (p!=null) {
				System.out.println(p.getNombre());
				LinkedList<Cancion> lista = p.CancionesPlaylist(null);
				for (Cancion can1 : lista) {
					System.out.println(x++ + ") "+can1.getNombre()+" - "+can1.getFechaPublicacion()+" - "+can1.getDuracion()+" --- "+can1.getUrl());
				}
				System.out.println("M) Marcar como me gusta");
				System.out.println("D) Borrar de me gusta");
				System.out.println("V) Volver al menu");
				String res= sc.nextLine();
				if (!res.equals("M") && !res.equals("m") && !res.equals("V") && !res.equals("v")&& !res.equals("D") && !res.equals("d")) {
					int xx = Integer.parseInt (res);
					d=lista.get(xx-1);
					p=null;
				}
				if (res.equals("M") || res.equals("m")) {
					if (user.mgPlaylist(p)) System.out.println("Playlist añadida correctamente");
					else System.out.println("Ya esta añadido");
				}
				if (res.equals("d") || res.equals("D")) {
					if (user.deleteMgPlaylist(p.getId())) System.out.println("Playlist borrada correctamente");
					else System.out.println("Fallo al borrar la playlist");
				}
				if (res.equals("V") || res.equals("v")) exit1=false;
			}
			if (d!=null) {
				System.out.println(d.getNombre()+" - "+d.getFechaPublicacion()+" - "+ d.getDuracion()+" --- "+d.getUrl());
				System.out.println("1) Marcar como me gusta");
				System.out.println("2) Borrar de me gusta");
				System.out.println("3) Añadir cancion a una playlist");
				System.out.println("4) Volver al menu");
				boolean error = true;
				int res=0;
				do {
					try {
						res= Integer.parseInt(sc.nextLine());
						error=false;
					}catch (Exception e) {
						System.out.println("Introduce un numero");
					}
				} while (error);
				
				if (res==1) {
					if (user.mgsong(d)) System.out.println("Cancion añadida correctamente");
					else System.out.println("Ya esta añadida");
				}
				if (res==2) {
					if (user.deleteMgCancion(d.getId())) System.out.println("Cancion borrada correctamente");
					else System.out.println("Fallo borrando cancion");
				}
				
				if (res==3) {
					System.out.println("A que Playlist quieres añadir la canciones");
					LinkedList<Playlist> lista1 = Playlist.load(null, null, user.getId());
					
					int x1=1;
					for (Playlist p1 : lista1) {
						System.out.println(x1+++") "+p1.getNombre()+" - "+p1.getFechaCreacion());
					}
					System.out.println("-----------------------");
					System.out.println("pulsa enter para salir");
					boolean error1=true;
					int res1 = 0;
					String resString = sc.nextLine();
					if (resString.equals("")) {}
					else {
					
						do {
							try {
								res1= Integer.parseInt(resString);
								error1=false;
							}catch (Exception e) {
								System.out.println("Introduce un numero");
							}
						} while (error1);
						
						Playlist p1 = lista1.get(res1-1);
						if(d.updatePlaylist(p1.getId())) System.out.println("Añadido correctamente");
					}
					
				}
				
				if (res==4) exit1=false;
			}
		} while (exit1);
	}
	
	public void Discografia(Cantante userCa) {

		System.out.println(userCa.getNick());
		Usuario user = Usuario.load(userCa.getNick());
		
		int x=1,can=0,alb=0;
		LinkedList<Cancion> listCan = Cancion.load(userCa.getId(),null);
		if (!listCan.isEmpty()) {
			System.out.println("Canciones");
			for (Cancion c : listCan) {
				System.out.println(x++ +") "+c.getNombre()+" - "+c.getUrl());
				can++;
			}
		}
		
		LinkedList<Album> listaAlbum = Album.load(userCa.getId(),null);
		if (!listaAlbum.isEmpty()) {
			System.out.println("Albumes");
			for (Album a : listaAlbum) {
				System.out.println(x++ +") "+a.getNombre()+" - "+a.getFechaCreacion());
				alb++;
			}
		}
		LinkedList<Playlist> listaPlaylist = Playlist.load(null, null, Usuario.load(userCa.getNick()).getId());
		if (!listaPlaylist.isEmpty()) {
			System.out.println("Playlist");
			for (Playlist a : listaPlaylist) {
				System.out.println(x++ +") "+a.getNombre()+" - "+a.getFechaCreacion());
				
			}
		}
		
		System.out.println("-----------------------------");
		System.out.println("Pulsa enter para salir");
		String res2=sc.nextLine();
		if (res2.equals("")) {} else {
		boolean error=false;
		int num = 0,repite=0;
		do {
			error=false;
			try {
			if (repite==1) res2=sc.nextLine();
			num = Integer.parseInt(res2);
			if (num>x-1) throw new Exception();
			error = true;
			} catch (Exception e) {
				System.out.println("error añadiendo un numero");
				repite=1;
			}
		}while (error==false);
		//hay cantantes
		int ir=0;
		
		if (num>(can+alb)) { num=num-can-alb;ir=4; }
		// el num es mas grande quue el de art can
		else if (num>(can)) { num=num-can; ir=1;}
		// es mas grande que can 
		else if (num>can) { num-=can; ir=2; }
		// es un can
		else { ir=3; }
		
		Album a = null; Cancion d = null;Playlist p = null;
	
		if (ir==1) a = listaAlbum.get(num-1);
		else if (ir==3) d = listCan.get(num-1);
		else if (ir==4) p = listaPlaylist.get(num-1);
		
		boolean exit1 = true; 
		do {
			x=1;
			if (p!=null) {
				System.out.println(p.getNombre());
				LinkedList<Cancion> lista = p.CancionesPlaylist(null);
				for (Cancion can1 : lista) {
					System.out.println(x++ + ") "+can1.getNombre()+" - "+can1.getFechaPublicacion()+" - "+can1.getDuracion()+" --- "+can1.getUrl());
				}
				System.out.println("M) Marcar como me gusta");
				System.out.println("D) Borrar de me gusta");
				System.out.println("V) Volver al menu");
				String res= sc.nextLine();
				if (!res.equals("M") && !res.equals("m") && !res.equals("V") && !res.equals("v")&& !res.equals("D") && !res.equals("d")) {
					int xx = Integer.parseInt (res);
					d=lista.get(xx-1);
					p=null;
				}
				if (res.equals("M") || res.equals("m")) {
					if (user.mgPlaylist(p)) System.out.println("Playlist añadida correctamente");
					else System.out.println("Ya esta añadido");
				}
				if (res.equals("d") || res.equals("D")) {
					if (user.deleteMgPlaylist(p.getId())) System.out.println("Playlist borrada correctamente");
					else System.out.println("Fallo al borrar la playlist");
				}
				if (res.equals("V") || res.equals("v")) exit1=false;
			}
			if (a!=null) {
				System.out.println(a.getNombre());
				LinkedList<Cancion> lista = Album.loadSong(null, a.getNombre());
				for (Cancion can1 : lista) {
					System.out.println(x++ + ") "+can1.getNombre()+" - "+can1.getFechaPublicacion()+" - "+can1.getDuracion()+" --- "+can1.getUrl());
				}
				System.out.println("M) Marcar como me gusta");
				System.out.println("D) Borrar de me gusta");
				System.out.println("P) Añadir canciones a una playlist");
				System.out.println("A) Añadir cancion al album");
				System.out.println("B) Borrar album");
				System.out.println("V) Volver al menu");
				String res= sc.nextLine();
				
				if (!res.equals("M") && !res.equals("m") && !res.equals("a") && !res.equals("A") && !res.equals("B") && !res.equals("b") && !res.equals("A") && !res.equals("a") && !res.equals("V") && !res.equals("v")&& !res.equals("D") && !res.equals("d")) {
					int xx = Integer.parseInt (res);
					d=lista.get(xx-1);
					a=null;
				}
				if (res.equals("M") || res.equals("m")) {
					if (user.mgalbum(a)) System.out.println("Album añadido correctamente");
					else System.out.println("Ya esta añadido");
				}
				if (res.equals("d") || res.equals("D")) {
					if (user.deleteMgAlbum(a.getId())) System.out.println("Album borrado correctamente");
					else System.out.println("Fallo al borrar el album");
				}
				if (res.equals("B") || res.equals("b")) {
					a.delete();
					a=null; exit1=false;
				}
				if (res.equals("p") || res.equals("P")) {
					System.out.println("A que Playlist quieres añadir las canciones");
					LinkedList<Playlist> lista1 = Playlist.load(null, null, user.getId());
					
					int x1=1;
					for (Playlist p1 : lista1) {
						System.out.println(x1+++") "+p1.getNombre()+" - "+p1.getFechaCreacion());
					}
					System.out.println("-----------------------");
					System.out.println("pulsa enter para salir");
					boolean error1=true;
					int res1 = 0;
					String resString = sc.nextLine();
					if (resString.equals("")) {}
					else {
					
						do {
							try {
								res1= Integer.parseInt(resString);
								error1=false;
							}catch (Exception e) {
								System.out.println("Introduce un numero");
							}
						} while (error1);
						
						Playlist p1 = lista1.get(res1-1);
						for (Cancion c1 : lista) c1.updatePlaylist(p1.getId());
					}
				}
				if (res.equals("a") || res.equals("A")) {
					
					String res1=null;
					do {
						System.out.println("Quieres añadir una cancion al album (si/no)");
						res1=sc.nextLine();
						if (res1.equals("si")) {
							int id =a.getId();
							
							System.out.println("1) Añadir nueva cancion");
							System.out.println("2) Añadir cancion existente");
							int r = Integer.parseInt(sc.nextLine());
							if (r==1) {
								
								System.out.println("Nombre de la cancion");
								String nombre= sc.nextLine();
								String duracion = null;
								boolean error1= false;
								do {error1=false;
									try {
										System.out.println("Introduce la duracion de la cancion (mm:ss)");
										duracion = sc.nextLine();
										if (duracion.length()!=5 || !duracion.contains(":")) throw new Exception();
									} catch(Exception e) {
										System.out.println("Introduce el siguiente formato (mm:ss)"); error1 = true;
									}
								}while(error1);
								
								System.out.println("Introduce el URL de la cancion");
								String URL = sc.nextLine();
								userCa.anyadirCancion(nombre, duracion, id, URL);
								
							} else {
								int x1=0; Cancion c1=null;
								do {
									System.out.println("Introduce el nombre de la cancion");
									String n = sc.nextLine();
									if(userCa.BuscarCancion(n).isEmpty()) {
											System.out.println("Error añadiendo una cancion");
											
									} else {
										c1 = userCa.BuscarCancion(n).getFirst();
										if (c1==null) { System.out.println("Introduce un nombre valido");x1=1;}
									}
								}while(x1==1);
								
								if (x1==1) c1.updateA(id,userCa.getId());
							}
							
						}	 
					}while(!res1.equals("no"));
					break;
				}
				if (res.equals("V") || res.equals("v")) exit1=false;
			}
			
			if (d!=null) {
				System.out.println(d.getNombre()+" - "+d.getFechaPublicacion()+" - "+ d.getDuracion()+" --- "+d.getUrl());
				System.out.println("1) Marcar como me gusta");
				System.out.println("2) Borrar de me gusta");
				System.out.println("3) Añadir cancion a una playlist");
				System.out.println("4) Volver al menu");
				boolean error1 = true;
				int res=0;
				do {
					try {
						res= Integer.parseInt(sc.nextLine());
						error1=false;
					}catch (Exception e) {
						System.out.println("Introduce un numero");
					}
				} while (error1);
				
				if (res==1) {
					if (user.mgsong(d)) System.out.println("Cancion añadida correctamente");
					else System.out.println("Ya esta añadida");
				}
				if (res==2) {
					if (user.deleteMgCancion(d.getId())) System.out.println("Cancion borrada correctamente");
					else System.out.println("Fallo borrando cancion");
				}
				
				if (res==3) {
					System.out.println("A que Playlist quieres añadir la canciones");
					LinkedList<Playlist> lista1 = Playlist.load(null, null, user.getId());
					
					int x1=1;
					for (Playlist p1 : lista1) {
						System.out.println(x1+++") "+p1.getNombre()+" - "+p1.getFechaCreacion());
					}
					System.out.println("-----------------------");
					System.out.println("pulsa enter para salir");
					boolean error11=true;
					int res1 = 0;
					String resString = sc.nextLine();
					if (resString.equals("")) {}
					else {
					
						do {
							try {
								res1= Integer.parseInt(resString);
								error11=false;
							}catch (Exception e) {
								System.out.println("Introduce un numero");
							}
						} while (error11);
						
						Playlist p1 = lista1.get(res1-1);
						if(d.updatePlaylist(p1.getId())) System.out.println("Añadido correctamente");
					}
					
				}
				
				if (res==4) exit1=false;
			}
			
		} while (exit1);
		
	}
	}
	
	public void MenuInvitado() {
		
		boolean exitInvitado = false; 
		AppInicioSesion con = new AppInicioSesion();
		
		do {
		System.out.println("Menu de invitado");
		System.out.println("B) Buscar");
		System.out.println("S) Salir");
		String resinv = sc.nextLine();
		if (resinv.equals("b") || resinv.equals("B")) {
			
			con.BuscadorInvitado(Usuario.load("Invitado"));
		} else if (resinv.equals("s") || resinv.equals("S")) {
			exitInvitado = true;
		}
		
		} while (exitInvitado == false);
	}

	public void BuscadorInvitado(Usuario user) {
		System.out.println("Buscar:");
		String busqueda = sc.nextLine();
		
		int x=1,can=0,art=0,alb=0;
		LinkedList<Cancion> listCan = Cancion.loadtodo(busqueda);
		if (!listCan.isEmpty()) {
			System.out.println("Canciones");
			for (Cancion c : listCan) {
				System.out.println(x++ +") "+c.getNombre()+" - "+c.getUrl());
				can++;
			}
		}
		
		LinkedList<Cantante> listartistas = Cantante.loadTodo(busqueda);
		if (listartistas != null) {
		if (!listartistas.isEmpty()) {
			System.out.println("Cantantes");
			for (Cantante c : listartistas) {
				System.out.println(x++ +") "+c.getNick());
				art++;
			}
		}}
		
		LinkedList<Album> listaAlbum = Album.load(null,busqueda);
		if (!listaAlbum.isEmpty()) {
			System.out.println("Albumes");
			for (Album a : listaAlbum) {
				System.out.println(x++ +") "+a.getNombre()+" - "+a.getFechaCreacion());
				alb++;
			}
		}
		
		LinkedList<Playlist> listaPlaylist = Playlist.load(null,busqueda,null);
		if (!listaPlaylist.isEmpty()) {
			System.out.println("Playlist");
			for (Playlist a : listaPlaylist) {
				System.out.println(x++ +") "+a.getNombre()+" - "+a.getFechaCreacion());
				
			}
		}
		
		System.out.println("-----------------------------");
		System.out.println("Pulsa enter para salir");
		String res2=sc.nextLine();
		if (res2.equals("")){
			
		} else {
		int num = Integer.parseInt(res2);
		//hay cantantes
		int ir=0;
		
			if (num>(art+can+alb)) { num=num-art-can-alb;ir=4; }
			// el num es mas grande quue el de art can
			else if (num>(art+can)) { num=num-art-can; ir=1;}
			// es mas grande que can 
			else if (num>can) { num-=can; ir=2; }
			// es un can
			else { ir=3; }
			
		
		Album a = null; Cantante c = null; Cancion d = null;Playlist p = null;
		
		if (ir==1) a = listaAlbum.get(num-1);
		else if (ir==2) c = listartistas.get(num-1);
		else if (ir==3) d = listCan.get(num-1);
		else if (ir==4) p = listaPlaylist.get(num-1);
		
		boolean exit1 = true; 
		do {
			x=1;
			
			if (p!=null) {
				System.out.println(p.getNombre());
				LinkedList<Cancion> lista = p.CancionesPlaylist(null);
				for (Cancion can1 : lista) {
					System.out.println(x++ + ") "+can1.getNombre()+" - "+can1.getFechaPublicacion()+" - "+can1.getDuracion()+" --- "+can1.getUrl());
				}
				
				System.out.println("V) Volver al menu");
				String res= sc.nextLine();
				if (!res.equals("M") && !res.equals("m") && !res.equals("V") && !res.equals("v")&& !res.equals("D") && !res.equals("d")) {
					int xx = Integer.parseInt (res);
					d=lista.get(xx-1);
					p=null;
				}
				
				if (res.equals("V") || res.equals("v")) exit1=false;
			}
			
			if (a!=null) {
				System.out.println(a.getNombre());
				LinkedList<Cancion> lista = Album.loadSong(null, a.getNombre());
				for (Cancion can1 : lista) {
					System.out.println(x++ + ") "+can1.getNombre()+" - "+can1.getFechaPublicacion()+" - "+can1.getDuracion()+" --- "+can1.getUrl());
				}
				
				System.out.println("V) Volver al menu");
				String res= sc.nextLine();
				
				if (!res.equals("M") && !res.equals("m") && !res.equals("A") && !res.equals("a") && !res.equals("V") && !res.equals("v")&& !res.equals("D") && !res.equals("d")) {
					int xx = Integer.parseInt (res);
					d=lista.get(xx-1);
					a=null;
				}
				if (res.equals("V") || res.equals("v")) exit1=false;
				
			}
			if (c!=null) {
		
				LinkedList<Cancion> lista = c.BuscarCancion(null);
				x=1;
				System.out.println(c.getNick());
				
				for (Cancion c1: lista) {
					System.out.println(x++ +") "+c1.getNombre()+" - "+c1.getFechaPublicacion()+" - "+ c1.getDuracion()+" --- "+c1.getUrl());
				}
				
				System.out.println("V) Volver");
				String res = sc.nextLine();
				
				if (!res.equals("S") && !res.equals("s") && !res.equals("V") && !res.equals("v")&& !res.equals("d") && !res.equals("D")) {
					int xx = Integer.parseInt (res);
					d=lista.get(xx-1);
					c=null;
				}
				
				if (res.equals("v") || res.equals("V")) {
					c=null;exit1=false;
				}
			}
			
			if (d!=null) {
				System.out.println(d.getNombre()+" - "+d.getFechaPublicacion()+" - "+ d.getDuracion()+" --- "+d.getUrl());
				
				System.out.println("4) Volver al menu");
				boolean error = true;
				int res=0;
				do {
					try {
						res= Integer.parseInt(sc.nextLine());
						error=false;
					}catch (Exception e) {
						System.out.println("Introduce un numero");
					}
				} while (error);
				
				if (res==4) exit1=false;
			}
			
		} while (exit1);
		
		}
	}	

	public void MenuUsuario() {
		
		System.out.println("Menu usuario");
		System.out.println("1) Buscar");
		System.out.println("2) Canciones que me gustan");
		System.out.println("3) Playlists que me gustan");
		System.out.println("4) Albumes que me gustan");
		System.out.println("5) Cantantes que me gustan");
		System.out.println("C) Crear playlist");
		System.out.println("M) Mis playlist");
		System.out.println("Z) Ajustes del usuario");
	}

	public void MenuCantante() {
		
		System.out.println("Menu Cantante");
		System.out.println("1) Buscar");
		System.out.println("2) Canciones que me gustan");
		System.out.println("3) Playlists que me gustan");
		System.out.println("4) Albumes que me gustan");
		System.out.println("5) Cantantes que me gustan");
		System.out.println("----------------------------");
		System.out.println("A) Añadir cancion");
		System.out.println("B) Crear album");
		System.out.println("C) Crear una playlist");
		System.out.println("D) Discografia");
		System.out.println("Z) Ajustes del usuario");
	}
}
