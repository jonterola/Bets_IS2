package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.Registro;

public class BadLuckGUI extends JFrame {

	private JPanel contentPane;
	private Registro user;

	public BadLuckGUI(Registro usr) {
		user = usr;
		try {
			Init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public void Init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBadLuckThe = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("explosion"));
		lblBadLuckThe.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 17));
		lblBadLuckThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblBadLuckThe.setBounds(10, 26, 414, 49);
		contentPane.add(lblBadLuckThe);

		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("playagain"));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				again();
			}
		});

		btnNewButton.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 15));
		btnNewButton.setBounds(43, 128, 165, 78);
		contentPane.add(btnNewButton);

		JButton btnGoBack = new JButton("GO BACK");
		btnGoBack.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 15));
		btnGoBack.setBounds(237, 128, 165, 78);
		contentPane.add(btnGoBack);
		btnGoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				back();
			}
		});
	}

	private void again() {
		this.setVisible(false);
		BMinas1GUI b = new BMinas1GUI(user);
		b.setVisible(true);
	}

	private void back() {
		this.setVisible(false);

	}
}
