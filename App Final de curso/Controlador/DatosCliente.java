package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.LinkedList;

import Modelo.City;
import Modelo.Cliente;
import Modelo.Country;

public class DatosCliente {
	
	Vista.DatosCliente VentanaDatosCliente;
	
	public DatosCliente(Cliente c) {
		VentanaDatosCliente = new Vista.DatosCliente();
		rellenarPaises();
		rellenarCiudad();
		rellenarDatos(c);
		Update(c);
		
		VentanaDatosCliente.comboBoxPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rellenarCiudad();
			}
		});
		VentanaDatosCliente.btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = VentanaDatosCliente.textFieldNombre.getText();
				String apellidos = VentanaDatosCliente.textFieldApellidos.getText();
				String email = VentanaDatosCliente.textFieldEmail.getText();
				String telefono = VentanaDatosCliente.textFieldTelefono.getText();
				String direccion1 = VentanaDatosCliente.textFieldDireccion1.getText();
				String direccion2 = VentanaDatosCliente.textFieldDireccion2.getText();
				String poblacion = VentanaDatosCliente.textFieldPoblacion.getText();
				String cp = VentanaDatosCliente.textFieldCodigoPostal.getText();
				String city = (String) VentanaDatosCliente.comboBoxCiudad.getSelectedItem();
				int cid =  Cliente.BuscarCip(city);
				if (c==null) {
					Modelo.Cliente.create(nombre, apellidos, email, telefono, direccion1, direccion2, poblacion, Modelo.Cliente.BuscarCip(city), cp);
					Vista.Tabla.loadObjetos();
					VentanaDatosCliente.dispose();
				} else {
					c.update(nombre, apellidos, email, telefono, direccion1, direccion2, poblacion, cp, city, cid);
					Vista.Tabla.loadObjetos();
					VentanaDatosCliente.dispose();
				}
			}
		});
		
		VentanaDatosCliente.btnVaciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaDatosCliente.textFieldNombre.setText("");
				VentanaDatosCliente.textFieldApellidos.setText("");
				VentanaDatosCliente.textFieldEmail.setText("");
				VentanaDatosCliente.textFieldTelefono.setText("");
				VentanaDatosCliente.textFieldDireccion1.setText("");
				VentanaDatosCliente.textFieldDireccion2.setText("");
				VentanaDatosCliente.textFieldPoblacion.setText("");
				VentanaDatosCliente.textFieldCodigoPostal.setText("");
				VentanaDatosCliente.comboBoxPais.setSelectedItem(null);
				VentanaDatosCliente.comboBoxCiudad.setSelectedItem(null);
			}
		});
		
		VentanaDatosCliente.btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c==null) {
					VentanaDatosCliente.dispose();
				} else {
					c.delete();
					Vista.Tabla.loadObjetos();
					VentanaDatosCliente.dispose();
				}
			}
		});
	}
	
	private void Update(Cliente c) {
		if (c!=null) {
			VentanaDatosCliente.textFieldNombre.setEditable(false);
			VentanaDatosCliente.textFieldApellidos.setEditable(false);
		}
	}

	private void rellenarDatos(Cliente c) {
		if (c!=null) {
			VentanaDatosCliente.textFieldNombre.setText(c.nombre);
			VentanaDatosCliente.textFieldApellidos.setText(c.apellidos);
			VentanaDatosCliente.textFieldEmail.setText(c.email);
			VentanaDatosCliente.textFieldTelefono.setText(c.telefono);
			VentanaDatosCliente.textFieldDireccion1.setText(c.direccion);
			VentanaDatosCliente.textFieldDireccion2.setText(c.direccion2);
			VentanaDatosCliente.textFieldPoblacion.setText(c.poblacion);
			VentanaDatosCliente.textFieldCodigoPostal.setText(c.cp);
			City city = c.BuscarPaisporCip();
			VentanaDatosCliente.comboBoxPais.setSelectedItem(Country.load(city.country_id).country);
			rellenarCiudad();
			VentanaDatosCliente.comboBoxCiudad.setSelectedItem(city.city);
			
		}
		
	}

	public void rellenarPaises() {
		
		LinkedList<Country> lista = Modelo.Country.find(null);
		
		for (Country c : lista) {
			VentanaDatosCliente.comboBoxPais.addItem(c.toString());
		}
		VentanaDatosCliente.comboBoxPais.setSelectedItem(null);;
	}
	
	public void rellenarCiudad() {
		VentanaDatosCliente.comboBoxCiudad.removeAllItems();
		String name = (String) VentanaDatosCliente.comboBoxPais.getSelectedItem();
		Country c  = Country.find(name).getFirst();
		
		LinkedList<City> lista;
		
		if (c==null) lista = Modelo.City.find(null, null);
		else lista = Modelo.City.find(null, c.country_id);

		for (City ccc : lista) {
			VentanaDatosCliente.comboBoxCiudad.addItem(ccc.toString());
		}
		VentanaDatosCliente.comboBoxCiudad.setSelectedItem(null);
	}
}
