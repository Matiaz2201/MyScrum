package com.myscrum.model;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

public class TamanhoDAO extends Tamanho{
	
	private BD bd;

	public void cadastar(){ // METODO CADASTRAR
		int result;
		bd = new BD();
		String sql = "INSERT INTO tamanho(peso, descricao)"
				+ "VALUES(?, ?)";
		if(verificar(getpeso(),gettamanho()) == 0) {
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.st.setDouble(1, getpeso());
				bd.st.setString(2, gettamanho());
				
				result = bd.st.executeUpdate();
				
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "Tamanho cadastrado com sucesso", "Cadastro de tamanho", 1);
				}else{
					JOptionPane.showMessageDialog(null,"Falha ao cadastrar tamanho", "Cadastro de tamanho", 0);
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NO CADASTRO DO TAMANHO",0 );
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		}else {
			JOptionPane.showMessageDialog(null,"Tamanho já existe", "Aviso", 0);
		}
	bd.close();
	}
	
	public void atualizar() { // METODO ATUALIZAR
		int result;
		bd = new BD();
		String sql = "UPDATE tamanho SET peso = ?, descricao = ? WHERE id_tamanho = ?";
		setId_tamanho(buscaidtamanho());
		if(verificar(getpeso_atualizar(),gettamanho_atualizar()) <= 1){
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.st.setDouble(1, getpeso_atualizar());
				bd.st.setString(2, gettamanho_atualizar());
				bd.st.setInt(3,  getId_tamanho());
				
				result = bd.st.executeUpdate();
				
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "atualizado com sucesso", "Atualização", 1);
				}else{
					JOptionPane.showMessageDialog(null,"Falha ao atualizar", "Atualização", 0);
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NA ATUALIZAÇÂO",0 );
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		}else {
			JOptionPane.showMessageDialog(null, "Tamanho ou Peso já existe", "Avios", 0);
		}
	bd.close();
	}
		
	public int verificar(Double peso, String tamanho) { // METODO VERIFICAR
		int verificar = 0;
		
		bd = new BD();
		String sql = "SELECT * "
				+ "FROM tamanho "
				+ "WHERE peso = ? OR descricao = ?";
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
			
				bd.st.setDouble(1, peso);
				bd.st.setString(2, tamanho);
				
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) {
					verificar += 1;
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NA VERIFICAÇÂO",0 );
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}	
	bd.close();	
	return verificar;
	}
	
	public int buscaidtamanho() {
		bd = new BD();
		String sql = "SELECT id_tamanho "
				+ "FROM tamanho "
				+ "WHERE peso = ? AND descricao = ?";
		int ID = 0;
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);

				bd.st.setDouble(1, getpeso());
				bd.st.setString(2, gettamanho());
				
				
				bd.rs = bd.st.executeQuery();
				
				if(bd.rs.next()) {
					ID = bd.rs.getInt(1);
				}else {
					JOptionPane.showMessageDialog(null,"tamanho não encontrado");
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO BUSCA ID TAMANHO",0 );
			}
		}else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}	
	return ID;
    }
}