
package com.myscrum.model;

import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

public class KambanDAO extends kamban {

	public static BD bd = new BD();
	public static BD bdAFazer = new BD();
	public static BD bdFazendo = new BD();
	public static BD bdFeito = new BD();

//-----------------------------------------------CALCULOS DE PRODUÇÂO--------------------------------------------------------------------//		

	public String dataDeHoje() {
		Date hoje = new Date();// Pegando data de hoje

		String s = new SimpleDateFormat("yyyy-MM-dd").format(hoje.getTime());// Transformando em String padrão
																				// do banc
		return s;
	}

	public void calcularProducao(String Data_Inicio, String Data_Fim, String Status_Tarefa, String Porcentagem_Tarefa,
			String Prioridade, Double Pontos) throws SQLException {

		try {
			calculoProducao(Data_Inicio, Data_Fim, Status_Tarefa, Porcentagem_Tarefa, Prioridade);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}

		if (Data_Fim.equals(dataDeHoje()) && Status_Tarefa.equals("Feito")) {
			setPontuacaoFeitaHoje(Pontos);
		}

		if (Status_Tarefa.equals("A fazer") || Status_Tarefa.equals("Fazendo")) {// Se a tarefa estiver a fazer ou
																					// fazendo
			setQtd_Tarefas(1);
		}
	}

	public void finalizarCalculoProducao() {
		if (getPrazoPrevisto() == 0) {// Caso o previsto seja zero trocamos por 1 para não dar erro na divisão por 0
			setPrazoPrevisto(1);
		}
		if (getPrazoPrevisto1() == 0) {// Caso o previsto seja zero trocamos por 1 para não dar erro na divisão por 0
			setPrazoPrevisto1(1);
		}

		setProducaoGeral(((getPrazoPrevisto() - getPrazoProducao()) + (getPrazoReal() - getPrazoPrevisto()))
				/ getPrazoPrevisto());
		setProducao1(((getPrazoPrevisto1() - getPrazoProducao1()) + (getPrazoReal1() - getPrazoPrevisto1()))
				/ getPrazoPrevisto1());
	}

	public void calculoProducao(String inicio, String fim, String status_tarefa, String porcentagem, String prioridade)
			throws ParseException {
		double PrazoPrevisto = 0;
		double PrazoEmProducao = 0;
		double PrazoReal = 0;

		Date hoje = new Date();
		String hojeString = new SimpleDateFormat("yyyy-MM-dd").format(hoje);

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date data_inicial = formato.parse(inicio);
		Date data_fim = formato.parse(fim);

		/*--Buscando quantidades de feriados dentro da tarefa--*/

		/*--------------------------------------PREVISTO-------------------------------------*/
		if (status_tarefa.equals("Feito")) {// Se status for feito zera

			PrazoPrevisto = 0;
		} else {

			int feriados = ContarFeriados(inicio, fim);
			long dt = (data_fim.getTime() - data_inicial.getTime()) + 3600000; // 1 hora para compensar horário de verão
			long dias = (dt / 86400000L);

			if (dias >= 0) {
				dias += 1;
			} else if (dias < 0) {
				dias -= 1;
			}

			PrazoPrevisto = dias - feriados;
		}

		/*-------------------------------------PRODUÇÂO---------------------------*/
		if (status_tarefa.equals("Feito") || status_tarefa.equals("A fazer")) {// Se status for feito ou a fazer zera

			PrazoEmProducao = 0;
		} else if (status_tarefa.equals("Fazendo")) {

			PrazoEmProducao = PrazoPrevisto * (Double.parseDouble(porcentagem) / 100);
		}

		/*--------------------------------------REAL----------------------------*/
		if (status_tarefa.equals("Feito")) {

			PrazoReal = 0;
		} else if (hoje.after(data_fim)) {

			int feriados = ContarFeriados(inicio, hojeString);
			long dt = (hoje.getTime() - data_inicial.getTime()) + 3600000; // 1 hora para compensar horário de verão
			long dias = (dt / 86400000L);

			if (dias >= 0) {
				dias += 1;
			} else if (dias < 0) {
				dias -= 1;
			}

			PrazoReal = dias - feriados;
		} else if (status_tarefa.equals("A fazer") && hoje.before(data_inicial)) {

			PrazoReal = 0;

		} else {

			int feriados = ContarFeriados(inicio, hojeString);
			long dt = (hoje.getTime() - data_inicial.getTime()) + 3600000; // 1 hora para compensar horário de verão
			long dias = (dt / 86400000L);

			if (dias >= 0) {
				dias += 1;
			} else if (dias < 0) {
				dias -= 1;
			}

			PrazoReal = dias - feriados;
		}

		if (prioridade.equals("1")) {// se a tarefa for prioridade 1
			setPrazoReal1(PrazoReal);// adicionando valores de prazo real na variavel prioridade 1
			setPrazoProducao1(PrazoEmProducao);// adicionando valores de prazo em produção na variavel prioridade 1
			setPrazoPrevisto1(PrazoPrevisto);// adicionando valores de prazo previsto na variavel prioridade 1
		}

		setPrazoReal(PrazoReal);// adicionando valores de prazo real na variavel
		setPrazoProducao(PrazoEmProducao);// adicionando valores de prazo em produção na variavel
		setPrazoPrevisto(PrazoPrevisto);// adicionando valores de prazo previsto na variavel

	}

	public void limparVariavelProducao() {
		zerarQtd_Tarefas();
		zerarPontuacaoFeitaHoje();
		zerarPrazoProducao();
		zerarPrazoReal();
		zerarPrazoPrevisto();
		zerarPrazoProducao1();
		zerarPrazoReal1();
		zerarPrazoPrevisto1();
		setProducao1(0);
		setProducaoGeral(0);

	}

