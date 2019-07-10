package com.myscrum.model;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.myscrum.banco.Banco;
import com.mysql.jdbc.PreparedStatement;

import view.TarefaEditTela;

public class TratamentoDeAnexo2 {

	public boolean salvarAnexo(File arquivo) {
		byte[] arquivoEmBytes = new byte[(int) arquivo.length()];
		String sql = "INSERT INTO arquivos (nome, arquivo) VALUES (?,?)";
		if (Banco.conexao()) {

			try {
				Banco.st = Banco.con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				Banco.st.setString(1, arquivo.getName().toString());
				Banco.st.setBytes(2, arquivoEmBytes);

				if (Banco.st.executeUpdate() == 1) {
					Banco.rs = Banco.st.getGeneratedKeys();
					return true;
				} else {
					return false;
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString());
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean excluirAnexo() {
		return false;

	}

	public boolean abrirAnexo() {
		return false;

	}
	
	public void mudarIcone(JLabel label, File Arquivo) {

		String extensao = Arquivo.toString().substring(Arquivo.toString().lastIndexOf("."),
				Arquivo.toString().length());

		switch (extensao) {
		case ".jpg":

			label.setText("");
			label.setIcon(Redimensionar.redimensionar(Arquivo.getPath(),
					new Dimension(label.getWidth(), label.getHeight()), Image.SCALE_DEFAULT));
			label.setToolTipText(Arquivo.getName());

			break;

		case ".jpeg":

			label.setText("");
			label.setIcon(Redimensionar.redimensionar(Arquivo.getPath(),
					new Dimension(label.getWidth(), label.getHeight()), Image.SCALE_DEFAULT));
			label.setToolTipText(Arquivo.getName());

			break;

		case ".png":

			label.setText("");
			label.setIcon(Redimensionar.redimensionar(Arquivo.getPath(),
					new Dimension(label.getWidth(), label.getHeight()), Image.SCALE_DEFAULT));
			label.setToolTipText(Arquivo.getName());

			break;

		case ".pdf":

			label.setText("");
			label.setIcon(new ImageIcon(TarefaEditTela.class.getResource("/com/myscrum/assets/pdf.png")));
			label.setToolTipText(Arquivo.getName());

			break;

		case ".xls":

			label.setText("");
			label.setIcon(new ImageIcon(TarefaEditTela.class.getResource("/com/myscrum/assets/excel.png")));
			label.setToolTipText(Arquivo.getName());

			break;

		case ".xlsx":

			label.setText("");
			label.setIcon(new ImageIcon(TarefaEditTela.class.getResource("/com/myscrum/assets/excel.png")));
			label.setToolTipText(Arquivo.getName());

			break;
			
		case ".xlsm":

			label.setText("");
			label.setIcon(new ImageIcon(TarefaEditTela.class.getResource("/com/myscrum/assets/excel.png")));
			label.setToolTipText(Arquivo.getName());

			break;

		case ".rar":

			label.setText("");
			label.setIcon(new ImageIcon(TarefaEditTela.class.getResource("/com/myscrum/assets/rar.png")));
			label.setToolTipText(Arquivo.getName());

			break;

		case ".zip":

			label.setText("");
			label.setIcon(new ImageIcon(TarefaEditTela.class.getResource("/com/myscrum/assets/zip.png")));
			label.setToolTipText(Arquivo.getName());

			break;

		case ".doc":

			label.setText("");
			label.setIcon(new ImageIcon(TarefaEditTela.class.getResource("/com/myscrum/assets/word.png")));
			label.setToolTipText(Arquivo.getName());

			break;

		case ".docx":

			label.setText("");
			label.setIcon(new ImageIcon(TarefaEditTela.class.getResource("/com/myscrum/assets/word.png")));
			label.setToolTipText(Arquivo.getName());

			break;

		case ".dwg":

			label.setText("");
			label.setIcon(new ImageIcon(TarefaEditTela.class.getResource("/com/myscrum/assets/dwg.png")));
			label.setToolTipText(Arquivo.getName());

			break;

		default:
			break;
		}

	}
}
