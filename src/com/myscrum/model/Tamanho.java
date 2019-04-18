package com.myscrum.model;

public class Tamanho {
	
	static int id_tamanho;
	// VARIAVEL TAMANHO ESTA SE REFERINDO A DESCRIÇÃO DA TABELA TAMANHO.
	static String tamanho;
	static String tamanho_atualizar;
	static double peso;
	static double peso_atualizar;
	
	public void settamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String gettamanho() {
		return tamanho;
	}
	
	public void setpeso(double peso) {
		this.peso = peso;
	}
	public double getpeso() {
		return peso;
	}
	
	public void setpeso_atualiza(double peso_atualizar) {
		this.peso_atualizar = peso_atualizar;
	}
	public double getpeso_atualizar() {
		return peso_atualizar;
	}
	
	public void setId_tamanho(int id_tamanho) {
		this.id_tamanho = id_tamanho;
	}
	public int getId_tamanho() {
		return id_tamanho;
	}
	
	public void setTamanho_atualiza(String tamanho_atualizar) {
		this.tamanho_atualizar = tamanho_atualizar;
	}
	public String gettamanho_atualizar() {
		return tamanho_atualizar;
	}
	

}
