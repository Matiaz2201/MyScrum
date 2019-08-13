package com.myscrum.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public void cadastrar(Tarefa tarefa) {

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

				bd.st.setString(1, tarefa.getCentroCusto());
				bd.st.setString(2, tarefa.getTamanho());
				bd.st.setString(3, tarefa.getDepartamento());
				bd.st.setString(4, tarefa.getProcesso());/// Processo relacionado
				bd.st.setString(5, tarefa.getDescricao());
				bd.st.setInt(6, tarefa.getPrioridade());
				bd.st.setString(7, tarefa.getStatus());
				bd.st.setInt(8, tarefa.getPorcentagem());
				bd.st.setInt(9, tarefa.getPrazo());
				if (tarefa.getPredecessor1() == 0) {
					bd.st.setNull(10, 1);
				} else {
					bd.st.setInt(10, tarefa.getPredecessor1());
				}
				if (tarefa.getPredecessor2() == 0) {
					bd.st.setNull(11, 0);
				} else {
					bd.st.setInt(11, tarefa.getPredecessor2());
				}

				if (tarefa.getPredecessor3() == 0) {
					bd.st.setNull(12, 0);
				} else {
					bd.st.setInt(12, tarefa.getPredecessor3());
				}
				bd.st.setString(13, tarefa.getChecado());
				bd.st.setString(14, DataParaoBanco(tarefa.getDataInicio()));
				bd.st.setString(15, DataParaoBanco(tarefa.getDataReal()));
				bd.st.setString(16, DataParaoBanco(tarefa.getDataFim()));
				bd.st.setString(17, tarefa.getPendentePor());
				bd.st.setString(18, tarefa.getStatusPendencia());
				bd.st.setString(19, tarefa.getHistorico());
				bd.st.setString(20, tarefa.getResponsavel());
				bd.st.setString(21, tarefa.getAutoridade());
				bd.st.setString(22, "");// Departamento correto
				bd.st.setInt(23, sessao.getId());
				bd.st.setInt(24, sessao.getId());
				bd.st.setString(25, BuscaIDEtapaESubEtapa(tarefa.getCentroCusto(), tarefa.getEtapa(), tarefa.getSubEtapa(), "Etapa"));
				bd.st.setString(26, BuscaIDEtapaESubEtapa(tarefa.getCentroCusto(), tarefa.getEtapa(), tarefa.getSubEtapa(), "SubEtapa"));

				int ok = bd.st.executeUpdate();

				// Pegando o id maior (ultimo id inserido)
				sql = "SELECT MAX(id_tarefa) FROM tarefa";
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery(sql);

				if (bd.rs.next()) {
					ID_TAREFA = bd.rs.getInt(1);
				}

				if (ok == 1) {
					cadastrarExe(tarefa);
					JOptionPane.showMessageDialog(null, "Tarefa " + ID_TAREFA + " cadastrada com sucesso");
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

	public String cadastrar(Tarefa tarefa, boolean importar) {
		
		String sql = "INSERT INTO tarefa(id_centro_custo,id_tamanho,id_departamento,processo_relacionado,descri,prioridade,stat,porcentagem,prazo,predecessor_1,predecessor_2,predecessor_3,checado,data_ini,data_real,data_fim,pendente_por,status_pendencia,historico,responsavel,autoridade,dpto_correto,id_pessoa,id_update,etapa,subetapa)"
				+ "SELECT"
				+ "(SELECT centro_custo.id_centro_custo FROM centro_custo WHERE centro_custo.centrocusto= '" + tarefa.getCentroCusto() + "'),"
				+ "(SELECT tamanho.id_tamanho FROM tamanho WHERE tamanho.descricao= '" + tarefa.getTamanho() + "'),"
				+ "(SELECT departamento.id_departamento FROM departamento WHERE departamento.departamento = '" + tarefa.getDepartamento() + "' ),"
				+ "(SELECT processos.id_processo FROM processos WHERE processos.processo = '" + tarefa.getProcesso() + "' )," 
				+ "'" + tarefa.getDescricao() + "' AS descri,"
				+ tarefa.getPrioridade() + " AS prioridade," 
				+ "'" + tarefa.getStatus() + "' AS stat," 
				+ tarefa.getPorcentagem() + " AS porcentagem," 
				+ tarefa.getPrazo() + " AS prazo," ;
				
				if (tarefa.getPredecessor1() != 0) {
					sql += tarefa.getPredecessor1() + " AS predecessor1 ,";
				} else {
					sql += "null AS predecessor1,";
				}
				if (tarefa.getPredecessor2() != 0) {
					sql += tarefa.getPredecessor2() + " AS predecessor2 ,";
				} else {
					sql += "null AS predecessor2,";
				}

				if (tarefa.getPredecessor3() != 0) {
					sql += tarefa.getPredecessor3() + " AS predecessor3 ,";
				} else {
					sql += "null AS predecessor3,";
				}
				
				sql += "'" + tarefa.getChecado() + "' AS checado, " 
				+ "'" + DataParaoBanco(tarefa.getDataInicio()) + "' AS data_ini,"
				+ "'" + DataParaoBanco(tarefa.getDataReal()) + "' AS data_real," 
				+ "'" + DataParaoBanco(tarefa.getDataFim()) + "' AS data_fim," 
				+ "'" + tarefa.getPendentePor() + "' AS pendente_por," 
				+ "'" + tarefa.getStatusPendencia() + "' AS status_pendencia,"
				+ "'" + tarefa.getHistorico() + "' AS historico," 
				+ "'" + tarefa.getResponsavel() + "' AS responsavel," 
			    + "'" + tarefa.getAutoridade() + "' AS autoridade," 
				+ " '' AS dpto_correto,"
				+ sessao.getId() + " AS id_pessoa," 
				+ sessao.getId() + " AS id_update,"
				+ "(SELECT id_etapa FROM etapas INNER JOIN centro_custo ON centro_custo.id_centro_custo = etapas.id_cc WHERE etapa = '" + tarefa.getEtapa() + "' AND centrocusto = '" + tarefa.getCentroCusto() + "') AS etapa, " 
				+ "(SELECT id_sub_etapas FROM sub_etapas INNER JOIN etapas ON etapas.id_etapa = sub_etapas.id_etapa INNER JOIN centro_custo ON centro_custo.id_centro_custo = etapas.id_cc WHERE sub_etapa = '" + tarefa.getSubEtapa() + "' AND etapa = '" + tarefa.getEtapa() + "' AND centrocusto = '" + tarefa.getCentroCusto() + "') AS subetapa ";

		if (Banco.conexao()) {// se conectar com o Banco continua...

			try {
				
				System.out.println(sql);
				
				Banco.st = Banco.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				int ok = Banco.st.executeUpdate();
			
				//Colentando o ID gerado pela operação de INSERT da tarefa
				ResultSet result = Banco.st.getGeneratedKeys();
				result.next();
				ID_TAREFA = result.getInt(0);
			
				if (ok == 1) {
					cadastrarExe(tarefa);
					return tarefa.getDescricao() + " - (" + ID_TAREFA + ") | Cadastrada com sucesso"; 
				} else {
					return tarefa.getDescricao() + " - (" + ID_TAREFA + ") | Falha no cadastro";
				}
			} catch (SQLException erro) {
				return tarefa.getDescricao() + " - (" + ID_TAREFA + ") | Falha no cadastro  - " + erro.toString();
			}
		} else {
			return tarefa.getDescricao() + " - (" + ID_TAREFA + ") | Falha no cadastro - Falha na conexão com o bd";
		}
	}
	
	public boolean cadastrarExe(Tarefa tarefa) {
		boolean executor = false;

		String sql = "INSERT INTO executor (executor1,porcento1,executor2,porcento2,executor3,porcento3,executor4,porcento4,executor5,porcento5,executor6,porcento6,executor7,porcento7,executor8,porcento8,executor9,porcento9,executor10,porcento10,id_tarefa)"
				+ "VALUES "
				+ "('"+ tarefa.getExecutor1() +"'," + tarefa.getPorcento1() + ","
				+ "'"+ tarefa.getExecutor2() +"'," + tarefa.getPorcento2() + ","
				+ "'"+ tarefa.getExecutor3() +"'," + tarefa.getPorcento3() + ","
				+ "'"+ tarefa.getExecutor4() +"'," + tarefa.getPorcento4() + ","
				+ "'"+ tarefa.getExecutor5() +"'," + tarefa.getPorcento5() + ","
				+ "'"+ tarefa.getExecutor6() +"'," + tarefa.getPorcento6() + ","
				+ "'"+ tarefa.getExecutor7() +"'," + tarefa.getPorcento7() + ","
				+ "'"+ tarefa.getExecutor8() +"'," + tarefa.getPorcento8() + ","
				+ "'"+ tarefa.getExecutor9() +"'," + tarefa.getPorcento9() + ","
				+ "'"+ tarefa.getExecutor10() +"'," + tarefa.getPorcento10() + ")";
				
		if (Banco.conexao()) {
			try {
				bd.st = bd.con.prepareStatement(sql);

				if(Banco.st.executeUpdate() == 1) {
					executor = true;
				}

			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO INSERT EXECUTORES", 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		return executor;
	}
	
	
	public void atualizar(Tarefa tarefa) {
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

				bd.st.setString(1, tarefa.getCentroCusto());
				bd.st.setString(2, tarefa.getTamanho());
				bd.st.setString(3, tarefa.getDepartamento());
				bd.st.setString(4, tarefa.getDescricao());
				bd.st.setInt(5, tarefa.getPrioridade());
				bd.st.setString(6, tarefa.getStatus());
				bd.st.setInt(7, tarefa.getPorcentagem());
				bd.st.setInt(8, tarefa.getPrazo());
				if (tarefa.getPredecessor1() == 0) {
					bd.st.setNull(9, 1);
				} else {
					bd.st.setInt(9, tarefa.getPredecessor1());
				}
				if (tarefa.getPredecessor2() == 0) {
					bd.st.setNull(10, 0);
				} else {
					bd.st.setInt(10, tarefa.getPredecessor2());
				}

				if (tarefa.getPredecessor3() == 0) {
					bd.st.setNull(11, 0);
				} else {
					bd.st.setInt(11, tarefa.getPredecessor3());
				}
				bd.st.setString(12, tarefa.getChecado());
				bd.st.setString(13, DataParaoBanco(tarefa.getDataInicio()));
				bd.st.setString(14, DataParaoBanco(tarefa.getDataReal()));
				bd.st.setString(15, DataParaoBanco(tarefa.getDataFim()));
				bd.st.setString(16, tarefa.getPendentePor());
				bd.st.setString(17, tarefa.getStatusPendencia());
				bd.st.setString(18, tarefa.getHistorico());
				bd.st.setString(19, tarefa.getResponsavel());
				bd.st.setString(20, tarefa.getAutoridade());
				bd.st.setString(21, "");// Departamento correto
				bd.st.setString(22, tarefa.getProcesso());/// Processo relacionado
				bd.st.setString(23, BuscaIDEtapaESubEtapa(tarefa.getCentroCusto(), tarefa.getEtapa(), tarefa.getSubEtapa(), "Etapa"));
				bd.st.setString(24, BuscaIDEtapaESubEtapa(tarefa.getCentroCusto(), tarefa.getEtapa(), tarefa.getSubEtapa(), "SubEtapa"));
				bd.st.setInt(25, sessao.getId());
				bd.st.setString(26, tarefa.getExecutor1());
				bd.st.setInt(27, tarefa.getPorcento1());
				bd.st.setString(28, tarefa.getExecutor2());
				bd.st.setInt(29, tarefa.getPorcento2());
				bd.st.setString(30, tarefa.getExecutor3());
				bd.st.setInt(31, tarefa.getPorcento3());
				bd.st.setString(32, tarefa.getExecutor4());
				bd.st.setInt(33, tarefa.getPorcento4());
				bd.st.setString(34, tarefa.getExecutor5());
				bd.st.setInt(35, tarefa.getPorcento5());
				bd.st.setString(36, tarefa.getExecutor6());
				bd.st.setInt(37, tarefa.getPorcento6());
				bd.st.setString(38, tarefa.getExecutor7());
				bd.st.setInt(39, tarefa.getPorcento7());
				bd.st.setString(40, tarefa.getExecutor8());
				bd.st.setInt(41, tarefa.getPorcento8());
				bd.st.setString(42, tarefa.getExecutor9());
				bd.st.setInt(43, tarefa.getPorcento9());
				bd.st.setString(44, tarefa.getExecutor10());
				bd.st.setInt(45, tarefa.getPorcento10());
				bd.st.setInt(46, tarefa.getIDTarefa());

				int ok = bd.st.executeUpdate();
				if (ok == 2) {
					JOptionPane.showMessageDialog(null, "Tarefa " + tarefa.getIDTarefa() +" atualizada com sucesso");
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

	public String atualizar(Tarefa tarefa, boolean importar) {
		
		if (Banco.conexao()) {
			try {
					
				String sql = "UPDATE tarefa \n\r" 
						+ "INNER JOIN executor ON tarefa.id_tarefa = executor.id_tarefa \n\r"
						+ "SET \n\r "
						+ "tarefa.id_centro_custo = (SELECT centro_custo.id_centro_custo FROM centro_custo WHERE centro_custo.centrocusto = '" + tarefa.getCentroCusto() + "'), "
						+ "tarefa.id_tamanho = (SELECT tamanho.id_tamanho FROM tamanho WHERE tamanho.descricao = '" + tarefa.getTamanho() + "'), "
						+ "tarefa.id_departamento = (SELECT departamento.id_departamento FROM departamento WHERE departamento.departamento = '" + tarefa.getDepartamento() + "'), "
						+ "tarefa.descri= '" + tarefa.getDescricao() + "', "
						+ "tarefa.prioridade= '" + tarefa.getPrioridade() + "', " 
						+ "tarefa.stat= '" + tarefa.getStatus() + "', " 
						+ "tarefa.porcentagem= '" + tarefa.getPorcentagem() + "', "
						+ "tarefa.prazo= '" + tarefa.getPrazo() + "', ";
				
						if (tarefa.getPredecessor1() != 0) {
							sql += "tarefa.predecessor_1= '" + tarefa.getPredecessor1() + "', ";
						}
						
						if (tarefa.getPredecessor2() != 0) {
							sql += "tarefa.predecessor_2= '" + tarefa.getPredecessor2() + "', ";
						}
						
						if (tarefa.getPredecessor3() != 0) {
							sql += "tarefa.predecessor_3= '" + tarefa.getPredecessor3() + "', ";
						}
						
						sql +=  "tarefa.checado= '" + tarefa.getChecado() + "', " 
						+ "tarefa.data_ini= '" + DataParaoBanco(tarefa.getDataInicio()) + "', "
						+ "tarefa.data_real= '" + DataParaoBanco(tarefa.getDataReal()) + "', "
						+ "tarefa.data_fim= '" + DataParaoBanco(tarefa.getDataFim()) + "', " 
						+ "tarefa.pendente_por= '" + tarefa.getPendentePor() + "'," 
						+ "tarefa.status_pendencia= '" + tarefa.getStatusPendencia() + "',"
						+ "tarefa.historico= '" + tarefa.getHistorico() + "'," 
						+ "tarefa.responsavel= '" + tarefa.getResponsavel() + "',"
						+ "tarefa.autoridade= '" + tarefa.getAutoridade() + "'," 
					//	+ "tarefa.dpto_correto= " + "" + ","
						+ "tarefa.processo_relacionado = (SELECT processos.id_processo FROM processos WHERE processos.processo = '" + tarefa.getProcesso() + "'),"
						+ "tarefa.etapa= (SELECT id_etapa FROM etapas INNER JOIN centro_custo ON centro_custo.id_centro_custo = etapas.id_cc WHERE etapa = '" + tarefa.getEtapa() + "' AND centrocusto = '" + tarefa.getCentroCusto() + "'), " 
						+ "tarefa.subetapa= (SELECT id_sub_etapas FROM sub_etapas INNER JOIN etapas ON etapas.id_etapa = sub_etapas.id_etapa INNER JOIN centro_custo ON centro_custo.id_centro_custo = etapas.id_cc WHERE sub_etapa = '" + tarefa.getSubEtapa() + "' AND etapa = '" + tarefa.getEtapa() + "' AND centrocusto = '" + tarefa.getCentroCusto() + "'), " 
						+ "tarefa.id_update= " + sessao.getId() + "," 
						+ "executor.executor1= '" + tarefa.getExecutor1() + "',"
						+ "executor.porcento1= " + tarefa.getPorcento1() + " \n\r";
						
						if(tarefa.getExecutor2() != "" && tarefa.getExecutor2() != null) {
							sql += ", executor.executor2= '" + tarefa.getExecutor2() + "',"
								+ "executor.porcento2= " + tarefa.getPorcento2() + " \n\r ";
						}
						
						if(tarefa.getExecutor3() != "" && tarefa.getExecutor3() != null) {
							sql += ", executor.executor3= '" + tarefa.getExecutor3() + "',"
								+ "executor.porcento3= " + tarefa.getPorcento3() + " \n\r ";
						}
						
						if(tarefa.getExecutor4() != "" && tarefa.getExecutor4() != null) {
							sql += ", executor.executor4= '" + tarefa.getExecutor4() + "',"
								+ "executor.porcento4= " + tarefa.getPorcento4() + " \n\r ";
						}
						
						if(tarefa.getExecutor5() != "" && tarefa.getExecutor5() != null) {
							sql += ", executor.executor5= '" + tarefa.getExecutor5() + "',"
								+ "executor.porcento5= " + tarefa.getPorcento5() + " \n\r ";
						}
						
						if(tarefa.getExecutor6() != "" && tarefa.getExecutor6() != null) {
							sql += ", executor.executor6= '" + tarefa.getExecutor6() + "',"
								+ "executor.porcento6= " + tarefa.getPorcento6() + " \n\r ";
						}
						
						if(tarefa.getExecutor7() != "" && tarefa.getExecutor7() != null) {
							sql += ", executor.executor7= '" + tarefa.getExecutor7() + "',"
								+ "executor.porcento7= " + tarefa.getPorcento7() + " \n\r ";
						}
						
						if(tarefa.getExecutor8() != "" && tarefa.getExecutor8() != null) {
							sql += ", executor.executor8= '" + tarefa.getExecutor8() + "',"
								+ "executor.porcento8= " + tarefa.getPorcento8() + " \n\r ";
						}
						
						if(tarefa.getExecutor9() != "" && tarefa.getExecutor9() != null) {
							sql += ", executor.executor9= '" + tarefa.getExecutor9() + "',"
								+ "executor.porcento9= " + tarefa.getPorcento9() + " \n\r ";
						}
						
						if(tarefa.getExecutor10() != "" && tarefa.getExecutor10() != null) {
							sql += ", executor.executor10= '" + tarefa.getExecutor10() + "',"
								+ "executor.porcento10= " + tarefa.getPorcento10() + " \n\r ";
						}
						
						sql += "WHERE executor.id_tarefa= '" + tarefa.getIDTarefa() + "'";
				
					
						
				Banco.st = Banco.con.prepareStatement(sql);
				System.out.println(sql);
				int ok = Banco.st.executeUpdate();
				if (ok == 2) {
					return tarefa.getDescricao() + " - (" + tarefa.getIDTarefa() + ") | Atualizada com sucesso"; 
				} else {
					return tarefa.getDescricao() + " - (" + tarefa.getIDTarefa() + ") | Falha ao atualizar"; 
				}
			} catch (SQLException erro) {
				return tarefa.getDescricao() + " - (" + tarefa.getIDTarefa() + ") | Falha ao atualizar - " + erro.toString(); 
			}
		} else {
			return tarefa.getDescricao() + " - (" + tarefa.getIDTarefa() + ") | Falha ao atualizar - Falha ao conectar com o bd"; 
		}
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

			sql = "SELECT id_etapa FROM etapas\r\n" 
					+ "INNER JOIN centro_custo\r\n"
					+ "ON centro_custo.id_centro_custo = etapas.id_cc\r\n" 
					+ "WHERE etapa = '" + etapa
					+ "' AND centrocusto = '" + cc + "'";

		} else if (etapaOuSubetapa.equals("SubEtapa")) {

			sql = "SELECT id_sub_etapas FROM sub_etapas\r\n" 
					+ "INNER JOIN etapas\r\n"
					+ "ON etapas.id_etapa = sub_etapas.id_etapa\r\n" 
					+ "INNER JOIN centro_custo\r\n"
					+ "ON centro_custo.id_centro_custo = etapas.id_cc\r\n" 
					+ "WHERE sub_etapa = '" + subEtapa
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