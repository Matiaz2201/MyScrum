package view;



import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

import com.myscrum.banco.BD;
import com.myscrum.controller.Controle;
import com.myscrum.model.TableGrade;
import com.myscrum.model.Usuario;


public class ListUsuarioTela extends JFrame{
	
	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	private static final long serialVersionUID = 1L;
private UsuarioTela formularioPai;
  private JRadioButton nomeCheckBox;
  private JRadioButton emailCheckBox;
  private JTextField nomeText;
  private JTextField emailText;
  private JRadioButton loginCheckBox;
  private JTextField loginText;
  private JButton atualizarButton;
  private JTable tabela;
  private JScrollPane sp;
  private String sql;
  private ButtonGroup bg;
  JPanel panel = new JPanel();
  Usuario user = new Usuario();
  BD bd = new BD();

//metodo de referencia a classe pai, para poder usar os metodos da clalsse usuarioTela
public ListUsuarioTela(JFrame formularioPai) {
	 if (formularioPai instanceof UsuarioTela)
         this.formularioPai = (UsuarioTela) formularioPai;
	 
	 try {
         //here you can put the selected theme class name in JTattoo
         UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

     } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(KambanTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(KambanTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(KambanTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(KambanTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     }
	 
	  //passando para tela controler que a tela listusuario foi aberta
	  Controle.setListUsuario(true);
	 
	  //set iniciais
      setTitle("Usuarios");//titulo do frame	
	  setSize(531, 298);//define o tamanho do frame
	  getContentPane().add(panel);//add panel ao frame
	  setResizable(false);
      panel.setLayout(null);//organiza os bot�es conforme setado no panel
	  
      //construct components
      nomeCheckBox = new JRadioButton ("Nome:");
      nomeCheckBox.setBackground(Color.WHITE);
      emailCheckBox = new JRadioButton ("E-mail:");
      emailCheckBox.setBackground(Color.WHITE);
      loginCheckBox = new JRadioButton ("Login:");
      loginCheckBox.setBackground(Color.WHITE);
      nomeText = new JTextField (5);
      nomeText.setEditable(false);
      emailText = new JTextField (5);
      emailText.setEditable(false);
      loginText = new JTextField (5);
      loginText.setEditable(false);
      atualizarButton = new JButton ("Atualizar");

      //add components
      panel.add (nomeCheckBox);
      panel.add (emailCheckBox);
      panel.add (nomeText);
      panel.add (emailText);
      panel.add (loginCheckBox);
      panel.add (loginText);
      panel.add (atualizarButton);

      //set component bounds (only needed by Absolute Positioning)
      nomeCheckBox.setBounds (0, 10, 70, 25);
      emailCheckBox.setBounds (170, 10, 70, 25);
      nomeText.setBounds (70, 10, 100, 25);
      emailText.setBounds (240, 10, 100, 25);
      loginCheckBox.setBounds (340, 10, 70, 25);
      loginText.setBounds (410, 10, 100, 25);
      atualizarButton.setBounds (403, 50, 120, 35);
      atualizarButton.setBackground(new Color(163, 184,204));
      
      bg = new ButtonGroup();
      bg.add(emailCheckBox);
      bg.add(nomeCheckBox);
      bg.add(loginCheckBox);
    		  
      //Setando Icone do Frame
      java.net.URL url = this.getClass().getResource("/com/myscrum/assets/setIcon1.png");
      Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
      this.setIconImage(iconeTitulo);
  	
      //propriedades do frame 
      panel.setBackground(Color.WHITE);
      setLocationRelativeTo(null);//localidade do frame referente a tela(centraliza o frame)
      setVisible(true);//habilita visualiza��o do frame
      
      //carregando o select com o select padr�o sem filtro
      sql = "SELECT pessoa.id_pessoa,pessoa.nome, pessoa.email, pessoa.login, pessoa.salario, pessoa.carga_horaria, \r\n"
			+"CASE (pessoa.ativo) \r\n"
			+"WHEN 1 THEN 'Sim' \r\n"
			+"WHEN 0 THEN 'N�o' \r\n"
				+"ELSE 'CampoVazio' \r\n"
				+"END AS ativo, \r\n"
			+"CASE (pessoa.adm) \r\n"
			+"WHEN 0 THEN 'Usuario' \r\n"
			+"WHEN 1 THEN 'Administrador' \r\n"
			+"WHEN 2 THEN 'Lider' \r\n"
			+"WHEN 3 THEN 'Gestor' \r\n"
				+"ELSE 'Campo Vazio' \r\n"
				+"END AS adm, \r\n "
			   +"departamento.departamento, \r\n"
				+"centro_custo.centrocusto, \r\n"
				+"pessoa.observacao \r\n"
			+"FROM scrum.pessoa \r\n"
				+"INNER JOIN departamento \r\n"
			    +"ON pessoa.id_departamento=departamento.id_departamento \r\n"
				+"INNER JOIN centro_custo \r\n"
			    +"ON pessoa.id_centrocusto = centro_custo.id_centro_custo \r\n"
			   +"ORDER BY pessoa.nome ASC";
     
      listar(sql);

      //fun��es do minimizar, maximizar e fechar
      addWindowListener(new WindowListener() {
		public void windowOpened(WindowEvent e) {
		}
		public void windowIconified(WindowEvent e) {
		}
		public void windowDeiconified(WindowEvent e) {
		}
		public void windowDeactivated(WindowEvent e) {
		}
		public void windowClosing(WindowEvent e) {
           dispose();
           Controle.setListUsuario(false);
           ListUsuarioTela.this.formularioPai.cadastrarButton();
           ListUsuarioTela.this.formularioPai.limpar();
        }
        public void windowClosed(WindowEvent e) {
        }
		public void windowActivated(WindowEvent e) {
	
		}
	});
      
      atualizarButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		  if(nomeCheckBox.isSelected() == true) {
			  sql = "SELECT pessoa.id_pessoa,pessoa.nome, pessoa.email, pessoa.login, pessoa.salario, pessoa.carga_horaria, \r\n"
						+"CASE (pessoa.ativo) \r\n"
						+"WHEN 1 THEN 'Sim' \r\n"
						+"WHEN 0 THEN 'N�o' \r\n"
							+"ELSE 'CampoVazio' \r\n"
							+"END AS ativo, \r\n"
							+"CASE (pessoa.adm) \r\n"
							+"WHEN 0 THEN 'Usuario' \r\n"
							+"WHEN 1 THEN 'Administrador' \r\n"
							+"WHEN 2 THEN 'Lider' \r\n"
							+"WHEN 3 THEN 'Gestor' \r\n"
							+"ELSE 'Campo Vazio' \r\n"
							+"END AS adm, \r\n "
						   +"departamento.departamento, \r\n"
							+"centro_custo.centrocusto, \r\n"
							+"pessoa.observacao \r\n"
						+"FROM scrum.pessoa \r\n"
							+"INNER JOIN departamento \r\n"
						    +"ON pessoa.id_departamento=departamento.id_departamento \r\n"
							+"INNER JOIN centro_custo \r\n"
						    +"ON pessoa.id_centrocusto = centro_custo.id_centro_custo \r\n"
						   +"WHERE nome LIKE '%"+nomeText.getText()+"%'"
						   +"ORDER BY pessoa.nome ASC";
		  			
		  		}else if(emailCheckBox.isSelected() == true) {
				  sql = "SELECT pessoa.id_pessoa,pessoa.nome, pessoa.email, pessoa.login, pessoa.salario, pessoa.carga_horaria, \r\n"
							+"CASE (pessoa.ativo) \r\n"
							+"WHEN 1 THEN 'Sim' \r\n"
							+"WHEN 0 THEN 'N�o' \r\n"
								+"ELSE 'CampoVazio' \r\n"
								+"END AS ativo, \r\n"
								+"CASE (pessoa.adm) \r\n"
								+"WHEN 0 THEN 'Usuario' \r\n"
								+"WHEN 1 THEN 'Administrador' \r\n"
								+"WHEN 2 THEN 'Lider' \r\n"
								+"WHEN 3 THEN 'Gestor' \r\n"
								+"ELSE 'Campo Vazio' \r\n"
								+"END AS adm, \r\n "
							   +"departamento.departamento, \r\n"
								+"centro_custo.centrocusto, \r\n"
								+"pessoa.observacao \r\n"
							+"FROM scrum.pessoa \r\n"
								+"INNER JOIN departamento \r\n"
							    +"ON pessoa.id_departamento=departamento.id_departamento \r\n"
								+"INNER JOIN centro_custo \r\n"
							    +"ON pessoa.id_centrocusto = centro_custo.id_centro_custo \r\n"
							    +"WHERE email LIKE '%"+emailText.getText()+"%'"
				  				+"ORDER BY pessoa.nome ASC";
			  
		  			}else if(loginCheckBox.isSelected() == true) {
				   sql = "SELECT pessoa.id_pessoa,pessoa.nome, pessoa.email, pessoa.login, pessoa.salario, pessoa.carga_horaria, \r\n"
							+"CASE (pessoa.ativo) \r\n"
							+"WHEN 1 THEN 'Sim' \r\n"
							+"WHEN 0 THEN 'N�o' \r\n"
								+"ELSE 'CampoVazio' \r\n"
								+"END AS ativo, \r\n"
								+"CASE (pessoa.adm) \r\n"
								+"WHEN 0 THEN 'Usuario' \r\n"
								+"WHEN 1 THEN 'Administrador' \r\n"
								+"WHEN 2 THEN 'Lider' \r\n"
								+"WHEN 3 THEN 'Gestor' \r\n"
								+"ELSE 'Campo Vazio' \r\n"
								+"END AS adm, \r\n "
							   +"departamento.departamento, \r\n"
								+"centro_custo.centrocusto, \r\n"
								+"pessoa.observacao \r\n"
							+"FROM scrum.pessoa \r\n"
								+"INNER JOIN departamento \r\n"
							    +"ON pessoa.id_departamento=departamento.id_departamento \r\n"
								+"INNER JOIN centro_custo \r\n"
							    +"ON pessoa.id_centrocusto = centro_custo.id_centro_custo \r\n"
							    +"WHERE login LIKE '%"+loginText.getText()+"%'"
				   				+"ORDER BY pessoa.nome ASC";
		  			
		  		}
		  
		   listar(sql);
		}
	});
      
      nomeCheckBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		   emailText.setText("");
		   loginText.setText("");	
		   emailText.setEditable(false);
		   loginText.setEditable(false);
		   nomeText.setEditable(true);
		}
	});
     
      emailCheckBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		   nomeText.setText("");
		   loginText.setText("");
		   nomeText.setEditable(false);
		   loginText.setEditable(false);
		   emailText.setEditable(true);
			
		}
	});
    
      loginCheckBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			nomeText.setText("");
			emailText.setText("");
			nomeText.setEditable(false);
			emailText.setEditable(false);
			loginText.setEditable(true);
			
		}
	});
}

