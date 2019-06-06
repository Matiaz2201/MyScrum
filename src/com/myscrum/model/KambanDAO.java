
package com.myscrum.model;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.myscrum.banco.Banco;

public class KambanDAO extends kamban {

	public static ResultSet bdAFazer;
	public static ResultSet bdFazendo;
	public static ResultSet bdFeito;

	// -----------------------------------------------CALCULOS DE
	// PRODUÇÂO--------------------------------------------------------------------//

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
		if (Banco.conexao()) {
			try {

				Banco.st = Banco.con.prepareStatement(sql);

				Banco.rs = Banco.st.executeQuery();

				while (Banco.rs.next() == true) {
					setFeriados(Banco.rs.getDate(1));
				}

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO LISTAR FERIADOS NO PERIODO", 0);
			}
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
			if (dias != 1)
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
			if (dias1 != 1)
				Retorno = "Atrasada em " + dias1 + " dias";
			else
				Retorno = "Atrasada em " + dias1 + " dia";

		} else if (getStatus(coluna, linha).equals("Fazendo")
				&& Double.parseDouble(getPorcentagem(coluna, linha)) < PercentualRealizado) {
			setAtrasada(coluna, linha, new Color(230, 230, 0));/// AMARELO
			setAtrasadoOuEmdia(coluna, linha, "ATENÇÂO");
			if (dias != 1)
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
			if (dias != 1)
				Retorno = "Tarefa adiantada"; // Confirmar escrita *********
			else
				Retorno = "Tarefa adiantada";
		}

