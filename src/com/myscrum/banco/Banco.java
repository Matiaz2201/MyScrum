package com.myscrum.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * @author MATIAS
 *
 *	Nesta classe fazemos todo o tratamento de conexo com o banco de dados.
 */

public abstract class Banco {
	public static Connection con = null; 
	public static PreparedStatement st = null; 
	public static ResultSet rs = null; 
	public static boolean conexao = false;
	
	
	//CONEX�O PARA O SERVIDOR AWS
	//private static final String DRIVER = "com.mysql.jdbc.Driver";
    //private static final String URL = "jdbc:mysql://scrum-masotti.cymmnyt6gbz6.sa-east-1.rds.amazonaws.com/scrum?allowMultiQueries=true";
	//private static final String LOGIN = "masottiscrum";
	//private static final String SENHA = "m4s0ttiscruM11";
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://mysql.myscrum.com.br/myscrum?allowMultiQueries=true";
	private static final String LOGIN = "myscrum";
	private static final String SENHA = "nwdev2020";
	

//	private static final String DRIVER = "com.mysql.jdbc.Driver";
//	private static final String URL = "jdbc:mysql://192.168.1.251:3306/scrum";
//	private static final String LOGIN = "root";
//	private static final String SENHA = "pdv15wwl";
	
	/**
	 * Realiza a conexão com o banco de dados, caso esta conexo obtenha sucesso, a variavel conexao recevbe true
	 * 
	 * @return retorna true caso obtenha sucesso na conexão
	 * @exception Caso o driver nao seja encotrado, e apresentando uma mensagem no console
	 * @exception Casso ocorra algum erro na conexao, e imprimido no console
	 */
	public static boolean getConnection() {

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, LOGIN, SENHA);
			System.out.println("Conectou...");
			conexao = true;
			return true;
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver não encontrado! ");
			conexao = false;
			return false;
		} catch (SQLException erro) {
			System.out.println("Erro " + erro.toString());
			conexao = false;
			return false;
		}
	}

	/**
	 * Encerra a conexão ao banco de dados, fechando o ResultSet, PreparedStatement e o Connection
	 * 
	 * @exception Caso ocorra alguma falha na finalizacao da conexao, apresenta mensagem no console
	 */
	public static void close() {
		conexao = false;
		try {
			if (rs != null) {
				rs.close(); // encerra o rs
			}
		} catch (SQLException erro) {
		}
		try {
			if (st != null) {
				st.close(); // encerra o st
			}
		} catch (SQLException erro) {
		}
		try {
			if (con != null) {
				con.close(); // encerra a conexão
				System.out.println("Desconectou...");
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Falha ao fechar conexão com o banco");
		}
	}

	/**
	 * Neste metodo fazemos a verificacao da variavel conexao, 
	 * caso seja false o seu valor, tentamos fazer a conexao com o banco de dados e setamos o valor true para o retorno, 
	 * caso seja true pulamos direto para o retorno, que ja inicia com um valor true.
	 * 
	 * @return retorna true caso a conexão esteja ou seja estabelecida ou false para falha na conexão
	 */
	public static boolean conexao() {
		boolean retorno = true;
		if (Banco.conexao == false) {
			try {
				Banco.getConnection();
				retorno = true;
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "Falha na conexão com o banco", 0);
				retorno = false;
			}
		}
		return retorno;
	}

	/**
	 * void main para teste de conexão direto da classe BD
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Banco.getConnection();
		Banco.close();
	}
}