	public void ListarFeriados() {
		getFeriados().removeAll(getFeriados());// Zera a lista de feriados

		String sql = "SELECT data_feriado FROM feriados";
		try {

			bd.st = bd.con.prepareStatement(sql);

			bd.rs = bd.st.executeQuery();

			while (bd.rs.next() == true) {
				setFeriados(bd.rs.getDate(1));
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO LISTAR FERIADOS NO PERIODO", 0);
		}
	}

	public String CalcularDiasIniciados(Date data) {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		String Data_real = new SimpleDateFormat("dd/MM/yyyy").format(data);// Transformando data para BR

		Date d1 = null;
		try {
			d1 = df.parse(Data_real);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date d2 = new Date();

		long dt = (d2.getTime() - d1.getTime()) + 3600000; // 1 hora para compensar horário de verão
		long dias = (dt / 86400000L) + 1;// Soma +1 para contar o dia de inicio da tarefa

		String Data_Iniciada = new SimpleDateFormat("dd/MM/yyyy").format(d1);// Transformando a data para data
																				// Brasileira

		return String.valueOf("Iniciado " + Data_Iniciada + " - " + dias + " dias ");

	}

	public String CalcularPontosRealizados(double porcentagem, double peso) {

		porcentagem = porcentagem / 100;

		double pontos = porcentagem * peso;

		DecimalFormat df = new DecimalFormat("#0.00");

		return df.format(pontos);
	}

	public String CalcularAtraso(Date data_real, Date data_fim, int coluna, int linha) {

		Date hoje = new Date();
		String Retorno;

		long dt = (hoje.getTime() - data_real.getTime()) + 3600000; // 1 hora para compensar horário de verão
		long dias = (dt / 86400000L);// Soma +1 para contar o dia de inicio da tarefa

		long dt1 = (hoje.getTime() - data_fim.getTime()) + 3600000; // 1 hora para compensar horário de verão
		long dias1 = (dt1 / 86400000L);
		
		long dt2 = data_real.getTime() - hoje.getTime() + 3600000;
		long dias2 = (dt2 / 86400000L);
		
		double Percentual = 100 / Double.parseDouble(getPrazo(coluna, linha));

		double PercentualRealizado = Double.parseDouble(String.valueOf(dias)) * Percentual;

		if (getStatus(coluna, linha).equals("A fazer") && dias >= 1) {
			setAtrasada(coluna, linha, Color.RED);
			setAtrasadoOuEmdia(coluna, linha, "ATRASADO");
			setDias_iniciado(coluna, linha, "");
			if(dias != 1)
				Retorno = "Atrasada em " + dias + " dias";
			else
				Retorno = "Atrasada em " + dias + " dia";

		} else if (getStatus(coluna, linha).equals("A fazer") && dias <= 0) {
			setAtrasada(coluna, linha, new Color(230, 230, 0));// AMARELO
			setAtrasadoOuEmdia(coluna, linha, "ATENÇÃO");
			setDias_iniciado(coluna, linha, "");
			if (dias2 > 1)
				Retorno = "Faltam " + dias2 + " dias p/ início";
			else if (data_real.getTime() > hoje.getTime())
				Retorno = "Falta 1 dia p/ início";
			else
				Retorno = "Inicia hoje";
			
		} else if (getStatus(coluna, linha).equals("Fazendo") && dias1 >= 1) {
			setAtrasada(coluna, linha, Color.RED);
			setAtrasadoOuEmdia(coluna, linha, "ATRASADO");
			if(dias1 != 1)
				Retorno = "Atrasada em " + dias1 + " dias";
			else
				Retorno = "Atrasada em " + dias1 + " dia";

		} else if (getStatus(coluna, linha).equals("Fazendo")
				&& Double.parseDouble(getPorcentagem(coluna, linha)) < PercentualRealizado) {
			setAtrasada(coluna, linha, new Color(230, 230, 0));/// AMARELO
			setAtrasadoOuEmdia(coluna, linha, "ATENÇÂO");
			if(dias != 1)
				Retorno = "Iniciada a " + dias + " dias";
			else
				Retorno = "Iniciada a " + dias + " dia";

		} else if (getStatus(coluna, linha).equals("Feito")) {
			setAtrasada(coluna, linha, Color.GRAY);
			setAtrasadoOuEmdia(coluna, linha, "FINALIZADO");
			setDias_iniciado(coluna, linha, "Finalizada a " + dias1 + " dias");
			Retorno = "";
		} else {
			setAtrasada(coluna, linha, new Color(30, 200, 30));
			setAtrasadoOuEmdia(coluna, linha, "ADIANTADA");
			if(dias != 1)
				Retorno = "Tarefa adiantada"; // Confirmar escrita *********
			else
				Retorno = "Tarefa adiantada";
		}

		if (dias <= 0) {
			dias = 0;
		}
		return Retorno;
	}

//-----------------------------------------------CALCULOS DE PRODUÇÂO--------------------------------------------------------------------//	

//--------------------------------------------METODOS PARA TAREFAS A FAZER---------------------------------------------------------------//

	int contadorIncioAFazer;
	int contadorFimAFazer;

	public void pesquisarAFazer(String filtro) {
		contadorIncioAFazer = 1;
		contadorFimAFazer = 0;
		limparVariaveisAFazer();
		setQtd_Afazer(0);

		String sql = "";
		if (filtro.equals("")) {// Se o parametro de filtro estiver vazio, select em toda tarefas a fazer
			sql = "SELECT tarefa.data_ini AS Data_Inicio, tarefa.id_tarefa, tarefa.descri, tarefa.prioridade, tarefa.stat, \r\n"
					+ "(SELECT tamanho.descricao FROM tamanho WHERE tamanho.id_tamanho=tarefa.id_tamanho) AS Tamanho,\r\n"
					+ "tarefa.data_real, tarefa.data_fim, tarefa.prazo, tarefa.status_pendencia, tarefa.porcentagem,\r\n"
					+ "(SELECT tamanho.peso FROM tamanho WHERE tamanho.id_tamanho=tarefa.id_tamanho) AS Peso,\r\n"
					+ "(SELECT centro_custo.centrocusto FROM centro_custo WHERE centro_custo.id_centro_custo=tarefa.id_centro_custo) AS Centro_de_Custo,\r\n"
					+ "(SELECT departamento.departamento FROM departamento WHERE departamento.id_departamento=tarefa.id_departamento) AS Departamento, \r\n"
					+ "tarefa.responsavel, tarefa.autoridade,\r\n"
					+ "(SELECT CONCAT_WS('/',executor.executor1,executor.executor2,executor.executor3,executor.executor4,executor.executor5,executor.executor6,executor.executor7,executor.executor8,executor.executor9,executor.executor10)\r\n"
					+ "FROM executor \r\n" 
					+ "WHERE executor.id_tarefa=tarefa.id_tarefa) AS Executores, \r\n"
					+ "tarefa.pendente_por, \r\n"
					+ "tarefa.anexo1, \r\n"
					+ "tarefa.anexo2, \r\n"
					+ "tarefa.anexo3, \r\n"
					+ "tarefa.anexo4 \r\n" 
					+ "FROM tarefa\r\n" 
					+ "WHERE  tarefa.stat= 'A fazer'\r\n"
					+ "ORDER BY tarefa.prioridade";
		} else {// se não use o select com filtro
			sql = filtro;
		}
		try {

			bdAFazer.st = bdAFazer.con.prepareStatement(sql);

			bdAFazer.rs = bdAFazer.st.executeQuery();

			while (bdAFazer.rs.next()) {
				// Calcua produção da tarefa
				calcularProducao(bdAFazer.rs.getString("Data_Inicio"), bdAFazer.rs.getString("data_fim"),
						bdAFazer.rs.getString("stat"), bdAFazer.rs.getString("porcentagem"),
						bdAFazer.rs.getString("prioridade"), bdAFazer.rs.getDouble("Peso"));

				setQtd_Afazer(getQtd_Afazer() + 1);
			}

			bdAFazer.rs.absolute(0);// Seleciona a primeira linha o result set novamente
			for (int a = 0; a <= 1; a++) {
				for (int b = 0; b <= 4; b++) {
					if (bdAFazer.rs.next() == true) {

						// Aumenta o contador a cada tarefa armazenada
						contadorFimAFazer++;

						// Armazena os dados da tarefa em suas posiçãs
						setId(a, b, Integer.parseInt(bdAFazer.rs.getString("id_tarefa")));
						setDias_iniciado(a, b, CalcularDiasIniciados(bdAFazer.rs.getDate("data_real")));
						setDesc(a, b, bdAFazer.rs.getString("descri"));
						setPrioridade(a, b, bdAFazer.rs.getString("prioridade"));
						setTamanho(a, b, bdAFazer.rs.getString("Tamanho"));
						setDpto(a, b, bdAFazer.rs.getString("Departamento"));
						String data_real = new SimpleDateFormat("dd/MM/yyyy").format(bdAFazer.rs.getDate("data_real"));// Tranformando
																														// data
																														// para
																														// BR
						setData_real(a, b, data_real);
						String data_fim = new SimpleDateFormat("dd/MM/yyyy").format(bdAFazer.rs.getDate("data_fim"));// Tranformando
																														// data
																														// para
																														// BR
						setData_fim(a, b, data_fim);
						setPrazo(a, b, bdAFazer.rs.getString("prazo"));
						setStatus_pendencia(a, b, bdAFazer.rs.getString("status_pendencia"));
						setPorcentagem(a, b, bdAFazer.rs.getString("porcentagem"));
						setPeso(a, b, bdAFazer.rs.getString("Peso"));
						setPontuacao_realizada(a, b,
								String.valueOf(CalcularPontosRealizados(bdAFazer.rs.getDouble("porcentagem"),
										bdAFazer.rs.getDouble("Peso"))));
						setCentro_custo(a, b, bdAFazer.rs.getString("Centro_de_Custo"));
						setResponsavel(a, b, bdAFazer.rs.getString("responsavel"));
						setAutoridade(a, b, bdAFazer.rs.getString("autoridade"));
						setExecutores(a, b, bdAFazer.rs.getString("Executores"));
						setStatus(a, b, bdAFazer.rs.getString("stat"));
						setDias_atraso(a, b, CalcularAtraso(bdAFazer.rs.getDate("data_real"),
								bdAFazer.rs.getDate("data_fim"), a, b));
						setPendete_por(a, b, bdAFazer.rs.getString("pendente_por"));
						
						if(bdAFazer.rs.getString("anexo1") == null && bdAFazer.rs.getString("anexo2") == null 
								&& bdAFazer.rs.getString("anexo3") == null && bdAFazer.rs.getString("anexo4") == null) {// se não houver nenhum anexo, passamos false para o set da variavel
							setAnexo(a, b, false);
						}else {
							setAnexo(a, b, true);
						}

					}
				}
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "Erro na pesquisa de tarefas a fazer", 0);
		}
	}

	public boolean ProximoAFazer() {// Metodo para as proximas tarefas
		boolean retorno = false;
		limparVariaveisAFazer();
		try {
			bdAFazer.rs.absolute(contadorFimAFazer);
			if (bdAFazer.rs.next() == true) {// Se ainda existir resultado prossiga
				retorno = true;
				bdAFazer.rs.absolute(contadorFimAFazer);// Poscicionando o ResultSet na ultima posição usada
				contadorIncioAFazer += 10;
				for (int a = 0; a <= 1; a++) {
					for (int b = 0; b <= 4; b++) {

						if (bdAFazer.rs.next() == true) {

							contadorFimAFazer++;
							setId(a, b, Integer.parseInt(bdAFazer.rs.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdAFazer.rs.getDate("data_real")));
							setDesc(a, b, bdAFazer.rs.getString("descri"));
							setPrioridade(a, b, bdAFazer.rs.getString("prioridade"));
							setTamanho(a, b, bdAFazer.rs.getString("Tamanho"));
							setDpto(a, b, bdAFazer.rs.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdAFazer.rs.getDate("data_real"));// Tranformando data para BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdAFazer.rs.getDate("data_fim"));// Tranformando data para BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdAFazer.rs.getString("prazo"));
							setStatus_pendencia(a, b, bdAFazer.rs.getString("status_pendencia"));
							setPorcentagem(a, b, bdAFazer.rs.getString("porcentagem"));
							setPeso(a, b, bdAFazer.rs.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdAFazer.rs.getDouble("porcentagem"),
											bdAFazer.rs.getDouble("Peso"))));
							setCentro_custo(a, b, bdAFazer.rs.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdAFazer.rs.getString("responsavel"));
							setAutoridade(a, b, bdAFazer.rs.getString("autoridade"));
							setExecutores(a, b, bdAFazer.rs.getString("Executores"));
							setStatus(a, b, bdAFazer.rs.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdAFazer.rs.getDate("data_real"),
									bdAFazer.rs.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdAFazer.rs.getString("pendente_por"));
							
							if(bdAFazer.rs.getString("anexo1") == null && bdAFazer.rs.getString("anexo2") == null 
									&& bdAFazer.rs.getString("anexo3") == null && bdAFazer.rs.getString("anexo4") == null) {// se não houver nenhum anexo, passamos false para o set da variavel
								setAnexo(a, b, false);
							}else {
								setAnexo(a, b, true);
							}

						}

					}
				}
			} else {
				retorno = false;
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "Falha ao recarregar tarefas A Fazer", 0);
		}
		return retorno;
	}

