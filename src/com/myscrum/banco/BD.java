package com.myscrum.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class BD {
	public Connection con = null; //conecta ao banco
	public PreparedStatement st = null; //Executa SQL
	public ResultSet rs = null; //recebe resultado SQL
	public boolean conexao = false;

	/*
	//CONEX�O PARA O SERVIDOR AWS
	private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://scrum-masotti.cymmnyt6gbz6.sa-east-1.rds.amazonaws.com/scrum";
	private final String LOGIN = "masottiscrum";
	private final String SENHA = "m4s0ttiscruM11";
	*/
	

	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://192.168.1.251:3306/scrum";
	private final String LOGIN = "root";
	private final String SENHA = "pdv15wwl";
	
	 /*
	 * Realiza a conex�o ao banco de dados
	 * @return: true em caso de sucesso, ou false caso contrario
	 */
	
	public boolean getConnection(){

		try{
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,LOGIN,SENHA);
			System.out.println("Conectou...");
			conexao = true;
			return true;
		}
		catch(ClassNotFoundException erro){
			System.out.println("Driver não encontrado! ");
			conexao = false;
			return false;
		}
		catch(SQLException erro){
			System.out.println("Erro " + erro.toString());
			conexao = false;
			return false;
		}		
	}

	/**
	 * Encerra a conex�o ao banco de dados
	 */
	public void close(){  
		conexao = false;
		try{
			if(rs!=null){
				rs.close();  //encerra o rs
			}
		}
		catch(SQLException erro){}
		try{
			if(st!=null){
				st.close(); //encerra o st
			}
		}
		catch(SQLException erro){}
		try{
			if(con!=null){
				con.close(); //encerra a conex�o
				System.out.println("Desconectou...");
			}
		}
		catch(SQLException erro){
			JOptionPane.showMessageDialog(null, "Falha ao fechar conexão com o banco");
		}
	}
	
	public static void main(String[] args) {
		BD bd = new BD();
		bd.getConnection();
		bd.close();
	}
}
