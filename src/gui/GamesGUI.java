package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.Registro;

public class GamesGUI extends JFrame {
	private JPanel contentPane;
	private Registro user;

	/**
	 * Create the frame.
	 */
	public GamesGUI(Registro usr) {
		user = usr;
		try {
			Init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGames = new JLabel("GAMES");
		lblGames.setHorizontalAlignment(SwingConstants.CENTER);
		lblGames.setVerticalAlignment(SwingConstants.CENTER);
		lblGames.setFont(new Font("Dialog", Font.PLAIN, 35));
		lblGames.setBounds(10, 0, 414, 53);
		contentPane.add(lblGames);

		JButton btnBox = new JButton();
		btnBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				box();
			}
		});

		btnBox.setIcon(new ImageIcon("330x192.png"));
		btnBox.setBounds(20, 75, 193, 175);
		contentPane.add(btnBox);

		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				minas();
			}
		});
		button.setIcon(new ImageIcon("mina.png"));
		button.setBounds(231, 75, 193, 175);
		contentPane.add(button);
	}

	public void minas() {
		this.setVisible(false);
		BMinas1GUI b = new BMinas1GUI(user);
		b.setVisible(true);
	}

	public void box() {
		this.setVisible(false);
		BoxGUI b = new BoxGUI(user);
		b.setVisible(true);
	}
}