	public boolean AnteriorAFazer() {
		boolean retorno = false;
		limparVariaveisAFazer();
		try {
			if (contadorIncioAFazer != 1 && bdAFazer.rs.previous() == true) {

				retorno = true;
				bdAFazer.rs.absolute(contadorIncioAFazer);
				for (int a = 1; a >= 0; a--) {
					for (int b = 4; b >= 0; b--) {
						if (bdAFazer.rs.previous() == true) {

							contadorIncioAFazer--;
							setId(a, b, Integer.parseInt(bdAFazer.rs.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdAFazer.rs.getDate("data_real")));
							setDesc(a, b, bdAFazer.rs.getString("descri"));
							setPrioridade(a, b, bdAFazer.rs.getString("prioridade"));
							setTamanho(a, b, bdAFazer.rs.getString("Tamanho"));
							setDpto(a, b, bdAFazer.rs.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdAFazer.rs.getDate("data_real"));// Tranformando data para BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdAFazer.rs.getDate("data_fim"));// Tranformando data para BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdAFazer.rs.getString("prazo"));
							setStatus_pendencia(a, b, bdAFazer.rs.getString("status_pendencia"));
							setPorcentagem(a, b, bdAFazer.rs.getString("porcentagem"));
							setPeso(a, b, bdAFazer.rs.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdAFazer.rs.getDouble("porcentagem"),
											bdAFazer.rs.getDouble("Peso"))));
							setCentro_custo(a, b, bdAFazer.rs.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdAFazer.rs.getString("responsavel"));
							setAutoridade(a, b, bdAFazer.rs.getString("autoridade"));
							setExecutores(a, b, bdAFazer.rs.getString("Executores"));
							setStatus(a, b, bdAFazer.rs.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdAFazer.rs.getDate("data_real"),
									bdAFazer.rs.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdAFazer.rs.getString("pendente_por"));
							
							if(bdAFazer.rs.getString("anexo1") == null && bdAFazer.rs.getString("anexo2") == null 
									&& bdAFazer.rs.getString("anexo3") == null && bdAFazer.rs.getString("anexo4") == null) {// se não houver nenhum anexo, passamos false para o set da variavel
								setAnexo(a, b, false);
							}else {
								setAnexo(a, b, true);
							}

						}
					}
				}
				contadorFimAFazer = contadorIncioAFazer + 9;
			} else {
				retorno = false;
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "Falha ao recarregar tarefas A Fazer", 0);
		}
		return retorno;
	}

	public void limparVariaveisAFazer() {
		for (int a = 0; a <= 1; a++) {
			for (int b = 0; b <= 4; b++) {

				setId(a, b, 0);
				setDias_iniciado(a, b, "");
				setDesc(a, b, "");
				setPrioridade(a, b, "");
				setTamanho(a, b, "");
				setData_real(a, b, "");
				setData_fim(a, b, "");
				setPrazo(a, b, "");
				setStatus_pendencia(a, b, "");
				setPorcentagem(a, b, "");
				setPeso(a, b, "");
				setPontuacao_realizada(a, b, "");
				setCentro_custo(a, b, "");
				setResponsavel(a, b, "");
				setAutoridade(a, b, "");
				setExecutores(a, b, "");
				setStatus(a, b, "");
				setDias_atraso(a, b, "");
				setPendete_por(a, b, "");
				setDpto(a, b, "");
				setAnexo(a, b, false);
			}
		}
	}

