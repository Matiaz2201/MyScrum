package com.myscrum.model;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

public class EtapaDAO {

	BD bd;

	public void cadastar(Etapa etapa) {

		int result;
		bd = new BD();
		String sql = "INSERT INTO etapas(etapa, id_cc)" // trocar
				+ "VALUES('" + etapa.getEtapa() + "', (SELECT id_centro_custo FROM centro_custo WHERE centrocusto = '" + etapa.getCc() + "'))";
		if (verificar(etapa.getEtapa(), etapa.getCc()) == false) {
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
			JOptionPane.showMessageDialog(null, "Etapa j� existe", "Aviso", 0);
		}
		bd.close();

	}

	public boolean verificar(String etapa, String cc) {
		boolean verificar = false;

		bd = new BD();
		String sql = "SELECT * FROM etapas WHERE etapa = '" + etapa + "' AND id_cc = (SELECT id_centro_custo FROM centro_custo WHERE centrocusto = '" + cc + "')";
		if (bd.getConnection()) {// se conectar com o banco continua...
			try {

				bd.st = bd.con.prepareStatement(sql);

				bd.rs = bd.st.executeQuery();

				if (bd.rs.next()) {
					verificar = true;
				} else {
					verificar = false;
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO NA VERIFICA��O DA ETAPA", 0);
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
				+ "WHERE id_etapa = '"+ buscaidEtapa(etapa.getEtapa(), etapa.getCc()) + "'";
		
		System.out.println(sql);
		
		if (verificar(etapa.getEtapaAtualiza(), etapa.getCc()) == false) {
			if (bd.getConnection()) {// se conectar com o banco continua...
				try {

					bd.st = bd.con.prepareStatement(sql);

					result = bd.st.executeUpdate();

					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Etapa atualizada com sucesso",
								"Atualiza��o etapa", 1);
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao atualizar a etapa",
								"Atualiza��o etapa", 0);
					}
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, erro.toString(), "ERRO NA ATUALIZA��O DA ETAPA", 0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Etapa j� existe", "Aviso", 0);
		}
		bd.close();

	}

	public int buscaidEtapa(String etapa, String cc) {
		bd = new BD();
		String sql = "SELECT id_etapa FROM etapas WHERE etapa = '"+etapa+"' AND id_cc = (SELECT id_centro_custo FROM centro_custo WHERE centrocusto = '" + cc +"')";
		int ID = 0;
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.rs = bd.st.executeQuery();
				
				if(bd.rs.next()) {
					ID = bd.rs.getInt(1);
				}else {
					JOptionPane.showMessageDialog(null,"etapa n�o encontrada");
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
