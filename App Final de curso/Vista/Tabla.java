package Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Modelo.Actor;
import Modelo.Cliente;


public class Tabla {
	public JTable tabla;
	public DefaultTableModel modelo;
	public Color colorPar=SystemColor.menu;
	public Color colorImpar=Color.WHITE;
	public Color colorSel=UIManager.getColor("Table.selectionBackground");
	private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();	
	
	
	
	public Tabla(Object[] cabecera, Class<?>[] tipos, Integer[] medidas, Color par, Color impar, Color sel) {
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		leftRenderer.setHorizontalAlignment( SwingConstants.LEFT );
		rightRenderer.setHorizontalAlignment( SwingConstants.RIGHT );
		if (par!=null) colorPar=par;
		if (impar!=null) colorImpar=impar;
		modelo= new DefaultTableModel(null,	cabecera){
			private static final long serialVersionUID = 9209503312321682270L;

			public Class<?> getColumnClass(int columnIdex){
				return tipos[columnIdex];
			}
			public boolean isCellEditable(int row, int column) {
				 if (tipos[column]!=Boolean.class && tipos[column]!=boolean.class) return false;
				 return true;
			    }
		};	
		tabla = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		        Component c = super.prepareRenderer(renderer, row, column);
		        if (!isRowSelected(row))c.setBackground(row%2==0? colorPar:colorImpar);
				else c.setBackground(colorSel);
		        return c;
		    }
		};
		tabla.setShowGrid(false);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.setModel(modelo);
		
		tabla.setRowHeight(25);
		tabla.setAutoCreateRowSorter(true);

		for (int x=0;x<medidas.length;x++){
			tabla.getColumnModel().getColumn(x).setPreferredWidth(medidas[x]);
			tabla.getColumnModel().getColumn(x).setMinWidth(medidas[x]);
		}

		DefaultTableCellRenderer render=new DefaultTableCellRenderer(){
			private static final long serialVersionUID = 5161847153923664111L;

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
				if (value instanceof JButton) {
				   return (JButton) value;
				}
				if (value instanceof JComboBox) {
					   return (JComboBox<?>) value;
					}
				return null;
			}
		};
		
		/*tabla.setDefaultEditor(Object.class, null);
		tabla.setDefaultEditor(Double.class, null);
		tabla.setDefaultEditor(Integer.class, null);
		tabla.setDefaultEditor(String.class, null);
		tabla.setDefaultEditor(Fecha.class, null);*/
		
		tabla.setDefaultRenderer(JButton.class, render);
		tabla.setDefaultRenderer(JComboBox.class, render);
	
		
	}
	
	public void setResizableColumn(int columIndex, boolean fijo){
		int medida=tabla.getColumnModel().getColumn(columIndex).getPreferredWidth();
		if (fijo) tabla.getColumnModel().getColumn(columIndex).setMaxWidth(10000);
		else tabla.getColumnModel().getColumn(columIndex).setMaxWidth(medida);
		
	}

	public void hideColumn(int i) {
		tabla.removeColumn(tabla.getColumnModel().getColumn(i));
		
	}
	public int getRealSelectedRow() {
		return tabla.getRowSorter().convertRowIndexToModel(tabla.getSelectedRow());
		
	}
	public int getRealSelectedColumn() {
		return tabla.convertColumnIndexToModel(tabla.getSelectedColumn());
		
	}
	public Object getValueSelected(int columnIndex) {
		return getValueAt(tabla.getSelectedRow(), columnIndex);
	}
	
	public Object getValueAt(int fileIndex, int columnIndex) {
		if (fileIndex<0) return null;
		return modelo.getValueAt(tabla.getRowSorter().convertRowIndexToModel(fileIndex), columnIndex); 
	}
	public void vaciar() {
		modelo.setRowCount(0);
	}
	public void alinear(char a, int columIndex) {
		if (Character.toLowerCase(a)=='l') tabla.getColumnModel().getColumn(columIndex).setCellRenderer( leftRenderer );
		if (Character.toLowerCase(a)=='r') tabla.getColumnModel().getColumn(columIndex).setCellRenderer( rightRenderer ); 
		if (Character.toLowerCase(a)=='c') tabla.getColumnModel().getColumn(columIndex).setCellRenderer( centerRenderer ); 
	}
	public void alinearHeader(char a, int columIndex) {
		if (Character.toLowerCase(a)=='l') tabla.getTableHeader().getColumnModel().getColumn(columIndex).setHeaderRenderer(leftRenderer);
		if (Character.toLowerCase(a)=='r') tabla.getTableHeader().getColumnModel().getColumn(columIndex).setHeaderRenderer(rightRenderer);
		if (Character.toLowerCase(a)=='c') tabla.getTableHeader().getColumnModel().getColumn(columIndex).setHeaderRenderer(centerRenderer);
	}
	
	public static int loadObjetos() {
		Vista.Menu.mitabla.vaciar();
		LinkedList<Cliente> listaClientes = Cliente.find(null, null, null, null);
		int x=0;
		for (Cliente c : listaClientes) {
			SimpleDateFormat formato = new SimpleDateFormat ("dd-MM-yyyy");
			
			String fechaTexto = formato.format(c.fechaCreacion);
			
			Object[] datos = { c.id, c.nombre,c.apellidos,c.telefono, fechaTexto };
			Vista.Menu.mitabla.modelo.addRow(datos);
			x++;
		}
		return x;
	}
	
	public static int loadActores() {
		Vista.Menu.mitabla.vaciar();
		LinkedList<Actor> listaClientes = Actor.find(null,null, null);
		int x=0;
		for (Actor a : listaClientes) {
			SimpleDateFormat formato = new SimpleDateFormat ("dd-MM-yyyy");
			
			String fechaTexto = formato.format(a.fechaCreacion);
			Object[] datos = { a.id, a.Nombre,a.Apellidos,null,fechaTexto};
			Vista.Menu.mitabla.modelo.addRow(datos);
			x++;
		}
		return x;
	}
}