//--------------------------------------------METODOS PARA TAREFAS A FAZER---------------------------------------------------------------//	

//--------------------------------------------METODOS PARA TAREFAS FAZENDO---------------------------------------------------------------//

	int contadorIncioFazendo;
	int contadorFimFazendo;

	public void pesquisarFazendo(String filtro) {
		contadorIncioFazendo = 1;
		contadorFimFazendo = 0;
		limparVariaveisFazendo();
		setQtd_Fazendo(0);

		String sql = "";
		if (filtro.equals("")) {// Se o parametro de filtro estiver vazio, select em toda tarefas a fazer
			sql = "SELECT tarefa.data_ini AS Data_Inicio, tarefa.id_tarefa, tarefa.descri, tarefa.prioridade, tarefa.stat, \r\n"
					+ "(SELECT tamanho.descricao FROM tamanho WHERE tamanho.id_tamanho=tarefa.id_tamanho) AS Tamanho,\r\n"
					+ "tarefa.data_real, tarefa.data_fim, tarefa.prazo, tarefa.status_pendencia, tarefa.porcentagem,\r\n"
					+ "(SELECT tamanho.peso FROM tamanho WHERE tamanho.id_tamanho=tarefa.id_tamanho) AS Peso,\r\n"
					+ "(SELECT centro_custo.centrocusto FROM centro_custo WHERE centro_custo.id_centro_custo=tarefa.id_centro_custo) AS Centro_de_Custo,\r\n"
					+ "(SELECT departamento.departamento FROM departamento WHERE departamento.id_departamento=tarefa.id_departamento) AS Deparamento, \r\n"
					+ "tarefa.responsavel, tarefa.autoridade,\r\n"
					+ "(SELECT CONCAT_WS('/',executor.executor1,executor.executor2,executor.executor3,executor.executor4,executor.executor5,executor.executor6,executor.executor7,executor.executor8,executor.executor9,executor.executor10)\r\n"
					+ "FROM executor \r\n" 
					+ "WHERE executor.id_tarefa=tarefa.id_tarefa) AS Executores, \r\n"
					+ "tarefa.pendente_por, \r\n" 
					+ "tarefa.anexo1, \r\n"
					+ "tarefa.anexo2, \r\n"
					+ "tarefa.anexo3, \r\n"
					+ "tarefa.anexo4 \r\n" 
					+ "FROM tarefa\r\n" 
					+ "WHERE  tarefa.stat= 'Fazendo'\r\n"
					+ "ORDER BY tarefa.prioridade";

		} else {// se não use o select com filtro
			sql = filtro;
		}

		try {

			bdFazendo.st = bdFazendo.con.prepareStatement(sql);

			bdFazendo.rs = bdFazendo.st.executeQuery();

			while (bdFazendo.rs.next()) {
				// Calcua produção da tarefa
				calcularProducao(bdFazendo.rs.getString("Data_Inicio"), bdFazendo.rs.getString("data_fim"),
						bdFazendo.rs.getString("stat"), bdFazendo.rs.getString("porcentagem"),
						bdFazendo.rs.getString("prioridade"), bdFazendo.rs.getDouble("Peso"));

				setQtd_Fazendo(getQtd_Fazendo() + 1);
			}

			bdFazendo.rs.absolute(0);// seleciona primeira linha do rsult set
			for (int a = 2; a <= 3; a++) {
				for (int b = 0; b <= 4; b++) {
					if (bdFazendo.rs.next() == true) {

						// Aumenta o contador a cada tarefa listada
						contadorFimFazendo++;

						// Armazena os dados da tarefa em suas posições
						setId(a, b, Integer.parseInt(bdFazendo.rs.getString("id_tarefa")));
						setDias_iniciado(a, b, CalcularDiasIniciados(bdFazendo.rs.getDate("data_real")));
						setDesc(a, b, bdFazendo.rs.getString("descri"));
						setPrioridade(a, b, bdFazendo.rs.getString("prioridade"));
						setTamanho(a, b, bdFazendo.rs.getString("Tamanho"));
						setDpto(a, b, bdFazendo.rs.getString("Departamento"));
						String data_real = new SimpleDateFormat("dd/MM/yyyy").format(bdFazendo.rs.getDate("data_real"));// Tranformando
																														// data
																														// para
																														// BR
						setData_real(a, b, data_real);
						String data_fim = new SimpleDateFormat("dd/MM/yyyy").format(bdFazendo.rs.getDate("data_fim"));// Tranformando
																														// data
																														// para
																														// BR
						setData_fim(a, b, data_fim);
						setPrazo(a, b, bdFazendo.rs.getString("prazo"));
						setStatus_pendencia(a, b, bdFazendo.rs.getString("status_pendencia"));
						setPorcentagem(a, b, bdFazendo.rs.getString("porcentagem"));
						setPeso(a, b, bdFazendo.rs.getString("Peso"));
						setPontuacao_realizada(a, b,
								String.valueOf(CalcularPontosRealizados(bdFazendo.rs.getDouble("porcentagem"),
										bdFazendo.rs.getDouble("Peso"))));
						setCentro_custo(a, b, bdFazendo.rs.getString("Centro_de_Custo"));
						setResponsavel(a, b, bdFazendo.rs.getString("responsavel"));
						setAutoridade(a, b, bdFazendo.rs.getString("autoridade"));
						setExecutores(a, b, bdFazendo.rs.getString("Executores"));
						setStatus(a, b, bdFazendo.rs.getString("stat"));
						setDias_atraso(a, b, CalcularAtraso(bdFazendo.rs.getDate("data_real"),
								bdFazendo.rs.getDate("data_fim"), a, b));
						setPendete_por(a, b, bdFazendo.rs.getString("pendente_por"));
						
						if(bdFazendo.rs.getString("anexo1") == null && bdFazendo.rs.getString("anexo2") == null 
								&& bdFazendo.rs.getString("anexo3") == null && bdFazendo.rs.getString("anexo4") == null) {// se não houver nenhum anexo, passamos false para o set da variavel
							setAnexo(a, b, false);
						}else {
							setAnexo(a, b, true);
						}

					}
				}
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AQUI", 0);
		}
	}

	public boolean ProximoFazendo() {
		boolean retorno = false;
		limparVariaveisFazendo();
		try {
			bdFazendo.rs.absolute(contadorFimFazendo);
			if (bdFazendo.rs.next() == true) {// Se ainda existir resultado prossiga
				retorno = true;
				bdFazendo.rs.absolute(contadorFimFazendo);// Poscicionando o ResultSet na ultima posição usada
				contadorIncioFazendo += 10;
				for (int a = 2; a <= 3; a++) {
					for (int b = 0; b <= 4; b++) {

						if (bdFazendo.rs.next() == true) {

							contadorFimFazendo++;
							setId(a, b, Integer.parseInt(bdFazendo.rs.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdFazendo.rs.getDate("data_real")));
							setDesc(a, b, bdFazendo.rs.getString("descri"));
							setPrioridade(a, b, bdFazendo.rs.getString("prioridade"));
							setTamanho(a, b, bdFazendo.rs.getString("Tamanho"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFazendo.rs.getDate("data_real"));// Tranformando data para BR
							setDpto(a, b, bdFazendo.rs.getString("Departamento"));
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFazendo.rs.getDate("data_fim"));// Tranformando data para BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdFazendo.rs.getString("prazo"));
							setStatus_pendencia(a, b, bdFazendo.rs.getString("status_pendencia"));
							setPorcentagem(a, b, bdFazendo.rs.getString("porcentagem"));
							setPeso(a, b, bdFazendo.rs.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdFazendo.rs.getDouble("porcentagem"),
											bdFazendo.rs.getDouble("Peso"))));
							setCentro_custo(a, b, bdFazendo.rs.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdFazendo.rs.getString("responsavel"));
							setAutoridade(a, b, bdFazendo.rs.getString("autoridade"));
							setExecutores(a, b, bdFazendo.rs.getString("Executores"));
							setStatus(a, b, bdFazendo.rs.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdFazendo.rs.getDate("data_real"),
									bdFazendo.rs.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdFazendo.rs.getString("pendente_por"));
							
							if(bdFazendo.rs.getString("anexo1") == null && bdFazendo.rs.getString("anexo2") == null 
									&& bdFazendo.rs.getString("anexo3") == null && bdFazendo.rs.getString("anexo4") == null) {// se não houver nenhum anexo, passamos false para o set da variavel
								setAnexo(a, b, false);
							}else {
								setAnexo(a, b, true);
							}

						}

					}
				}
			} else {
				retorno = false;
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "Falha ao recarregar tarefas A Fazendo", 0);
		}
		return retorno;
	}

	public boolean AnteriorFazendo() {
		boolean retorno = false;
		limparVariaveisFazendo();
		try {
			if (contadorIncioFazendo != 1 && bdFazendo.rs.previous() == true) {

				retorno = true;
				bdFazendo.rs.absolute(contadorIncioFazendo);
				for (int a = 3; a >= 2; a--) {
					for (int b = 4; b >= 0; b--) {
						if (bdFazendo.rs.previous() == true) {

							contadorIncioFazendo--;
							setId(a, b, Integer.parseInt(bdFazendo.rs.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdFazendo.rs.getDate("data_real")));
							setDesc(a, b, bdFazendo.rs.getString("descri"));
							setPrioridade(a, b, bdFazendo.rs.getString("prioridade"));
							setTamanho(a, b, bdFazendo.rs.getString("Tamanho"));
							setDpto(a, b, bdFazendo.rs.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFazendo.rs.getDate("data_real"));// Tranformando data para BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFazendo.rs.getDate("data_fim"));// Tranformando data para BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdFazendo.rs.getString("prazo"));
							setStatus_pendencia(a, b, bdFazendo.rs.getString("status_pendencia"));
							setPorcentagem(a, b, bdFazendo.rs.getString("porcentagem"));
							setPeso(a, b, bdFazendo.rs.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdFazendo.rs.getDouble("porcentagem"),
											bdFazendo.rs.getDouble("Peso"))));
							setCentro_custo(a, b, bdFazendo.rs.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdFazendo.rs.getString("responsavel"));
							setAutoridade(a, b, bdFazendo.rs.getString("autoridade"));
							setExecutores(a, b, bdFazendo.rs.getString("Executores"));
							setStatus(a, b, bdFazendo.rs.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdFazendo.rs.getDate("data_real"),
									bdFazendo.rs.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdFazendo.rs.getString("pendente_por"));
							
							if(bdFazendo.rs.getString("anexo1") == null && bdFazendo.rs.getString("anexo2") == null 
									&& bdFazendo.rs.getString("anexo3") == null && bdFazendo.rs.getString("anexo4") == null) {// se não houver nenhum anexo, passamos false para o set da variavel
								setAnexo(a, b, false);
							}else {
								setAnexo(a, b, true);
							}

						}
					}
				}
				contadorFimFazendo = contadorIncioFazendo + 9;
			} else {
				retorno = false;
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "Falha ao recarregar tarefas Fazendo", 0);
		}
		return retorno;

	}

	public void limparVariaveisFazendo() {
		for (int a = 2; a <= 3; a++) {
			for (int b = 0; b <= 4; b++) {

				setId(a, b, 0);
				setDias_iniciado(a, b, "");
				setDesc(a, b, "");
				setPrioridade(a, b, "");
				setTamanho(a, b, "");
				setData_real(a, b, "");
				setData_fim(a, b, "");
				setPrazo(a, b, "");
				setStatus_pendencia(a, b, "");
				setPorcentagem(a, b, "");
				setPeso(a, b, "");
				setPontuacao_realizada(a, b, "");
				setCentro_custo(a, b, "");
				setResponsavel(a, b, "");
				setAutoridade(a, b, "");
				setExecutores(a, b, "");
				setStatus(a, b, "");
				setDias_atraso(a, b, "");
				setPendete_por(a, b, "");
				setDpto(a, b, "");
				setAnexo(a, b, false);

			}
		}
	}

