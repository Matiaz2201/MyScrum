
package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import com.myscrum.banco.Banco;
import com.myscrum.controller.Controle;
import com.myscrum.model.Sessao;
import com.myscrum.model.TextPrompt;
import com.myscrum.model.Salvar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginTela extends JFrame {
	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField senhaText;
	private JTextField loginText;
	private JButton entrarButton;
	private JCheckBox salvarLoginCheckBox;
	private JLabel horarioLabel;
	private JLabel fundoLabel;
	private Panel leftJapnel;
	private JLabel myScrumLabel;
	private JLabel loginLabel;
	private JLabel senhaLabel;
	private JButton cancelarButton;
	private JLabel dataLabel;
	private JPanel rightJpanel;

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
					LoginTela frame = new LoginTela();
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
	public LoginTela() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Banco.close();
			}
		
		});
		setTitle("MyScrum");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(LoginTela.class.getResource("/com/myscrum/assets/setIcon1.png")));
		setUndecorated(true);
		setBounds(100, 100, 868, 476);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		leftJapnel = new Panel();
		leftJapnel.setName("");
		leftJapnel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(leftJapnel);
		leftJapnel.setLayout(null);

		dataLabel = new JLabel();
		dataLabel.setFont(new Font("Century Gothic", Font.BOLD, 18));
		dataLabel.setForeground(Color.WHITE);
		dataLabel.setBounds(12, 398, 331, 35);
		leftJapnel.add(dataLabel);

		horarioLabel = new JLabel("");
		horarioLabel.setForeground(Color.WHITE);
		horarioLabel.setFont(new Font("Century Gothic", Font.BOLD, 43));
		horarioLabel.setHorizontalAlignment(SwingConstants.LEFT);
		horarioLabel.setBounds(12, 346, 193, 54);
		leftJapnel.add(horarioLabel);

		fundoLabel = new JLabel("");
		fundoLabel.setBounds(0, 0, 429, 445);
		leftJapnel.add(fundoLabel);

		rightJpanel = new JPanel();
		rightJpanel.setBackground(new Color(0, 0, 51));
		contentPane.add(rightJpanel);
		rightJpanel.setLayout(null);

		loginText = new JTextField();
		TextPrompt tp = new TextPrompt("Usuário",loginText);
		tp.setForeground(Color.WHITE);
		tp.setFont(new Font("Century Gothic", Font.BOLD, 12));
		tp.changeAlpha(0.5f);
		loginText.setBounds(88, 161, 227, 23);
		rightJpanel.add(loginText);
		loginText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginText.setText("");
			}
		});
		loginText.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
					senhaText.requestFocus();
				}
			}

		});
		loginText.setForeground(Color.WHITE);
		loginText.setFont(new Font("Century Gothic", Font.BOLD, 12));
		loginText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		loginText.setBackground(new Color(0, 0, 51));
		loginText.setColumns(10);

		senhaText = new JPasswordField();
		TextPrompt tpsenha = new TextPrompt("Password",senhaText);
		tpsenha.setForeground(Color.WHITE);
		tpsenha.setFont(new Font("Century Gothic", Font.BOLD, 12));
		tpsenha.changeAlpha(0.5f);
		senhaText.setBounds(88, 240, 235, 23);
		rightJpanel.add(senhaText);
		senhaText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				senhaText.setText("");
			}
		});
		senhaText.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					logar();
				}
			}

		});
		senhaText.setForeground(Color.WHITE);
		senhaText.setFont(new Font("Century Gothic", Font.BOLD, 12));
		senhaText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		senhaText.setBackground(new Color(0, 0, 51));
		senhaText.setColumns(10);

		myScrumLabel = new JLabel("MY SCRUM");
		myScrumLabel.setBounds(39, 34, 93, 23);
		rightJpanel.add(myScrumLabel);
		myScrumLabel.setFont(new Font("Century Gothic", Font.BOLD, 18));
		myScrumLabel.setForeground(Color.WHITE);

		loginLabel = new JLabel("Login");
		loginLabel.setBounds(88, 130, 36, 19);
		rightJpanel.add(loginLabel);
		loginLabel.setForeground(Color.WHITE);
		loginLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));

		senhaLabel = new JLabel("Senha");
		senhaLabel.setBounds(88, 209, 41, 19);
		rightJpanel.add(senhaLabel);
		senhaLabel.setForeground(Color.WHITE);
		senhaLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));

		entrarButton = new JButton("Entrar");
		entrarButton.setBounds(88, 334, 99, 32);
		rightJpanel.add(entrarButton);

		salvarLoginCheckBox = new JCheckBox("Salvar login");
		salvarLoginCheckBox.setBounds(88, 270, 99, 24);
		rightJpanel.add(salvarLoginCheckBox);
		salvarLoginCheckBox.setBackground(new Color(0, 0, 51));
		salvarLoginCheckBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		salvarLoginCheckBox.setForeground(Color.WHITE);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(224, 334, 99, 32);
		rightJpanel.add(cancelarButton);
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Banco.close();
				System.exit(0);
			}
		});
		entrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});

		imagem();
		Banco.conexao();// Inicia conexão com banco pela clase Abstrata

	}
	
	public void logar() {

		String puname = loginText.getText(); // Alocando variavel login
		@SuppressWarnings("deprecation")
		String ppaswd = senhaText.getText(); // Alocando variavel senha
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA"); // Criptografia
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		md.update(ppaswd.getBytes());
		BigInteger hash = new BigInteger(1, md.digest());
		String retornasenha = hash.toString(16); // senha criptografada
		// System.out.println("retorna senha:" +retornasenha); = Retorna senha no
		// console

		try {// consulta se o usuario existi
			String sql = "SELECT login,senha FROM pessoa WHERE login='" + puname + "'and senha='" + retornasenha + "'";

			if (Banco.conexao()) {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.rs = Banco.st.executeQuery();
				if (Banco.rs.next()) {// consultar se ta ativo
					try {
						sql = "SELECT nome,id_pessoa,adm,email,"
								+ "(SELECT departamento.departamento FROM departamento WHERE departamento.id_departamento=pessoa.id_departamento) AS DPTO, \n "
								+ "(SELECT centro_custo.centrocusto FROM centro_custo WHERE centro_custo.id_centro_custo=pessoa.id_centrocusto) AS CC \n "
								+ "FROM pessoa WHERE login='" + puname + "'and senha='" + retornasenha
								+ "' and ativo=1";
						Banco.st = Banco.con.prepareStatement(sql);
						Banco.rs = Banco.st.executeQuery();
						if (Banco.rs.next()) {
							JOptionPane.showMessageDialog(null, "Bem vindo(a) " + Banco.rs.getString(1), "My Scrum", 1);
							Sessao sessao = Sessao.getInstance();
							sessao.setNome(Banco.rs.getString(1));
							sessao.setFuncao(Banco.rs.getInt(3));// Armazena qual a função do usuario
							sessao.setUsuario(puname);// Armazena usuario em Sess?o
							sessao.setSenha(retornasenha);// Armazena senha em Sess?o
							sessao.setId(Banco.rs.getInt(2));
							sessao.setEmail(Banco.rs.getString(4));
							sessao.setDpto(Banco.rs.getString(5));
							sessao.setCC(Banco.rs.getString(6));
							if (salvarLoginCheckBox.isSelected()) {
								Salvar status = new Salvar();
								status.write();
							}

							Controle.abrirframe("telaPrincipal");
							dispose();// Fecha frame atual

						} else {

							JOptionPane.showMessageDialog(null, "Usuario Bloqueado");
							loginText.setText("");
							senhaText.setText("");
							loginText.requestFocus();

						}
					} catch (SQLException erro) {
						JOptionPane.showMessageDialog(null, erro.toString());
					}
				} else {

					JOptionPane.showMessageDialog(null, "Usuario ou Senha incorreto !!!");
					senhaText.setText("");
					senhaText.requestFocus();

				}
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());
		}

	}

	public void imagem() {

		// Fundo
		Random r = new Random();
		fundoLabel.setIcon(new ImageIcon(LoginTela.class.getResource("/com/myscrum/images/" + r.nextInt(14) + ".jpg")));

		// Data
		Date hoje = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));

		String dataExtenso = formatador.format(hoje);

		dataLabel.setText(dataExtenso);

		// Relogio
		Timer timer = null;
		final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date tempo = new Date();

		if (timer == null) {
			timer = new Timer();
			TimerTask tarefa = new TimerTask() {
				@SuppressWarnings("deprecation")
				public void run() {
					try {
						if (tempo.getSeconds() == 60) {
							tempo.setMinutes(tempo.getMinutes() + 1);
							tempo.setSeconds(0);
						}
						if (tempo.getMinutes() == 60) {
							tempo.setHours(tempo.getHours() + 1);
							tempo.setMinutes(0);

						}

						tempo.setSeconds(tempo.getSeconds() + 1);
						horarioLabel.setText(format.format(tempo));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			timer.scheduleAtFixedRate(tarefa, 0, 1000);
		}
	}
}
