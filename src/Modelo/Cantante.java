package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Cantante {
	private int id;
	private String nick;
	private String nombre;
	private String apellidos;
	private String email;
	private String contrasenya;
	static Scanner sc=new Scanner(System.in);
	
		public Cantante(int id,String nick, String nom, String apell, String email, String contra) {
			this.id=id;
			this.nick=nick;
			this.nombre=nom; 
			this.apellidos=apell;
			this.email=email;
			this.contrasenya=contra;
		}

		public static Cantante load(String logueo) {
			
			LinkedList<Cantante> lista = new LinkedList<Cantante>();
			
			Conexion.open();
			
			PreparedStatement stm;
			
			ResultSet res;
			
			String select; 
			
			if (logueo.contains("@")) {
				select = "select * from usuarios inner join cantantes on can_codusu=usu_codusu  where usu_email like ?";
				
			} else {
				select = "select * from usuarios inner join cantantes on can_codusu=usu_codusu  where usu_nick like ?";
			}
			
			try {
				stm=Conexion.con.prepareStatement(select);
				stm.setString(1, logueo);
				
				res=stm.executeQuery();
				while (res.next()) {
					lista.add(new Cantante (res.getInt("can_codcan"), 
										  res.getString("usu_nick"), 
										  res.getString("usu_nombre"),  
										  res.getString("usu_apellidos"),  
										  res.getString("usu_email"),  
										  res.getString("usu_contra") 
										  ));
				}
				
			}catch (SQLException e) {
				
			}
			if (lista.size()==0) return null;
			else if (lista.size()==1) return lista.getFirst();
			else {
				int x=1,num = 0; boolean error = true;
				do {
					try {
						// por si el correo tiene mas de una cuenta enlazada
						System.out.println("A que cuenta quieres entrar");
						for (Cantante u:lista) {
							System.out.println(x+": Nick: "+u.getNick()+" Nombre: "+u.getNombre()+" "+u.getApellidos());
							x++;
						}
						num= Integer.parseInt(sc.nextLine()); 
						
						if (num==0) { throw new RuntimeException();}
						
						error=false;
						
					} catch (RuntimeException e1) {
						System.out.println("Caracter invalido");
					}
				} while (error);
			
			return lista.get(num-1);
			}
		}
		
		public static LinkedList<Cantante> loadTodo(String logueo) {
			
			LinkedList<Cantante> lista = new LinkedList<Cantante>();
			
			Conexion.open();
			
			PreparedStatement stm;
			
			ResultSet res;
			
			String select; 
			
			
				select = "select * from usuarios inner join cantantes on can_codusu=usu_codusu where usu_nick like ?";
			
			
			try {
				stm=Conexion.con.prepareStatement(select);
				stm.setString(1, logueo+"%");
				
				res=stm.executeQuery();
				while (res.next()) {
					lista.add(new Cantante (res.getInt("can_codcan"), 
										  res.getString("usu_nick"), 
										  res.getString("usu_nombre"),  
										  res.getString("usu_apellidos"),  
										  res.getString("usu_email"),  
										  res.getString("usu_contra") 
										  ));
				}
				
			}catch (SQLException e) {
				System.out.println("Fallo cargando usuario");
			}
			if (lista.size()==0) return null;
			else return lista;
			
		}
		
		
		
		public Cancion anyadirCancion(String nombrec, String duracion,Integer idalbum, String url) {
			
			
			return Cancion.create(nombrec,duracion,this.getId(),idalbum, url);
			
		}
		
		public Album anyadirAlbum(String nombre, Date FechaCr) {
			
			
			return Album.create(this.getId(),nombre,FechaCr);
			
		}
		
		public Playlist anyadirPlaylist(String nombre,int iduser) {
			
			
			return Playlist.Create(nombre, iduser);
			
		}
		
		public LinkedList<Cancion> BuscarCancion(String nombre) {
			
			LinkedList<Cancion> lista = Cancion.load(this.getId(),nombre);
			
			return lista;
		}
		
		public LinkedList<Album> BuscarAlbum(String nombre) {
			
			LinkedList<Album> lista = Album.load(this.getId(),nombre);
			
			return lista;
		}
		
		public boolean borrarCancion(String nombre) {
			
			if (nombre == null) if (Cancion.load(this.getId(),null).getFirst().delete()) return true;
			
			if (Cancion.load(this.getId(),nombre).getFirst().delete()) return true;
			
			return false;
		}
		
		public boolean borrarAlbum(String nombre) {
			
			if (Album.load(this.getId(),nombre).size()!=0) {
				if (nombre == null) if (Album.load(this.getId(),null).getFirst().delete()) return true;
			
				if (Album.load(this.getId(),nombre).getFirst().delete()) return true;
			
			} else {
				System.out.println("No encontro el Album");
			}
			return false;
		}
		
		public int getId() {
			return id;
		}

		public String getNick() {
			return nick;
		}

		public String getNombre() {
			return nombre;
		}

		public String getApellidos() {
			return apellidos;
		}

		public String getEmail() {
			return email;
		}

		public String getContrasenya() {
			return contrasenya;
		}
}
