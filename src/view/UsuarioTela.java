package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

import com.myscrum.banco.BD;
import com.myscrum.controller.Controle;
import com.myscrum.model.Redimensionar;
import com.myscrum.model.Usuario;
import com.myscrum.model.UsuarioDAO;

public class UsuarioTela extends JFrame {
	
	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@SuppressWarnings("unused")
	public static void main(String[] args) {
		UsuarioTela frame = new UsuarioTela();
	}	
	
    public ListUsuarioTela tela;//criando objeto para a classe toda enchergar
    
	//Criando classe e metodo listener para implementar na classe listusuarioTela
	private class AdicionarUsuarioListener implements ActionListener {
        public UsuarioTela formularioPrincipal;//criando objeto para o formulario pai
     
        
        public AdicionarUsuarioListener(JFrame formularioPrincipal) {
            this.formularioPrincipal = (UsuarioTela) formularioPrincipal;
        }
        
        public void actionPerformed(ActionEvent event) {
             if(Controle.getListusuario() == false) {//Se já não foi aberto então abre o frame
             tela = new ListUsuarioTela(formularioPrincipal);  //colocando a tela dentro do objeto
             tela.setVisible(true);//mostrando a tela
             atualizarButton();//ativar botão atualziar e desativa botão cadastro
             
        	}else{//se já está dispara um aviso e joga a usuarioTela para frente
        	   JOptionPane.showMessageDialog(null, "Janela editar já está aberta", "Mensagem", 1);
        	   tela.toFront();
        	}
        }
    }

	
  public JButton cadastarButton;
  public JButton atualizarButton;
  private JLabel idLabel1;
  private JLabel idLabel;
  public JButton editarButton;
  private JTextField emailText;
  private JLabel emailLabel;
  private JLabel nomeLabel;
  private JCheckBox admCheckBox;
  private JCheckBox usuarioCheckBox;
  private JCheckBox ativoCheckBox;
  private JCheckBox bloqueadoCheckBox;
  private JTextField nomeText;
  private JPasswordField senhaText;
  private JLabel senhaLabel;
  private JComboBox<String> dptoComboBox;
  private JComboBox<String> cc_comboBox;
  private JLabel dptoLabel;
  private JTextArea obsCampo;
  private JLabel obsLabel;
  private String sql;
  private ButtonGroup bg;
  private ButtonGroup bg1;
  private JPasswordField password;
  private JLabel passwordLabel;
  private JTextField loginText;
  private JLabel loginLabel;
  Usuario variavel = new Usuario();
  UsuarioDAO metodo = new UsuarioDAO();
  JPanel panel = new JPanel();
  BD bd = new BD();
  Redimensionar rsize = new Redimensionar();
  private JTextField salarioText;
  private JLabel salarioLabel;
  private JTextField cHorarioText;
  private JCheckBox liderCheckBox;
  private JCheckBox gestorCheckBox;
  
  
public UsuarioTela() {
	  
	  //set iniciais
	  super("Cadastro usuario");//titulo do frame	
	  setSize(496, 699);//define o tamanho do frame
	  getContentPane().add(panel);//add panel ao frame
	  setResizable(false);
      panel.setLayout(null);//organiza os botões conforme setado no panel
	  
	  
      //construct preComponents
      String dpto = null;
      String[] dptoComboBoxItems = {"Selecione departamento"};
      String cc = null;
      String[] CcComboBoxItems = {"Selecione o CC"};

      //construct components
      idLabel1 = new JLabel("");
      idLabel = new JLabel("ID Usuário: ");
      cadastarButton = new JButton ("Cadastrar");
      atualizarButton = new JButton("Atualizar");
      editarButton = new JButton ("Editar");
      editarButton.addActionListener(new AdicionarUsuarioListener(this));
      emailText = new JTextField (5);
      emailLabel = new JLabel ("Email:");
      nomeLabel = new JLabel ("Nome:");
      gestorCheckBox = new JCheckBox("Gestor");
      liderCheckBox = new JCheckBox("Lider");
      admCheckBox = new JCheckBox ("ADM");
      usuarioCheckBox = new JCheckBox ("Usuário");
      ativoCheckBox = new JCheckBox("Ativo");
      bloqueadoCheckBox = new JCheckBox("Bloqueado");
      nomeText = new JTextField (5);
      senhaText = new JPasswordField();
      senhaLabel = new JLabel ("Senha:");
      salarioText = new JTextField(5);
      salarioLabel = new JLabel("Sal\u00E1rio:");
      dptoComboBox = new JComboBox<String> (dptoComboBoxItems);
      dptoLabel = new JLabel ("Departamento:");
      obsCampo = new JTextArea (5, 5);
      obsLabel = new JLabel ("Observação:");
      password = new JPasswordField();
      passwordLabel = new JLabel ("Confirme a Senha:");
      loginLabel = new JLabel ("Login: ");
      loginText = new JTextField();
     
     
      //add components
      panel.add (idLabel1);
      panel.add (idLabel);
      panel.add (cadastarButton);
      panel.add (atualizarButton);
      panel.add (editarButton);
      panel.add (emailText);
      panel.add (emailLabel);
      panel.add (nomeLabel);
      panel.add (admCheckBox);
      panel.add (usuarioCheckBox);
      panel.add (ativoCheckBox);
      panel.add (bloqueadoCheckBox);
      panel.add (nomeText);
      panel.add (senhaText);
      panel.add (senhaLabel);
      panel.add (salarioText);
      panel.add (salarioLabel);
      panel.add (dptoComboBox);
      panel.add (dptoLabel);
      panel.add (obsCampo);
      panel.add (obsLabel);
      panel.add (password);
      panel.add (passwordLabel);
      panel.add (loginLabel);
      panel.add (loginText);
      
      
      //set component bounds (only needed by Absolute Positioning)
      idLabel1.setBounds(80,1,80,20);
      idLabel.setBounds(5,1,80,20);
      cadastarButton.setBounds (117, 611, 120, 35);
      cadastarButton.setBackground(new Color(163, 184,204));//Fundo
      atualizarButton.setBounds (117, 611, 120, 35);
      atualizarButton.setBackground(new Color(163, 184,204));//Fundo 
      editarButton.setBounds (284, 611, 120, 35);
      editarButton.setBackground(new Color(163, 184,204));//Fundo
      emailText.setBounds (130, 81, 275, 30);
      emailText.setBackground(new Color(41,106,158));
      emailText.setForeground(Color.WHITE);
      emailLabel.setBounds (80, 81, 45, 30);
      nomeLabel.setBounds (80, 31, 45, 25);
      nomeText.setBounds (130, 31, 275, 30);
      nomeText.setBackground(new Color(41,106,158));
      nomeText.setForeground(Color.WHITE);
      loginLabel.setBounds(80, 131, 45, 25);
      loginText.setBounds(130, 131, 275, 30);
      loginText.setBackground(new Color(41,106,158));
      loginText.setForeground(Color.WHITE);
      admCheckBox.setBounds (261, 409, 80, 25);
      admCheckBox.setBackground(Color.WHITE);//setando cor do check
      usuarioCheckBox.setBounds (261, 434, 80, 25);
      usuarioCheckBox.setBackground(Color.WHITE);//setando cor do check
      ativoCheckBox.setBounds(261, 352, 55, 30);
      ativoCheckBox.setBackground(Color.WHITE);//setando cor do check
      bloqueadoCheckBox.setBounds(343, 355, 90, 25);
      bloqueadoCheckBox.setBackground(Color.WHITE);//setando cor do check
      senhaText.setBounds (130, 181, 275, 30);
      senhaText.setBackground(new Color(41,106,158));
      senhaText.setForeground(Color.WHITE);
      senhaLabel.setBounds (80, 186, 50, 25);
      salarioText.setBounds(130, 283, 100, 30);
      salarioText.setBackground(new Color(41,106,158));
      salarioText.setForeground(Color.WHITE);
      salarioLabel.setBounds(75, 291, 50, 14);
      dptoComboBox.setBounds (80, 357, 175, 25);
      dptoComboBox.setBackground(new Color(41,106,158));
      dptoComboBox.setForeground(Color.WHITE);
      dptoLabel.setBounds (80, 333, 100, 30);
      obsCampo.setBounds (81, 470, 370, 115);
      obsCampo.setBackground(new Color(41,106,158));
      obsCampo.setForeground(Color.WHITE);
      obsCampo.setLineWrap(true);//quebra de linha
      obsLabel.setBounds (81, 445, 80, 25);
      password.setBounds(130, 231, 275, 30);
      password.setBackground(new Color(41,106,158));
      password.setForeground(Color.WHITE);
      passwordLabel.setBounds(17, 234, 108, 25);
     
      
      //Setando Icone do Frame
      java.net.URL url = this.getClass().getResource("/com/myscrum/assets/setIcon1.png");
      Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
      this.setIconImage(iconeTitulo);
  	
      //propriedades do frame
      panel.setBackground(Color.WHITE);
      setLocationRelativeTo(null);//localidade do frame referente a tela(centraliza o frame)
      setVisible(true);//habilita visualização do frame
      
      //adicionando os chekbox ao buttongroup, caso um seja selecionado  o outro e desmarcado
      bg = new ButtonGroup();
      bg.add(admCheckBox);
      bg.add(usuarioCheckBox);
      bg.add(liderCheckBox);
      bg.add(gestorCheckBox);
      
      bg1 = new ButtonGroup();
      bg1.add(ativoCheckBox);
      bg1.add(bloqueadoCheckBox);
      
      cHorarioText = new JTextField();
      cHorarioText.setColumns(10);
      cHorarioText.setForeground(Color.WHITE);
      cHorarioText.setBackground(new Color(41,106,158));
      cHorarioText.setBounds(307, 283, 100, 30);
      panel.add(cHorarioText);
      
      JLabel cHorarioLabel = new JLabel("<html><center>Carga <br/>\r\nHor\u00E1rio:<html/>");
      cHorarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
      cHorarioLabel.setToolTipText("");
      cHorarioLabel.setBackground(Color.BLACK);
      cHorarioLabel.setBounds(245, 284, 61, 30);
      panel.add(cHorarioLabel);
      
      JLabel CentroDeCustoLabel = new JLabel("Centro de Custo:");
      CentroDeCustoLabel.setBounds(80, 406, 100, 14);
      panel.add(CentroDeCustoLabel);
      
      cc_comboBox = new JComboBox<String>(CcComboBoxItems);
      cc_comboBox.setForeground(Color.WHITE);
      cc_comboBox.setBackground(new Color(41, 106, 158));
      cc_comboBox.setBounds(80, 420, 175, 25);
      panel.add(cc_comboBox);
      
      
      liderCheckBox.setBackground(Color.WHITE);
      liderCheckBox.setBounds(343, 409, 90, 25);
      panel.add(liderCheckBox);
      
      
      gestorCheckBox.setBackground(Color.WHITE);
      gestorCheckBox.setBounds(343, 434, 90, 25);
      panel.add(gestorCheckBox);
      
      
      salarioText.addFocusListener(new FocusAdapter() {
		  public void focusLost(FocusEvent evt) {
			  ValidaNumero(salarioText);
			 
		  }	  
	});
      
      if(Controle.getListusuario() == true) {
    	  atualizarButton();
      }else{
    	  cadastrarButton();
      }
    
    //Preenchendo a combo box do DPTO
  	try{
		sql = "SELECT * FROM departamento";
		bd.getConnection();
		bd.st = bd.con.prepareStatement(sql);
		bd.rs = bd.st.executeQuery();
		while(bd.rs.next()){
			dpto = bd.rs.getString("departamento");
			dptoComboBox.addItem(dpto);
		}
    bd.close();
	}catch(SQLException erro){
		JOptionPane.showMessageDialog(null,erro.toString());{
		}
	  }
      //FIM
  	
  	//PREENCHENDO A COMBO BOX do centro de custo
  	try{
		sql = "SELECT * FROM centro_custo";
		bd.getConnection();
		bd.st = bd.con.prepareStatement(sql);
		bd.rs = bd.st.executeQuery();
		while(bd.rs.next()){
			cc = bd.rs.getString("centrocusto");
			cc_comboBox.addItem(cc);
		}
    bd.close();
	}catch(SQLException erro){
		JOptionPane.showMessageDialog(null,erro.toString());{
		}
	  }
      //FIM
  	
  	
  	
      //funções do minimizar, maximizar e fechar
      addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		  if(Controle.getListusuario() == true) {
		  tela.dispose();//fecha a list usuario
		  Controle.setListUsuario(false);// informa a control que a tela foi fechada
		  }
          dispose();//fecha o frame usuarioTela
          Controle.setUsuario(false);// informa a control que a tela foi fechada  
		}
	});
      	
      //botão cadastrar
      cadastarButton.addActionListener(new ActionListener() {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			int escolha;
			
			//verifica os campos se estão preenchidos
			if(cc_comboBox.getSelectedIndex() == 0 || dptoComboBox.getSelectedIndex() == 0 || nomeText.getText().equals("") ||
			emailText.getText().equals("") || senhaText.getText().equals("") || loginText.getText().equals("") 
			|| password.getText().equals("") || salarioText.getText().equals("") || cHorarioText.getText().equals("") ||
			admCheckBox.isSelected() == false && usuarioCheckBox.isSelected() == false && liderCheckBox.isSelected() == false && gestorCheckBox.isSelected() == false
			|| ativoCheckBox.isSelected() == false 
			&& bloqueadoCheckBox.isSelected() == false  ){
			
				JOptionPane.showMessageDialog(null, "Por favor preencha ou selecione todos os dados", "ERRO", 0);
			}else{
				
			if(password.getText().equals(senhaText.getText())) {
				escolha = JOptionPane.showConfirmDialog(null, "Deseja realmente cadastrar "+nomeText.getText()+"?", "Selecione uma opção", JOptionPane.YES_NO_OPTION);
	            if(escolha == JOptionPane.YES_OPTION) {
			variavel.setNome(nomeText.getText());
			variavel.setEmail(emailText.getText());
			variavel.setSenha(senhaText.getText());
			variavel.setLogin(loginText.getText());
			variavel.setSalario(Double.parseDouble(salarioText.getText()));
			variavel.setCHoraria(Integer.parseInt(cHorarioText.getText()));
			variavel.setAtivo(1);
			
			//verificando se é adm ou não
			if(usuarioCheckBox.isSelected()) {
				variavel.setFuncao(0);//Usuario
			}
			else if(admCheckBox.isSelected()) {
				variavel.setFuncao(1);//Adm
			}
			else if(liderCheckBox.isSelected()) {
				variavel.setFuncao(2);//Lider
			}
			else if(gestorCheckBox.isSelected()){
				variavel.setFuncao(3);//Gestor
			}
			//FIM
			
			variavel.setDpto(dptoComboBox.getSelectedItem().toString());
			variavel.setCC(cc_comboBox.getSelectedItem().toString());
			variavel.setObs(obsCampo.getText());
		
			if(metodo.cadastro() == true) {//se o cadastro der sucesso limpar os campos
				limpar();
			}
	      }
	    	}else {
	    		JOptionPane.showMessageDialog(null, "As senhas não conferem", "ERRO", 0);
	        }
         }
	   }
		
	
     });
      //botão atualizar
      atualizarButton.addActionListener(new ActionListener() {
		@SuppressWarnings("deprecation")
		
		public void actionPerformed(ActionEvent e) {
			int escolha;
			if(idLabel1.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Nenhum usuario selecionado para ser atualizado", "Atualização usuario", 0);
			}else {
			if(cc_comboBox.getSelectedIndex() == 0 || dptoComboBox.getSelectedIndex() == 0 || nomeText.getText().equals("") || emailText.getText().equals("") || loginText.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor preencha ou selecione todos os dados", "ERRO", 0);
				}else {
				if(password.getText().equals(senhaText.getText())) {
					escolha = JOptionPane.showConfirmDialog(null, "Deseja realmente atualizar "+nomeText.getText()+"?", "Selecione uma opção", JOptionPane.YES_NO_OPTION);
		            if(escolha == JOptionPane.YES_OPTION) {
				variavel.setID(Integer.parseInt(idLabel1.getText()));
				variavel.setNome(nomeText.getText());
				variavel.setEmail(emailText.getText());
				variavel.setSenha(senhaText.getText());
				variavel.setLogin(loginText.getText());
				variavel.setSalario(Double.parseDouble(salarioText.getText()));
				variavel.setCHoraria(Integer.parseInt(cHorarioText.getText()));
				
				//verificando se é adm ou não
				if(usuarioCheckBox.isSelected()) {
					variavel.setFuncao(0);//Usuario
				}
				else if(admCheckBox.isSelected()) {
					variavel.setFuncao(1);//Adm
				}
				else if(liderCheckBox.isSelected()) {
					variavel.setFuncao(2);//Lider
				}
				else if(gestorCheckBox.isSelected()){
					variavel.setFuncao(3);//Gestor
				}
				//FIM
				
				//verificando se está ativo ou não
				if(ativoCheckBox.isSelected()) {
					variavel.setAtivo(1);
				}else {
					variavel.setAtivo(0);
				}
				//FIM
				
				variavel.setDpto(dptoComboBox.getSelectedItem().toString());
				variavel.setCC(cc_comboBox.getSelectedItem().toString());
				variavel.setObs(obsCampo.getText());
			
				if(metodo.atualiza() == true) {//se a atulização der sucesso
					limpar();
				
				}
		      }
		    	}else {
		    		JOptionPane.showMessageDialog(null, "As senhas não conferem", "ERRO", 0);
		        }
	         }
		   }
		}
	});
}

