package com.myscrum.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ImportarTarefasXLS {
	TarefaDAO dao = new TarefaDAO();

	public ImportarTarefasXLS() {
		JFileChooser arquivo = new JFileChooser();
		arquivo.addChoosableFileFilter(new Filtro());
		arquivo.setAcceptAllFileFilterUsed(false);
		
		if (arquivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			ArrayList<Tarefa> tarefas = montarListaDeTarefas(arquivo);

			int option = JOptionPane.showConfirmDialog(null,
					"Deseja realmente cadastrar/atualizar " + tarefas.size() + " tarefa(s)");

			if (option == JOptionPane.YES_OPTION) {
				for (int x = 0; x != tarefas.size(); x++) {
					if (tarefas.get(x).getIDTarefa() != 0) {
						atualizarTarefa(tarefas.get(x));
					} else {
						inserirTarefa(tarefas.get(x));
					}
				}
				JOptionPane.showMessageDialog(null, "Processo de importação finalizado, LOG gerado na pasta do projeto");
			}
		}
	}

	public ArrayList<Tarefa> montarListaDeTarefas(JFileChooser arquivo) {
		ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
		if (arquivo.getSelectedFile() != null) {

			Workbook workbook = null;

			try {
				workbook = Workbook.getWorkbook(arquivo.getSelectedFile());
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Sheet sheet = workbook.getSheet(0);
			int linhas = sheet.getRows();

			for (int i = 1; i < linhas; i++) {
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

				String id_tarefa = a1.getContents();
				String descricao = a2.getContents();
				String prioridade = a3.getContents();
				String centrocusto = a4.getContents();
				String status = a5.getContents();				
				String tamanho = a6.getContents();
				String porcentagem = a7.getContents();
				String prazo = a8.getContents();
				String datainicio = a9.getContents();
				String datareal = a10.getContents();
				String datafim = a11.getContents();
				
				String executor1 = a12.getContents();
				String porcento1 = a13.getContents();
				
				String executor2 = a14.getContents();
				String porcento2 = a15.getContents();
				
				String executor3 = a16.getContents();
				String porcento3 = a17.getContents();
				
				String executor4 = a18.getContents();
				String porcento4 = a19.getContents();
				
				String executor5 = a20.getContents();	
			    String porcento5 = a21.getContents();
				
			    String executor6 = a22.getContents();
			    String porcento6 = a23.getContents();
			    
			    String executor7 = a24.getContents();
				String porcento7 = a25.getContents();
				
				String executor8 = a26.getContents();
				String porcento8 = a27.getContents();
			
				String executor9 = a28.getContents();
				String porcento9 = a29.getContents();
				
				String executor10 = a30.getContents();
				String porcento10 = a31.getContents();
				
				String pendente_por = a32.getContents();
				String status_pendencia = a33.getContents();
				String historico = a34.getContents();
				String departamento = a35.getContents();
				String responsavel = a36.getContents();
				String autoridade = a37.getContents();
				String etapa = a38.getContents();
				String subetapa = a39.getContents();
				String processo = a40.getContents();
				
				String predecessor1 = a41.getContents();
				String predecessor2 = a42.getContents();
				String predecessor3 = a43.getContents();
			
				String checado = a44.getContents();
				
				
				Tarefa tarefa = new Tarefa();
				dao.zeraVariaveis();

				tarefa.setDescricao(descricao);
				JOptionPane.showMessageDialog(null, prioridade);
				tarefa.setPrioridade(Integer.parseInt(prioridade));
				tarefa.setTamanho(tamanho);
				tarefa.setStatus(status);
				tarefa.setCentroCusto(centrocusto);
				tarefa.setDepartamento(departamento);
				tarefa.setPrazo(Integer.parseInt(prazo));
				tarefa.setDataInicio(datainicio);
				tarefa.setDataReal(datareal);
				tarefa.setDataFim(datafim);
				tarefa.setPorcentagem(Integer.parseInt(porcentagem));

				if (predecessor1 != "") {
					tarefa.setPredecessor1(Integer.parseInt(predecessor1));
				}
				if (predecessor2 != "") {
					tarefa.setPredecessor2(Integer.parseInt(predecessor2));
				}
				if (predecessor3 != "") {
					tarefa.setPredecessor3(Integer.parseInt(predecessor3));
				}

				tarefa.setEtapa(etapa);
				tarefa.setSubEtapa(subetapa);
				tarefa.setProcesso(processo);
				tarefa.setChecado(checado);
				tarefa.setResponsavel(responsavel);
				tarefa.setAutoridade(autoridade);
				tarefa.setPendentePor(pendente_por);
				tarefa.setExecutor1(executor1);
				tarefa.setPorcento1(Integer.parseInt(porcento1));

				if (porcento2 != "" && executor2 != "") {
					tarefa.setPorcento2(Integer.parseInt(porcento2));
					tarefa.setExecutor2(executor2);
				}

				if (porcento3 != "" && executor3 != "") {
					tarefa.setPorcento3(Integer.parseInt(porcento3));
					tarefa.setExecutor3(executor3);
				}

				if (porcento4 != "" && executor4 != "") {
					tarefa.setPorcento4(Integer.parseInt(porcento4));
					tarefa.setExecutor4(executor4);
				}

				if (porcento5 != "" && executor5 != "") {
					tarefa.setPorcento5(Integer.parseInt(porcento5));
					tarefa.setExecutor5(executor5);
				}

				if (porcento6 != "" && executor6 != "") {
					tarefa.setPorcento6(Integer.parseInt(porcento6));
					tarefa.setExecutor6(executor6);
				}

				if (porcento7 != "" && executor7 != "") {
					tarefa.setPorcento7(Integer.parseInt(porcento7));
					tarefa.setExecutor7(executor7);
				}

				if (porcento8 != "" && executor8 != "") {
					tarefa.setPorcento8(Integer.parseInt(porcento8));
					tarefa.setExecutor8(executor8);
				}

				if (porcento9 != "" && executor9 != "") {
					tarefa.setPorcento9(Integer.parseInt(porcento9));
					tarefa.setExecutor9(executor9);
				}

				if (porcento10 != "" && executor10 != "") {
					tarefa.setPorcento10(Integer.parseInt(porcento10));
					tarefa.setExecutor10(executor10);
				}

				tarefa.setStatusPendencia(status_pendencia);
				tarefa.setHistorico(historico);

				if (id_tarefa != "") {
					tarefa.setIDTarefa(Integer.parseInt(id_tarefa));
				} else {
					tarefa.setIDTarefa(0);
				}

				tarefas.add(tarefa);

			}

			workbook.close();
		}
		return tarefas;
	}

	public void inserirTarefa(Tarefa tarefa) {
		relatorio(dao.cadastrar(tarefa,true));
	}

	public void atualizarTarefa(Tarefa tarefa) {
		relatorio(dao.atualizar(tarefa,true));
	}

	public static void main(String[] args) throws BiffException, IOException {
		ImportarTarefasXLS importar = new ImportarTarefasXLS();
	}
	
	public void relatorio(String conteudo) {
		try {
			FileWriter log = new FileWriter("ImportaçãoLOG.txt",true);
			log.append(conteudo + "\n\r");
			log.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
