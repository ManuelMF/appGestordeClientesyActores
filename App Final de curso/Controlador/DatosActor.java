package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.LinkedList;

import Modelo.Actor;
import Modelo.City;
import Modelo.Cliente;
import Modelo.Country;

public class DatosActor {
	
	Vista.DatosActor VentanaDatosActor;
	
	public DatosActor(Actor c) {
		VentanaDatosActor = new Vista.DatosActor();
		rellenarDatos(c);
		
		VentanaDatosActor.btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = VentanaDatosActor.textFieldNombre.getText();
				String apellidos = VentanaDatosActor.textFieldApellidos.getText();
				
				if (c==null) {
					Modelo.Actor.create(nombre, apellidos);
					Vista.Tabla.loadActores();
					VentanaDatosActor.dispose();
				} else {
					c.update(nombre, apellidos);
					Vista.Tabla.loadActores();
					VentanaDatosActor.dispose();
				}
			}
		});
		
		VentanaDatosActor.btnVaciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaDatosActor.textFieldNombre.setText("");
				VentanaDatosActor.textFieldApellidos.setText("");
			}
		});
		
		VentanaDatosActor.btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c==null) {
					VentanaDatosActor.dispose();
				} else {
					Actor.delete(c.id);
					Vista.Tabla.loadActores();
					VentanaDatosActor.dispose();
				}
			}
		});
	}
	

	private void rellenarDatos(Actor c) {
		if (c!=null) {
			VentanaDatosActor.textFieldNombre.setText(c.Nombre);
			VentanaDatosActor.textFieldApellidos.setText(c.Apellidos);

		}
		
	}

}
