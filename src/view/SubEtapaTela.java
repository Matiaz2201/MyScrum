package view;

//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import com.myscrum.banco.BD;
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
	Redimensionar rsize = new Redimensionar();
	JPanel panel = new JPanel();

	SubEtapaDAO metodos = new SubEtapaDAO();
	BD bd = new BD();
	String sql;

	public SubEtapaTela() {
		// construct preComponents
		String[] subEtapaComboItems = { "Selecione" };

		// construct components
		subEtapaCombo = new JComboBox<String>(subEtapaComboItems);
		etapaCombo = new JComboBox<String>(subEtapaComboItems);
		etapaCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] etapa = {""};
				
				etapa = etapaCombo.getSelectedItem().toString().split("_");
				
				carregarComboBoxSubEtapa(etapa[0], etapa[1]);
		
			}
		});
		subEtapaText = new JTextField(5);
		salvarButton = new JButton("Salvar");
		atualizarButton = new JButton("Atualizar");
		atualizarButton.setEnabled(false);
		subEtapaLabel = new JLabel("Sub etapa:");
		cadastradoLabel = new JLabel("J� cadastrado:");

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
						String[] etapa = {""};
						
						etapa = etapaCombo.getSelectedItem().toString().split("_");
						
						SubEtapa subetapa = new SubEtapa(subEtapaText.getText(), etapa[0], etapa[1]);

						metodos.cadastrar(subetapa);
						subEtapaText.setText("");
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
						SubEtapa subetapa = new SubEtapa(subEtapaCombo.getSelectedItem().toString(),
								subEtapaText.getText());

						metodos.atualizar(subetapa);
						subEtapaText.setText("");
					}
				}
			}
		});
	
		carregarComboBoxEtapa();
	}

	public void atualizarButton() {
		atualizarButton.setEnabled(true);
		salvarButton.setEnabled(false);
	}

	public void salvarButton() {
		salvarButton.setEnabled(true);
		atualizarButton.setEnabled(false);
	}

	public void carregarComboBoxSubEtapa(String etapa, String cc) {
		String subEtapa = null;
		String a;
		int b = 1;
		// Esvaziando a combobox
		while (b < subEtapaCombo.getItemCount()) {
			a = subEtapaCombo.getItemAt(b).toString();
			subEtapaCombo.removeItem(a);
		}

		// Preenchendo a combo box
		try {
			sql = "SELECT sub_etapas.sub_etapa FROM sub_etapas \r\n" +
					"INNER JOIN etapas \r\n" +
					"ON sub_etapas.id_etapa = etapas.id_etapa \r\n" +
					"INNER JOIN centro_custo \r\n" +
					"ON etapas.id_cc = centro_custo.id_centro_custo \r\n" +
					"WHERE sub_etapas.id_etapa = (SELECT etapas.id_etapa FROM etapas WHERE etapa = '" + etapa + "'  AND \r\n" +
					"id_cc = (SELECT id_centro_custo FROM centro_custo WHERE centrocusto = '" + cc + "'))";
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			while (bd.rs.next()) {
				subEtapa = bd.rs.getString("sub_etapa");
				subEtapaCombo.addItem(subEtapa);
			}
			bd.close();
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());
		}
	}

	public void carregarComboBoxEtapa() {
		String etapa = null;
		String a;
		int b = 1;
		// Esvaziando a combobox
		while (b < etapaCombo.getItemCount()) {
			a = etapaCombo.getItemAt(b).toString();
			etapaCombo.removeItem(a);
		}

		// Preenchendo a combo box
		try {
			sql = "SELECT CONCAT (etapa,'_',(SELECT centrocusto FROM centro_custo WHERE id_centro_custo = etapas.id_cc)) AS etapa FROM etapas";
	
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			while (bd.rs.next()) {
				etapa = bd.rs.getString("etapa");
				etapaCombo.addItem(etapa);
			}
			bd.close();
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro.toString());
		}
	}
}
