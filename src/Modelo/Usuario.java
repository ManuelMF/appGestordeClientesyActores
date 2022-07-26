package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Usuario {

	private int id;
	private String nick;
	private String nombre;
	private String apellidos;
	private Date nacimiento;
	private String email;
	private String contrasenya;
	static Scanner sc=new Scanner(System.in);
	private LinkedList<Cancion> mgsong;
	private LinkedList<Album> mgalb;
	private LinkedList<Cantante> mgcan;
	private LinkedList<Playlist> mgpla;
	
	
	public Usuario(int id,String nick, String nom, String apell, Date naci, String email, String contra) {
		this.id=id;
		this.nick=nick;
		this.nombre=nom;
		this.apellidos=apell;
		this.nacimiento=naci;
		this.email=email;
		this.contrasenya=contra;
		this.mgsong = new LinkedList<Cancion>();
		this.mgalb = new LinkedList<Album>();
		this.mgcan = new LinkedList<Cantante>();
		this.mgpla = new LinkedList<Playlist>();
	}
	
	static public Usuario create(String nick, String nom, String apell, Date naci, String email, String contra) {
		
		Conexion.open();
		
		PreparedStatement stm;
		
		ResultSet res;
		
		String insert = "insert into usuarios (usu_nick,usu_nombre, usu_apellidos, usu_nacimiento, usu_email, usu_contra) values (?,?,?,?,?,?)";
		
		try {
			stm=Conexion.con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, nick);
			stm.setString(2, nom);
			stm.setString(3, apell);
			java.sql.Date date = new java.sql.Date(naci.getTime());
			stm.setDate(4,  date);
			stm.setString(5, email);
			stm.setString(6, contra);
			
			stm.executeQuery();
			
			res=stm.getGeneratedKeys();
			res.next();
			
			Usuario u = new Usuario(res.getInt(1),nick,nom,apell,naci,email,contra);
			
			return u;
			
		} catch (SQLException e) {
			System.out.println("Fallo creado usuario");
		}
		
		return null;
	}
	
	public Playlist anyadirPlaylist(String nombre) {
		
		return Playlist.Create(nombre, this.id);
		
	}
	
	public static Usuario load(String logueo) {
		
		LinkedList<Usuario> lista = new LinkedList<Usuario>();
		
		Conexion.open();
		
		PreparedStatement stm;
		
		ResultSet res;
		
		String select; 
		
		if (logueo.contains("@")) {
			select = "select * from usuarios where usu_email like ?";
			
		} else {
			select = "select * from usuarios where usu_nick like ?";
			
		}
		
		try {
			stm=Conexion.con.prepareStatement(select);
			stm.setString(1, logueo);
			
			res=stm.executeQuery();
			while (res.next()) {
				lista.add(new Usuario(res.getInt("usu_codusu"), 
									  res.getString("usu_nick"), 
									  res.getString("usu_nombre"),  
									  res.getString("usu_apellidos"),  
									  res.getDate("usu_nacimiento"), 
									  res.getString("usu_email"),  
									  res.getString("usu_contra") 
									  ));
			}
			
		}catch (SQLException e) {
			System.out.println("Fallo cargando usuario");
		}
		
		if (lista.size()==0) return null;
		else if (lista.size()==1) return lista.getFirst();
		else {
			int x=1,num = 0; boolean error = true;
			do {
				try {
					System.out.println("A que cuenta quieres entrar");
					for (Usuario u:lista) {
						System.out.println(x+": Nick: "+u.getNick()+" Nombre: "+u.getNombre()+" "+u.getApellidos());
						x++;
					}
					num= Integer.parseInt(sc.nextLine()); 
					
					if (num==0) { throw new RuntimeException();}
					
					error=false;
					
				} catch (RuntimeException e) {
					System.out.println("Caracter invalido");
				}
			} while (error);
		
		return lista.get(num-1);
		}
	}

	public boolean loadContra(String contra) {
		
		Usuario user = null;
		
		Conexion.open();
		
		PreparedStatement stm;
		
		ResultSet res;
		
		String select; 
		
		
			select = "select * from usuarios where usu_codusu like ?";
		
		try {
			stm=Conexion.con.prepareStatement(select);
			stm.setInt(1, this.id);
			
			res=stm.executeQuery();
			
			res.next();
			user = new Usuario(res.getInt("usu_codusu"), 
									   res.getString("usu_nick"), 
									   res.getString("usu_nombre"),  
									   res.getString("usu_apellidos"),  
									   res.getDate("usu_nacimiento"), 
									   res.getString("usu_email"),  
									   res.getString("usu_contra") 
									   );
			
			
		}catch (SQLException e) {
			System.out.println("Fallo cargando usuario");
		}
		
		if (user.contrasenya.equals(contra)) return true;
		else return false;
	}
	
	public boolean updateContra(String contra) {
		
		Conexion.open();
		
		PreparedStatement stm;
		
		String update="update usuarios set usu_contra=? where usu_codusu=?";
		
		try {
			stm=Conexion.con.prepareStatement(update);
			stm.setString(1,contra);
			stm.setInt(2, this.getId());
			
			stm.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error modificando la contraseña");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean updatenick(String nick) {
		
		Conexion.open();
		
		PreparedStatement stm;
		ResultSet res;
		
		String select="select * from usuarios where usu_nick like ?";
		
		try {
			stm=Conexion.con.prepareStatement(select);
			stm.setString(1,nick);
			
			res=stm.executeQuery();
			
			if(res.next()) return false;
		} catch (SQLException e) {
			System.out.println("error modificando la contraseña al buscar nombre");
			e.printStackTrace();
			return false;
		}
		
		
		String update="update usuarios set usu_nick=? where usu_codusu=?";
		
		try {
			stm=Conexion.con.prepareStatement(update);
			stm.setString(1,nick);
			stm.setInt(2, this.getId());
			
			stm.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error modificando la contraseña");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Cantante pasarCantante() {
		
		PreparedStatement stm;
		ResultSet res;
		
		Conexion.open();
		
		String select="select * from usuarios inner join cantantes on can_codusu=usu_codusu where usu_nick like ?";
		
		try {
			stm=Conexion.con.prepareStatement(select);
			stm.setString(1,nick);
			
			res=stm.executeQuery();
			
			if(res.next()) return null;
		} catch (SQLException e) {
			System.out.println("error pasando a cantante al buscar repetidos");
			e.printStackTrace();
			return null;
		}
		
		String insert="insert into cantantes (can_codusu) values (?)";
		
		
		
		try {
			stm=Conexion.con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stm.setInt(1, this.getId());
			
			stm.executeQuery();
			
			res=stm.getGeneratedKeys();
			res.next();
			
			return new Cantante(res.getInt(1),this.getNick(),this.getNombre(),this.getApellidos(),this.getEmail(),this.getContrasenya());
			
			
		}	catch (SQLException e) {
			System.out.println("Problema añadiendo un cantantes");
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	public boolean mgsong(Cancion c) {
		
		PreparedStatement stm1;
		ResultSet res1;
		
		String select = "select * from usuarios_gusta_canciones where uce_codces like ? and uce_codusu like ?";
		
		try {
			stm1=Conexion.con.prepareStatement(select);
			stm1.setInt(1, c.getId());
			stm1.setInt(2, this.id);
			
			
			res1=stm1.executeQuery();
			
			if (res1.next()) return false;
			
			
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta una cancion");
			e.printStackTrace();
			return false;
		}
		
		PreparedStatement stm;
		ResultSet res;
		
		String insert ="Insert into usuarios_gusta_canciones (uce_codusu, uce_codces) values (?,?)";
		
		Conexion.open();
		
		try {
			stm=Conexion.con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			stm.setInt(1, this.getId());
			stm.setInt(2, c.getId());
			
			res=stm.executeQuery();
			
			res.next();
			
			this.mgsong.add(c);
			return true;
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta una cancion");
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public LinkedList<Cancion> listaMgSong() {
		PreparedStatement stm1;
		ResultSet res1;
		this.mgsong.clear(); 
		String select = "select * from usuarios_gusta_canciones inner join canciones on ces_codces=uce_codces where uce_codusu like ?";
		
		try {
			stm1=Conexion.con.prepareStatement(select);
			stm1.setInt(1, this.getId());
			
			res1=stm1.executeQuery();
			
			while (res1.next()) {
				this.mgsong.add(new Cancion(res1.getInt("ces_codces"), res1.getString("ces_nombre"), res1.getDate("ces_fechaPublicacion"), res1.getString("ces_duracion"), res1.getString("ces_url"), res1.getInt("ces_codcan")));
			}
			
			return this.mgsong;
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta una cancion");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean mgalbum(Album c) {
		
		PreparedStatement stm1;
		ResultSet res1;
		
		String select = "select * from usuarios_gusta_albumes where ual_codalb like ? and ual_codusu like ?";
		
		try {
			stm1=Conexion.con.prepareStatement(select);
			stm1.setInt(1, c.getId());
			stm1.setInt(2, this.id);
			
			res1=stm1.executeQuery();
			
			if (res1.next()) return false;
			
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta una album");
			e.printStackTrace();
			return false;
		}
		
	
		PreparedStatement stm;
		ResultSet res;
		String insert ="Insert into usuarios_gusta_albumes (ual_codusu, ual_codalb) values (?,?)";
		
		Conexion.open();
		
		try {
			stm=Conexion.con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			stm.setInt(1, this.getId());
			stm.setInt(2, c.getId());
			
			res=stm.executeQuery();
			
			res.next();
			
			this.mgalb.add(c);
			return true;
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta una album");
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public LinkedList<Album> listaMgAlbum() {
		this.mgalb.clear();
		PreparedStatement stm1;
		ResultSet res1;
		this.mgsong.clear(); 
		String select = "select * from usuarios_gusta_albumes inner join albumes on alb_codalb=ual_codalb where ual_codusu like ?";
		
		try {
			stm1=Conexion.con.prepareStatement(select);
			stm1.setInt(1, this.getId());
			
			res1=stm1.executeQuery();
			
			while (res1.next()) {
				this.mgalb.add(new Album(res1.getInt("alb_codalb"),res1.getString("alb_nombre"), res1.getDate("alb_fechaCreacion"))    );
			}
			
			return this.mgalb;
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta una cancion");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean mgCantante(Cantante c) {
		
		PreparedStatement stm1;
		ResultSet res1;
		
		String select = "select * from usuarios_gusta_cantantes where uca_codcan like ? and uca_codusu like ?";
		
		try {
			stm1=Conexion.con.prepareStatement(select);
			stm1.setInt(1, c.getId());
			stm1.setInt(2, this.id);
			
			res1=stm1.executeQuery();
			
			if (res1.next()) return false;
			
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta una cantante");
			e.printStackTrace();
			return false;
		}
		
	
		PreparedStatement stm;
		ResultSet res;
		String insert ="Insert into usuarios_gusta_cantantes (uca_codusu, uca_codcan) values (?,?)";
		
		Conexion.open();
		
		try {
			stm=Conexion.con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			stm.setInt(1, this.getId());
			stm.setInt(2, c.getId());
			
			res=stm.executeQuery();
			
			res.next();
			
			this.mgcan.add(c);
			return true;
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta a un cantante");
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean deleteMgCantante(int idcan) {
		
		PreparedStatement stm;
		
		
		String delete="delete from usuarios_gusta_cantantes where uca_codusu=? and uca_codcan=?";
		Conexion.open();
		
		try {
			stm=Conexion.con.prepareStatement(delete);
			stm.setInt(1, this.getId());
			stm.setInt(2, idcan);
			
			stm.executeUpdate();

		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public boolean deleteMgCancion(int idcan) {
		
		PreparedStatement stm;
		
		
		String delete="delete from usuarios_gusta_canciones where uce_codusu=? and uce_codces=?";
		Conexion.open();
		
		try {
			stm=Conexion.con.prepareStatement(delete);
			stm.setInt(1, this.getId());
			stm.setInt(2, idcan);
			
			stm.executeUpdate();

		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean deleteMgAlbum(int idal) {
		int x=0;
		PreparedStatement stm;
		ResultSet res;
		Conexion.open();
		
		String select = "select * from usuarios_gusta_albumes where ual_codusu=? and ual_codalb=?";

		try {
			stm=Conexion.con.prepareStatement(select);
			stm.setInt(1, this.getId());
			stm.setInt(2, idal);
			
			res = stm.executeQuery();
			if (res.next()) x=1;
			
		} catch (SQLException e) {
			return false;
		}
		
		if (x==1) {
			String delete="delete from usuarios_gusta_albumes where ual_codusu=? and ual_codalb=?";
			
			
			try {
				stm=Conexion.con.prepareStatement(delete);
				stm.setInt(1, this.getId());
				stm.setInt(2, idal);
				
				stm.executeUpdate();
	
			} catch (SQLException e) {
				return false;
			}
			return true;
		}
		else return false;
	}
	
	public LinkedList<Cantante> listaMgCantantes() {
		this.mgcan.clear();
		PreparedStatement stm1;
		ResultSet res1;
		String select = " select * from usuarios_gusta_cantantes \r\n"
				+ " inner join cantantes on can_codcan=uca_codcan  \r\n"
				+ " inner join usuarios on can_codusu=usu_codusu\r\n"
				+ " where uca_codusu like ?;";
		
		try {
			stm1=Conexion.con.prepareStatement(select);
			stm1.setInt(1, this.getId());
			
			res1=stm1.executeQuery();
			
			while (res1.next()) {
				this.mgcan.add(new Cantante(res1.getInt("can_codcan"),
						res1.getString("usu_nick"),
						res1.getString("usu_nombre"),
						res1.getString("usu_apellidos"),
						res1.getString("usu_email"),
						res1.getString("usu_contra")
						));
			}
			
			return this.mgcan;
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta una cancion");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean mgPlaylist(Playlist c) {
		
		PreparedStatement stm1;
		ResultSet res1;
		
		String select = "select * from usuarios_gusta_playlists where upl_codpla like ? and upl_codusu like ?";
		
		try {
			stm1=Conexion.con.prepareStatement(select);
			stm1.setInt(1, c.getId());
			stm1.setInt(2, this.id);
			
			res1=stm1.executeQuery();
			
			if (res1.next()) return false;
			
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta a una playlist");
			e.printStackTrace();
			return false;
		}
		
	
		PreparedStatement stm;
		ResultSet res;
		String insert ="Insert into usuarios_gusta_playlists (upl_codusu, upl_codpla) values (?,?)";
		
		Conexion.open(); 
		
		try {
			stm=Conexion.con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			stm.setInt(1, this.getId());
			stm.setInt(2, c.getId());
			
			res=stm.executeQuery();
			
			res.next();
			
			this.mgpla.add(c);
			return true;
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta una playlist");
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public LinkedList<Playlist> listaMgPlaylist() {
		mgpla = new LinkedList<Playlist>();
		if (mgpla.isEmpty() == false) this.mgpla.clear();
		PreparedStatement stm1;
		ResultSet res1;
		String select = " select * from usuarios_gusta_playlists \r\n"
				+ " inner join playlists on upl_codpla=pla_codpla  \r\n"
				+ " inner join usuarios on upl_codusu=usu_codusu\r\n"
				+ " where upl_codusu like ?;";
		
		try {
			stm1=Conexion.con.prepareStatement(select);
			stm1.setInt(1, this.getId());
			
			res1=stm1.executeQuery();
			
			while (res1.next()) {
				this.mgpla.add(new Playlist (res1.getInt("pla_codpla"),
						  res1.getInt("pla_codusu"),
						  res1.getString("pla_nombre"), 
						  res1.getDate("pla_fechaCreacion")
						  ));
			}
			
			return this.mgpla;
		} catch (SQLException e) {
			System.out.println("Error añadiendo a me gusta una cancion");
			e.printStackTrace();
			return null;
		}
		
	}
		
	public boolean deleteMgPlaylist(int idpla) {
		int x=0;
		PreparedStatement stm;
		ResultSet res;
		Conexion.open();
		
		String select = "select * from usuarios_gusta_playlists where upl_codusu=? and upl_codpla=?";

		try {
			stm=Conexion.con.prepareStatement(select);
			stm.setInt(1, this.getId());
			stm.setInt(2, idpla);
			
			res = stm.executeQuery();
			if (res.next()) x=1;
			
		} catch (SQLException e) {
			System.out.println("Problema buscando el usuario para borrar");
			return false;
		}
		
		if (x==1) {
			String delete="delete from usuarios_gusta_playlists where upl_codusu=? and upl_codpla=?";
			
			
			try {
				stm=Conexion.con.prepareStatement(delete);
				stm.setInt(1, this.getId());
				stm.setInt(2, idpla);
				
				stm.executeUpdate();
	
			} catch (SQLException e) {
				return false;
			}
			return true;
		}
		else return false;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public String getEmail() {
		return email;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public String getNick() {
		return nick;
	}

	public LinkedList<Cancion> getMgsong() {
		return mgsong;
	}

	public LinkedList<Album> getMgalb() {
		return mgalb;
	}

	public LinkedList<Cantante> getMgcan() {
		return mgcan;
	}
}
