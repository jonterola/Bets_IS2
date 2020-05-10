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

public class FelicidadesGUI extends JFrame {

	private JPanel contentPane;
	private Registro user;
	private float ganancias;

	public FelicidadesGUI(Registro usr, float apuesta) {
		user = usr;
		ganancias = apuesta;
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

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("congratulations"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 29));
		lblNewLabel.setBounds(10, 11, 414, 58);
		contentPane.add(lblNewLabel);

		JLabel lblYouEarned = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("youearned"));
		lblYouEarned.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		lblYouEarned.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouEarned.setBounds(10, 80, 414, 29);
		contentPane.add(lblYouEarned);

		JLabel money = new JLabel(Float.toString(ganancias));
		money.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		money.setHorizontalAlignment(SwingConstants.RIGHT);
		money.setBounds(98, 120, 127, 40);
		contentPane.add(money);

		JLabel label_1 = new JLabel("€");
		label_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		label_1.setBounds(235, 121, 46, 38);
		contentPane.add(label_1);

		JButton btnAgain = new JButton(ResourceBundle.getBundle("Etiquetas").getString("playagain"));
		btnAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				again();
			}
		});
		btnAgain.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		btnAgain.setBounds(10, 171, 195, 79);
		contentPane.add(btnAgain);

		JButton back = new JButton("GO BACK");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		back.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		back.setBounds(229, 171, 195, 79);
		contentPane.add(back);
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
