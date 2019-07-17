package com.myscrum.model;

import java.awt.Dimension;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import com.sun.mail.iap.ByteArray;

import view.TarefaEditTela;

public class TratamentoDeAnexo2 {

	public boolean salvarAnexo(File arquivo, int nAnexo, String idTarefa) {
		FileInputStream io = null;
		try {
			io = new FileInputStream(arquivo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "INSERT INTO arquivos (nome, arquivo) VALUES (?,?)";
		if (Banco.conexao()) {

			try {
				Banco.st = Banco.con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				Banco.st.setString(1, arquivo.getName().toString());
				Banco.st.setBinaryStream(2, (InputStream) io, (int) arquivo.length());

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

	public boolean excluirAnexo(int nAnexo, String idTarefa) {
		String sql = "SELECT anexo" + nAnexo + " FROM tarefa WHERE id_tarefa = " + idTarefa;
		int idArquivo = 0;
		if (Banco.conexao()) {
			try {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.rs = Banco.st.executeQuery();

				if (Banco.rs.next()) {
					idArquivo = Banco.rs.getInt(1);

					sql = "UPDATE tarefa SET anexo" + nAnexo + " = null WHERE id_tarefa = " + idTarefa;

					Banco.st = Banco.con.prepareStatement(sql);
					if (Banco.st.executeUpdate(sql) == 1) {
						sql = "DELETE FROM arquivos WHERE idarquivos = " + idArquivo;

						Banco.st = Banco.con.prepareStatement(sql);
						if (Banco.st.executeUpdate(sql) == 1) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}

				} else {
					return false;
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, "Falha na exlcusão do arquivo \r\n " + erro.toString());
				return false;
			}
		} else {
			return false;
		}

	}

	public void carregarAnexo(String idTarefa, TarefaEditTela tela) {
		String sql = "SELECT \r\n " + "(SELECT arquivo FROM arquivos WHERE idarquivos = anexo1) AS Anexo1,\r\n"
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
			Banco.rs = Banco.st.executeQuery();

			Banco.rs.next();

			if (Banco.rs.getBlob(1) != null) {
				tela.anexo1File = criarArquivo(Banco.rs.getBinaryStream("Anexo1"), Banco.rs.getString("NomeAnexo1"));
				mudarIcone(tela.anexo1Label, tela.anexo1File);
			}

			if (Banco.rs.getBlob(3) != null) {
				tela.anexo2File = criarArquivo(Banco.rs.getBinaryStream("Anexo2"), Banco.rs.getString("NomeAnexo2"));
				mudarIcone(tela.anexo2Label, tela.anexo2File);
			}

			if (Banco.rs.getBlob(5) != null) {
				tela.anexo3File = criarArquivo(Banco.rs.getBinaryStream("Anexo3"), Banco.rs.getString("NomeAnexo3"));
				mudarIcone(tela.anexo3Label, tela.anexo3File);
			}

			if (Banco.rs.getBlob(7) != null) {
				tela.anexo4File = criarArquivo(Banco.rs.getBinaryStream("Anexo4"), Banco.rs.getString("NomeAnexo4"));
				mudarIcone(tela.anexo4Label, tela.anexo4File);
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

	public File criarArquivo(InputStream teste, String nomeArquivo) {
		InputStream input = teste;
		File f = null;

		if (input != null) {

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] bytes = null;

			byte[] rb = new byte[1024];
			int ch = 0;

			try {
				while ((ch = input.read(rb)) != -1) {
					output.write(rb, 0, ch);

					bytes = output.toByteArray();
					input.close();
					output.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			f = new File(nomeArquivo);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(f);
				fos.write(bytes);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (fos != null)
					try {
						fos.flush();
						fos.close();
					} catch (IOException ex) {
					}
			}
		}
		return f;
	}
}
