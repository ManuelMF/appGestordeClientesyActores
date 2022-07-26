                                                                                                                                                                                                                                                                      package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import Modelo.Actor;
import Modelo.Cliente;

public class Menu {
	Vista.Menu VentanaMenu;
	Vista.Buscador VentanaBuscador;
	
	public Menu() {
		
		VentanaMenu =  new Vista.Menu();	
		
		Vista.Menu.mitabla.tabla.addKeyListener(new KeyAdapter() {
	        	public void keyPressed(KeyEvent e) {
	        		KeyListener(e);
	        	}
	        });
		
		VentanaMenu.btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comprobadorCliente_Actor()==0) new Controlador.DatosCliente(null);
				else new Controlador.DatosActor(null);
			}
		});
		
		VentanaMenu.mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenu.dispose();
			}
		});
		
		VentanaMenu.btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearVentanaBuscar();
			}
		});
		
		VentanaMenu.btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrar();
			}
		});
		
		VentanaMenu.btnQuitarFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				if (comprobadorCliente_Actor()==0) Vista.Tabla.loadObjetos();
				else Vista.Tabla.loadActores();
				} catch(IndexOutOfBoundsException ee) {
					Vista.Tabla.loadObjetos();
				}
				
			}
		});
		
		VentanaMenu.mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Vista.AcercaDe();
			}
		});
		
		VentanaMenu.mntmActores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vista.Tabla.loadActores();
			}
		});
		
		VentanaMenu.mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vista.Tabla.loadObjetos();
			}
		});
		
		Vista.Menu.mitabla.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2 && e.getButton()==1) {
                    int id = (int) Vista.Menu.mitabla.getValueSelected(0);
                    if (comprobadorCliente_Actor()==0) {
                    	Cliente c = Cliente.load(id);
                    	new Controlador.DatosCliente(c);
                    } else {
                    	Actor a = Actor.load(id);
                    	new Controlador.DatosActor(a);
                    }
                }
            }
        });
		
		Vista.Menu.mitabla.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	if (e.getClickCount()==1 && e.getButton()==1) {
                    try {
	                	int id = Vista.Menu.mitabla.tabla.getSelectedRow();
	                	if (id==-1) {
	                		String text = Vista.Menu.lblBusqueda.getText();
	                    	Vista.Menu.lblBusqueda.setText(text.substring(0, 12));
	                	} else {
	                		String text = Vista.Menu.lblBusqueda.getText();
	                		Vista.Menu.lblBusqueda.setText(text.substring(0, 12)+"  Fila actual:"+id);
	                	}
                    } catch (java.lang.NullPointerException ee) {
                    	String text = Vista.Menu.lblBusqueda.getText();
                    	Vista.Menu.lblBusqueda.setText(text.substring(0, 12));
					}
                }
            }
        });
		
	}
	
	public static int comprobadorCliente_Actor() {
		int PrimerId = (int) Vista.Menu.mitabla.getValueAt(0, 0);
		String PrimerNombre = (String) Vista.Menu.mitabla.getValueAt(0, 1);
		
		if (Cliente.load(PrimerId).nombre.equals(PrimerNombre)) return 0;
		return 1;
	}
	
	private void Buscar() {
		
		if (comprobadorCliente_Actor()==0) {
			
			Integer id = (int) VentanaBuscador.spinnerid.getValue();
			String nombre = VentanaBuscador.textFieldNombre.getText();
			String apellidos = VentanaBuscador.textFieldApellido.getText();
			String telefono = VentanaBuscador.textFieldTelefono.getText();
			
			if (id==0) id=null;
			LinkedList<Cliente> list = Cliente.find(id, nombre, apellidos, telefono);
			Vista.Menu.mitabla.vaciar();
			
			int repeticiones = 0;
			for (Cliente c : list) {
				Object[] datos = { c.id, c.nombre,c.apellidos,c.telefono, c.fechaCreacion };
				Vista.Menu.mitabla.modelo.addRow(datos);
				repeticiones++;
			}
			if (repeticiones==1) Vista.Menu.lblBusqueda.setText("  Fila: "+repeticiones);
			else Vista.Menu.lblBusqueda.setText("  Filas: "+repeticiones);
			
			VentanaBuscador.dispose();
		
		} else {
			Integer id = (int) VentanaBuscador.spinnerid.getValue();
			String nombre = VentanaBuscador.textFieldNombre.getText();
			String apellidos = VentanaBuscador.textFieldApellido.getText();
			
			if (id==0) id=null;
			LinkedList<Actor> list = Actor.find(id, nombre, apellidos);
			Vista.Menu.mitabla.vaciar();
			
			int repeticiones = 0;
			for (Actor a : list) {
				SimpleDateFormat formato = new SimpleDateFormat ("dd-MM-yyyy");
				
				String fechaTexto = formato.format(a.fechaCreacion);
				Object[] datos = { a.id, a.Nombre,a.Apellidos,null ,fechaTexto };
				Vista.Menu.mitabla.modelo.addRow(datos);
				repeticiones++;
			}
			if (repeticiones==1) Vista.Menu.lblBusqueda.setText("  Fila: "+repeticiones);
			else Vista.Menu.lblBusqueda.setText("  Filas: "+repeticiones);
			
			VentanaBuscador.dispose();
		}
	}
	
	public void CrearVentanaBuscar() {
		VentanaBuscador = new Vista.Buscador();
		
		VentanaBuscador.btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar();
			}
		});
	}
	
	public void borrar() {
		if (comprobadorCliente_Actor()==0) {
			for (int num :Vista.Menu.mitabla.tabla.getSelectedRows()) {
				
				int n = (int) Vista.Menu.mitabla.getValueAt(num, 0);
				Cliente c = Cliente.load(n);
				c.delete();
			}
			Vista.Tabla.loadObjetos();
		} else {
			for (int num :Vista.Menu.mitabla.tabla.getSelectedRows()) {
				
				int n = (int) Vista.Menu.mitabla.getValueAt(num, 0);
				Actor c = Actor.load(n);
				Actor.delete(c.id);
			}
			Vista.Tabla.loadActores();
		}
	}
	
	public void KeyListener(KeyEvent e) {
		
		if (e.getKeyCode()==67) {
			if (comprobadorCliente_Actor()==0) new Controlador.DatosCliente(null);
			else new Controlador.DatosActor(null);
		} else if (e.getKeyCode()==68) {
			borrar();
		} else if(e.getKeyCode()==66) {
			CrearVentanaBuscar();
		} else if(e.getKeyCode()==65) {
			new Vista.AcercaDe();
		}
	}
}
