package view;

//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.myscrum.banco.Banco;
import com.myscrum.model.Redimensionar;
import com.myscrum.model.Etapa;
import com.myscrum.model.EtapaDAO;

public class EtapaTela extends JPanel {

	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> etapaCombo;
	private JComboBox<String> ccCombo;
	private JTextField etapaText;
	private JButton salvarButton;
	private JButton atualizarButton;
	private JLabel etapaLabel;
	private JLabel cadastradoLabel;
	private JLabel ccLabel;

	private ResultSet etapas;
	private ResultSet cc;

	Redimensionar rsize = new Redimensionar();
	JPanel panel = new JPanel();

	EtapaDAO metodos = new EtapaDAO();
	String sql;

	public EtapaTela() {

		// construct preComponents
		String[] Items = { "Selecione" };

		// construct components
		etapaCombo = new JComboBox<String>(Items);
		ccCombo = new JComboBox<String>(Items);
		ccCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				carregarComboBoxEtapa(ccCombo.getSelectedItem().toString());
			}
		});
		etapaText = new JTextField(5);
		salvarButton = new JButton("Salvar");
		atualizarButton = new JButton("Atualizar");
		atualizarButton.setEnabled(false);
		etapaLabel = new JLabel("Etapa:");
		ccLabel = new JLabel("Centro de custo:");
		cadastradoLabel = new JLabel("J� cadastrado:");

		setBackground(Color.WHITE);

		// adjust size and set layout
		setPreferredSize(new Dimension(400, 400));

		// add components
		add(etapaCombo);
		add(ccCombo);
		add(etapaText);
		add(salvarButton);
		add(atualizarButton);
		add(etapaLabel);
		add(ccLabel);
		add(cadastradoLabel);

		// set component bounds (only needed by Absolute Positioning)
		etapaCombo.setBounds(110, 190, 210, 25);
		etapaCombo.setBackground(new Color(41, 106, 158));
		etapaCombo.setForeground(Color.WHITE);

		ccCombo.setBounds(110, 60, 210, 25);
		ccCombo.setBackground(new Color(41, 106, 158));
		ccCombo.setForeground(Color.WHITE);

		etapaText.setBounds(110, 125, 210, 30);
		etapaText.setBackground(new Color(41, 106, 158));
		etapaText.setForeground(Color.WHITE);

		salvarButton.setBounds(250, 265, 120, 35);
		salvarButton.setBackground(new Color(163, 184, 204));

		atualizarButton.setBounds(50, 265, 120, 35);
		atualizarButton.setBackground(new Color(163, 184, 204));

		etapaLabel.setBounds(110, 100, 95, 25);

		ccLabel.setBounds(110, 35, 95, 25);

		cadastradoLabel.setBounds(110, 165, 95, 25);

		etapaCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (etapaCombo.getSelectedIndex() == 0) {
					salvarButton();
					etapaText.setText("");
				} else {
					atualizarButton();
					etapaText.setText(etapaCombo.getSelectedItem().toString());
				}
			}
		});

		salvarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escolha;
				if (etapaText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o campos corretamente", "Campo Vazio", 0);
				} else if (etapaText.getText().equals(etapaCombo.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(null, "Altere o nome da etapa para atualizar", "Mensagem", 0);
				} else {
					escolha = JOptionPane.showConfirmDialog(null,
							"Deseja realmente cadastrar " + etapaText.getText() + " como etapa ?",
							"Selecione uma op��o", JOptionPane.YES_NO_OPTION);
					if (escolha == JOptionPane.YES_OPTION) {

						Etapa etapa = new Etapa(etapaText.getText(), ccCombo.getSelectedItem().toString());
						metodos.cadastar(etapa);
						criarListEtapa();
						carregarComboBoxEtapa(ccCombo.getSelectedItem().toString());
						etapaText.setText("");

						MenuBar.card7.criarListEtapa();
						MenuBar.card7.carregarComboBoxEtapa();
					}
				}
			}
		});

		atualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escolha;
				if (etapaText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o campos corretamente", "Campo Vazio", 0);
				} else if (etapaText.getText().equals(etapaCombo.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(null, "Altera o nome do etapa para atualizar", "Mensagem", 0);
				} else {
					escolha = JOptionPane
							.showConfirmDialog(null,
									"Deseja realmente alterar " + etapaCombo.getSelectedItem().toString() + " para "
											+ etapaText.getText() + " ?",
									"Selecione uma op��o", JOptionPane.YES_NO_OPTION);
					if (escolha == JOptionPane.YES_OPTION) {

						Etapa etapa = new Etapa(etapaCombo.getSelectedItem().toString(), etapaText.getText(),
								ccCombo.getSelectedItem().toString());
						metodos.atualizar(etapa);
						criarListEtapa();
						carregarComboBoxEtapa(ccCombo.getSelectedItem().toString());
						etapaText.setText("");

						MenuBar.card7.criarListEtapa();
						MenuBar.card7.carregarComboBoxEtapa();
					}
				}
			}
		});

		criarListCC();
		carregarComboBoxCC();
		criarListEtapa();
		carregarComboBoxEtapa("");
	}

	public void atualizarButton() {
		atualizarButton.setEnabled(true);
		salvarButton.setEnabled(false);
	}

	public void salvarButton() {
		salvarButton.setEnabled(true);
		atualizarButton.setEnabled(false);
	}

	public void carregarComboBoxEtapa(String centrocusto) {
		String a;
		String idCC = null;
		int b = 1;
		// Esvaziando a combobox
		while (b < etapaCombo.getItemCount()) {
			a = etapaCombo.getItemAt(b).toString();
			etapaCombo.removeItem(a);
		}

		try {
			cc.first();
			while (cc.next()) {
				if (cc.getString("centrocusto").equals(centrocusto)) {
					idCC = cc.getString("id_centro_custo");
				}
			}

			etapas.first();
			while (etapas.next()) {
				if (etapas.getString("id_cc").equals(idCC)) {
					etapaCombo.addItem(etapas.getString("etapa"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void criarListEtapa() {

		try {
			sql = "SELECT * FROM etapas ORDER BY etapa ASC";

			if (Banco.conexao()) {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.rs = Banco.st.executeQuery();

				etapas = Banco.rs;
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());
		}
	}

	public void carregarComboBoxCC() {
		String a;
		int b = 1;
		// Esvaziando a combobox
		while (b < ccCombo.getItemCount()) {
			a = ccCombo.getItemAt(b).toString();
			ccCombo.removeItem(a);
		}

		try {
			cc.first();
			while (cc.next()) {
				ccCombo.addItem(cc.getString("centrocusto"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void criarListCC() {

		try {
			sql = "SELECT * FROM centro_custo ORDER BY centrocusto ASC";

			if (Banco.conexao()) {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.rs = Banco.st.executeQuery();

				cc = Banco.rs;
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());
		}
	}
}
