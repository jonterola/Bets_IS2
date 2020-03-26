package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HistorialGUI extends JFrame {

	private JPanel contentPane;
	private String user;

	public HistorialGUI(String usr) {
		user = usr;
		init();
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHistorialDeApuestas = new JLabel("HISTORIAL DE APUESTAS");
		lblHistorialDeApuestas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHistorialDeApuestas.setBounds(124, 11, 180, 27);
		contentPane.add(lblHistorialDeApuestas);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				atras();
			}
		});
		btnAtras.setBounds(335, 11, 89, 23);
		contentPane.add(btnAtras);

		JList apuestas = new JList();
		apuestas.setBounds(10, 40, 180, 210);
		contentPane.add(apuestas);

	}

	private void atras() {
		this.setVisible(false);
	}
}
