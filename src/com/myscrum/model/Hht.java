package com.myscrum.model;

import java.sql.Date;
import java.util.ArrayList;

public class Hht {

	static int quantidade; // VARIAVEL PAI DE TODAS
	static int id;
	static boolean loading;
	
	static ArrayList<String> pessoas = new ArrayList<>(); // ARRAY PESSOAS
	static ArrayList<String> departamento = new ArrayList<>(); // ARRAY DPTO
	static ArrayList<String> centroCusto = new ArrayList<>(); // ARRAY CC
	static ArrayList<Double> responsavel = new ArrayList<>(); // ARRAY PONTOS
	static ArrayList<Double> executor = new ArrayList<>(); // ARRAY EXECUTORES
	static ArrayList<Double> autoridade = new ArrayList<>(); // ARRAY AUTORIDADE
	static ArrayList<Double> checado = new ArrayList<>(); // ARRAY CHECADO
	static ArrayList<Double> pontos = new ArrayList<>(); // ARRAY PONTOS

	// ----------------------------------------------------- PONTOS PREVISTOS
	// ------------------------------------------------------------------
	public void setLoading(boolean var){
		loading = var;
	}
	public boolean getLoading(){
		return loading;
	}
	
	// PEGANDO A QUANTIDADE DE USUARIOS
	public void setQuantidade(int quantidade) {
		Hht.quantidade = quantidade;
	}

	public static int getQuantidade() {
		return quantidade;
	}

	public void setID(int posicao, int id) {
		Hht.id = id;
	}

	public static int getID() {
		return id;
	}

	// SETANDO ARRAY PESSOA
	public static void setPessoas(int posicao, String pessoas) {
		Hht.pessoas.add(posicao, pessoas);
	}

	public static String getPessoas(int posicao) {
		return pessoas.get(posicao);
	}

	// setando get arrayList
	public static ArrayList<String> getArrayPessoas() {
		return pessoas;
	}

