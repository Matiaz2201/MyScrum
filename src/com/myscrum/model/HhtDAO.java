package com.myscrum.model;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

public class HhtDAO extends Hht {

	public BD bd = new BD(); // variavel referente ao banco

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++ PONTOS PREVISTOS
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	// **************************************** - FUNÇÕES TOP LEVEL A PARTIR
	// DESTE PONTO (PONTOS PREVISTOS) -
	// **********************************************************

	// ---ok---------------------------------------------- *FUNÇÃO TOP LEVEL
	// RESPONSÁVEL * ----------------------------------------------------------

	public void chamadaDoBancoResponsavel(String Data_ini_periodo, String Data_fim_periodo, String filtro)
			throws ParseException {

		double responsavel = 0;
		int a = 0;
		while (a < getQuantidade()) {

			String sql = filtro;

			try {
				bd.st = bd.con.prepareStatement(sql);

				// SET'S E GET'S
				bd.st.setString(1, getPessoas(a));
				bd.rs = bd.st.executeQuery();

				while (bd.rs.next() == true) {
					responsavel += CalcularResponsavel(bd.rs.getDouble("peso"), bd.rs.getDouble("Porcentagem_tarefa"),
							bd.rs.getDate("Data_Real"), bd.rs.getDate("Data_Final"), Data_ini_periodo,
							Data_fim_periodo);
					System.out.println("calculando responsavel " + getPessoas(a));
				}
				setResponsavel(a, converterDoubleDoisDecimais(responsavel));
				responsavel = 0;

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString());

			}

			a++;

		} // fim do while
	}

	// ---OK---------------------------------------------- *FUNÇÃO TOP LEVEL
	// EXECUTOR * -------------------------------------------------------------

	public void chamadaDoBancoExecutor(String Data_ini_periodo, String Data_fim_periodo, String filtro)
			throws ParseException {

		double executor = 0;
		int a = 0;
		while (a < getQuantidade()) {

			String sql = filtro;

			try {
				bd.st = bd.con.prepareStatement(sql);
				// SET'S E GET'S

				bd.st.setString(1, getPessoas(a));
				bd.st.setString(2, getPessoas(a));
				bd.st.setString(3, getPessoas(a));
				bd.st.setString(4, getPessoas(a));
				bd.st.setString(5, getPessoas(a));
				bd.st.setString(6, getPessoas(a));
				bd.st.setString(7, getPessoas(a));
				bd.st.setString(8, getPessoas(a));
				bd.st.setString(9, getPessoas(a));
				bd.st.setString(10, getPessoas(a));
				bd.st.setString(11, getPessoas(a));
				bd.st.setString(12, getPessoas(a));
				bd.st.setString(13, getPessoas(a));
				bd.st.setString(14, getPessoas(a));
				bd.st.setString(15, getPessoas(a));
				bd.st.setString(16, getPessoas(a));
				bd.st.setString(17, getPessoas(a));
				bd.st.setString(18, getPessoas(a));
				bd.st.setString(19, getPessoas(a));
				bd.st.setString(20, getPessoas(a));

				System.out.println(sql);
				bd.rs = bd.st.executeQuery();

				while (bd.rs.next() == true) {
					executor += CalcularExecutor(bd.rs.getDouble("peso"), bd.rs.getDouble("Porcentagem_excutor"),
							bd.rs.getDouble("Porcentagem_tarefa"), bd.rs.getDate("Data_Real"),
							bd.rs.getDate("Data_Final"), Data_ini_periodo, Data_fim_periodo);
					System.out.println("calculando Executor " + getPessoas(a));
				}
				setExecutor(a, converterDoubleDoisDecimais(executor));
				executor = 0;

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString() + "Chama Banco executor");

			}

			a++;

		} // fim get connection

	} // fim do while getQuantidade

	// ----OK--------------------------------------------- *FUNÇÃO TOP LEVEL
	// AUTORIDADE * ----------------------------------------------------------
	public void chamadaDoBancoAutoridade(String Data_ini_periodo, String Data_fim_periodo, String filtro)
			throws ParseException {

		double autoridade = 0;
		int a = 0;
		while (a < getQuantidade()) {

			String sql = filtro;

			try {
				bd.st = bd.con.prepareStatement(sql);
				// SET'S E GET'S
				bd.st.setString(1, getPessoas(a));
				bd.rs = bd.st.executeQuery();

				while (bd.rs.next() == true) {

					autoridade += CalcularAutoridade(bd.rs.getDouble("peso"), bd.rs.getDouble("Porcentagem_tarefa"),
							bd.rs.getDate("Data_Real"), bd.rs.getDate("Data_Final"), Data_ini_periodo,
							Data_fim_periodo);
					System.out.println("calculando Autoridade " + getPessoas(a));
				}
				setAutoridade(a, converterDoubleDoisDecimais(autoridade));
				autoridade = 0;

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString());

			}

			a++;

		} // fim do while
	}

	// **************************************** - FUNÇÕES DE CALCULO A PARTIR
	// DESTE PONTO (PONTOS PREVISTOS) -
	// *****************************************

	// --OK-------------------------------------------- CALCULO COMO RESPONSÁVEL
	// -----------------------------------------------------------------------
	public double CalcularResponsavel(double peso, double porcentagemTarefa, Date Data_real, Date Data_fim,
			String Data_ini_periodo, String Data_fim_periodo) throws ParseException {
		double responsavel = 0;
		// 15%
		// condições de data
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		Date Data_Inicio_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_real));
		Date Data_Fim_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_fim));
		Date Data_Inicio_Periodo = df.parse(Data_ini_periodo);
		Date Data_Fim_Periodo = df.parse(Data_fim_periodo);

		Date Data_Inicio_Calculo = null;
		Date Data_Fim_Calculo = null;

		long diasTarefa = (Data_Fim_Tarefa.getTime() - Data_Inicio_Tarefa.getTime()) + 3600000; // 1
																								// hora
																								// para
																								// compensar
																								// horário
																								// de
																								// verão
		long diasTarefaCalc = (diasTarefa / 86400000L) + 1;

		// Condição da Data de inicio
		if (Data_Inicio_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Inicio_Periodo) && Data_Inicio_Tarefa.before(Data_Fim_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Inicio_Calculo = Data_Inicio_Tarefa;

		} else {

			Data_Inicio_Calculo = Data_Inicio_Periodo;

		}
		// Condição da Data de inicio

		/* ------------------------------------ */

		// Condição da Data de fim
		if (Data_Fim_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Inicio_Periodo) && Data_Fim_Tarefa.before(Data_Fim_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Fim_Calculo = Data_Fim_Tarefa;

		} else {

			Data_Fim_Calculo = Data_Fim_Periodo;
		}
		// Condição da Data de fim

		long dt = (Data_Fim_Calculo.getTime() - Data_Inicio_Calculo.getTime()) + 3600000; // 1
																							// hora
																							// para
																							// compensar
																							// horário
																							// de
																							// verão
		long dias = (dt / 86400000L) + 1;
		// System.out.println("Dias da tarefa dentro do periodo: " + dias);
		// System.out.println("Dias da tarefa: " + diasTarefaCalc);

		double resultado;
		if(diasTarefaCalc == 0) {//evitando divisão por zero
			resultado  = 0;
		}else {
			resultado = Double.parseDouble(String.valueOf(dias)) / Double.parseDouble(String.valueOf(diasTarefaCalc)) * peso; // (pontos da tarefa e porcentagem padrão do executor
			// 80%)
		} 
		double resultFinal = (resultado * 0.15); // 15%
		responsavel = resultFinal;

		return responsavel;
	}

	// --OK-------------------------------------------- CALCULO COMO EXECUTOR
	// -----------------------------------------------------------------------
	public double CalcularExecutor(double peso, double porcentagemExec, double porcentagemTarefa, Date Data_real,
			Date Data_fim, String Data_ini_periodo, String Data_fim_periodo) throws ParseException {

		double executor = 0;
		// 80%
		// condições de data
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		Date Data_Inicio_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_real));
		Date Data_Fim_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_fim));
		Date Data_Inicio_Periodo = df.parse(Data_ini_periodo);
		Date Data_Fim_Periodo = df.parse(Data_fim_periodo);

		Date Data_Inicio_Calculo = null;
		Date Data_Fim_Calculo = null;

		long diasTarefa = (Data_Fim_Tarefa.getTime() - Data_Inicio_Tarefa.getTime()) + 3600000; // 1
																								// hora
																								// para
																								// compensar
																								// horário
																								// de
																								// verão
		long diasTarefaCalc = (diasTarefa / 86400000L) + 1;

		// Condição da Data de inicio
		if (Data_Inicio_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Inicio_Periodo) && Data_Inicio_Tarefa.before(Data_Fim_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Inicio_Calculo = Data_Inicio_Tarefa;

		} else {

			Data_Inicio_Calculo = Data_Inicio_Periodo;

		}
		// Condição da Data de inicio

		/* ------------------------------------ */

		// Condição da Data de fim
		if (Data_Fim_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Inicio_Periodo) && Data_Fim_Tarefa.before(Data_Fim_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Fim_Calculo = Data_Fim_Tarefa;

		} else {

			Data_Fim_Calculo = Data_Fim_Periodo;
		}
		// Condição da Data de fim

		long dt = (Data_Fim_Calculo.getTime() - Data_Inicio_Calculo.getTime()) + 3600000; // 1
																							// hora
																							// para
																							// compensar
																							// horário
																							// de
																							// verão
		long dias = (dt / 86400000L) + 1;
		// System.out.println("Dias da tarefa dentro do periodo: " + dias);
		// System.out.println("Dias da tarefa: " + diasTarefaCalc);

		double resultado;
		
		if(diasTarefaCalc == 0) {
			resultado = 0;
		}else {
			resultado = Double.parseDouble(String.valueOf(dias)) / Double.parseDouble(String.valueOf(diasTarefaCalc)) * peso; // (pontos da tarefa e porcentagem padrão do executor
							// 80%)
		}
		
		double resultFinal = (resultado * 0.80) * (porcentagemExec/100); // 80%
		executor = resultFinal;

		return executor;

		// System.out.printf("Resultado é igual á: %.2f \n", resultado); //
		// imprime pontos total da tarefa dentro do periodo
		/// System.out.printf("Resultado final é igual á: %.2f \n",
		// resultFinal);

	}

	// ----OK------------------------------------------ CALCULO COMO AUTORIDADE
	// ---------------------------------------------------------------------
	public double CalcularAutoridade(double peso, double porcentagemTarefa, Date Data_real, Date Data_fim,
			String Data_ini_periodo, String Data_fim_periodo) throws ParseException {

		double autoridade = 0;
		// 5%
		// condições de data
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		Date Data_Inicio_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_real));
		Date Data_Fim_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_fim));
		Date Data_Inicio_Periodo = df.parse(Data_ini_periodo);
		Date Data_Fim_Periodo = df.parse(Data_fim_periodo);

		Date Data_Inicio_Calculo = null;
		Date Data_Fim_Calculo = null;

		long diasTarefa = (Data_Fim_Tarefa.getTime() - Data_Inicio_Tarefa.getTime()) + 3600000; // 1
																								// hora
																								// para
																								// compensar
																								// horário
																								// de
																								// verão
		long diasTarefaCalc = (diasTarefa / 86400000L) + 1;

		// Condição da Data de inicio
		if (Data_Inicio_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Inicio_Periodo) && Data_Inicio_Tarefa.before(Data_Fim_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Inicio_Calculo = Data_Inicio_Tarefa;

		} else {

			Data_Inicio_Calculo = Data_Inicio_Periodo;

		}
		// Condição da Data de inicio

		/* ------------------------------------ */

		// Condição da Data de fim
		if (Data_Fim_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Inicio_Periodo) && Data_Fim_Tarefa.before(Data_Fim_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Fim_Calculo = Data_Fim_Tarefa;

		} else {

			Data_Fim_Calculo = Data_Fim_Periodo;
		}
		// Condição da Data de fim

		long dt = (Data_Fim_Calculo.getTime() - Data_Inicio_Calculo.getTime()) + 3600000; // 1
																							// hora
																							// para
																							// compensar
																							// horário
																							// de
																							// verão
		long dias = (dt / 86400000L) + 1;
		// System.out.println("Dias da tarefa dentro do periodo: " + dias);
		// System.out.println("Dias da tarefa: " + diasTarefaCalc);

		double resultado;
		if(diasTarefaCalc == 0) {
			resultado = 0;
		}else {
			resultado = Double.parseDouble(String.valueOf(dias)) / Double.parseDouble(String.valueOf(diasTarefaCalc)) * peso; // (pontos da tarefa e porcentagem padrão do executor					// 80%)
		}
		
		double resultFinal = (resultado * 0.05); // 5%
		autoridade = resultFinal;

		return autoridade;
		// System.out.printf("Resultado é igual á: %.2f \n", resultado); //
		// imprime pontos total da tarefa dentro do periodo
		// System.out.printf("Resultado final é igual á: %.2f \n", resultFinal);

	}

	// ------------------------------------------------- METODO QUE CALCULA
	// PONTOS ----------------------------------------------------------------
	public void calculaPontos() {
		int a = 0;
		while (a < getQuantidade()) {
			double result = getExecutor(a) + getAutoridade(a) + getResponsavel(a);
			setPontos(a, arredondar(result, 2, 0));
			a++;
		}

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ FINAL DOS PONTOS
	// PREVISTOS ++++++++++++++++++++++++++++++++++++++++++++++++++

	// ================================================= INICIO DOS PONTOS
	// REALIZADOS =======================================================

	// **************************************** - FUNÇÕES TOP LEVEL A PARTIR
	// DESTE PONTO (PONTOS REALIZADOS) -
	// ***************************************************

	// -OK--------------------------------------------- COMO RESPONSAVEL
	// -----------------------------------------------------------------------
	public void chamadaDoBancoResponsavelR(String Data_ini_periodo, String Data_fim_periodo, String filtro)
			throws ParseException {

		double responsavelR = 0;
		int a = 0;
		while (a < getQuantidade()) {

			String sql = filtro;

			try {
				bd.st = bd.con.prepareStatement(sql);
				// SET'S E GET'S
				bd.st.setString(1, getPessoas(a));

				bd.rs = bd.st.executeQuery();

				while (bd.rs.next() == true) {
					responsavelR += CalcularResponsavelR(bd.rs.getDouble("peso"), bd.rs.getDouble("Porcentagem_tarefa"),
							bd.rs.getDate("Data_Real"), bd.rs.getDate("Data_Final"), Data_ini_periodo,
							Data_fim_periodo);
					System.out.println("calculando responsavel R " + getPessoas(a));
				}
				setResponsavelR(a, converterDoubleDoisDecimais(responsavelR));
				responsavelR = 0;

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString());

			}

			a++;

		} // fim do while
	}

	// --OK--------------------------------------------- COMO Executor
	// -----------------------------------------------------------------------
	public void chamadaDoBancoExecutorR(String Data_ini_periodo, String Data_fim_periodo, String filtro)
			throws ParseException {

		double executorR = 0;
		int a = 0;
		while (a < getQuantidade()) {

			String sql = filtro;

			try {
				bd.st = bd.con.prepareStatement(sql);
				// SET'S E GET'S
				bd.st.setString(1, getPessoas(a));
				bd.st.setString(2, getPessoas(a));
				bd.st.setString(3, getPessoas(a));
				bd.st.setString(4, getPessoas(a));
				bd.st.setString(5, getPessoas(a));
				bd.st.setString(6, getPessoas(a));
				bd.st.setString(7, getPessoas(a));
				bd.st.setString(8, getPessoas(a));
				bd.st.setString(9, getPessoas(a));
				bd.st.setString(10, getPessoas(a));
				bd.st.setString(11, getPessoas(a));
				bd.st.setString(12, getPessoas(a));
				bd.st.setString(13, getPessoas(a));
				bd.st.setString(14, getPessoas(a));
				bd.st.setString(15, getPessoas(a));
				bd.st.setString(16, getPessoas(a));
				bd.st.setString(17, getPessoas(a));
				bd.st.setString(18, getPessoas(a));
				bd.st.setString(19, getPessoas(a));
				bd.st.setString(20, getPessoas(a));

				bd.rs = bd.st.executeQuery();

				while (bd.rs.next() == true) {
					executorR += CalcularExecutorR(bd.rs.getDouble("peso"), bd.rs.getDouble("Porcentagem_tarefa"), bd.rs.getDouble("Porcentagem_executor"),
							bd.rs.getDate("Data_Real"), bd.rs.getDate("Data_Final"), Data_ini_periodo,
							Data_fim_periodo);
					System.out.println("calculando Executor R " + getPessoas(a));
				}
				setExecutorR(a, converterDoubleDoisDecimais(executorR));
				executorR = 0;

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString());

			}
			a++;
		}

	} // fim do while

	// --OK-------------------------------------------- COMO RESPONSAVEL
	// -----------------------------------------------------------------------
	public void chamadaDoBancoAutoridadeR(String Data_ini_periodo, String Data_fim_periodo, String filtro)
			throws ParseException {

		double autoridadeR = 0;
		int a = 0;
		while (a < getQuantidade()) {

			String sql = filtro;

			try {
				bd.st = bd.con.prepareStatement(sql);
				// SET'S E GET'S
				bd.st.setString(1, getPessoas(a));

				bd.rs = bd.st.executeQuery();

				while (bd.rs.next() == true) {
					autoridadeR += CalcularAutoridadeR(bd.rs.getDouble("peso"), bd.rs.getDouble("Porcentagem_tarefa"),
							bd.rs.getDate("Data_Real"), bd.rs.getDate("Data_Final"), Data_ini_periodo,
							Data_fim_periodo);
					System.out.println("calculando autoridade R " + getPessoas(a));
				}
				setAutoridadeR(a, converterDoubleDoisDecimais(autoridadeR));
				autoridadeR = 0;

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString());

			}
			a++;
		} // fim do while
	}

	// **************************************** - FUNÇÕES DE CALCULO A PARTIR
	// DESTE PONTO (PONTOS REALIZADOS) - *************************************

	// ---OK------------------------------------------- CALCULO COMO RESPONSAVEL
	// --------------------------------------------------------------------
	public double CalcularResponsavelR(double peso, double porcentagemTarefa, Date Data_real, Date Data_fim,
			String Data_ini_periodo, String Data_fim_periodo) throws ParseException {

		double responsavelR = 0;
		// 15%
		// condições de data
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		Date Data_Inicio_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_real));
		Date Data_Fim_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_fim));
		Date Data_Inicio_Periodo = df.parse(Data_ini_periodo);
		Date Data_Fim_Periodo = df.parse(Data_fim_periodo);

		Date Data_Inicio_Calculo = null;
		Date Data_Fim_Calculo = null;

		long diasTarefa = (Data_Fim_Tarefa.getTime() - Data_Inicio_Tarefa.getTime()) + 3600000; // 1
																								// hora
																								// para
																								// compensar
																								// horário
																								// de
																								// verão
		long diasTarefaCalc = (diasTarefa / 86400000L) + 1;

		// Condição da Data de inicio
		if (Data_Inicio_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Inicio_Periodo) && Data_Inicio_Tarefa.before(Data_Fim_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Inicio_Calculo = Data_Inicio_Tarefa;

		} else {

			Data_Inicio_Calculo = Data_Inicio_Periodo;

		}
		// Condição da Data de inicio

		/* ------------------------------------ */

		// Condição da Data de fim
		if (Data_Fim_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Inicio_Periodo) && Data_Fim_Tarefa.before(Data_Fim_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Fim_Calculo = Data_Fim_Tarefa;

		} else {

			Data_Fim_Calculo = Data_Fim_Periodo;
		}
		// Condição da Data de fim

		long dt = (Data_Fim_Calculo.getTime() - Data_Inicio_Calculo.getTime()) + 3600000; // 1
																							// hora
																							// para
																							// compensar
																							// horário
																							// de
																							// verão
		long dias = (dt / 86400000L) + 1;
		// System.out.println("Dias da tarefa dentro do periodo: " + dias);
		// System.out.println("Dias da tarefa: " + diasTarefaCalc);

		double resultado;
		if(diasTarefaCalc == 0) {
			resultado = 0;
		}else {
			resultado = (Double.parseDouble(String.valueOf(dias))/ Double.parseDouble(String.valueOf(diasTarefaCalc))) * peso; // (ponto da tarefa e porcentagem padrão do executor 80%)
			
		}
		// convertando as casas decimais
		double resultFinal = ((resultado * 0.15) * (porcentagemTarefa / 100)); // 15%
		responsavelR = resultFinal;

		return responsavelR;
		// System.out.printf("Resultado é igual á: %.2f \n", resultado); //
		// imprime pontos total da tarefa dentro do periodo
		// System.out.printf("Resultado final é igual á: %.2f \n", resultFinal);

	}

	// --OK-------------------------------------------- CALCULO COMO EXECUTOR
	// -----------------------------------------------------------------------
	public double CalcularExecutorR(double peso, double porcentagemTarefa, double porcentagem_executor, Date Data_real, Date Data_fim,
			String Data_ini_periodo, String Data_fim_periodo) throws ParseException {

		double executorR = 0;
		// 80%
		// condições de data
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		Date Data_Inicio_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_real));
		Date Data_Fim_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_fim));
		Date Data_Inicio_Periodo = df.parse(Data_ini_periodo);
		Date Data_Fim_Periodo = df.parse(Data_fim_periodo);

		Date Data_Inicio_Calculo = null;
		Date Data_Fim_Calculo = null;

		long diasTarefa = (Data_Fim_Tarefa.getTime() - Data_Inicio_Tarefa.getTime()) + 3600000; // 1
																								// hora
																								// para
																								// compensar
																								// horário
																								// de
																								// verão
		long diasTarefaCalc = (diasTarefa / 86400000L) + 1;

		// Condição da Data de inicio
		if (Data_Inicio_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Inicio_Periodo) && Data_Inicio_Tarefa.before(Data_Fim_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Inicio_Calculo = Data_Inicio_Tarefa;

		} else {

			Data_Inicio_Calculo = Data_Inicio_Periodo;

		}
		// Condição da Data de inicio

		/* ------------------------------------ */

		// Condição da Data de fim
		if (Data_Fim_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Inicio_Periodo) && Data_Fim_Tarefa.before(Data_Fim_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Fim_Calculo = Data_Fim_Tarefa;

		} else {

			Data_Fim_Calculo = Data_Fim_Periodo;
		}
		// Condição da Data de fim

		long dt = (Data_Fim_Calculo.getTime() - Data_Inicio_Calculo.getTime()) + 3600000; // 1 hora A + 
																							
		long dias = (dt / 86400000L) + 1;
		// System.out.println("Dias da tarefa dentro do periodo: " + dias);
		// System.out.println("Dias da tarefa: " + diasTarefaCalc);

		double resultado;
		if(diasTarefaCalc == 0) {
			resultado = 0;
		}else {
			resultado = Double.parseDouble(String.valueOf(dias)) / Double.parseDouble(String.valueOf(diasTarefaCalc)) * peso; // (pontos da tarefa e porcentagem padrão do executor 80%)		
		}
		double resultFinal = ((resultado * 0.80) * (porcentagemTarefa / 100)) * (porcentagem_executor/100); // 80%
		executorR = resultFinal;

		return executorR;
		// System.out.printf("Resultado é igual á: %.2f \n", resultado); //
		// imprime pontos total da tarefa dentro do periodo
		// System.out.printf("Resultado final é igual á: %.2f \n", resultFinal);

	}

	// --OK-------------------------------------------- CALCULO COMO EXECUTOR
	// -----------------------------------------------------------------------
	public double CalcularAutoridadeR(double peso, double porcentagemTarefa, Date Data_real, Date Data_fim,
			String Data_ini_periodo, String Data_fim_periodo) throws ParseException {

		double autoridadeR = 0;
		// 5%
		// condições de data
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);

		Date Data_Inicio_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_real));
		Date Data_Fim_Tarefa = df.parse(new SimpleDateFormat("dd/MM/yyyy").format(Data_fim));
		Date Data_Inicio_Periodo = df.parse(Data_ini_periodo);
		Date Data_Fim_Periodo = df.parse(Data_fim_periodo);

		Date Data_Inicio_Calculo = null;
		Date Data_Fim_Calculo = null;

		long diasTarefa = (Data_Fim_Tarefa.getTime() - Data_Inicio_Tarefa.getTime()) + 3600000; // 1
																								// hora
																								// para
																								// compensar
																								// horário
																								// de
																								// verão
		long diasTarefaCalc = (diasTarefa / 86400000L) + 1;

		// Condição da Data de inicio
		if (Data_Inicio_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Inicio_Periodo) && Data_Inicio_Tarefa.before(Data_Fim_Periodo)
				|| Data_Inicio_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Inicio_Calculo = Data_Inicio_Tarefa;

		} else {

			Data_Inicio_Calculo = Data_Inicio_Periodo;

		}
		// Condição da Data de inicio

		/* ------------------------------------ */

		// Condição da Data de fim
		if (Data_Fim_Tarefa.after(Data_Inicio_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Inicio_Periodo) && Data_Fim_Tarefa.before(Data_Fim_Periodo)
				|| Data_Fim_Tarefa.equals(Data_Fim_Periodo)) {

			Data_Fim_Calculo = Data_Fim_Tarefa;

		} else {

			Data_Fim_Calculo = Data_Fim_Periodo;
		}
		// Condição da Data de fim

		long dt = (Data_Fim_Calculo.getTime() - Data_Inicio_Calculo.getTime()) + 3600000; // 1
																							// hora
																							// para
																							// compensar
																							// horário
																							// de
																							// verão
		long dias = (dt / 86400000L) + 1;
		// System.out.println("Dias da tarefa dentro do periodo: " + dias);
		// System.out.println("Dias da tarefa: " + diasTarefaCalc);

		double resultado;
		if(diasTarefaCalc == 0) {
			resultado = 0;
		}else {
			resultado = Double.parseDouble(String.valueOf(dias)) / Double.parseDouble(String.valueOf(diasTarefaCalc)) * peso; // (pontos da tarefa e porcentagem padrão do executor 80%)	
		}
		
		double resultFinal = ((resultado * 0.05)) * (porcentagemTarefa / 100); // 5%
		autoridadeR = resultFinal;

		return autoridadeR;
		// System.out.printf("Resultado é igual á: %.2f \n", resultado); //
		// imprime pontos total da tarefa dentro do periodo
		// System.out.printf("Resultado final é igual á: %.2f \n", resultFinal);

	}

	// --------------------------------------------------- METODO QUE CALCULA
	// PONTOS ----------------------------------------------------------------
	public void calculaPontosR() {

		int a = 0;
		while (a < getQuantidade()) {

			// JOptionPane.showMessageDialog(null,"Get Executor R: " +
			// getExecutorR(a) + " Get Autoridade R: " + getAutoridadeR(a) + "Get
			// Responsavel R: " + getResponsavelR(a));

			double result = getExecutorR(a) + getAutoridadeR(a) + getResponsavelR(a);
			setPontosR(a, converterDoubleDoisDecimais(result));
			a++;
		}

	}

	// ********************************************* - MÉTODOS EXTRAS A PARTIR
	// DESTE PONTO - ****************************************************

	// ARREDONDAMENTO
	// Parâmetros:
	/**
	 * 1 - Valor a arredondar. 2 - Quantidade de casas depois da vírgula. 3 -
	 * Arredondar para cima ou para baixo? Para cima = 0 (ceil) Para baixo = 1 ou
	 * qualquer outro inteiro (floor)
	 **/
	double arredondar(double valor, int casas, int ceilOrFloor) {
		double arredondado = valor;
		arredondado *= (Math.pow(10, casas));
		if (ceilOrFloor == 0) {
			arredondado = Math.ceil(arredondado);
		} else {
			arredondado = Math.floor(arredondado);
		}
		arredondado /= (Math.pow(10, casas));
		return arredondado;
	}
	// -------------------------------------

	// METODO QUE PEGA AS INFORMAÇÕES DO BANCO REFERENTE A O SALARY
	public void HHT_Salary() {
		String sql = "SELECT salario, carga_horaria \r\n" + "FROM pessoa";

		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();

			int a = 0;
			while (bd.rs.next()) {
				double custo = bd.rs.getDouble("salario") / 220;
				setHHTSalary(a, arredondar(custo, 2, 0));
				a++;
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());

		}
	}

	// METODO QUE CALCULA O ATRASO
	public void Atraso() {

		int a = 0;
		while (a < getQuantidade()) {

			if (getPontos(a) == 0 && getPontosR(a) == 0) {
				setHHTAtraso(a, "0%");
			} else {
				int result = (int) ((int) (getPontos(a) - getPontosR(a)) / getPontos(a) * 100);
				setHHTAtraso(a, String.valueOf(result) + "%");

			}
			a++;

		} // fim do while
	}// fim do metodo

	// METODO QUE FAZ A CHAMADA DO BANCO PARA O HORAS PERIODO PERIODO
	public void horas_periodo(String Data_ini_Periodo, String Data_fim_Periodo) throws ParseException {

		String sql = "SELECT salario, carga_horaria \r\n" + "FROM pessoa";

		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();

			int a = 0;
			while (bd.rs.next() == true) {

				CalcularHorasPeriodo(bd.rs.getInt("carga_horaria"), a, Data_ini_Periodo, Data_fim_Periodo);
				a++;
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());

		}
	}

	// -----------------------------------------------------####### MEIO
	// ########----------------------------------------------------
	// METODO QUE CALCULA AS HORAS NO PERIODO
	public void CalcularHorasPeriodo(int carga_horaria, int posicao, String Data_ini_Periodo, String Data_fim_Periodo)
			throws ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data_inicial = formato.parse(Data_ini_Periodo);
		Date data_fim = formato.parse(Data_fim_Periodo);

		long diferenca = (data_fim.getTime() - data_inicial.getTime()) + 3600000; // 1

		// Contando feriados no periodo
		int feriados = ContarFeriados(DataParaoBanco(Data_ini_Periodo), DataParaoBanco(Data_fim_Periodo));
		// Fim da contagem de feriados no periodo

		long dias = (diferenca / 86400000L) + 1;

		double conversao = Double.parseDouble(String.valueOf(dias));
		// System.out.println("A conversão é : " + conversao);
		double result = (conversao - feriados) * carga_horaria;
		setHHTHorasPeriodo(posicao, arredondar(result, 2, 0));

	}

	// METODO QUE CALCULA O DESVIO HORA HOMEM
	public void DesvioHoraHomem() {

		int a = 0;
		while (a < getQuantidade()) {
			double result = (getHHTHorasPeriodo(a) - getPontosR(a)) / getHHTHorasPeriodo(a);
			setHHTDesvio(a, converterDoubleDoisDecimais(result * 100) + "%"); // MULTIPLICAR POR
			// 100 PARA
			// FICAR COMO
			// PORCENTAGEM
			a++;
		}
	}

	// METODO QUE FAZ A SOMA HHT
	public void SomaHHT() {

		int a = 0;
		while (a < getQuantidade()) {
			double result = getPontosR(a) * getHHTSalary(a);
			setHHTSoma(a, "R$: " + arredondar(result, 2, 0));
			a++;
		}
	}
	/*----------------------------------------------------------------PRINCIPAIS-----------------------------------------------------------*/

	// metodo que calcula o custo total dos usuarios
	public void Custo() {
		double sum = 0;

		for (int a = 0; a < getArraySoma().size(); a++) {
			sum += Double.parseDouble(getHHTSoma(a).replace("R$:", ""));
			setCusto(arredondar(sum, 2, 0));

		} // fim do for

	}// fim do metodo

	// --------------------------------------------------- METODO QUE CONTA A
	// QTD DE USUARIOS ---------------------------------------------------
	public void ContarPessoas() {

		String sql = "SELECT COUNT(*) AS pessoas\r\n" + "FROM pessoa\r\n" + "WHERE pessoa.ativo = 1";

		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();

			if (bd.rs.next() == true) {
				setQuantidade(bd.rs.getInt(1));
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString() + "CONTAR PESSOAS");

		}
	}

	// --------------------------------------------- METODO QUE LISTA O NOME DE
	// TODOS AS PESSOAS -----------------------------------------------
	public void ListarPessoas() {

		String sql = "SELECT id_pessoa AS ID, nome AS Usuarios,\r\n"
				+ "(SELECT centro_custo.centrocusto FROM centro_custo WHERE centro_custo.id_centro_custo=pessoa.id_centrocusto) AS Centro,\r\n"
				+ "(SELECT departamento.departamento FROM departamento WHERE departamento.id_departamento=pessoa.id_departamento) AS Departamento\r\n"
				+ "FROM pessoa \r\n" + "WHERE pessoa.ativo = 1\r\n" + "GROUP BY nome";

		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();

			int a = 0;
			while (a < getQuantidade()) {

				if (bd.rs.next() == true) {

					setID(a, bd.rs.getInt("ID")); // colocar
					setPessoas(a, bd.rs.getString("Usuarios"));
					setCentroCusto(a, bd.rs.getString("Centro"));
					setDepartamento(a, bd.rs.getString("Departamento"));

				}
				a++;
			} // fim do while

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString() + "LISTAR PESSOAS");

		}
	}

	public static Double converterDoubleDoisDecimais(double precoDouble) {
		DecimalFormat fmt = new DecimalFormat("##.00");
	
		String string = fmt.format(precoDouble);
		
		String[] part = string.split("[,]");

		if (part.length == 2) {
			if (part[0].equals("") || part[0].equals(null)) {
				part[0] = "0";
			}
			if (part[1].equals("") || part[1].equals(null)) {
				part[1] = "0";
			}
		}
		
		String string2 = part[0] + "." + part[1];
	
		double preco = Double.parseDouble(string2);
		return preco;
		
	}

	// -----------------------------------------METODO PARA LIMPEZA DAS
	// VARIAVEIS PARA REFAZERS OS
	// CALCULOS---------------------------------------
	public void LimparVariaveis() {
		// PONTOS PREVISTOS
		executor.clear();
		responsavel.clear();
		autoridade.clear();
		pessoas.clear();
		pontos.clear();

		// PONTOS REALIZADOS
		executorR.clear();
		responsavelR.clear();
		autoridadeR.clear();
		pontosR.clear();
		hhtSoma.clear();
		quantidade = 0;

	}

	// Metodos complementares
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
				System.out.println("Feriado igual");
			}
			if (getFeriados().get(a).after(inicioData) && getFeriados().get(a).before(fimData)) {// Se algum feriado
																									// estiver entre
																									// data inicio e
																									// data fim conta
																									// mais um em
																									// feriados
				feriados++;
				System.out.println("Feriado entre");
			}
			a++;
		}

		return feriados;
	}
	// fecha a classe
}
