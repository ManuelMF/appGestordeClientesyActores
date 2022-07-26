package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class City {
	public Integer country_id;
	public Integer city_id;
	public String city;
	
	private City (Integer id,String name, Integer pid) {
		this.city_id=id;
		this.city=name;
		this.country_id=pid;

	}
	
	public static LinkedList<City> find (String n, Integer pid){
		Conexion.open();
		PreparedStatement stm;
		ResultSet resultado;
		LinkedList<City> lista = new LinkedList<City>();
		int x=1;
		
		String consulta="Select * from City where ";
		if (n!=null) consulta+=" city like ? and ";
		if (pid!=null) consulta+=" country_id = ? and ";
		consulta+=" 1=1 order by city; ";
		
		
		try {
			stm=Conexion.con.prepareStatement(consulta);
			if (n!=null)  stm.setString(x++, n);
			if (pid!=null)  stm.setInt(x++, pid); 
			resultado=stm.executeQuery();

			while(resultado.next()) {
				City c=new City(resultado.getInt("city_id"), 
								resultado.getString("city"),
								resultado.getInt("country_id"));
							
				lista.add(c);
			}
			stm.close();
		} catch (SQLException e) {
			
		}
		
		return lista;
		
	}
	
	public static Integer delete(int id) {
		Conexion.open();
		PreparedStatement del;
		
		try {
			del=Conexion.con.prepareStatement("Delete from city where city_id=?");
			del.setInt(1, id);
			
			del.executeUpdate();
			del.close();
			return null;
			
		} catch (SQLException e) {
			
			return -1;
		}
	}
	
	public Integer delete() {
		return City.delete(this.country_id);
	}
	
	public static City load(Integer id) {
		Conexion.open();
		PreparedStatement consulta;
		ResultSet resultado;
		try {
			consulta=Conexion.con.prepareStatement("Select * from city where city_id=?");
			consulta.setInt(1, id);
			
			resultado=consulta.executeQuery();
			if (resultado.next()) {
				City c=new City(resultado.getInt("city_id"), 
						resultado.getString("city"),
						resultado.getInt("country_id"));
				consulta.close();
				return c;
			}
			consulta.close();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	public static City create(String name, Integer pid) {
		Conexion.open();
		PreparedStatement insert;
		ResultSet resultado;
		
		try {
			insert=Conexion.con.prepareStatement("Insert into City (city, country_id) values (?,?);"
					,Statement.RETURN_GENERATED_KEYS);
			insert.setString(1, name);
			insert.setInt(2, pid);
			
			insert.executeUpdate();
			resultado=insert.getGeneratedKeys();
			
			if (resultado.next()) {
				City c=load(resultado.getInt(1));
				insert.close();
				return c;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String toString() {
		return this.city;
	}
}
