package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.myscrum.controller.Controle;
import com.myscrum.model.Sessao;
import com.myscrum.model.kamban;
import com.myscrum.model.KambanDAO;
import com.towel.swing.calendar.CalendarView;

import javax.swing.UIManager;

public class KambanTela extends JFrame {

	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PainelCentral;
	public JComboBox<String> ccComboBox;
	public JComboBox<String> dptoComboBox;
	public JComboBox<String> ExeRespAutComboBox;
	private JTextField producao1Text;
	private JTextField producaogeralText;
	private JTextField tarefasnaofeitasText;
	private JTextField realizadohojeText;
	public JButton pesquisarButton;

	KambanDAO metodos = new KambanDAO();
	private JTextField velocidadeText;
	private JTextField velocidadeAnteriorText;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField pontosPrevistoText;
	private CalendarView iniPeriodo;
	private CalendarView fimPeriodo;
	public JLabel paginaAFazerLabel;
	public JLabel paginaFazendoLabel;
	public JLabel paginaFeitoLabel;
	public JLabel qtdTarefasAfazerLabel;
	public JLabel tarefasAfazerLabel;
	public JLabel qtdTarefasFazendoLabel;
	public JLabel tarefasFazendoLabel;
	public JLabel qtdTarefasFeitoLabel;
	public JLabel tarefasFeitoLabel;
	public int paginaAFazer = 0;
	public int paginaFazendo = 0;
	public int paginaFeito = 0;
	public int voltarAfazer = 1;
	public int voltarFazendo = 1;
	public int voltarFeito = 1;
	public JButton proximoAFazerButton;
	public JButton proximoFazendoButton;
	public JButton proximoFeitoButton;
	Kamban Graficos[][] = new Kamban[5][5];

	public Kamban kamban_0;
	public Kamban kamban_1;
	public Kamban kamban_2;
	public Kamban kamban_3;
	public Kamban kamban_4;
	public Kamban kamban_5;
	public Kamban kamban_6;
	public Kamban kamban_7;
	public Kamban kamban_8;
	public Kamban kamban_9;
	public Kamban kamban_10;
	public Kamban kamban_11;
	public Kamban kamban_12;
	public Kamban kamban_13;
	public Kamban kamban_14;
	public Kamban kamban_15;
	public Kamban kamban_16;
	public Kamban kamban_17;
	public Kamban kamban_18;
	public Kamban kamban_19;
	public Kamban kamban_20;
	public Kamban kamban_21;
	public Kamban kamban_22;
	public Kamban kamban_23;
	public Kamban kamban_24;

	public int xAfazer;
	public int xFazendo;
	public int xFeito;
	private JPanel Painel1;
	private JPanel Painel2;
	private JPanel Painel3;
	private JPanel Painel4;
	private JPanel Painel5;
	private JPanel Painel6;
	private JPanel Painel7;
	private JLabel producao1Label;
	private JLabel producaogeralLabel;
	private JLabel tarefasnaofeitasLabel;
	private JLabel pontosrealizadoshojeLabel;
	private JButton anteriorAFazerButton;
	private JButton anteriorFazendoButton;
	private JButton anteriorFeitoButton;
	private JPanel PainelSuperior;
	private JScrollPane Jscroll;
	
	public JTextField predecessora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KambanTela frame = new KambanTela();
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