//--------------------------------------------METODOS PARA TAREFAS FAZENDO---------------------------------------------------------------//	

//--------------------------------------------METODOS PARA TAREFAS FEITO---------------------------------------------------------------//

	int contadorIncioFeito;
	int contadorFimFeito;

	public void pesquisarFeito(String filtro) {
		contadorIncioFeito = 1;
		contadorFimFeito = 0;
		limparVariaveisFeito();
		setQtd_Feito(0);

		String sql = filtro;

		try {

			bdFeito.st = bdFeito.con.prepareStatement(sql);

			bdFeito.rs = bdFeito.st.executeQuery();

			while (bdFeito.rs.next()) {
				// Calcua produção da tarefa
				calcularProducao(bdFeito.rs.getString("Data_Inicio"), bdFeito.rs.getString("data_fim"),
						bdFeito.rs.getString("stat"), bdFeito.rs.getString("porcentagem"),
						bdFeito.rs.getString("prioridade"), bdFeito.rs.getDouble("Peso"));

				setQtd_Feito(getQtd_Feito() + 1);
			}

			bdFeito.rs.absolute(0);// Seleciona a primeira linha do result set
			for (int a = 4; a <= 4; a++) {
				for (int b = 0; b <= 4; b++) {
					if (bdFeito.rs.next() == true) {

						// Aumenta o contador a cada tarefa listada
						contadorFimFeito++;

						// Armazena os dados da tarefa em suas posições
						setId(a, b, Integer.parseInt(bdFeito.rs.getString("id_tarefa")));
						setDias_iniciado(a, b, CalcularDiasIniciados(bdFeito.rs.getDate("data_real")));
						setDesc(a, b, bdFeito.rs.getString("descri"));
						setPrioridade(a, b, bdFeito.rs.getString("prioridade"));
						setTamanho(a, b, bdFeito.rs.getString("Tamanho"));
						setDpto(a, b, bdFeito.rs.getString("Departamento"));
						String data_real = new SimpleDateFormat("dd/MM/yyyy").format(bdFeito.rs.getDate("data_real"));// Tranformando
																														// data
																														// para
																														// BR
						setData_real(a, b, data_real);
						String data_fim = new SimpleDateFormat("dd/MM/yyyy").format(bdFeito.rs.getDate("data_fim"));// Tranformando
																													// data
																													// para
																													// BR
						setData_fim(a, b, data_fim);
						setPrazo(a, b, bdFeito.rs.getString("prazo"));
						setStatus_pendencia(a, b, bdFeito.rs.getString("status_pendencia"));
						setPorcentagem(a, b, bdFeito.rs.getString("porcentagem"));
						setPeso(a, b, bdFeito.rs.getString("Peso"));
						setPontuacao_realizada(a, b,
								String.valueOf(CalcularPontosRealizados(bdFeito.rs.getDouble("porcentagem"),
										bdFeito.rs.getDouble("Peso"))));
						setCentro_custo(a, b, bdFeito.rs.getString("Centro_de_Custo"));
						setResponsavel(a, b, bdFeito.rs.getString("responsavel"));
						setAutoridade(a, b, bdFeito.rs.getString("autoridade"));
						setExecutores(a, b, bdFeito.rs.getString("Executores"));
						setStatus(a, b, bdFeito.rs.getString("stat"));
						setDias_atraso(a, b,
								CalcularAtraso(bdFeito.rs.getDate("data_real"), bdFeito.rs.getDate("data_fim"), a, b));
						setPendete_por(a, b, bdFeito.rs.getString("pendente_por"));
						
						if(bdFeito.rs.getString("anexo1") == null && bdFeito.rs.getString("anexo2") == null 
								&& bdFeito.rs.getString("anexo3") == null && bdFeito.rs.getString("anexo4") == null) {// se não houver nenhum anexo, passamos false para o set da variavel
							setAnexo(a, b, false);
						}else {
							setAnexo(a, b, true);
						}

					}
				}
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO CARREGAR TAREFAS FEITAS", 0);
		}
	}

	public boolean ProximoFeito() {// Metodo para as proximas tarefas
		boolean retorno = false;
		limparVariaveisFeito();
		try {
			if (bdFeito.rs.next() == true) {// Se ainda existir resultado prossiga
				retorno = true;
				bdFeito.rs.absolute(contadorFimFeito);// Poscicionando o ResultSet na ultima posição usada
				contadorIncioFeito += 5;
				for (int a = 4; a <= 4; a++) {
					for (int b = 0; b <= 4; b++) {

						if (bdFeito.rs.next() == true) {

							contadorFimFeito++;
							setId(a, b, Integer.parseInt(bdFeito.rs.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdFeito.rs.getDate("data_real")));
							setDesc(a, b, bdFeito.rs.getString("descri"));
							setPrioridade(a, b, bdFeito.rs.getString("prioridade"));
							setTamanho(a, b, bdFeito.rs.getString("Tamanho"));
							setDpto(a, b, bdFeito.rs.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFeito.rs.getDate("data_real"));// Tranformando data para BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy").format(bdFeito.rs.getDate("data_fim"));// Tranformando
																														// data
																														// para
																														// BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdFeito.rs.getString("prazo"));
							setStatus_pendencia(a, b, bdFeito.rs.getString("status_pendencia"));
							setPorcentagem(a, b, bdFeito.rs.getString("porcentagem"));
							setPeso(a, b, bdFeito.rs.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdFeito.rs.getDouble("porcentagem"),
											bdFeito.rs.getDouble("Peso"))));
							setCentro_custo(a, b, bdFeito.rs.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdFeito.rs.getString("responsavel"));
							setAutoridade(a, b, bdFeito.rs.getString("autoridade"));
							setExecutores(a, b, bdFeito.rs.getString("Executores"));
							setStatus(a, b, bdFeito.rs.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdFeito.rs.getDate("data_real"),
									bdFeito.rs.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdFeito.rs.getString("pendente_por"));
							
							if(bdFeito.rs.getString("anexo1") == null && bdFeito.rs.getString("anexo2") == null 
									&& bdFeito.rs.getString("anexo3") == null && bdFeito.rs.getString("anexo4") == null) {// se não houver nenhum anexo, passamos false para o set da variavel
								setAnexo(a, b, false);
							}else {
								setAnexo(a, b, true);
							}

						}

					}
				}
			} else {
				retorno = false;
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "Falha ao recarregar tarefas feitas", 0);
		}
		return retorno;
	}

	public boolean AnteriorFeito() {
		boolean retorno = false;
		limparVariaveisFeito();
		try {
			if (contadorIncioFeito != 1 && bdFeito.rs.previous() == true) {

				retorno = true;
				bdFeito.rs.absolute(contadorIncioFeito);
				for (int a = 4; a <= 4; a++) {
					for (int b = 4; b >= 0; b--) {
						if (bdFeito.rs.previous() == true) {

							contadorIncioFeito--;
							setId(a, b, Integer.parseInt(bdFeito.rs.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdFeito.rs.getDate("data_real")));
							setDesc(a, b, bdFeito.rs.getString("descri"));
							setPrioridade(a, b, bdFeito.rs.getString("prioridade"));
							setTamanho(a, b, bdFeito.rs.getString("Tamanho"));
							setDpto(a, b, bdFeito.rs.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFeito.rs.getDate("data_real"));// Tranformando data para BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy").format(bdFeito.rs.getDate("data_fim"));// Tranformando
																														// data
																														// para
																														// BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdFeito.rs.getString("prazo"));
							setStatus_pendencia(a, b, bdFeito.rs.getString("status_pendencia"));
							setPorcentagem(a, b, bdFeito.rs.getString("porcentagem"));
							setPeso(a, b, bdFeito.rs.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdFeito.rs.getDouble("porcentagem"),
											bdFeito.rs.getDouble("Peso"))));
							setCentro_custo(a, b, bdFeito.rs.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdFeito.rs.getString("responsavel"));
							setAutoridade(a, b, bdFeito.rs.getString("autoridade"));
							setExecutores(a, b, bdFeito.rs.getString("Executores"));
							setStatus(a, b, bdFeito.rs.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdFeito.rs.getDate("data_real"),
									bdFeito.rs.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdFeito.rs.getString("pendente_por"));
							
							if(bdFeito.rs.getString("anexo1") == null && bdFeito.rs.getString("anexo2") == null 
									&& bdFeito.rs.getString("anexo3") == null && bdFeito.rs.getString("anexo4") == null) {// se não houver nenhum anexo, passamos false para o set da variavel
								setAnexo(a, b, false);
							}else {
								setAnexo(a, b, true);
							}

						}
					}
				}
				contadorFimFeito = contadorIncioFeito + 4;
			} else {
				retorno = false;
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "Falha ao recarregar tarefas feitas", 0);
		}
		return retorno;
	}

	public void limparVariaveisFeito() {
		for (int a = 4; a <= 4; a++) {
			for (int b = 0; b <= 4; b++) {

				setId(a, b, 0);
				setDias_iniciado(a, b, "");
				setDesc(a, b, "");
				setPrioridade(a, b, "");
				setTamanho(a, b, "");
				setData_real(a, b, "");
				setData_fim(a, b, "");
				setPrazo(a, b, "");
				setStatus_pendencia(a, b, "");
				setPorcentagem(a, b, "");
				setPeso(a, b, "");
				setPontuacao_realizada(a, b, "");
				setCentro_custo(a, b, "");
				setResponsavel(a, b, "");
				setAutoridade(a, b, "");
				setExecutores(a, b, "");
				setStatus(a, b, "");
				setDias_atraso(a, b, "");
				setPendete_por(a, b, "");
				setDpto(a, b, "");
				setAnexo(a, b, false);

			}
		}
	}

