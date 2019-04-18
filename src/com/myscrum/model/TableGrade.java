package com.myscrum.model;


import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import com.myscrum.banco.BD;
import com.towel.swing.table.TableFilter;


public class TableGrade{
	private static Vector<String> cabecalho;
	private static Vector<Vector<String>> linhas;
	private static ResultSetMetaData rsmd;
	

	public static JTable getTable(BD bd, String sql, Vector<String> cabecalhoPersonalizado){
		JTable table;
		try 
		{	
			cabecalho = cabecalhoPersonalizado;

			linhas = new Vector<Vector<String>>();
			
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			bd.rs.next();

			// busca metadados 
		    rsmd = bd.rs.getMetaData();

			// busca dados das linhas
			do 
			{

				Vector<String> linhaAtual = new Vector<String>();
				DecimalFormat df = new DecimalFormat("R$ 00.00");
				for ( int i = 1; i <= rsmd.getColumnCount(); i++ )
				{

					switch( rsmd.getColumnType(i)) 
					{

					case Types.LONGVARCHAR:
						linhaAtual.addElement(bd.rs.getString(i));break;
					case Types.BIT:
						linhaAtual.addElement(bd.rs.getString(i));break;
					case Types.VARCHAR:
						linhaAtual.addElement(bd.rs.getString(i));break;
					case Types.CHAR:
						linhaAtual.addElement(bd.rs.getString(i));break;
					case Types.DOUBLE:
						linhaAtual.addElement(""+bd.rs.getDouble(i));break;
					case Types.INTEGER:
						linhaAtual.addElement(""+bd.rs.getInt(i));break;
					case Types.NUMERIC:
						linhaAtual.addElement(""+df.format(bd.rs.getDouble(i)));break;
					case Types.SMALLINT:
						linhaAtual.addElement(""+bd.rs.getInt(i));break;
					case Types.DECIMAL:
						linhaAtual.addElement(""+bd.rs.getDouble(i));break;
					case Types.DATE:
						if(bd.rs.getDate(i) == null) {
							linhaAtual.addElement("Sem Data");break;
						}else {
						   String s = new SimpleDateFormat("dd/MM/yyyy").format(bd.rs.getDate(i));// Tranformando data para BR
						   linhaAtual.addElement(s);break;
						}
					case Types.TIMESTAMP:
						if(bd.rs.getDate(i) == null) {
							linhaAtual.addElement("Sem Data");break;
						}else {
						   String s = new SimpleDateFormat("dd/MM/yyyy").format(bd.rs.getDate(i));// Tranformando data para BR
						   linhaAtual.addElement(s);break;
					    }
						//					default:System.out.println(rsmd.getColumnType(i));   
					}
				}
				linhas.addElement(linhaAtual);     
			} 
			while (bd.rs.next());       

			table = new JTable(linhas,cabecalho){
			
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex,int collIndex ){
					return false;	
				}
			};
			table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			table.setSelectionForeground(Color.WHITE);
			table.setSelectionBackground(new Color(42, 42, 180));
			table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			table.setFont(new Font("Arial", Font.ITALIC, 13));
			table.setBackground(Color.WHITE);
			table.setShowVerticalLines(true);
			table.setShowHorizontalLines(true);
			table.getTableHeader().setReorderingAllowed(false);
			table.setRowHeight(25);
			table.setModel(new TableFilter(table));
			table.setDefaultRenderer(Object.class, new LivroCellRenderer());
		
	

			@SuppressWarnings("unused")
			JTableHeader header = table.getTableHeader();
			

		}

		catch (SQLException erro) 
		{ 
			System.out.println(erro.toString());
			return null;
		}
		return table;
	}


	public static JTable getTableSemBanco(@SuppressWarnings("rawtypes") Vector<ArrayList> matriz, Vector<String> cabecalhoPersonalizado) {
		JTable table;
		Vector<String> cabecalho = cabecalhoPersonalizado;
		Vector<Vector<Object>> linhas = new Vector<Vector<Object>>();
		
		int a = 0;
		int i = 0;
		do 
		{

			Vector<Object> linhaAtual = new Vector<Object>();
			for (i = 0; i < matriz.size(); i++ )
			{
				try {
				linhaAtual.addElement(matriz.get(i).get(a).toString());
				}catch(Exception erro) {
				linhaAtual.addElement("");
				}
				
			}
			
			linhas.addElement(linhaAtual);   
			a++;
			
		} 
		
		while(a<matriz.get(0).size());    

		
		table = new JTable(linhas,cabecalho){

			
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex,int collIndex ){
				return false;	
			}
		};
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setSelectionForeground(Color.WHITE);
		table.setSelectionBackground(new Color(42, 42, 180));
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setFont(new Font("Arial", Font.ITALIC, 13));
		table.setBackground(Color.WHITE);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(25);
		table.setModel(new TableFilter(table));
		table.setDefaultRenderer(Object.class, new LivroCellRenderer());

		@SuppressWarnings("unused")
		JTableHeader header = table.getTableHeader();
		
		return table;
  }
	
	public void limpar(JTable tabela) {
		if (tabela != null) {// se existir outra tabela montada apaga
			for(int a = 1; a < tabela.getColumnCount(); a++) {
				for(int b = 1; b < tabela.getRowCount(); b++) {
					tabela.removeRowSelectionInterval(a, b);
					System.out.println(a + " x " + b);
				}
			}
		}
	}
}

