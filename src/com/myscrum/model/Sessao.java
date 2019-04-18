package com.myscrum.model;

public class Sessao {
	private static Sessao instance = null;
	private static String nome;
	private static String usuario;
	private static String senha;
	private static int funcao; // 0 = usuario, 1= administraor, 2= lider, 3=gestor de obra;
	private static int id;
	private static String  email;
	private static String dpto;
	private static String cc;
	
	public Sessao() {
		
	}
	
	public void setCC(String cc) {
		Sessao.cc = cc;
	}
	
	public String getCC() {
	    return cc;
		
	}

	public void setDpto(String dpto) {
		Sessao.dpto = dpto;
	}
	
	public String getDpto() {
	    return dpto;
		
	}
	
	public void setEmail(String email) {
		Sessao.email = email;
	}
	
	public String getEmail() {
	    return email;
		
	}
	
	public void setNome(String nome) {
		Sessao.nome = nome;
	}
	
	public String getNome() {
	    return nome;
	}

	public void setUsuario(String usuario) {   // armazenamento de variaveis globais
		Sessao.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setSenha(String senha) {
		Sessao.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setFuncao(int funcao) {
		Sessao.funcao = funcao;
	}
	
	public int getFuncao() {
		return funcao;
	}
	
	public void setId(int id) {
		Sessao.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static Sessao getInstance() {
		if (instance == null) {
			instance = new Sessao();
		}
		return instance;
	}

}