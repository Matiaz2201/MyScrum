package com.myscrum.controller;

public class Controlev {

	static boolean principal = false;
	static boolean tarefa = false;
	static boolean usuario = false;
	static boolean menu = false;
	static boolean listusuario = false;
	static boolean atalho = false;
	static boolean kamban = false;
	static boolean contato = false;
	static boolean hht_tela = false;
	static boolean loading = false;
	
	
	public static void setPrincipal(boolean var) {
		principal = var;
	}
	public static boolean getPrincipal() {
		return principal;
	}
	public static void setLoading(boolean var) {
		loading = var;
	}
	public static boolean getLoading() {
		return loading;
	}
	public static void setTarefa(boolean var) {
		tarefa = var;
	}
	public static boolean getTarefa() {
		return tarefa;
	}
	public static void setUsuario(boolean var) {
		usuario = var;
	}
	public static boolean getUsuario() {
		return usuario;
	}
	public static void setMenu(boolean var) {
		menu = var;
	}
	public static boolean getMenu() {
		return menu;
	}
	public static  void setListUsuario(boolean var) {
		listusuario = var;
	}
	public static  boolean getListusuario() {
		return listusuario;
	}

	public static void setAtalho(boolean var){
		atalho = var;
	}
	public static  boolean getAtalho(){
		return atalho;	
	}
	
	public static void setKamban(boolean var){
		kamban = var;
	}
	public static  boolean getKamban(){
		return kamban;	
	}
	
	public static void setContato(boolean var) {
		contato = var;
	}
	public static boolean getContato() {
		return contato;
	}
	
	public static void sethhtTela(boolean var) {
		hht_tela = var;
	}
	public static boolean gethhtTela() {
		return hht_tela;
	}

}// fim
