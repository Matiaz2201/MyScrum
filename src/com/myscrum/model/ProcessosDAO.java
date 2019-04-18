package com.myscrum.model;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

public class ProcessosDAO extends Processos {

	private BD bd;

	public void cadastar() {
		int result;
		bd = new BD();
		String sql = "INSERT INTO processos(processo)" + "VALUES(?)";
		if (verificar(getProcesso()) == false) {
			if (bd.getConnection()) {// se conectar com o banco continua...
				try {

					bd.st = bd.con.prepareStatement(sql);

					bd.st.setString(1, getProcesso());

					result = bd.st.executeUpdate();

					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Processo cadastrado com sucesso", "Cadastro processo", 1);
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao cadastrar processo", "Cadastro processo", 0);
					}
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, erro.toString(), "ERRO NO CADASTRO DO PROCESSO", 0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Processo já existe", "Aviso", 0);
		}
		bd.close();
	}

	public void atualizar() {
		int result;
		bd = new BD();
		String sql = "UPDATE processos SET processos.processo= ? WHERE id_processo = ?";
		setId_processo(buscaidProcesso());
		if (verificar(getProcesso_atualizar()) == false) {
			if (bd.getConnection()) {// se conectar com o banco continua...
				try {

					bd.st = bd.con.prepareStatement(sql);

					bd.st.setString(1, getProcesso_atualizar());
					bd.st.setInt(2, getid_Processo());

					result = bd.st.executeUpdate();

					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Processo atualizado com sucesso", "Atualização processo",
								1);
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao atualizar o processo", "Atualização processo", 0);
					}
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, erro.toString(), "ERRO NA ATUALIZAÇÂO DO PROCESSO", 0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Processo já existe", "Aviso", 0);
		}
		bd.close();
	}

	public boolean verificar(String string) {
		boolean verificar = false;

		bd = new BD();
		String sql = "SELECT * FROM processos WHERE processos.processo = ?";
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
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO NA VERIFICAÇÂO DO PROCESSO", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		bd.close();
		return verificar;
	}

	public int buscaidProcesso() {
		bd = new BD();
		String sql = "SELECT id_processo FROM processos WHERE processos.processo = ?";
		int ID = 0;
		if (bd.getConnection()) {// se conectar com o banco continua...
			try {

				bd.st = bd.con.prepareStatement(sql);

				bd.st.setString(1, getProcesso());

				bd.rs = bd.st.executeQuery();

				if (bd.rs.next()) {
					ID = bd.rs.getInt(1);
				} else {
					JOptionPane.showMessageDialog(null, "Processo não encontrado");
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO BUSCA ID PROCESSO", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		return ID;
	}
}