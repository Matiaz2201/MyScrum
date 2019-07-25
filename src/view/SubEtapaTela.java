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
import com.myscrum.model.SubEtapa;
import com.myscrum.model.SubEtapaDAO;

public class SubEtapaTela extends JPanel {

	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> subEtapaCombo;
	private JComboBox<String> etapaCombo;
	private JTextField subEtapaText;
	private JButton salvarButton;
	private JButton atualizarButton;
	private JLabel subEtapaLabel;
	private JLabel cadastradoLabel;
	private JLabel etapaECC;

	private ResultSet subEtapas;
	private ResultSet etapas;

	Redimensionar rsize = new Redimensionar();
	JPanel panel = new JPanel();

	SubEtapaDAO metodos = new SubEtapaDAO();
	String sql;

	public SubEtapaTela() {
		// construct preComponents
		String[] subEtapaComboItems = { "Selecione" };

		// construct components
		subEtapaCombo = new JComboBox<String>(subEtapaComboItems);
		etapaCombo = new JComboBox<String>(subEtapaComboItems);
		etapaCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				carregarComboBoxSubEtapa(etapaCombo.getSelectedItem().toString());
			}
		});
		subEtapaText = new JTextField(5);
		salvarButton = new JButton("Salvar");
		atualizarButton = new JButton("Atualizar");
		atualizarButton.setEnabled(false);
		subEtapaLabel = new JLabel("Sub etapa:");
		cadastradoLabel = new JLabel("J� cadastrado:");
		etapaECC = new JLabel("Centro de custo e Etapa");

		setBackground(Color.WHITE);

		// adjust size and set layout
		setPreferredSize(new Dimension(400, 400));

		// add components
		add(subEtapaCombo);
		add(etapaCombo);
		add(subEtapaText);
		add(salvarButton);
		add(atualizarButton);
		add(subEtapaLabel);
		add(cadastradoLabel);
		add(etapaECC);

		// set component bounds (only needed by Absolute Positioning)
		subEtapaCombo.setBounds(110, 190, 210, 25);
		subEtapaCombo.setBackground(new Color(41, 106, 158));
		subEtapaCombo.setForeground(Color.WHITE);

		etapaCombo.setBounds(110, 60, 210, 25);
		etapaCombo.setBackground(new Color(41, 106, 158));
		etapaCombo.setForeground(Color.WHITE);

		subEtapaText.setBounds(110, 125, 210, 30);
		subEtapaText.setBackground(new Color(41, 106, 158));
		subEtapaText.setForeground(Color.WHITE);

		salvarButton.setBounds(250, 265, 120, 35);
		salvarButton.setBackground(new Color(163, 184, 204));

		atualizarButton.setBounds(50, 265, 120, 35);
		atualizarButton.setBackground(new Color(163, 184, 204));

		subEtapaLabel.setBounds(110, 100, 95, 25);

		cadastradoLabel.setBounds(110, 165, 95, 25);

		etapaECC.setBounds(110, 35, 160, 25);

		subEtapaCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subEtapaCombo.getSelectedIndex() == 0) {
					salvarButton();
					subEtapaText.setText("");
				} else {
					atualizarButton();
					subEtapaText.setText(subEtapaCombo.getSelectedItem().toString());
				}
			}
		});

		salvarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escolha;
				if (subEtapaText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o campos corretamente", "Campo Vazio", 0);
				} else if (subEtapaText.getText().equals(subEtapaCombo.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(null, "Altere o nome da etapa para atualizar", "Mensagem", 0);
				} else {
					escolha = JOptionPane.showConfirmDialog(null,
							"Deseja realmente cadastrar " + subEtapaText.getText() + " como etapa ?",
							"Selecione uma op��o", JOptionPane.YES_NO_OPTION);
					if (escolha == JOptionPane.YES_OPTION) {
						String[] etapa = { "" };

						etapa = etapaCombo.getSelectedItem().toString().split("@");

						SubEtapa subetapa = new SubEtapa(subEtapaText.getText(), etapa[1], etapa[0]);

						metodos.cadastrar(subetapa);
						subEtapaText.setText("");

						criarListSubEtapa();
						carregarComboBoxSubEtapa(etapaCombo.getSelectedItem().toString());

					}
				}
			}
		});

		atualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escolha;
				if (subEtapaText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o campos corretamente", "Campo Vazio", 0);
				} else if (subEtapaText.getText().equals(subEtapaCombo.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(null, "Altera o nome da etapa para atualizar", "Mensagem", 0);
				} else {
					escolha = JOptionPane
							.showConfirmDialog(null,
									"Deseja realmente alterar " + subEtapaCombo.getSelectedItem().toString() + " para "
											+ subEtapaText.getText() + " ?",
									"Selecione uma op��o", JOptionPane.YES_NO_OPTION);
					if (escolha == JOptionPane.YES_OPTION) {
						String[] etapa = { "" };

						etapa = etapaCombo.getSelectedItem().toString().split("@");
						

						SubEtapa subetapa = new SubEtapa(subEtapaCombo.getSelectedItem().toString(),
								subEtapaText.getText(), etapa[1], etapa[0]);

						metodos.atualizar(subetapa);
						subEtapaText.setText("");

						criarListSubEtapa();
						carregarComboBoxSubEtapa(etapaCombo.getSelectedItem().toString());
					}
				}
			}
		});

		criarListEtapa();
		carregarComboBoxEtapa();
		criarListSubEtapa();
		carregarComboBoxSubEtapa(etapaCombo.getSelectedItem().toString());
	}

	public void atualizarButton() {
		atualizarButton.setEnabled(true);
		salvarButton.setEnabled(false);
	}

	public void salvarButton() {
		salvarButton.setEnabled(true);
		atualizarButton.setEnabled(false);
	}

	public void criarListSubEtapa() {

		try {
			sql = "SELECT * FROM sub_etapas ORDER BY sub_etapa ASC";

			if (Banco.conexao()) {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.rs = Banco.st.executeQuery();

				subEtapas = Banco.rs;
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());
		}
	}

	public void carregarComboBoxSubEtapa(String cc_etapa) {
		String idEtapa = null;
		String a;
		int b = 1;
		// Esvaziando a combobox
		while (b < subEtapaCombo.getItemCount()) {
			a = subEtapaCombo.getItemAt(b).toString();
			subEtapaCombo.removeItem(a);
		}

		try {
			etapas.first();
			while (etapas.next()) {
				if (etapas.getString("etapa").equals(cc_etapa)) {
					idEtapa = etapas.getString("id_etapa");
				}
			}

			subEtapas.first();
			while (subEtapas.next()) {
				if (subEtapas.getString("id_etapa").equals(idEtapa)) {
					subEtapaCombo.addItem(subEtapas.getString("sub_etapa"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void criarListEtapa() {

		try {
			sql = " SELECT CONCAT (centro_custo.centrocusto,'@',etapas.etapa) AS etapa, id_etapa, centro_custo.id_centro_custo \r\n"
					+ " FROM etapas \r\n" + "	INNER JOIN centro_custo \r\n"
					+ "	ON centro_custo.id_centro_custo = etapas.id_cc \r\n" + "	ORDER BY etapa ASC \r\n";

			if (Banco.conexao()) {
				Banco.st = Banco.con.prepareStatement(sql);
				Banco.rs = Banco.st.executeQuery();

				etapas = Banco.rs;
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());
		}
	}

	public void carregarComboBoxEtapa() {
		String a;
		int b = 1;
		// Esvaziando a combobox
		while (b < etapaCombo.getItemCount()) {
			a = etapaCombo.getItemAt(b).toString();
			etapaCombo.removeItem(a);
		}

		try {
			etapas.first();
			while (etapas.next()) {
				etapaCombo.addItem(etapas.getString("etapa"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
