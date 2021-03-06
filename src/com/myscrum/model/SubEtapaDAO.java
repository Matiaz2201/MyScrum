package com.myscrum.model;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

public class SubEtapaDAO {

	BD bd;

	public void cadastrar(SubEtapa subetapa) {
		int result;
		bd = new BD();
		String sql = "INSERT INTO sub_etapas(sub_etapa, id_etapa)" // trocar
				+ "VALUES('" + subetapa.getSubetapa() + "', '" + buscaidEtapa(subetapa.getEtapa(), subetapa.getCc()) + "')";
		if (verificar(subetapa.getSubetapa(), subetapa.getEtapa(), subetapa.getCc()) == false) {
			if (bd.getConnection()) {// se conectar com o banco continua...
				try {

					bd.st = bd.con.prepareStatement(sql);

					result = bd.st.executeUpdate();

					if (result == 1) {
						JOptionPane.showMessageDialog(null, "SubEtapa cadastrada com sucesso", "Cadastro de SubEtapa",
								1);
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao cadastrar SubEtapa", "Cadastro de SubEtapa", 0);
					}
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, erro.toString(), "ERRO NO CADASTRO DO SUBETAPA", 0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
			}
		} else {
			JOptionPane.showMessageDialog(null, "SubEtapa j� existe", "Aviso", 0);
		}
		bd.close();

	}

	public void atualizar(SubEtapa subetapa) {
		int result;
		bd = new BD();
		String sql = "UPDATE sub_etapas \r\n"
				+ "SET sub_etapa = '" + subetapa.getSubetapaAtualiza() + "'\r\n"
				+ "WHERE id_sub_etapas = '"+ buscaidSubEtapa(subetapa.getSubetapa(), buscaidEtapa(subetapa.getEtapa(), subetapa.getCc())) + "'";
		
			
		if (verificar(subetapa.getSubetapaAtualiza(), subetapa.getEtapa(), subetapa.getCc()) == false) {
			if (bd.getConnection()) {// se conectar com o banco continua...
				try {

					bd.st = bd.con.prepareStatement(sql);

					result = bd.st.executeUpdate();

					if (result == 1) {
						JOptionPane.showMessageDialog(null, "SubEtapa atualizada com sucesso",
								"Atualiza��o etapa", 1);
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao atualizar a SubEtapa",
								"Atualiza��o etapa", 0);
					}
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, erro.toString(), "ERRO NA ATUALIZA��O DA SUBETAPA", 0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
			}
		} else {
			JOptionPane.showMessageDialog(null, "SubEtapa j� existe", "Aviso", 0);
		}
		bd.close();

	}

	public boolean verificar(String subetapa, String etapa, String cc) {
		boolean verificar = false;

		bd = new BD();
		String sql = "SELECT * FROM sub_etapas WHERE sub_etapa = '" + subetapa + "' AND id_etapa = " + buscaidEtapa(etapa, cc);
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
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO NA VERIFICA��O DA SUBETAPA", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		bd.close();
		return verificar;
	}

	public int buscaidSubEtapa(String subetapa, int id_etapa) {
		bd = new BD();
		String sql = "SELECT id_sub_etapas FROM sub_etapas WHERE sub_etapa = '"+subetapa+"' AND id_etapa = '" + id_etapa + "'";
		int ID = 0;
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.rs = bd.st.executeQuery();
				
				if(bd.rs.next()) {
					ID = bd.rs.getInt(1);
				}else {
					JOptionPane.showMessageDialog(null,"SubEtapa n�o encontrada");
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO BUSCA ID SUBETAPA",0 );
			}
		}else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}	
	return ID;
    }

	public int buscaidEtapa(String etapa, String cc) {
		String sql = "SELECT id_etapa FROM etapas WHERE etapa = '" + etapa + "' AND id_cc = (SELECT id_centro_custo FROM centro_custo WHERE centrocusto = '" + cc + "')";
		int ID = 0;
		if (bd.getConnection()) {// se conectar com o banco continua...
			try {

				bd.st = bd.con.prepareStatement(sql);
				
				bd.rs = bd.st.executeQuery();

				if (bd.rs.next()) {
					ID = bd.rs.getInt(1);
				} else {
					JOptionPane.showMessageDialog(null, "Etapa n�o encontrada");
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO BUSCA ID ETAPA", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		return ID;
	}
}
