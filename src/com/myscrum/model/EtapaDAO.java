package com.myscrum.model;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

public class EtapaDAO {

	BD bd;

	public void cadastar(Etapa etapa) {

		int result;
		bd = new BD();
		String sql = "INSERT INTO etapas(etapa)" // trocar
				+ "VALUES('" + etapa.getEtapa() + "')";
		if (verificar(etapa.getEtapa()) == false) {
			if (bd.getConnection()) {// se conectar com o banco continua...
				try {

					bd.st = bd.con.prepareStatement(sql);

					result = bd.st.executeUpdate();

					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Etapa cadastrada com sucesso", "Cadastro de processos", 1);
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao cadastrar etapa", "Cadastro de processo", 0);
					}
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, erro.toString(), "ERRO NO CADASTRO DO ETAPA", 0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Etapa já existe", "Aviso", 0);
		}
		bd.close();

	}

	public boolean verificar(String string) {
		boolean verificar = false;

		bd = new BD();
		String sql = "SELECT * FROM etapas WHERE etapa = ?";
		if (bd.getConnection()) {// se conectar com o banco continua...
			try {

				bd.st = bd.con.prepareStatement(sql);

				bd.st.setString(1, string);

				bd.rs = bd.st.executeQuery();

				if (bd.rs.next()) {
					verificar = true;
				} else {
					verificar = false;
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO NA VERIFICAÇÂO DA ETAPA", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		bd.close();
		return verificar;
	}

	public void atualizar(Etapa etapa) {
		int result;
		bd = new BD();
		String sql = "UPDATE etapas \r\n"
				+ "SET etapa = '" + etapa.getEtapaAtualiza() + "'\r\n"
				+ "WHERE id_etapa = '"+ buscaidEtapa(etapa.getEtapa()) + "'";
		
		System.out.println(sql);
		
		if (verificar(etapa.getEtapaAtualiza()) == false) {
			if (bd.getConnection()) {// se conectar com o banco continua...
				try {

					bd.st = bd.con.prepareStatement(sql);

					result = bd.st.executeUpdate();

					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Etapa atualizada com sucesso",
								"Atualização etapa", 1);
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao atualizar a etapa",
								"Atualização etapa", 0);
					}
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, erro.toString(), "ERRO NA ATUALIZAÇÂO DA ETAPA", 0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Etapa já existe", "Aviso", 0);
		}
		bd.close();

	}

	public int buscaidEtapa(String etapa) {
		bd = new BD();
		String sql = "SELECT id_etapa FROM etapas WHERE etapa = '"+etapa+"'";
		int ID = 0;
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.rs = bd.st.executeQuery();
				
				if(bd.rs.next()) {
					ID = bd.rs.getInt(1);
				}else {
					JOptionPane.showMessageDialog(null,"etapa não encontrada");
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO BUSCA ID ETAPA",0 );
			}
		}else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}	
	return ID;
    }
}
