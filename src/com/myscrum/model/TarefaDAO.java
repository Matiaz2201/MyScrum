package com.myscrum.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.myscrum.banco.BD;
import com.myscrum.banco.Banco;
import com.myscrum.model.Sessao;

public class TarefaDAO extends Tarefa {

	Sessao sessao = new Sessao();
	public BD bd;
	public int ID_TAREFA;

	public void cadastrar() {

		bd = new BD();
		String sql = "INSERT INTO tarefa(id_centro_custo,id_tamanho,id_departamento,processo_relacionado,descri,prioridade,stat,porcentagem,prazo,predecessor_1,predecessor_2,predecessor_3,checado,data_ini,data_real,data_fim,pendente_por,status_pendencia,historico,responsavel,autoridade,dpto_correto,id_pessoa,id_update,etapa,subetapa)"
				+ "SELECT"
				+ "(SELECT centro_custo.id_centro_custo FROM centro_custo WHERE centro_custo.centrocusto= ?),"
				+ "(SELECT tamanho.id_tamanho FROM tamanho WHERE tamanho.descricao= ?),"
				+ "(SELECT departamento.id_departamento FROM departamento WHERE departamento.departamento = ? ),"
				+ "(SELECT processos.id_processo FROM processos WHERE processos.processo = ? )," 
				+ " ? AS descri,"
				+ " ? AS prioridade," 
				+ " ? AS stat," 
				+ " ? AS porcentagem," 
				+ " ? AS prazo," 
				+ " ? AS predecessor1, "
				+ " ? AS predecessor2, " 
				+ " ? AS predecessor3, " 
				+ " ? AS checado, " 
				+ " ? AS data_ini,"
				+ " ? AS data_real," 
				+ " ? AS data_fim," 
				+ " ? AS pendente_por," 
				+ " ? AS status_pendencia,"
				+ " ? AS historico," 
				+ " ? AS responsavel," 
				+ " ? AS autoridade," 
				+ " ? AS dpto_correto,"
				+ " ? AS id_pessoa," 
				+ " ? AS id_update,"
				+ " ? AS etapa,"
				+ " ? AS subetapa";

		if (bd.getConnection()) {// se conectar com o banco continua...

			try {
				bd.st = bd.con.prepareStatement(sql);

				bd.st.setString(1, getCentroCusto());
				bd.st.setString(2, getTamanho());
				bd.st.setString(3, getDepartamento());
				bd.st.setString(4, getProcesso());/// Processo relacionado
				bd.st.setString(5, getDescricao());
				bd.st.setInt(6, getPrioridade());
				bd.st.setString(7, getStatus());
				bd.st.setInt(8, getPorcentagem());
				bd.st.setInt(9, getPrazo());
				if (getPredecessor1() == 0) {
					bd.st.setNull(10, 1);
				} else {
					bd.st.setInt(10, getPredecessor1());
				}
				if (getPredecessor2() == 0) {
					bd.st.setNull(11, 0);
				} else {
					bd.st.setInt(11, getPredecessor2());
				}

				if (getPredecessor3() == 0) {
					bd.st.setNull(12, 0);
				} else {
					bd.st.setInt(12, getPredecessor3());
				}
				bd.st.setString(13, getChecado());
				bd.st.setString(14, DataParaoBanco(getDataInicio()));
				bd.st.setString(15, DataParaoBanco(getDataReal()));
				bd.st.setString(16, DataParaoBanco(getDataFim()));
				bd.st.setString(17, getPendentePor());
				bd.st.setString(18, getStatusPendencia());
				bd.st.setString(19, getHistorico());
				bd.st.setString(20, getResponsavel());
				bd.st.setString(21, getAutoridade());
				bd.st.setString(22, "");// Departamento correto
				bd.st.setInt(23, sessao.getId());
				bd.st.setInt(24, sessao.getId());
				bd.st.setString(25, BuscaIDEtapaESubEtapa(getCentroCusto(), getEtapa(), getSubEtapa(), "Etapa"));
				bd.st.setString(26, BuscaIDEtapaESubEtapa(getCentroCusto(), getEtapa(), getSubEtapa(), "SubEtapa"));

				int ok = bd.st.executeUpdate();

				// Pegando o id maior (ultimo id inserido)
				sql = "SELECT MAX(id_tarefa) FROM tarefa";
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery(sql);

				if (bd.rs.next()) {
					ID_TAREFA = bd.rs.getInt(1);
				}

				if (ok == 1) {
					cadastrarExe();
					JOptionPane.showMessageDialog(null, "Tarefa cadastrada com sucesso");
				} else {
					JOptionPane.showMessageDialog(null, "Falha ao cadastrar tarefa");
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO INSERT", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		bd.close();
	}

	public boolean cadastrarExe() {
		boolean executor = false;

		bd = new BD();
		String sql = "INSERT INTO executor (executor1,porcento1,executor2,porcento2,executor3,porcento3,executor4,porcento4,executor5,porcento5,executor6,porcento6,executor7,porcento7,executor8,porcento8,executor9,porcento9,executor10,porcento10,id_tarefa)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		if (bd.getConnection()) {
			try {
				bd.st = bd.con.prepareStatement(sql);

				bd.st.setString(1, getExecutor1());
				bd.st.setInt(2, getPorcento1());
				bd.st.setString(3, getExecutor2());
				bd.st.setInt(4, getPorcento2());
				bd.st.setString(5, getExecutor3());
				bd.st.setInt(6, getPorcento3());
				bd.st.setString(7, getExecutor4());
				bd.st.setInt(8, getPorcento4());
				bd.st.setString(9, getExecutor5());
				bd.st.setInt(10, getPorcento5());
				bd.st.setString(11, getExecutor6());
				bd.st.setInt(12, getPorcento6());
				bd.st.setString(13, getExecutor7());
				bd.st.setInt(14, getPorcento7());
				bd.st.setString(15, getExecutor8());
				bd.st.setInt(16, getPorcento8());
				bd.st.setString(17, getExecutor9());
				bd.st.setInt(18, getPorcento9());
				bd.st.setString(19, getExecutor10());
				bd.st.setInt(20, getPorcento10());
				bd.st.setInt(21, ID_TAREFA);

				bd.st.executeUpdate();

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO INSERT EXECUTORES", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		bd.close();
		return executor;
	}

	public void atualizar() {
		bd = new BD();
		String sql = "UPDATE tarefa \n\r" + "INNER JOIN executor ON tarefa.id_tarefa = executor.id_tarefa \n\r"
				+ "SET \n\r "
				+ "tarefa.id_centro_custo = (SELECT centro_custo.id_centro_custo FROM centro_custo WHERE centro_custo.centrocusto = ?), \n\r"
				+ "tarefa.id_tamanho = (SELECT tamanho.id_tamanho FROM tamanho WHERE tamanho.descricao = ? ),"
				+ "tarefa.id_departamento = (SELECT departamento.id_departamento FROM departamento WHERE departamento.departamento = ?),"
				+ "tarefa.descri = ?," + "tarefa.prioridade=?," + "tarefa.stat=?," + "tarefa.porcentagem=?,"
				+ "tarefa.prazo=?," + "tarefa.predecessor_1=?, " + "tarefa.predecessor_2=?, "
				+ "tarefa.predecessor_3=?, " + "tarefa.checado=?, " + "tarefa.data_ini=?, " + "tarefa.data_real=?, "
				+ "tarefa.data_fim=?, " + "tarefa.pendente_por=?," + "tarefa.status_pendencia=?,"
				+ "tarefa.historico=?," + "tarefa.responsavel=?," + "tarefa.autoridade=?," + "tarefa.dpto_correto=?,"
				+ "tarefa.processo_relacionado = (SELECT processos.id_processo FROM processos WHERE processos.processo = ?),"
				+ "tarefa.etapa = ?," + "tarefa.subetapa = ?," + "tarefa.id_update=?," + "executor.executor1=?,"
				+ "executor.porcento1=?," + "executor.executor2=?," + "executor.porcento2=?," + "executor.executor3=?,"
				+ "executor.porcento3=?," + "executor.executor4=?," + "executor.porcento4=?," + "executor.executor5=?,"
				+ "executor.porcento5=?," + "executor.executor6=?," + "executor.porcento6=?," + "executor.executor7=?,"
				+ "executor.porcento7=?," + "executor.executor8=?," + "executor.porcento8=?," + "executor.executor9=?,"
				+ "executor.porcento9=?," + "executor.executor10=?," + "executor.porcento10=? \r\n"
				+ "WHERE executor.id_tarefa=?";
		if (bd.getConnection()) {
			try {
				bd.st = bd.con.prepareStatement(sql);

				bd.st.setString(1, getCentroCusto());
				bd.st.setString(2, getTamanho());
				bd.st.setString(3, getDepartamento());
				bd.st.setString(4, getDescricao());
				bd.st.setInt(5, getPrioridade());
				bd.st.setString(6, getStatus());
				bd.st.setInt(7, getPorcentagem());
				bd.st.setInt(8, getPrazo());
				if (getPredecessor1() == 0) {
					bd.st.setNull(9, 1);
				} else {
					bd.st.setInt(9, getPredecessor1());
				}
				if (getPredecessor2() == 0) {
					bd.st.setNull(10, 0);
				} else {
					bd.st.setInt(10, getPredecessor2());
				}

				if (getPredecessor3() == 0) {
					bd.st.setNull(11, 0);
				} else {
					bd.st.setInt(11, getPredecessor3());
				}
				bd.st.setString(12, getChecado());
				bd.st.setString(13, DataParaoBanco(getDataInicio()));
				bd.st.setString(14, DataParaoBanco(getDataReal()));
				bd.st.setString(15, DataParaoBanco(getDataFim()));
				bd.st.setString(16, getPendentePor());
				bd.st.setString(17, getStatusPendencia());
				bd.st.setString(18, getHistorico());
				bd.st.setString(19, getResponsavel());
				bd.st.setString(20, getAutoridade());
				bd.st.setString(21, "");// Departamento correto
				bd.st.setString(22, getProcesso());/// Processo relacionado
				bd.st.setString(23, BuscaIDEtapaESubEtapa(getCentroCusto(), getEtapa(), getSubEtapa(), "Etapa"));
				bd.st.setString(24, BuscaIDEtapaESubEtapa(getCentroCusto(), getEtapa(), getSubEtapa(), "SubEtapa"));
				bd.st.setInt(25, sessao.getId());
				bd.st.setString(26, getExecutor1());
				bd.st.setInt(27, getPorcento1());
				bd.st.setString(28, getExecutor2());
				bd.st.setInt(29, getPorcento2());
				bd.st.setString(30, getExecutor3());
				bd.st.setInt(31, getPorcento3());
				bd.st.setString(32, getExecutor4());
				bd.st.setInt(33, getPorcento4());
				bd.st.setString(34, getExecutor5());
				bd.st.setInt(35, getPorcento5());
				bd.st.setString(36, getExecutor6());
				bd.st.setInt(37, getPorcento6());
				bd.st.setString(38, getExecutor7());
				bd.st.setInt(39, getPorcento7());
				bd.st.setString(40, getExecutor8());
				bd.st.setInt(41, getPorcento8());
				bd.st.setString(42, getExecutor9());
				bd.st.setInt(43, getPorcento9());
				bd.st.setString(44, getExecutor10());
				bd.st.setInt(45, getPorcento10());
				bd.st.setInt(46, getIDTarefa());

				int ok = bd.st.executeUpdate();
				if (ok == 2) {
					JOptionPane.showMessageDialog(null, "Tarefa atualizada com sucesso");
				} else {
					JOptionPane.showMessageDialog(null, "Falha ao atualizar tarefa", "Atualização tarefa", 0);
				}
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco", "ERRO CONEXÃO", 0);
		}
		bd.close();
	}

	public void zeraVariaveis() {
		setAutoridade("");
		setCentroCusto("");
		setDataFim("");
		setDataInicio("");
		setDataReal("");
		setDepartamento("");
		setDescricao("");
		setExecutor1("");
		setPorcento1(0);
		setExecutor2("");
		setPorcento2(0);
		setExecutor3("");
		setPorcento3(0);
		setExecutor4("");
		setPorcento4(0);
		setExecutor5("");
		setPorcento5(0);
		setExecutor6("");
		setPorcento6(0);
		setExecutor7("");
		setPorcento7(0);
		setExecutor8("");
		setPorcento8(0);
		setExecutor9("");
		setPorcento9(0);
		setExecutor10("");
		setPorcento10(0);
		setHistorico("");
		setIdcc(0);
		setIddpto(0);
		setIdtamanho(0);
		setPendentePor("");
		setPorcentagem(0);
		setPrazo(0);
		setPredecessor1(0);
		setPredecessor2(0);
		setPredecessor3(0);
		setChecado("");
		setPrioridade(0);
		setResponsavel("");
		setStatus("");
		setStatusPendencia("");
		setTamanho("");
		setAtualizacao("");
		setProcesso("");
		setEtapa("");
		setSubEtapa("");
	}

	public String DataParaoBanco(String data) {
		String data_correta = null;

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date data_desejada = null;
		try {
			data_desejada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		data_correta = formato.format(data_desejada);

		return data_correta;
	}

	public String BuscaIDEtapaESubEtapa(String cc, String etapa, String subEtapa, String etapaOuSubetapa) {
		String id = null;
		String sql = "";

		if (etapaOuSubetapa.equals("Etapa")) {

			sql = "SELECT id_etapa FROM etapas\r\n" + "INNER JOIN centro_custo\r\n"
					+ "ON centro_custo.id_centro_custo = etapas.id_cc\r\n" + "WHERE etapa = '" + etapa
					+ "' AND centrocusto = '" + cc + "'";

		} else if (etapaOuSubetapa.equals("SubEtapa")) {

			sql = "SELECT id_sub_etapas FROM sub_etapas\r\n" + "INNER JOIN etapas\r\n"
					+ "ON etapas.id_etapa = sub_etapas.id_etapa\r\n" + "INNER JOIN centro_custo\r\n"
					+ "ON centro_custo.id_centro_custo = etapas.id_cc\r\n" + "WHERE sub_etapa = '" + subEtapa
					+ "' AND etapa = '" + etapa + "' AND centrocusto = '" + cc + "'";
		}

		if (Banco.conexao()) {
			try {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.rs = Banco.st.executeQuery();

				if (Banco.rs.next()) {
					id = Banco.rs.getString(1);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return id;
	}
}