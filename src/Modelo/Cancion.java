package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

public class Cancion {
	private int id;
	private String nombre;
	private Date FechaPublicacion;
	private String duracion;
	private String url;
	private int idcan;

		public Cancion(int id,String nom,Date publi, String durac,String url,int idcan) {
			this.id=id;
			this.nombre=nom;
			this.FechaPublicacion=publi;
			this.duracion=durac;
			this.url=url;
			this.idcan=idcan;
		}
		
		public String toString() {
			return nombre +" - "+duracion+ " - "+ FechaPublicacion  ;
		}
		
		public static Cancion create(String nom,String duracion,int idcan,Integer idalb,String url) {
			
			// tengo que anyadir el id de la nueva playlist ponerla en cancion create y hacer un if idplaylist != null haga otro insert en otra tabla de canciones palylsit 
			PreparedStatement stm;
			ResultSet res;
			
			String insert="insert into canciones (ces_nombre,ces_fechaPublicacion,ces_duracion,ces_codalb,ces_codcan,ces_url) values (?,?,?,?,?,?)";
			if (idalb == null) insert="insert into canciones (ces_nombre,ces_fechaPublicacion,ces_duracion,ces_codcan,ces_url) values (?,?,?,?,?)";
			Conexion.open();
			
			try {
				int x=1;
				stm=Conexion.con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
				stm.setString(x++, nom);
				Date publi=new Date();
				java.sql.Date date = new java.sql.Date(publi.getTime());
				stm.setDate(x++, date);
				stm.setString(x++, duracion);
				if (idalb != null) stm.setInt(x++, idalb);
				stm.setInt(x++, idcan);
				stm.setString(x++, url);
				
				stm.executeQuery();
				res=stm.getGeneratedKeys();
				res.next();
				
				return new Cancion(res.getInt(1),nom,publi,duracion,url,idcan);
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}

		public boolean updateA(Integer idalbum,Integer idcantante) {
			
			Conexion.open();
			
			PreparedStatement stm;
			
			String update=" update canciones inner join cantantes on ces_codcan = can_codcan set ces_codalb=? where ces_codces=? ";
			if (idcantante != null) update+="and can_codcan=?";
			if (idalbum==null) idalbum=0;
			try {
				stm=Conexion.con.prepareStatement(update);
				stm.setInt(1, idalbum);
				stm.setInt(2, this.getId());
				if (idcantante != null)  stm.setInt(3, idcantante);
				
				stm.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("error anyadiendo cancion al album");
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		public boolean updatePlaylist(Integer idPlaylist) {
			
			
			Conexion.open();
			
			PreparedStatement stm;
			ResultSet res;
			
			
			String select="select * from playlist_añadir_canciones where pca_codces = ? and pca_codpla = ?";
			int x=0;
			
			try {
				stm=Conexion.con.prepareStatement(select);
				stm.setInt(1,this.getId() );
				stm.setInt(2, idPlaylist);
				
				res = stm.executeQuery();
				
				if(res.next()) x=1;
			} catch (SQLException e) {
				System.out.println("error anyadiendo cancion a la playlist");
				e.printStackTrace();
				return false;
			} 
			
			if (x==0) {
				String insert="insert into playlist_añadir_canciones(pca_codces,pca_codpla) values (?,?)";
				
				try {
					stm=Conexion.con.prepareStatement(insert);
					stm.setInt(1,this.getId() );
					stm.setInt(2, idPlaylist);
					
					stm.executeUpdate();
					
				} catch (SQLException e) {
					System.out.println("error anyadiendo cancion a la playlist");
					e.printStackTrace();
					return false;
				}
				
				return true;
			} else System.out.println("Ya esta añadido");
			return false;
		}
		
		public static LinkedList<Cancion> load(int id, String nombre) {
			LinkedList<Cancion> lista = new LinkedList<Cancion>();
			
			PreparedStatement stm;
			ResultSet res;
			String select = "select * from canciones where ces_codcan = ?";
			
			if (nombre != null) select+= " and ces_nombre = ?";
			
			Conexion.open();
			
			try {
				stm=Conexion.con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
				stm.setInt(1, id);
				if (nombre != null) stm.setString(2, nombre);
				
				res=stm.executeQuery();
				while (res.next()) {
					lista.add(new Cancion (res.getInt("ces_codces"), 
										  res.getString("ces_nombre"), 
										  res.getDate("ces_fechaPublicacion"),  
										  res.getString("ces_duracion"),  
										  res.getString("ces_url"),
										  res.getInt("ces_codcan")
										  ));
				}
				
				return lista;
				
			}catch (SQLException e) {
				System.out.println("Problema cargando cancion");
			}
			
			return null;
		}
		
		public static LinkedList<Cancion> loadtodo(String nombre) {
			LinkedList<Cancion> lista = new LinkedList<Cancion>();
			
			PreparedStatement stm;
			ResultSet res;
			String select = "select * from canciones where ces_nombre like ?";
			
			Conexion.open();
			
			try {
				stm=Conexion.con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
				stm.setString(1, nombre+"%");
				
				res=stm.executeQuery();
				while (res.next()) {
					lista.add(new Cancion (res.getInt("ces_codces"), 
										  res.getString("ces_nombre"), 
										  res.getDate("ces_fechaPublicacion"),  
										  res.getString("ces_duracion"),  
										  res.getString("ces_url"),
										  res.getInt("ces_codcan")
										  ));
				}
				
				return lista;
				
			}catch (SQLException e) {
				System.out.println("Problema cargando cancion");
			}
			
			return null;
		}
		
		public boolean delete() {
			
			PreparedStatement stm;
			
			
			String delete="delete from canciones where ces_codces=?";
			Conexion.open();
			
			try {
				stm=Conexion.con.prepareStatement(delete);
				stm.setInt(1, this.getId());
				
				stm.executeUpdate();

			} catch (SQLException e) {
				return false;
			}
			return true;
		}
		
		public int getId() {
			return id;
		}

		public String getNombre() {
			return nombre;
		}

		public Date getFechaPublicacion() {
			return FechaPublicacion;
		}

		public String getDuracion() {
			return duracion;
		}

		public String getUrl() {
			return url;
		}

		public int getIdcan() {
			return idcan;
		}
}
