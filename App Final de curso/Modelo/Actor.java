package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

public class Actor {
	
	public int id;
	public String Nombre;
	public String Apellidos;
	public Date fechaCreacion;
	
	public Actor(int id, String nom, String apel, Date fc) {
		this.id = id; 
		this.Nombre=nom;
		this.Apellidos=apel;
		this.fechaCreacion=fc;
	}
	
	public static int delete(int i) {
		Conexion.open();
		PreparedStatement stm;
		try {
			stm=Conexion.con.prepareStatement("delete from actor where actor_id=?");
			stm.setInt(1, i);
			stm.executeUpdate();
			stm.close();
			return 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		
		
	}
	
	public static Actor create (String n, String a) {
		Conexion.open();
		PreparedStatement insercion;
		ResultSet respuesta;
		try {
			
			insercion=Conexion.con.prepareStatement("insert into actor (first_name, last_name) values (?,?)", Statement.RETURN_GENERATED_KEYS);
			insercion.setString(1, n);
			insercion.setString(2, a);
			insercion.executeUpdate();
			respuesta=insercion.getGeneratedKeys();
			
			if (respuesta.next()) return Actor.load(respuesta.getInt(1));
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
		
	}
	
	public static Actor load(Integer i) {
		Conexion.open();
		PreparedStatement stm;
		ResultSet respuesta;
		try {
			stm=Conexion.con.prepareStatement("Select * from actor where actor_id=?");
			stm.setInt(1, i);
			respuesta=stm.executeQuery();
			if (respuesta.next()) {
				return new Actor (respuesta.getInt("actor_id"), 
									respuesta.getString("first_name"), 
									respuesta.getString("last_name"),
									respuesta.getDate("last_update"));
			}
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void update(String nombre,String apellidos) {
		Conexion.open();
		PreparedStatement stm;
		
		try {
			stm=Conexion.con.prepareStatement("Update actor set first_name=?, last_name=? where actor_id=?");
			stm.setString(1,nombre);
			stm.setString(2,apellidos);
			stm.setInt(3, this.id);
			stm.executeUpdate();
			
			stm.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static LinkedList<Actor> find (Integer i, String n, String a){
		Conexion.open();
		PreparedStatement stm;
		ResultSet respuesta;
		int x=1;
		LinkedList<Actor> lista=new LinkedList<Actor>();
		
		String consulta="Select * from actor where ";
		if (i!=null) consulta+=" actor_id=? and ";
		if (n!=null) consulta+=" first_name like ? and ";
		if (a!=null) consulta+=" last_name like ? and ";
		consulta+=" 1=1 ";
		
		try {
			stm=Conexion.con.prepareStatement(consulta);
			if (i!=null) { stm.setInt(x,i); x++; }
			if (n!=null) { stm.setString(x, n+"%"); x++; }
			if (a!=null) { stm.setString(x, a+"%"); x++; }

			
			respuesta=stm.executeQuery();
			 while(respuesta.next()) {
				 lista.add(new Actor (respuesta.getInt("actor_id"), 
							respuesta.getString("first_name"), 
							respuesta.getString("last_name"),
							respuesta.getDate("last_update")));
			 }
			 stm.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
