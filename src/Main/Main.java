package Main;

import java.util.Scanner;

import javax.swing.UIManager;

import Modelo.Conexion;
import Modelo.Usuario;

public class Main {
	// quiero que el jlsit se actualize solo que nose porque no lo hace y que no repita canciones 
	
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
	//cuando haga doble click abrire una nueva ventana con los datos de la cancion y podre o del cantante o meno diferentes ventanas por cada tipo 
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {	
		}
		
		
		//new Controlador.AppInicioSesion();
		new Controlador.AppUser(Usuario.load("Lolo"));
	}
}
