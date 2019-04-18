package com.myscrum.model;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

public class CcCustoDAO extends CcCusto {

	BD bd;
	
	public void cadastar(){
		
		int result;
		bd = new BD();
		String sql = "INSERT INTO centro_custo(centrocusto)" // trocar
				+ "VALUES(?)";
		if(verificar(getccCusto()) == false) {
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.st.setString(1, getccCusto());
				
				result = bd.st.executeUpdate();
				
				if(result == 1) {						
					JOptionPane.showMessageDialog(null, "Centro de custo cadastrado com sucesso", "Cadastro Centro de custo", 1);
				}else{
					JOptionPane.showMessageDialog(null,"Falha ao cadastrar centro de custo", "Cadastro Centro de custo", 0);
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NO CADASTRO DO CENTRO DE CUSTO",0 );
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		}else {
			JOptionPane.showMessageDialog(null,"Centro de custo já existe", "Aviso", 0);
		}
	bd.close();	
		
	}
	
	
	public boolean verificar(String string) {
		boolean verificar = false;
		
		bd = new BD();
		String sql = "SELECT * "
				+ "FROM centro_custo "
				+ "WHERE centrocusto = ?";
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
			
				bd.st.setString(1, string);
				
				bd.rs = bd.st.executeQuery();
				
				if(bd.rs.next()) {
					verificar = true;
				}else{
					verificar = false;
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NA VERIFICAÇÂO DO CENTRO DE CUSTO",0 );
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}	
	bd.close();	
	return verificar;
	}

	
	public void atualizar() {
		int result;
		bd = new BD();
		String sql = "UPDATE centro_custo SET centrocusto = ? WHERE id_centro_custo = ?";
		setid_ccCusto(buscaidccCusto());
		if(verificar(getccCusto_atualiza()) == false) {
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.st.setString(1,  getccCusto_atualiza());
				bd.st.setInt(2, getid_ccCusto());
				
				result = bd.st.executeUpdate();
				
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "Centro de custo atualizado com sucesso", "Atualização centro de custo", 1);
				}else{
					JOptionPane.showMessageDialog(null,"Falha ao atualizar o centro de custo", "Atualização centro de custo", 0);
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NA ATUALIZAÇÂO DO CENTRO DE CUSTO", 0 );
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
	}else {
		JOptionPane.showMessageDialog(null, "Centro de custo já existe","Aviso",0);
	}
	bd.close();	
	}
	
	public int buscaidccCusto() {
		bd = new BD();
		String sql = "SELECT id_centro_custo FROM centro_custo WHERE centrocusto = ?";
		int ID = 0;
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, getccCusto());
				
				bd.rs = bd.st.executeQuery();
				
				if(bd.rs.next()) {
					ID = bd.rs.getInt(1);
				}else {
					JOptionPane.showMessageDialog(null,"centro de custo não encontrado");
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO BUSCA ID CC",0 );
			}
		}else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}	
	return ID;
    }


}
