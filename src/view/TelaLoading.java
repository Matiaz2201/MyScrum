package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class TelaLoading extends JFrame {

	/**
	 * Desenvolvido por Abner Matias e Pedro Henrique
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLoading frame = new TelaLoading();
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
	public TelaLoading() {
		
		setUndecorated(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(Color.WHITE);
		progressBar.setForeground(new Color(41, 106, 158));
		progressBar.setIndeterminate(true);
		progressBar.setBounds(0, 100, 450, 15);
		contentPane.add(progressBar);
		
		JLabel fundoLabel = new JLabel("");
		fundoLabel.setVerticalAlignment(SwingConstants.TOP);
		fundoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fundoLabel.setIcon(new ImageIcon(TelaLoading.class.getResource("/com/myscrum/assets/bgProgressBar1.jpg")));
		fundoLabel.setBounds(0, 0, 450, 150);
		contentPane.add(fundoLabel);

		
	}
}
