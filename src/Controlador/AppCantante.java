package Controlador;

import java.util.Scanner;

import Modelo.Cantante;

public class AppCantante {
	
	static Scanner sc=new Scanner(System.in);
	Vista.AppCantante VentanaCantante;
	
	public AppCantante(Cantante userCa) {
		VentanaCantante = new Vista.AppCantante();
	}
}
