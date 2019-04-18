package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.myscrum.controller.Controle;

import javax.swing.JScrollPane;

public class AtalhoTela extends JFrame {

	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Controle control = new Controle();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtalhoTela frame = new AtalhoTela();
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
	public AtalhoTela() {
		super("Atalhos");
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				Controle.setAtalho(false);
			}
		});
		
		  //mudando Icone do Frame
        java.net.URL url = this.getClass().getResource("/com/myscrum/assets/setIcon1.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(iconeTitulo);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 545, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JScrollPane sp = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		setPreferredSize(new Dimension(505, 626));
		getContentPane().add(sp, BorderLayout.CENTER);
		
		
		JTextArea txtrAbreATela = new JTextArea();
		txtrAbreATela.setEditable(false);
		txtrAbreATela.setWrapStyleWord(true);
		txtrAbreATela.setText("Abre a tela de Configura\u00E7\u00F5es (somente para ADM)");
		txtrAbreATela.setTabSize(2);
		txtrAbreATela.setLineWrap(true);
		txtrAbreATela.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtrAbreATela.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrAbreATela.setBackground(SystemColor.menu);
		txtrAbreATela.setAlignmentY(1.0f);
		txtrAbreATela.setAlignmentX(0.0f);
		txtrAbreATela.setBounds(105, 171, 167, 59);
		panel.add(txtrAbreATela);
		
		JLabel lblShiftA = new JLabel("SHIFT + A");
		lblShiftA.setHorizontalAlignment(SwingConstants.CENTER);
		lblShiftA.setBounds(10, 331, 64, 14);
		panel.add(lblShiftA);
		
		JLabel lblShiftK = new JLabel("SHIFT + K");
		lblShiftK.setHorizontalAlignment(SwingConstants.CENTER);
		lblShiftK.setBounds(10, 401, 64, 14);
		panel.add(lblShiftK);
		
		JTextArea txtrAbreATela_2 = new JTextArea();
		txtrAbreATela_2.setEditable(false);
		txtrAbreATela_2.setWrapStyleWord(true);
		txtrAbreATela_2.setText("Abre a tela do Kamban");
		txtrAbreATela_2.setTabSize(2);
		txtrAbreATela_2.setLineWrap(true);
		txtrAbreATela_2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtrAbreATela_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrAbreATela_2.setBackground(SystemColor.menu);
		txtrAbreATela_2.setAlignmentY(1.0f);
		txtrAbreATela_2.setAlignmentX(0.0f);
		txtrAbreATela_2.setBounds(105, 379, 167, 59);
		panel.add(txtrAbreATela_2);
		
		JTextArea txtrNaTelaKamban = new JTextArea();
		txtrNaTelaKamban.setWrapStyleWord(true);
		txtrNaTelaKamban.setText("Na tela Kamban precione as teclas Alt + F5");
		txtrNaTelaKamban.setTabSize(5);
		txtrNaTelaKamban.setRows(5);
		txtrNaTelaKamban.setLineWrap(true);
		txtrNaTelaKamban.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrNaTelaKamban.setBackground(SystemColor.menu);
		txtrNaTelaKamban.setBounds(307, 519, 159, 59);
		panel.add(txtrNaTelaKamban);
		
		JTextArea txtrNaTelaPrincipal_4 = new JTextArea();
		txtrNaTelaPrincipal_4.setEditable(false);
		txtrNaTelaPrincipal_4.setWrapStyleWord(true);
		txtrNaTelaPrincipal_4.setText("Na tela principal precione as teclas Shift + K");
		txtrNaTelaPrincipal_4.setLineWrap(true);
		txtrNaTelaPrincipal_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrNaTelaPrincipal_4.setBackground(SystemColor.menu);
		txtrNaTelaPrincipal_4.setBounds(307, 379, 159, 59);
		panel.add(txtrNaTelaPrincipal_4);
		
		JLabel lblCtrl = new JLabel("Ctrl + :");
		lblCtrl.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrl.setBounds(10, 471, 64, 14);
		panel.add(lblCtrl);
		
		JTextArea txtrInsereAData = new JTextArea();
		txtrInsereAData.setEditable(false);
		txtrInsereAData.setWrapStyleWord(true);
		txtrInsereAData.setText("Insere a data do dia no campo  em que est\u00E1 sendo digitando.");
		txtrInsereAData.setTabSize(2);
		txtrInsereAData.setLineWrap(true);
		txtrInsereAData.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtrInsereAData.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrInsereAData.setBackground(SystemColor.menu);
		txtrInsereAData.setAlignmentY(1.0f);
		txtrInsereAData.setAlignmentX(0.0f);
		txtrInsereAData.setBounds(105, 449, 167, 59);
		panel.add(txtrInsereAData);
		
		JTextArea txtrNaTelaTarefa = new JTextArea();
		txtrNaTelaTarefa.setTabSize(5);
		txtrNaTelaTarefa.setRows(5);
		txtrNaTelaTarefa.setWrapStyleWord(true);
		txtrNaTelaTarefa.setText("Na tela tarefa selecione o campo a ser digitado e precione as teclas Ctrl + :");
		txtrNaTelaTarefa.setLineWrap(true);
		txtrNaTelaTarefa.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrNaTelaTarefa.setBackground(SystemColor.menu);
		txtrNaTelaTarefa.setBounds(307, 449, 159, 59);
		panel.add(txtrNaTelaTarefa);
		
		JTextArea txtrAbreEstaTela = new JTextArea();
		txtrAbreEstaTela.setEditable(false);
		txtrAbreEstaTela.setWrapStyleWord(true);
		txtrAbreEstaTela.setText("Abre esta tela com informa\u00E7\u00F5es sobre os atalhos");
		txtrAbreEstaTela.setTabSize(2);
		txtrAbreEstaTela.setLineWrap(true);
		txtrAbreEstaTela.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtrAbreEstaTela.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrAbreEstaTela.setBackground(SystemColor.menu);
		txtrAbreEstaTela.setAlignmentY(1.0f);
		txtrAbreEstaTela.setAlignmentX(0.0f);
		txtrAbreEstaTela.setBounds(105, 309, 167, 59);
		panel.add(txtrAbreEstaTela);
		
		JLabel lblAltF = new JLabel("Alt + F5 :");
		lblAltF.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltF.setBounds(10, 541, 64, 14);
		panel.add(lblAltF);
		
		JTextArea txtrNaTelaPrincipal = new JTextArea();
		txtrNaTelaPrincipal.setEditable(false);
		txtrNaTelaPrincipal.setWrapStyleWord(true);
		txtrNaTelaPrincipal.setText("Na tela principal precione as teclas Shift + T");
		txtrNaTelaPrincipal.setLineWrap(true);
		txtrNaTelaPrincipal.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrNaTelaPrincipal.setBackground(SystemColor.menu);
		txtrNaTelaPrincipal.setBounds(307, 101, 159, 59);
		panel.add(txtrNaTelaPrincipal);
		
		JTextArea txtrCriaUmaNova = new JTextArea();
		txtrCriaUmaNova.setEditable(false);
		txtrCriaUmaNova.setWrapStyleWord(true);
		txtrCriaUmaNova.setLineWrap(true);
		txtrCriaUmaNova.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtrCriaUmaNova.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtrCriaUmaNova.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtrCriaUmaNova.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtrCriaUmaNova.setText("Cria uma nova tarefa");
		txtrCriaUmaNova.setBackground(UIManager.getColor("CheckBox.background"));
		txtrCriaUmaNova.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrCriaUmaNova.setTabSize(2);
		txtrCriaUmaNova.setBounds(105, 31, 167, 59);
		panel.add(txtrCriaUmaNova);
		
		JTextArea txtrNaTela = new JTextArea();
		txtrNaTela.setEditable(false);
		txtrNaTela.setLineWrap(true);
		txtrNaTela.setWrapStyleWord(true);
		txtrNaTela.setText("Na tela principal precione as teclas Shift + N");
		txtrNaTela.setBackground(UIManager.getColor("CheckBox.background"));
		txtrNaTela.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrNaTela.setBounds(307, 31, 159, 59);
		panel.add(txtrNaTela);
		
		JLabel lblTecla = new JLabel("Tecla:");
		lblTecla.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTecla.setBounds(10, 11, 64, 14);
		panel.add(lblTecla);
		lblTecla.setBackground(Color.BLACK);
		lblTecla.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrio.setBackground(Color.BLACK);
		lblDescrio.setBounds(151, 11, 64, 14);
		panel.add(lblDescrio);
		
		
		JLabel lblComoFazer = new JLabel("Como fazer:");
		lblComoFazer.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblComoFazer.setHorizontalAlignment(SwingConstants.LEFT);
		lblComoFazer.setBackground(Color.BLACK);
		lblComoFazer.setBounds(350, 11, 78, 14);
		panel.add(lblComoFazer);
		
		JLabel lblCtrlN = new JLabel("SHIFT + N");
		lblCtrlN.setBounds(10, 53, 64, 14);
		panel.add(lblCtrlN);
		lblCtrlN.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblShiftT = new JLabel("SHIFT + T");
		lblShiftT.setHorizontalAlignment(SwingConstants.CENTER);
		lblShiftT.setBounds(10, 123, 64, 14);
		panel.add(lblShiftT);
		
		JTextArea txtrVisualizaoDeTodas = new JTextArea();
		txtrVisualizaoDeTodas.setEditable(false);
		txtrVisualizaoDeTodas.setWrapStyleWord(true);
		txtrVisualizaoDeTodas.setText("Visualiza\u00E7\u00E3o de todas as tarefas");
		txtrVisualizaoDeTodas.setTabSize(2);
		txtrVisualizaoDeTodas.setLineWrap(true);
		txtrVisualizaoDeTodas.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtrVisualizaoDeTodas.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrVisualizaoDeTodas.setBackground(SystemColor.menu);
		txtrVisualizaoDeTodas.setAlignmentY(1.0f);
		txtrVisualizaoDeTodas.setBounds(105, 101, 167, 59);
		panel.add(txtrVisualizaoDeTodas);
		
		JLabel lblShiftC = new JLabel("SHIFT + C");
		lblShiftC.setHorizontalAlignment(SwingConstants.CENTER);
		lblShiftC.setBounds(10, 193, 64, 14);
		panel.add(lblShiftC);
		
		JTextArea txtrNaTelaPrincipal_1 = new JTextArea();
		txtrNaTelaPrincipal_1.setEditable(false);
		txtrNaTelaPrincipal_1.setWrapStyleWord(true);
		txtrNaTelaPrincipal_1.setText("Na tela principal precione as teclas Shift + C");
		txtrNaTelaPrincipal_1.setLineWrap(true);
		txtrNaTelaPrincipal_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrNaTelaPrincipal_1.setBackground(SystemColor.menu);
		txtrNaTelaPrincipal_1.setBounds(307, 171, 159, 59);
		panel.add(txtrNaTelaPrincipal_1);
		
		JLabel lblShiftU = new JLabel("SHIFT + U");
		lblShiftU.setHorizontalAlignment(SwingConstants.CENTER);
		lblShiftU.setBounds(10, 261, 64, 14);
		panel.add(lblShiftU);
		
		JTextArea txtrAbreATela_1 = new JTextArea();
		txtrAbreATela_1.setEditable(false);
		txtrAbreATela_1.setWrapStyleWord(true);
		txtrAbreATela_1.setText("Abre a tela de Cadastro de Usu\u00E1rios (somente para ADM)");
		txtrAbreATela_1.setTabSize(2);
		txtrAbreATela_1.setLineWrap(true);
		txtrAbreATela_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtrAbreATela_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrAbreATela_1.setBackground(SystemColor.menu);
		txtrAbreATela_1.setAlignmentY(1.0f);
		txtrAbreATela_1.setAlignmentX(0.0f);
		txtrAbreATela_1.setBounds(105, 239, 167, 59);
		panel.add(txtrAbreATela_1);
		
		JTextArea txtrNaTelaPrincipal_2 = new JTextArea();
		txtrNaTelaPrincipal_2.setEditable(false);
		txtrNaTelaPrincipal_2.setWrapStyleWord(true);
		txtrNaTelaPrincipal_2.setText("Na tela principal precione as teclas Shift + U");
		txtrNaTelaPrincipal_2.setLineWrap(true);
		txtrNaTelaPrincipal_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrNaTelaPrincipal_2.setBackground(SystemColor.menu);
		txtrNaTelaPrincipal_2.setBounds(307, 239, 159, 59);
		panel.add(txtrNaTelaPrincipal_2);
		
		JTextArea txtrNaTelaPrincipal_3 = new JTextArea();
		txtrNaTelaPrincipal_3.setEditable(false);
		txtrNaTelaPrincipal_3.setWrapStyleWord(true);
		txtrNaTelaPrincipal_3.setText("Na tela principal precione as teclas Shift + A");
		txtrNaTelaPrincipal_3.setLineWrap(true);
		txtrNaTelaPrincipal_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrNaTelaPrincipal_3.setBackground(SystemColor.menu);
		txtrNaTelaPrincipal_3.setBounds(307, 309, 159, 59);
		panel.add(txtrNaTelaPrincipal_3);
		
		JTextArea txtrAtualizaATela = new JTextArea();
		txtrAtualizaATela.setWrapStyleWord(true);
		txtrAtualizaATela.setText("Atualiza a tela do Kamban");
		txtrAtualizaATela.setTabSize(2);
		txtrAtualizaATela.setLineWrap(true);
		txtrAtualizaATela.setEditable(false);
		txtrAtualizaATela.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtrAtualizaATela.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtrAtualizaATela.setBackground(SystemColor.menu);
		txtrAtualizaATela.setAlignmentY(1.0f);
		txtrAtualizaATela.setAlignmentX(0.0f);
		txtrAtualizaATela.setBounds(105, 519, 167, 59);
		panel.add(txtrAtualizaATela);
	
		//set final 
		setLocationRelativeTo(null);

	
	}
}
