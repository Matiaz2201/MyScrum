package com.myscrum.model;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.myscrum.banco.Banco;
import com.mysql.jdbc.PreparedStatement;

import view.TarefaEditTela;

public class TratamentoDeAnexo2 {

	public boolean salvarAnexo(File arquivo, int nAnexo, String idTarefa) {
		byte[] arquivoEmBytes = new byte[(int) arquivo.length()];
		String sql = "INSERT INTO arquivos (nome, arquivo) VALUES (?,?)";
		if (Banco.conexao()) {

			try {
				Banco.st = Banco.con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				Banco.st.setString(1, arquivo.getName().toString());
				Banco.st.setBytes(2, arquivoEmBytes);

				if (Banco.st.executeUpdate() == 1) {
					Banco.rs = Banco.st.getGeneratedKeys();
					Banco.rs.next();
					int idArquivos = Banco.rs.getInt(1);

					sql = "UPDATE tarefa \r\n" + "SET anexo" + nAnexo + " = " + idArquivos + "\r\n"
							+ "WHERE id_tarefa = " + idTarefa;

					Banco.st = Banco.con.prepareStatement(sql);
					if (Banco.st.executeUpdate() == 1) {
						return true;
					} else {
						return false;
					}

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

	public void carregarAnexo(String idTarefa, TarefaEditTela tela) {
		String sql = "SELECT \r\n "
				+ "(SELECT arquivo FROM arquivos WHERE idarquivos = anexo1) AS Anexo1,\r\n"
				+ "(SELECT nome FROM arquivos WHERE idarquivos = anexo1) AS NomeAnexo1,\r\n"
				+ "(SELECT arquivo FROM arquivos WHERE idarquivos = anexo2) AS Anexo2,\r\n"
				+ "(SELECT nome FROM arquivos WHERE idarquivos = anexo2) AS NomeAnexo2,\r\n"
				+ "(SELECT arquivo FROM arquivos WHERE idarquivos = anexo3) AS Anexo3,\r\n"
				+ "(SELECT nome FROM arquivos WHERE idarquivos = anexo3) AS NomeAnexo3,\r\n"
				+ "(SELECT arquivo FROM arquivos WHERE idarquivos = anexo4) AS Anexo4,\r\n"
				+ "(SELECT nome FROM arquivos WHERE idarquivos = anexo4) AS NomeAnexo4\r\n"
				+ "FROM tarefa WHERE id_tarefa = " + idTarefa;

		try {
			Banco.st = Banco.con.prepareStatement(sql);
			JOptionPane.showMessageDialog(null, sql);
			Banco.rs = Banco.st.executeQuery();
			
			Banco.rs.next();

			if (Banco.rs.getBlob(1) != null) {
				tela.anexo1File = criarArquivo(Banco.rs.getBlob("Anexo1"), Banco.rs.getString("NomeAnexo1"));
				mudarIcone(tela.anexo1Label, tela.anexo1File);
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());
		}

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

	public File criarArquivo(Blob arquivoBlob, String nomeArquivo) {
		File arquivo = null;

		InputStream bin = null;
		FileOutputStream bout = null;
		byte[] bbuf = new byte[1024];
		int bytesRead = 0;

		try {

			bin = arquivoBlob.getBinaryStream();
			bout = new FileOutputStream(nomeArquivo);

			while ((bytesRead = bin.read(bbuf)) != -1) {
				bout.write(bbuf, 0, bytesRead);
			}

			arquivo = new File(nomeArquivo);
		} catch (SQLException | IOException erro) {

		}
		return arquivo;
	}

}