//METODOS DA CLASSE 
      public void atualizarButton() {
	  atualizarButton.setVisible(true);
	  cadastarButton.setVisible(false);
	  nomeText.setEditable(false);
      }
      
      public void cadastrarButton() {
      atualizarButton.setVisible(false);
      cadastarButton.setVisible(true);
      nomeText.setEditable(true);
      }

      //METODO CARREGAR DADOS
public void carregarDados() {
		idLabel1.setText(""+variavel.getID());	   
      nomeText.setText(variavel.getNome());
      emailText.setText(variavel.getEmail());
      loginText.setText(variavel.getLogin());
      dptoComboBox.setSelectedItem(variavel.getDpto());
      cc_comboBox.setSelectedItem(variavel.getCC());
      obsCampo.setText(variavel.getObs());
      salarioText.setText(String.valueOf(variavel.getSalario()));
      cHorarioText.setText(String.valueOf(variavel.getCHoraria()));
      
      //verificando se é adm ou não para ativa checkbox
      if(variavel.getFuncao() == 0) {
    	  usuarioCheckBox.setSelected(true);
      }
      if(variavel.getFuncao() == 1) {
    	  admCheckBox.setSelected(true);
      }
      if(variavel.getFuncao() == 2) {
    	  liderCheckBox.setSelected(true);
      }
      if(variavel.getFuncao() == 3) {
    	  gestorCheckBox.setSelected(true);
      }
      //FIM
      
      //verificando se tá bloqueado ou não para ativar checkbox
      if(variavel.getAtivo() == 1) {
    	  ativoCheckBox.setSelected(true);
      }else {
    	  bloqueadoCheckBox.setSelected(true);
            }
      //FIM
      
      password.setText("");
      senhaText.setText("");
      
      toFront();
      }
 
// metodo que valida a utilização somente de numeros na text
public void ValidaNumero(JTextField Numero) {
	  @SuppressWarnings("unused")
	long valor;
	  if (Numero.getText().length() != 0){
		  try {
			  valor = Long.parseLong(Numero.getText());
		  }catch(NumberFormatException ex){
			  JOptionPane.showMessageDialog(null, "Esse Campo só aceita números" ,"Informação",JOptionPane.INFORMATION_MESSAGE);
			  Numero.grabFocus();
			  Numero.setText("");
	      }
	  	}
	  }
      //METODO LIMPAR VARIAVEIS
      public void limpar() {
       nomeText.setText("");
	   emailText.setText("");
	   loginText.setText("");
	   senhaText.setText("");
	   password.setText("");
	   dptoComboBox.setSelectedIndex(0);
	   cc_comboBox.setSelectedIndex(0);
	   obsCampo.setText("");
	   bg.clearSelection();
	   bg1.clearSelection();
	   idLabel1.setText("");
	   salarioText.setText("");
	   cHorarioText.setText("");
	   
      }
}
