package com.myscrum.model;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.myscrum.banco.BD;

public class UsuarioDAO extends Usuario{
	
	private BD bd;

//METODO CADASTRO
public boolean cadastro() {

		boolean ok = false;
		bd = new BD();
		String sql = "INSERT INTO pessoa (nome,email,senha,login,adm,id_departamento,observacao,salario,carga_horaria,id_centrocusto)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		setIDpto(buscaidDpto());//buscando ID do Dpto 
		setIdCC(buscaidCC()); // busca ID centro decusto
		int verifica = verificar();
		if(verifica == 0){
		if(bd.getConnection()) {//se conectar com o banco continua...
			
			try {
				bd.st = bd.con.prepareStatement(sql);
				
				bd.st.setString(1, getNome());
				bd.st.setString(2, getEmail());
				//Criptografando senha
				MessageDigest md = null;
				
				try {
					md = MessageDigest.getInstance("SHA");
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}

				String ppaswd = getSenha();
				md.update(ppaswd.getBytes());
				BigInteger hash = new BigInteger(1,md.digest());
				String retornasenha = hash.toString(16);
				//Fim criptografia
				bd.st.setString(3, retornasenha);
				bd.st.setString(4, getLogin());
				bd.st.setInt(5, getFuncao());
				bd.st.setInt(6, getIDpto());
				bd.st.setString(7, getObs());
				bd.st.setDouble(8,getSalario());
				bd.st.setInt(9, getCHoraria());
				bd.st.setInt(10, getIdCC());
				
				int rs = bd.st.executeUpdate();
				
				if(rs == 1) {
					JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso");
					ok = true;
				}else {
					JOptionPane.showMessageDialog(null,"Falha ao cadastrar usuario");
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO INSERT",0 );
			}
		}else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
	}else {
		JOptionPane.showMessageDialog(null, "Usuario ou e-mail já está em uso");
	}
		bd.close();
		return ok;
}

//METODO ATUALIZA
public boolean atualiza() {
		boolean ok = false;
		String sql;
		bd = new BD();
		
		if(getSenha().equals("")) {//se o campo senha estiver vazio use o update sem o campo senha
		sql = "UPDATE pessoa "
				+ "SET nome=?, email=?, login=?, ativo=?, adm=?, id_departamento=?, observacao=?, salario=?, carga_horaria=?, id_centrocusto =? "
				+ "WHERE id_pessoa=?";
		}else{
		sql = "UPDATE pessoa "
				+ "SET nome=?, email=?, login=?, ativo=?, adm=?, id_departamento=?, observacao=?, salario=?, carga_horaria=?, id_centrocusto =?, senha=? "
				+ "WHERE id_pessoa=?";
		}
		setIDpto(buscaidDpto());//buscando ID do Dpto 
		setIdCC(buscaidCC()); //busca id do CC
		int verifica = verificar();
		if(verifica <= 1){
		if(bd.getConnection()) {//se conectar com o banco continua...
			
			try {
				bd.st = bd.con.prepareStatement(sql);
				
				bd.st.setString(1, getNome());
				bd.st.setString(2, getEmail());
				bd.st.setString(3, getLogin());
				bd.st.setInt(4, getAtivo());
				bd.st.setInt(5, getFuncao());
				bd.st.setInt(6, getIDpto());
				bd.st.setString(7, getObs());
				bd.st.setDouble(8, getSalario());
				bd.st.setInt(9, getCHoraria());
				bd.st.setInt(10, getIdCC());
		     
				if(getSenha().equals("")) {// se o campo não estiver vazio acrecentamos mais um parametro no SQL
		    	 bd.st.setInt(11, getID());
		     }else {
		    	    //Criptografando senha
					MessageDigest md = null; 
					
					try {
						md = MessageDigest.getInstance("SHA");   
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}

					String ppaswd = getSenha();
					md.update(ppaswd.getBytes());
					BigInteger hash = new BigInteger(1,md.digest());
					String retornasenha = hash.toString(16);
					//Fim criptografia
		            bd.st.setString(11, retornasenha);
		    	    bd.st.setInt(12, getID());
		     }
				int rs = bd.st.executeUpdate();
				
				if(rs == 1) {
					JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso");
					ok = true;
				}else {
					JOptionPane.showMessageDialog(null,"Falha ao atualizar usuario");
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO INSERT",0 );
			}
		}else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
	}else {
		JOptionPane.showMessageDialog(null, "Usuario ou e-mail já está em uso");
	}
		bd.close();
		return ok;
	}

//METODO BUSCAR ID CC
public int buscaidCC() {
		bd = new BD();
		String sql = "SELECT id_centro_custo FROM centro_custo WHERE centrocusto = ?";
		int ID = 0;
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.st.setString(1, getCC());
				
				bd.rs = bd.st.executeQuery();
				
				if(bd.rs.next()) {
					ID = bd.rs.getInt(1);
				}else {
					JOptionPane.showMessageDialog(null,"Centro de custo não encontrado");
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO BUSCA ID CC",0 );
			}
		}else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
		bd.close();
	    return ID;
	}

//METODO BUSCAR ID DPTO
public int buscaidDpto() {
		bd = new BD();
		String sql = "SELECT id_departamento FROM departamento WHERE departamento = ?";
		int ID = 0;
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.st.setString(1, getDpto());
				
				bd.rs = bd.st.executeQuery();
				
				if(bd.rs.next()) {
					ID = bd.rs.getInt(1);
				}else {
					JOptionPane.showMessageDialog(null,"Departamento não encontrado");
				}
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO BUSCA ID DPTO",0 );
			}
		}else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}
	bd.close();	
	return ID;
	}
	
//METODO VERIFICAR
public int verificar() {
		bd = new BD();
		String sql = "SELECT login,email FROM pessoa WHERE login=? or email=? OR nome=?";
		int verifica = 0;
		if(bd.getConnection()) {//se conectar com o banco continua...
			try {
				
				bd.st = bd.con.prepareStatement(sql);
				
				bd.st.setString(1, getLogin());
				bd.st.setString(2, getEmail());
				bd.st.setString(3, getNome());
				
				bd.rs = bd.st.executeQuery();
				
			 while(bd.rs.next()) {
				 verifica += 1;
			 }
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, erro.toString(), "ERRO VERIFICAÇÂO E-MAIL e USER",0 );
			}
		}else {
			JOptionPane.showMessageDialog(null, "Falha ao conectar com o banco");
		}	
		bd.close();
	    return verifica;
	}

//fim
}