//--------------------------------------------METODOS PARA TAREFAS FEITO---------------------------------------------------------------//	

//-------------------------------------------------METODOS AUXILIARES------------------------------------------------------------------//	

	public String DataParaoBanco(String data) {
		String data_correta = null;

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date data_desejada = null;
		try {
			data_desejada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		data_correta = formato.format(data_desejada);

		return data_correta;
	}

	public Date stringToDate(String data) {
		Date dataretorno = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		try {
			dataretorno = formato.parse(data);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return dataretorno;
	}

	public int ContarFeriados(String inicio, String fim) {
		int feriados = 0;

		int a = 0;

		Date inicioData = stringToDate(inicio);
		Date fimData = stringToDate(fim);

		while (a < getFeriados().size()) {
			if (getFeriados().get(a).equals(inicioData) || getFeriados().get(a).equals(fimData)) {// Se algum feriado
																									// dentro da lista
																									// for igual a data
																									// de inicio ou fim
																									// conta mais um em
																									// feriados

				feriados++;

			}
			if (getFeriados().get(a).after(inicioData) && getFeriados().get(a).before(fimData)) {// Se algum feriado
																									// estiver entre
																									// data inicio e
																									// data fim conta
																									// mais um em
																									// feriados
				feriados++;

			}
			a++;
		}

		return feriados;
	}

	public static void SelectTarefa(int id) {
		try {
			String sql = "SELECT tarefa.id_tarefa,tarefa.descri,tarefa.prioridade,centro_custo.centrocusto,tarefa.stat,"
					+ "tamanho.descricao,tarefa.porcentagem,tarefa.prazo,tarefa.data_ini,tarefa.data_real,tarefa.data_fim,"
					+ "executor.executor1,executor.porcento1,executor.executor2,executor.porcento2,executor.executor3,executor.porcento3,executor.executor4,executor.porcento4,"
					+ "executor.executor5, executor.porcento5, executor.executor6, executor.porcento6, executor.executor7, executor.porcento7, executor.executor8, executor.porcento8,"
					+ "executor.executor9, executor.porcento9, executor.executor10, executor.porcento10,"
					+ "tarefa.pendente_por,tarefa.status_pendencia,tarefa.historico,departamento.departamento,"
					+ "tarefa.responsavel,tarefa.autoridade,"
					+ "(SELECT etapas.etapa FROM etapas WHERE etapas.id_etapa = tarefa.etapa) AS etapa,"
					+ "(SELECT sub_etapas.sub_etapa FROM sub_etapas WHERE sub_etapas.id_sub_etapas = tarefa.subetapa) AS subetapa,"
					+ "(SELECT processo FROM processos WHERE processos.id_processo = tarefa.processo_relacionado), "
					+ "tarefa.predecessor_1, tarefa.predecessor_2, tarefa.predecessor_3, tarefa.last_update, "
					+ "(SELECT pessoa.nome FROM pessoa WHERE pessoa.id_pessoa = tarefa.id_update), "
					+ "tarefa.checado \r\n"
					+ "FROM tarefa\r\n" 
					+ "INNER JOIN centro_custo\r\n"
					+ "ON tarefa.id_centro_custo=centro_custo.id_centro_custo\r\n" 
					+ "INNER JOIN tamanho\r\n"
					+ "ON tarefa.id_tamanho=tamanho.id_tamanho\r\n" 
					+ "INNER JOIN executor\r\n"
					+ "ON tarefa.id_tarefa=executor.id_tarefa\r\n" 
					+ "INNER JOIN departamento\r\n"
					+ "ON tarefa.id_departamento=departamento.id_departamento\r\n" 
					+ "WHERE tarefa.id_tarefa=?";

			bd.st = bd.con.prepareStatement(sql);// Prepara o SQL

			bd.st.setInt(1, id);// Seta os valroes

			bd.rs = bd.st.executeQuery();// Exxecute

			bd.rs.next();// Inicia o Result Set

			Tarefa variaveis = new Tarefa();// instancia a classe de get e set da tarefa

			variaveis.setIDTarefa(bd.rs.getInt(1));
			variaveis.setDescricao(bd.rs.getString(2));
			variaveis.setPrioridade(bd.rs.getInt(3));
			variaveis.setCentroCusto(bd.rs.getString(4));
			variaveis.setStatus(bd.rs.getString(5));
			variaveis.setTamanho(bd.rs.getString(6));
			variaveis.setPorcentagem(bd.rs.getInt(7));
			variaveis.setPrazo(bd.rs.getInt(8));
			variaveis.setDataInicio(new SimpleDateFormat("dd/MM/yyyy").format(bd.rs.getDate(9)));
			variaveis.setDataReal(new SimpleDateFormat("dd/MM/yyyy").format(bd.rs.getDate(10)));
			variaveis.setDataFim(new SimpleDateFormat("dd/MM/yyyy").format(bd.rs.getDate(11)));
			variaveis.setExecutor1(bd.rs.getString(12));
			variaveis.setPorcento1(bd.rs.getInt(13));
			variaveis.setExecutor2(bd.rs.getString(14));
			variaveis.setPorcento2(bd.rs.getInt(15));
			variaveis.setExecutor3(bd.rs.getString(16));
			variaveis.setPorcento3(bd.rs.getInt(17));
			variaveis.setExecutor4(bd.rs.getString(18));
			variaveis.setPorcento4(bd.rs.getInt(19));
			variaveis.setExecutor5(bd.rs.getString(20));
			variaveis.setPorcento5(bd.rs.getInt(21));
			variaveis.setExecutor6(bd.rs.getString(22));
			variaveis.setPorcento6(bd.rs.getInt(23));
			variaveis.setExecutor7(bd.rs.getString(24));
			variaveis.setPorcento7(bd.rs.getInt(25));
			variaveis.setExecutor8(bd.rs.getString(26));
			variaveis.setPorcento8(bd.rs.getInt(27));
			variaveis.setExecutor9(bd.rs.getString(28));
			variaveis.setPorcento9(bd.rs.getInt(29));
			variaveis.setExecutor10(bd.rs.getString(30));
			variaveis.setPorcento10(bd.rs.getInt(31));
			variaveis.setPendentePor(bd.rs.getString(32));
			variaveis.setStatusPendencia(bd.rs.getString(33));
			variaveis.setHistorico(bd.rs.getString(34));
			variaveis.setDepartamento(bd.rs.getString(35));
			variaveis.setResponsavel(bd.rs.getString(36));
			variaveis.setAutoridade(bd.rs.getString(37));
			variaveis.setEtapa(bd.rs.getString(38));
			variaveis.setSubEtapa(bd.rs.getString(39));
			variaveis.setProcesso(bd.rs.getString(40));
			variaveis.setPredecessor1(bd.rs.getInt(41));
			variaveis.setPredecessor2(bd.rs.getInt(42));
			variaveis.setPredecessor3(bd.rs.getInt(43));
			
			if (bd.rs.getString(45) == null || bd.rs.getString(45) == "") {// Se não existir atualização coloca vazio na variavel de atualização				// tarefa
				variaveis.setAtualizacao("");
			} else {
				variaveis.setAtualizacao("Atualizado " + new SimpleDateFormat("dd/MM/yyyy").format(bd.rs.getDate(44))
						+ " Por " + bd.rs.getString(45));
			}
			
			variaveis.setChecado(bd.rs.getString(46));

			// DPTO CORRETO
			// PROCESSO RELACIONADO

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString() + "KambamDao");
		}
	}

	// -------------------------------------------------METODOS
	// AUXILIARES------------------------------------------------------------------//
}// FIM DA CLASSE