	// SETANDO ARRAY CENTRO DE CUSTO
	public static void setCentroCusto(int posicao, String centro_custo) {
		Hht.centroCusto.add(posicao, centro_custo);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<String> getArrayCentroCusto() {
		return centroCusto;
	}

	// SETANDO ARRAY DPTO
	public static void setDepartamento(int posicao, String departamento) {
		Hht.departamento.add(posicao, departamento);
	}

	public static String getDepartamento(int posicao) {
		return departamento.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<String> getArrayDepartamento() {
		return departamento;
	}

	// SETANDO ARRAY PONTOS - Pontos previstos
	public static void setPontos(int posicao, double pontos) {
		Hht.pontos.add(posicao, pontos);
	}

	public static double getPontos(int posicao) {
		return pontos.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArrayPontos() {
		return pontos;
	}

	// SETANDO ARRAY RESPONSAVEL - Pontos previstos
	public static void setResponsavel(int posicao, double responsavel) {
		Hht.responsavel.add(posicao, responsavel);

	}

	public static double getResponsavel(int posicao) {
		return responsavel.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArrayResponsavel() {
		return responsavel;
	}

	// SETANDO ARRAY Executor - Pontos previstos
	public static void setExecutor(int posicao, double executor) {
		Hht.executor.add(posicao, executor);

	}

	public static double getExecutor(int posicao) {
		return executor.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArrayExecutor() {
		return executor;
	}

	// SETANDO ARRAY AUTORIDADE - Pontos previstos
	public static void setAutoridade(int posicao, Double string) {
		Hht.autoridade.add(posicao, string);

	}

	public static double getAutoridade(int posicao) {
		return autoridade.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArrayAutoridade() {
		return autoridade;
	}

	// --------------------------------- SETANDO ARRAY CHECADO - Pontos
	// previstos *(NÃO IMPLEMENTADO)*
	// ----------------------------------------------

	// SETANDO VETOR CHECADO - Pontos previstos
	public static void setChecado(Double checado) {
		Hht.checado.add(checado);
	}

	public static Double getChecado(int posicao) {
		return checado.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArrayChecado() {
		return checado;
	}
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% - FIM -
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	// ----------------------------------------------------- PONTOS REALIZADOS
	// NO PERIODO -------------------------------------------------------

	static ArrayList<Double> responsavelR = new ArrayList<>(); // ARRAY PONTOS
	static ArrayList<Double> executorR = new ArrayList<>(); // ARRAY EXECUTORES
	static ArrayList<Double> autoridadeR = new ArrayList<>(); // ARRAY																// AUTORIDADE
	static ArrayList<Double> pontosR = new ArrayList<>(); // ARRAY PONTOS
	static ArrayList<Double> checadoR = new ArrayList<>(); // ARRAY CHECADO

	// SETANDO ARRAY RESPONSAVEL - Pontos realizados
	public static void setResponsavelR(int posicao, double responsavelR) {
		Hht.responsavelR.add(posicao, responsavelR);

	}

	public static double getResponsavelR(int posicao) {
		return responsavelR.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArrayResponsavelR() {
		return responsavelR;
	}

	// SETANDO ARRAY EXECUTOR - Pontos realizados
	public static void setExecutorR(int posicao, double executorR) {
		Hht.executorR.add(posicao, executorR);

	}

	public static double getExecutorR(int posicao) {
		return executorR.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArrayExecutorR() {
		return executorR;
	}

	// SETANDO ARRAY AUTORIDADE - Pontos realizados
	public static void setAutoridadeR(int posicao, double autoridadeR) {
		Hht.autoridadeR.add(posicao, autoridadeR);

	}

	public static double getAutoridadeR(int posicao) {
		return autoridadeR.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArrayAutoridadeR() {
		return autoridadeR;
	}
	
	// SETANDO ARRAY PONTOS - Pontos realizados
	public static void setPontosR(int posicao, Double string) {
		Hht.pontosR.add(posicao, string);
	}

	public static Double getPontosR(int posicao) {
		return pontosR.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArrayPontosR() {
		return pontosR;
	}
	// --------------------------------- SETANDO ARRAY CHECADO - Pontos
	// REALIZADOS *(NÃO IMPLEMENTADO)*
	// ----------------------------------------------

	// SETANDO ARRAY CHECADO - Pontos realizados
	public static void setChecadoR(Double checadoR) {
		Hht.checado.add(checadoR);
	}

	public static Double getChecadoR(int posicao) {
		return checadoR.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArrayChecadoR() {
		return checadoR;
	}
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% - FIM -
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	// ############################################### -- METODOS EXTRAS --
	// #########################################

	static double custo;
	static ArrayList<Double> hhtSalary = new ArrayList<>(); // ARRAY salary
	static ArrayList<String> hhtAtraso = new ArrayList<>(); // ARRAY atraso
	static ArrayList<Double> hhtHorasPeriodo = new ArrayList<>(); // ARRAY horas																// periodo
	static ArrayList<String> hhtDesvio = new ArrayList<>(); // ARRAY horas															// periodo
	static ArrayList<String> hhtSoma = new ArrayList<>(); // ARRAY soma
    static ArrayList<Date> feriados = new ArrayList<>();
	
	// SETANDO ARRAY HHT SALARY - METODOS EXTRAS
	public static void setHHTSalary(int posicao, double Salary) {
		Hht.hhtSalary.add(posicao, Salary);
	}

	public static double getHHTSalary(int posicao) {
		return hhtSalary.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArraySalary() {
		return hhtSalary;
	}

	// SETANDO ARRAY HHT ATRASO - METODOS EXTRAS
	public static void setHHTAtraso(int posicao, String atraso) {
		Hht.hhtAtraso.add(posicao, atraso);
	}

	public static String getHHTAtraso(int posicao) {
		return hhtAtraso.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<String> getArrayAtraso() {
		return hhtAtraso;
	}

	// SETANDO ARRAY HHT HORAS PERIODO - METODOS EXTRAS
	public static void setHHTHorasPeriodo(int posicao, double horas_periodo) {
		Hht.hhtHorasPeriodo.add(posicao, horas_periodo);
	}

	public static double getHHTHorasPeriodo(int posicao) {
		return hhtHorasPeriodo.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<Double> getArrayHorasP() {
		return hhtHorasPeriodo;
	}

	public static void setHHTDesvio(int posicao, String desvio) {
		Hht.hhtDesvio.add(posicao, desvio);
	}

	public static String getHHTDesvio(int posicao) {
		return hhtDesvio.get(posicao);
	}

	// ---------------------GET-----------------------------------
	public static ArrayList<String> getArrayDesvio() {
		return hhtDesvio;
	}

	public static void setHHTSoma(int posicao, String soma) {
		Hht.hhtSoma.add(posicao, soma);
	}

	public static String getHHTSoma(int posicao) {
		return hhtSoma.get(posicao);

	}

	// ---------------------GET-----------------------------------
	public static ArrayList<String> getArraySoma() {
		return hhtSoma;
	}

	// arrumar para poder limpar a variavel
	public static void setCusto(double custo) {
		Hht.custo = custo;
	}

	public double getCusto() {
		return custo;
	}
	
	public static void setFeriados(Date feriado) {
	    Hht.feriados.add(feriado);
	}

	public static ArrayList<Date> getFeriados() {
		return feriados;
	}

	// FIM DA CLASSE HHT
}
