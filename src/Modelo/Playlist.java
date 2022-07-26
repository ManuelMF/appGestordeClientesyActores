package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

public class Playlist {
	
	private int id;
	private int iduser;
	private String nombre;
	private Date fechaCreacion;
	
	public Playlist(int id, int iduser, String nombre, Date fechaCreacion) {
		this.id=id;
		this.iduser=iduser;
		this.nombre=nombre;
		this.fechaCreacion=fechaCreacion;
	}
	
	public static Playlist Create(String nombre,int iduser) {
		
		Conexion.open();
		PreparedStatement stm;
		ResultSet res;
		
		String insert = "insert into playlists (pla_nombre,pla_codusu) values (?,?)";
		
		try {
			stm = Conexion.con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, nombre);
			stm.setInt(2, iduser);
			
			stm.executeQuery();
			
			res=stm.getGeneratedKeys();
			res.next();
			
			return new Playlist(res.getInt(1),iduser,nombre,new Date());
			
		} catch (SQLException e) {
			System.out.println("Problema creando una playlist");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean delete() {
		Conexion.open();
		PreparedStatement stm;
		
		String delete1 = "delete from playlist_añadir_canciones where pca_codpla = ?";
		try {
			stm = Conexion.con.prepareStatement(delete1);
			stm.setInt(1, this.id);
			
			stm.executeQuery();
			
			
		} catch (SQLException e) {
			System.out.println("Problema borrando una playlist");
			e.printStackTrace();
		}
		
		String delete2 = "delete from usuarios_gusta_playlists where upl_codpla = ?";
		try {
			stm = Conexion.con.prepareStatement(delete2);
			stm.setInt(1, this.id);
			
			stm.executeQuery();
			
			
		} catch (SQLException e) {
			System.out.println("Problema borrando una playlist");
			e.printStackTrace();
		}
		
		String delete = "delete from playlists where pla_codpla = ?";
		
		try {
			stm = Conexion.con.prepareStatement(delete);
			stm.setInt(1, this.id);
			
			stm.executeQuery();
			return true;
			
		} catch (SQLException e) {
			System.out.println("Problema borrando una playlist");
			e.printStackTrace();
		}
		
		return false;
	}

	public static LinkedList<Playlist> load(Integer id, String nombre, Integer iduser) {
		LinkedList<Playlist> lista = new LinkedList<Playlist>();
		
		PreparedStatement stm;
		ResultSet res;
		String select = "select * from playlists where 1=1";
		if (id != null) select+= " and pla_codpla = ?";
		if (nombre != null) select+= " and pla_nombre like ?";
		if (iduser != null) select+= " and pla_codusu like ?";
		
		Conexion.open();
		
		try {
			int x=1;
			stm=Conexion.con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			if (id != null) stm.setInt(x++, id);
			if (nombre != null) stm.setString(x++, nombre+"%");
			if (iduser != null) stm.setInt(x++, iduser);
			
			res=stm.executeQuery();
			while (res.next()) {
				lista.add(new Playlist (res.getInt("pla_codpla"),
									  res.getInt("pla_codusu"),
									  res.getString("pla_nombre"), 
									  res.getDate("pla_fechaCreacion")
									  ));
			}
			
			return lista;
			
		}catch (SQLException e) {
			System.out.println("Problema cargando album");
		}
		
		return null;
	}
	
	public LinkedList<Cancion> CancionesPlaylist(Integer idCancion) {
		
		LinkedList<Cancion> lista = new LinkedList<Cancion>();
		
		Conexion.open();
		
		PreparedStatement stm;
		ResultSet res;
		
		
		String select="select * from playlist_añadir_canciones inner join canciones on ces_codces=pca_codces where  pca_codpla = ?";
		if (idCancion != null) select+="and pca_codces = ?";
		
		
		try {
			stm=Conexion.con.prepareStatement(select);
			stm.setInt(1,this.getId() );
			if (idCancion != null) stm.setInt(2, idCancion);
			
			res = stm.executeQuery();
			
			while(res.next()) lista.add(new Cancion(res.getInt("ces_codces"),res.getString("ces_nombre"),res.getDate("ces_fechaPublicacion"),res.getString("ces_duracion"),res.getString("ces_url"),res.getInt("ces_codcan")));
			return lista;
		} catch (SQLException e) {
			System.out.println("error anyadiendo cancion a la playlist");
			e.printStackTrace();
			return null;
		}
	}
	
	public  boolean addSong(Cancion c) {
		

		Conexion.open();
		
		PreparedStatement stm;
		ResultSet res;
		
		
		String select="select * from playlist_añadir_canciones where pca_codces = ? and pca_codpla = ?";
		int x=0;
		
		try {
			stm=Conexion.con.prepareStatement(select);
			stm.setInt(1, c.getId());
			stm.setInt(2, this.getId());
			
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
				stm.setInt(1, c.getId());
				stm.setInt(2, this.getId());
				
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
	
	public static boolean ComprobarCantante(Playlist playlist, Integer id) {
		  
		
		PreparedStatement stm;
		ResultSet res;
		String select = "select * from playlists where pla_codpla=? and pla_codusu=?;";

		Conexion.open();
		
		try {
			stm=Conexion.con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			stm.setInt(1, playlist.id);
			stm.setInt(2, id);
			
			res=stm.executeQuery();
			while (res.next()) {
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getId() {
		return id;
	}

	public int getIduser() {
		return iduser;
	}

	public String getNombre() {
		return nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}
}
