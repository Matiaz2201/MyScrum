package com.myscrum.model;

import java.util.ArrayList;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Usuario {

	static int ID;
	static String nome;
	static String email;
	static String senha;
	static String dpto;
	static int id_dpto;
	static int funcao;
	static int ativo;
	static String obs;
	static String login;
	static double salario;
	static int carga_horaria;
	static String centro_custo;
	static int id_centro_custo;
	private String dptoVinculado;
	private String ccVinculado;
	
	public void setDPTOVinculados(String dptoVinculado) {
		this.dptoVinculado = dptoVinculado;
	}
	
	public String getDPTOVinculados(){
		return dptoVinculado;
	}
	
	public void setCCVinculados(String ccVinculado) {
		this.ccVinculado = ccVinculado;
	}
	
	public String getCCVinculados(){
		return ccVinculado;
	}
	
	public void setID(int id) {
		this.ID = id;
	}
	public int getID() {
		return ID;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSenha() {
		return senha;
	}
	
	public void setDpto(String dpto) {
		this.dpto = dpto;
	}
	public String getDpto() {
		return dpto;
	}
	
	public void setIDpto(int id_dpto) {
		this.id_dpto = id_dpto;
	}
	public int getIDpto() {
		return id_dpto;
	}
//-------------- centro de custo------------------
	
	public void setCC(String centro_custo){
		this.centro_custo = centro_custo;
		
	}
	
	public String getCC(){
		return centro_custo;
	}
	
	public void setIdCC(int id_centro_custo){
		this.id_centro_custo = id_centro_custo;
	}
//-------------------------------------------------	
	public int getIdCC(){
		return id_centro_custo;
	}
	
	public void setFuncao(int funcao) {
		this.funcao = funcao;
	}
	public int getFuncao() {
		return funcao;
	}
	
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	public int getAtivo() {
		return ativo;
	}
	
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getObs() {
		return obs;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLogin() {
		return login;
	}

	public void setSalario(double salario){
		this.salario = salario;
	}
	
	public double getSalario(){
		return salario;
	}
	
	public void setCHoraria(int carga_horaria){
	this.carga_horaria = carga_horaria;
	
	}
	
	public int getCHoraria(){
		return carga_horaria;
		
	}
	
	
	
//fim	
}

