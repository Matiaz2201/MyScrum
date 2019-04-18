package com.myscrum.model;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

public class DptoDAO extends Dpto{
	
	private BD bd;

	public void cadastar(){
		int result;
		bd = new BD();
		String sql = "INSERT INTO departamento(departamento)"
				+ "VALUES(?)";
		if(verificar(getDpto()) == false) {
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.st.setString(1, getDpto());
				
				result = bd.st.executeUpdate();
				
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "Departamento cadastrado com sucesso", "Cadastro departamento", 1);
				}else{
					JOptionPane.showMessageDialog(null,"Falha ao cadastrar departamento", "Cadastro departamento", 0);
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NO CADASTRO DO DEPARTAMENTO",0 );
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		}else {
			JOptionPane.showMessageDialog(null,"Departamento já existe", "Aviso", 0);
		}
	bd.close();	
	}
	
	public void atualizar() {
		int result;
		bd = new BD();
		String sql = "UPDATE departamento SET departamento.departamento= ? WHERE id_departamento = ?";
		setId_dpto(buscaidDpto());
		if(verificar(getDpto_atualizar()) == false) {
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.st.setString(1, getDpto_atualizar());
				bd.st.setInt(2, getId_dpto());
				
				result = bd.st.executeUpdate();
				
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "Departamento atualizado com sucesso", "Atualização departamento", 1);
				}else{
					JOptionPane.showMessageDialog(null,"Falha ao atualizar o departamento", "Atualização departamento", 0);
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NA ATUALIZAÇÂO DO DEPARTAMENTO",0 );
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
	}else {
		JOptionPane.showMessageDialog(null, "Departamento já existe","Aviso", 0);	
	}
	bd.close();	
	}
		
	public boolean verificar(String string) {
		boolean verificar = false;
		
		bd = new BD();
		String sql = "SELECT * "
				+ "FROM departamento "
				+ "WHERE departamento.departamento = ?";
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
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NA VERIFICAÇÂO DO DEPARTAMENTO",0 );
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}	
	bd.close();	
	return verificar;
	}
	
	public int buscaidDpto() {
		bd = new BD();
		String sql = "SELECT id_departamento FROM departamento WHERE departamento.departamento = ?";
		int ID = 0;
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);

				bd.st.setString(1, getDpto());
				
				bd.rs = bd.st.executeQuery();
				
				if(bd.rs.next()) {
					ID = bd.rs.getInt(1);
				}else {
					JOptionPane.showMessageDialog(null,"Departamento não encontrado");
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO BUSCA ID DPTO",0 );
			}
		}else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}	
	return ID;
    }
}