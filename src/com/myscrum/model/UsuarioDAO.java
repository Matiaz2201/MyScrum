package com.myscrum.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.myscrum.banco.BD;
import com.myscrum.banco.Banco;

public class UsuarioDAO extends Usuario {

	// METODO CADASTRO
	public boolean cadastro() {

		boolean ok = false;
		String sql = "INSERT INTO pessoa (nome,email,senha,login,adm,id_departamento,observacao,salario,carga_horaria,id_centrocusto)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		setIDpto(buscaidDpto());// buscando ID do Dpto
		setIdCC(buscaidCC()); // busca ID centro decusto
		int verifica = verificar();
		if (verifica == 0) {
			if (Banco.conexao()) {// se conectar com o banco continua...

				try {
					Banco.st = Banco.con.prepareStatement(sql);

					Banco.st.setString(1, getNome());
					Banco.st.setString(2, getEmail());
					// Criptografando senha
					MessageDigest md = null;

					try {
						md = MessageDigest.getInstance("SHA");
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}

					String ppaswd = getSenha();
					md.update(ppaswd.getBytes());
					BigInteger hash = new BigInteger(1, md.digest());
					String retornasenha = hash.toString(16);
					// Fim criptografia
					Banco.st.setString(3, retornasenha);
					Banco.st.setString(4, getLogin());
					Banco.st.setInt(5, getFuncao());
					Banco.st.setInt(6, getIDpto());
					Banco.st.setString(7, getObs());
					Banco.st.setDouble(8, getSalario());
					Banco.st.setInt(9, getCHoraria());
					Banco.st.setInt(10, getIdCC());

					int rs = Banco.st.executeUpdate();

					if (rs == 1) {
						JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso");
						ok = true;
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao cadastrar usuario");
					}
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, erro.toString(), "ERRO INSERT", 0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Usuario ou e-mail já está em uso");
		}
		return ok;
	}

	// METODO ATUALIZA
	public boolean atualiza() {
		boolean ok = false;
		String sql;

		if (getSenha().equals("")) {// se o campo senha estiver vazio use o update sem o campo senha
			sql = "UPDATE pessoa "
					+ "SET email=?, login=?, ativo=?, adm=?, id_departamento=?, observacao=?, salario=?, carga_horaria=?, id_centrocusto =? "
					+ "WHERE id_pessoa=?";
		} else {
			sql = "UPDATE pessoa "
					+ "SET email=?, login=?, ativo=?, adm=?, id_departamento=?, observacao=?, salario=?, carga_horaria=?, id_centrocusto =?, senha=? "
					+ "WHERE id_pessoa=?";
		}
		setIDpto(buscaidDpto());// buscando ID do Dpto
		setIdCC(buscaidCC()); // busca id do CC
		int verifica = verificar();
		if (verifica <= 1) {
			if (Banco.conexao()) {// se conectar com o banco continua...

				try {
					Banco.st = Banco.con.prepareStatement(sql);

					Banco.st.setString(1, getEmail());
					Banco.st.setString(2, getLogin());
					Banco.st.setInt(3, getAtivo());
					Banco.st.setInt(4, getFuncao());
					Banco.st.setInt(5, getIDpto());
					Banco.st.setString(6, getObs());
					Banco.st.setDouble(7, getSalario());
					Banco.st.setInt(8, getCHoraria());
					Banco.st.setInt(9, getIdCC());

					if (getSenha().equals("")) {// se o campo não estiver vazio acrecentamos mais um parametro no SQL
						Banco.st.setInt(10, getID());
					} else {
						// Criptografando senha
						MessageDigest md = null;

						try {
							md = MessageDigest.getInstance("SHA");
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}

						String ppaswd = getSenha();
						md.update(ppaswd.getBytes());
						BigInteger hash = new BigInteger(1, md.digest());
						String retornasenha = hash.toString(16);
						// Fim criptografia
						Banco.st.setString(10, retornasenha);
						Banco.st.setInt(11, getID());
					}
					int rs = Banco.st.executeUpdate();

					if (rs == 1) {
						atualizarNome(getID(), getNome());
						JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso");
						ok = true;
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao atualizar usuario");
					}
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, erro.toString(), "ERRO INSERT", 0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Usuario ou e-mail já está em uso");
		}
		return ok;
	}

	// METODO BUSCAR ID CC
	public int buscaidCC() {
		String sql = "SELECT id_centro_custo FROM centro_custo WHERE centrocusto = ?";
		int ID = 0;
		if (Banco.conexao()) {// se conectar com o banco continua...
			try {

				Banco.st = Banco.con.prepareStatement(sql);

				Banco.st.setString(1, getCC());

				Banco.rs = Banco.st.executeQuery();

				if (Banco.rs.next()) {
					ID = Banco.rs.getInt(1);
				} else {
					JOptionPane.showMessageDialog(null, "Centro de custo não encontrado");
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO BUSCA ID CC", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		return ID;
	}

	// METODO BUSCAR ID DPTO
	public int buscaidDpto() {
		String sql = "SELECT id_departamento FROM departamento WHERE departamento = ?";
		int ID = 0;
		if (Banco.conexao()) {// se conectar com o banco continua...
			try {

				Banco.st = Banco.con.prepareStatement(sql);

				Banco.st.setString(1, getDpto());

				Banco.rs = Banco.st.executeQuery();

				if (Banco.rs.next()) {
					ID = Banco.rs.getInt(1);
				} else {
					JOptionPane.showMessageDialog(null, "Departamento não encontrado");
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO BUSCA ID DPTO", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		return ID;
	}

	// METODO VERIFICAR
	public int verificar() {
		String sql = "SELECT login,email FROM pessoa WHERE login=? or email=? OR nome=?";
		int verifica = 0;
		if (Banco.conexao()) {// se conectar com o banco continua...
			try {

				Banco.st = Banco.con.prepareStatement(sql);

				Banco.st.setString(1, getLogin());
				Banco.st.setString(2, getEmail());
				Banco.st.setString(3, getNome());

				Banco.rs = Banco.st.executeQuery();

				while (Banco.rs.next()) {
					verifica += 1;
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO VERIFICAÇÂO E-MAIL e USER", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		return verifica;
	}

	// CADASTRA VINCULOS COM USUARIO
	public boolean cadastrarVinculos(Usuario usuario, String tipoVinculo) {
		boolean retorno = false;
		String sql = "";
		if (Banco.conexao()) {
			if (tipoVinculo == "cc") {
				sql = "INSERT INTO vinculos (id_usuario, id_cc)" + "VALUES (" + usuario.getID()
						+ ",(SELECT id_centro_custo FROM centro_custo WHERE centrocusto = '" + usuario.getCCVinculados()
						+ "'))";
			} else if (tipoVinculo == "dpto") {
				sql = "INSERT INTO vinculos (id_usuario, id_dpto)" + "VALUES (" + usuario.getID()
						+ ",(SELECT id_departamento FROM departamento WHERE departamento = '"
						+ usuario.getDPTOVinculados() + "'))";
			}
			try {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.st.executeUpdate();
				retorno = true;
			} catch (SQLException e) {
				JOptionPane.showConfirmDialog(null, e.toString(), "Vinculos Adicionar", 0);
			}
		}
		return retorno;
	}

	// EXCLUIR VINCULOS COM USUARIO
	public boolean excluirVinculos(Usuario usuario, String tipoVinculo) {
		boolean retorno = false;
		String sql = "";
		if (Banco.conexao()) {
			if (tipoVinculo == "cc") {
				sql = "DELETE FROM vinculos WHERE id_usuario = " + usuario.getID()
						+ " AND id_cc = (SELECT id_centro_custo FROM centro_custo WHERE centrocusto = '"
						+ usuario.getCCVinculados() + "')";
			} else if (tipoVinculo == "dpto") {
				sql = "DELETE FROM vinculos WHERE id_usuario = " + usuario.getID()
						+ " AND id_dpto = (SELECT id_departamento FROM departamento WHERE departamento = '"
						+ usuario.getDPTOVinculados() + "')";
			}
			try {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.st.executeUpdate();
				retorno = true;
			} catch (SQLException e) {
				JOptionPane.showConfirmDialog(null, e.toString(), "Vinculos Excluir", 0);
			}
		}
		return retorno;
	}

	// VERIFICAR EXISTENCIA DO VINCULO
	public boolean verificarVinculo(String vinculo, String usuario, String tipoVinculo) {
		boolean retorno = true;
		String sql = "";

		if (Banco.conexao()) {
			if (tipoVinculo == "cc") {
				sql = "SELECT * FROM vinculos"
						+ " WHERE id_cc = (SELECT id_centro_custo FROM centro_custo WHERE centrocusto = '" + vinculo
						+ "') AND id_usuario = '" + usuario + "'";
			} else if (tipoVinculo == "dpto") {
				sql = "SELECT * FROM vinculos"
						+ " WHERE id_dpto = (SELECT id_departamento FROM departamento WHERE departamento = '" + vinculo
						+ "') AND id_usuario = '" + usuario + "'";
			}

			try {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.rs = Banco.st.executeQuery();

				if (Banco.rs.next()) {
					retorno = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Falha na verificação do vinculo", "Verificação do vinculo", 0);
			}

		}

		return retorno;
	}

	// ATUALIZA TODOS OS CAMPOS QUE CONTÉM O NOME DO USUARIO
	public boolean atualizarNome(int idPessoa, String novoNome) {
		
		if (Banco.conexao()) {
			
			String nomeUser = buscarNome(idPessoa);
			String sql = "UPDATE pessoa\r\n" + "SET nome = '" + novoNome + "'\r\n"
					+ "WHERE nome =  '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE tarefa\r\n" + "SET pendente_por = '" + novoNome + "'\r\n"
					+ "WHERE pendente_por = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE tarefa\r\n" + "SET responsavel = '" + novoNome + "'\r\n"
					+ "WHERE responsavel = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE tarefa\r\n" + "SET autoridade = '" + novoNome + "'\r\n"
					+ "WHERE autoridade = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE tarefa\r\n" + "SET checado = '" + novoNome + "'\r\n"
					+ "WHERE checado = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE executor\r\n" + "SET executor1 = '" + novoNome + "'\r\n"
					+ "WHERE executor1 = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE executor\r\n" + "SET executor2 = '" + novoNome + "'\r\n"
					+ "WHERE executor2 = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE executor\r\n" + "SET executor3 = '" + novoNome + "'\r\n"
					+ "WHERE executor3 = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE executor\r\n" + "SET executor4 = '" + novoNome + "'\r\n"
					+ "WHERE executor4 = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE executor\r\n" + "SET executor5 = '" + novoNome + "'\r\n"
					+ "WHERE executor5 = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE executor\r\n" + "SET executor6 = '" + novoNome + "'\r\n"
					+ "WHERE executor6 = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE executor\r\n" + "SET executor7 = '" + novoNome + "'\r\n"
					+ "WHERE executor7 = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE executor\r\n" + "SET executor8 = '" + novoNome + "'\r\n"
					+ "WHERE executor8 = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE executor\r\n" + "SET executor9 = '" + novoNome + "'\r\n"
					+ "WHERE executor9 = '" + nomeUser + "';\r\n" + "\r\n"
					+ "UPDATE executor\r\n" + "SET executor10 = '" + novoNome + "'\r\n"
					+ "WHERE executor10 = '" + nomeUser + "';";

			try {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.st.executeUpdate();

				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}

	// BUSCA O NOME DO USUARIO
	public String buscarNome(int idPessoa) {
		if (Banco.conexao()) {
			String sql = "SELECT nome FROM pessoa WHERE id_pessoa = '" + idPessoa + "'";

			try {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.rs = Banco.st.executeQuery(sql);
				Banco.rs.next();

				return Banco.rs.getString(1);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		return null;
	}
	// fim
}