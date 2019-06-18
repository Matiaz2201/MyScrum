/*
 * Implemented by Pedro Henrique and Abner Matias
 * All right reserved - 2018
 * 
 */
package view;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.myscrum.banco.BD;
import com.myscrum.banco.Banco;
import com.myscrum.controller.Controle;
import com.myscrum.controller.Controlev;
import com.myscrum.model.ExportarTarefasXLS;
import com.myscrum.model.Sessao;
import com.myscrum.model.TableGrade;
import com.myscrum.model.Tarefa;
import com.towel.swing.calendar.CalendarView;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Font;
import java.awt.BorderLayout;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.border.MatteBorder;
import app.bolivia.swing.JCTextField;

public class TarefaTela extends javax.swing.JFrame {

	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	class AdicionarTarefaListener implements ActionListener {
		public TarefaTela formularioPrincipal;// criando objeto para o formulario pai

		public AdicionarTarefaListener(JFrame formularioPrincipal) {
			this.formularioPrincipal = (TarefaTela) formularioPrincipal;
		}

		public void actionPerformed(ActionEvent event) {
			tela = new TarefaEditTela(formularioPrincipal); // colocando a tela dentro do objeto
			tela.setVisible(true);// mostrando a tela
			tela.toFront();
			tela.salvarButton();
			tela.dataIniText.setText(tela.AtalhoCTRL());// Inserindo data de hoje no calendario
			tela.dataRealText.setText(tela.AtalhoCTRL());
		}
	}

	class AdicionarTarefaMouseListener implements MouseListener {
		public TarefaTela formularioPrincipal;// criando objeto para o formulario pai

		public AdicionarTarefaMouseListener(JFrame formularioPrincipal) {
			this.formularioPrincipal = (TarefaTela) formularioPrincipal;
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				tela = new TarefaEditTela(formularioPrincipal); // colocando a tela dentro do objeto
				tela.setVisible(true);// mostrando a tela
				tela.toFront();
				tela.atualizarButton();
				tela.carregarDados(tarefa);
				tela.setTitle(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
			}
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}

	private static final long serialVersionUID = 1L;
	int x, y;

	/**
	 * Creates new form Principal
	 * 
	 * @throws PropertyVetoException
	 */

	public TarefaTela() throws PropertyVetoException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				bd.close();
				Controlev.setTarefa(false);
				dispose();

			}
		});
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(TarefaTela.class.getResource("/com/myscrum/assets/setIcon1.png")));
		initComponents();
		setExtendedState(MAXIMIZED_BOTH);
		setSize(d.width, d.height);

	}

	private void initComponents() throws PropertyVetoException {
		jPanel1 = new javax.swing.JPanel();
		panelLateral = new javax.swing.JPanel();
		panelFiltro = new javax.swing.JPanel();
		filtrosLabel = new javax.swing.JLabel();
		ExecCheck = new javax.swing.JCheckBox();
		ExecCheck.setBackground(UIManager.getColor("CheckBox.background"));

		// ABILITANDO A COMBO AO SELECIONAR A CHECKBOX
		ExecCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ExecCheck.isEnabled()) {
					ExecCombo.setEnabled(true);
				}
				if (ExecCheck.isSelected() == false) {
					ExecCombo.setEnabled(false);
					ExecCombo.setSelectedIndex(0);
				}

			}
		});

		RespCheck = new javax.swing.JCheckBox();
		RespCheck.setBackground(UIManager.getColor("CheckBox.background"));

		// ABILITANDO A COMBO AO SELECIONAR A CHECKBOX
		RespCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (RespCheck.isEnabled()) {
					RespCombo.setEnabled(true);
				}
				if (RespCheck.isSelected() == false) {
					RespCombo.setEnabled(false);
					RespCombo.setSelectedIndex(0);
				}

			}
		});
		AutoCheck = new javax.swing.JCheckBox();
		AutoCheck.setBackground(UIManager.getColor("CheckBox.background"));

		// ABILITANDO A COMBO AO SELECIONAR A CHECKBOX
		AutoCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AutoCheck.isEnabled()) {
					AutoCombo.setEnabled(true);
				}
				if (AutoCheck.isSelected() == false) {
					AutoCombo.setEnabled(false);
					AutoCombo.setSelectedIndex(0);
				}

			}
		});
		ExecCombo = new javax.swing.JComboBox<>();
		ExecCombo.setEnabled(false);
		RespCombo = new javax.swing.JComboBox<>();
		RespCombo.setEnabled(false);
		AutoCombo = new javax.swing.JComboBox<>();
		AutoCombo.setEnabled(false);
		ccCheck = new javax.swing.JCheckBox();
		ccCheck.setBackground(UIManager.getColor("CheckBox.background"));
		// .setToolTipText("Insira seu nome");

		// ABILITANDO A COMBO AO SELECIONAR A CHECKBOX
		ccCheck.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (ccCheck.isEnabled()) {
					ccCombo.setEnabled(true);
				}
				if (ccCheck.isSelected() == false) {
					ccCombo.setEnabled(false);
					ccCombo.setSelectedIndex(0);
				}

			}
		});

		// ABILITANDO A COMBO AO SELECIONAR A CHECKBOX
		ccCombo = new javax.swing.JComboBox<>();
		ccCombo.setEnabled(false);
		dptoCheck = new javax.swing.JCheckBox();
		dptoCheck.setBackground(UIManager.getColor("CheckBox.background"));
		dptoCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dptoCheck.isEnabled()) {
					dptoCombo.setEnabled(true);
				}
				if (dptoCheck.isSelected() == false) {
					dptoCombo.setEnabled(false);
					dptoCombo.setSelectedIndex(0);
				}

			}
		});
		dptoCombo = new javax.swing.JComboBox<>();
		dptoCombo.setEnabled(false);
		deText = new CalendarView();
		deText.getEditor().setBackground(Color.WHITE);
		deText.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deText.getButton().setText("");
		deText.getButton().setIcon(new ImageIcon(TarefaTela.class.getResource("/com/myscrum/assets/calendar.png")));
		deLabel = new javax.swing.JLabel();
		ateLabel = new javax.swing.JLabel();
		ateText = new CalendarView();
		ateText.getEditor().setBackground(Color.WHITE);
		ateText.getButton().setIcon(new ImageIcon(TarefaTela.class.getResource("/com/myscrum/assets/calendar.png")));
		ateText.getButton().setText("");
		pesquisarButton = new JButton();

//--------------------------------------------- FILTROS -------------------------------------------------------------------------------------------

		pesquisarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listar(gerarSQL());
				JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

			}
		});

