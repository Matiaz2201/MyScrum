
package com.myscrum.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;

import javax.swing.JOptionPane;

import view.TelaLoading;
import view.AtalhoTela;
import view.ContatoTela;
import view.KambanTela;
import view.LoginTela;
import view.MenuBar;
import view.TarefaTela;
import view.TelaPrincipal;
import view.UsuarioTela;
import view.HhtTela;

public class Controle extends Controlev {

	static public TelaPrincipal telaprincipal;
	static public TarefaTela telatarefa;
	static public UsuarioTela telausuario;
	static public MenuBar telamenu;
	static public AtalhoTela telaAtalho;
	static public KambanTela telaKamban;
	static public ContatoTela telaContato;
	static public HhtTela telahht;

	public static void abrirframe(String nometela) {

		switch (nometela) {
		
		case "telaPrincipal":
			if (getPrincipal() == false) {
				TelaLoading Loading = new TelaLoading();
				Loading.setVisible(true);
				Loading.toFront();
				
				Thread t = new Thread() {
					@Override
					public void run() {

						telaprincipal = new TelaPrincipal();
						while (getLoading() != true) {
							System.out.println("Continua,.....");
						}

						Loading.dispose();
						telaprincipal.setLocationRelativeTo(null);
						telaprincipal.setVisible(true);
						setPrincipal(true);
						setLoading(false);

					}
				};
				t.start();
			}
			break;
		

		case "tarefaTela":
			if (getTarefa() == false) {
				try {
					telatarefa = new TarefaTela();
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				telatarefa.setVisible(true);
				setTarefa(true);
			} else {
				JOptionPane.showMessageDialog(null, "Esta janela já está aberta ");
				telatarefa.toFront();
			}
			break;

		case "usuarioTela":
			if (getUsuario() == false) {
				telausuario = new UsuarioTela();
				telausuario.setVisible(true);
				setUsuario(true);
			} else {
				JOptionPane.showMessageDialog(null, "Esta janela já está aberta ");
				telausuario.toFront();
			}
			break;

		case "menuBar":
			if (getMenu() == false) {
				telamenu = new MenuBar();
				telamenu.setVisible(true);
				setMenu(true);
			} else {
				JOptionPane.showMessageDialog(null, "Esta janela já está aberta ");
				telamenu.toFront();
			}
			break;

		case "atalhoTela":
			if (getAtalho() == false) {
				telaAtalho = new AtalhoTela();
				telaAtalho.setVisible(true);
				setAtalho(true);

			} else {
				JOptionPane.showMessageDialog(null, "Esta janela já está aberta ");
				telaAtalho.toFront();
			}
			break;

		case "kambanTela":
			if (getKamban() == false) {
				TelaLoading Loading = new TelaLoading();
				Loading.setVisible(true);
				Loading.toFront();
				
				Toolkit r = Toolkit.getDefaultToolkit();
				Dimension size = r.getScreenSize();

				Thread t = new Thread() {
					@Override
					public void run() {

						if(size.getWidth() == 1920) {
							telaKamban = new KambanTela("FullHD");
						}else {
							telaKamban = new KambanTela();
						}
						while (getLoading() != true) {
							System.out.println("Continua,.....");
						}

						Loading.dispose();
						telaKamban.setLocationRelativeTo(null);
						telaKamban.setVisible(true);
						setKamban(true);
						setLoading(false);

					}
				};
				t.start();
			}else {
				JOptionPane.showMessageDialog(null, "Esta janela já está aberta ");
				telaKamban.toFront();
			}
			break;

		case "contatoTela":
			if (getContato() == false) {
				telaContato = new ContatoTela();
				telaContato.setVisible(true);
				setContato(true);

			} else {
				JOptionPane.showMessageDialog(null, "Esta janela já está aberta ");
				telaContato.toFront();
			}
			break;

		case "hhtTela":
			if (gethhtTela() == false) {
				TelaLoading Loading = new TelaLoading();
		    	Loading.setVisible(true);
				Loading.toFront();
				

				Thread t = new Thread() {
					@Override
					public void run() {

						telahht = new HhtTela();
						while (getLoading() != true) {
							System.out.println("Continua,.....");
						}

						Loading.dispose();
						telahht.setLocationRelativeTo(null);
						telahht.setVisible(true);
						setLoading(false);

					}
				};
				t.start();

				sethhtTela(true);

			} else {
				JOptionPane.showMessageDialog(null, "Esta janela já está aberta ");
				telahht.toFront();
			}
			break;

//--------------------- fim -------------------------------------		   
		}
	}

    public static void encerrarSistema() {
    	if(tarefa == true) {
    		telatarefa.dispose();
    		setTarefa(false);
    	}
    	if(usuario == true) {
    		telausuario.dispose();
    		setUsuario(false);
    	}
    	if(menu == true) {
    		telamenu.dispose();
    		setMenu(false);
    	}
    	if(atalho == true) {
    		telaAtalho.dispose();
    		setAtalho(false);
    	}
    	if(kamban == true) {
    		telaKamban.dispose();
    		setKamban(false);
    	}
    	if(contato == true) {
    		telaContato.dispose();
    		setContato(false);
    	}
    	if(hht_tela == true) {
    		telahht.dispose();
    		sethhtTela(false);
    	}
    	
    	LoginTela telalogin = new LoginTela();
    	telalogin.setVisible(true);
    }
}