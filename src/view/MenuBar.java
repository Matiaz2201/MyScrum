package view;

//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import com.myscrum.controller.Controle;
import com.myscrum.controller.Controlev;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuBar extends JFrame {

	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Controlev control = new Controlev();
	public static JPanel card1;
	public static JPanel card3;
	public static JPanel card2;
	public static JPanel card4;
	public static JPanel card5;
	public static EtapaTela card6;
	public static SubEtapaTela card7;

	public static void main(String[] args) {
	}

	public MenuBar() {

		// set iniciais
		super("Configura��es");// titulo do frame
		getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {

				JOptionPane.showMessageDialog(null, "oi");

			}

			@Override
			public void keyPressed(KeyEvent e) {

				JOptionPane.showMessageDialog(null, "oi");

			}
		});
		setSize(435, 400);// define o tamanho do frame
		setResizable(false);

		// Setando Icone do Frame
		java.net.URL url = this.getClass().getResource("/com/myscrum/assets/setIcon1.png");
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(iconeTitulo);

		// Cria��o do objeto de menu
		JTabbedPane tabbedPane = new JTabbedPane();

		card1 = new CcCustoTela();
		card1.setLayout(null);

		card2 = new DptoTela();
		card2.setLayout(null);

		card3 = new TamanhoTela();
		card3.setLayout(null);

		card4 = new FeriadoTela();
		card4.setLayout(null);

		card5 = new ProcessosTela();
		card5.setLayout(null);

		card6 = new EtapaTela();
		card6.setLayout(null);

		card7 = new SubEtapaTela();
		card7.setLayout(null);

		// Criando bot�es no menu e setando seu paineis referentes

		tabbedPane.addTab("Centros de Custo", card1);
		tabbedPane.addTab("Departamentos", card2);
		tabbedPane.addTab("Tamanho", card3);
		tabbedPane.addTab("Feriados", card4);
		tabbedPane.addTab("Processos", card5);
		tabbedPane.addTab("Etapa", card6);
		tabbedPane.addTab("SubEtapa", card7);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		// propriedades do frame
		setLocationRelativeTo(null);// localidade do frame referente a tela(centraliza o frame)
		setVisible(true);// habilita visualiza��o do frame
		setBackground(Color.WHITE);

		// metodos da janela
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
				Controle.setMenu(false);
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
			}
		});
	}
}
