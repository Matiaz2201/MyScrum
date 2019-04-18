package com.myscrum.model;

import java.sql.SQLException;
import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

public class FeriadoDAO extends Feriado{
	
	BD bd;

	
	public void cadastrar() {
		int result;
		bd = new BD();
		String sql = "INSERT INTO feriados(data_feriado,descri_feriado)"
				+ "VALUES(?,?)";
		if(verificar() == 0) {
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				bd.st = bd.con.prepareStatement(sql);
		
				bd.st.setString(1, getData());
				bd.st.setString(2, getDesc());
				
				result = bd.st.executeUpdate();
				
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "Feriado cadastrado com sucesso", "Cadastro feriado", 1);
				}else{
					JOptionPane.showMessageDialog(null,"Falha ao cadastrar feriado", "Cadastro feriado", 0);
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NO CADASTRO DO FERIADO",0 );
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		}else {
			JOptionPane.showMessageDialog(null,"Feriado já existe", "Aviso", 0);
		}
	bd.close();	
	}
	
	public void atualizar() {
		if(verificar() == 0) {
		int result;
		bd = new BD();
		String sql = "UPDATE feriados SET data_feriado = ?, descri_feriado=? WHERE id_feriado = ?";
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
			
				bd.st.setString(1, getData());
				bd.st.setString(2, getDesc());
				bd.st.setInt(3, getId_feriado());
				
				result = bd.st.executeUpdate();
				
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "Feriado atualizado com sucesso", "Atualização de feriado", 1);
				}else{
					JOptionPane.showMessageDialog(null,"Falha ao atualizar o feriado", "Atualização de feriado", 0);
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NA ATUALIZAÇÂO DO FERIADO",0 );
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
	bd.close();	
		}else {
			JOptionPane.showMessageDialog(null,"Feriado já existe", "Atualização de feriado", 0);
		}
	}
	
	public int verificar() {
		int verificar = 0;
		
		bd = new BD();
		String sql = "SELECT * "
				+ "FROM feriados "
				+ "WHERE data_feriado = ? and id_feriado != ?";
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);

				bd.st.setString(1, getData());
				bd.st.setInt(2, getId_feriado());
				
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) {
					verificar += 1;
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(),"ERRO NA VERIFICAÇÂO DO FERIADO",0 );
				verificar = 0;
			}
		}else{
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}	
	bd.close();	
	return verificar;
	}
}
