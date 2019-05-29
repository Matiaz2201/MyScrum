package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import view.TarefaEditTela;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.myscrum.controller.Controle;
import com.myscrum.model.Redimensionar;
import com.myscrum.model.Sessao;

import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class TelaPrincipal extends JFrame {

	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Redimensionar rsize = new Redimensionar();
	Sessao s = new Sessao();
	private JTextField rodapeText;
	private JLabel fundo;
	private JMenuBar menuBar;
	private JMenu sistemaMenu;
	private JMenu tarefaMenu;
	private JMenu configMenu;
	private JMenu userMenu;
	private JMenu scrumMenu;
	private JMenu HHTMenu;
	private JMenu atalhoMenu;
	private JMenu ajudaMenu;
	private JMenu sobreMenu;
	private JMenu testeMenu;
	private JMenuItem sobreItem;
	private JMenuItem ajudaItem;
	private JMenuItem atalhoItem;
	private JMenuItem hhtItem;
	private JMenuItem kambanItem;
	private JMenuItem userItem;
	private JMenuItem configItem;
	private JMenuItem novatarefaItem;
	private JMenuItem tarefasItem;
	private JMenuItem sairItem;
	private JPanel panel;
	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Controle.encerrarSistema();
				Controle.setPrincipal(false);
				dispose();
			}
		});
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/com/myscrum/assets/setIcon1.png")));
		setTitle("MyScrum");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		menuBar = new JMenuBar();
		panel.add(menuBar, BorderLayout.NORTH);
		
		sistemaMenu = new JMenu("Sistema");
		menuBar.add(sistemaMenu);
		
		sairItem = new JMenuItem("Sair");
		sairItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controle.encerrarSistema();
				Controle.setPrincipal(false);
				dispose();	
			}
		});
		sistemaMenu.add(sairItem);
		 
		
		tarefaMenu = new JMenu("Tarefa");
		menuBar.add(tarefaMenu);
		//Tarefa
		novatarefaItem = new JMenuItem("Nova Tarefa");
		novatarefaItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.VK_CONTROL));
		novatarefaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controle.getTarefa() != true) {
					Controle.abrirframe("tarefaTela");
					TarefaEditTela tela = new TarefaEditTela(Controle.telatarefa);
					tela.setVisible(true);
					tela.salvarButton();
					tela.dataIniText.setText(tela.AtalhoCTRL());//Inserindo data de hoje no calendario
					tela.dataRealText.setText(tela.AtalhoCTRL());
					
				}else {
				TarefaEditTela tela = new TarefaEditTela(Controle.telatarefa);
				tela.setVisible(true);
				tela.salvarButton();
				tela.dataIniText.setText(tela.AtalhoCTRL());//Inserindo data de hoje no calendario
				tela.dataRealText.setText(tela.AtalhoCTRL());
				}
			}
		});
		
		//NovaTarefa Tela
		tarefaMenu.add(novatarefaItem);
		
		tarefasItem = new JMenuItem("Tarefas");
		tarefasItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.VK_CONTROL));
		tarefasItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controle.abrirframe("tarefaTela");
			}
		});
		tarefaMenu.add(tarefasItem);
		
		//Configuração Tela
		configMenu = new JMenu("Configura\u00E7\u00F5es");
		menuBar.add(configMenu);
		
		configItem = new JMenuItem("Configura\u00E7\u00F5es");
		configItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.VK_CONTROL));
		configItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(s.getFuncao() == 1){
				Controle.abrirframe("menuBar");
				}else{
					JOptionPane.showMessageDialog(null, "Acesso permitido somente para administradores");
				}
			}
		});
		configMenu.add(configItem);
		
		//Usuarios Tela
		userMenu = new JMenu("Usuarios");
		menuBar.add(userMenu);
		
		userItem = new JMenuItem("Usuario");
		userItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.VK_CONTROL));
		userItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s.getFuncao() == 1 || s.getFuncao() == 3){
				Controle.abrirframe("usuarioTela");
				}else{
					JOptionPane.showMessageDialog(null, "Acesso permitido somente para administradores ou gestores");
				}
			}
		});
		userMenu.add(userItem);
		
		//Kambam Tela
		scrumMenu = new JMenu("Scrum");
		menuBar.add(scrumMenu);
		
		kambanItem = new JMenuItem("Kamban");
		kambanItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.VK_CONTROL));
		kambanItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controle.abrirframe("kambanTela");
			}
		});
		scrumMenu.add(kambanItem);
		
		//HHT tela
		HHTMenu = new JMenu("HHT");
		menuBar.add(HHTMenu);
		
		hhtItem = new JMenuItem("HHT");
		hhtItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.VK_CONTROL));
		hhtItem.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				
				Controle.abrirframe("hhtTela");
			}
			
		});
		HHTMenu.add(hhtItem);

		
		//Atalho Tela
		atalhoMenu = new JMenu("Atalhos");
		menuBar.add(atalhoMenu);
		
		atalhoItem = new JMenuItem("Atalhos");
		atalhoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.VK_CONTROL));
		atalhoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controle.abrirframe("atalhoTela");
			}
		});
		atalhoMenu.add(atalhoItem);
		
		//Ajuda Tela
		ajudaMenu = new JMenu("Ajuda");
		menuBar.add(ajudaMenu);
		
		ajudaItem = new JMenuItem("Contato");
		ajudaItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.VK_CONTROL));
		ajudaItem.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				Controle.abrirframe("contatoTela");
			}
		});
		ajudaMenu.add(ajudaItem);
		
		
		//Sobre Tela
		sobreMenu = new JMenu("Sobre");
		menuBar.add(sobreMenu);
		
		sobreItem = new JMenuItem("Software");
		sobreMenu.add(sobreItem);
		
		
		
		fundo = new JLabel("");
		fundo.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon icon = Redimensionar.maximizar("/com/myscrum/assets/fundo_tela_principal.jpg");
		fundo.setIcon(icon);
		panel.add(fundo, BorderLayout.CENTER);
		
		
		
		
		rodapeText = new JTextField();
		rodapeText.setHorizontalAlignment(SwingConstants.CENTER);
		rodapeText.setEditable(false);
		panel.add(rodapeText, BorderLayout.SOUTH);
		rodapeText.setColumns(10);
		
		
		//tela de loading
		Controle.setLoading(true);
		//Inicia contagem de tempo de sessão
		iniciaCronometro();
	
	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void iniciaCronometro(){
    	Timer timer = null;      
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date tempo = new Date();
        tempo.setHours(0);
        tempo.setMinutes(0);
        tempo.setSeconds(0);
        
        if (timer == null)   
         {      
             timer = new Timer();  
            TimerTask tarefa = new TimerTask() {     
                 public void run()   
                 {      
                     try {  
                    	 if(tempo.getSeconds() == 60){
                    		 tempo.setMinutes(tempo.getMinutes()+1);
                    		 tempo.setSeconds(0);
                    	 }
                    	 if(tempo.getMinutes() == 60) {
                    		 tempo.setHours(tempo.getHours()+1);
                    		 tempo.setMinutes(0);
                    		 
                    	 }
                    	 
                    	 tempo.setSeconds(tempo.getSeconds()+1);
                    	 rodapeText.setText("User: "+ s.getNome()+"               "
                    	 		+ "      "+"Tempo de sessão: "+format.format(tempo)
                    	 +"                " + " (Versão 1.4.6)");
                           
                   } catch (Exception e) {      
                         e.printStackTrace();      
                     }      
                }   
             };      
            timer.scheduleAtFixedRate(tarefa, 0, 1000);      
        }    
	}
}
	