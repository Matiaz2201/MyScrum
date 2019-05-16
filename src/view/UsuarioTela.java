package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

import com.myscrum.banco.BD;
import com.myscrum.banco.Banco;
import com.myscrum.controller.Controle;
import com.myscrum.model.Redimensionar;
import com.myscrum.model.TableGrade;
import com.myscrum.model.Usuario;
import com.myscrum.model.UsuarioDAO;
import javax.swing.border.LineBorder;

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

	public ListUsuarioTela tela;// criando objeto para a classe toda enchergar

	// Criando classe e metodo listener para implementar na classe listusuarioTela
	private class AdicionarUsuarioListener implements ActionListener {
		public UsuarioTela formularioPrincipal;// criando objeto para o formulario pai

		public AdicionarUsuarioListener(JFrame formularioPrincipal) {
			this.formularioPrincipal = (UsuarioTela) formularioPrincipal;
		}

		public void actionPerformed(ActionEvent event) {
			if (Controle.getListusuario() == false) {// Se já não foi aberto então abre o frame
				tela = new ListUsuarioTela(formularioPrincipal); // colocando a tela dentro do objeto
				tela.setVisible(true);// mostrando a tela
				atualizarButton();// ativar botão atualziar e desativa botão cadastro

			} else {// se já está dispara um aviso e joga a usuarioTela para frente
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
	private JComboBox dptoComboBox;
	private JComboBox cc_comboBox;
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
	private JTextField salarioText;
	private JLabel salarioLabel;
	private JTextField cHorarioText;
	private JCheckBox liderCheckBox;
	private JCheckBox gestorCheckBox;
	private JLabel funcaoQuadroLabel;
	private JLabel estadoLabel;
	private JScrollPane ccSP;
	private JTable ccTable;
	private JScrollPane dptoSP;
	private JTable dptoTable;
	private JScrollPane dptoVinculoSP;
	private JLabel quadroVinculoLabel;
	private JLabel funcaoLabel;
	private JLabel estadoQuadroLabel;

	Usuario variavel = new Usuario();
	UsuarioDAO metodo = new UsuarioDAO();
	JPanel panel = new JPanel();
	BD bd = new BD();
	Redimensionar rsize = new Redimensionar();
	private JLabel CentroDeCustoLabel;
	private JLabel cHorarioLabel;
	private JPanel ccVinculoPanel;
	private JLabel lblNewLabel;

	public UsuarioTela() {

		// set iniciais
		super("Cadastro usuario");// titulo do frame
		setSize(700, 590);// define o tamanho do frame
		getContentPane().add(panel);// add panel ao frame
		setResizable(false);
		panel.setLayout(null);// organiza os botões conforme setado no panel

		// construct preComponents

		String[] dptoComboBoxItems = { "Selecione departamento" };
		String[] CcComboBoxItems = { "Selecione o CC" };

		// construct components
		idLabel1 = new JLabel("");
		idLabel = new JLabel("ID Usuário: ");
		cadastarButton = new JButton("Cadastrar");
		atualizarButton = new JButton("Atualizar");
		editarButton = new JButton("Editar");
		editarButton.addActionListener(new AdicionarUsuarioListener(this));
		emailText = new JTextField(5);
		emailLabel = new JLabel("Email:");
		nomeLabel = new JLabel("Nome:");
		gestorCheckBox = new JCheckBox("Gestor");
		liderCheckBox = new JCheckBox("Lider");
		admCheckBox = new JCheckBox("ADM");
		usuarioCheckBox = new JCheckBox("Usuário");
		ativoCheckBox = new JCheckBox("Ativo");
		bloqueadoCheckBox = new JCheckBox("Bloqueado");
		nomeText = new JTextField(5);
		senhaText = new JPasswordField();
		senhaLabel = new JLabel("Senha:");
		salarioText = new JTextField(5);
		salarioLabel = new JLabel("Sal\u00E1rio:");
		dptoComboBox = new JComboBox(dptoComboBoxItems);
		dptoLabel = new JLabel("Departamento:");
		obsCampo = new JTextArea(5, 5);
		obsLabel = new JLabel("Observação:");
		password = new JPasswordField();
		passwordLabel = new JLabel("Confirme a Senha:");
		loginLabel = new JLabel("Login: ");
		loginText = new JTextField();

		// add components
		panel.add(idLabel1);
		panel.add(idLabel);
		panel.add(cadastarButton);
		panel.add(atualizarButton);
		panel.add(editarButton);
		panel.add(emailText);
		panel.add(emailLabel);
		panel.add(nomeLabel);
		panel.add(admCheckBox);
		panel.add(usuarioCheckBox);
		panel.add(ativoCheckBox);
		panel.add(bloqueadoCheckBox);
		panel.add(nomeText);
		panel.add(senhaText);
		panel.add(senhaLabel);
		panel.add(salarioText);
		panel.add(salarioLabel);
		panel.add(dptoComboBox);
		panel.add(dptoLabel);
		panel.add(obsCampo);
		panel.add(obsLabel);
		panel.add(password);
		panel.add(passwordLabel);
		panel.add(loginLabel);
		panel.add(loginText);

		// set component bounds (only needed by Absolute Positioning)
		idLabel1.setBounds(80, 1, 80, 20);
		idLabel.setBounds(5, 1, 80, 20);
		cadastarButton.setBounds(442, 246, 120, 35);
		cadastarButton.setBackground(new Color(163, 184, 204));// Fundo
		atualizarButton.setBounds(442, 246, 120, 35);
		atualizarButton.setBackground(new Color(163, 184, 204));// Fundo
		editarButton.setBounds(568, 246, 120, 35);
		editarButton.setBackground(new Color(163, 184, 204));// Fundo
		emailText.setBounds(52, 70, 200, 30);
		emailText.setBackground(new Color(41, 106, 158));
		emailText.setForeground(Color.WHITE);
		emailLabel.setBounds(5, 70, 45, 30);
		nomeLabel.setBounds(5, 34, 45, 25);
		nomeText.setBounds(52, 28, 200, 30);
		nomeText.setBackground(new Color(41, 106, 158));
		nomeText.setForeground(Color.WHITE);
		loginLabel.setBounds(5, 111, 45, 25);
		loginText.setBounds(52, 112, 200, 30);
		loginText.setBackground(new Color(41, 106, 158));
		loginText.setForeground(Color.WHITE);
		admCheckBox.setBounds(585, 110, 70, 25);
		admCheckBox.setBackground(Color.WHITE);// setando cor do check
		usuarioCheckBox.setBounds(585, 170, 85, 25);
		usuarioCheckBox.setBackground(Color.WHITE);// setando cor do check
		ativoCheckBox.setBounds(585, 35, 55, 25);
		ativoCheckBox.setBackground(Color.WHITE);// setando cor do check
		bloqueadoCheckBox.setBounds(585, 58, 100, 20);
		bloqueadoCheckBox.setBackground(Color.WHITE);// setando cor do check
		senhaText.setBounds(360, 31, 130, 30);
		senhaText.setBackground(new Color(41, 106, 158));
		senhaText.setForeground(Color.WHITE);
		senhaLabel.setBounds(254, 34, 50, 25);
		salarioText.setBounds(52, 148, 70, 30);
		salarioText.setBackground(new Color(41, 106, 158));
		salarioText.setForeground(Color.WHITE);
		salarioLabel.setBounds(5, 156, 50, 14);
		dptoComboBox.setBounds(360, 110, 175, 25);
		dptoComboBox.setBackground(new Color(41, 106, 158));
		dptoComboBox.setForeground(Color.WHITE);
		dptoLabel.setBounds(254, 110, 100, 20);
		obsCampo.setBounds(5, 205, 247, 76);
		obsCampo.setBackground(new Color(41, 106, 158));
		obsCampo.setForeground(Color.WHITE);
		obsCampo.setLineWrap(true);// quebra de linha
		obsLabel.setBounds(5, 185, 80, 25);
		password.setBounds(360, 70, 130, 30);
		password.setBackground(new Color(41, 106, 158));
		password.setForeground(Color.WHITE);
		passwordLabel.setBounds(254, 73, 108, 25);

		// Setando Icone do Frame
		java.net.URL url = this.getClass().getResource("/com/myscrum/assets/setIcon1.png");
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(iconeTitulo);

		// propriedades do frame
		panel.setBackground(Color.WHITE);
		setLocationRelativeTo(null);// localidade do frame referente a tela(centraliza o frame)
		setVisible(true);// habilita visualização do frame

		// adicionando os chekbox ao buttongroup, caso um seja selecionado o outro e
		// desmarcado
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
		cHorarioText.setBackground(new Color(41, 106, 158));
		cHorarioText.setBounds(197, 148, 55, 30);
		panel.add(cHorarioText);

		cHorarioLabel = new JLabel("<html><center>Carga <br/>\r\nHor\u00E1rio:<html/>");
		cHorarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cHorarioLabel.setToolTipText("");
		cHorarioLabel.setBackground(Color.BLACK);
		cHorarioLabel.setBounds(134, 148, 61, 30);
		panel.add(cHorarioLabel);

		CentroDeCustoLabel = new JLabel("Centro de Custo:");
		CentroDeCustoLabel.setBounds(254, 148, 100, 20);
		panel.add(CentroDeCustoLabel);

		cc_comboBox = new JComboBox(CcComboBoxItems);
		cc_comboBox.setForeground(Color.WHITE);
		cc_comboBox.setBackground(new Color(41, 106, 158));
		cc_comboBox.setBounds(360, 148, 175, 25);
		panel.add(cc_comboBox);

		liderCheckBox.setBackground(Color.WHITE);
		liderCheckBox.setBounds(585, 130, 85, 25);
		panel.add(liderCheckBox);

		gestorCheckBox.setBackground(Color.WHITE);
		gestorCheckBox.setBounds(585, 150, 85, 25);
		panel.add(gestorCheckBox);

		estadoQuadroLabel = new JLabel("");
		estadoQuadroLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		estadoQuadroLabel.setBounds(583, 34, 105, 46);
		panel.add(estadoQuadroLabel);

		funcaoQuadroLabel = new JLabel("");
		funcaoQuadroLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		funcaoQuadroLabel.setBounds(583, 105, 105, 97);
		panel.add(funcaoQuadroLabel);

		funcaoLabel = new JLabel("Fun\u00E7\u00E3o");
		funcaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		funcaoLabel.setBounds(583, 87, 105, 16);
		panel.add(funcaoLabel);

		estadoLabel = new JLabel("Estado");
		estadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		estadoLabel.setBounds(583, 17, 105, 16);
		panel.add(estadoLabel);

		dptoVinculoSP = new JScrollPane();
		dptoVinculoSP.setBounds(530, 370, 155, 175);
		panel.add(dptoVinculoSP);

		ccVinculoPanel = new JPanel();
		ccVinculoPanel.setBounds(170, 370, 155, 175);
		panel.add(ccVinculoPanel);

		quadroVinculoLabel = new JLabel("Vinculos com Centro de custos e departamentos");
		quadroVinculoLabel.setVerticalAlignment(SwingConstants.TOP);
		quadroVinculoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		quadroVinculoLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		quadroVinculoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quadroVinculoLabel.setBounds(5, 318, 683, 237);
		panel.add(quadroVinculoLabel);

		salarioText.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				ValidaNumero(salarioText);

			}
		});

		if (Controle.getListusuario() == true) {
			atualizarButton();
		} else {
			cadastrarButton();
		}

		// funções do minimizar, maximizar e fechar
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (Controle.getListusuario() == true) {
					tela.dispose();// fecha a list usuario
					Controle.setListUsuario(false);// informa a control que a tela foi fechada
				}
				dispose();// fecha o frame usuarioTela
				Controle.setUsuario(false);// informa a control que a tela foi fechada
			}
		});

		// botão cadastrar
		cadastarButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				int escolha;

				// verifica os campos se estão preenchidos
				if (cc_comboBox.getSelectedIndex() == 0 || dptoComboBox.getSelectedIndex() == 0
						|| nomeText.getText().equals("") || emailText.getText().equals("")
						|| senhaText.getText().equals("") || loginText.getText().equals("")
						|| password.getText().equals("") || salarioText.getText().equals("")
						|| cHorarioText.getText().equals("")
						|| admCheckBox.isSelected() == false && usuarioCheckBox.isSelected() == false
								&& liderCheckBox.isSelected() == false && gestorCheckBox.isSelected() == false
						|| ativoCheckBox.isSelected() == false && bloqueadoCheckBox.isSelected() == false) {

					JOptionPane.showMessageDialog(null, "Por favor preencha ou selecione todos os dados", "ERRO", 0);
				} else {

					if (password.getText().equals(senhaText.getText())) {
						escolha = JOptionPane.showConfirmDialog(null,
								"Deseja realmente cadastrar " + nomeText.getText() + "?", "Selecione uma opção",
								JOptionPane.YES_NO_OPTION);
						if (escolha == JOptionPane.YES_OPTION) {
							variavel.setNome(nomeText.getText());
							variavel.setEmail(emailText.getText());
							variavel.setSenha(senhaText.getText());
							variavel.setLogin(loginText.getText());
							variavel.setSalario(Double.parseDouble(salarioText.getText()));
							variavel.setCHoraria(Integer.parseInt(cHorarioText.getText()));
							variavel.setAtivo(1);

							// verificando se é adm ou não
							if (usuarioCheckBox.isSelected()) {
								variavel.setFuncao(0);// Usuario
							} else if (admCheckBox.isSelected()) {
								variavel.setFuncao(1);// Adm
							} else if (liderCheckBox.isSelected()) {
								variavel.setFuncao(2);// Lider
							} else if (gestorCheckBox.isSelected()) {
								variavel.setFuncao(3);// Gestor
							}
							// FIM

							variavel.setDpto(dptoComboBox.getSelectedItem().toString());
							variavel.setCC(cc_comboBox.getSelectedItem().toString());
							variavel.setObs(obsCampo.getText());

							if (metodo.cadastro() == true) {// se o cadastro der sucesso limpar os campos
								limpar();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "As senhas não conferem", "ERRO", 0);
					}
				}
			}

		});
		// botão atualizar
		atualizarButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")

			public void actionPerformed(ActionEvent e) {
				int escolha;
				if (idLabel1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhum usuario selecionado para ser atualizado",
							"Atualização usuario", 0);
				} else {
					if (cc_comboBox.getSelectedIndex() == 0 || dptoComboBox.getSelectedIndex() == 0
							|| nomeText.getText().equals("") || emailText.getText().equals("")
							|| loginText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor preencha ou selecione todos os dados", "ERRO",
								0);
					} else {
						if (password.getText().equals(senhaText.getText())) {
							escolha = JOptionPane.showConfirmDialog(null,
									"Deseja realmente atualizar " + nomeText.getText() + "?", "Selecione uma opção",
									JOptionPane.YES_NO_OPTION);
							if (escolha == JOptionPane.YES_OPTION) {
								variavel.setID(Integer.parseInt(idLabel1.getText()));
								variavel.setNome(nomeText.getText());
								variavel.setEmail(emailText.getText());
								variavel.setSenha(senhaText.getText());
								variavel.setLogin(loginText.getText());
								variavel.setSalario(Double.parseDouble(salarioText.getText()));
								variavel.setCHoraria(Integer.parseInt(cHorarioText.getText()));

								// verificando se é adm ou não
								if (usuarioCheckBox.isSelected()) {
									variavel.setFuncao(0);// Usuario
								} else if (admCheckBox.isSelected()) {
									variavel.setFuncao(1);// Adm
								} else if (liderCheckBox.isSelected()) {
									variavel.setFuncao(2);// Lider
								} else if (gestorCheckBox.isSelected()) {
									variavel.setFuncao(3);// Gestor
								}
								// FIM

								// verificando se está ativo ou não
								if (ativoCheckBox.isSelected()) {
									variavel.setAtivo(1);
								} else {
									variavel.setAtivo(0);
								}
								// FIM

								variavel.setDpto(dptoComboBox.getSelectedItem().toString());
								variavel.setCC(cc_comboBox.getSelectedItem().toString());
								variavel.setObs(obsCampo.getText());

								if (metodo.atualiza() == true) {// se a atulização der sucesso
									limpar();

								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "As senhas não conferem", "ERRO", 0);
						}
					}
				}
			}
		});

		preencherComboBox();
		listarCCTable();
		listarDPTOTable();
	}

	/**
	 * METODOS DA CLASSE
	 */

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

	// METODO CARREGAR DADOS
	public void carregarDados() {
		idLabel1.setText("" + variavel.getID());
		nomeText.setText(variavel.getNome());
		emailText.setText(variavel.getEmail());
		loginText.setText(variavel.getLogin());
		dptoComboBox.setSelectedItem(variavel.getDpto());
		cc_comboBox.setSelectedItem(variavel.getCC());
		obsCampo.setText(variavel.getObs());
		salarioText.setText(String.valueOf(variavel.getSalario()));
		cHorarioText.setText(String.valueOf(variavel.getCHoraria()));

		// verificando se é adm ou não para ativa checkbox
		if (variavel.getFuncao() == 0) {
			usuarioCheckBox.setSelected(true);
		}
		if (variavel.getFuncao() == 1) {
			admCheckBox.setSelected(true);
		}
		if (variavel.getFuncao() == 2) {
			liderCheckBox.setSelected(true);
		}
		if (variavel.getFuncao() == 3) {
			gestorCheckBox.setSelected(true);
		}
		// FIM

		// verificando se tá bloqueado ou não para ativar checkbox
		if (variavel.getAtivo() == 1) {
			ativoCheckBox.setSelected(true);
		} else {
			bloqueadoCheckBox.setSelected(true);
		}
		// FIM

		password.setText("");
		senhaText.setText("");

		toFront();
	}

	// metodo que valida a utilização somente de numeros na text
	public void ValidaNumero(JTextField Numero) {
		@SuppressWarnings("unused")
		long valor;
		if (Numero.getText().length() != 0) {
			try {
				valor = Long.parseLong(Numero.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Esse Campo só aceita números", "Informação",
						JOptionPane.INFORMATION_MESSAGE);
				Numero.grabFocus();
				Numero.setText("");
			}
		}
	}

	// METODO LIMPAR VARIAVEIS
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

	// Metodo para carregar comboBox
	public void preencherComboBox() {
		// Preenchendo a combo box do DPTO
		try {
			String dpto = "";
			sql = "SELECT * FROM departamento";
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			while (bd.rs.next()) {
				dpto = bd.rs.getString("departamento");
				dptoComboBox.addItem(dpto);
			}
			bd.close();
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());
			{
			}
		}
		// FIM

		// PREENCHENDO A COMBO BOX do centro de custo
		try {
			String cc = "";
			sql = "SELECT * FROM centro_custo";
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			while (bd.rs.next()) {
				cc = bd.rs.getString("centrocusto");
				cc_comboBox.addItem(cc);
			}
			bd.close();
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());
			{
			}
		}
		// FIM
	}

	// Metodo para listar tabela com centro de custos
	public void listarCCTable() {
		String sqlcc = "SELECT centrocusto FROM centro_custo";

		if (Banco.conexao()) {
			try {
				Banco.st = Banco.con.prepareStatement(sqlcc);
				Banco.rs = Banco.st.executeQuery();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}

			if (ccTable != null) {// se existir outra tabela montada apaga
				ccTable.setVisible(false);
				ccTable = null;
				ccSP.setVisible(false);
				ccSP = null;
			}

			Vector<String> cabecalhoPersonalizado = new Vector<>();

			cabecalhoPersonalizado.addElement("Centro de custos");// 0

			ccTable = TableGrade.getTable(sqlcc, cabecalhoPersonalizado);

			ccTable.addMouseListener(new MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent a) {
					if (a.getClickCount() == 2) {

						String cc = ccTable.getValueAt(ccTable.getSelectedRow(), 0).toString();
						if (ccVinculoPanel.getComponentCount() == 0) {
							JLabel ccVinculado = new JLabel(cc);
							ccVinculado.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
							ccVinculado.setName(cc);
							ccVinculado.addMouseListener(new MouseAdapter() {
								public void mouseReleased(java.awt.event.MouseEvent a) {
									if (a.getClickCount() == 2) {
										ccVinculoPanel.remove(ccVinculado);
										ccVinculoPanel.updateUI();
									}
								}
							});
							ccVinculoPanel.add(ccVinculado);
							ccVinculoPanel.updateUI();
						} else {
							for (int x = 0; x < ccVinculoPanel.getComponentCount(); x++) {
								if (ccVinculoPanel.getComponent(x).getName() == cc) {
									JOptionPane.showMessageDialog(null, "Usuario já vinculado a este centro de custo");
								} else {
									JLabel ccVinculado = new JLabel(cc);
									ccVinculado.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
									ccVinculado.setName(cc);
									ccVinculado.addMouseListener(new MouseAdapter() {
										public void mouseReleased(java.awt.event.MouseEvent a) {
											if (a.getClickCount() == 2) {
												ccVinculoPanel.remove(ccVinculado);
												ccVinculoPanel.updateUI();
											}
										}
									});
									ccVinculoPanel.add(ccVinculado);
									ccVinculoPanel.updateUI();
								}
							}
						}
					}
				}
			});

			// adiciona Scroll ao frame
			ccSP = new JScrollPane(ccTable);

			ccSP.setBounds(10, 370, 155, 175);
			panel.add(ccSP);
			panel.updateUI(); // atualiza tela

			ccTable.setEditingRow(1);
			ccTable.setEditingColumn(1);
			ccTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		}

	}

	// Metodo para listar tabela com departamentos
	public void listarDPTOTable() {
		String sqldpto = "SELECT departamento FROM departamento";

		if (Banco.conexao()) {
			try {
				Banco.st = Banco.con.prepareStatement(sqldpto);
				Banco.rs = Banco.st.executeQuery();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}

			if (dptoTable != null) {// se existir outra tabela montada apaga
				dptoTable.setVisible(false);
				dptoTable = null;
				dptoSP.setVisible(false);
				dptoSP = null;
			}

			Vector<String> cabecalhoPersonalizado = new Vector<>();

			cabecalhoPersonalizado.addElement("Departamentos");// 0

			dptoTable = TableGrade.getTable(sqldpto, cabecalhoPersonalizado);

			// adiciona Scroll ao frame
			dptoSP = new JScrollPane(dptoTable);

			dptoSP.setBounds(370, 370, 155, 175);
			panel.add(dptoSP);
			panel.updateUI(); // atualiza tela

			dptoTable.setEditingRow(1);
			dptoTable.setEditingColumn(1);
			dptoTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		}
	}
}
