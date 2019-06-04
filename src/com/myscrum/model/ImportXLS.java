package com.myscrum.model;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import javax.swing.JOptionPane;



public class ImportXLS {

	public static void main(String[] args) throws BiffException, IOException {
		
		Workbook workbook = Workbook.getWorkbook(new File("teste.xls"));
		Sheet sheet = workbook.getSheet(0);
		int linhas = sheet.getRows();
		TarefaDAO metodos = new TarefaDAO();
		
		System.out.println("Iniciando a leitura. . .");
		
		for(int i = 1; i < linhas; i++) {
			Cell a1 = sheet.getCell(0, i);
			Cell a2 = sheet.getCell(1, i);
			Cell a3 = sheet.getCell(2, i);
			Cell a4 = sheet.getCell(3, i);
			Cell a5 = sheet.getCell(4, i);
			Cell a6 = sheet.getCell(5, i);
			Cell a7 = sheet.getCell(6, i);
			Cell a8 = sheet.getCell(7, i);
			Cell a9 = sheet.getCell(8, i);
			Cell a10 = sheet.getCell(9, i);
			Cell a11 = sheet.getCell(10, i);
			Cell a12 = sheet.getCell(11, i);
			Cell a13 = sheet.getCell(12, i);
			Cell a14 = sheet.getCell(13, i);
			Cell a15 = sheet.getCell(14, i);
			Cell a16 = sheet.getCell(15, i);
			Cell a17 = sheet.getCell(16, i);
			Cell a18 = sheet.getCell(17, i);
			Cell a19 = sheet.getCell(18, i);
			Cell a20 = sheet.getCell(19, i);
			Cell a21 = sheet.getCell(20, i);
			Cell a22 = sheet.getCell(21, i);
			Cell a23 = sheet.getCell(22, i);
			Cell a24 = sheet.getCell(23, i);
			Cell a25 = sheet.getCell(24, i);
			Cell a26 = sheet.getCell(25, i);
			Cell a27 = sheet.getCell(26, i);
			Cell a28 = sheet.getCell(27, i);
			Cell a29 = sheet.getCell(28, i);
			Cell a30 = sheet.getCell(29, i);
			Cell a31 = sheet.getCell(30, i);
			Cell a32 = sheet.getCell(31, i);
			Cell a33 = sheet.getCell(32, i);
			Cell a34 = sheet.getCell(33, i);
			Cell a35 = sheet.getCell(34, i);
			Cell a36 = sheet.getCell(35, i);
			Cell a37 = sheet.getCell(36, i);
			Cell a38 = sheet.getCell(37, i);
			Cell a39 = sheet.getCell(38, i);
			Cell a40 = sheet.getCell(39, i);
			Cell a41 = sheet.getCell(40, i);
			Cell a42 = sheet.getCell(41, i);
			Cell a43 = sheet.getCell(42, i);
			Cell a44 = sheet.getCell(43, i);
			
			String descricao = a1.getContents();
			String prioridade = a2.getContents();
			String tamanho = a3.getContents();
			String status = a4.getContents();
			String centrocusto = a5.getContents();
			String departamento = a6.getContents();
			String prazo = a7.getContents();
			String datainicio = a8.getContents();
			String datareal = a9.getContents();
			String datafim = a10.getContents();
			String porcentagem = a11.getContents();
			String predecessor1 = a12.getContents();
			String predecessor2 = a13.getContents();
			String predecessor3 = a14.getContents();
			String etapa = a15.getContents();
			String subetapa = a16.getContents();
			String processo = a17.getContents();
			String checado = a18.getContents();
			String responsavel = a19.getContents();
			String autoridade = a20.getContents();
			String pendente_por = a21.getContents();
			String porcento1 = a22.getContents();
			String executor1 = a23.getContents();
			String porcento2 = a24.getContents();
			String executor2 = a25.getContents();
			String porcento3 = a26.getContents();
			String executor3 = a27.getContents();
			String porcento4 = a28.getContents();
			String executor4 = a29.getContents();
			String porcento5 = a30.getContents();
			String executor5 = a31.getContents();
			String porcento6 = a32.getContents();
			String executor6 = a33.getContents();
			String porcento7 = a34.getContents();
			String executor7 = a35.getContents();
			String porcento8 = a36.getContents();
			String executor8 = a37.getContents();
			String porcento9 = a38.getContents();
			String executor9 = a39.getContents();
			String porcento10 = a40.getContents();
			String executor10 = a41.getContents();
			String status_pendencia = a42.getContents();
			String historico = a43.getContents();
			String id_tarefa = a44.getContents();
			
			Tarefa variavel = new Tarefa();
			
			variavel.setDescricao(descricao);
			variavel.setPrioridade(Integer.parseInt(prioridade));
			variavel.setTamanho(tamanho);
			variavel.setStatus(status);
			variavel.setCentroCusto(centrocusto);
			variavel.setDepartamento(departamento);
			variavel.setPrazo(Integer.parseInt(prazo));
			variavel.setDataInicio(datainicio);
			variavel.setDataReal(datareal);
			variavel.setDataFim(datafim);
			variavel.setPorcentagem(Integer.parseInt(porcentagem));
			
			if(predecessor1 != "") {
				variavel.setPredecessor1(Integer.parseInt(predecessor1));
			}
			
			if(predecessor2 != "") {
				variavel.setPredecessor2(Integer.parseInt(predecessor2));
			}
			if(predecessor3 != "") {
				variavel.setPredecessor3(Integer.parseInt(predecessor3));
			}
			
			variavel.setEtapa(etapa);
			variavel.setSubEtapa(subetapa);
			variavel.setProcesso(processo);
			variavel.setChecado(checado);
			variavel.setResponsavel(responsavel);
			variavel.setAutoridade(autoridade);
			variavel.setPendentePor(pendente_por);
			variavel.setExecutor1(executor1);
			variavel.setPorcento1(Integer.parseInt(porcento1));
			
			if(porcento2 != "" && executor2 != "") {
				variavel.setPorcento2(Integer.parseInt(porcento2));
				variavel.setExecutor2(executor2);
			}
			
			if(porcento3 != "" && executor3 != "") {
				variavel.setPorcento3(Integer.parseInt(porcento3));
				variavel.setExecutor3(executor3);
			}
		
			if(porcento4 != "" && executor4 != "") {
				variavel.setPorcento4(Integer.parseInt(porcento4));
				variavel.setExecutor4(executor4);
			}
			
		
			if(porcento5 != "" && executor5 != "") {
				variavel.setPorcento5(Integer.parseInt(porcento5));
				variavel.setExecutor5(executor5);
			}
			
			if(porcento6 != "" && executor6 != "") {
				variavel.setPorcento6(Integer.parseInt(porcento6));
				variavel.setExecutor6(executor6);
			}
			
			if(porcento7 != "" && executor7 != "") {
				variavel.setPorcento7(Integer.parseInt(porcento7));
				variavel.setExecutor7(executor7);
			}
			
			if(porcento8 != "" && executor8 != "") {
				variavel.setPorcento8(Integer.parseInt(porcento8));
				variavel.setExecutor8(executor8);
			}
			
			if(porcento9 != "" && executor9 != "") {
				variavel.setPorcento9(Integer.parseInt(porcento9));
				variavel.setExecutor9(executor9);
			}
			
			if(porcento10 != "" && executor10 != "") {
				variavel.setPorcento10(Integer.parseInt(porcento10));
				variavel.setExecutor10(executor10);
			}
			
			variavel.setStatusPendencia(status_pendencia);
			
			if(id_tarefa != "") {
				variavel.setIDTarefa(Integer.parseInt(id_tarefa));
			}
			
			metodos.cadastrar();
			
			
			//System.out.println("Coluna A: " + descricao);
			//System.out.println("Coluna B: " + prioridade);
			//System.out.println("Coluna C: " + tamanho);
			//System.out.println("Coluna D: " + status);
			//System.out.println("Coluna E: " + centrocusto);
			//System.out.println("Coluna F: " + departamento);
			//System.out.println("Coluna G: " + prazo);
			//System.out.println("Coluna H: " + datainicio);
			//System.out.println("Coluna I: " + datareal);
			//System.out.println("Coluna J: " + datafim);
			//System.out.println("Coluna K: " + porcentagem);
			//System.out.println("Coluna L: " + predecessor1);
			//System.out.println("Coluna L: " + predecessor2);
			//System.out.println("Coluna L: " + predecessor3);
		
		
		}
		
		workbook.close();
		
	}

}
