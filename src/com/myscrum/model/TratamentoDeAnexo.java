package com.myscrum.model;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

import view.TarefaEditTela;

public class TratamentoDeAnexo {

	String Origem;
	boolean existencia;
	String[] extensoes = { ".jpg", ".jpeg", ".png", "pdf", ".xls", ".xlsx", ".xlsm", ".rar", ".zip", ".doc", ".docx", ".dwg" };

	String sql;

	public void salvarAnexo(JLabel label, File Arquivo, String Destino, BD bd, String coluna, String id_tarefa) {

		String extensao = Arquivo.toString().substring(Arquivo.toString().lastIndexOf("."),
				Arquivo.toString().length());

		String Nome = Arquivo.getName().toString().substring(0, Arquivo.getName().toString().lastIndexOf("."));

		switch (extensao) {

		// Imagem
		case ".jpg":

			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {
				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".jpg"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".jpg' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}

			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null, "Falha ao carregar o arquivo", "Falha", 0);
				System.out.println(e.toString());
			}

			break;

		// Imagem
		case ".jpeg":

			Origem = Arquivo.getPath();
			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {
				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".jpeg"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".jpeg' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}

			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null, "Falha ao carregar o arquivo", "Falha", 0);
				System.out.println(e.toString());
			}

			break;

		// Imagem
		case ".png":

			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {
				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".png"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".png' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}

			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null, "Falha ao carregar o arquivo", "Falha", 0);
				System.out.println(e.toString());
			}

			break;

		// Pdf
		case ".pdf":

			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {
				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".pdf"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".pdf' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}

			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null, "Falha ao carregar o arquivo", "Falha", 0);
				System.out.println(e.toString());
			}

			break;

		// Planilha
		case ".xls":

			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {

				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".xls"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".xls' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}

			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro ao carregar o arquivo  \r\n ou o arquivo já está registrado para esta tarefa",
						"Falha ao carregar o arquivo", 0);
				System.out.println(e.toString());
			}

			break;

		// Planilha
		case ".xlsx":

			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {
				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".xlsx"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".xlsx' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}

			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro ao carregar o arquivo  \r\n ou o arquivo já está registrado para esta tarefa",
						"Falha ao carregar o arquivo", 0);
				System.out.println(e.toString());
			}

			break;
			
		//macros	
		case ".xlsm":

			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {
				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".xlsm"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".xlsm' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}

			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro ao carregar o arquivo  \r\n ou o arquivo já está registrado para esta tarefa",
						"Falha ao carregar o arquivo", 0);
				System.out.println(e.toString());
			}

			break;

		// Winrar
		case ".rar":

			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {
				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".rar"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".rar' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}
			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro ao carregar o arquivo  \r\n ou o arquivo já está registrado para esta tarefa",
						"Falha ao carregar o arquivo", 0);
				System.out.println(e.toString());
			}

			break;

		case ".zip":

			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {
				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".zip"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".zip' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}
			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro ao carregar o arquivo  \r\nou o arquivo já está registrado para esta tarefa",
						"Falha ao carregar o arquivo", 0);
				System.out.println(e.toString());
			}

			break;

		case ".docx":

			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {
				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".docx"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".docx' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}
			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro ao carregar o arquivo  \r\n ou o arquivo já está registrado para esta tarefa",
						"Falha ao carregar o arquivo", 0);
				System.out.println(e.toString());
			}

			break;

		case ".doc":

			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {
				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".doc"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".doc' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}
			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro ao carregar o arquivo  \r\n ou o arquivo já está registrado para esta tarefa",
						"Falha ao carregar o arquivo", 0);
				System.out.println(e.toString());
			}

			break;

		case ".dwg":

			Origem = Arquivo.getPath();

			verificaExistencia(bd, coluna, id_tarefa);

			try {
				Files.copy(Paths.get(Origem), Paths.get(Destino + Nome + ".dwg"));

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = '" + Destino + Nome + ".dwg' \r\n"
						+ "WHERE id_tarefa=" + id_tarefa;

				System.out.println(sql);

				bd.st = bd.con.prepareStatement(sql);

				if (bd.st.executeUpdate() == 1) {

					mudarIcone(label, Arquivo);

					JOptionPane.showMessageDialog(null, "Sucesso ao carregar o arquivo", "Sucesso", 1);

				}
			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro ao carregar o arquivo  \r\n ou o arquivo já está registrado para esta tarefa",
						"Falha ao carregar o arquivo", 0);
				System.out.println(e.toString());
			}

			break;

		default:

			JOptionPane.showMessageDialog(null, "Desculpe mas o software ainda não tem suporte para essa extensão");

			break;
		}

	}

	public void excluirAnexo(JLabel label, BD bd, String coluna, String id_tarefa) {

		sql = "SELECT " + coluna + " FROM tarefa WHERE id_tarefa= " + id_tarefa;

		File file = null;

		try {
			bd.st = bd.con.prepareStatement(sql);

			System.out.println(sql);

			bd.rs = bd.st.executeQuery();

			bd.rs.next();

			if (bd.rs.getString(1) != null) {

				file = new File(bd.rs.getString(1));
				file.delete();

				sql = "UPDATE tarefa \r\n" + "SET " + coluna + " = null \r\n" + "WHERE id_tarefa=" + id_tarefa;

				bd.st = bd.con.prepareStatement(sql);
				bd.st.executeUpdate();

				label.setIcon(null);
				label.setText("+");
				label.setToolTipText("");

				JOptionPane.showMessageDialog(null, "Anexo excluido com sucesso");

			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e.toString() + "excluir");
		}

	}

	public void verificaExistencia(BD bd, String coluna, String id_tarefa) {

		sql = "SELECT " + coluna + " FROM tarefa WHERE id_tarefa= " + id_tarefa;

		File file = null;

		try {
			bd.st = bd.con.prepareStatement(sql);

			System.out.println(sql);

			bd.rs = bd.st.executeQuery();

			bd.rs.next();

			if (bd.rs.getString(1) != null) {

				file = new File(bd.rs.getString(1));
				file.delete();

			}
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e.toString() + " Verificar");
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

	public void carregarAnexo(BD bd, JLabel label, String coluna, String id_tarefa) {

		sql = "SELECT " + coluna + " FROM tarefa WHERE id_tarefa= " + id_tarefa;

		try {

			bd.st = bd.con.prepareStatement(sql);

			bd.rs = bd.st.executeQuery();

			bd.rs.next();

			if (bd.rs.getString(1) != null) {

				File Arquivo = new File(bd.rs.getString(1));

				mudarIcone(label, Arquivo);
			}

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.toString());
		}

	}

	public void abrirAnexo(BD bd, String coluna, String id_tarefa) {

		sql = "SELECT " + coluna + " FROM tarefa WHERE id_tarefa= " + id_tarefa;
		try {

			bd.st = bd.con.prepareStatement(sql);

			bd.rs = bd.st.executeQuery();

			bd.rs.next();

			if (bd.rs.getString(1) != null) {

				File Arquivo = new File(bd.rs.getString(1));

				Desktop.getDesktop().open(Arquivo);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}

	}
}