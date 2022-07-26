package Controlador;

import java.util.Scanner;

import Modelo.Usuario;

public class AppInvitado {
	
	static Scanner sc=new Scanner(System.in);
	Vista.AppInvitado VentanaUser;
	
	public AppInvitado(Usuario user) {
		VentanaUser = new Vista.AppInvitado();
	}
}
