package com.myscrum.model;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import jxl.*;
import java.util.*;

import javax.swing.JOptionPane;

import jxl.Workbook;

import jxl.write.*;

public class ExportarTarefasXLS {

	public static void exportar(ResultSet tarefas, String caminho) {
		try {
			String filename = caminho + ".xls";
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
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(4, 0, "Centro de custo", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(5, 0, "Departamento", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(6, 0, "Prazo", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(7, 0, "Data de inicio", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(8, 0, "Data real", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(9, 0, "Data fim", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(10, 0, "Porcentagem", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(11, 0, "Predecessor 1", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(12, 0, "Predecessor 2", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(13, 0, "Predecessor 3", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(14, 0, "Etapa", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(15, 0, "SubEtapa", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(16, 0, "Processo", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(17, 0, "Checado", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(18, 0, "Responsavel", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(19, 0, "Autoridade", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(20, 0, "Pendente por", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(21, 0, "Porcento 1", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(22, 0, "Executor 1", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(23, 0, "Porcento 2", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(24, 0, "Executor 2", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(25, 0, "Porcento 3", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(26, 0, "Executor 3", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(27, 0, "Porcento 4", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(28, 0, "Executor 4", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(29, 0, "Porcento 5", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(30, 0, "Executor 5", cf);
		planilha.addCell(titulo);
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(31, 0, "Porcento 6", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(32, 0, "Executor 6", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(33, 0, "Porcento 7", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(34, 0, "Executor 7", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(35, 0, "Porcento 8", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(36, 0, "Executor 8", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(37, 0, "Porcento 9", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(38, 0, "Executor 9", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(39, 0, "Porcento 10", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(40, 0, "Executor 10", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(41, 0, "Status pendencia", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(42, 0, "Historico", cf);
		planilha.addCell(titulo);
		
		/* Cria um label e escreve a data em uma célula da folha */
		titulo = new Label(43, 0, "ID_Tarefa", cf);
		planilha.addCell(titulo);
		
		
		int linhasExportadas = 0;
		try {
			for (int linha = 1; linha <= tarefas.getRow(); linha++) {
					linhasExportadas++;
					
					valor = new Label(0, linha, tarefas.getString("descri"));
					planilha.addCell(valor);
					
					valor = new Label(1, linha, tarefas.getString("prioridade"));
					planilha.addCell(valor);
					
					valor = new Label(2, linha, tarefas.getString("descricao"));
					planilha.addCell(valor);
					
					valor = new Label(3, linha, tarefas.getString("stat"));
					planilha.addCell(valor);
					
					valor = new Label(4, linha, tarefas.getString("centrocusto"));
					planilha.addCell(valor);
					
					valor = new Label(5, linha, tarefas.getString("departamento"));
					planilha.addCell(valor);
					
					valor = new Label(6, linha, tarefas.getString("prazo"));
					planilha.addCell(valor);
					
					valor = new Label(7, linha, new SimpleDateFormat("dd/MM/yyyy").format(tarefas.getDate("data_ini")));
					planilha.addCell(valor);
					
					valor = new Label(8, linha, new SimpleDateFormat("dd/MM/yyyy").format(tarefas.getDate("data_real")));
					planilha.addCell(valor);
					
					valor = new Label(9, linha, new SimpleDateFormat("dd/MM/yyyy").format(tarefas.getDate("data_fim")));
					planilha.addCell(valor);
					
					valor = new Label(10, linha, tarefas.getString("porcentagem"));
					planilha.addCell(valor);
					
					valor = new Label(11, linha, tarefas.getString("predecessor_1"));
					planilha.addCell(valor);
					
					valor = new Label(12, linha, tarefas.getString("predecessor_2"));
					planilha.addCell(valor);
					
					valor = new Label(13, linha, tarefas.getString("predecessor_3"));
					planilha.addCell(valor);
					
					valor = new Label(14, linha, tarefas.getString("etapa"));
					planilha.addCell(valor);
					
					valor = new Label(15, linha, tarefas.getString("subetapa"));
					planilha.addCell(valor);
					
					valor = new Label(16, linha, tarefas.getString("processo_relacionado"));
					planilha.addCell(valor);
					
					valor = new Label(17, linha, tarefas.getString("checado"));
					planilha.addCell(valor);
					
					valor = new Label(18, linha, tarefas.getString("responsavel"));
					planilha.addCell(valor);
					
					valor = new Label(19, linha, tarefas.getString("autoridade"));
					planilha.addCell(valor);
					
					valor = new Label(20, linha, tarefas.getString("pendente_por"));
					planilha.addCell(valor);
					
					valor = new Label(21, linha, tarefas.getString("porcento1"));
					planilha.addCell(valor);
					
					valor = new Label(22, linha, tarefas.getString("executor1"));
					planilha.addCell(valor);
					
					valor = new Label(23, linha, tarefas.getString("porcento2"));
					planilha.addCell(valor);
					
					valor = new Label(24, linha, tarefas.getString("executor2"));
					planilha.addCell(valor);
					
					valor = new Label(25, linha, tarefas.getString("porcento3"));
					planilha.addCell(valor);
					
					valor = new Label(26, linha, tarefas.getString("executor3"));
					planilha.addCell(valor);
					
					valor = new Label(27, linha, tarefas.getString("porcento4"));
					planilha.addCell(valor);
					
					valor = new Label(28, linha, tarefas.getString("executor4"));
					planilha.addCell(valor);
					
					valor = new Label(29, linha, tarefas.getString("porcento5"));
					planilha.addCell(valor);
					
					valor = new Label(30, linha, tarefas.getString("executor5"));
					planilha.addCell(valor);
					
					valor = new Label(31, linha, tarefas.getString("porcento6"));
					planilha.addCell(valor);
					
					valor = new Label(32, linha, tarefas.getString("executor6"));
					planilha.addCell(valor);
					
					valor = new Label(33, linha, tarefas.getString("porcento7"));
					planilha.addCell(valor);
					
					valor = new Label(34, linha, tarefas.getString("executor7"));
					planilha.addCell(valor);
					
					valor = new Label(35, linha, tarefas.getString("porcento8"));
					planilha.addCell(valor);
					
					valor = new Label(36, linha, tarefas.getString("executor8"));
					planilha.addCell(valor);
					
					valor = new Label(37, linha, tarefas.getString("porcento9"));
					planilha.addCell(valor);
					
					valor = new Label(38, linha, tarefas.getString("executor9"));
					planilha.addCell(valor);
					
					valor = new Label(39, linha, tarefas.getString("porcento10"));
					planilha.addCell(valor);
					
					valor = new Label(40, linha, tarefas.getString("executor10"));
					planilha.addCell(valor);
					
					valor = new Label(41, linha, tarefas.getString("status_pendencia"));
					planilha.addCell(valor);
					
					valor = new Label(42, linha, tarefas.getString("historico"));
					planilha.addCell(valor);
					
					valor = new Label(43, linha, tarefas.getString("id_tarefa"));
					planilha.addCell(valor);
					
					tarefas.next();
			
			}
			
			JOptionPane.showMessageDialog(null, linhasExportadas + " Tarefas(s) exportada(s) com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