//-------------------------------------------------------------------- BOTÃO LIMPAR FILTROS --------------------------------------------------------
		limparButton = new JButton();
		limparButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (ExecCheck.isEnabled() || RespCheck.isEnabled() || AutoCheck.isEnabled() || dptoCheck.isEnabled()
						|| ccCheck.isEnabled() || checkAFazer.isEnabled() || checkFazendo.isEnabled()
						|| checkFeito.isEnabled() || deText.isEnabled() || ateText.isEnabled()) {

					ExecCheck.setSelected(false);
					RespCheck.setSelected(false);
					AutoCheck.setSelected(false);
					dptoCheck.setSelected(false);
					ccCheck.setSelected(false);
					checkAFazer.setSelected(false);
					checkFazendo.setSelected(false);
					checkFeito.setSelected(false);
					deText.setText("");
					ateText.setText("");

					ExecCombo.setEnabled(false);
					ExecCombo.setSelectedIndex(0);

					RespCombo.setEnabled(false);
					RespCombo.setSelectedIndex(0);

					AutoCombo.setEnabled(false);
					AutoCombo.setSelectedIndex(0);

					ccCombo.setEnabled(false);
					ccCombo.setSelectedIndex(0);

					dptoCombo.setEnabled(false);
					dptoCombo.setSelectedIndex(0);

				}

			}
		});
		panelSuperior = new javax.swing.JPanel();
		panelSuperior.setBorder(new MatteBorder(1, 1, 1, 1, (Color) null));
		jButton1 = new javax.swing.JButton();
		myScrumLabel = new javax.swing.JLabel();
		searchText = new app.bolivia.swing.JCTextField();
		searchText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText.getText()));

			}
		});
		panelMain = new javax.swing.JPanel();

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setLayout(new java.awt.GridBagLayout());

		panelLateral.setBackground(UIManager.getColor("CheckBox.background"));
		panelLateral
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(239, 238, 244)));

		panelFiltro.setBackground(UIManager.getColor("CheckBox.background"));

		filtrosLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		filtrosLabel.setForeground(new java.awt.Color(128, 128, 131));
		filtrosLabel.setText("FILTROS");

		javax.swing.GroupLayout gl_panelFiltro = new javax.swing.GroupLayout(panelFiltro);
		panelFiltro.setLayout(gl_panelFiltro);
		gl_panelFiltro.setHorizontalGroup(
				gl_panelFiltro.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(gl_panelFiltro
						.createSequentialGroup().addComponent(filtrosLabel).addGap(0, 0, Short.MAX_VALUE)));
		gl_panelFiltro.setVerticalGroup(gl_panelFiltro.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gl_panelFiltro.createSequentialGroup()
						.addContainerGap(35, Short.MAX_VALUE).addComponent(filtrosLabel).addContainerGap()));

		ExecCheck.setText("Executor");
		ExecCheck.setActionCommand("executorCheck");

		RespCheck.setText("Responsavel");
		RespCheck.setActionCommand("responsavelCheck");

		AutoCheck.setText("Autoridade");
		AutoCheck.setActionCommand("AutoridadeCheck");

		ExecCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
		ExecCombo.setActionCommand("execCombo");

		RespCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
		RespCombo.setActionCommand("respCombo");

		AutoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
		AutoCombo.setActionCommand("AutorCombo");

		ccCheck.setText("Centro de custo");
		ccCheck.setActionCommand("ccCheck");

		ccCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
		ccCombo.setActionCommand("ccCombo");

		dptoCheck.setText("Departamento");
		dptoCheck.setActionCommand("dptoCheck");

		dptoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
		dptoCombo.setActionCommand("dptoCombo");

		deText.setForeground(new java.awt.Color(204, 204, 204));

		deLabel.setText("De:");

		ateLabel.setText("Até:");

		ateText.setForeground(new java.awt.Color(204, 204, 204));
		ateText.setToolTipText("");

		pesquisarButton.setText("PESQUISAR");
		pesquisarButton.setActionCommand("PesquisarButton");

		limparButton.setText("LIMPAR");
		limparButton.setActionCommand("limparButton");

		javax.swing.GroupLayout gl_panelLateral = new javax.swing.GroupLayout(panelLateral);
		gl_panelLateral.setHorizontalGroup(gl_panelLateral.createParallelGroup(Alignment.LEADING)
				.addComponent(panelFiltro, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panelLateral.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelLateral.createParallelGroup(Alignment.TRAILING)
								.addComponent(ExecCombo, Alignment.LEADING, 0, 167, Short.MAX_VALUE)
								.addComponent(RespCombo, Alignment.LEADING, 0, 167, Short.MAX_VALUE)
								.addComponent(AutoCombo, Alignment.LEADING, 0, 167, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_panelLateral.createSequentialGroup()
										.addGroup(gl_panelLateral.createParallelGroup(Alignment.LEADING)
												.addComponent(ExecCheck).addComponent(RespCheck).addComponent(AutoCheck)
												.addComponent(ccCheck).addComponent(dptoCheck))
										.addGap(64))
								.addComponent(ccCombo, Alignment.LEADING, 0, 167, Short.MAX_VALUE)
								.addComponent(dptoCombo, 0, 167, Short.MAX_VALUE))
						.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_panelLateral.createSequentialGroup().addGap(33)
						.addGroup(gl_panelLateral.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(deLabel, Alignment.LEADING).addComponent(ateLabel, Alignment.LEADING)
								.addComponent(ateText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(deText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(18, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panelLateral.createSequentialGroup().addContainerGap()
						.addComponent(limparButton, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE).addContainerGap())
				.addGroup(gl_panelLateral.createSequentialGroup().addContainerGap()
						.addComponent(pesquisarButton, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panelLateral
				.setVerticalGroup(
						gl_panelLateral.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelLateral.createSequentialGroup()
										.addComponent(panelFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(5).addComponent(ExecCheck).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(RespCheck).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(AutoCheck).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(ExecCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(RespCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(AutoCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(31).addComponent(ccCheck).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(ccCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(dptoCheck)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(dptoCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(27).addComponent(deLabel).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(deText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(ateLabel).addGap(4)
										.addComponent(ateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(30).addComponent(pesquisarButton)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(limparButton)
										.addContainerGap(223, Short.MAX_VALUE)));
		panelLateral.setLayout(gl_panelLateral);

		gbc_panelLateral = new java.awt.GridBagConstraints();
		gbc_panelLateral.gridx = 0;
		gbc_panelLateral.gridy = 1;
		gbc_panelLateral.fill = java.awt.GridBagConstraints.VERTICAL;
		gbc_panelLateral.anchor = java.awt.GridBagConstraints.WEST;
		gbc_panelLateral.weighty = 8.3;
		gbc_panelLateral.insets = new java.awt.Insets(0, 0, 0, 1);
		jPanel1.add(panelLateral, gbc_panelLateral);

		panelSuperior.setBackground(new java.awt.Color(38, 86, 186));
		panelSuperior.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		panelSuperior.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				jPanel2MouseDragged(evt);
			}
		});
		panelSuperior.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jPanel2MousePressed(evt);
			}
		});

		jButton1.setBorder(null);
		jButton1.setContentAreaFilled(false);
		jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		myScrumLabel.setBackground(new java.awt.Color(255, 255, 255));
		myScrumLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		myScrumLabel.setForeground(new java.awt.Color(255, 255, 255));
		myScrumLabel.setText("My Scrum - Tarefas");

		searchText.setBackground(new java.awt.Color(38, 86, 186));
		searchText
				.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
		searchText.setForeground(new java.awt.Color(255, 255, 255));
		searchText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		searchText.setPhColor(new java.awt.Color(255, 255, 255));
		searchText.setPlaceholder("SEARCH");

		descText = new app.bolivia.swing.JCTextField();
		descText.setPhColor(new Color(255, 255, 255));
		descText.setPlaceholder("DESCRI\u00C7\u00C3O");
		descText.setForeground(new Color(255, 255, 255));
		descText.setFont(new Font("Tahoma", Font.BOLD, 18));
		descText.setCaretColor(new Color(255, 255, 255));
		descText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		descText.setBackground(new Color(38, 86, 186));
		descText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + descText.getText(), 1));
			}
		});

		JCTextField statusText = new app.bolivia.swing.JCTextField();
		statusText.setPlaceholder("STATUS");
		statusText.setPhColor(new Color(255, 255, 255));
		statusText.setForeground(new Color(176, 196, 222));
		statusText.setFont(new Font("Tahoma", Font.BOLD, 18));
		statusText.setCaretColor(Color.WHITE);
		statusText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		statusText.setBackground(new Color(38, 86, 186));
		statusText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + statusText.getText(), 32));
			}
		});

		JCTextField historicoText = new app.bolivia.swing.JCTextField();
		historicoText.setPlaceholder("HIST\u00D3RICO");
		historicoText.setPhColor(new Color(255, 255, 255));
		historicoText.setForeground(new Color(176, 196, 222));
		historicoText.setFont(new Font("Tahoma", Font.BOLD, 18));
		historicoText.setCaretColor(Color.WHITE);
		historicoText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		historicoText.setBackground(new Color(38, 86, 186));
		historicoText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + historicoText.getText(), 33));
			}
		});

		javax.swing.GroupLayout gl_panelSuperior = new javax.swing.GroupLayout(panelSuperior);
		gl_panelSuperior
				.setHorizontalGroup(gl_panelSuperior.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSuperior.createSequentialGroup().addContainerGap().addComponent(jButton1)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(myScrumLabel).addGap(71)
								.addComponent(searchText, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(descText, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(statusText, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(historicoText,
										GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(515, Short.MAX_VALUE)));
		gl_panelSuperior.setVerticalGroup(gl_panelSuperior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperior.createSequentialGroup().addContainerGap().addGroup(gl_panelSuperior
						.createParallelGroup(Alignment.LEADING, false)
						.addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panelSuperior.createParallelGroup(Alignment.BASELINE)
								.addComponent(myScrumLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(searchText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(descText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(statusText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(historicoText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelSuperior.setLayout(gl_panelSuperior);

		gbc_panelSuperior = new java.awt.GridBagConstraints();
		gbc_panelSuperior.gridx = 0;
		gbc_panelSuperior.gridy = 0;
		gbc_panelSuperior.gridwidth = 3;
		gbc_panelSuperior.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gbc_panelSuperior.anchor = java.awt.GridBagConstraints.PAGE_START;
		gbc_panelSuperior.weightx = 0.2;
		jPanel1.add(panelSuperior, gbc_panelSuperior);

		panelMain.setBackground(new java.awt.Color(255, 255, 255));

		panelBelow = new JPanel();
		panelBelow.setBounds(10, 0, 1701, 48);
		panelBelow.setBackground(SystemColor.inactiveCaptionBorder);
		centro.setBounds(10, 66, d.width - 225, d.height - 280);
		centro.setLayout(new BorderLayout(0, 0));

		novaTarefa = new JButton();
		novaTarefa.setBounds(10, 11, 163, 26);
		novaTarefa.addActionListener(new AdicionarTarefaListener(this));

		novaTarefa.setText("NOVA TAREFA");
		novaTarefa.setActionCommand("novaTarefa");

		checkAFazer = new JCheckBox("A fazer");
		checkAFazer.setBounds(237, 16, 89, 25);
		checkAFazer.setBackground(SystemColor.inactiveCaptionBorder);
		checkAFazer.setFont(new Font("Tahoma", Font.BOLD, 14));

		checkFazendo = new JCheckBox("Fazendo");
		checkFazendo.setBounds(324, 16, 97, 25);
		checkFazendo.setBackground(SystemColor.inactiveCaptionBorder);
		checkFazendo.setFont(new Font("Tahoma", Font.BOLD, 14));

		checkFeito = new JCheckBox("Feito");
		checkFeito.setBounds(425, 16, 79, 25);
		checkFeito.setBackground(SystemColor.inactiveCaptionBorder);
		checkFeito.setFont(new Font("Tahoma", Font.BOLD, 14));

		checkCancelado = new JCheckBox("Cancelado");
		checkCancelado.setBounds(500, 16, 115, 25);
		checkCancelado.setBackground(SystemColor.inactiveCaptionBorder);
		checkCancelado.setFont(new Font("Tahoma", Font.BOLD, 14));

		button = new JButton("Exportar tarefas");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = gerarSQL();
				System.out.println(sql);
				if (Banco.conexao()) {
					try {
						Banco.st = Banco.con.prepareStatement(sql);
						Banco.rs = Banco.st.executeQuery();

						if (Banco.rs.next()) {
							ResultSet tarefas = Banco.rs;
							String caminho = "";

							JFileChooser jc = new JFileChooser();
							jc.setDialogTitle("Selecione o caminho e nome do arquivo");
							int opJc = jc.showOpenDialog(null);

							if (opJc == JFileChooser.APPROVE_OPTION) {
								caminho = jc.getSelectedFile().getAbsolutePath();
								ExportarTarefasXLS.exportar(tarefas, caminho);

							}
						}

					} catch (SQLException erro) {
						JOptionPane.showMessageDialog(null, erro.toString());
					}
				}
			}
		});
		button.setBounds(674, 16, 115, 29);

		panelBelow.setLayout(null);
		panelBelow.add(novaTarefa);
		panelBelow.add(checkAFazer);
		panelBelow.add(checkFazendo);
		panelBelow.add(checkFeito);
		panelBelow.add(checkCancelado);
		panelBelow.add(button);
		panelMain.setLayout(null);

		gbc_panelMain = new java.awt.GridBagConstraints();
		gbc_panelMain.gridx = 1;
		gbc_panelMain.gridy = 1;
		gbc_panelMain.gridwidth = 2;
		gbc_panelMain.fill = java.awt.GridBagConstraints.BOTH;
		gbc_panelMain.weightx = 0.4;
		gbc_panelMain.weighty = 0.1;
		jPanel1.add(panelMain, gbc_panelMain);
		panelMain.add(panelBelow);
		panelMain.add(centro);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		bd.getConnection();

		CarregaDatas();
		listar(gerarSQL());
		CarregaCombobox();

	}

	public void listar(String sql) {

		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			if (bd.rs.next()) {// se haver tarefas continue
				if (tabela != null) {// se existir outra tabela montada apaga

					sp.setVisible(false);
					sp.remove(tabela);
					sp = null;
					tabela.setVisible(false);
					tabela = null;

				}

				Vector<String> cabecalhoPersonalizado = new Vector<>();

				cabecalhoPersonalizado.addElement("ID");// 0
				cabecalhoPersonalizado.addElement("Descrição");// 1
				cabecalhoPersonalizado.addElement("Prioridade");// 2
				cabecalhoPersonalizado.addElement("C.C");// 3
				cabecalhoPersonalizado.addElement("Status");// 4
				cabecalhoPersonalizado.addElement("Tamanho");// 5
				cabecalhoPersonalizado.addElement("Porcentagem");// 6
				cabecalhoPersonalizado.addElement("Prazo");// 7
				cabecalhoPersonalizado.addElement("Data Inicial");// 8
				cabecalhoPersonalizado.addElement("Data Real");// 9
				cabecalhoPersonalizado.addElement("Data Fim");// 10
				cabecalhoPersonalizado.addElement("1º Executor");// 11
				cabecalhoPersonalizado.addElement("%");// 12
				cabecalhoPersonalizado.addElement("2º Executor");// 13
				cabecalhoPersonalizado.addElement("%");// 14
				cabecalhoPersonalizado.addElement("3º Executor");// 15
				cabecalhoPersonalizado.addElement("%");// 16
				cabecalhoPersonalizado.addElement("4º Executor");// 17
				cabecalhoPersonalizado.addElement("%");// 18
				cabecalhoPersonalizado.addElement("5º Executor");// 19
				cabecalhoPersonalizado.addElement("%");// 20
				cabecalhoPersonalizado.addElement("6º Executor");// 21
				cabecalhoPersonalizado.addElement("%");// 22
				cabecalhoPersonalizado.addElement("7º Executor");// 23
				cabecalhoPersonalizado.addElement("%");// 24
				cabecalhoPersonalizado.addElement("8º Executor");// 25
				cabecalhoPersonalizado.addElement("%");// 26
				cabecalhoPersonalizado.addElement("9º Executor");// 27
				cabecalhoPersonalizado.addElement("%");// 28
				cabecalhoPersonalizado.addElement("10º Executor");// 29
				cabecalhoPersonalizado.addElement("%");// 30
				cabecalhoPersonalizado.addElement("Pendente");// 31
				cabecalhoPersonalizado.addElement("Status pendência");// 32
				cabecalhoPersonalizado.addElement("Historico");// 33
				cabecalhoPersonalizado.addElement("Departamento");// 34
				cabecalhoPersonalizado.addElement("Responsavel");// 35
				cabecalhoPersonalizado.addElement("Autoridade");// 36
				cabecalhoPersonalizado.addElement("Etapa");// 37
				cabecalhoPersonalizado.addElement("Sub Etapa");// 38
				cabecalhoPersonalizado.addElement("Departamento Correto");// Obsoletos //39
				cabecalhoPersonalizado.addElement("Processo Relacionado");// 40
				cabecalhoPersonalizado.addElement("Predecessor 1");// 41
				cabecalhoPersonalizado.addElement("Predecessor 2");// 42
				cabecalhoPersonalizado.addElement("Predecessor 3");
				;// 43
				cabecalhoPersonalizado.addElement("Ultima Atualização");// 44
				cabecalhoPersonalizado.addElement("Atualizado por");// 45
				cabecalhoPersonalizado.addElement("Checado");// 46

				tabela = TableGrade.getTable(sql, cabecalhoPersonalizado);

				tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
				tabela.getColumnModel().getColumn(1).setPreferredWidth(400);
				tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabela.getColumnModel().getColumn(4).setPreferredWidth(65);
				tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
				tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(7).setPreferredWidth(50);
				tabela.getColumnModel().getColumn(11).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(12).setPreferredWidth(30);
				tabela.getColumnModel().getColumn(13).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(14).setPreferredWidth(30);
				tabela.getColumnModel().getColumn(15).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(16).setPreferredWidth(30);
				tabela.getColumnModel().getColumn(17).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(18).setPreferredWidth(30);
				tabela.getColumnModel().getColumn(19).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(20).setPreferredWidth(30);
				tabela.getColumnModel().getColumn(21).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(22).setPreferredWidth(30);
				tabela.getColumnModel().getColumn(23).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(24).setPreferredWidth(30);
				tabela.getColumnModel().getColumn(25).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(26).setPreferredWidth(30);
				tabela.getColumnModel().getColumn(27).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(28).setPreferredWidth(30);
				tabela.getColumnModel().getColumn(29).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(30).setPreferredWidth(30);

				sp = new JScrollPane(tabela);

				// adiciona Scroll ao frame
				centro.add(sp);

				centro.updateUI(); // atualiza tela

				tabela.setEditingRow(1);
				tabela.setEditingColumn(1);
				tabela.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

				sorter = new TableRowSorter<TableModel>(tabela.getModel());
				tabela.setRowSorter(sorter);

				sorter.setRowFilter(RowFilter.regexFilter(searchText.getText()));

				tabela.addMouseListener(new AdicionarTarefaMouseListener(this));// Adicionando a clase de abrir tela
				tabela.addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent a) {
						if (a.getClickCount() == 2) {
							tarefa = new Tarefa();

							tarefa.setIDTarefa(
									Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString()));
							tarefa.setDescricao(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
							tarefa.setPrioridade(
									Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 2).toString()));
							tarefa.setCentroCusto(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
							tarefa.setStatus(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
							tarefa.setTamanho(tabela.getValueAt(tabela.getSelectedRow(), 5).toString());
							tarefa.setPorcentagem(
									Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 6).toString()));
							tarefa.setPrazo(Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 7).toString()));
							tarefa.setDataInicio(tabela.getValueAt(tabela.getSelectedRow(), 8).toString());
							tarefa.setDataReal(tabela.getValueAt(tabela.getSelectedRow(), 9).toString());
							tarefa.setDataFim(tabela.getValueAt(tabela.getSelectedRow(), 10).toString());

							// teste se existi executor 1
							if (tabela.getValueAt(tabela.getSelectedRow(), 11) == null) {
								tarefa.setExecutor1("");
								tarefa.setPorcento2(0);
							} else {
								tarefa.setExecutor1(tabela.getValueAt(tabela.getSelectedRow(), 11).toString());
								tarefa.setPorcento1(
										Integer.parseInt((tabela.getValueAt(tabela.getSelectedRow(), 12).toString())));
							}

							// teste se existi executor 2
							if (tabela.getValueAt(tabela.getSelectedRow(), 13) == null) {
								tarefa.setExecutor2("");
								tarefa.setPorcento2(0);
							} else {
								tarefa.setExecutor2(tabela.getValueAt(tabela.getSelectedRow(), 13).toString());
								tarefa.setPorcento2(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 14).toString()));
							}

							// teste se existi executor 3
							if (tabela.getValueAt(tabela.getSelectedRow(), 15) == null) {
								tarefa.setExecutor3("");
								tarefa.setPorcento3(0);
							} else {
								tarefa.setExecutor3(tabela.getValueAt(tabela.getSelectedRow(), 15).toString());
								tarefa.setPorcento3(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 16).toString()));
							}

							// teste se existi executor 4
							if (tabela.getValueAt(tabela.getSelectedRow(), 17) == null) {
								tarefa.setExecutor4("");
								tarefa.setPorcento4(0);
							} else {
								tarefa.setExecutor4(tabela.getValueAt(tabela.getSelectedRow(), 17).toString());
								tarefa.setPorcento4(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 18).toString()));
							}

							// teste se existi executor 5
							if (tabela.getValueAt(tabela.getSelectedRow(), 19) == null) {
								tarefa.setExecutor5("");
								tarefa.setPorcento5(0);
							} else {
								tarefa.setExecutor5(tabela.getValueAt(tabela.getSelectedRow(), 19).toString());
								tarefa.setPorcento5(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 20).toString()));
							}

							// teste se existi executor 6
							if (tabela.getValueAt(tabela.getSelectedRow(), 21) == null) {
								tarefa.setExecutor6("");
								tarefa.setPorcento6(0);
							} else {
								tarefa.setExecutor6(tabela.getValueAt(tabela.getSelectedRow(), 21).toString());
								tarefa.setPorcento6(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 22).toString()));
							}

							// teste se existi executor 7
							if (tabela.getValueAt(tabela.getSelectedRow(), 23) == null) {
								tarefa.setExecutor7("");
								tarefa.setPorcento7(0);
							} else {
								tarefa.setExecutor7(tabela.getValueAt(tabela.getSelectedRow(), 23).toString());
								tarefa.setPorcento7(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 24).toString()));
							}

							// teste se existi executor 8
							if (tabela.getValueAt(tabela.getSelectedRow(), 25) == null) {
								tarefa.setExecutor8("");
								tarefa.setPorcento8(0);
							} else {
								tarefa.setExecutor8(tabela.getValueAt(tabela.getSelectedRow(), 25).toString());
								tarefa.setPorcento8(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 26).toString()));
							}

							// teste se existi executor 9
							if (tabela.getValueAt(tabela.getSelectedRow(), 27) == null) {
								tarefa.setExecutor9("");
								tarefa.setPorcento9(0);
							} else {
								tarefa.setExecutor9(tabela.getValueAt(tabela.getSelectedRow(), 27).toString());
								tarefa.setPorcento9(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 28).toString()));
							}

							// teste se existi executor 10
							if (tabela.getValueAt(tabela.getSelectedRow(), 29) == null) {
								tarefa.setExecutor10("");
								tarefa.setPorcento10(0);
							} else {
								tarefa.setExecutor10(tabela.getValueAt(tabela.getSelectedRow(), 29).toString());
								tarefa.setPorcento10(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 30).toString()));
							}

							// teste se pendente existir
							if (tabela.getValueAt(tabela.getSelectedRow(), 31) == null) {
								tarefa.setPendentePor("");
							} else {
								tarefa.setPendentePor(tabela.getValueAt(tabela.getSelectedRow(), 31).toString());
							}
							// teste se status pendencia eexistir
							if (tabela.getValueAt(tabela.getSelectedRow(), 32) == null) {
								tarefa.setStatusPendencia("");
							} else {
								tarefa.setStatusPendencia(tabela.getValueAt(tabela.getSelectedRow(), 32).toString());
							}
							// teste se existir historico
							if (tabela.getValueAt(tabela.getSelectedRow(), 33) == null) {
								tarefa.setHistorico("");
							} else {
								tarefa.setHistorico(tabela.getValueAt(tabela.getSelectedRow(), 33).toString());
							}
							tarefa.setDepartamento(tabela.getValueAt(tabela.getSelectedRow(), 34).toString());
							tarefa.setResponsavel(tabela.getValueAt(tabela.getSelectedRow(), 35).toString());
							tarefa.setAutoridade(tabela.getValueAt(tabela.getSelectedRow(), 36).toString());

							if (tabela.getValueAt(tabela.getSelectedRow(), 37) == null) {
								tarefa.setEtapa("");
							} else {
								tarefa.setEtapa(tabela.getValueAt(tabela.getSelectedRow(), 37).toString());
							}

							if (tabela.getValueAt(tabela.getSelectedRow(), 38) == null) {
								tarefa.setSubEtapa("");
							} else {
								tarefa.setSubEtapa(tabela.getValueAt(tabela.getSelectedRow(), 38).toString());
							}

							if (tabela.getValueAt(tabela.getSelectedRow(), 40) == null) {// Se existir processo carrega
																							// a tarefa
								tarefa.setProcesso("");
							} else {
								tarefa.setProcesso(tabela.getValueAt(tabela.getSelectedRow(), 40).toString());
							}

							if (tabela.getValueAt(tabela.getSelectedRow(), 41) == ""
									|| tabela.getValueAt(tabela.getSelectedRow(), 41) == null) {
								tarefa.setPredecessor1(0);
							} else {
								tarefa.setPredecessor1(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 41).toString()));
							}

							if (tabela.getValueAt(tabela.getSelectedRow(), 42) == ""
									|| tabela.getValueAt(tabela.getSelectedRow(), 42) == null) {
								tarefa.setPredecessor2(0);
							} else {
								tarefa.setPredecessor2(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 42).toString()));
							}

							if (tabela.getValueAt(tabela.getSelectedRow(), 43) == ""
									|| tabela.getValueAt(tabela.getSelectedRow(), 43) == null) {
								tarefa.setPredecessor3(0);
							} else {
								tarefa.setPredecessor3(
										Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 43).toString()));
							}

							if (tabela.getValueAt(tabela.getSelectedRow(), 44) == null) {
								tarefa.setAtualizacao("");
							} else {
								tarefa.setAtualizacao("Atualizado " + tabela.getValueAt(tabela.getSelectedRow(), 45)
										+ " Por " + tabela.getValueAt(tabela.getSelectedRow(), 44));
							}

							if (tabela.getValueAt(tabela.getSelectedRow(), 46) == ""
									|| tabela.getValueAt(tabela.getSelectedRow(), 46) == null) {
								tarefa.setChecado("");
							} else {
								tarefa.setChecado(tabela.getValueAt(tabela.getSelectedRow(), 46).toString());
							}
						}
					}
				});

				tabela.addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent a) {
						if (a.getClickCount() == 1 && a.getModifiersEx() == InputEvent.ALT_DOWN_MASK) {
							try {
								predecessora.setText((tabela.getValueAt(tabela.getSelectedRow(), 0).toString()));
								tela.toFront();
							} catch (Exception e) {

							}

						}
					}
				});

			} else {

				JOptionPane.showMessageDialog(null, "Sem registro de tarefas");
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());
		}

	}

	public String gerarSQL() {
		String data1;
		String data2;

		sql = "SELECT tarefa.id_tarefa,tarefa.descri,tarefa.prioridade,centro_custo.centrocusto,tarefa.stat,"
				+ "tamanho.descricao,tarefa.porcentagem,tarefa.prazo,tarefa.data_ini,tarefa.data_real,tarefa.data_fim,"
				+ "executor.executor1,executor.porcento1,executor.executor2,executor.porcento2,executor.executor3,executor.porcento3,executor.executor4,executor.porcento4,"
				+ "executor.executor5, executor.porcento5, executor.executor6, executor.porcento6, executor.executor7, executor.porcento7, executor.executor8, executor.porcento8,"
				+ "executor.executor9, executor.porcento9, executor.executor10, executor.porcento10,"
				+ "tarefa.pendente_por,tarefa.status_pendencia,tarefa.historico,departamento.departamento,"
				+ "tarefa.responsavel,tarefa.autoridade,"
				+ "(SELECT etapas.etapa FROM etapas WHERE etapas.id_etapa = tarefa.etapa) AS etapa,"
				+ "(SELECT sub_etapas.sub_etapa FROM sub_etapas WHERE sub_etapas.id_sub_etapas = tarefa.subetapa) AS subetapa,"
				+ "dpto_correto,"
				+ "(SELECT processos.processo FROM processos WHERE tarefa.processo_relacionado=processos.id_processo) AS processo_relacionado,"
				+ "tarefa.predecessor_1, tarefa.predecessor_2, tarefa.predecessor_3, tarefa.last_update,  "
				+ "(SELECT pessoa.nome FROM pessoa WHERE pessoa.id_pessoa = tarefa.id_update), " + "tarefa.checado \r\n"
				+ "FROM tarefa\r\n" + "INNER JOIN centro_custo\r\n"
				+ "ON tarefa.id_centro_custo=centro_custo.id_centro_custo\r\n" + "INNER JOIN tamanho\r\n"
				+ "ON tarefa.id_tamanho=tamanho.id_tamanho\r\n" + "INNER JOIN executor\r\n"
				+ "ON tarefa.id_tarefa=executor.id_tarefa\r\n" + "INNER JOIN departamento\r\n"
				+ "ON tarefa.id_departamento=departamento.id_departamento\r\n";

		// Periodo de
		if (deText.getText().equals("")) {
			data1 = "2018-01-01";
		} else {
			data1 = DataParaoBanco(deText.getText());
		}

		// Periodo até
		if (ateText.getText().equals("")) {
			data2 = "2018-12-31";

		} else {
			data2 = DataParaoBanco(ateText.getText());

		}

		if (Sessao.getInstance().getFuncao() == 0) {// Se a função for apenas usuario limitamos as tarefas apenas para
													// qual o nome dele está envolvido
			String eu = "'" + Sessao.getInstance().getNome() + "'";

			sql += "WHERE (tarefa.responsavel = " + eu + " OR tarefa.autoridade = " + eu + " OR tarefa.pendente_por = "
					+ eu + " OR tarefa.checado = " + eu + " \r\n" + "OR (executor.executor1 = " + eu
					+ " OR executor.executor2 = " + eu + " OR executor.executor3 = " + eu + " \r\n"
					+ "OR executor.executor4 = " + eu + " OR executor.executor5 = " + eu + " OR executor.executor6 = "
					+ eu + " \r\n" + "OR executor.executor7 = " + eu + " OR executor.executor8 = " + eu
					+ " OR executor.executor9 = " + eu + " \r\n" + "OR executor.executor10 = " + eu + "))\r\n";

			sql += "AND tarefa.data_ini\r\n" + "BETWEEN IF(tarefa.stat = 'Feito' OR tarefa.stat = 'Cancelado'," + "'"
					+ data1 + "'" + ",'2014-01-01') AND " + "'" + data2 + "'";
		}

		else if (Sessao.getInstance().getFuncao() == 2) {// Se a função for lider limitamos as tarefas apenas para qual
															// o nome dele está envolvido e qual o departamento dele
															// aparece
			String eu = "'" + Sessao.getInstance().getNome() + "'";
			String dpto = "'" + Sessao.getInstance().getDpto() + "'";

			sql += "WHERE (tarefa.responsavel = " + eu + " OR tarefa.autoridade = " + eu + " OR tarefa.pendente_por = "
					+ eu + " OR tarefa.checado = " + eu + " \r\n" + "OR (executor.executor1 = " + eu
					+ " OR executor.executor2 = " + eu + " OR executor.executor3 = " + eu + " \r\n"
					+ "OR executor.executor4 = " + eu + " OR executor.executor5 = " + eu + " OR executor.executor6 = "
					+ eu + " \r\n" + "OR executor.executor7 = " + eu + " OR executor.executor8 = " + eu
					+ " OR executor.executor9 = " + eu + " \r\n" + "OR executor.executor10 = " + eu + " OR \r\n"
					+ "(tarefa.id_centro_custo in (SELECT vinculos.id_cc FROM vinculos WHERE vinculos.id_usuario = "
					+ Sessao.getInstance().getId() + ") AND \r\n"
					+ "tarefa.id_departamento in (SELECT vinculos.id_dpto FROM vinculos WHERE vinculos.id_usuario = "
					+ Sessao.getInstance().getId() + "))))\r\n";

			sql += "AND tarefa.data_ini\r\n" + "BETWEEN IF(tarefa.stat = 'Feito' OR tarefa.stat = 'Cancelado'," + "'"
					+ data1 + "'" + ",'2014-01-01') AND " + "'" + data2 + "'";
		}

		else if (Sessao.getInstance().getFuncao() == 3) {// Se a função for limitamos as tarefas apenas para qual o nome
															// dele está envolvido e qual o departamento dele aparece
			String eu = "'" + Sessao.getInstance().getNome() + "'";
			String cc = "'" + Sessao.getInstance().getCC() + "'";

			sql += "WHERE (tarefa.responsavel = " + eu + " OR tarefa.autoridade = " + eu + " OR tarefa.pendente_por = "
					+ eu + " OR tarefa.checado = " + eu + " \r\n" + "OR (executor.executor1 = " + eu
					+ " OR executor.executor2 = " + eu + " OR executor.executor3 = " + eu + " \r\n"
					+ "OR executor.executor4 = " + eu + " OR executor.executor5 = " + eu + " OR executor.executor6 = "
					+ eu + " \r\n" + "OR executor.executor7 = " + eu + " OR executor.executor8 = " + eu
					+ " OR executor.executor9 = " + eu + " \r\n" + "OR executor.executor10 = " + eu + " OR \r\n"
					+ "(tarefa.id_centro_custo in (SELECT vinculos.id_cc FROM vinculos WHERE vinculos.id_usuario = "
					+ Sessao.getInstance().getId() + ") AND \r\n"
					+ "tarefa.id_departamento in (SELECT vinculos.id_dpto FROM vinculos WHERE vinculos.id_usuario = "
					+ Sessao.getInstance().getId() + "))))\r\n";

			sql += "AND tarefa.data_ini\r\n" + "BETWEEN IF(tarefa.stat = 'Feito' OR tarefa.stat = 'Cancelado'," + "'"
					+ data1 + "'" + ",'2014-01-01') AND " + "'" + data2 + "'";
		}

		else {
			sql += "WHERE tarefa.data_ini\r\n" + "BETWEEN IF(tarefa.stat = 'Feito' OR tarefa.stat = 'Cancelado'," + "'"
					+ data1 + "'" + ",'2014-01-01') AND " + "'" + data2 + "'";
		}

		// DPTO
		if (dptoCombo.isEnabled() && dptoCombo.getSelectedIndex() != 0) {
			String dpto = dptoCombo.getSelectedItem().toString();

			sql += "AND tarefa.id_departamento = (SELECT departamento.id_departamento FROM departamento WHERE departamento.departamento = '"
					+ dpto + "')";
		}
		// CC
		if (ccCombo.isEnabled() && ccCombo.getSelectedIndex() != 0) {
			String cc = ccCombo.getSelectedItem().toString();

			sql += "AND tarefa.id_centro_custo = (SELECT centro_custo.id_centro_custo FROM centro_custo WHERE centro_custo.centrocusto = '"
					+ cc + "')";
		}

		// EXECUTOR
		if (ExecCombo.isEnabled() && ExecCombo.getSelectedIndex() != 0) {
			String Exec = "'" + ExecCombo.getSelectedItem().toString() + "'";

			sql += "AND (executor.executor1=" + Exec + "OR executor.executor2=" + Exec + "OR executor.executor3=" + Exec
					+ "OR executor.executor3=" + Exec + "OR executor.executor4=" + Exec + "OR executor.executor5="
					+ Exec + "OR executor.executor6=" + Exec + "OR executor.executor7=" + Exec
					+ "OR executor.executor8=" + Exec + "OR executor.executor9=" + Exec + "OR executor.executor10="
					+ Exec + ")";
		}

		// RESPONSÁVEL
		if (RespCombo.isEnabled() && RespCombo.getSelectedIndex() != 0) {
			String Resp = "'" + RespCombo.getSelectedItem().toString() + "'";

			sql += "AND tarefa.responsavel=" + Resp;
		}

		// AUTORIDADE
		if (AutoCombo.isEnabled() && AutoCombo.getSelectedIndex() != 0) {
			String Auto = "'" + AutoCombo.getSelectedItem().toString() + "'";

			sql += "AND tarefa.autoridade=" + Auto;
		}

		// A FAZER
		if (checkAFazer.isSelected() == true && checkCancelado.isSelected() == false
				&& checkFazendo.isSelected() == false && checkFeito.isSelected() == false) {
			sql += "AND tarefa.stat = 'A fazer'";
		}

		// FAZENDO
		if (checkFazendo.isSelected() == true && checkAFazer.isSelected() == false
				&& checkCancelado.isSelected() == false && checkFeito.isSelected() == false) {
			sql += "AND tarefa.stat = 'Fazendo'";
		}

		// FEITO
		if (checkFeito.isSelected() == true && checkAFazer.isSelected() == false && checkCancelado.isSelected() == false
				&& checkFazendo.isSelected() == false) {
			sql += "AND tarefa.stat = 'Feito'";
		}

		// CANCELADO
		if (checkCancelado.isSelected() == true && checkAFazer.isSelected() == false
				&& checkFazendo.isSelected() == false && checkFeito.isSelected() == false) {
			sql += "AND (tarefa.stat = 'Cancelado')";
		}

		// CANCELADO + FEITO
		if (checkCancelado.isSelected() && checkFeito.isSelected()) {

			sql += "AND (tarefa.stat = 'Cancelado' OR tarefa.stat = 'Feito')";

		}

		// CANCELAD0 + FAZENDO
		if (checkCancelado.isSelected() && checkFazendo.isSelected()) {

			sql += "AND (tarefa.stat = 'Cancelado' OR tarefa.stat = 'Fazendo')";

		}

		// FEITO + FAZENDO
		if (checkFeito.isSelected() && checkFazendo.isSelected()) {

			sql += "AND (tarefa.stat = 'Feito' OR tarefa.stat = 'Fazendo')";

		}

		// CANCELAD0 + FEITO + FAZENDO
		if (checkCancelado.isSelected() && checkFeito.isSelected() && checkFazendo.isSelected()) {

			sql += "AND (tarefa.stat = 'Cancelado' OR tarefa.stat = 'Feito' OR tarefa.stat = 'Fazendo')";

		}

		// CANCELAD0 + A FAZER
		if (checkCancelado.isSelected() && checkAFazer.isSelected()) {

			sql += "AND (tarefa.stat = 'Cancelado' OR tarefa.stat = 'A fazer')";

		}

		// FEITO + A FAZER
		if (checkFeito.isSelected() && checkAFazer.isSelected()) {

			sql += "AND (tarefa.stat = 'Feito' OR tarefa.stat = 'A fazer')";

		}

		// CANCELADO + FEITO + A FAZER
		if (checkCancelado.isSelected() && checkFeito.isSelected() && checkAFazer.isSelected()) {

			sql += "AND (tarefa.stat = 'Cancelado' OR tarefa.stat = 'Feito' OR tarefa.stat = 'A fazer')";

		}

		// FAZENDO + A FAZER
		if (checkFazendo.isSelected() && checkAFazer.isSelected()) {

			sql += "AND (tarefa.stat = 'Fazendo' OR tarefa.stat = 'A fazer')";
			System.out.println(sql);
		}

		// FEITO + FAZENDO + A FAZER
		if (checkFeito.isSelected() && checkFazendo.isSelected() && checkAFazer.isSelected()) {

			sql += "AND (tarefa.stat = 'Feito' OR tarefa.stat = 'Fazendo' OR tarefa.stat = 'A fazer')";

		}

		sql += "\r\nORDER BY tarefa.id_tarefa DESC";
		return sql;
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

	public void CarregaDatas() {
		// PRIMEIRO DIA DO MÉS
		Calendar PrimeiroDiaDoMés = Calendar.getInstance();
		Calendar UltimoDiaDoMés = Calendar.getInstance();

		// primeiro dia do mes
		PrimeiroDiaDoMés.set(Calendar.DAY_OF_MONTH, PrimeiroDiaDoMés.getActualMinimum(Calendar.DAY_OF_MONTH));

		deText.setText((new SimpleDateFormat("dd/MM/yyyy").format(PrimeiroDiaDoMés.getTime())));

		// ultimo dia do mes
		UltimoDiaDoMés.set(Calendar.DAY_OF_MONTH, UltimoDiaDoMés.getActualMaximum(Calendar.DAY_OF_MONTH));

		ateText.setText((new SimpleDateFormat("dd/MM/yyyy").format(UltimoDiaDoMés.getTime())));

	}

	public void CarregaCombobox() {
		CarregarComboBoxDpto();
		CarregarComboBoxCC();
		CarregarPessoa();

	}

	public void CarregarPessoa() {

		String sql = "SELECT nome FROM pessoa ORDER BY nome ASC";

		try {
			bd.st = bd.con.prepareStatement(sql);

			bd.rs = bd.st.executeQuery();

			while (bd.rs.next() == true) {
				ExecCombo.addItem(bd.rs.getString(1));
				RespCombo.addItem(bd.rs.getString(1));
				AutoCombo.addItem(bd.rs.getString(1));

			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO CARREGAR COMBOBOX", 0);
		}

	}

	public void CarregarComboBoxDpto() {
		String sql = "SELECT departamento.departamento FROM departamento ORDER BY departamento ASC";

		try {
			bd.st = bd.con.prepareStatement(sql);

			bd.rs = bd.st.executeQuery();

			while (bd.rs.next() == true) {
				dptoCombo.addItem(bd.rs.getString(1));
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO CARREGAR COMBOBOX", 0);
		}
	}

	public void CarregarComboBoxCC() {
		String sql = "SELECT centrocusto FROM centro_custo ORDER BY centrocusto ASC";

		try {
			bd.st = bd.con.prepareStatement(sql);

			bd.rs = bd.st.executeQuery();

			while (bd.rs.next() == true) {
				ccCombo.addItem(bd.rs.getString(1));
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO CARREGAR COMBOBOX", 0);
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		int posicion = panelLateral.getX();
		if (posicion > -1) {
			Animacion.Animacion.mover_izquierda(0, -264, 2, 2, panelLateral);
		} else {
			Animacion.Animacion.mover_derecha(-264, 0, 2, 2, panelLateral);
		}
	}

	private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {
		x = evt.getX();
		y = evt.getY();
	}

	private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {
		Point mueve = MouseInfo.getPointerInfo().getLocation();
		this.setLocation(mueve.x - x, mueve.y - y);
	}

	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TarefaTela.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TarefaTela.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TarefaTela.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TarefaTela.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					new TarefaTela().setVisible(true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
					Logger.getLogger(TarefaTela.class.getName()).log(Level.SEVERE, null, ex);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Variables declaration - do not modify

	TableRowSorter<TableModel> sorter;
	public String sql;
	BD bd = new BD();
	Controle control = new Controle();
	Tarefa variavel = new Tarefa();
	public TarefaEditTela tela;
	JTable tabela = new JTable();
	JScrollPane sp = new JScrollPane(tabela);
	JPanel centro = new JPanel();
	JTable tabelaMult;
	JScrollPane spMult = new JScrollPane(tabela);
	public JTextField predecessora; // Text usada pra receber text da edit tarefa

	Toolkit r = Toolkit.getDefaultToolkit();
	Dimension d = r.getScreenSize();

	Tarefa tarefa = new Tarefa();

	private CalendarView deText;
	private javax.swing.JButton jButton1;
	private app.bolivia.swing.JCTextField searchText;
	private app.bolivia.swing.JCTextField descText;

	private javax.swing.JCheckBox ExecCheck;
	private javax.swing.JCheckBox RespCheck;
	private javax.swing.JCheckBox AutoCheck;
	private javax.swing.JCheckBox ccCheck;
	private javax.swing.JCheckBox dptoCheck;
	private javax.swing.JComboBox<String> ExecCombo;
	private javax.swing.JComboBox<String> RespCombo;
	private javax.swing.JComboBox<String> ccCombo;
	private javax.swing.JComboBox<String> dptoCombo;
	private javax.swing.JComboBox<String> AutoCombo;
	private javax.swing.JLabel filtrosLabel;
	private javax.swing.JLabel myScrumLabel;
	private javax.swing.JLabel deLabel;
	private javax.swing.JLabel ateLabel;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel panelSuperior;
	private javax.swing.JPanel panelFiltro;
	private CalendarView ateText;
	private javax.swing.JPanel panelMain;
	private javax.swing.JPanel panelLateral;
	private JButton novaTarefa;
	private JButton pesquisarButton;
	private JButton limparButton;
	private JPanel panelBelow;
	private JCheckBox checkAFazer;
	private JCheckBox checkFazendo;
	private JCheckBox checkFeito;
	private GridBagConstraints gbc_panelSuperior;
	private GridBagConstraints gbc_panelLateral;
	private GridBagConstraints gbc_panelMain;
	private JCheckBox checkCancelado;
	private JButton button;
}