	public KambanTela() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KambanTela.class.getResource("/com/myscrum/assets/setIcon1.png")));
		setTitle("Scrum");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				KambanDAO.bdAFazer.close();
				KambanDAO.bdFazendo.close();
				KambanDAO.bdFazendo.close();
				KambanDAO.bd.close();

				Controle.setKamban(false);
				dispose();
			}

		});

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();

		setExtendedState(MAXIMIZED_BOTH);
		setBounds(0, 0, d.width + 50, d.height);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		PainelCentral = new JPanel();
		PainelCentral.setBackground(Color.WHITE);
		PainelCentral.setBounds(0, 185, d.width, d.height);
		contentPane.add(PainelCentral);

		xAfazer = 195;
		xFazendo = 723;
		xFeito = 1168;

		kamban_0 = new Kamban();
		Graficos[0][0] = kamban_0;

		kamban_1 = new Kamban();
		Graficos[0][1] = kamban_1;

		kamban_2 = new Kamban();
		Graficos[2][0] = kamban_2;

		kamban_3 = new Kamban();
		Graficos[2][1] = kamban_3;

		kamban_4 = new Kamban();
		Graficos[4][0] = kamban_4;

		kamban_5 = new Kamban();
		Graficos[0][2] = kamban_5;

		kamban_6 = new Kamban();
		Graficos[0][3] = kamban_6;

		kamban_7 = new Kamban();
		Graficos[2][2] = kamban_7;

		kamban_8 = new Kamban();
		Graficos[2][3] = kamban_8;

		kamban_9 = new Kamban();
		Graficos[4][1] = kamban_9;

		kamban_10 = new Kamban();
		Graficos[0][4] = kamban_10;

		kamban_11 = new Kamban();
		Graficos[1][0] = kamban_11;

		kamban_12 = new Kamban();
		Graficos[2][4] = kamban_12;

		kamban_13 = new Kamban();
		Graficos[3][0] = kamban_13;

		kamban_14 = new Kamban();
		Graficos[4][2] = kamban_14;

		kamban_15 = new Kamban();
		Graficos[1][1] = kamban_15;

		kamban_16 = new Kamban();
		Graficos[1][2] = kamban_16;

		kamban_17 = new Kamban();
		Graficos[3][1] = kamban_17;

		kamban_18 = new Kamban();
		Graficos[3][2] = kamban_18;

		kamban_19 = new Kamban();
		Graficos[4][3] = kamban_19;

		kamban_20 = new Kamban();
		Graficos[1][3] = kamban_20;

		kamban_21 = new Kamban();
		Graficos[1][4] = kamban_21;

		kamban_22 = new Kamban();
		Graficos[3][3] = kamban_22;

		kamban_23 = new Kamban();
		Graficos[3][4] = kamban_23;

		kamban_24 = new Kamban();
		Graficos[4][4] = kamban_24;

		GroupLayout gl_PainelCentral = new GroupLayout(PainelCentral);
		gl_PainelCentral
				.setHorizontalGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_PainelCentral.createSequentialGroup().addContainerGap()
								.addGroup(gl_PainelCentral
										.createParallelGroup(
												Alignment.LEADING)
										.addGroup(
												gl_PainelCentral.createSequentialGroup()
														.addComponent(kamban_0, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 / 2)
														.addComponent(kamban_1, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27)
														.addComponent(kamban_2, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 / 2).addComponent(kamban_3,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE)
														.addGap(27 * 2).addComponent(kamban_4,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_PainelCentral.createSequentialGroup()
														.addComponent(kamban_5, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 / 2)
														.addComponent(kamban_6, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27)
														.addComponent(kamban_7, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 / 2).addComponent(kamban_8,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE)
														.addGap(27 * 2).addComponent(kamban_9,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_PainelCentral.createSequentialGroup()
														.addComponent(kamban_10, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 / 2)
														.addComponent(kamban_11, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27)
														.addComponent(kamban_12, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 / 2).addComponent(kamban_13,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE)
														.addGap(27 * 2).addComponent(kamban_14,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_PainelCentral.createSequentialGroup()
														.addComponent(kamban_15, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 / 2)
														.addComponent(kamban_16, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27)
														.addComponent(kamban_17, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 / 2)
														.addComponent(kamban_18, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 * 2).addComponent(kamban_19,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_PainelCentral.createSequentialGroup()
														.addComponent(kamban_20, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 / 2)
														.addComponent(kamban_21, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27)
														.addComponent(kamban_22, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 / 2)
														.addComponent(kamban_23, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(27 * 2).addComponent(kamban_24,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(101, Short.MAX_VALUE)));
		gl_PainelCentral.setVerticalGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PainelCentral.createSequentialGroup().addContainerGap()
						.addGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(kamban_4, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_3, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_2, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_1, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_0, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(kamban_5, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_6, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_7, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_8, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_9, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(kamban_10, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_11, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_12, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_13, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_14, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(kamban_15, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_16, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_17, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_18, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_19, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(kamban_20, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_21, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_22, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_23, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_24, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		PainelCentral.setLayout(gl_PainelCentral);

		Icon icon = new ImageIcon(getClass().getResource("/com/myscrum/assets/calendar.png"));// Criando icone para os botï¿½es do

		// superior

		PainelSuperior = new JPanel();
		PainelSuperior.setBorder(null);
		PainelSuperior.setBackground(Color.WHITE);
		PainelSuperior.setBounds(0, 0, d.width, 144);
		contentPane.add(PainelSuperior);
		PainelSuperior.setLayout(null);

		Painel1 = new JPanel();
		Painel1.setBackground(Color.WHITE);
		Painel1.setBounds(5, 5, 194, 135);
		PainelSuperior.add(Painel1);
		Painel1.setLayout(null);

		tarefasnaofeitasText = new JTextField();
		tarefasnaofeitasText.setBounds(108, 34, 65, 20);
		Painel1.add(tarefasnaofeitasText);
		tarefasnaofeitasText.setEditable(false);
		tarefasnaofeitasText.setBackground(new Color(0, 102, 153));
		tarefasnaofeitasText.setForeground(new Color(255, 255, 255));
		tarefasnaofeitasText.setColumns(10);

		producao1Label = new JLabel("<html><center>% Produ\u00E7\u00E3o<br/>\r\n(prioridade 1): </html>");
		producao1Label.setBounds(0, 5, 88, 28);
		Painel1.add(producao1Label);
		producao1Label.setHorizontalAlignment(SwingConstants.CENTER);
		producao1Label.setFont(new Font("Tahoma", Font.BOLD, 11));

		producao1Text = new JTextField();
		producao1Text.setBounds(10, 34, 65, 20);
		Painel1.add(producao1Text);
		producao1Text.setEditable(false);
		producao1Text.setForeground(new Color(255, 255, 255));
		producao1Text.setBackground(new Color(41, 106, 158));
		producao1Text.setColumns(10);

		realizadohojeText = new JTextField();
		realizadohojeText.setBounds(108, 106, 65, 20);
		Painel1.add(realizadohojeText);
		realizadohojeText.setEditable(false);
		realizadohojeText.setBackground(new Color(0, 102, 153));
		realizadohojeText.setForeground(new Color(255, 255, 255));
		realizadohojeText.setColumns(10);

		producaogeralLabel = new JLabel("<html><center>% Produ\u00E7\u00E3o<br/>\r\ngeral: <html/>\r\n");
		producaogeralLabel.setBounds(0, 77, 88, 28);
		Painel1.add(producaogeralLabel);
		producaogeralLabel.setHorizontalAlignment(SwingConstants.CENTER);
		producaogeralLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		producaogeralText = new JTextField();
		producaogeralText.setBounds(10, 106, 65, 20);
		Painel1.add(producaogeralText);
		producaogeralText.setEditable(false);
		producaogeralText.setForeground(new Color(255, 255, 255));
		producaogeralText.setBackground(new Color(41, 106, 158));
		producaogeralText.setColumns(10);

		tarefasnaofeitasLabel = new JLabel("<html><center>Qtd. de tarefas <br/>\r\nn\u00E3o feitas:<html/>");
		tarefasnaofeitasLabel.setBounds(94, 5, 88, 28);
		Painel1.add(tarefasnaofeitasLabel);
		tarefasnaofeitasLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		pontosrealizadoshojeLabel = new JLabel("<html><center>Pontos realizados<br/>\r\n hoje:<html/>");
		pontosrealizadoshojeLabel.setBounds(88, 77, 106, 28);
		Painel1.add(pontosrealizadoshojeLabel);
		pontosrealizadoshojeLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		Painel2 = new JPanel();
		Painel2.setBackground(Color.WHITE);
		Painel2.setBounds(235, 5, 235, 91);
		PainelSuperior.add(Painel2);

		JLabel velocidadeMediaLabel = new JLabel("Velocidade m\u00E9dia (pontos/dias uteis):  ");
		velocidadeMediaLabel.setBounds(12, 0, 220, 14);
		velocidadeMediaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label = new JLabel("jun/18:");
		label.setBounds(22, 19, 35, 14);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));

		velocidadeAnteriorText = new JTextField();
		velocidadeAnteriorText.setBounds(57, 15, 42, 20);
		velocidadeAnteriorText.setBackground(new Color(0, 102, 153));
		velocidadeAnteriorText.setForeground(new Color(255, 255, 255));
		velocidadeAnteriorText.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("jun/18:");
		lblNewLabel_3.setBounds(135, 19, 35, 14);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));

		velocidadeText = new JTextField();
		velocidadeText.setBounds(169, 15, 42, 20);
		velocidadeText.setBackground(new Color(0, 102, 153));
		velocidadeText.setForeground(new Color(255, 255, 255));
		velocidadeText.setColumns(10);

		JLabel velocidadePeriodoLabel = new JLabel("Velocidade periodo (pontos): ");
		velocidadePeriodoLabel.setBounds(32, 46, 165, 14);
		velocidadePeriodoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		Painel2.setLayout(null);
		Painel2.add(velocidadeMediaLabel);
		Painel2.add(label);
		Painel2.add(velocidadeAnteriorText);
		Painel2.add(lblNewLabel_3);
		Painel2.add(velocidadeText);
		Painel2.add(velocidadePeriodoLabel);

		JLabel lblNewLabel_2 = new JLabel("Atual:");
		lblNewLabel_2.setBounds(27, 72, 30, 14);
		Painel2.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblAnterior = new JLabel("Anterior:");
		lblAnterior.setBounds(127, 72, 43, 14);
		Painel2.add(lblAnterior);
		lblAnterior.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textField_2 = new JTextField();
		textField_2.setBounds(169, 68, 42, 20);
		Painel2.add(textField_2);
		textField_2.setBackground(new Color(0, 102, 153));
		textField_2.setForeground(new Color(255, 255, 255));
		textField_2.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(57, 68, 42, 20);
		Painel2.add(textField_1);
		textField_1.setBackground(new Color(0, 102, 153));
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setColumns(10);

		Painel3 = new JPanel();
		Painel3.setBackground(Color.WHITE);
		Painel3.setBounds(515, 5, 100, 112);
		PainelSuperior.add(Painel3);
		Painel3.setLayout(null);

		JLabel pontoPrevistoLabel = new JLabel("<html><center>Pontos previsto<html/>");
		pontoPrevistoLabel.setBounds(5, 0, 94, 14);
		Painel3.add(pontoPrevistoLabel);
		pontoPrevistoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		pontosPrevistoText = new JTextField();
		pontosPrevistoText.setBounds(28, 15, 40, 20);
		Painel3.add(pontosPrevistoText);
		pontosPrevistoText.setBackground(new Color(0, 102, 153));
		pontosPrevistoText.setForeground(new Color(255, 255, 255));
		pontosPrevistoText.setColumns(10);

		JLabel lentoEmLabel = new JLabel("Lento em");
		lentoEmLabel.setBounds(20, 46, 60, 14);
		Painel3.add(lentoEmLabel);
		lentoEmLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		textField = new JTextField();
		textField.setBounds(28, 68, 40, 20);
		Painel3.add(textField);
		textField.setBackground(new Color(0, 102, 153));
		textField.setForeground(new Color(255, 255, 255));
		textField.setColumns(10);

		JLabel ponto_diaLabel = new JLabel("pontos/dia");
		ponto_diaLabel.setBounds(17, 95, 71, 14);
		Painel3.add(ponto_diaLabel);
		ponto_diaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		Painel4 = new JPanel();
		Painel4.setBackground(Color.WHITE);
		Painel4.setBounds(d.width - 703, 7, 698, 89);
		PainelSuperior.add(Painel4);
		Painel4.setLayout(null);

		iniPeriodo = new CalendarView();
		iniPeriodo.setBounds(414, 5, 130, 25);
		Painel4.add(iniPeriodo);
		iniPeriodo.setIcon(icon);
		iniPeriodo.getEditor().setBackground(new Color(41, 106, 158));
		iniPeriodo.getEditor().setForeground(new Color(255, 255, 255));
		iniPeriodo.setWeekDaysBackground(new Color(41, 106, 158));

		fimPeriodo = new CalendarView();
		fimPeriodo.setBounds(556, 5, 130, 25);
		Painel4.add(fimPeriodo);
		fimPeriodo.setIcon(icon);
		fimPeriodo.getEditor().setBackground(new Color(41, 106, 158));
		fimPeriodo.getEditor().setForeground(new Color(255, 255, 255));
		fimPeriodo.setWeekDaysBackground(new Color(41, 106, 158));

		JButton limparButton = new JButton("Limpar");
		limparButton.setBounds(414, 41, 130, 23);
		Painel4.add(limparButton);
		limparButton.setForeground(new Color(255, 255, 255));
		limparButton.setBackground(new Color(41, 106, 158));

		pesquisarButton = new JButton("Atualizar");
		pesquisarButton.setBounds(556, 41, 130, 23);
		Painel4.add(pesquisarButton);
		pesquisarButton.setForeground(new Color(255, 255, 255));
		pesquisarButton.setBackground(new Color(41, 106, 158));
		pesquisarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarregarDadosKambans();

				JOptionPane.showMessageDialog(null, "Kamban atualizado com sucesso", "Atualizado", 1);
			}
		});
		pesquisarButton.setMnemonic(KeyEvent.VK_F5);

		JLabel ExeRespAutLabel = new JLabel("Exe. / Resp. / Autoridade : ");
		ExeRespAutLabel.setBounds(5, 65, 180, 14);
		Painel4.add(ExeRespAutLabel);
		ExeRespAutLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		ExeRespAutLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel dptoLabel = new JLabel("Dpto. / terceiro / impedimento :");
		dptoLabel.setBounds(5, 5, 180, 14);
		Painel4.add(dptoLabel);
		dptoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dptoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel ccLabel = new JLabel("Centro de Custo : ");
		ccLabel.setBounds(5, 35, 180, 14);
		Painel4.add(ccLabel);
		ccLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		ccLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		ExeRespAutComboBox = new JComboBox<String>();
		ExeRespAutComboBox.setBounds(194, 65, 150, 20);
		Painel4.add(ExeRespAutComboBox);
		ExeRespAutComboBox.setBackground(new Color(0, 102, 153));
		ExeRespAutComboBox.setForeground(new Color(255, 255, 255));

		ccComboBox = new JComboBox<String>();
		ccComboBox.setBounds(194, 35, 150, 20);
		Painel4.add(ccComboBox);
		ccComboBox.setBackground(new Color(0, 102, 153));
		ccComboBox.setForeground(new Color(255, 255, 255));

		dptoComboBox = new JComboBox<String>();
		dptoComboBox.setBounds(194, 5, 150, 20);
		Painel4.add(dptoComboBox);
		dptoComboBox.setBackground(new Color(0, 102, 153));
		dptoComboBox.setForeground(new Color(255, 255, 255));
		dptoComboBox.addItem("Todos");
		ccComboBox.addItem("Todos");
		ExeRespAutComboBox.addItem("Todos");

		Painel5 = new JPanel();
		Painel5.setBackground(Color.WHITE);
		Painel5.setBounds(1140, 90, 200, 54);
		PainelSuperior.add(Painel5);
		Painel5.setLayout(null);

		JLabel feitoLabel = new JLabel("Feito");
		feitoLabel.setBounds(0, 0, 130, 40);
		Painel5.add(feitoLabel);
		feitoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		feitoLabel.setFont(new Font("Tahoma", Font.BOLD, 31));

		proximoFeitoButton = new JButton(">");
		proximoFeitoButton.setBounds(72, 34, 50, 20);
		Painel5.add(proximoFeitoButton);
		proximoFeitoButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		proximoFeitoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximoFeito();
				Jscroll.getVerticalScrollBar().setValue(Jscroll.getVerticalScrollBar().getMinimum());//Retornando o Scroll pra cima
			}
		});
		proximoFeitoButton.setFocusPainted(false);
		proximoFeitoButton.setBorderPainted(false);
		proximoFeitoButton.setContentAreaFilled(false);

		anteriorFeitoButton = new JButton("<");
		anteriorFeitoButton.setBounds(15, 34, 50, 20);
		Painel5.add(anteriorFeitoButton);
		anteriorFeitoButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		anteriorFeitoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anteriorFeito();
				Jscroll.getVerticalScrollBar().setValue(Jscroll.getVerticalScrollBar().getMinimum());//Retornando o Scroll pra cima
			}
		});
		anteriorFeitoButton.setFocusPainted(false);
		anteriorFeitoButton.setBorderPainted(false);
		anteriorFeitoButton.setContentAreaFilled(false);

		paginaFeitoLabel = new JLabel("0");
		paginaFeitoLabel.setBounds(56, 36, 26, 14);
		Painel5.add(paginaFeitoLabel);
		paginaFeitoLabel.setHorizontalAlignment(SwingConstants.CENTER);

		qtdTarefasFeitoLabel = new JLabel("");
		qtdTarefasFeitoLabel.setForeground(new Color(51, 102, 204));
		qtdTarefasFeitoLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 22));
		qtdTarefasFeitoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qtdTarefasFeitoLabel.setBounds(142, 15, 55, 23);
		Painel5.add(qtdTarefasFeitoLabel);

		tarefasFeitoLabel = new JLabel("Tarefas");
		tarefasFeitoLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		tarefasFeitoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tarefasFeitoLabel.setBounds(142, 37, 55, 16);
		Painel5.add(tarefasFeitoLabel);

		Painel6 = new JPanel();
		Painel6.setBackground(Color.WHITE);
		Painel6.setBounds(715, 90, 200, 54);
		PainelSuperior.add(Painel6);
		Painel6.setLayout(null);

		proximoFazendoButton = new JButton(">");
		proximoFazendoButton.setBounds(72, 34, 50, 20);
		Painel6.add(proximoFazendoButton);
		proximoFazendoButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		proximoFazendoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximoFazendo();
				Jscroll.getVerticalScrollBar().setValue(Jscroll.getVerticalScrollBar().getMinimum());//Retornando o Scroll pra cima
			}
		});
		proximoFazendoButton.setFocusPainted(false);
		proximoFazendoButton.setContentAreaFilled(false);
		proximoFazendoButton.setBorderPainted(false);

		JLabel fazendoLabel = new JLabel("Fazendo");
		fazendoLabel.setBounds(0, 0, 130, 40);
		Painel6.add(fazendoLabel);
		fazendoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fazendoLabel.setFont(new Font("Tahoma", Font.BOLD, 31));

		anteriorFazendoButton = new JButton("<");
		anteriorFazendoButton.setBounds(15, 34, 50, 20);
		Painel6.add(anteriorFazendoButton);
		anteriorFazendoButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		anteriorFazendoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anteriorFazendo();
				Jscroll.getVerticalScrollBar().setValue(Jscroll.getVerticalScrollBar().getMinimum());//Retornando o Scroll pra cima
			}
		});
		anteriorFazendoButton.setFocusPainted(false);
		anteriorFazendoButton.setContentAreaFilled(false);
		anteriorFazendoButton.setBorderPainted(false);

		paginaFazendoLabel = new JLabel("0");
		paginaFazendoLabel.setBounds(56, 36, 26, 14);
		Painel6.add(paginaFazendoLabel);
		paginaFazendoLabel.setHorizontalAlignment(SwingConstants.CENTER);

		qtdTarefasFazendoLabel = new JLabel("");
		qtdTarefasFazendoLabel.setForeground(new Color(51, 102, 204));
		qtdTarefasFazendoLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 22));
		qtdTarefasFazendoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qtdTarefasFazendoLabel.setBounds(142, 15, 55, 23);
		Painel6.add(qtdTarefasFazendoLabel);

		tarefasFazendoLabel = new JLabel("Tarefas");
		tarefasFazendoLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		tarefasFazendoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tarefasFazendoLabel.setBounds(142, 37, 55, 16);
		Painel6.add(tarefasFazendoLabel);

		Painel7 = new JPanel();
		Painel7.setBackground(Color.WHITE);
		Painel7.setBounds(195, 90, 200, 54);
		PainelSuperior.add(Painel7);
		Painel7.setLayout(null);

		JLabel afazerLabel = new JLabel("A Fazer");
		afazerLabel.setBounds(0, 0, 130, 40);
		Painel7.add(afazerLabel);
		afazerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		afazerLabel.setFont(new Font("Tahoma", Font.BOLD, 31));

		anteriorAFazerButton = new JButton("<");
		anteriorAFazerButton.setHorizontalAlignment(SwingConstants.LEFT);
		anteriorAFazerButton.setBounds(10, 34, 50, 20);
		Painel7.add(anteriorAFazerButton);
		anteriorAFazerButton.setHorizontalTextPosition(SwingConstants.LEFT);
		anteriorAFazerButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		anteriorAFazerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anteriorAfazer();
				Jscroll.getVerticalScrollBar().setValue(Jscroll.getVerticalScrollBar().getMinimum());//Retornando o Scroll pra cima
			}
		});
		anteriorAFazerButton.setFocusPainted(false);
		anteriorAFazerButton.setContentAreaFilled(false);
		anteriorAFazerButton.setBorderPainted(false);

		proximoAFazerButton = new JButton(">");
		proximoAFazerButton.setHorizontalAlignment(SwingConstants.RIGHT);
		proximoAFazerButton.setBounds(72, 34, 50, 20);
		Painel7.add(proximoAFazerButton);
		proximoAFazerButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		proximoAFazerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		proximoAFazerButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		proximoAFazerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximoAfazer();
				Jscroll.getVerticalScrollBar().setValue(Jscroll.getVerticalScrollBar().getMinimum());//Retornando o Scroll pra cima
			}
		});
		proximoAFazerButton.setFocusPainted(false);
		proximoAFazerButton.setContentAreaFilled(false);
		proximoAFazerButton.setBorderPainted(false);

		paginaAFazerLabel = new JLabel("0");
		paginaAFazerLabel.setBounds(52, 36, 26, 14);
		Painel7.add(paginaAFazerLabel);
		paginaAFazerLabel.setHorizontalAlignment(SwingConstants.CENTER);

		tarefasAfazerLabel = new JLabel("Tarefas");
		tarefasAfazerLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		tarefasAfazerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tarefasAfazerLabel.setBounds(142, 37, 55, 16);
		Painel7.add(tarefasAfazerLabel);

		qtdTarefasAfazerLabel = new JLabel("");
		qtdTarefasAfazerLabel.setForeground(new Color(51, 102, 204));
		qtdTarefasAfazerLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 22));
		qtdTarefasAfazerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qtdTarefasAfazerLabel.setBounds(142, 15, 55, 23);
		Painel7.add(qtdTarefasAfazerLabel);
		limparButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});

		Jscroll = new JScrollPane(PainelCentral, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Jscroll.setBounds(0, 145, d.width, d.height - 205);
		Jscroll.setVisible(true);
		Jscroll.getVerticalScrollBar().setUnitIncrement(60);
		getContentPane().add(Jscroll);

		KambanDAO.bd.getConnection();// Abre conexï¿½o
		KambanDAO.bdAFazer.getConnection();// Abre conexï¿½o
		KambanDAO.bdFazendo.getConnection();// Abre conexï¿½o
		KambanDAO.bdFeito.getConnection();// Abre conexï¿½o

		CarregarComboBoxDpto();// Carrega as combobox de filtros
		CarregarComboBoxCC();// Carrega as combobox de filtros
		CarregarComboBoxExe();// Carrega as combobox de filtros
		CarregaDatasDeVelocidade();// Carrega primeiro dia do mes e hoje

		CarregarDadosKambans();

		Controle.setLoading(true);
	}
	
	public KambanTela(String FullHD) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KambanTela.class.getResource("/com/myscrum/assets/setIcon1.png")));
		setTitle("Scrum");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				KambanDAO.bdAFazer.close();
				KambanDAO.bdFazendo.close();
				KambanDAO.bdFazendo.close();
				KambanDAO.bd.close();

				Controle.setKamban(false);
				dispose();
			}

		});

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();

		setExtendedState(MAXIMIZED_BOTH);
		setBounds(0, 0, d.width + 50, d.height);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		PainelCentral = new JPanel();
		PainelCentral.setBackground(Color.WHITE);
		PainelCentral.setBounds(0, 185, d.width, d.height);
		contentPane.add(PainelCentral);

		xAfazer = 294;
		xFazendo = 1050;
		xFeito = 1700;

		kamban_0 = new Kamban("Full HD");
		Graficos[0][0] = kamban_0;

		kamban_1 = new Kamban("Full HD");
		Graficos[0][1] = kamban_1;

		kamban_2 = new Kamban("Full HD");
		Graficos[2][0] = kamban_2;

		kamban_3 = new Kamban("Full HD");
		Graficos[2][1] = kamban_3;

		kamban_4 = new Kamban("Full HD");
		Graficos[4][0] = kamban_4;

		kamban_5 = new Kamban("Full HD");
		Graficos[0][2] = kamban_5;

		kamban_6 = new Kamban("Full HD");
		Graficos[0][3] = kamban_6;

		kamban_7 = new Kamban("Full HD");
		Graficos[2][2] = kamban_7;

		kamban_8 = new Kamban("Full HD");
		Graficos[2][3] = kamban_8;

		kamban_9 = new Kamban("Full HD");
		Graficos[4][1] = kamban_9;

		kamban_10 = new Kamban("Full HD");
		Graficos[0][4] = kamban_10;

		kamban_11 = new Kamban("Full HD");
		Graficos[1][0] = kamban_11;

		kamban_12 = new Kamban("Full HD");
		Graficos[2][4] = kamban_12;

		kamban_13 = new Kamban("Full HD");
		Graficos[3][0] = kamban_13;

		kamban_14 = new Kamban("Full HD");
		Graficos[4][2] = kamban_14;

		kamban_15 = new Kamban("Full HD");
		Graficos[1][1] = kamban_15;

		kamban_16 = new Kamban("Full HD");
		Graficos[1][2] = kamban_16;

		kamban_17 = new Kamban("Full HD");
		Graficos[3][1] = kamban_17;

		kamban_18 = new Kamban("Full HD");
		Graficos[3][2] = kamban_18;

		kamban_19 = new Kamban("Full HD");
		Graficos[4][3] = kamban_19;

		kamban_20 = new Kamban("Full HD");
		Graficos[1][3] = kamban_20;

		kamban_21 = new Kamban("Full HD");
		Graficos[1][4] = kamban_21;

		kamban_22 = new Kamban("Full HD");
		Graficos[3][3] = kamban_22;

		kamban_23 = new Kamban("Full HD");
		Graficos[3][4] = kamban_23;

		kamban_24 = new Kamban("Full HD");
		Graficos[4][4] = kamban_24;

		GroupLayout gl_PainelCentral = new GroupLayout(PainelCentral);
		gl_PainelCentral
				.setHorizontalGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_PainelCentral.createSequentialGroup().addContainerGap()
								.addGroup(gl_PainelCentral
										.createParallelGroup(
												Alignment.LEADING)
										.addGroup(
												gl_PainelCentral.createSequentialGroup()
														.addComponent(kamban_0, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 / 2)
														.addComponent(kamban_1, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66)
														.addComponent(kamban_2, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 / 2).addComponent(kamban_3,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE)
														.addGap(66 * 2).addComponent(kamban_4,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_PainelCentral.createSequentialGroup()
														.addComponent(kamban_5, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 / 2)
														.addComponent(kamban_6, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66)
														.addComponent(kamban_7, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 / 2).addComponent(kamban_8,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE)
														.addGap(66 * 2).addComponent(kamban_9,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_PainelCentral.createSequentialGroup()
														.addComponent(kamban_10, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 / 2)
														.addComponent(kamban_11, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66)
														.addComponent(kamban_12, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 / 2).addComponent(kamban_13,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE)
														.addGap(66 * 2).addComponent(kamban_14,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_PainelCentral.createSequentialGroup()
														.addComponent(kamban_15, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 / 2)
														.addComponent(kamban_16, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66)
														.addComponent(kamban_17, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 / 2)
														.addComponent(kamban_18, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 * 2).addComponent(kamban_19,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_PainelCentral.createSequentialGroup()
														.addComponent(kamban_20, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 / 2)
														.addComponent(kamban_21, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66)
														.addComponent(kamban_22, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 / 2)
														.addComponent(kamban_23, GroupLayout.PREFERRED_SIZE,
																kamban_0.getWidth(), GroupLayout.PREFERRED_SIZE)
														.addGap(66 * 2).addComponent(kamban_24,
																GroupLayout.PREFERRED_SIZE, kamban_0.getWidth(),
																GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(101, Short.MAX_VALUE)));
		gl_PainelCentral.setVerticalGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PainelCentral.createSequentialGroup().addContainerGap()
						.addGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(kamban_4, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_3, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_2, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_1, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_0, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(kamban_5, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_6, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_7, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_8, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_9, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(kamban_10, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_11, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_12, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_13, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_14, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(kamban_15, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_16, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_17, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_18, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_19, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_PainelCentral.createParallelGroup(Alignment.LEADING)
								.addComponent(kamban_20, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_21, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_22, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_23, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE)
								.addComponent(kamban_24, GroupLayout.PREFERRED_SIZE, kamban_0.getHeight(),
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		PainelCentral.setLayout(gl_PainelCentral);

		Icon icon = new ImageIcon(getClass().getResource("/com/myscrum/assets/calendar.png"));// Criando icone para os botï¿½es do

		// superior

		PainelSuperior = new JPanel();
		PainelSuperior.setBorder(null);
		PainelSuperior.setBackground(Color.WHITE);
		PainelSuperior.setBounds(0, 0, d.width, 144);
		contentPane.add(PainelSuperior);
		PainelSuperior.setLayout(null);

		Painel1 = new JPanel();
		Painel1.setBackground(Color.WHITE);
		Painel1.setBounds(5, 5, 194, 135);
		PainelSuperior.add(Painel1);
		Painel1.setLayout(null);

		tarefasnaofeitasText = new JTextField();
		tarefasnaofeitasText.setBounds(108, 34, 65, 20);
		Painel1.add(tarefasnaofeitasText);
		tarefasnaofeitasText.setEditable(false);
		tarefasnaofeitasText.setBackground(new Color(0, 102, 153));
		tarefasnaofeitasText.setForeground(new Color(255, 255, 255));
		tarefasnaofeitasText.setColumns(10);

		producao1Label = new JLabel("<html><center>% Produ\u00E7\u00E3o<br/>\r\n(prioridade 1): </html>");
		producao1Label.setBounds(0, 5, 88, 28);
		Painel1.add(producao1Label);
		producao1Label.setHorizontalAlignment(SwingConstants.CENTER);
		producao1Label.setFont(new Font("Tahoma", Font.BOLD, 11));

		producao1Text = new JTextField();
		producao1Text.setBounds(10, 34, 65, 20);
		Painel1.add(producao1Text);
		producao1Text.setEditable(false);
		producao1Text.setForeground(new Color(255, 255, 255));
		producao1Text.setBackground(new Color(41, 106, 158));
		producao1Text.setColumns(10);

		realizadohojeText = new JTextField();
		realizadohojeText.setBounds(108, 106, 65, 20);
		Painel1.add(realizadohojeText);
		realizadohojeText.setEditable(false);
		realizadohojeText.setBackground(new Color(0, 102, 153));
		realizadohojeText.setForeground(new Color(255, 255, 255));
		realizadohojeText.setColumns(10);

		producaogeralLabel = new JLabel("<html><center>% Produ\u00E7\u00E3o<br/>\r\ngeral: <html/>\r\n");
		producaogeralLabel.setBounds(0, 77, 88, 28);
		Painel1.add(producaogeralLabel);
		producaogeralLabel.setHorizontalAlignment(SwingConstants.CENTER);
		producaogeralLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		producaogeralText = new JTextField();
		producaogeralText.setBounds(10, 106, 65, 20);
		Painel1.add(producaogeralText);
		producaogeralText.setEditable(false);
		producaogeralText.setForeground(new Color(255, 255, 255));
		producaogeralText.setBackground(new Color(41, 106, 158));
		producaogeralText.setColumns(10);

		tarefasnaofeitasLabel = new JLabel("<html><center>Qtd. de tarefas <br/>\r\nn\u00E3o feitas:<html/>");
		tarefasnaofeitasLabel.setBounds(94, 5, 88, 28);
		Painel1.add(tarefasnaofeitasLabel);
		tarefasnaofeitasLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		pontosrealizadoshojeLabel = new JLabel("<html><center>Pontos realizados<br/>\r\n hoje:<html/>");
		pontosrealizadoshojeLabel.setBounds(88, 77, 106, 28);
		Painel1.add(pontosrealizadoshojeLabel);
		pontosrealizadoshojeLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		Painel2 = new JPanel();
		Painel2.setBackground(Color.WHITE);
		Painel2.setBounds(350, 5, 235, 91);
		PainelSuperior.add(Painel2);

		JLabel velocidadeMediaLabel = new JLabel("Velocidade m\u00E9dia (pontos/dias uteis):  ");
		velocidadeMediaLabel.setBounds(12, 0, 220, 14);
		velocidadeMediaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label = new JLabel("jun/18:");
		label.setBounds(22, 19, 35, 14);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));

		velocidadeAnteriorText = new JTextField();
		velocidadeAnteriorText.setBounds(57, 15, 42, 20);
		velocidadeAnteriorText.setBackground(new Color(0, 102, 153));
		velocidadeAnteriorText.setForeground(new Color(255, 255, 255));
		velocidadeAnteriorText.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("jun/18:");
		lblNewLabel_3.setBounds(135, 19, 35, 14);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));

		velocidadeText = new JTextField();
		velocidadeText.setBounds(169, 15, 42, 20);
		velocidadeText.setBackground(new Color(0, 102, 153));
		velocidadeText.setForeground(new Color(255, 255, 255));
		velocidadeText.setColumns(10);

		JLabel velocidadePeriodoLabel = new JLabel("Velocidade periodo (pontos): ");
		velocidadePeriodoLabel.setBounds(32, 46, 165, 14);
		velocidadePeriodoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		Painel2.setLayout(null);
		Painel2.add(velocidadeMediaLabel);
		Painel2.add(label);
		Painel2.add(velocidadeAnteriorText);
		Painel2.add(lblNewLabel_3);
		Painel2.add(velocidadeText);
		Painel2.add(velocidadePeriodoLabel);

		JLabel lblNewLabel_2 = new JLabel("Atual:");
		lblNewLabel_2.setBounds(27, 72, 30, 14);
		Painel2.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblAnterior = new JLabel("Anterior:");
		lblAnterior.setBounds(127, 72, 43, 14);
		Painel2.add(lblAnterior);
		lblAnterior.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textField_2 = new JTextField();
		textField_2.setBounds(169, 68, 42, 20);
		Painel2.add(textField_2);
		textField_2.setBackground(new Color(0, 102, 153));
		textField_2.setForeground(new Color(255, 255, 255));
		textField_2.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(57, 68, 42, 20);
		Painel2.add(textField_1);
		textField_1.setBackground(new Color(0, 102, 153));
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setColumns(10);

		Painel3 = new JPanel();
		Painel3.setBackground(Color.WHITE);
		Painel3.setBounds(670, 5, 348, 46);
		PainelSuperior.add(Painel3);
		Painel3.setLayout(null);

		JLabel pontoPrevistoLabel = new JLabel("<html><center>Pontos previsto<html/>");
		pontoPrevistoLabel.setBounds(5, 10, 94, 14);
		Painel3.add(pontoPrevistoLabel);
		pontoPrevistoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		pontosPrevistoText = new JTextField();
		pontosPrevistoText.setBounds(110, 6, 40, 20);
		Painel3.add(pontosPrevistoText);
		pontosPrevistoText.setBackground(new Color(0, 102, 153));
		pontosPrevistoText.setForeground(new Color(255, 255, 255));
		pontosPrevistoText.setColumns(10);

		JLabel lentoEmLabel = new JLabel("Lento em");
		lentoEmLabel.setBounds(156, 9, 60, 14);
		Painel3.add(lentoEmLabel);
		lentoEmLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		textField = new JTextField();
		textField.setBounds(219, 5, 40, 20);
		Painel3.add(textField);
		textField.setBackground(new Color(0, 102, 153));
		textField.setForeground(new Color(255, 255, 255));
		textField.setColumns(10);

		JLabel ponto_diaLabel = new JLabel("pontos/dia");
		ponto_diaLabel.setBounds(265, 9, 71, 14);
		Painel3.add(ponto_diaLabel);
		ponto_diaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		Painel4 = new JPanel();
		Painel4.setBackground(Color.WHITE);
		Painel4.setBounds(d.width - 703, 7, 698, 89);
		PainelSuperior.add(Painel4);
		Painel4.setLayout(null);

		iniPeriodo = new CalendarView();
		iniPeriodo.setBounds(414, 5, 130, 25);
		Painel4.add(iniPeriodo);
		iniPeriodo.setIcon(icon);
		iniPeriodo.getEditor().setBackground(new Color(41, 106, 158));
		iniPeriodo.getEditor().setForeground(new Color(255, 255, 255));
		iniPeriodo.setWeekDaysBackground(new Color(41, 106, 158));

		fimPeriodo = new CalendarView();
		fimPeriodo.setBounds(556, 5, 130, 25);
		Painel4.add(fimPeriodo);
		fimPeriodo.setIcon(icon);
		fimPeriodo.getEditor().setBackground(new Color(41, 106, 158));
		fimPeriodo.getEditor().setForeground(new Color(255, 255, 255));
		fimPeriodo.setWeekDaysBackground(new Color(41, 106, 158));

		JButton limparButton = new JButton("Limpar");
		limparButton.setBounds(414, 41, 130, 23);
		Painel4.add(limparButton);
		limparButton.setForeground(new Color(255, 255, 255));
		limparButton.setBackground(new Color(41, 106, 158));

		pesquisarButton = new JButton("Atualizar");
		pesquisarButton.setBounds(556, 41, 130, 23);
		Painel4.add(pesquisarButton);
		pesquisarButton.setForeground(new Color(255, 255, 255));
		pesquisarButton.setBackground(new Color(41, 106, 158));
		pesquisarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarregarDadosKambans();

				JOptionPane.showMessageDialog(null, "Kamban atualizado com sucesso", "Atualizado", 1);
			}
		});
		pesquisarButton.setMnemonic(KeyEvent.VK_F5);

		JLabel ExeRespAutLabel = new JLabel("Exe. / Resp. / Autoridade : ");
		ExeRespAutLabel.setBounds(5, 65, 180, 14);
		Painel4.add(ExeRespAutLabel);
		ExeRespAutLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		ExeRespAutLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel dptoLabel = new JLabel("Dpto. / terceiro / impedimento :");
		dptoLabel.setBounds(5, 5, 180, 14);
		Painel4.add(dptoLabel);
		dptoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dptoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel ccLabel = new JLabel("Centro de Custo : ");
		ccLabel.setBounds(5, 35, 180, 14);
		Painel4.add(ccLabel);
		ccLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		ccLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		ExeRespAutComboBox = new JComboBox<String>();
		ExeRespAutComboBox.setBounds(194, 65, 150, 20);
		Painel4.add(ExeRespAutComboBox);
		ExeRespAutComboBox.setBackground(new Color(0, 102, 153));
		ExeRespAutComboBox.setForeground(new Color(255, 255, 255));

		ccComboBox = new JComboBox<String>();
		ccComboBox.setBounds(194, 35, 150, 20);
		Painel4.add(ccComboBox);
		ccComboBox.setBackground(new Color(0, 102, 153));
		ccComboBox.setForeground(new Color(255, 255, 255));

		dptoComboBox = new JComboBox<String>();
		dptoComboBox.setBounds(194, 5, 150, 20);
		Painel4.add(dptoComboBox);
		dptoComboBox.setBackground(new Color(0, 102, 153));
		dptoComboBox.setForeground(new Color(255, 255, 255));
		dptoComboBox.addItem("Todos");
		ccComboBox.addItem("Todos");
		ExeRespAutComboBox.addItem("Todos");

		Painel5 = new JPanel();
		Painel5.setBackground(Color.WHITE);
		Painel5.setBounds(1665, 90, 200, 54);
		PainelSuperior.add(Painel5);
		Painel5.setLayout(null);

		JLabel feitoLabel = new JLabel("Feito");
		feitoLabel.setBounds(0, 0, 130, 40);
		Painel5.add(feitoLabel);
		feitoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		feitoLabel.setFont(new Font("Tahoma", Font.BOLD, 31));

		proximoFeitoButton = new JButton(">");
		proximoFeitoButton.setBounds(72, 34, 50, 20);
		Painel5.add(proximoFeitoButton);
		proximoFeitoButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		proximoFeitoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximoFeito();
			}
		});
		proximoFeitoButton.setFocusPainted(false);
		proximoFeitoButton.setBorderPainted(false);
		proximoFeitoButton.setContentAreaFilled(false);

		anteriorFeitoButton = new JButton("<");
		anteriorFeitoButton.setBounds(15, 34, 50, 20);
		Painel5.add(anteriorFeitoButton);
		anteriorFeitoButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		anteriorFeitoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anteriorFeito();
			}
		});
		anteriorFeitoButton.setFocusPainted(false);
		anteriorFeitoButton.setBorderPainted(false);
		anteriorFeitoButton.setContentAreaFilled(false);

		paginaFeitoLabel = new JLabel("0");
		paginaFeitoLabel.setBounds(56, 36, 26, 14);
		Painel5.add(paginaFeitoLabel);
		paginaFeitoLabel.setHorizontalAlignment(SwingConstants.CENTER);

		qtdTarefasFeitoLabel = new JLabel("");
		qtdTarefasFeitoLabel.setForeground(new Color(51, 102, 204));
		qtdTarefasFeitoLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 22));
		qtdTarefasFeitoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qtdTarefasFeitoLabel.setBounds(142, 15, 55, 23);
		Painel5.add(qtdTarefasFeitoLabel);

		tarefasFeitoLabel = new JLabel("Tarefas");
		tarefasFeitoLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		tarefasFeitoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tarefasFeitoLabel.setBounds(142, 37, 55, 16);
		Painel5.add(tarefasFeitoLabel);

		Painel6 = new JPanel();
		Painel6.setBackground(Color.WHITE);
		Painel6.setBounds(1025, 90, 200, 54);
		PainelSuperior.add(Painel6);
		Painel6.setLayout(null);

		proximoFazendoButton = new JButton(">");
		proximoFazendoButton.setBounds(72, 34, 50, 20);
		Painel6.add(proximoFazendoButton);
		proximoFazendoButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		proximoFazendoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximoFazendo();
			}
		});
		proximoFazendoButton.setFocusPainted(false);
		proximoFazendoButton.setContentAreaFilled(false);
		proximoFazendoButton.setBorderPainted(false);

		JLabel fazendoLabel = new JLabel("Fazendo");
		fazendoLabel.setBounds(0, 0, 130, 40);
		Painel6.add(fazendoLabel);
		fazendoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fazendoLabel.setFont(new Font("Tahoma", Font.BOLD, 31));

		anteriorFazendoButton = new JButton("<");
		anteriorFazendoButton.setBounds(15, 34, 50, 20);
		Painel6.add(anteriorFazendoButton);
		anteriorFazendoButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		anteriorFazendoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anteriorFazendo();
			}
		});
		anteriorFazendoButton.setFocusPainted(false);
		anteriorFazendoButton.setContentAreaFilled(false);
		anteriorFazendoButton.setBorderPainted(false);

		paginaFazendoLabel = new JLabel("0");
		paginaFazendoLabel.setBounds(56, 36, 26, 14);
		Painel6.add(paginaFazendoLabel);
		paginaFazendoLabel.setHorizontalAlignment(SwingConstants.CENTER);

		qtdTarefasFazendoLabel = new JLabel("");
		qtdTarefasFazendoLabel.setForeground(new Color(51, 102, 204));
		qtdTarefasFazendoLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 22));
		qtdTarefasFazendoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qtdTarefasFazendoLabel.setBounds(142, 15, 55, 23);
		Painel6.add(qtdTarefasFazendoLabel);

		tarefasFazendoLabel = new JLabel("Tarefas");
		tarefasFazendoLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		tarefasFazendoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tarefasFazendoLabel.setBounds(142, 37, 55, 16);
		Painel6.add(tarefasFazendoLabel);

		Painel7 = new JPanel();
		Painel7.setBackground(Color.WHITE);
		Painel7.setBounds(285, 90, 200, 54);
		PainelSuperior.add(Painel7);
		Painel7.setLayout(null);

		JLabel afazerLabel = new JLabel("A Fazer");
		afazerLabel.setBounds(0, 0, 130, 40);
		Painel7.add(afazerLabel);
		afazerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		afazerLabel.setFont(new Font("Tahoma", Font.BOLD, 31));

		anteriorAFazerButton = new JButton("<");
		anteriorAFazerButton.setHorizontalAlignment(SwingConstants.LEFT);
		anteriorAFazerButton.setBounds(10, 34, 50, 20);
		Painel7.add(anteriorAFazerButton);
		anteriorAFazerButton.setHorizontalTextPosition(SwingConstants.LEFT);
		anteriorAFazerButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		anteriorAFazerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anteriorAfazer();
			}
		});
		anteriorAFazerButton.setFocusPainted(false);
		anteriorAFazerButton.setContentAreaFilled(false);
		anteriorAFazerButton.setBorderPainted(false);

		proximoAFazerButton = new JButton(">");
		proximoAFazerButton.setHorizontalAlignment(SwingConstants.RIGHT);
		proximoAFazerButton.setBounds(72, 34, 50, 20);
		Painel7.add(proximoAFazerButton);
		proximoAFazerButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		proximoAFazerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		proximoAFazerButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		proximoAFazerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximoAfazer();
			}
		});
		proximoAFazerButton.setFocusPainted(false);
		proximoAFazerButton.setContentAreaFilled(false);
		proximoAFazerButton.setBorderPainted(false);

		paginaAFazerLabel = new JLabel("0");
		paginaAFazerLabel.setBounds(52, 36, 26, 14);
		Painel7.add(paginaAFazerLabel);
		paginaAFazerLabel.setHorizontalAlignment(SwingConstants.CENTER);

		tarefasAfazerLabel = new JLabel("Tarefas");
		tarefasAfazerLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		tarefasAfazerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tarefasAfazerLabel.setBounds(142, 37, 55, 16);
		Painel7.add(tarefasAfazerLabel);

		qtdTarefasAfazerLabel = new JLabel("");
		qtdTarefasAfazerLabel.setForeground(new Color(51, 102, 204));
		qtdTarefasAfazerLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 22));
		qtdTarefasAfazerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qtdTarefasAfazerLabel.setBounds(142, 15, 55, 23);
		Painel7.add(qtdTarefasAfazerLabel);
		limparButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});

		Jscroll = new JScrollPane(PainelCentral, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Jscroll.setBounds(0, 145, d.width, d.height - 205);
		Jscroll.setVisible(true);
		Jscroll.getVerticalScrollBar().setUnitIncrement(60);
		getContentPane().add(Jscroll);

		KambanDAO.bd.getConnection();// Abre conexï¿½o
		KambanDAO.bdAFazer.getConnection();// Abre conexï¿½o
		KambanDAO.bdFazendo.getConnection();// Abre conexï¿½o
		KambanDAO.bdFeito.getConnection();// Abre conexï¿½o

		CarregarComboBoxDpto();// Carrega as combobox de filtros
		CarregarComboBoxCC();// Carrega as combobox de filtros
		CarregarComboBoxExe();// Carrega as combobox de filtros
		CarregaDatasDeVelocidade();// Carrega primeiro dia do mes e hoje

		CarregarDadosKambans();

		Controle.setLoading(true);
	}

	/*----------------INCIO---------------Metodos gerais-------------INICIO----------------*/

	public void anteriorFeito() {
		if (metodos.AnteriorFeito() == true) {// Se tiver resultado prossiga

			for (int a = 4; a <= 4; a++) {// Limpando os kambans
				for (int b = 0; b <= 4; b++) {

					Color cor = Color.WHITE;
					AlterarCor(Graficos[a][b], cor);
					InserirInformacao(Graficos[a][b], 0, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
							"", "", "", false);
					Graficos[a][b].respLabel.setVisible(false);
					Graficos[a][b].autoridadeLabel.setVisible(false);
					Graficos[a][b].ccLabel.setVisible(false);
					Graficos[a][b].execLabel.setVisible(false);
					Graficos[a][b].scrollPane.setBorder(null);
					Graficos[a][b].scrollPane_2.setBorder(null);
					Graficos[a][b].ccText.setBorder(null);
					Graficos[a][b].respText.setBorder(null);
					Graficos[a][b].autText.setBorder(null);
					Graficos[a][b].execText.setBorder(null);
					Graficos[a][b].inicioText.setBorder(null);
					Graficos[a][b].fimText.setBorder(null);
					Graficos[a][b].duracaoText.setBorder(null);
					Graficos[a][b].porcentoText.setBorder(null);
					Graficos[a][b].pesoText.setBorder(null);
					Graficos[a][b].pontuacaoText.setBorder(null);
					Graficos[a][b].idText.setForeground(Color.WHITE);
					Graficos[a][b].prioridadeLabel.setForeground(Color.WHITE);
					Graficos[a][b].tamanhoLabel.setForeground(Color.WHITE);
					Graficos[a][b].statusLabel.setForeground(Color.WHITE);
					Graficos[a][b].predLabel.setForeground(Color.WHITE);
					Graficos[a][b].pendenteText.setBackground(Color.WHITE);
					Graficos[a][b].anexoLabel.setVisible(false);
				}
			}

			for (int a = 4; a <= 4; a++) {// Carregando o Kamban com as informaï¿½ï¿½es novas
				for (int b = 0; b <= 4; b++) {
					Color cor = Color.WHITE;

					if (metodos.getId(a, b) != 0) {// Se a posiï¿½ï¿½o da matriz for difente da matriz preencha o
													// kamban com as informaï¿½ï¿½es

						cor = metodos.getAtrasada(a, b);
						AlterarCor(Graficos[a][b], cor);
						InserirInformacao(Graficos[a][b], metodos.getId(a, b), metodos.getDias_iniciado(a, b),
								metodos.getAtrasadoOuEmdia(a, b), metodos.getPrioridade(a, b), metodos.getTamanho(a, b),
								metodos.getDpto(a, b), metodos.getDesc(a, b), metodos.getData_real(a, b),
								metodos.getData_fim(a, b), metodos.getPrazo(a, b), metodos.getStatus_pendencia(a, b),
								metodos.getPorcentagem(a, b) + "%", metodos.getPeso(a, b),
								metodos.getPontuacao_realizada(a, b), metodos.getCentro_custo(a, b),
								metodos.getResponsavel(a, b), metodos.getAutoridade(a, b), metodos.getExecutores(a, b),
								metodos.getDias_atraso(a, b), metodos.getPendete_por(a, b), metodos.getAnexo(a, b));
					}
				}
			}
			paginaFeito--;// Diminuindo um numero da pagina
			paginaFeitoLabel.setText(String.valueOf(paginaFeito));// trocando numero da pagina
		}

	}

	public void proximoFeito() {
		if (metodos.ProximoFeito() == true) {// Se tiver resultado prossiga

			for (int a = 4; a <= 4; a++) {// Limpando os kambans
				for (int b = 0; b <= 4; b++) {

					Color cor = Color.WHITE;
					AlterarCor(Graficos[a][b], cor);
					InserirInformacao(Graficos[a][b], 0, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
							"", "", "", false);
					Graficos[a][b].respLabel.setVisible(false);
					Graficos[a][b].autoridadeLabel.setVisible(false);
					Graficos[a][b].ccLabel.setVisible(false);
					Graficos[a][b].execLabel.setVisible(false);
					Graficos[a][b].scrollPane.setBorder(null);
					Graficos[a][b].scrollPane_2.setBorder(null);
					Graficos[a][b].ccText.setBorder(null);
					Graficos[a][b].respText.setBorder(null);
					Graficos[a][b].autText.setBorder(null);
					Graficos[a][b].execText.setBorder(null);
					Graficos[a][b].inicioText.setBorder(null);
					Graficos[a][b].fimText.setBorder(null);
					Graficos[a][b].duracaoText.setBorder(null);
					Graficos[a][b].porcentoText.setBorder(null);
					Graficos[a][b].pesoText.setBorder(null);
					Graficos[a][b].pontuacaoText.setBorder(null);
					Graficos[a][b].idText.setForeground(Color.WHITE);
					Graficos[a][b].prioridadeLabel.setForeground(Color.WHITE);
					Graficos[a][b].tamanhoLabel.setForeground(Color.WHITE);
					Graficos[a][b].statusLabel.setForeground(Color.WHITE);
					Graficos[a][b].predLabel.setForeground(Color.WHITE);
					Graficos[a][b].pendenteText.setBackground(Color.WHITE);
					Graficos[a][b].pendenteText.setBackground(Color.WHITE);
					Graficos[a][b].anexoLabel.setVisible(false);
				}
			}

			for (int a = 4; a <= 4; a++) {// Carregando o Kamban com as informaï¿½ï¿½es novas
				for (int b = 0; b <= 4; b++) {
					Color cor = Color.WHITE;

					if (metodos.getId(a, b) != 0) {// Se a posiï¿½ï¿½o da matriz for diferente de vazio alimente o
													// kamban com as informaï¿½ï¿½es

						cor = metodos.getAtrasada(a, b);
						AlterarCor(Graficos[a][b], cor);
						InserirInformacao(Graficos[a][b], metodos.getId(a, b), metodos.getDias_iniciado(a, b),
								metodos.getAtrasadoOuEmdia(a, b), metodos.getPrioridade(a, b), metodos.getTamanho(a, b),
								metodos.getDpto(a, b), metodos.getDesc(a, b), metodos.getData_real(a, b),
								metodos.getData_fim(a, b), metodos.getPrazo(a, b), metodos.getStatus_pendencia(a, b),
								metodos.getPorcentagem(a, b) + "%", metodos.getPeso(a, b),
								metodos.getPontuacao_realizada(a, b), metodos.getCentro_custo(a, b),
								metodos.getResponsavel(a, b), metodos.getAutoridade(a, b), metodos.getExecutores(a, b),
								metodos.getDias_atraso(a, b), metodos.getPendete_por(a, b), metodos.getAnexo(a, b));
					}
				}
			}
			paginaFeito++;// Acrescenta um numero no numero da pagina
			paginaFeitoLabel.setText(String.valueOf(paginaFeito));// troca o numero da pagina
		} else {
			JOptionPane.showMessageDialog(null, "Não há mais tarefas feitas com estes criterios");
		}
	}

	public void anteriorFazendo() {
		if (metodos.AnteriorFazendo() == true) {// Se tiver resultado prossiga

			for (int a = 2; a <= 3; a++) {// Limpando os kambans
				for (int b = 0; b <= 4; b++) {

					Color cor = Color.WHITE;
					AlterarCor(Graficos[a][b], cor);
					InserirInformacao(Graficos[a][b], 0, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
							"", "", "", false);
					Graficos[a][b].respLabel.setVisible(false);
					Graficos[a][b].autoridadeLabel.setVisible(false);
					Graficos[a][b].ccLabel.setVisible(false);
					Graficos[a][b].execLabel.setVisible(false);
					Graficos[a][b].scrollPane.setBorder(null);
					Graficos[a][b].scrollPane_2.setBorder(null);
					Graficos[a][b].ccText.setBorder(null);
					Graficos[a][b].respText.setBorder(null);
					Graficos[a][b].autText.setBorder(null);
					Graficos[a][b].execText.setBorder(null);
					Graficos[a][b].inicioText.setBorder(null);
					Graficos[a][b].fimText.setBorder(null);
					Graficos[a][b].duracaoText.setBorder(null);
					Graficos[a][b].porcentoText.setBorder(null);
					Graficos[a][b].pesoText.setBorder(null);
					Graficos[a][b].pontuacaoText.setBorder(null);
					Graficos[a][b].idText.setForeground(Color.WHITE);
					Graficos[a][b].prioridadeLabel.setForeground(Color.WHITE);
					Graficos[a][b].tamanhoLabel.setForeground(Color.WHITE);
					Graficos[a][b].statusLabel.setForeground(Color.WHITE);
					Graficos[a][b].predLabel.setForeground(Color.WHITE);
					Graficos[a][b].pendenteText.setBackground(Color.WHITE);
					Graficos[a][b].anexoLabel.setVisible(false);
				}
			}

			for (int a = 2; a <= 3; a++) {// Carregando o Kamban com as informaï¿½ï¿½es novas
				for (int b = 0; b <= 4; b++) {
					Color cor = Color.WHITE;

					if (metodos.getId(a, b) != 0) {// Se a posiï¿½ï¿½o da matriz for difente da matriz preencha o
													// kamban com as informaï¿½ï¿½es

						cor = metodos.getAtrasada(a, b);
						AlterarCor(Graficos[a][b], cor);
						InserirInformacao(Graficos[a][b], metodos.getId(a, b), metodos.getDias_iniciado(a, b),
								metodos.getAtrasadoOuEmdia(a, b), metodos.getPrioridade(a, b), metodos.getTamanho(a, b),
								metodos.getDpto(a, b), metodos.getDesc(a, b), metodos.getData_real(a, b),
								metodos.getData_fim(a, b), metodos.getPrazo(a, b), metodos.getStatus_pendencia(a, b),
								metodos.getPorcentagem(a, b) + "%", metodos.getPeso(a, b),
								metodos.getPontuacao_realizada(a, b), metodos.getCentro_custo(a, b),
								metodos.getResponsavel(a, b), metodos.getAutoridade(a, b), metodos.getExecutores(a, b),
								metodos.getDias_atraso(a, b), metodos.getPendete_por(a, b), metodos.getAnexo(a, b));
					}
				}
			}
			paginaFazendo--;// Diminuindo um numero da pagina
			paginaFazendoLabel.setText(String.valueOf(paginaFazendo));// trocando numero da pagina
		}

	}

	public void proximoFazendo() {
		if (metodos.ProximoFazendo() == true) {// Se tiver resultado prossiga

			for (int a = 2; a <= 3; a++) {// Limpando os kambans
				for (int b = 0; b <= 4; b++) {

					Color cor = Color.WHITE;
					AlterarCor(Graficos[a][b], cor);
					InserirInformacao(Graficos[a][b], 0, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
							"", "", "", false);
					Graficos[a][b].respLabel.setVisible(false);
					Graficos[a][b].autoridadeLabel.setVisible(false);
					Graficos[a][b].ccLabel.setVisible(false);
					Graficos[a][b].execLabel.setVisible(false);
					Graficos[a][b].scrollPane.setBorder(null);
					Graficos[a][b].scrollPane_2.setBorder(null);
					Graficos[a][b].ccText.setBorder(null);
					Graficos[a][b].respText.setBorder(null);
					Graficos[a][b].autText.setBorder(null);
					Graficos[a][b].execText.setBorder(null);
					Graficos[a][b].inicioText.setBorder(null);
					Graficos[a][b].fimText.setBorder(null);
					Graficos[a][b].duracaoText.setBorder(null);
					Graficos[a][b].porcentoText.setBorder(null);
					Graficos[a][b].pesoText.setBorder(null);
					Graficos[a][b].pontuacaoText.setBorder(null);
					Graficos[a][b].idText.setForeground(Color.WHITE);
					Graficos[a][b].prioridadeLabel.setForeground(Color.WHITE);
					Graficos[a][b].tamanhoLabel.setForeground(Color.WHITE);
					Graficos[a][b].statusLabel.setForeground(Color.WHITE);
					Graficos[a][b].predLabel.setForeground(Color.WHITE);
					Graficos[a][b].pendenteText.setBackground(Color.WHITE);
					Graficos[a][b].anexoLabel.setVisible(false);
				}
			}

			for (int a = 2; a <= 3; a++) {// Carregando o Kamban com as informaï¿½ï¿½es novas
				for (int b = 0; b <= 4; b++) {
					Color cor = Color.WHITE;

					if (metodos.getId(a, b) != 0) {// Se a posiï¿½ï¿½o da matriz for diferente de vazio alimente o
													// kamban com as informaï¿½ï¿½es

						cor = metodos.getAtrasada(a, b);
						AlterarCor(Graficos[a][b], cor);
						InserirInformacao(Graficos[a][b], metodos.getId(a, b), metodos.getDias_iniciado(a, b),
								metodos.getAtrasadoOuEmdia(a, b), metodos.getPrioridade(a, b), metodos.getTamanho(a, b),
								metodos.getDpto(a, b), metodos.getDesc(a, b), metodos.getData_real(a, b),
								metodos.getData_fim(a, b), metodos.getPrazo(a, b), metodos.getStatus_pendencia(a, b),
								metodos.getPorcentagem(a, b) + "%", metodos.getPeso(a, b),
								metodos.getPontuacao_realizada(a, b), metodos.getCentro_custo(a, b),
								metodos.getResponsavel(a, b), metodos.getAutoridade(a, b), metodos.getExecutores(a, b),
								metodos.getDias_atraso(a, b), metodos.getPendete_por(a, b), metodos.getAnexo(a, b));
					}
				}
			}
			paginaFazendo++;// Acrescenta um numero no numero da pagina
			paginaFazendoLabel.setText(String.valueOf(paginaFazendo));// troca o numero da pagina
		} else {
			JOptionPane.showMessageDialog(null, "Não há mais tarefas Fazendo com estes criterios");
		}
	}

	public void anteriorAfazer() {
		if (metodos.AnteriorAFazer() == true) {// Se tiver resultado prossiga

			for (int a = 0; a <= 1; a++) {// Limpando os kambans
				for (int b = 0; b <= 4; b++) {

					Color cor = Color.WHITE;
					AlterarCor(Graficos[a][b], cor);
					InserirInformacao(Graficos[a][b], 0, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
							"", "", "", false);
					Graficos[a][b].respLabel.setVisible(false);
					Graficos[a][b].autoridadeLabel.setVisible(false);
					Graficos[a][b].ccLabel.setVisible(false);
					Graficos[a][b].execLabel.setVisible(false);
					Graficos[a][b].scrollPane.setBorder(null);
					Graficos[a][b].scrollPane_2.setBorder(null);
					Graficos[a][b].ccText.setBorder(null);
					Graficos[a][b].respText.setBorder(null);
					Graficos[a][b].autText.setBorder(null);
					Graficos[a][b].execText.setBorder(null);
					Graficos[a][b].inicioText.setBorder(null);
					Graficos[a][b].fimText.setBorder(null);
					Graficos[a][b].duracaoText.setBorder(null);
					Graficos[a][b].porcentoText.setBorder(null);
					Graficos[a][b].pesoText.setBorder(null);
					Graficos[a][b].pontuacaoText.setBorder(null);
					Graficos[a][b].idText.setForeground(Color.WHITE);
					Graficos[a][b].prioridadeLabel.setForeground(Color.WHITE);
					Graficos[a][b].tamanhoLabel.setForeground(Color.WHITE);
					Graficos[a][b].statusLabel.setForeground(Color.WHITE);
					Graficos[a][b].predLabel.setForeground(Color.WHITE);
					Graficos[a][b].pendenteText.setBackground(Color.WHITE);
					Graficos[a][b].anexoLabel.setVisible(false);
				}
			}

			for (int a = 0; a <= 1; a++) {// Carregando o Kamban com as informaï¿½ï¿½es novas
				for (int b = 0; b <= 4; b++) {
					Color cor = Color.WHITE;

					if (metodos.getId(a, b) != 0) {// Se a posiï¿½ï¿½o da matriz for difente da matriz preencha o
													// kamban com as informaï¿½ï¿½es

						cor = metodos.getAtrasada(a, b);
						AlterarCor(Graficos[a][b], cor);
						InserirInformacao(Graficos[a][b], metodos.getId(a, b), metodos.getDias_iniciado(a, b),
								metodos.getAtrasadoOuEmdia(a, b), metodos.getPrioridade(a, b), metodos.getTamanho(a, b),
								metodos.getDpto(a, b), metodos.getDesc(a, b), metodos.getData_real(a, b),
								metodos.getData_fim(a, b), metodos.getPrazo(a, b), metodos.getStatus_pendencia(a, b),
								metodos.getPorcentagem(a, b) + "%", metodos.getPeso(a, b),
								metodos.getPontuacao_realizada(a, b), metodos.getCentro_custo(a, b),
								metodos.getResponsavel(a, b), metodos.getAutoridade(a, b), metodos.getExecutores(a, b),
								metodos.getDias_atraso(a, b), metodos.getPendete_por(a, b), metodos.getAnexo(a, b));
					}
				}
			}
			paginaAFazer--;// Diminuindo um numero da pagina
			paginaAFazerLabel.setText(String.valueOf(paginaAFazer));// trocando numero da pagina
		}
	}

	public void proximoAfazer() {
		if (metodos.ProximoAFazer() == true) {// Se tiver resultado prossiga

			for (int a = 0; a <= 1; a++) {// Limpando os kambans
				for (int b = 0; b <= 4; b++) {

					Color cor = Color.WHITE;
					AlterarCor(Graficos[a][b], cor);
					InserirInformacao(Graficos[a][b], 0, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
							"", "", "", false);
					Graficos[a][b].respLabel.setVisible(false);
					Graficos[a][b].autoridadeLabel.setVisible(false);
					Graficos[a][b].ccLabel.setVisible(false);
					Graficos[a][b].execLabel.setVisible(false);
					Graficos[a][b].scrollPane.setBorder(null);
					Graficos[a][b].scrollPane_2.setBorder(null);
					Graficos[a][b].ccText.setBorder(null);
					Graficos[a][b].respText.setBorder(null);
					Graficos[a][b].autText.setBorder(null);
					Graficos[a][b].execText.setBorder(null);
					Graficos[a][b].inicioText.setBorder(null);
					Graficos[a][b].fimText.setBorder(null);
					Graficos[a][b].duracaoText.setBorder(null);
					Graficos[a][b].porcentoText.setBorder(null);
					Graficos[a][b].pesoText.setBorder(null);
					Graficos[a][b].pontuacaoText.setBorder(null);
					Graficos[a][b].idText.setForeground(Color.WHITE);
					Graficos[a][b].prioridadeLabel.setForeground(Color.WHITE);
					Graficos[a][b].tamanhoLabel.setForeground(Color.WHITE);
					Graficos[a][b].statusLabel.setForeground(Color.WHITE);
					Graficos[a][b].predLabel.setForeground(Color.WHITE);
					Graficos[a][b].pendenteText.setBackground(Color.WHITE);
					Graficos[a][b].anexoLabel.setVisible(false);
				}
			}

			for (int a = 0; a <= 1; a++) {// Carregando o Kamban com as informaï¿½ï¿½es novas
				for (int b = 0; b <= 4; b++) {
					Color cor = Color.WHITE;

					if (metodos.getId(a, b) != 0) {// Se a posiï¿½ï¿½o da matriz for diferente de vazio alimente o
													// kamban com as informaï¿½ï¿½es

						cor = metodos.getAtrasada(a, b);
						AlterarCor(Graficos[a][b], cor);
						InserirInformacao(Graficos[a][b], metodos.getId(a, b), metodos.getDias_iniciado(a, b),
								metodos.getAtrasadoOuEmdia(a, b), metodos.getPrioridade(a, b), metodos.getTamanho(a, b),
								metodos.getDpto(a, b), metodos.getDesc(a, b), metodos.getData_real(a, b),
								metodos.getData_fim(a, b), metodos.getPrazo(a, b), metodos.getStatus_pendencia(a, b),
								metodos.getPorcentagem(a, b) + "%", metodos.getPeso(a, b),
								metodos.getPontuacao_realizada(a, b), metodos.getCentro_custo(a, b),
								metodos.getResponsavel(a, b), metodos.getAutoridade(a, b), metodos.getExecutores(a, b),
								metodos.getDias_atraso(a, b), metodos.getPendete_por(a, b), metodos.getAnexo(a, b));
					}
				}
			}
			paginaAFazer++;// Acrescenta um numero no numero da pagina
			paginaAFazerLabel.setText(String.valueOf(paginaAFazer));// troca o numero da pagina
		} else {
			JOptionPane.showMessageDialog(null, "Não há mais tarefas a fazer com estes criterios");
		}
	}

	public void limpar() {
		ccComboBox.setSelectedIndex(0);
		dptoComboBox.setSelectedIndex(0);
		ExeRespAutComboBox.setSelectedIndex(0);

	}

	public void CarregarComboBoxCC() {

		String sql = "SELECT centrocusto FROM centro_custo ORDER BY centrocusto ASC";

		try {
			KambanDAO.bd.st = KambanDAO.bd.con.prepareStatement(sql);

			KambanDAO.bd.rs = KambanDAO.bd.st.executeQuery();

			while (KambanDAO.bd.rs.next() == true) {
				ccComboBox.addItem(KambanDAO.bd.rs.getString(1));
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO CARREGAR COMBOBOX", 0);
		}
	}

	public void CarregarComboBoxExe() {

		String sql = "SELECT nome FROM pessoa ORDER BY nome ASC";

		try {
			KambanDAO.bd.st = KambanDAO.bd.con.prepareStatement(sql);

			KambanDAO.bd.rs = KambanDAO.bd.st.executeQuery();

			while (KambanDAO.bd.rs.next() == true) {
				ExeRespAutComboBox.addItem(KambanDAO.bd.rs.getString(1));
				dptoComboBox.addItem(KambanDAO.bd.rs.getString(1));
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO CARREGAR COMBOBOX", 0);
		}

	}

	public void CarregarComboBoxDpto() {

		String sql = "SELECT departamento FROM departamento ORDER BY departamento ASC";

		try {
			KambanDAO.bd.st = KambanDAO.bd.con.prepareStatement(sql);

			KambanDAO.bd.rs = KambanDAO.bd.st.executeQuery();

			while (KambanDAO.bd.rs.next() == true) {
				dptoComboBox.addItem(KambanDAO.bd.rs.getString(1));
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString(), "ERRO AO CARREGAR COMBOBOX", 0);
		}
	}

	public void AlterarCor(Kamban kamban, Color cor) {
		kamban.BarraLateral.setInitialColor(Color.WHITE);
		kamban.BarraLateral.setFinalColor(cor);
		kamban.idText.setBorder(new LineBorder(cor, 2));
		kamban.inicioLabel.setForeground(cor);
		kamban.fimLabel.setForeground(cor);
		kamban.duracaoLabel.setForeground(cor);
		kamban.atrasadoText.setBackground(cor);
		kamban.setBorder(new MatteBorder(2, 0, 2, 4, cor));
		kamban.BarraLateral.setBorder(new MatteBorder(2, 2, 0, 2, cor));
		kamban.scrollPane.setBorder(new LineBorder(cor));
		kamban.scrollPane_2.setBorder(new LineBorder(cor));
		kamban.ccText.setBorder(new LineBorder(cor));
		kamban.respText.setBorder(new LineBorder(cor));
		kamban.autText.setBorder(new LineBorder(cor));
		kamban.execText.setBorder(new LineBorder(cor));
		kamban.inicioText.setBorder(new LineBorder(cor));
		kamban.fimText.setBorder(new LineBorder(cor));
		kamban.duracaoText.setBorder(new LineBorder(cor));
		kamban.porcentoText.setBorder(new LineBorder(cor));
		kamban.pesoText.setBorder(new LineBorder(cor));
		kamban.pontuacaoText.setBorder(new LineBorder(cor));
		kamban.idText.setForeground(Color.BLACK);
		kamban.prioridadeLabel.setForeground(Color.BLACK);
		kamban.tamanhoLabel.setForeground(Color.BLACK);
		kamban.statusLabel.setForeground(Color.BLACK);
		kamban.predLabel.setForeground(Color.BLACK);

	}

	public void InserirInformacao(Kamban kamban, int id, String FraseIniciado, String AtrasadoOuEmDia,
			String prioridade, String tamanho, String dpto, String descricao, String DataInicio, String DataFim,
			String duracao, String StatusPendencia, String porcento, String peso, String pontuacao, String cc,
			String responsavel, String autoridade, String exec, String dias, String pendente, boolean anexo) {

		kamban.setVisible(true);
		kamban.respLabel.setVisible(true);
		kamban.autoridadeLabel.setVisible(true);
		kamban.execLabel.setVisible(true);
		kamban.ccLabel.setVisible(true);

		kamban.idText.setText(String.valueOf(id));// id da tarefa
		kamban.iniciadoLabel.setText(FraseIniciado);// frase de quando foi iniciado e a quantos dias
		kamban.atrasadoText.setText(AtrasadoOuEmDia);// status de se estar atrasada ou em dia
		kamban.prioridadeText.setText(String.valueOf(prioridade));// Prioridade da tarefa
		kamban.tamanhoText.setText(tamanho);// Tamanho da tarefa
		kamban.dpto.setText(dpto);
		kamban.descText.setText(descricao);// Descriï¿½ï¿½o da tarefa
		kamban.inicioText.setText(DataInicio);
		kamban.fimText.setText(DataFim);
		kamban.duracaoText.setText(duracao);// prazo
		kamban.statText.setText(StatusPendencia);
		kamban.porcentoText.setText(porcento);
		kamban.pesoText.setText(peso);
		kamban.pontuacaoText.setText(pontuacao);// Pontuaï¿½ï¿½o realizada
		kamban.ccText.setText(cc);
		kamban.respText.setText(responsavel);
		kamban.autText.setText(autoridade);
		kamban.execText.setText(exec);
		kamban.diasatrasadosLabel.setText(dias);
		kamban.pendenteText.setText(pendente);
		if (pendente != "" && pendente != null) {
			kamban.pendenteText.setBackground(Color.ORANGE);
		}
		if (anexo == true) {
			kamban.anexoLabel.setVisible(true);
		}

	}

	public void limparKambans(int a, int b) {
		Graficos[a][b].respLabel.setVisible(false);
		Graficos[a][b].autoridadeLabel.setVisible(false);
		Graficos[a][b].ccLabel.setVisible(false);
		Graficos[a][b].execLabel.setVisible(false);
		Graficos[a][b].scrollPane.setBorder(null);
		Graficos[a][b].scrollPane_2.setBorder(null);
		Graficos[a][b].ccText.setBorder(null);
		Graficos[a][b].respText.setBorder(null);
		Graficos[a][b].autText.setBorder(null);
		Graficos[a][b].execText.setBorder(null);
		Graficos[a][b].inicioText.setBorder(null);
		Graficos[a][b].fimText.setBorder(null);
		Graficos[a][b].duracaoText.setBorder(null);
		Graficos[a][b].porcentoText.setBorder(null);
		Graficos[a][b].pesoText.setBorder(null);
		Graficos[a][b].pontuacaoText.setBorder(null);
		Graficos[a][b].idText.setForeground(Color.WHITE);
		Graficos[a][b].prioridadeLabel.setForeground(Color.WHITE);
		Graficos[a][b].tamanhoLabel.setForeground(Color.WHITE);
		Graficos[a][b].statusLabel.setForeground(Color.WHITE);
		Graficos[a][b].predLabel.setForeground(Color.WHITE);
		Graficos[a][b].pendenteText.setBackground(Color.WHITE);
		Graficos[a][b].dpto.setText("");
		Graficos[a][b].anexoLabel.setVisible(false);
	}

	public String GerarFiltro(String Status_Tarefa) {
		String filtro = "";
		String sql = "";
		
		if(Sessao.getInstance().getFuncao() == 0) {//Se a função for apenas usuario limitamos as tarefas apenas para qual o nome dele está envolvido
			String eu = "'"+Sessao.getInstance().getNome()+"'";
			
			filtro += "(tarefa.responsavel = "+ eu +" OR tarefa.autoridade = "+ eu +" OR tarefa.pendente_por = "+ eu +" OR tarefa.checado = "+ eu +" \r\n" 
					+"OR (executor.executor1 = "+ eu +" OR executor.executor2 = "+ eu +" OR executor.executor3 = "+ eu +" \r\n" 
					+"OR executor.executor4 = "+ eu +" OR executor.executor5 = "+ eu +" OR executor.executor6 = "+ eu +" \r\n" 
					+"OR executor.executor7 = "+ eu +" OR executor.executor8 = "+ eu +" OR executor.executor9 = "+ eu +" \r\n" 
					+"OR executor.executor10 = "+ eu +")) AND\r\n";
		}
		
		if(Sessao.getInstance().getFuncao() == 2) {//Se a função for lider limitamos as tarefas apenas para qual o nome dele está envolvido e qual o departamento dele aparece
			String eu = "'"+Sessao.getInstance().getNome()+"'";
			String dpto = "'"+Sessao.getInstance().getDpto()+"'";
			
			filtro += "(tarefa.responsavel = "+ eu +" OR tarefa.autoridade = "+ eu +" OR tarefa.pendente_por = "+ eu +" OR tarefa.checado = "+ eu +" \r\n" 
					+"OR (executor.executor1 = "+ eu +" OR executor.executor2 = "+ eu +" OR executor.executor3 = "+ eu +" \r\n" 
					+"OR executor.executor4 = "+ eu +" OR executor.executor5 = "+ eu +" OR executor.executor6 = "+ eu +" \r\n" 
					+"OR executor.executor7 = "+ eu +" OR executor.executor8 = "+ eu +" OR executor.executor9 = "+ eu +" \r\n" 
					+"OR executor.executor10 = "+ eu +" OR tarefa.id_departamento = (SELECT id_departamento FROM departamento WHERE departamento = "+ dpto +"))) AND\r\n";
		}
		
		if(Sessao.getInstance().getFuncao() == 3) {//Se a função for lider limitamos as tarefas apenas para qual o nome dele está envolvido e qual o departamento dele aparece
			String eu = "'"+Sessao.getInstance().getNome()+"'";
			String cc = "'"+Sessao.getInstance().getCC()+"'";
			
			filtro += "(tarefa.responsavel = "+ eu +" OR tarefa.autoridade = "+ eu +" OR tarefa.pendente_por = "+ eu +" OR tarefa.checado = "+ eu +" \r\n" 
					+"OR (executor.executor1 = "+ eu +" OR executor.executor2 = "+ eu +" OR executor.executor3 = "+ eu +" \r\n" 
					+"OR executor.executor4 = "+ eu +" OR executor.executor5 = "+ eu +" OR executor.executor6 = "+ eu +" \r\n" 
					+"OR executor.executor7 = "+ eu +" OR executor.executor8 = "+ eu +" OR executor.executor9 = "+ eu +" \r\n" 
					+"OR executor.executor10 = "+ eu +" OR tarefa.id_centro_custo = (SELECT id_centro_custo FROM centro_custo WHERE centrocusto = "+ cc +"))) AND\r\n";
		}

		String Executor = "'" + ExeRespAutComboBox.getSelectedItem().toString() + "'";
		if (ExeRespAutComboBox.getSelectedIndex() != 0) {
			filtro += "(tarefa.autoridade = " + Executor + " OR\r\n" + "tarefa.responsavel = " + Executor + " OR\r\n"
					+ "(SELECT executor.executor1 FROM executor WHERE executor.id_tarefa=tarefa.id_tarefa) = "
					+ Executor + " OR\r\n"
					+ "(SELECT executor.executor2 FROM executor WHERE executor.id_tarefa=tarefa.id_tarefa) = "
					+ Executor + " OR\r\n"
					+ "(SELECT executor.executor3 FROM executor WHERE executor.id_tarefa=tarefa.id_tarefa) = "
					+ Executor + " OR\r\n"
					+ "(SELECT executor.executor4 FROM executor WHERE executor.id_tarefa=tarefa.id_tarefa) = "
					+ Executor + " OR\r\n"
					+ "(SELECT executor.executor5 FROM executor WHERE executor.id_tarefa=tarefa.id_tarefa) = "
					+ Executor + " OR\r\n"
					+ "(SELECT executor.executor6 FROM executor WHERE executor.id_tarefa=tarefa.id_tarefa) = "
					+ Executor + " OR\r\n"
					+ "(SELECT executor.executor7 FROM executor WHERE executor.id_tarefa=tarefa.id_tarefa) = "
					+ Executor + " OR\r\n"
					+ "(SELECT executor.executor8 FROM executor WHERE executor.id_tarefa=tarefa.id_tarefa) = "
					+ Executor + " OR\r\n"
					+ "(SELECT executor.executor9 FROM executor WHERE executor.id_tarefa=tarefa.id_tarefa) = "
					+ Executor + " OR\r\n"
					+ "(SELECT executor.executor10 FROM executor WHERE executor.id_tarefa=tarefa.id_tarefa) = "
					+ Executor + ") AND\r\n";

		}
		String CC = "'" + ccComboBox.getSelectedItem().toString() + "'";
		if (ccComboBox.getSelectedIndex() != 0) {
			filtro += "(SELECT centro_custo.centrocusto FROM centro_custo WHERE centro_custo.id_centro_custo=tarefa.id_centro_custo) = "
					+ CC + " AND\r\n";
		}
		String Pendente = "'" + dptoComboBox.getSelectedItem().toString() + "'";
		if (dptoComboBox.getSelectedIndex() != 0) {
			filtro += "((SELECT departamento.departamento FROM departamento WHERE departamento.id_departamento=tarefa.id_departamento) = "
					+ Pendente + " OR  (tarefa.pendente_por = " + Pendente + ")) AND\r\n";
		}

		if (Status_Tarefa == "Feito") {// SE o statu for feito Selecte tem mais um o where, baseado nada data de hoje

			sql = "SELECT tarefa.data_ini AS Data_Inicio, tarefa.id_tarefa, tarefa.descri, tarefa.prioridade, tarefa.stat, \r\n"
					+ "(SELECT tamanho.descricao FROM tamanho WHERE tamanho.id_tamanho=tarefa.id_tamanho) AS Tamanho,\r\n"
					+ "tarefa.data_real, tarefa.data_fim, tarefa.prazo, tarefa.status_pendencia, tarefa.porcentagem,\r\n"
					+ "(SELECT tamanho.peso FROM tamanho WHERE tamanho.id_tamanho=tarefa.id_tamanho) AS Peso,\r\n"
					+ "(SELECT centro_custo.centrocusto FROM centro_custo WHERE centro_custo.id_centro_custo=tarefa.id_centro_custo) AS Centro_de_Custo,\r\n"
					+ "(SELECT departamento.departamento FROM departamento WHERE departamento.id_departamento=tarefa.id_departamento) AS Departamento, \r\n"
					+ "tarefa.responsavel, tarefa.autoridade, tarefa.anexo1, tarefa.anexo2, tarefa.anexo3, tarefa.anexo4, \r\n"
					+ "(SELECT CONCAT_WS('/',executor.executor1,executor.executor2,executor.executor3,executor.executor4,executor.executor5,executor.executor6,executor.executor7,executor.executor8,executor.executor9,executor.executor10) FROM executor WHERE executor.id_tarefa=tarefa.id_tarefa) AS Executores, \r\n"
					+ "tarefa.pendente_por\r\n" 
					+ "FROM tarefa\r\n" 
					+ "INNER JOIN executor\r\n"
					+ "ON tarefa.id_tarefa=executor.id_tarefa\r\n"
					+ "WHERE " + filtro + "tarefa.stat= '"+ Status_Tarefa +"'" 
					+ "AND tarefa.data_fim BETWEEN '"+ DataParaoBanco(dataDeHoje(7)) +"' AND '"+ DataParaoBanco(dataDeHoje(0)) +"'"
					+ "ORDER BY tarefa.data_fim DESC, tarefa.prioridade ";

		} else {

			sql = "SELECT tarefa.data_ini AS Data_Inicio, tarefa.id_tarefa, tarefa.descri, tarefa.prioridade, tarefa.stat, \r\n"
					+ "(SELECT tamanho.descricao FROM tamanho WHERE tamanho.id_tamanho=tarefa.id_tamanho) AS Tamanho,\r\n"
					+ "tarefa.data_real, tarefa.data_fim, tarefa.prazo, tarefa.status_pendencia, tarefa.porcentagem,\r\n"
					+ "(SELECT tamanho.peso FROM tamanho WHERE tamanho.id_tamanho=tarefa.id_tamanho) AS Peso,\r\n"
					+ "(SELECT centro_custo.centrocusto FROM centro_custo WHERE centro_custo.id_centro_custo=tarefa.id_centro_custo) AS Centro_de_Custo,\r\n"
					+ "(SELECT departamento.departamento FROM departamento WHERE departamento.id_departamento=tarefa.id_departamento) AS Departamento, \r\n"
					+ "tarefa.responsavel, tarefa.autoridade, tarefa.anexo1, tarefa.anexo2, tarefa.anexo3, tarefa.anexo4, \r\n"
					+ "(SELECT CONCAT_WS('/',executor.executor1,executor.executor2,executor.executor3,executor.executor4,executor.executor5,executor.executor6,executor.executor7,executor.executor8,executor.executor9,executor.executor10)\r\n"
					+ "FROM executor \r\n" + "WHERE executor.id_tarefa=tarefa.id_tarefa) AS Executores, \r\n"
					+ "tarefa.pendente_por, \r\n"
					+ "IF(stat = 'A fazer', \r\n"
					+ "DATEDIFF(DATE_FORMAT(now(), '%Y-%m-%d'), data_ini), \r\n" 
					+ "DATEDIFF(DATE_FORMAT(now(), '%Y-%m-%d'), data_fim)) AS Atraso \r\n" 
					+ "FROM tarefa\r\n" 
					+ "INNER JOIN executor\r\n"
					+ "ON tarefa.id_tarefa=executor.id_tarefa\r\n"
					+ "WHERE " + filtro + "tarefa.stat= '"+ Status_Tarefa + "'" 
					+ "ORDER BY tarefa.prioridade, Atraso DESC";

		}
		return sql;
	}

	public void CarregaDatasDeVelocidade() {
		// PRIMEIRO DIA DO Mï¿½S
		Calendar PrimeiroDiaDoMes = Calendar.getInstance();

		PrimeiroDiaDoMes.set(Calendar.DAY_OF_MONTH, PrimeiroDiaDoMes.getActualMinimum(Calendar.DAY_OF_MONTH));

		iniPeriodo.setText((new SimpleDateFormat("dd/MM/yyyy").format(PrimeiroDiaDoMes.getTime())));

		// HOJE
		Calendar hoje = Calendar.getInstance();

		fimPeriodo.setText((new SimpleDateFormat("dd/MM/yyyy").format(hoje.getTime())));

	}

	public void CarregarDadosKambans() {
		voltarAfazer = paginaAFazer;// GUardando a pagina a qual estï¿½
		voltarFazendo = paginaFazendo;
		voltarFeito = paginaFeito;

		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {// Limpa todos kambans para depois pesquisar e carrega novamente com as novas
											// tarefas
				limparKambans(a, b);
			}
		}

		metodos.ListarFeriados();// Lista feriados ques existem cadastrados
		metodos.limparVariavelProducao();// Limpar as variaveis de calclos de produï¿½ï¿½o
		
		metodos.pesquisarAFazer(GerarFiltro("A fazer"));// Pesquisa, alocar em variaveis, e calcula as tarefas A Fazer
														// baseadas na condiï¿½ï¿½o do filtro
		metodos.pesquisarFazendo(GerarFiltro("Fazendo"));// Pesquisa, alocar em variaveis, e calcula as tarefas Fazendo baseadas na condiï¿½ï¿½o do filtro

		metodos.pesquisarFeito(GerarFiltro("Feito"));// Pesquisa, alocar em variaveis, e calcula as tarefas A Feito
														// baseadas na condiï¿½ï¿½o do filtro
		paginaAFazer = 1;// Passando para a variavel o numero da primeira pagina
		paginaFazendo = 1;// Passando para a variavel o numero da primeira pagina
		paginaFeito = 1;// Passando para a variavel o numero da primeira pagina
		if (kamban.getQtd_Afazer() == 1) {
			tarefasAfazerLabel.setText("Tarefa");
		} else {
			tarefasAfazerLabel.setText("Tarefas");
		}
		qtdTarefasAfazerLabel.setText(String.valueOf(kamban.getQtd_Afazer()));
		if (kamban.getQtd_Fazendo() == 1) {
			tarefasFazendoLabel.setText("Tarefa");
		} else {
			tarefasFazendoLabel.setText("Tarefas");
		}
		qtdTarefasFazendoLabel.setText(String.valueOf(kamban.getQtd_Fazendo()));
		if (kamban.getQtd_Feito() == 1) {
			tarefasFeitoLabel.setText("Tarefa");
		} else {
			tarefasFeitoLabel.setText("Tarefas");
		}
		qtdTarefasFeitoLabel.setText(String.valueOf(kamban.getQtd_Feito()));
		paginaFeitoLabel.setText(String.valueOf(paginaFeito));
		paginaAFazerLabel.setText(String.valueOf(paginaAFazer));
		paginaFazendoLabel.setText(String.valueOf(paginaFazendo));
		metodos.finalizarCalculoProducao();// finaliza o calculo de produï¿½ï¿½o que foi iniciado junto com a pesquisa das
											// tarefas
		producao1Text.setText(String.valueOf(converterDoubleDoisDecimais(kamban.getProducao1() * 100)) + "%");// seta os
																												// campos
																												// com
																												// // os
																												// resultado
		producaogeralText.setText(String.valueOf(converterDoubleDoisDecimais(kamban.getProducaoGeral() * 100)) + "%");
		realizadohojeText.setText(String.valueOf(converterDoubleDoisDecimais(kamban.getPontuacaoFeitaHoje())));
		tarefasnaofeitasText.setText(String.valueOf(metodos.getQtd_Tarefas()));

		for (int a = 0; a <= 4; a++) {
			for (int b = 0; b <= 4; b++) {
				Color cor = Color.WHITE;

				if (metodos.getId(a, b) != 0) {// SE o ID for diferente de zero existe dados entï¿½o preencha os
												// kambans

					cor = metodos.getAtrasada(a, b);
					AlterarCor(Graficos[a][b], cor);
					InserirInformacao(Graficos[a][b], metodos.getId(a, b), metodos.getDias_iniciado(a, b),
							metodos.getAtrasadoOuEmdia(a, b), metodos.getPrioridade(a, b), metodos.getTamanho(a, b),
							metodos.getDpto(a, b), metodos.getDesc(a, b), metodos.getData_real(a, b),
							metodos.getData_fim(a, b), metodos.getPrazo(a, b), metodos.getStatus_pendencia(a, b),
							metodos.getPorcentagem(a, b) + "%", metodos.getPeso(a, b),
							metodos.getPontuacao_realizada(a, b), metodos.getCentro_custo(a, b),
							metodos.getResponsavel(a, b), metodos.getAutoridade(a, b), metodos.getExecutores(a, b),
							metodos.getDias_atraso(a, b), metodos.getPendete_por(a, b), metodos.getAnexo(a, b));
				} else {// Se nï¿½o tiver limpe todo os kamabans
					cor = Color.WHITE;
					AlterarCor(Graficos[a][b], cor);
					InserirInformacao(Graficos[a][b], 0, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
							"", "", "", false);
					limparKambans(a, b);

				}
			}
		}
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

	@SuppressWarnings("deprecation")
	public String dataDeHoje(int dias) {//Data de hoje ou data desejada, quantidade de dias a menos que a data de hoje no parametro
		Date hoje = new Date();// Pegando data de hoje

		hoje.setDate(hoje.getDate()-dias);
		
		String s = new SimpleDateFormat("dd/MM/yyyy").format(hoje.getTime());// Transformando em String padrï¿½o
																				// do banco
		
		return s;
	}

	public static double converterDoubleDoisDecimais(double precoDouble) {
		DecimalFormat fmt = new DecimalFormat("##.00");
		String string = fmt.format(precoDouble);
		String[] part = string.split("[,]");
		String string2 = part[0] + "." + part[1];
		double preco = Double.parseDouble(string2);
		return preco;
	}
}// Fim da Classe
