package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.myscrum.controller.Controle;
import com.myscrum.model.JGradientPanel;
import com.myscrum.model.VerticalLabelUI;
import com.myscrum.model.KambanDAO;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Kamban extends JPanel {
	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public JLabel iniciadoLabel;
	public JTextArea descText;
	public JScrollPane scrollPane;
	public JTextField inicioText;
	public JTextField fimText;
	public JTextField ccText;
	public JTextField respText;
	public JTextField autText;
	public JTextField execText;
	public JLabel respLabel;
	public JLabel execLabel;
	public JLabel ccLabel;
	public JLabel autoridadeLabel;
	public JLabel duracaoLabel;
	public JLabel statusLabel;
	public JTextField atrasadoText;
	public JTextField pendenteText;
	public JTextField pred1Text;
	public JTextField pred3Text;
	public JTextField pred2Text;
	public JLabel predLabel;
	public JLabel tamanhoLabel;
	public JLabel prioridadeLabel;
	public JLabel inicioLabel;
	public JLabel fimLabel;
	public JTextArea statText;
	public JScrollPane scrollPane_2;
	public JLabel diasatrasadosLabel;

	public JGradientPanel BarraLateral;

	public JTextField idText;
	public JTextField duracaoText;
	public JTextField prioridadeText;
	public JTextField tamanhoText;
	public JLabel dpto;
	public JTextField pontuacaoText;
	public JTextField pesoText;
	public JTextField porcentoText;

	Controle control = new Controle();
	public JLabel anexoLabel;
	
	TarefaEditTela tela;

	/**
	 * Create the panel.
	 */

	
		
	public Kamban() {// tamanho do grafico 243x254
		setSize(243, 254);
		setBackground(new Color(255, 255, 255));
		setBorder(BorderFactory.createTitledBorder(""));
		setLayout(null);

		duracaoLabel = new JLabel("Dura\u00E7\u00E3o");
		duracaoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		duracaoLabel.setForeground(Color.BLACK);
		duracaoLabel.setBounds(191, 56, 46, 14);
		add(duracaoLabel);
		
		anexoLabel = new JLabel("");
		anexoLabel.setVisible(false);
		anexoLabel.setIcon(new ImageIcon(Kamban.class.getResource("/com/myscrum/assets/clips.png")));
		anexoLabel.setBounds(212, 197, 25, 57);
		add(anexoLabel);

		inicioText = new JTextField();
		inicioText.setFont(new Font("Arial", Font.PLAIN, 11));
		inicioText.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		inicioText.setBackground(new Color(255, 255, 255));
		inicioText.setEditable(false);
		inicioText.setBounds(40, 70, 59, 14);
		add(inicioText);
		inicioText.setColumns(10);

		fimText = new JTextField();
		fimText.setFont(new Font("Arial", Font.PLAIN, 11));
		fimText.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		fimText.setBackground(new Color(255, 255, 255));
		fimText.setEditable(false);
		fimText.setBounds(109, 70, 59, 14);
		add(fimText);
		fimText.setColumns(10);

		ccText = new JTextField();
		ccText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				Controle.telaKamban.ccComboBox.setSelectedItem(ccText.getText());//Ao clicar na text de centro de custo o nome do cc vai para o filtro
			}
		  }
		});
		ccText.setBorder(new LineBorder(Color.BLACK, 1, true));
		ccText.setBackground(new Color(255, 255, 255));
		ccText.setEditable(false);
		ccText.setBounds(70, 158, 69, 20);
		add(ccText);

		respText = new JTextField();
		respText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				Controle.telaKamban.ExeRespAutComboBox.setSelectedItem(respText.getText());//Ao clicar na text de centro de custo o nome do cc vai para o filtro
			}
		  }
		});
		respText.setFont(new Font("Arial", Font.PLAIN, 11));
		respText.setBorder(new LineBorder(Color.BLACK, 1, true));
		respText.setBackground(new Color(255, 255, 255));
		respText.setEditable(false);
		respText.setBounds(70, 186, 69, 20);
		add(respText);
		respText.setColumns(10);

		autText = new JTextField();
		autText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				Controle.telaKamban.ExeRespAutComboBox.setSelectedItem(autText.getText());//Ao clicar na text de centro de custo o nome do cc vai para o filtro
			}
		  }
		});
		autText.setFont(new Font("Arial", Font.PLAIN, 11));
		autText.setBorder(new LineBorder(Color.BLACK, 1, true));
		autText.setBackground(new Color(255, 255, 255));
		autText.setEditable(false);
		autText.setBounds(168, 186, 69, 20);
		add(autText);
		autText.setColumns(10);

		execText = new JTextField();
		execText.setFont(new Font("Arial", Font.PLAIN, 11));
		execText.setBorder(new LineBorder(Color.BLACK, 1, true));
		execText.setBackground(new Color(255, 255, 255));
		execText.setEditable(false);
		execText.setBounds(70, 213, 167, 25);
		add(execText);
		execText.setColumns(10);

		BarraLateral = new JGradientPanel(Color.WHITE, new Color(41, 106, 158));
		BarraLateral.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
		BarraLateral.setBounds(0, 0, 35, 254);
		add(BarraLateral);
		BarraLateral.setLayout(null);

		idText = new JTextField();
		idText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					tela = new TarefaEditTela(Controle.telatarefa);//Instancia a tela
					tela.setVisible(true);// Torna visivel
					tela.atualizarButton();// Habilita botão de atualizar
					tela.setTitle(descText.getText());// Seta o titulo da tela com a descrição

					KambanDAO.SelectTarefa(Integer.parseInt(idText.getText()));// Executa o metodo para select da tarefa
																				// baseado no ID
					
					tela.carregarDados();// Carrega os dados na tela
					tela.ChamadaPeloKamban();// Informar a tela que ela foi chamada atraves do kamban
				}
			}

			public void mouseReleased(MouseEvent a) {
				if (a.getClickCount() == 1 && a.getModifiersEx() == InputEvent.ALT_DOWN_MASK) {
					try {
						Controle.telaKamban.predecessora.setText((idText.getText()));
						Controle.telaKamban.toBack();
					} catch (Exception e) {

					}

				}
			}
		});
		idText.setHorizontalAlignment(SwingConstants.CENTER);
		idText.setBorder(new LineBorder(new Color(41, 106, 158), 2));
		idText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		idText.setBackground(new Color(255, 255, 255));
		idText.setEditable(false);
		idText.setBounds(0, 0, 35, 20);
		BarraLateral.add(idText);
		idText.setColumns(10);

		prioridadeText = new JTextField();
		prioridadeText.setBackground(Color.WHITE);
		prioridadeText.setEditable(false);
		prioridadeText.setBorder(null);
		prioridadeText.setHorizontalAlignment(SwingConstants.CENTER);
		prioridadeText.setBounds(5, 42, 24, 20);
		BarraLateral.add(prioridadeText);
		prioridadeText.setColumns(10);

		tamanhoText = new JTextField();
		tamanhoText.setBackground(Color.WHITE);
		tamanhoText.setEditable(false);
		tamanhoText.setBorder(null);
		tamanhoText.setHorizontalAlignment(SwingConstants.CENTER);
		tamanhoText.setBounds(5, 80, 24, 20);
		BarraLateral.add(tamanhoText);
		tamanhoText.setColumns(10);
		
		dpto = new JLabel("Informática");
		dpto.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				Controle.telaKamban.dptoComboBox.setSelectedItem(dpto.getText());//Ao clicar na text de centro de custo o nome do cc vai para o filtro
			}
		  }
		});
		dpto.setHorizontalAlignment(SwingConstants.CENTER);
		dpto.setFont(new Font("Arial", Font.BOLD, 11));
		dpto.setBounds(0,101,35,90);
		dpto.setUI(new VerticalLabelUI(true));
		BarraLateral.add(dpto);
		
		porcentoText = new JTextField();
		porcentoText.setBackground(Color.WHITE);
		porcentoText.setEditable(false);
		porcentoText.setHorizontalAlignment(SwingConstants.CENTER);
		porcentoText.setBounds(0, 190, 35, 20);
		BarraLateral.add(porcentoText);
		porcentoText.setColumns(10);

		pesoText = new JTextField();
		pesoText.setBackground(Color.WHITE);
		pesoText.setEditable(false);
		pesoText.setHorizontalAlignment(SwingConstants.CENTER);
		pesoText.setBounds(0, 210, 35, 20);
		BarraLateral.add(pesoText);
		pesoText.setColumns(10);

		pontuacaoText = new JTextField();
		pontuacaoText.setHorizontalAlignment(SwingConstants.CENTER);
		pontuacaoText.setBackground(Color.WHITE);
		pontuacaoText.setEditable(false);
		pontuacaoText.setColumns(10);
		pontuacaoText.setBounds(0, 230, 35, 20);
		BarraLateral.add(pontuacaoText);

		tamanhoLabel = new JLabel("Tam.");
		tamanhoLabel.setFont(new Font("Arial", Font.BOLD, 11));
		tamanhoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tamanhoLabel.setBounds(0, 65, 35, 14);
		BarraLateral.add(tamanhoLabel);

		prioridadeLabel = new JLabel("Priori.");
		prioridadeLabel.setFont(new Font("Arial", Font.BOLD, 11));
		prioridadeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		prioridadeLabel.setBounds(0, 31, 35, 14);
		BarraLateral.add(prioridadeLabel);

		respLabel = new JLabel("");
		respLabel.setIcon(new ImageIcon(Kamban.class.getResource("/com/myscrum/assets/icone_resp.jpg")));
		respLabel.setHorizontalAlignment(SwingConstants.CENTER);
		respLabel.setBounds(40, 181, 30, 25);
		add(respLabel);

		execLabel = new JLabel("");
		execLabel.setIcon(new ImageIcon(Kamban.class.getResource("/com/myscrum/assets/icone_exec.jpg")));
		execLabel.setHorizontalAlignment(SwingConstants.CENTER);
		execLabel.setBounds(40, 210, 30, 25);
		add(execLabel);

		ccLabel = new JLabel("");
		ccLabel.setIcon(new ImageIcon(Kamban.class.getResource("/com/myscrum/assets/icone_cc.jpg")));
		ccLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ccLabel.setBounds(40, 155, 30, 25);
		add(ccLabel);

		autoridadeLabel = new JLabel("");
		autoridadeLabel.setIcon(new ImageIcon(Kamban.class.getResource("/com/myscrum/assets/icone_autoridade.jpg")));
		autoridadeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		autoridadeLabel.setBounds(140, 181, 25, 25);
		add(autoridadeLabel);

		inicioLabel = new JLabel("Inicio");
		inicioLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		inicioLabel.setForeground(Color.BLACK);
		inicioLabel.setBounds(40, 56, 54, 14);
		add(inicioLabel);

		fimLabel = new JLabel("Termino");
		fimLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		fimLabel.setForeground(Color.BLACK);
		fimLabel.setBounds(109, 56, 46, 14);
		add(fimLabel);

		duracaoText = new JTextField();
		duracaoText.setHorizontalAlignment(SwingConstants.CENTER);
		duracaoText.setBackground(Color.WHITE);
		duracaoText.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		duracaoText.setBounds(191, 69, 46, 14);
		add(duracaoText);
		duracaoText.setEditable(false);
		duracaoText.setFont(new Font("Arial", Font.PLAIN, 11));

		statusLabel = new JLabel("Status");
		statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		statusLabel.setBounds(40, 85, 46, 14);
		add(statusLabel);

		statText = new JTextArea();
		statText.setBackground(Color.WHITE);
		statText.setEditable(false);
		statText.setFont(new Font("Arial", Font.PLAIN, 11));
		statText.setLineWrap(true);
		statText.setBounds(40, 100, 195, 145);
		add(statText);

		scrollPane_2 = new JScrollPane(statText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBorder(new LineBorder(Color.BLACK, 1, true));
		scrollPane_2.setBounds(40, 100, 197, 51);
		add(scrollPane_2);

		iniciadoLabel = new JLabel("");
		iniciadoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		iniciadoLabel.setBounds(40, 2, 155, 14);
		add(iniciadoLabel);

		atrasadoText = new JTextField();
		atrasadoText.setFont(new Font("Arial", Font.BOLD, 9));
		atrasadoText.setHorizontalAlignment(SwingConstants.CENTER);
		atrasadoText.setBorder(null);
		atrasadoText.setBackground(Color.WHITE);
		atrasadoText.setBounds(189, 0, 54, 17);
		add(atrasadoText);
		atrasadoText.setColumns(10);

		diasatrasadosLabel = new JLabel("");
		diasatrasadosLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		diasatrasadosLabel.setBounds(40, 238, 116, 14);
		add(diasatrasadosLabel);

		pendenteText = new JTextField();
		pendenteText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				Controle.telaKamban.dptoComboBox.setSelectedItem(pendenteText.getText());//Ao clicar na text de centro de custo o nome do cc vai para o filtro
			}
		  }
		});
		pendenteText.setFont(new Font("SansSerif", Font.PLAIN, 11));
		pendenteText.setEditable(false);
		pendenteText.setBorder(null);
		pendenteText.setBackground(Color.WHITE);
		pendenteText.setHorizontalAlignment(SwingConstants.CENTER);
		pendenteText.setBounds(147, 238, 90, 14);
		add(pendenteText);
		pendenteText.setColumns(10);

		pred1Text = new JTextField();
		pred1Text.setToolTipText("Descrição da tarefa vai aqui");
		pred1Text.setHorizontalAlignment(SwingConstants.CENTER);
		pred1Text.setFont(new Font("Arial", Font.PLAIN, 10));
		pred1Text.setEditable(false);
		pred1Text.setColumns(10);
		pred1Text.setBorder(null);
		pred1Text.setBackground(Color.WHITE);
		pred1Text.setBounds(202, 154, 35, 10);
		add(pred1Text);

		pred3Text = new JTextField();
		pred3Text.setHorizontalAlignment(SwingConstants.CENTER);
		pred3Text.setFont(new Font("Arial", Font.PLAIN, 10));
		pred3Text.setEditable(false);
		pred3Text.setColumns(10);
		pred3Text.setBorder(null);
		pred3Text.setBackground(Color.WHITE);
		pred3Text.setBounds(202, 176, 35, 10);
		add(pred3Text);

		pred2Text = new JTextField();
		pred2Text.setHorizontalAlignment(SwingConstants.CENTER);
		pred2Text.setFont(new Font("Arial", Font.PLAIN, 10));
		pred2Text.setEditable(false);
		pred2Text.setColumns(10);
		pred2Text.setBorder(null);
		pred2Text.setBackground(Color.WHITE);
		pred2Text.setBounds(202, 165, 35, 10);
		add(pred2Text);

		predLabel = new JLabel("Pred. :");
		predLabel.setFont(new Font("Arial", Font.BOLD, 11));
		predLabel.setBounds(150, 161, 35, 14);
		add(predLabel);

		descText = new JTextArea();
		descText.setBackground(Color.WHITE);
		descText.setBorder(null);
		descText.setLineWrap(true);
		descText.setWrapStyleWord(true);
		descText.setEditable(false);
		descText.setBounds(40, 20, 197, 35);
		add(descText);

		scrollPane = new JScrollPane(descText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new LineBorder(Color.BLACK, 1, true));
		scrollPane.setBounds(40, 20, 197, 35);
		add(scrollPane);
		
		

	}

	
	public Kamban(String resolucao) {// tamanho do grafico 243x254
		setSize(322, 330);
		setBackground(new Color(255, 255, 255));
		setBorder(BorderFactory.createTitledBorder(""));
		setLayout(null);

		duracaoLabel = new JLabel("Dura\u00E7\u00E3o");
		duracaoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		duracaoLabel.setForeground(Color.BLACK);
		duracaoLabel.setBounds(261, 70, 46, 14);
		add(duracaoLabel);
		
		anexoLabel = new JLabel("");
		anexoLabel.setVisible(false);
		anexoLabel.setIcon(new ImageIcon(Kamban.class.getResource("/com/myscrum/assets/clips.png")));
		anexoLabel.setBounds(291, 273, 25, 57);
		add(anexoLabel);

		inicioText = new JTextField();
		inicioText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		inicioText.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		inicioText.setBackground(new Color(255, 255, 255));
		inicioText.setEditable(false);
		inicioText.setBounds(50, 84, 87, 20);
		add(inicioText);
		inicioText.setColumns(10);

		fimText = new JTextField();
		fimText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		fimText.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		fimText.setBackground(new Color(255, 255, 255));
		fimText.setEditable(false);
		fimText.setBounds(154, 84, 87, 20);
		add(fimText);
		fimText.setColumns(10);

		ccText = new JTextField();
		ccText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		ccText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				Controle.telaKamban.ccComboBox.setSelectedItem(ccText.getText());//Ao clicar na text de centro de custo o nome do cc vai para o filtro
			}
		  }
		});
		ccText.setBorder(new LineBorder(Color.BLACK, 1, true));
		ccText.setBackground(new Color(255, 255, 255));
		ccText.setEditable(false);
		ccText.setBounds(80, 185, 97, 29);
		add(ccText);

		respText = new JTextField();
		respText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				Controle.telaKamban.ExeRespAutComboBox.setSelectedItem(respText.getText());//Ao clicar na text de centro de custo o nome do cc vai para o filtro
			}
		  }
		});
		respText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		respText.setBorder(new LineBorder(Color.BLACK, 1, true));
		respText.setBackground(new Color(255, 255, 255));
		respText.setEditable(false);
		respText.setBounds(80, 226, 97, 29);
		add(respText);
		respText.setColumns(10);

		autText = new JTextField();
		autText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				Controle.telaKamban.ExeRespAutComboBox.setSelectedItem(autText.getText());//Ao clicar na text de centro de custo o nome do cc vai para o filtro
			}
		  }
		});
		autText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		autText.setBorder(new LineBorder(Color.BLACK, 1, true));
		autText.setBackground(new Color(255, 255, 255));
		autText.setEditable(false);
		autText.setBounds(219, 226, 97, 29);
		add(autText);
		autText.setColumns(10);

		execText = new JTextField();
		execText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		execText.setBorder(new LineBorder(Color.BLACK, 1, true));
		execText.setBackground(new Color(255, 255, 255));
		execText.setEditable(false);
		execText.setBounds(80, 260, 236, 40);
		add(execText);
		execText.setColumns(10);

		BarraLateral = new JGradientPanel(Color.WHITE, new Color(41, 106, 158));
		BarraLateral.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
		BarraLateral.setBounds(0, 0, 46, 330);
		add(BarraLateral);
		BarraLateral.setLayout(null);

		idText = new JTextField();
		idText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					tela = new TarefaEditTela(Controle.telatarefa);//Instancia a tela
					tela.setVisible(true);// Torna visivel
					tela.atualizarButton();// Habilita botão de atualizar
					tela.setTitle(descText.getText());// Seta o titulo da tela com a descrição

					KambanDAO.SelectTarefa(Integer.parseInt(idText.getText()));// Executa o metodo para select da tarefa
																				// baseado no ID

					tela.carregarDados();// Carrega os dados na tela
					tela.ChamadaPeloKamban();// Informar a tela que ela foi chamada atraves do kamban
				}
			}

			public void mouseReleased(MouseEvent a) {
				if (a.getClickCount() == 1 && a.getModifiersEx() == InputEvent.ALT_DOWN_MASK) {
					try {
						Controle.telaKamban.predecessora.setText((idText.getText()));
						Controle.telaKamban.toBack();
					} catch (Exception e) {

					}

				}
			}
		});
		idText.setHorizontalAlignment(SwingConstants.CENTER);
		idText.setBorder(new LineBorder(new Color(41, 106, 158), 2));
		idText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		idText.setBackground(new Color(255, 255, 255));
		idText.setEditable(false);
		idText.setBounds(0, 0, 46, 20);
		BarraLateral.add(idText);
		idText.setColumns(10);

		prioridadeText = new JTextField();
		prioridadeText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		prioridadeText.setBackground(Color.WHITE);
		prioridadeText.setEditable(false);
		prioridadeText.setBorder(null);
		prioridadeText.setHorizontalAlignment(SwingConstants.CENTER);
		prioridadeText.setBounds(1, 52, 44, 30);
		BarraLateral.add(prioridadeText);
		prioridadeText.setColumns(10);

		tamanhoText = new JTextField();
		tamanhoText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		tamanhoText.setBackground(Color.WHITE);
		tamanhoText.setEditable(false);
		tamanhoText.setBorder(null);
		tamanhoText.setHorizontalAlignment(SwingConstants.CENTER);
		tamanhoText.setBounds(1, 100, 44, 30);
		BarraLateral.add(tamanhoText);
		tamanhoText.setColumns(10);
		
		dpto = new JLabel("Informática");
		dpto.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				Controle.telaKamban.dptoComboBox.setSelectedItem(dpto.getText());//Ao clicar na text de centro de custo o nome do cc vai para o filtro
			}
		  }
		});
		dpto.setHorizontalAlignment(SwingConstants.CENTER);
		dpto.setFont(new Font("Arial", Font.BOLD, 16));
		dpto.setBounds(0,130,46,95);
		dpto.setUI(new VerticalLabelUI(true));
		BarraLateral.add(dpto);

		porcentoText = new JTextField();
		porcentoText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		porcentoText.setBackground(Color.WHITE);
		porcentoText.setEditable(false);
		porcentoText.setHorizontalAlignment(SwingConstants.CENTER);
		porcentoText.setBounds(0, 225, 46, 30);
		BarraLateral.add(porcentoText);
		porcentoText.setColumns(10);

		pesoText = new JTextField();
		pesoText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		pesoText.setBackground(Color.WHITE);
		pesoText.setEditable(false);
		pesoText.setHorizontalAlignment(SwingConstants.CENTER);
		pesoText.setBounds(0, 260, 46, 30);
		BarraLateral.add(pesoText);
		pesoText.setColumns(10);

		pontuacaoText = new JTextField();
		pontuacaoText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		pontuacaoText.setHorizontalAlignment(SwingConstants.CENTER);
		pontuacaoText.setBackground(Color.WHITE);
		pontuacaoText.setEditable(false);
		pontuacaoText.setColumns(10);
		pontuacaoText.setBounds(0, 295, 46, 30);
		BarraLateral.add(pontuacaoText);

		tamanhoLabel = new JLabel("Tam.");
		tamanhoLabel.setFont(new Font("Arial", Font.BOLD, 12));
		tamanhoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tamanhoLabel.setBounds(0, 85, 46, 14);
		BarraLateral.add(tamanhoLabel);

		prioridadeLabel = new JLabel("Priori.");
		prioridadeLabel.setFont(new Font("Arial", Font.BOLD, 12));
		prioridadeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		prioridadeLabel.setBounds(0, 38, 46, 14);
		BarraLateral.add(prioridadeLabel);

		respLabel = new JLabel("");
		respLabel.setIcon(new ImageIcon(Kamban.class.getResource("/com/myscrum/assets/icone_resp.jpg")));
		respLabel.setHorizontalAlignment(SwingConstants.CENTER);
		respLabel.setBounds(50, 226, 30, 34);
		add(respLabel);

		execLabel = new JLabel("");
		execLabel.setIcon(new ImageIcon(Kamban.class.getResource("/com/myscrum/assets/icone_exec.jpg")));
		execLabel.setHorizontalAlignment(SwingConstants.CENTER);
		execLabel.setBounds(50, 257, 30, 42);
		add(execLabel);

		ccLabel = new JLabel("");
		ccLabel.setIcon(new ImageIcon(Kamban.class.getResource("/com/myscrum/assets/icone_cc.jpg")));
		ccLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ccLabel.setBounds(50, 189, 30, 25);
		add(ccLabel);

		autoridadeLabel = new JLabel("");
		autoridadeLabel.setIcon(new ImageIcon(Kamban.class.getResource("/com/myscrum/assets/icone_autoridade.jpg")));
		autoridadeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		autoridadeLabel.setBounds(191, 226, 25, 34);
		add(autoridadeLabel);

		inicioLabel = new JLabel("Inicio");
		inicioLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		inicioLabel.setForeground(Color.BLACK);
		inicioLabel.setBounds(50, 70, 87, 14);
		add(inicioLabel);

		fimLabel = new JLabel("Termino");
		fimLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		fimLabel.setForeground(Color.BLACK);
		fimLabel.setBounds(154, 70, 87, 14);
		add(fimLabel);

		duracaoText = new JTextField();
		duracaoText.setHorizontalAlignment(SwingConstants.CENTER);
		duracaoText.setBackground(Color.WHITE);
		duracaoText.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		duracaoText.setBounds(261, 83, 55, 21);
		add(duracaoText);
		duracaoText.setEditable(false);
		duracaoText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));

		statusLabel = new JLabel("Status");
		statusLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		statusLabel.setBounds(50, 96, 59, 24);
		add(statusLabel);

		statText = new JTextArea();
		statText.setBackground(Color.WHITE);
		statText.setEditable(false);
		statText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		statText.setLineWrap(true);
		statText.setBounds(50, 124, 195, 145);
		add(statText);

		scrollPane_2 = new JScrollPane(statText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBorder(new LineBorder(Color.BLACK, 1, true));
		scrollPane_2.setBounds(50, 122, 266, 51);
		add(scrollPane_2);

		iniciadoLabel = new JLabel("");
		iniciadoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		iniciadoLabel.setBounds(50, 2, 203, 15);
		add(iniciadoLabel);

		atrasadoText = new JTextField();
		atrasadoText.setFont(new Font("Arial", Font.BOLD, 12));
		atrasadoText.setHorizontalAlignment(SwingConstants.CENTER);
		atrasadoText.setBorder(null);
		atrasadoText.setBackground(Color.WHITE);
		atrasadoText.setBounds(246, 0, 73, 20);
		add(atrasadoText);
		atrasadoText.setColumns(10);

		diasatrasadosLabel = new JLabel("");
		diasatrasadosLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		diasatrasadosLabel.setBounds(50, 304, 166, 20);
		add(diasatrasadosLabel);

		pendenteText = new JTextField();
		pendenteText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				Controle.telaKamban.dptoComboBox.setSelectedItem(pendenteText.getText());//Ao clicar na text de centro de custo o nome do cc vai para o filtro
			}
		  }
		});
		pendenteText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		pendenteText.setEditable(false);
		pendenteText.setBorder(null);
		pendenteText.setBackground(Color.WHITE);
		pendenteText.setHorizontalAlignment(SwingConstants.CENTER);
		pendenteText.setBounds(219, 304, 97, 20);
		add(pendenteText);
		pendenteText.setColumns(10);

		pred1Text = new JTextField();
		pred1Text.setToolTipText("Descrição da tarefa vai aqui");
		pred1Text.setHorizontalAlignment(SwingConstants.CENTER);
		pred1Text.setFont(new Font("Arial", Font.PLAIN, 10));
		pred1Text.setEditable(false);
		pred1Text.setColumns(10);
		pred1Text.setBorder(null);
		pred1Text.setBackground(Color.WHITE);
		pred1Text.setBounds(243, 182, 48, 10);
		add(pred1Text);

		pred3Text = new JTextField();
		pred3Text.setHorizontalAlignment(SwingConstants.CENTER);
		pred3Text.setFont(new Font("Arial", Font.PLAIN, 10));
		pred3Text.setEditable(false);
		pred3Text.setColumns(10);
		pred3Text.setBorder(null);
		pred3Text.setBackground(Color.WHITE);
		pred3Text.setBounds(243, 204, 48, 10);
		add(pred3Text);

		pred2Text = new JTextField();
		pred2Text.setHorizontalAlignment(SwingConstants.CENTER);
		pred2Text.setFont(new Font("Arial", Font.PLAIN, 10));
		pred2Text.setEditable(false);
		pred2Text.setColumns(10);
		pred2Text.setBorder(null);
		pred2Text.setBackground(Color.WHITE);
		pred2Text.setBounds(243, 193, 48, 10);
		add(pred2Text);

		predLabel = new JLabel("Pred. :");
		predLabel.setFont(new Font("Arial", Font.BOLD, 13));
		predLabel.setBounds(191, 189, 50, 20);
		add(predLabel);

		descText = new JTextArea();
		descText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		descText.setBackground(Color.WHITE);
		descText.setBorder(null);
		descText.setLineWrap(true);
		descText.setWrapStyleWord(true);
		descText.setEditable(false);
		descText.setBounds(40, 20, 197, 35);
		add(descText);

		scrollPane = new JScrollPane(descText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new LineBorder(Color.BLACK, 1, true));
		scrollPane.setBounds(50, 22, 266, 42);
		add(scrollPane);

	}

}

