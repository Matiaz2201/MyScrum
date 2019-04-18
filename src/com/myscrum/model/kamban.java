package com.myscrum.model;

import java.awt.Color;
import java.sql.Date;
import java.util.ArrayList;

public class kamban{
	
	static int id[][] = new int[5][5];
	static boolean[][] anexo = new boolean [5][5];
	static String dias_iniciado[][] = new String[5][5];
	static String AtrasadoOuEmdia [][] = new String[5][5];
	static String desc[][] = new String[5][5];
	static String prioridade[][]= new String[5][5];
    static String tamanho[][]= new String[5][5];
    static String dpto[][]= new String[5][5];
    static String data_real[][]= new String[5][5];
    static String data_fim[][]= new String[5][5];
    static String prazo[][]= new String[5][5];
    static String status_pendencia[][]= new String[5][5];
    static String porcentagem[][]= new String[5][5];
    static String peso[][]= new String[5][5];
    static String pontuacao_realizada[][]= new String[5][5];
    static String centro_custo[][]= new String[5][5];
    static String responsavel[][]= new String[5][5];
    static String autoridade[][]= new String[5][5];
    static String executores[][]= new String[5][5];
    static String dias_atraso[][]= new String[5][5];
    static String pendete_por[][]= new String[5][5];
    static String status[][]= new String[5][5];
    static Color atrasada[][]= new Color[5][5];
    static ArrayList<Date> feriados = new ArrayList<>();
	static double ProducaoGeral;
	static double Producao1;
	static double PontuacaoFeitaHoje;
	static double PrazoProducao;
	static double PrazoReal;
	static double PrazoPrevisto;
	static double PrazoProducao1;
	static double PrazoReal1;
	static double PrazoPrevisto1;
	
    static int Qtd_Tarefas;
    static int Qtd_Afazer;
    static int Qtd_Fazendo;
    static int Qtd_Feito;
  
    
    
	public int getId(int coluna, int linha) {
		return id[coluna][linha];
	}
	public void setId(int coluna, int linha, int id) {
		kamban.id[coluna][linha] = id;
	}
	
	public boolean getAnexo(int coluna, int linha) {
		return anexo[coluna][linha];
	}
	public void setAnexo(int coluna, int linha, boolean anexo) {
		kamban.anexo[coluna][linha] = anexo;
	}
	
	public String getDias_iniciado(int coluna, int linha) {
		return dias_iniciado[coluna][linha];
	}
	public void setDias_iniciado(int coluna, int linha, String dias_iniciado) {
		kamban.dias_iniciado[coluna][linha] = dias_iniciado;
	}
	
	public String getAtrasadoOuEmdia(int coluna, int linha) {
		return AtrasadoOuEmdia[coluna][linha];
	}
	public void setAtrasadoOuEmdia(int coluna, int linha, String AtrasadoOuEmdia) {
		kamban.AtrasadoOuEmdia[coluna][linha] = AtrasadoOuEmdia;
	}
	
	public String getDesc(int coluna, int linha) {
		return desc[coluna][linha];
	}
	public void setDesc(int coluna, int linha, String desc) {
		kamban.desc[coluna][linha] = desc;
	}
	
	public String getPrioridade(int coluna, int linha) {
		return prioridade[coluna][linha];
	}
	public void setPrioridade(int coluna, int linha, String prioridade) {
		kamban.prioridade[coluna][linha] = prioridade;
	}
	
	public String getTamanho(int coluna, int linha) {
		return tamanho[coluna][linha];
	}
	public void setTamanho(int coluna, int linha, String tamanho) {
		kamban.tamanho[coluna][linha] = tamanho;
	}
	
	public String getDpto(int coluna, int linha) {
		return dpto[coluna][linha];
	}
	public void setDpto(int coluna, int linha, String dpto) {
		kamban.dpto[coluna][linha] = dpto;
	}
	
	public String getData_real(int coluna, int linha) {
		return data_real[coluna][linha];
	}
	public void setData_real(int coluna, int linha, String data_real) {
		kamban.data_real[coluna][linha] = data_real;
	}
	
	public String getData_fim(int coluna, int linha) {
		return data_fim[coluna][linha];
	}
	public void setData_fim(int coluna, int linha, String data_fim) {
		kamban.data_fim[coluna][linha] = data_fim;
	}
	
	public String getPrazo(int coluna, int linha) {
		return prazo[coluna][linha];
	}
	public void setPrazo(int coluna, int linha, String prazo) {
		kamban.prazo[coluna][linha]= prazo;
	}
	
	public String getStatus_pendencia(int coluna, int linha) {
		return status_pendencia[coluna][linha];
	}
	public void setStatus_pendencia(int coluna, int linha, String status_pendencia) {
		kamban.status_pendencia[coluna][linha] = status_pendencia;
	}
	
	public String getPorcentagem(int coluna, int linha) {
		return porcentagem[coluna][linha];
	}
	public static void setPorcentagem(int coluna, int linha, String porcentagem) {
		kamban.porcentagem[coluna][linha] = porcentagem;
	}
	
	public String getPeso(int coluna, int linha) {
		return peso[coluna][linha];
	}
	public void setPeso(int coluna, int linha, String peso) {
		kamban.peso[coluna][linha] = peso;
	}
	
	public  String getPontuacao_realizada(int coluna, int linha) {
		return pontuacao_realizada[coluna][linha];
	}
	public void setPontuacao_realizada(int coluna, int linha, String pontuacao_realizada) {
		kamban.pontuacao_realizada[coluna][linha] = pontuacao_realizada;
	}
	
