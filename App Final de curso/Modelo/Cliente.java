package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

public class Cliente {
	public String nombre;
	public String apellidos;
	public String email;
	public String telefono;
	public Integer id;
	public Integer aid;
	public Date fechaCreacion;
	public String direccion;
	public String direccion2;
	public String poblacion;
	public Integer cid;
	public Integer pid;
	public String cp;
	
	private Cliente (Integer id, String n, String a, String e, String t, Integer aid, Date fc, String d1, String d2, String p, Integer cid, String cp, Integer pid) {
		this.id=id;
		this.nombre=n;
		this.apellidos=a;
		this.email=e;
		this.telefono=t;
		this.aid=aid;
		this.fechaCreacion=fc;
		this.direccion=d1;
		this.direccion2=d2;
		this.poblacion=p;
		this.cid=cid;
		this.cp=cp;
		this.pid=pid;
	}
	public int delete() {
		return Cliente.delete(this.id);
	}
	
	public static int delete(int i) {
		Conexion.open();
		PreparedStatement stm;
		try {
			stm=Conexion.con.prepareStatement("delete customer, address from customer join address using (address_id) where customer_id=?");
			stm.setInt(1, i);
			stm.executeUpdate();
			stm.close();
			return 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		
		
	}
	
	public static Cliente create (String n, String a, String e, String t, String d1, String d2, String p, Integer cid, String cp) {
		Conexion.open();
		PreparedStatement insercion;
		ResultSet respuesta;
		try {
			insercion=Conexion.con.prepareStatement("insert into address (address, address2, district, city_id, postal_code, phone) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			insercion.setString(1, d1);
			insercion.setString(2, d2);
			insercion.setString(3, p);
			insercion.setInt(4, cid);
			insercion.setString(5, cp);
			insercion.setString(6, t);
			
			insercion.executeUpdate();
			respuesta=insercion.getGeneratedKeys();
			respuesta.next();
			
			Integer aid=respuesta.getInt(1);
			insercion.close();
			
			insercion=Conexion.con.prepareStatement("insert into customer (store_id, first_name, last_name, email, address_id, create_date) values (1,?,?,?,?,NOW())", Statement.RETURN_GENERATED_KEYS);
			insercion.setString(1, n);
			insercion.setString(2, a);
			insercion.setString(3, e);
			insercion.setInt(4, aid);
		
			insercion.executeUpdate();
			respuesta=insercion.getGeneratedKeys();
			
			if (respuesta.next()) return Cliente.load(respuesta.getInt(1));
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
		
	}
	
	public static Cliente load(Integer i) {
		Conexion.open();
		PreparedStatement stm;
		ResultSet respuesta;
		try {
			stm=Conexion.con.prepareStatement("Select * from customer join address using (address_id) join city using (city_id) where customer_id=?");
			stm.setInt(1, i);
			respuesta=stm.executeQuery();
			if (respuesta.next()) {
				return new Cliente (respuesta.getInt("customer_id"), 
									respuesta.getString("first_name"), 
									respuesta.getString("last_name"), 
									respuesta.getString("email"),
									respuesta.getString("phone"),
									respuesta.getInt("address_id"), 
									respuesta.getDate("create_date"),
									respuesta.getString("address"), 
									respuesta.getString("address2"), 
									respuesta.getString("district"), 
									respuesta.getInt("city_id"), 
									respuesta.getString("postal_code"), 
									respuesta.getInt("country_id"));
			}
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void update(String nombre,String apellidos,String email,String telefono,String direccion1,String direccion2,String poblacion,String cp,String city,Integer cid) {
		Conexion.open();
		PreparedStatement stm;
		
		try {
			stm=Conexion.con.prepareStatement("Update customer join address using (address_id) set address=?, address2=?, district=?, city_id=?, postal_code=?, phone=?, first_name=?, last_name=?, email=? where customer_id=?");
			stm.setString(1,direccion1);
			stm.setString(2,direccion2);
			stm.setString(3,poblacion);
			stm.setInt(4,cid);
			stm.setString(5,cp);
			stm.setString(6,telefono);
			stm.setString(7,nombre);
			stm.setString(8,apellidos);
			stm.setString(9,email);
			stm.setInt(10,id);
			stm.executeUpdate();
			
			stm.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static LinkedList<Cliente> find (Integer i, String n, String a, String t){
		Conexion.open();
		PreparedStatement stm;
		ResultSet respuesta;
		int x=1;
		LinkedList<Cliente> lista=new LinkedList<Cliente>();
		
		String consulta="Select * from customer join address using (address_id) join city using (city_id) where ";
		if (i!=null) consulta+=" customer_id=? and ";
		if (n!=null) consulta+=" first_name like ? and ";
		if (a!=null) consulta+=" last_name like ? and ";
		if (t!=null) consulta+=" phone like ? and ";
		consulta+=" 1=1 ";
		
		try {
			stm=Conexion.con.prepareStatement(consulta);
			if (i!=null) { stm.setInt(x,i); x++; }
			if (n!=null) { stm.setString(x, n+"%"); x++; }
			if (a!=null) { stm.setString(x, a+"%"); x++; }
			if (t!=null) { stm.setString(x, t+"%"); x++; }
			
			respuesta=stm.executeQuery();
			 while(respuesta.next()) {
				 lista.add(new Cliente (respuesta.getInt("customer_id"), 
							respuesta.getString("first_name"), 
							respuesta.getString("last_name"), 
							respuesta.getString("email"),
							respuesta.getString("phone"),
							respuesta.getInt("address_id"), 
							respuesta.getDate("create_date"), 
							respuesta.getString("address"), 
							respuesta.getString("address2"), 
							respuesta.getString("district"), 
							respuesta.getInt("city_id"), 
							respuesta.getString("postal_code"), 
							respuesta.getInt("country_id")));
			 }
			 stm.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static Integer BuscarCip(String City) {
		
		City c = Modelo.City.find(City, null).getFirst();
		
		return c.city_id;
	}
	
	public City BuscarPaisporCip() {
		
		City c = Modelo.City.load(this.cid);
		return c;
	}
	
}
