package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.myscrum.controller.Controle;
import com.myscrum.controller.Controlev;
import com.myscrum.model.Sessao;
import com.myscrum.model.TableGrade;
import com.myscrum.model.Hht;
import com.myscrum.model.HhtDAO;
import com.myscrum.model.Tarefa;
import com.towel.swing.calendar.CalendarView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class HhtTela extends JFrame {

	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	
	// variasveis
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTable tabela;
	private JScrollPane sp;
	Controle control = new Controle();
	HhtDAO metodos = new HhtDAO();
	Tarefa variavel = new Tarefa();
	Sessao s = new Sessao();

	private JTextField desvioP1Text;
	private JTextField desvioP2Text;
	private JTextField custoText;
	private CalendarView ini_periodo_text;
	private CalendarView fim_periodo_text;
	JComboBox<String> ccComboBox = new JComboBox<String>();
	JComboBox<String> dptoComboBox = new JComboBox<String>();

	// calculando a dimensão da tela
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension d = tk.getScreenSize();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HhtTela frame = new HhtTela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */

	public HhtTela() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KambanTela.class.getResource("/com/myscrum/assets/setIcon1.png")));
		setTitle("HHT");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Controle.sethhtTela(false);
				metodos.bd.close();
				dispose();
			}
		});

		setExtendedState(MAXIMIZED_BOTH);// Iniciar maximizada
		setBounds(0, 0, d.width + 50, d.height - 25);

		// COMPONENTES
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 128));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 1925, 135);
		contentPane.add(panel_1);

		desvioP1Text = new JTextField();
		desvioP1Text.setBackground(new Color(41, 106, 158));
		desvioP1Text.setBounds(130, 59, 86, 20);
		desvioP1Text.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Desvio Padr\u00E3o:");
		lblNewLabel_1.setBounds(130, 31, 178, 17);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		desvioP2Text = new JTextField();
		desvioP2Text.setBackground(new Color(41, 106, 158));
		desvioP2Text.setBounds(222, 59, 86, 20);
		desvioP2Text.setColumns(10);

		JLabel lblCusto = new JLabel("Custo:");
		lblCusto.setBounds(403, 31, 86, 17);
		lblCusto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCusto.setHorizontalAlignment(SwingConstants.CENTER);

		custoText = new JTextField();
		custoText.setEditable(false);
		custoText.setHorizontalAlignment(SwingConstants.CENTER);
		custoText.setBackground(new Color(41, 106, 158));
		custoText.setForeground(Color.white);
		custoText.setBounds(403, 59, 86, 20);
		custoText.setColumns(10);
		panel_1.setLayout(null);
		panel_1.add(desvioP1Text);
		panel_1.add(desvioP2Text);
		panel_1.add(lblNewLabel_1);
		panel_1.add(lblCusto);
		panel_1.add(custoText);

		Icon icon = new ImageIcon(getClass().getResource("/com/myscrum/assets/calendar.png"));// Criando icone para os botões do
																					// calendar view

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBackground(Color.WHITE);
		panel.setBounds(d.width - 380, 34, 316, 92);
		panel_1.add(panel);
		panel.setLayout(null);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(188, 64, 105, 23);
		panel.add(btnPesquisar);
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPesquisar.setBackground(new Color(163, 184, 204));

		// TEXT DO PERIODO - FIM
		fim_periodo_text = new CalendarView();
		fim_periodo_text.setBounds(188, 26, 122, 25);
		panel.add(fim_periodo_text);
		fim_periodo_text.setIcon(icon);
		fim_periodo_text.getEditor().setBackground(new Color(41, 106, 158));
		fim_periodo_text.getEditor().setForeground(new Color(255, 255, 255));
		fim_periodo_text.setWeekDaysBackground(new Color(41, 106, 158));

		// TEXT DO PERIODO - INICIO
		ini_periodo_text = new CalendarView();
		ini_periodo_text.setBounds(6, 26, 122, 25);
		panel.add(ini_periodo_text);
		ini_periodo_text.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		ini_periodo_text.setIcon(icon);
		ini_periodo_text.getEditor().setBackground(new Color(41, 106, 158));
		ini_periodo_text.getEditor().setForeground(new Color(255, 255, 255));
		ini_periodo_text.setWeekDaysBackground(new Color(41, 106, 158));

		JLabel label = new JLabel("\u00C0");
		label.setBounds(154, 37, 13, 14);
		panel.add(label);

		JLabel lblNewLabel_2 = new JLabel("Periodo de analise");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_2.setBounds(93, 3, 145, 14);
		panel.add(lblNewLabel_2);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBackground(new Color(163, 184, 204));
		btnLimpar.setBounds(16, 64, 105, 23);
		panel.add(btnLimpar);

		// botão limpar
		btnLimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				dptoComboBox.setSelectedIndex(0);
				ccComboBox.setSelectedIndex(0);
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(calcDimension(panel, 350, 100), 34, 350, 92); // panel 2
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblCentroDeCusto = new JLabel("Centro de Custo:");
		lblCentroDeCusto.setBounds(20, 62, 113, 14);
		panel_2.add(lblCentroDeCusto);
		ccComboBox.setBounds(133, 59, 178, 25);
		panel_2.add(ccComboBox);
		dptoComboBox.setBounds(133, 16, 178, 25);
		panel_2.add(dptoComboBox);

		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setBounds(25, 19, 93, 14);
		panel_2.add(lblDepartamento);

		JMenuBar superiorMenu = new JMenuBar();
		superiorMenu.setBounds(0, 0, d.width, 23);
		panel_1.add(superiorMenu);

		JMenu arquivoMenu = new JMenu("Arquivo");
		superiorMenu.add(arquivoMenu);

		JMenuItem exportarMenuItem = new JMenuItem("Exportar");
		exportarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportar();
			}
		});
		arquivoMenu.add(exportarMenuItem);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (ini_periodo_text.getText().equals("") || fim_periodo_text.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Preencha as datas do periodo");
				} else {
					try {
						ExecutarMetodos();
					} catch (ParseException e) {
					
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "HHT Atualizado com sucesso");
				} // fim do else
			}
		});

		metodos.bd.getConnection();//Abri conexão com o banco
		
		// metodos que ocorrem ao iniciar a tela
		CarregaDatasDeVelocidade();

		// DPTO combo box
		dptoComboBox.addItem("Todos");
		CarregarComboBoxDpto();

		// CC combo box
		ccComboBox.addItem("Todos");
		CarregarComboBoxCC();
		
		try {// Executa os metodos ao iniciar a tela
			ExecutarMetodos();
		} catch (ParseException e1) {
		
			e1.printStackTrace();
		}

		Controlev.setLoading(true);// Pasa para variavel de controle de loading que a tela está totalmente
		// carregada

	} // fecha o main

	public void CarregarComboBoxDpto() {
		String sql = "SELECT departamento.departamento FROM departamento";

		try {
			metodos.bd.st = metodos.bd.con.prepareStatement(sql);

			metodos.bd.rs = metodos.bd.st.executeQuery();

			while (metodos.bd.rs.next() == true) {
				dptoComboBox.addItem(metodos.bd.rs.getString(1));
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO CARREGAR COMBOBOX", 0);
		}
		
		dptoComboBox.setSelectedItem(Sessao.getInstance().getDpto());
	}

	// carrega combo box CC
	public void CarregarComboBoxCC() {
		String sql = "SELECT centrocusto FROM centro_custo";

		try {
			metodos.bd.st = metodos.bd.con.prepareStatement(sql);

			metodos.bd.rs = metodos.bd.st.executeQuery();

			while (metodos.bd.rs.next() == true) {
				ccComboBox.addItem(metodos.bd.rs.getString(1));
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO CARREGAR COMBOBOX", 0);
		}
	}

	// metodo que ajusta a posicão do panel com os componetes dentor dele
	public int calcDimension(JPanel panel, int xComp, int varEspaço) {

		int calc = (panel.getBounds().x) - (xComp + varEspaço);

		return calc;
	}

//metodo que lista a Tabela com as informações
	@SuppressWarnings("rawtypes")
	public void listar() {

		if (tabela != null) {// se existir outra tabela montada apaga
			tabela.setVisible(false);
			tabela = null;
			sp.setVisible(false);
			sp = null;
		}

		Vector<ArrayList> matriz = new Vector<ArrayList>();
		matriz.addElement(Hht.getArrayPessoas()); // 0
		matriz.addElement(Hht.getArrayDepartamento()); // 1
		matriz.addElement(Hht.getArrayCentroCusto()); // 2
		if (s.getFuncao() == 1) {
			matriz.addElement(Hht.getArraySalary()); // 3 coluna referente ao sálario(aparecerá somente para usuarios
														// ADM).
		}
		matriz.addElement(Hht.getArrayPontos()); // 4
		matriz.addElement(Hht.getArrayResponsavel()); // 5
		matriz.addElement(Hht.getArrayExecutor()); // 6
		matriz.addElement(Hht.getArrayAutoridade()); // 7
		matriz.addElement(Hht.getArrayChecado()); // 8
		matriz.addElement(Hht.getArrayHorasP()); // 9
		matriz.addElement(Hht.getArrayAtraso()); // 10
		matriz.addElement(Hht.getArrayPontosR()); // 11
		matriz.addElement(Hht.getArrayResponsavelR()); // 12
		matriz.addElement(Hht.getArrayExecutorR()); // 13
		matriz.addElement(Hht.getArrayAutoridadeR()); // 14
		matriz.addElement(Hht.getArrayChecadoR()); // 15
		matriz.addElement(Hht.getArraySoma()); // 16
		matriz.addElement(Hht.getArrayDesvio()); // 17

		// hht.getArrayAutoridadeR()
		// cabeçalho
		Vector<String> cabecalho = new Vector<>();
//--------------------pontos previstos----------------------
		cabecalho.addElement("Funcionário"); // 1
		cabecalho.addElement("Departamento"); // 2
		cabecalho.addElement("Centro de Custo");// 3
		if (s.getFuncao() == 1) {
			cabecalho.addElement("HHT");// 4 // coluna referente ao sálario(aparecerá somente para usuarios ADM).
		}
		cabecalho.addElement("<html><center> Previsto <br/> Pontos <br/> 100%"); // 5
		cabecalho.addElement("<html><center> Previsto <br/> Responsável <br/> 15% </html>"); // 6
		cabecalho.addElement("<html><center> Previsto <br/> Executor <br/>  80% </html> ");// 7
		cabecalho.addElement("<html><center> Previsto <br/> Autoridade <br/>  5% </html>");// 8
		cabecalho.addElement("<html><center> Previsto <br/> Checado <br/> 0% </html>");// 9
//--------------------meio----------------------
		cabecalho.addElement("<html><center> Horas <br/> do <br/> periodo </html>"); // 10
		cabecalho.addElement("<html><center> Atraso <br/> em <br/> porcentagem </html>"); // 11
//--------------------pontos Realizados----------------------		 	
		cabecalho.addElement("<html><center> Realizado <br/> Pontos <br/> 100%"); // 12
		cabecalho.addElement("<html><center> Realizado <br/> Responsável <br/> 15% </html>"); // 13
		cabecalho.addElement("<html><center> Realizado <br/> Executor <br/>  80% </html> ");// 14
		cabecalho.addElement("<html><center> Realizado <br/> Autoridade <br/>  5% </html>");// 15
		cabecalho.addElement("<html><center> Realizado <br/> Checado <br/> 0% </html>");// 16
//--------------------final----------------------
		cabecalho.addElement("Soma"); // 17
		cabecalho.addElement("<html><center> % Desvio <br/> hora <br/> Homem </html>"); // 18

		tabela = TableGrade.getTableSemBanco(matriz, cabecalho);
		sp = new JScrollPane(tabela);
		tabela.setBounds(5, 135, d.width, 400);
		sp.setBounds(0, 135, d.width, 400);
		contentPane.add(sp);
		contentPane.updateUI();

		tabela.setEditingRow(0);
		tabela.setEditingColumn(0);
		tabela.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		int ColumnWidth = 0;
		if (s.getFuncao() == 1) {
			ColumnWidth = 1821;
			tabela.getColumnModel().getColumn(0).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(55);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(93);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(120);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(95);
			tabela.getColumnModel().getColumn(7).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(8).setPreferredWidth(90);
			tabela.getColumnModel().getColumn(9).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(10).setPreferredWidth(90);
			tabela.getColumnModel().getColumn(11).setPreferredWidth(93);
			tabela.getColumnModel().getColumn(12).setPreferredWidth(120);
			tabela.getColumnModel().getColumn(13).setPreferredWidth(95);
			tabela.getColumnModel().getColumn(14).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(15).setPreferredWidth(90);
			tabela.getColumnModel().getColumn(16).setPreferredWidth(90);
			tabela.getColumnModel().getColumn(17).setPreferredWidth(90);

		} else {
			ColumnWidth = 1766;
			tabela.getColumnModel().getColumn(0).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(93);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(120);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(95);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(7).setPreferredWidth(90);
			tabela.getColumnModel().getColumn(8).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(9).setPreferredWidth(90);
			tabela.getColumnModel().getColumn(10).setPreferredWidth(93);
			tabela.getColumnModel().getColumn(11).setPreferredWidth(120);
			tabela.getColumnModel().getColumn(12).setPreferredWidth(95);
			tabela.getColumnModel().getColumn(13).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(14).setPreferredWidth(90);
			tabela.getColumnModel().getColumn(15).setPreferredWidth(90);
			tabela.getColumnModel().getColumn(16).setPreferredWidth(90);
		}

		Dimension DimensionCabecalho = new Dimension(ColumnWidth, 55);
		tabela.getTableHeader().setPreferredSize(DimensionCabecalho);

		metodos.setLoading(true);

	}// fim do metodo listar

//cria o gráfico
//public Component Grafico(){

	// cria um data set
	// DefaultPieDataset pieDataSet = new DefaultPieDataset();

	// Adicinando os dados ao data set

	// pieDataSet.setValue("Abner", new Double(2.6));
	// pieDataSet.setValue("Pedro", new Double(5.4));
	// pieDataSet.setValue("João", new Double(52.6));

	// cria um gráfico passando alguns paramentros
	// org.jfree.chart.JFreeChart grafico = ChartFactory.createPieChart("Dados",
	// pieDataSet, true, true, false);

	// this.pack();
	// this.setBounds(20, 511, 150, 150);
	// return getContentPane().add( new ChartPanel(grafico));

//}

//metodo que carrega as datas iniciais
	public void CarregaDatasDeVelocidade() {
		// PRIMEIRO DIA DO MÉS
		Calendar PrimeiroDiaDoMés = Calendar.getInstance();

		PrimeiroDiaDoMés.set(Calendar.DAY_OF_MONTH, PrimeiroDiaDoMés.getActualMinimum(Calendar.DAY_OF_MONTH));

		ini_periodo_text.setText((new SimpleDateFormat("dd/MM/yyyy").format(PrimeiroDiaDoMés.getTime())));

		// HOJE
		Calendar hoje = Calendar.getInstance();

		fim_periodo_text.setText((new SimpleDateFormat("dd/MM/yyyy").format(hoje.getTime())));

	}

//Executa os metodos de calculo
	public void ExecutarMetodos() throws ParseException {

		// metodos extras de inicio
		metodos.LimparVariaveis();
		metodos.ContarPessoas();
		metodos.ListarPessoas();
		metodos.ListarFeriados();

		// metodos dos pontos previstos
		metodos.chamadaDoBancoResponsavel(ini_periodo_text.getText(), fim_periodo_text.getText(),
				filtroChamadaResposavel());
		metodos.chamadaDoBancoExecutor(ini_periodo_text.getText(), fim_periodo_text.getText(), filtroChamadaExecutor());
		metodos.chamadaDoBancoAutoridade(ini_periodo_text.getText(), fim_periodo_text.getText(),
				filtroChamadaAutoridade());
		metodos.calculaPontos();

		// metodos dos pontos realizados
		metodos.chamadaDoBancoResponsavelR(ini_periodo_text.getText(), fim_periodo_text.getText(),
				filtroChamadaResposavelR());
		metodos.chamadaDoBancoExecutorR(ini_periodo_text.getText(), fim_periodo_text.getText(),
				filtroChamadaExecutorR());
		metodos.chamadaDoBancoAutoridadeR(ini_periodo_text.getText(), fim_periodo_text.getText(),
				filtroChamadaAutoridadeR());
		metodos.calculaPontosR();

		// metodos extras 2
		metodos.HHT_Salary();
		metodos.Atraso();
		metodos.horas_periodo(ini_periodo_text.getText(), fim_periodo_text.getText());
		metodos.DesvioHoraHomem();
		metodos.SomaHHT();
		metodos.Custo();

		// metodos finais
		custoText.setText("R$:  " + String.valueOf(metodos.getCusto()).replace(".", ","));
		listar();
	}

//---------------------filtros para pontos previstos -------------------------
	public String filtroChamadaResposavel() {

		String ini = DataParaoBanco(ini_periodo_text.getText());
		String fim = DataParaoBanco(fim_periodo_text.getText());

		String sql = "SELECT tamanho.peso AS Peso, tarefa.porcentagem AS Porcentagem_tarefa, \r\n"
				+ "tarefa.data_real AS Data_Real, tarefa.data_fim AS Data_Final \r\n" + "FROM tamanho \r\n"
				+ "INNER JOIN tarefa \r\n" + "ON tarefa.id_tamanho=tamanho.id_tamanho \r\n"
				+ "WHERE tarefa.responsavel=? \r\n" + "AND IF(tarefa.stat = 'Feito',((tarefa.data_ini  < '" + ini
				+ "' AND tarefa.data_fim > '" + fim + "') OR (tarefa.data_ini BETWEEN '" + ini + "' AND '" + fim
				+ "' OR tarefa.data_fim BETWEEN '" + ini + "' AND '" + fim + "')),tarefa.stat != 'Cancelado')\r\n";

		if (dptoComboBox.getSelectedIndex() != 0) {
			String dpto = dptoComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_departamento = (SELECT departamento.id_departamento FROM departamento WHERE departamento.departamento = '"
					+ dpto + "')";
		}

		if (ccComboBox.getSelectedIndex() != 0) {
			String cc = ccComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_centro_custo = (SELECT centro_custo.id_centro_custo FROM centro_custo WHERE centro_custo.centrocusto = '"
					+ cc + "')";
		}

		return sql;
	}

	public String filtroChamadaExecutor() {

		String ini = DataParaoBanco(ini_periodo_text.getText());
		String fim = DataParaoBanco(fim_periodo_text.getText());

		String sql = "SELECT tamanho.peso AS peso, tarefa.porcentagem AS Porcentagem_tarefa, \r\n" + "(SELECT "
				+ "IF(executor.executor1=? ,executor.porcento1, \r\n"
				+ "IF(executor.executor2=?,executor.porcento2, \r\n"
				+ "IF(executor.executor3=?,executor.porcento3, \r\n"
				+ "IF(executor.executor4=?,executor.porcento4, \r\n"
				+ "IF(executor.executor5=?,executor.porcento5, \r\n"
				+ "IF(executor.executor6=?,executor.porcento6, \r\n"
				+ "IF(executor.executor7=?,executor.porcento7, \r\n"
				+ "IF(executor.executor8=?,executor.porcento8, \r\n"
				+ "IF(executor.executor9=?,executor.porcento9, \r\n"
				+ "IF(executor.executor10=?,executor.porcento10,'0'))))))))))) AS Porcentagem_excutor,  \r\n"
				+ "tarefa.data_real AS Data_Real, tarefa.data_fim AS Data_Final  \r\n" + "FROM tamanho  \r\n"
				+ "INNER JOIN tarefa  \r\n" + "ON tarefa.id_tamanho=tamanho.id_tamanho  \r\n"
				+ "INNER JOIN executor  \r\n" + "ON executor.id_tarefa=tarefa.id_tarefa  \r\n"
				+ "WHERE (executor.executor1=?  \r\n" + "OR executor.executor2= ?  \r\n"
				+ "OR executor.executor3= ?  \r\n" + "OR executor.executor4= ?  \r\n" + "OR executor.executor5= ?  \r\n"
				+ "OR executor.executor6= ?  \r\n" + "OR executor.executor7= ?  \r\n" + "OR executor.executor8= ? \r\n"
				+ "OR executor.executor9= ?  \r\n" + "OR executor.executor10=?) \r\n"
				+ "AND IF(tarefa.stat = 'Feito',((tarefa.data_ini  < '" + ini + "' AND tarefa.data_fim > '" + fim
				+ "') OR (tarefa.data_ini BETWEEN '" + ini + "' AND '" + fim + "' OR tarefa.data_fim BETWEEN '" + ini
				+ "' AND '" + fim + "')),tarefa.stat != 'Cancelado')\r\n";

		if (dptoComboBox.getSelectedIndex() != 0) {
			String dpto = dptoComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_departamento = (SELECT departamento.id_departamento FROM departamento WHERE departamento.departamento = '"
					+ dpto + "')";
		}

		if (ccComboBox.getSelectedIndex() != 0) {
			String cc = ccComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_centro_custo = (SELECT centro_custo.id_centro_custo FROM centro_custo WHERE centro_custo.centrocusto = '"
					+ cc + "')";
		}
		
		return sql;
	}

	public String filtroChamadaAutoridade() {

		String ini = DataParaoBanco(ini_periodo_text.getText());
		String fim = DataParaoBanco(fim_periodo_text.getText());

		String sql = "SELECT tamanho.peso AS Peso, tarefa.porcentagem AS Porcentagem_tarefa, \r\n"
				+ "tarefa.data_real AS Data_Real, tarefa.data_fim AS Data_Final \r\n" + "FROM tamanho \r\n"
				+ "INNER JOIN tarefa \r\n" + "ON tarefa.id_tamanho=tamanho.id_tamanho \r\n"
				+ "WHERE tarefa.autoridade=?\r\n" + "AND IF(tarefa.stat = 'Feito',((tarefa.data_ini  < '" + ini
				+ "' AND tarefa.data_fim > '" + fim + "') OR (tarefa.data_ini BETWEEN '" + ini + "' AND '" + fim
				+ "' OR tarefa.data_fim BETWEEN '" + ini + "' AND '" + fim + "')),tarefa.stat != 'Cancelado')\r\n";

		if (dptoComboBox.getSelectedIndex() != 0) {
			String dpto = dptoComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_departamento = (SELECT departamento.id_departamento FROM departamento WHERE departamento.departamento = '"
					+ dpto + "')";
		}

		if (ccComboBox.getSelectedIndex() != 0) {
			String cc = ccComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_centro_custo = (SELECT centro_custo.id_centro_custo FROM centro_custo WHERE centro_custo.centrocusto = '"
					+ cc + "')";
		}

		return sql;
	}

//-----------------filtros para pontos realizados-----------------------------
	public String filtroChamadaResposavelR() {

		String ini = DataParaoBanco(ini_periodo_text.getText());
		String fim = DataParaoBanco(fim_periodo_text.getText());

		String sql = "SELECT tamanho.peso AS Peso, tarefa.porcentagem AS Porcentagem_tarefa, tarefa.data_real AS Data_Real, tarefa.data_fim AS Data_Final \r\n"
				+ "FROM tamanho\r\n" + "INNER JOIN tarefa\r\n" + "ON tarefa.id_tamanho=tamanho.id_tamanho\r\n"
				+ "WHERE tarefa.responsavel=? " + "AND IF(tarefa.stat = 'Feito',((tarefa.data_ini  < '" + ini
				+ "' AND tarefa.data_fim > '" + fim + "') OR (tarefa.data_ini BETWEEN '" + ini + "' AND '" + fim
				+ "' OR tarefa.data_fim BETWEEN '" + ini + "' AND '" + fim + "')),tarefa.stat = 'Fazendo')\r\n";

		if (dptoComboBox.getSelectedIndex() != 0) {
			String dpto = dptoComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_departamento = (SELECT departamento.id_departamento FROM departamento WHERE departamento.departamento = '"
					+ dpto + "')";
		}

		if (ccComboBox.getSelectedIndex() != 0) {
			String cc = ccComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_centro_custo = (SELECT centro_custo.id_centro_custo FROM centro_custo WHERE centro_custo.centrocusto = '"
					+ cc + "')";
		}

		return sql;
	}

	public String filtroChamadaExecutorR() {

		String ini = DataParaoBanco(ini_periodo_text.getText());
		String fim = DataParaoBanco(fim_periodo_text.getText());

		String sql = "SELECT tamanho.peso AS peso, tarefa.porcentagem AS Porcentagem_tarefa, \r\n" + "(SELECT "
				+ "IF(executor.executor1=? ,executor.porcento1, \r\n"
				+ "IF(executor.executor2=?,executor.porcento2, \r\n"
				+ "IF(executor.executor3=?,executor.porcento3, \r\n"
				+ "IF(executor.executor4=?,executor.porcento4, \r\n"
				+ "IF(executor.executor5=?,executor.porcento5, \r\n"
				+ "IF(executor.executor6=?,executor.porcento6, \r\n"
				+ "IF(executor.executor7=?,executor.porcento7, \r\n"
				+ "IF(executor.executor8=?,executor.porcento8, \r\n"
				+ "IF(executor.executor9=?,executor.porcento9, \r\n"
				+ "IF(executor.executor10=?,executor.porcento10,'0'))))))))))) AS Porcentagem_executor,  \r\n"
				+ "tarefa.data_real AS Data_Real, tarefa.data_fim AS Data_Final  \r\n" + "FROM tamanho  \r\n"
				+ "INNER JOIN tarefa  \r\n" + "ON tarefa.id_tamanho=tamanho.id_tamanho  \r\n"
				+ "INNER JOIN executor  \r\n" + "ON executor.id_tarefa=tarefa.id_tarefa  \r\n"
				+ "WHERE (executor.executor1=?  \r\n" + "OR executor.executor2= ?  \r\n"
				+ "OR executor.executor3= ?  \r\n" + "OR executor.executor4= ?  \r\n" + "OR executor.executor5= ?  \r\n"
				+ "OR executor.executor6= ?  \r\n" + "OR executor.executor7= ?  \r\n" + "OR executor.executor8= ? \r\n"
				+ "OR executor.executor9= ?  \r\n" + "OR executor.executor10=?)\r\n"
				+ "AND IF(tarefa.stat = 'Feito',((tarefa.data_ini  < " + "'" + ini + "'" + "AND tarefa.data_fim >" + "'"
				+ fim + "'" + ") OR (tarefa.data_ini BETWEEN" + "'" + ini + "'" + "AND" + "'" + fim + "'"
				+ "OR tarefa.data_fim BETWEEN" + "'" + ini + "'" + "AND" + "'" + fim
				+ "')),tarefa.stat = 'Fazendo')\r\n";

		if (dptoComboBox.getSelectedIndex() != 0) {
			String dpto = dptoComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_departamento = (SELECT departamento.id_departamento FROM departamento WHERE departamento.departamento = '"
					+ dpto + "')";
		}

		if (ccComboBox.getSelectedIndex() != 0) {
			String cc = ccComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_centro_custo = (SELECT centro_custo.id_centro_custo FROM centro_custo WHERE centro_custo.centrocusto = '"
					+ cc + "')";

		}
		return sql;

	}

	public String filtroChamadaAutoridadeR() {

		String ini = DataParaoBanco(ini_periodo_text.getText());
		String fim = DataParaoBanco(fim_periodo_text.getText());

		String sql = "SELECT tamanho.peso AS Peso, tarefa.porcentagem AS Porcentagem_tarefa, \r\n"
				+ "tarefa.data_real AS Data_Real, tarefa.data_fim AS Data_Final \r\n" + "FROM tamanho \r\n"
				+ "INNER JOIN tarefa \r\n" + "ON tarefa.id_tamanho=tamanho.id_tamanho \r\n"
				+ "WHERE (tarefa.stat = 'Feito' OR tarefa.stat = 'Fazendo') AND tarefa.autoridade=?"
				+ "AND IF(tarefa.stat = 'Feito',((tarefa.data_ini  < '" + ini + "' AND tarefa.data_fim > '" + fim
				+ "') OR (tarefa.data_ini BETWEEN '" + ini + "' AND '" + fim + "' OR tarefa.data_fim BETWEEN '" + ini
				+ "' AND '" + fim + "')),tarefa.stat = 'Fazendo')\r\n";

		if (dptoComboBox.getSelectedIndex() != 0) {
			String dpto = dptoComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_departamento = (SELECT departamento.id_departamento FROM departamento WHERE departamento.departamento = '"
					+ dpto + "')";
		}

		if (ccComboBox.getSelectedIndex() != 0) {
			String cc = ccComboBox.getSelectedItem().toString();

			sql += "AND tarefa.id_centro_custo = (SELECT centro_custo.id_centro_custo FROM centro_custo WHERE centro_custo.centrocusto = '"
					+ cc + "')";

		}

		return sql;
	}

//Metodo auxiliar
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

	public void toExcel(JTable table, File file) {
		try {
			JTable model = table;
			FileWriter excel = new FileWriter(file);

			Sessao sessao = new Sessao();

			if (sessao.getFuncao() == 1) {
				excel.write("Funcionário" + "\t");
				excel.write("Departamento" + "\t");
				excel.write("Centro de Custo" + "\t");
				excel.write("HHT" + "\t");
				excel.write("Pontos previsto 100%" + "\t");
				excel.write("Responsavel previsto 15%" + "\t");
				excel.write("Executor previsto 80%" + "\t");
				excel.write("Autoridade previsto 5%" + "\t");
				excel.write("Checado previsto 0%" + "\t");
				excel.write("Horas do periodo" + "\t");
				excel.write("Atraso em porcentagem" + "\t");
				excel.write("Pontos realizados 100%" + "\t");
				excel.write("Responsavel realizados 15%" + "\t");
				excel.write("Executor realizado 80%" + "\t");
				excel.write("Autoridade realizado 5%" + "\t");
				excel.write("Checado realizado 0%" + "\t");
				excel.write("Soma" + "\t");
				excel.write("Desvio hora homem" + "\t");

			} else {
				excel.write("Funcionário" + "\t");
				excel.write("Departamento" + "\t");
				excel.write("Centro de Custo" + "\t");
				excel.write("Pontos previsto 100%" + "\t");
				excel.write("Responsavel previsto 15%" + "\t");
				excel.write("Executor previsto 80%" + "\t");
				excel.write("Autoridade previsto 5%" + "\t");
				excel.write("Checado previsto 0%" + "\t");
				excel.write("Horas do periodo" + "\t");
				excel.write("Atraso em porcentagem" + "\t");
				excel.write("Pontos realizados 100%" + "\t");
				excel.write("Responsavel realizados 15%" + "\t");
				excel.write("Executor realizado 80%" + "\t");
				excel.write("Autoridade realizado 5%" + "\t");
				excel.write("Checado realizado 0%" + "\t");
				excel.write("Soma" + "\t");
				excel.write("Desvio hora homem" + "\t");
			}
			excel.write("\n");

			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					Object valor = model.getValueAt(i, j);
					System.out.println(i + " e " + j);

					if (valor == null || valor == "") {
						excel.write("" + "\t");
					} else {
						String linhas[] = valor.toString().split("\n");
						String valor1 = "";

						valor1 += String.join(" ", linhas);

						excel.write(valor1 + "\t");
					}
				}
				excel.write("\n");
			}

			JOptionPane.showMessageDialog(null, "Arquivo exportado com sucesso", "Sucesso", 1);
			excel.close();
		} catch (IOException e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Falha ao exportar o arquivo", "Falha", 0);
		}
	}

	public void exportar() {
		JFileChooser fc = new JFileChooser();
		int option = fc.showSaveDialog(HhtTela.this);
		if (option == JFileChooser.APPROVE_OPTION) {
			String filename = fc.getSelectedFile().getName();
			String path = fc.getSelectedFile().getParentFile().getPath();

			int len = filename.length();
			String ext = "";
			String file = "";

			if (len > 4) {
				ext = filename.substring(len - 4, len);
			}

			if (ext.equals(".xls")) {
				file = path + "\\" + filename;
			} else {
				file = path + "\\" + filename + ".xls";
			}
			toExcel(tabela, new File(file));
		}
	}

} // fecha a class
