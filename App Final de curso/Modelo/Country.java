package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Country {
	
	public Integer country_id;
	public String country;
	
	public Country (Integer id,String name) {
		this.country_id=id;
		this.country=name;

	}
	
	public static LinkedList<Country> find (String n){
		Conexion.open();
		PreparedStatement stm;
		ResultSet resultado;
		LinkedList<Country> lista = new LinkedList<Country>();
		int x=1;
		
		String consulta="Select * from country where ";
		if (n!=null) consulta+=" country like ? and ";
		consulta+=" 1=1 order by country; ";
		
		try {
			stm=Conexion.con.prepareStatement(consulta);
			if (n!=null)  stm.setString(x++, n); 

			resultado=stm.executeQuery();

			while(resultado.next()) {
				Country c=new Country(	resultado.getInt("country_id"), 
										resultado.getString("country"));
							
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
			del=Conexion.con.prepareStatement("Delete from country where country_id=?");
			del.setInt(1, id);
			
			del.executeUpdate();
			del.close();
			return null;
			
		} catch (SQLException e) {
			
			return -1;
		}
	}
	
	public Integer delete() {
		return Country.delete(this.country_id);
	}
	
	public static Country load(Integer id) {
		Conexion.open();
		PreparedStatement consulta;
		ResultSet resultado;
		try {
			consulta=Conexion.con.prepareStatement("Select * from country where country_id=?");
			consulta.setInt(1, id);
			
			resultado=consulta.executeQuery();
			if (resultado.next()) {
				Country c=new Country(	resultado.getInt("country_id"), 
						resultado.getString("country"));
				consulta.close();
				return c;
			}
			consulta.close();
			
		} catch (Exception e) {


		}
		return null;
	}
	
	public static Country create(String name) {
		Conexion.open();
		PreparedStatement insert;
		ResultSet resultado;
		
		try {
			insert=Conexion.con.prepareStatement("Insert into country (country) values (?);"
					,Statement.RETURN_GENERATED_KEYS);
			insert.setString(1, name);
			
			insert.executeUpdate();
			resultado=insert.getGeneratedKeys();
			
			if (resultado.next()) {
				Country c=new Country(resultado.getInt(1), name);
				insert.close();
				return c;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String toString() {
		return this.country;
	}

}