		if (dias <= 0) {
			dias = 0;
		}
		return Retorno;
	}

	// -----------------------------------------------CALCULOS DE
	// PRODUÇÂO--------------------------------------------------------------------//

	// --------------------------------------------METODOS PARA TAREFAS A
	// FAZER---------------------------------------------------------------//

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
					+ "FROM executor \r\n" + "WHERE executor.id_tarefa=tarefa.id_tarefa) AS Executores, \r\n"
					+ "tarefa.pendente_por, \r\n" + "tarefa.anexo1, \r\n" + "tarefa.anexo2, \r\n"
					+ "tarefa.anexo3, \r\n" + "tarefa.anexo4 \r\n" + "FROM tarefa\r\n"
					+ "WHERE  tarefa.stat= 'A fazer'\r\n" + "ORDER BY tarefa.prioridade";
		} else {// se não use o select com filtro
			sql = filtro;
		}
		if (Banco.conexao()) {
			try {

				Banco.st = Banco.con.prepareStatement(sql);

				bdAFazer = Banco.st.executeQuery();

				while (bdAFazer.next()) {
					// Calcua produção da tarefa
					calcularProducao(bdAFazer.getString("Data_Inicio"), bdAFazer.getString("data_fim"),
							bdAFazer.getString("stat"), bdAFazer.getString("porcentagem"),
							bdAFazer.getString("prioridade"), bdAFazer.getDouble("Peso"));

					setQtd_Afazer(getQtd_Afazer() + 1);
				}

				bdAFazer.absolute(0);// Seleciona a primeira linha o result set novamente
				for (int a = 0; a <= 1; a++) {
					for (int b = 0; b <= 4; b++) {
						if (bdAFazer.next() == true) {

							// Aumenta o contador a cada tarefa armazenada
							contadorFimAFazer++;

							// Armazena os dados da tarefa em suas posiçãs
							setId(a, b, Integer.parseInt(bdAFazer.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdAFazer.getDate("data_real")));
							setDesc(a, b, bdAFazer.getString("descri"));
							setPrioridade(a, b, bdAFazer.getString("prioridade"));
							setTamanho(a, b, bdAFazer.getString("Tamanho"));
							setDpto(a, b, bdAFazer.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdAFazer.getDate("data_real"));// Tranformando
																				// data
																				// para
																				// BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdAFazer.getDate("data_fim"));// Tranformando
																				// data
																				// para
																				// BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdAFazer.getString("prazo"));
							setStatus_pendencia(a, b, bdAFazer.getString("status_pendencia"));
							setPorcentagem(a, b, bdAFazer.getString("porcentagem"));
							setPeso(a, b, bdAFazer.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdAFazer.getDouble("porcentagem"),
											bdAFazer.getDouble("Peso"))));
							setCentro_custo(a, b, bdAFazer.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdAFazer.getString("responsavel"));
							setAutoridade(a, b, bdAFazer.getString("autoridade"));
							setExecutores(a, b, bdAFazer.getString("Executores"));
							setStatus(a, b, bdAFazer.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdAFazer.getDate("data_real"),
									bdAFazer.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdAFazer.getString("pendente_por"));

							if (bdAFazer.getString("anexo1") == null && bdAFazer.getString("anexo2") == null
									&& bdAFazer.getString("anexo3") == null
									&& bdAFazer.getString("anexo4") == null) {// se
								// não
								// houver
								// nenhum
								// anexo,
								// passamos
								// false
								// para
								// o
								// set
								// da
								// variavel
								setAnexo(a, b, false);
							} else {
								setAnexo(a, b, true);
							}

						}
					}
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "Erro na pesquisa de tarefas a fazer", 0);
			}
		}
	}

	public boolean ProximoAFazer() {// Metodo para as proximas tarefas
		boolean retorno = false;
		limparVariaveisAFazer();
		try {
			bdAFazer.absolute(contadorFimAFazer);
			if (bdAFazer.next() == true) {// Se ainda existir resultado prossiga
				retorno = true;
				bdAFazer.absolute(contadorFimAFazer);// Poscicionando o ResultSet na ultima posição usada
				contadorIncioAFazer += 10;
				for (int a = 0; a <= 1; a++) {
					for (int b = 0; b <= 4; b++) {

						if (bdAFazer.next() == true) {

							contadorFimAFazer++;
							setId(a, b, Integer.parseInt(bdAFazer.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdAFazer.getDate("data_real")));
							setDesc(a, b, bdAFazer.getString("descri"));
							setPrioridade(a, b, bdAFazer.getString("prioridade"));
							setTamanho(a, b, bdAFazer.getString("Tamanho"));
							setDpto(a, b, bdAFazer.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdAFazer.getDate("data_real"));// Tranformando
																				// data
																				// para
																				// BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdAFazer.getDate("data_fim"));// Tranformando
																				// data
																				// para
																				// BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdAFazer.getString("prazo"));
							setStatus_pendencia(a, b, bdAFazer.getString("status_pendencia"));
							setPorcentagem(a, b, bdAFazer.getString("porcentagem"));
							setPeso(a, b, bdAFazer.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdAFazer.getDouble("porcentagem"),
											bdAFazer.getDouble("Peso"))));
							setCentro_custo(a, b, bdAFazer.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdAFazer.getString("responsavel"));
							setAutoridade(a, b, bdAFazer.getString("autoridade"));
							setExecutores(a, b, bdAFazer.getString("Executores"));
							setStatus(a, b, bdAFazer.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdAFazer.getDate("data_real"),
									bdAFazer.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdAFazer.getString("pendente_por"));

							if (bdAFazer.getString("anexo1") == null && bdAFazer.getString("anexo2") == null
									&& bdAFazer.getString("anexo3") == null
									&& bdAFazer.getString("anexo4") == null) {// se
								// não
								// houver
								// nenhum
								// anexo,
								// passamos
								// false
								// para
								// o
								// set
								// da
								// variavel
								setAnexo(a, b, false);
							} else {
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
			if (contadorIncioAFazer != 1 && bdAFazer.previous() == true) {

				retorno = true;
				bdAFazer.absolute(contadorIncioAFazer);
				for (int a = 1; a >= 0; a--) {
					for (int b = 4; b >= 0; b--) {
						if (bdAFazer.previous() == true) {

							contadorIncioAFazer--;
							setId(a, b, Integer.parseInt(bdAFazer.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdAFazer.getDate("data_real")));
							setDesc(a, b, bdAFazer.getString("descri"));
							setPrioridade(a, b, bdAFazer.getString("prioridade"));
							setTamanho(a, b, bdAFazer.getString("Tamanho"));
							setDpto(a, b, bdAFazer.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdAFazer.getDate("data_real"));// Tranformando
																				// data
																				// para
																				// BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdAFazer.getDate("data_fim"));// Tranformando
																				// data
																				// para
																				// BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdAFazer.getString("prazo"));
							setStatus_pendencia(a, b, bdAFazer.getString("status_pendencia"));
							setPorcentagem(a, b, bdAFazer.getString("porcentagem"));
							setPeso(a, b, bdAFazer.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdAFazer.getDouble("porcentagem"),
											bdAFazer.getDouble("Peso"))));
							setCentro_custo(a, b, bdAFazer.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdAFazer.getString("responsavel"));
							setAutoridade(a, b, bdAFazer.getString("autoridade"));
							setExecutores(a, b, bdAFazer.getString("Executores"));
							setStatus(a, b, bdAFazer.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdAFazer.getDate("data_real"),
									bdAFazer.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdAFazer.getString("pendente_por"));

							if (bdAFazer.getString("anexo1") == null && bdAFazer.getString("anexo2") == null
									&& bdAFazer.getString("anexo3") == null
									&& bdAFazer.getString("anexo4") == null) {// se
								// não
								// houver
								// nenhum
								// anexo,
								// passamos
								// false
								// para
								// o
								// set
								// da
								// variavel
								setAnexo(a, b, false);
							} else {
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

	// --------------------------------------------METODOS PARA TAREFAS A
	// FAZER---------------------------------------------------------------//

	// --------------------------------------------METODOS PARA TAREFAS
	// FAZENDO---------------------------------------------------------------//

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
					+ "FROM executor \r\n" + "WHERE executor.id_tarefa=tarefa.id_tarefa) AS Executores, \r\n"
					+ "tarefa.pendente_por, \r\n" + "tarefa.anexo1, \r\n" + "tarefa.anexo2, \r\n"
					+ "tarefa.anexo3, \r\n" + "tarefa.anexo4 \r\n" + "FROM tarefa\r\n"
					+ "WHERE  tarefa.stat= 'Fazendo'\r\n" + "ORDER BY tarefa.prioridade";

		} else {// se não use o select com filtro
			sql = filtro;
		}

		if (Banco.conexao()) {
			try {

				Banco.st = Banco.con.prepareStatement(sql);

				bdFazendo = Banco.st.executeQuery();

				while (bdFazendo.next()) {
					// Calcua produção da tarefa
					calcularProducao(bdFazendo.getString("Data_Inicio"),
							bdFazendo.getString("data_fim"),
							bdFazendo.getString("stat"), bdFazendo.getString("porcentagem"),
							bdFazendo.getString("prioridade"), bdFazendo.getDouble("Peso"));

					setQtd_Fazendo(getQtd_Fazendo() + 1);
				}

				bdFazendo.absolute(0);// seleciona primeira linha do rsult set
				for (int a = 2; a <= 3; a++) {
					for (int b = 0; b <= 4; b++) {
						if (bdFazendo.next() == true) {

							// Aumenta o contador a cada tarefa listada
							contadorFimFazendo++;

							// Armazena os dados da tarefa em suas posições
							setId(a, b, Integer.parseInt(bdFazendo.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdFazendo.getDate("data_real")));
							setDesc(a, b, bdFazendo.getString("descri"));
							setPrioridade(a, b, bdFazendo.getString("prioridade"));
							setTamanho(a, b, bdFazendo.getString("Tamanho"));
							setDpto(a, b, bdFazendo.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFazendo.getDate("data_real"));// Tranformando
																				// data
																				// para
																				// BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFazendo.getDate("data_fim"));// Tranformando
																				// data
																				// para
																				// BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdFazendo.getString("prazo"));
							setStatus_pendencia(a, b, bdFazendo.getString("status_pendencia"));
							setPorcentagem(a, b, bdFazendo.getString("porcentagem"));
							setPeso(a, b, bdFazendo.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdFazendo.getDouble("porcentagem"),
											bdFazendo.getDouble("Peso"))));
							setCentro_custo(a, b, bdFazendo.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdFazendo.getString("responsavel"));
							setAutoridade(a, b, bdFazendo.getString("autoridade"));
							setExecutores(a, b, bdFazendo.getString("Executores"));
							setStatus(a, b, bdFazendo.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdFazendo.getDate("data_real"),
									bdFazendo.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdFazendo.getString("pendente_por"));

							if (bdFazendo.getString("anexo1") == null && bdFazendo.getString("anexo2") == null
									&& bdFazendo.getString("anexo3") == null
									&& bdFazendo.getString("anexo4") == null) {// se
								// não
								// houver
								// nenhum
								// anexo,
								// passamos
								// false
								// para
								// o
								// set
								// da
								// variavel
								setAnexo(a, b, false);
							} else {
								setAnexo(a, b, true);
							}

						}
					}
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AQUI", 0);
			}
		}
	}

	public boolean ProximoFazendo() {
		boolean retorno = false;
		limparVariaveisFazendo();
		try {
			bdFazendo.absolute(contadorFimFazendo);
			if (bdFazendo.next() == true) {// Se ainda existir resultado prossiga
				retorno = true;
				bdFazendo.absolute(contadorFimFazendo);// Poscicionando o ResultSet na ultima posição usada
				contadorIncioFazendo += 10;
				for (int a = 2; a <= 3; a++) {
					for (int b = 0; b <= 4; b++) {

						if (bdFazendo.next() == true) {

							contadorFimFazendo++;
							setId(a, b, Integer.parseInt(bdFazendo.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdFazendo.getDate("data_real")));
							setDesc(a, b, bdFazendo.getString("descri"));
							setPrioridade(a, b, bdFazendo.getString("prioridade"));
							setTamanho(a, b, bdFazendo.getString("Tamanho"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFazendo.getDate("data_real"));// Tranformando data para BR
							setDpto(a, b, bdFazendo.getString("Departamento"));
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFazendo.getDate("data_fim"));// Tranformando
																				// data
																				// para
																				// BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdFazendo.getString("prazo"));
							setStatus_pendencia(a, b, bdFazendo.getString("status_pendencia"));
							setPorcentagem(a, b, bdFazendo.getString("porcentagem"));
							setPeso(a, b, bdFazendo.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdFazendo.getDouble("porcentagem"),
											bdFazendo.getDouble("Peso"))));
							setCentro_custo(a, b, bdFazendo.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdFazendo.getString("responsavel"));
							setAutoridade(a, b, bdFazendo.getString("autoridade"));
							setExecutores(a, b, bdFazendo.getString("Executores"));
							setStatus(a, b, bdFazendo.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdFazendo.getDate("data_real"),
									bdFazendo.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdFazendo.getString("pendente_por"));

							if (bdFazendo.getString("anexo1") == null && bdFazendo.getString("anexo2") == null
									&& bdFazendo.getString("anexo3") == null
									&& bdFazendo.getString("anexo4") == null) {// se
								// não
								// houver
								// nenhum
								// anexo,
								// passamos
								// false
								// para
								// o
								// set
								// da
								// variavel
								setAnexo(a, b, false);
							} else {
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
			if (contadorIncioFazendo != 1 && bdFazendo.previous() == true) {

				retorno = true;
				bdFazendo.absolute(contadorIncioFazendo);
				for (int a = 3; a >= 2; a--) {
					for (int b = 4; b >= 0; b--) {
						if (bdFazendo.previous() == true) {

							contadorIncioFazendo--;
							setId(a, b, Integer.parseInt(bdFazendo.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdFazendo.getDate("data_real")));
							setDesc(a, b, bdFazendo.getString("descri"));
							setPrioridade(a, b, bdFazendo.getString("prioridade"));
							setTamanho(a, b, bdFazendo.getString("Tamanho"));
							setDpto(a, b, bdFazendo.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFazendo.getDate("data_real"));// Tranformando data para BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFazendo.getDate("data_fim"));// Tranformando
																				// data
																				// para
																				// BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdFazendo.getString("prazo"));
							setStatus_pendencia(a, b, bdFazendo.getString("status_pendencia"));
							setPorcentagem(a, b, bdFazendo.getString("porcentagem"));
							setPeso(a, b, bdFazendo.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdFazendo.getDouble("porcentagem"),
											bdFazendo.getDouble("Peso"))));
							setCentro_custo(a, b, bdFazendo.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdFazendo.getString("responsavel"));
							setAutoridade(a, b, bdFazendo.getString("autoridade"));
							setExecutores(a, b, bdFazendo.getString("Executores"));
							setStatus(a, b, bdFazendo.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdFazendo.getDate("data_real"),
									bdFazendo.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdFazendo.getString("pendente_por"));

							if (bdFazendo.getString("anexo1") == null && bdFazendo.getString("anexo2") == null
									&& bdFazendo.getString("anexo3") == null
									&& bdFazendo.getString("anexo4") == null) {// se
								// não
								// houver
								// nenhum
								// anexo,
								// passamos
								// false
								// para
								// o
								// set
								// da
								// variavel
								setAnexo(a, b, false);
							} else {
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

	// --------------------------------------------METODOS PARA TAREFAS
	// FAZENDO---------------------------------------------------------------//

	// --------------------------------------------METODOS PARA TAREFAS
	// FEITO---------------------------------------------------------------//

	int contadorIncioFeito;
	int contadorFimFeito;

	public void pesquisarFeito(String filtro) {
		contadorIncioFeito = 1;
		contadorFimFeito = 0;
		limparVariaveisFeito();
		setQtd_Feito(0);

		String sql = filtro;
		if (Banco.conexao()) {
			try {

				Banco.st = Banco.con.prepareStatement(sql);

				bdFeito = Banco.st.executeQuery();

				while (bdFeito.next()) {
					// Calcua produção da tarefa
					calcularProducao(bdFeito.getString("Data_Inicio"), bdFeito.getString("data_fim"),
							bdFeito.getString("stat"), bdFeito.getString("porcentagem"),
							bdFeito.getString("prioridade"), bdFeito.getDouble("Peso"));

					setQtd_Feito(getQtd_Feito() + 1);
				}

				bdFeito.absolute(0);// Seleciona a primeira linha do result set
				for (int a = 4; a <= 4; a++) {
					for (int b = 0; b <= 4; b++) {
						if (bdFeito.next() == true) {

							// Aumenta o contador a cada tarefa listada
							contadorFimFeito++;

							// Armazena os dados da tarefa em suas posições
							setId(a, b, Integer.parseInt(bdFeito.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdFeito.getDate("data_real")));
							setDesc(a, b, bdFeito.getString("descri"));
							setPrioridade(a, b, bdFeito.getString("prioridade"));
							setTamanho(a, b, bdFeito.getString("Tamanho"));
							setDpto(a, b, bdFeito.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFeito.getDate("data_real"));// Tranformando
																				// data
																				// para
																				// BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy").format(bdFeito.getDate("data_fim"));// Tranformando
																														// data
																														// para
																														// BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdFeito.getString("prazo"));
							setStatus_pendencia(a, b, bdFeito.getString("status_pendencia"));
							setPorcentagem(a, b, bdFeito.getString("porcentagem"));
							setPeso(a, b, bdFeito.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdFeito.getDouble("porcentagem"),
											bdFeito.getDouble("Peso"))));
							setCentro_custo(a, b, bdFeito.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdFeito.getString("responsavel"));
							setAutoridade(a, b, bdFeito.getString("autoridade"));
							setExecutores(a, b, bdFeito.getString("Executores"));
							setStatus(a, b, bdFeito.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdFeito.getDate("data_real"),
									bdFeito.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdFeito.getString("pendente_por"));

							if (bdFeito.getString("anexo1") == null && bdFeito.getString("anexo2") == null
									&& bdFeito.getString("anexo3") == null
									&& bdFeito.getString("anexo4") == null) {// se
																				// não
																				// houver
																				// nenhum
																				// anexo,
																				// passamos
																				// false
																				// para
																				// o
																				// set
																				// da
																				// variavel
								setAnexo(a, b, false);
							} else {
								setAnexo(a, b, true);
							}

						}
					}
				}

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO CARREGAR TAREFAS FEITAS", 0);
			}
		}
	}

	public boolean ProximoFeito() {// Metodo para as proximas tarefas
		boolean retorno = false;
		limparVariaveisFeito();
		try {
			if (bdFeito.next() == true) {// Se ainda existir resultado prossiga
				retorno = true;
				bdFeito.absolute(contadorFimFeito);// Poscicionando o ResultSet na ultima posição usada
				contadorIncioFeito += 5;
				for (int a = 4; a <= 4; a++) {
					for (int b = 0; b <= 4; b++) {

						if (bdFeito.next() == true) {

							contadorFimFeito++;
							setId(a, b, Integer.parseInt(bdFeito.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdFeito.getDate("data_real")));
							setDesc(a, b, bdFeito.getString("descri"));
							setPrioridade(a, b, bdFeito.getString("prioridade"));
							setTamanho(a, b, bdFeito.getString("Tamanho"));
							setDpto(a, b, bdFeito.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFeito.getDate("data_real"));// Tranformando data para BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy").format(bdFeito.getDate("data_fim"));// Tranformando
																														// data
																														// para
																														// BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdFeito.getString("prazo"));
							setStatus_pendencia(a, b, bdFeito.getString("status_pendencia"));
							setPorcentagem(a, b, bdFeito.getString("porcentagem"));
							setPeso(a, b, bdFeito.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdFeito.getDouble("porcentagem"),
											bdFeito.getDouble("Peso"))));
							setCentro_custo(a, b, bdFeito.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdFeito.getString("responsavel"));
							setAutoridade(a, b, bdFeito.getString("autoridade"));
							setExecutores(a, b, bdFeito.getString("Executores"));
							setStatus(a, b, bdFeito.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdFeito.getDate("data_real"),
									bdFeito.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdFeito.getString("pendente_por"));

							if (bdFeito.getString("anexo1") == null && bdFeito.getString("anexo2") == null
									&& bdFeito.getString("anexo3") == null
									&& bdFeito.getString("anexo4") == null) {// se não houver nenhum anexo, passamos
																				// false para o set da variavel
								setAnexo(a, b, false);
							} else {
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
			if (contadorIncioFeito != 1 && bdFeito.previous() == true) {

				retorno = true;
				bdFeito.absolute(contadorIncioFeito);
				for (int a = 4; a <= 4; a++) {
					for (int b = 4; b >= 0; b--) {
						if (bdFeito.previous() == true) {

							contadorIncioFeito--;
							setId(a, b, Integer.parseInt(bdFeito.getString("id_tarefa")));
							setDias_iniciado(a, b, CalcularDiasIniciados(bdFeito.getDate("data_real")));
							setDesc(a, b, bdFeito.getString("descri"));
							setPrioridade(a, b, bdFeito.getString("prioridade"));
							setTamanho(a, b, bdFeito.getString("Tamanho"));
							setDpto(a, b, bdFeito.getString("Departamento"));
							String data_real = new SimpleDateFormat("dd/MM/yyyy")
									.format(bdFeito.getDate("data_real"));// Tranformando data para BR
							setData_real(a, b, data_real);
							String data_fim = new SimpleDateFormat("dd/MM/yyyy").format(bdFeito.getDate("data_fim"));// Tranformando
																														// data
																														// para
																														// BR
							setData_fim(a, b, data_fim);
							setPrazo(a, b, bdFeito.getString("prazo"));
							setStatus_pendencia(a, b, bdFeito.getString("status_pendencia"));
							setPorcentagem(a, b, bdFeito.getString("porcentagem"));
							setPeso(a, b, bdFeito.getString("Peso"));
							setPontuacao_realizada(a, b,
									String.valueOf(CalcularPontosRealizados(bdFeito.getDouble("porcentagem"),
											bdFeito.getDouble("Peso"))));
							setCentro_custo(a, b, bdFeito.getString("Centro_de_Custo"));
							setResponsavel(a, b, bdFeito.getString("responsavel"));
							setAutoridade(a, b, bdFeito.getString("autoridade"));
							setExecutores(a, b, bdFeito.getString("Executores"));
							setStatus(a, b, bdFeito.getString("stat"));
							setDias_atraso(a, b, CalcularAtraso(bdFeito.getDate("data_real"),
									bdFeito.getDate("data_fim"), a, b));
							setPendete_por(a, b, bdFeito.getString("pendente_por"));

							if (bdFeito.getString("anexo1") == null && bdFeito.getString("anexo2") == null
									&& bdFeito.getString("anexo3") == null
									&& bdFeito.getString("anexo4") == null) {// se não houver nenhum anexo, passamos
																				// false para o set da variavel
								setAnexo(a, b, false);
							} else {
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

	// --------------------------------------------METODOS PARA TAREFAS
	// FEITO---------------------------------------------------------------//

	// -------------------------------------------------METODOS
	// AUXILIARES------------------------------------------------------------------//

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

	public static Tarefa SelectTarefa(int id) {
		Tarefa tarefa = new Tarefa();// instancia a classe de get e set da tarefa
		if (Banco.conexao()) {
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
						+ "tarefa.checado \r\n" + "FROM tarefa\r\n" + "INNER JOIN centro_custo\r\n"
						+ "ON tarefa.id_centro_custo=centro_custo.id_centro_custo\r\n" + "INNER JOIN tamanho\r\n"
						+ "ON tarefa.id_tamanho=tamanho.id_tamanho\r\n" + "INNER JOIN executor\r\n"
						+ "ON tarefa.id_tarefa=executor.id_tarefa\r\n" + "INNER JOIN departamento\r\n"
						+ "ON tarefa.id_departamento=departamento.id_departamento\r\n" + "WHERE tarefa.id_tarefa=?";

				Banco.st = Banco.con.prepareStatement(sql);// Prepara o SQL

				Banco.st.setInt(1, id);// Seta os valroes

				Banco.rs = Banco.st.executeQuery();// Exxecute

				Banco.rs.next();// Inicia o Result Set

				tarefa.setIDTarefa(Banco.rs.getInt(1));
				tarefa.setDescricao(Banco.rs.getString(2));
				tarefa.setPrioridade(Banco.rs.getInt(3));
				tarefa.setCentroCusto(Banco.rs.getString(4));
				tarefa.setStatus(Banco.rs.getString(5));
				tarefa.setTamanho(Banco.rs.getString(6));
				tarefa.setPorcentagem(Banco.rs.getInt(7));
				tarefa.setPrazo(Banco.rs.getInt(8));
				tarefa.setDataInicio(new SimpleDateFormat("dd/MM/yyyy").format(Banco.rs.getDate(9)));
				tarefa.setDataReal(new SimpleDateFormat("dd/MM/yyyy").format(Banco.rs.getDate(10)));
				tarefa.setDataFim(new SimpleDateFormat("dd/MM/yyyy").format(Banco.rs.getDate(11)));
				tarefa.setExecutor1(Banco.rs.getString(12));
				tarefa.setPorcento1(Banco.rs.getInt(13));
				tarefa.setExecutor2(Banco.rs.getString(14));
				tarefa.setPorcento2(Banco.rs.getInt(15));
				tarefa.setExecutor3(Banco.rs.getString(16));
				tarefa.setPorcento3(Banco.rs.getInt(17));
				tarefa.setExecutor4(Banco.rs.getString(18));
				tarefa.setPorcento4(Banco.rs.getInt(19));
				tarefa.setExecutor5(Banco.rs.getString(20));
				tarefa.setPorcento5(Banco.rs.getInt(21));
				tarefa.setExecutor6(Banco.rs.getString(22));
				tarefa.setPorcento6(Banco.rs.getInt(23));
				tarefa.setExecutor7(Banco.rs.getString(24));
				tarefa.setPorcento7(Banco.rs.getInt(25));
				tarefa.setExecutor8(Banco.rs.getString(26));
				tarefa.setPorcento8(Banco.rs.getInt(27));
				tarefa.setExecutor9(Banco.rs.getString(28));
				tarefa.setPorcento9(Banco.rs.getInt(29));
				tarefa.setExecutor10(Banco.rs.getString(30));
				tarefa.setPorcento10(Banco.rs.getInt(31));
				tarefa.setPendentePor(Banco.rs.getString(32));
				tarefa.setStatusPendencia(Banco.rs.getString(33));
				tarefa.setHistorico(Banco.rs.getString(34));
				tarefa.setDepartamento(Banco.rs.getString(35));
				tarefa.setResponsavel(Banco.rs.getString(36));
				tarefa.setAutoridade(Banco.rs.getString(37));
				tarefa.setEtapa(Banco.rs.getString(38));
				tarefa.setSubEtapa(Banco.rs.getString(39));
				tarefa.setProcesso(Banco.rs.getString(40));
				tarefa.setPredecessor1(Banco.rs.getInt(41));
				tarefa.setPredecessor2(Banco.rs.getInt(42));
				tarefa.setPredecessor3(Banco.rs.getInt(43));

				if (Banco.rs.getString(45) == null || Banco.rs.getString(45) == "") {// Se não existir atualização
																						// coloca vazio na
					// variavel de atualização // tarefa
					tarefa.setAtualizacao("");
				} else {
					tarefa.setAtualizacao(
							"Atualizado " + new SimpleDateFormat("dd/MM/yyyy").format(Banco.rs.getDate(44)) + " Por "
									+ Banco.rs.getString(45));
				}

				tarefa.setChecado(Banco.rs.getString(46));

				// DPTO CORRETO
				// PROCESSO RELACIONADO

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString() + "KambamDao");
			}
		}
		return tarefa;
	}

	// -------------------------------------------------METODOS
	// AUXILIARES------------------------------------------------------------------//
}// FIM DA CLASSE
