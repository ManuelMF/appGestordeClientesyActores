package Controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Modelo.Cantante;
import Modelo.Usuario;
import Vista.FrameInicioSesion;

public class AppCrearCuenta {
	
	static Scanner sc=new Scanner(System.in);
	Vista.FrameCrearCuenta VentanaCrearCuenta;
	Controlador.AppInicioSesion VentanainicioSesion;
	
	public AppCrearCuenta() {
		VentanaCrearCuenta = new Vista.FrameCrearCuenta();
		
		VentanaCrearCuenta.btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearCuenta();
			}
		});
		
		VentanaCrearCuenta.btnIrIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCrearCuenta.dispose();
				VentanainicioSesion = new AppInicioSesion();
			}
		});
		
		VentanaCrearCuenta.btnEntrarInvitado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCrearCuenta.dispose();
				new Controlador.AppInvitado(Usuario.load("Invitado"));
			}
		}); 
	}
	
	public void CrearCuenta() {
		
		VentanaCrearCuenta.lblFalloInicioSesion.setVisible(false);
		String Correo = VentanaCrearCuenta.txtCorreo.getText();
		String Nick = VentanaCrearCuenta.textField_nick.getText();
		String Nombre = VentanaCrearCuenta.textField_Nombre.getText();
		String Apellido = VentanaCrearCuenta.textField_Apellido.getText();
		String Date = VentanaCrearCuenta.textFieldDate.getText();
		char[] arrayC = VentanaCrearCuenta.passwordField.getPassword();
		String pass = new String(arrayC);
		
		Usuario user = null; int x = 0;
		
		if (pass.length()<3) {
			VentanaCrearCuenta.lblFalloInicioSesion.setText("La contraseña tiene que ser mas larga");
			VentanaCrearCuenta.lblFalloInicioSesion.setVisible(true);
			x=1;
		}
		
		
		// fecha
		boolean error=true;
		SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy"),  formatoaño = new SimpleDateFormat("yyyy");
		Date miFecha = null, Fecha18 = new Date();
		int dia = 0, mes = 0, anyo = 0, diamin=1,diamax=31,mesmin=1,mesmax=12, añomin=Integer.parseInt(formatoaño.format(Fecha18)),añomax=1900;
		String fecha = VentanaCrearCuenta.textFieldDate.getText();
		
		try {
		dia = Integer.parseInt(fecha.substring(0, 2));
		mes = Integer.parseInt(fecha.substring(3, 5));
		anyo = Integer.parseInt(fecha.substring(6, 10));
		} catch (Exception e) {
			VentanaCrearCuenta.lblFalloInicioSesion.setText("Formato incorrecto dd/mm/yyyy");
			VentanaCrearCuenta.lblFalloInicioSesion.setVisible(true);
			x=1;
		}
		if (dia<diamin||diamax<dia||mesmin>mes||mesmax<mes||añomin<anyo||añomax>anyo) {
			VentanaCrearCuenta.lblFalloInicioSesion.setText("Edad erronea");
			VentanaCrearCuenta.lblFalloInicioSesion.setVisible(true);
			x=1;
		}
		
		try {
			miFecha=formato1.parse(dia+"/"+mes+"/"+anyo);
		} catch (ParseException e) {
			x=1;
		}
		
		if (Apellido.length()<3) { 
			VentanaCrearCuenta.lblFalloInicioSesion.setText("Apellido invalido");
			VentanaCrearCuenta.lblFalloInicioSesion.setVisible(true);
			x=1;
		}
		
		if (Nombre.length()<3) { 
			VentanaCrearCuenta.lblFalloInicioSesion.setText("Nombre invalido");
			VentanaCrearCuenta.lblFalloInicioSesion.setVisible(true);
			x=1;
		}
		
		if (Usuario.load(Nick)!=null) {
			VentanaCrearCuenta.lblFalloInicioSesion.setText("Este nombre no esta disponible");
			VentanaCrearCuenta.lblFalloInicioSesion.setVisible(true);
			x=1;
		}
		
		if (Nick.length()<3) { 
			VentanaCrearCuenta.lblFalloInicioSesion.setText("Nick invalido");
			VentanaCrearCuenta.lblFalloInicioSesion.setVisible(true);
			x=1;
		}
		
		if (Correo.contains("@")) {
			if (Correo.contains("."))  ;
			else {
				VentanaCrearCuenta.lblFalloInicioSesion.setText("El correo debe de contener .");
				VentanaCrearCuenta.lblFalloInicioSesion.setVisible(true);
				x=1;
			}
		} else {
			VentanaCrearCuenta.lblFalloInicioSesion.setText("El correo debe de contener @");
			VentanaCrearCuenta.lblFalloInicioSesion.setVisible(true);
			x=1;
		}
		
		if (x==0) {
			user = Usuario.create(Nick, Nombre, Apellido, miFecha, Correo, pass);
			VentanaCrearCuenta.dispose();
			new Controlador.AppUser(user);
		}
		
		VentanaCrearCuenta.repaint();
		VentanaCrearCuenta.revalidate();
		
		
	 
	
	
}}
