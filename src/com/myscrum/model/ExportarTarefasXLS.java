package com.myscrum.model;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import jxl.*;
import java.util.*;

import javax.swing.JOptionPane;

import jxl.Workbook;

import jxl.write.*;

public class ExportarTarefasXLS {

	public static void exportar(ResultSet tarefas, String caminho) {
		try {
			String filename = caminho+"Tarefas.xls";
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			WritableWorkbook workbook = Workbook.createWorkbook(new File(filename), ws);
			WritableSheet planilha = workbook.createSheet("Tarefas", 0);
			writeDataSheet(planilha, tarefas);
			workbook.write();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	private static void writeDataSheet(WritableSheet planilha, ResultSet tarefas) throws WriteException {

		/* Formata a fonte */
		WritableFont fonteTitulo = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat cf = new WritableCellFormat(fonteTitulo);
		cf.setWrap(true);

		Label titulo;
		Label valor;

		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(0, 0, "Descrição", cf);
		planilha.addCell(titulo);

		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(1, 0, "Prioridade", cf);
		planilha.addCell(titulo);

		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(2, 0, "Tamanho", cf);
		planilha.addCell(titulo);

		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(3, 0, "Status", cf);
		planilha.addCell(titulo);

		try {
			for (int linha = 1; linha <= tarefas.getRow(); linha++) {
				JOptionPane.showMessageDialog(null, "entrou no for");
				if (tarefas.next()) {
					for (int coluna = 0; coluna <= 3; coluna++) {
						System.out.println(tarefas.getString(coluna));
						// Parametros: 1º Coluna, 2º Linha, 3º Valor
						valor = new Label(coluna, linha, tarefas.getString(coluna));
						planilha.addCell(valor);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