//METODOS DA CLASSE
public void listar(String sql){
	boolean conectou = false;
	try{
		bd.getConnection();
		conectou = true;
		bd.st = bd.con.prepareStatement(sql);
		bd.rs = bd.st.executeQuery();
		if(bd.rs.next()){//se haver usuarios continue
			if(conectou == true){

				if(tabela != null){//se existir outra tabela montada apaga
					tabela.setVisible(false);
					tabela = null;
					sp.setVisible(false);
					sp = null;
				}
				
				Vector<String> cabecalhoPersonalizado = new Vector<>();

				cabecalhoPersonalizado.addElement("ID");//0
				cabecalhoPersonalizado.addElement("Nome");//1
				cabecalhoPersonalizado.addElement("E-mail");//2
				cabecalhoPersonalizado.addElement("Login");//3
				cabecalhoPersonalizado.addElement("Sal�rio");//4
				cabecalhoPersonalizado.addElement("Carga Hor�ria");//5
				cabecalhoPersonalizado.addElement("Ativo");//6
				cabecalhoPersonalizado.addElement("Fun��o");//7
				cabecalhoPersonalizado.addElement("Departamento");//8
				cabecalhoPersonalizado.addElement("Centro de Custo");//9
				cabecalhoPersonalizado.addElement("OBS");//10
				
				tabela = TableGrade.getTable(bd,sql, cabecalhoPersonalizado);
		
				//adiciona Scroll ao frame
				sp = new JScrollPane(tabela);
				
				sp.setBounds(3,100,520,165);
				panel.add(sp);
				panel.updateUI(); //atualiza tela

				tabela.setEditingRow(1);
				tabela.setEditingColumn(1);
				tabela.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
	
				tabela.addMouseListener(new MouseAdapter() {
					public void mouseReleased(java.awt.event.MouseEvent a) {
		                  if(a.getClickCount() == 2) {
		                	
		                	 user.setID(Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString()));
		                	 user.setNome(tabela.getValueAt(tabela.getSelectedRow(),1).toString());
		                	 user.setEmail(tabela.getValueAt(tabela.getSelectedRow(),2).toString());
		                	 user.setLogin(tabela.getValueAt(tabela.getSelectedRow(),3).toString());
		             	
		                	 user.setSalario(Double.parseDouble(tabela.getValueAt(tabela.getSelectedRow(), 4).toString()));
		 		             user.setCHoraria(Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 5).toString()));
		 		            
		                	 
		                	 //Condi��o para ver se � ADM ou N�o
		                	 int Ativo;
		                	 if(tabela.getValueAt(tabela.getSelectedRow(),6).toString().equals("Sim")) {
		                		 Ativo = 1;
		                	 }else {
		                		 Ativo = 0;
		                	 }
		                	 user.setAtivo(Ativo);
		                	 //FIM------------------------------
		                
		                	 //Condi��o para ver se � ADM ou N�o 
		                	 int Adm = 0;
		                	 
		                	
		                	 
		                	 if(tabela.getValueAt(tabela.getSelectedRow(),7).toString() == "Usuario" ) {
		                		 Adm = 0;
		                		 
		                	 }
		                	 if(tabela.getValueAt(tabela.getSelectedRow(),7).toString().equals("Administrador")){
		                		 Adm = 1;
		                	 }
		                	 if(tabela.getValueAt(tabela.getSelectedRow(),7).toString().equals("Lider")){
		                		 Adm = 2;
		                	 }
		                	 if(tabela.getValueAt(tabela.getSelectedRow(),7).toString().equals("Gestor")){
		                		 Adm = 3;
		                	 }
		                	 
		                	 user.setFuncao(Adm);
		                	 //FIM------------------------------
		                	 user.setDpto(tabela.getValueAt(tabela.getSelectedRow(), 8).toString());
		 		             user.setCC(tabela.getValueAt(tabela.getSelectedRow(),9).toString());         	
		                	 
		                	 //Condi��o para verificar se existe obs ou n�o
		                	 if(tabela.getValueAt(tabela.getSelectedRow(), 10) == null) {
		                		 user.setObs("");
		                	 }else {
                             user.setObs(tabela.getValueAt(tabela.getSelectedRow(), 10).toString());
		                	 }
		                	 //FIM------------------------------------------
		                	
		                	
		 		            
		                	 ListUsuarioTela.this.formularioPai.carregarDados();
		                  
		                }
		                
		            }
				});

			}else{
				JOptionPane.showMessageDialog(null,
						"Falha na conex�o ao banco");
			}	 
		}else{
			if(tabela != null){
				tabela.setVisible(false);
				sp.setVisible(false);
			}
			JOptionPane.showMessageDialog(null,"Sem registro de usuarios");
		}

	}catch(SQLException erro){
		JOptionPane.showMessageDialog(null,erro.toString());
	}
	finally{
		bd.close();
	}
}



}
