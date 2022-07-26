package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

public class Album {
	private int id;
	private String nombre;
	private Date FechaCreacion; 
	LinkedList<Cancion> c;
	
	
	public Album(int id, String nombre, Date FechaCreacion) {
		this.id=id;
		this.nombre=nombre;
		this.FechaCreacion=FechaCreacion;
		
	}

	
	public static Album create(int id,String nom,Date FechaCreacion) {
		
		PreparedStatement stm;
		ResultSet res;
		
		String insert="insert into Albumes (alb_nombre,alb_fechaCreacion,alb_codcan) values (?,?,?)";
		
		Modelo.Conexion.open();
		
		try {
			stm=Modelo.Conexion.con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, nom);
			
			Date publi=new Date();
			java.sql.Date date = new java.sql.Date(publi.getTime());
			stm.setDate(2, date);
			stm.setInt(3, id);
			stm.executeQuery();
			res=stm.getGeneratedKeys();
			res.next();
			
			return new Album(res.getInt(1),nom,publi);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static LinkedList<Album> load(Integer id, String nombre) {
		LinkedList<Album> lista = new LinkedList<Album>();
		
		PreparedStatement stm;
		ResultSet res;
		String select = "select * from albumes where 1=1";
		if (id != null) select= "select distinct albumes.* from albumes inner join cantantes on ? = alb_codcan";
		if (nombre != null) select+= " and alb_nombre like ?";
		
		Conexion.open();
		
		try {
			int x=1;
			stm=Conexion.con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			if (id != null) stm.setInt(x++, id);
			if (nombre != null) stm.setString(x++, nombre+"%");
			
			
			res=stm.executeQuery();
			while (res.next()) {
				lista.add(new Album (res.getInt("alb_codalb"), 
									  res.getString("alb_nombre"), 
									  res.getDate("alb_fechaCreacion")
									  ));
			}
			
			return lista;
			
		}catch (SQLException e) {
			System.out.println("Problema cargando album");
		}
		
		return null;
	}
	
	public static LinkedList<Cancion> loadSong(Integer id, String nombre) {
		LinkedList<Cancion> lista = new LinkedList<Cancion>();
		
		PreparedStatement stm;
		ResultSet res;
		String select = "select * from albumes where 1=1";
		if (id != null) select= "select * from albumes inner join cantantes on ? = alb_codcan";
		if (nombre != null) select= "select * from albumes inner join canciones on alb_codalb = ces_codalb and alb_nombre like ?";
		
		Conexion.open();
		
		try {
			int x=1;
			stm=Conexion.con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			if (id != null) stm.setInt(x++, id);
			if (nombre != null) stm.setString(x++, nombre+"%");
			
			
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
			System.out.println("Problema cargando canciones");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean delete() {
		
		PreparedStatement stm;
		
	     LinkedList<Cancion> lista =  Album.loadSong(null, nombre);
		for (Cancion c : lista) {
			
			c.delete();
			Cancion.create(c.getNombre(), c.getDuracion(), c.getIdcan(), null, c.getUrl());
			
		}
	     
		String delete="delete from albumes where alb_codalb=?";
		Conexion.open();
		
		try {
			stm=Conexion.con.prepareStatement(delete);
			stm.setInt(1, this.getId());
			
			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static boolean ComprobarCantante(Album album, Integer idCantante) {
		//;  
		
		PreparedStatement stm;
		ResultSet res;
		String select = "select * from albumes where alb_codalb=? and alb_codcan=?";

		Conexion.open();
		
		try {
			stm=Conexion.con.prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
			stm.setInt(1, album.id);
			stm.setInt(2, idCantante);
			
			res=stm.executeQuery();
			while (res.next()) {
				return true;
			}
		}catch (SQLException e) {
			System.out.println("Problema cargando album");
		}
		return false;
	}
	
	public Date getFechaCreacion() {
		return FechaCreacion;
	}

	public String getNombre() {
		return nombre;
	}

	public int getId() {
		return id;
	}
}
 