	public String getCentro_custo(int coluna, int linha) {
		return centro_custo[coluna][linha];
	}
	public void setCentro_custo(int coluna, int linha, String centro_custo) {
		kamban.centro_custo[coluna][linha] = centro_custo;
	}
	
	public String getResponsavel(int coluna, int linha) {
		return responsavel[coluna][linha];
	}
	public void setResponsavel(int coluna, int linha, String responsavel) {
		kamban.responsavel[coluna][linha] = responsavel;
	}
	
	public String getAutoridade(int coluna, int linha) {
		return autoridade[coluna][linha];
	}
	public void setAutoridade(int coluna, int linha, String autoridade) {
		kamban.autoridade[coluna][linha] = autoridade;
	}
	
	public String getExecutores(int coluna, int linha) {
		return executores[coluna][linha];
	}
	public void setExecutores(int coluna, int linha, String executores) {
		kamban.executores[coluna][linha] = executores;
	}
	
	public String getDias_atraso(int coluna, int linha) {
		return dias_atraso[coluna][linha];
	}
	public void setDias_atraso(int coluna, int linha, String dias_atraso) {
		kamban.dias_atraso[coluna][linha] = dias_atraso;
	}
	
	public String getPendete_por(int coluna, int linha) {
		return pendete_por[coluna][linha];
	}
	public void setPendete_por(int coluna, int linha, String pendete_por) {
		kamban.pendete_por[coluna][linha] = pendete_por;
	}
    
	public String getStatus(int coluna, int linha) {
		return status[coluna][linha];
	}
	public void setStatus(int coluna, int linha, String status) {
		kamban.status[coluna][linha] = status;
	}
	
	public Color getAtrasada(int coluna, int linha) {
		return atrasada[coluna][linha];
	}
	public void setAtrasada(int coluna, int linha, Color atrasada) {
		kamban.atrasada[coluna][linha] = atrasada;
	}

	
	public static ArrayList<Date> getFeriados() {
		return feriados;
	}
	public static void setFeriados(Date feriado) {
		kamban.feriados.add(feriado);
	}
	
	public int getQtd_Tarefas() {
		return Qtd_Tarefas;
	}
	public void setQtd_Tarefas(int Qtd_Tarefas) {
		kamban.Qtd_Tarefas+=Qtd_Tarefas;
	}
	public static void zerarQtd_Tarefas() {
		kamban.Qtd_Tarefas = 0;
	}
	
	public static double getProducaoGeral() {
		return ProducaoGeral;
	}
	public static void setProducaoGeral(double producaoGeral) {
		kamban.ProducaoGeral = producaoGeral;
	}
	
	public static double getProducao1() {
		return Producao1;
	}
	public static void setProducao1(double producao1) {
		kamban.Producao1 = producao1;
	}


	public static double getPontuacaoFeitaHoje() {
		return PontuacaoFeitaHoje;
	}
	public static void setPontuacaoFeitaHoje(double pontuacaoFeitaHoje) {
		kamban.PontuacaoFeitaHoje += pontuacaoFeitaHoje;
	}
	public static void zerarPontuacaoFeitaHoje() {
		kamban.PontuacaoFeitaHoje = 0;
	}
	
	public static double getPrazoProducao() {
		return PrazoProducao;
	}
	public static void setPrazoProducao(double prazoProducao) {
		kamban.PrazoProducao += prazoProducao;
	}
	public static void zerarPrazoProducao() {
		kamban.PrazoProducao = 0;
	}
	
	public static double getPrazoReal() {
		return PrazoReal;
	}
	public static void setPrazoReal(double prazoReal) {
		kamban.PrazoReal += prazoReal;
	}
	public static void zerarPrazoReal() {
		kamban.PrazoReal = 0;
	}
	
	public static double getPrazoPrevisto() {
		return PrazoPrevisto;
	}
	public static void setPrazoPrevisto(double prazoPrevisto) {
		kamban.PrazoPrevisto += prazoPrevisto;
	}
	public static void zerarPrazoPrevisto() {
		kamban.PrazoPrevisto = 0;
	}
	
	public static double getPrazoProducao1() {
		return PrazoProducao1;
	}
	public static void setPrazoProducao1(double prazoProducao1) {
		kamban.PrazoProducao1 += prazoProducao1;
	}
	public static void zerarPrazoProducao1() {
		kamban.PrazoProducao1 = 0;
	}
	
	public static double getPrazoReal1() {
		return PrazoReal1;
	}
	public static void setPrazoReal1(double prazoReal1) {
		kamban.PrazoReal1 += prazoReal1;
	}
	public static void zerarPrazoReal1() {
		kamban.PrazoReal1 = 0;
	}
	
	public static double getPrazoPrevisto1() {
		return PrazoPrevisto1;
	}
	public static void setPrazoPrevisto1(double prazoPrevisto1) {
		kamban.PrazoPrevisto1 += prazoPrevisto1;
	}
	public static void zerarPrazoPrevisto1() {
		kamban.PrazoPrevisto1 = 0;
	}
	public static int getQtd_Afazer() {
		return Qtd_Afazer;
	}
	public static void setQtd_Afazer(int qtd_Afazer) {
		Qtd_Afazer = qtd_Afazer;
	}
	public static int getQtd_Fazendo() {
		return Qtd_Fazendo;
	}
	public static void setQtd_Fazendo(int qtd_Fazendo) {
		Qtd_Fazendo = qtd_Fazendo;
	}
	public static int getQtd_Feito() {
		return Qtd_Feito;
	}
	public static void setQtd_Feito(int qtd_Feito) {
		Qtd_Feito = qtd_Feito;
	}
}
