package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Registro;

public class BoxGUI extends JFrame {

	private JPanel contentPane;
	private int[] prices;
	private Registro user;

	/**
	 * Create the frame.
	 */
	public BoxGUI(Registro usr) {
		user = usr;
		prices = new int[30];
		for (int i = 0; i < 30; i++) {
			if (i < 15) {
				prices[i] = 1;

			} else if (i < 20) {
				prices[i] = 2;
			} else if (i < 25) {
				prices[i] = 3;
			} else {
				prices[i] = 4;
			}
		}
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

		JLabel lblCongratulationsYouWon = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("boton"));
		lblCongratulationsYouWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongratulationsYouWon.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCongratulationsYouWon.setBounds(10, 11, 414, 42);
		contentPane.add(lblCongratulationsYouWon);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 113, 414, 53);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(142, 113, 153, 53);
		contentPane.add(btnNewButton);
		JButton btnNewButton2 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
		btnNewButton2.setBounds(142, 113, 153, 53);
		contentPane.add(btnNewButton2);

		JButton btnGoBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
		btnGoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
		btnGoBack.setBounds(329, 227, 95, 23);
		contentPane.add(btnGoBack);
		btnNewButton2.setVisible(false);
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton.setVisible(false);
				Date today = new Date();
				Date userd = user.getLastBox();
				if (today.getYear() == userd.getYear() && today.getMonth() == userd.getMonth()
						&& today.getDay() == userd.getDay()) {
					btnGoBack.setVisible(false);
					lblCongratulationsYouWon.setText(ResourceBundle.getBundle("Etiquetas").getString("played"));
					btnNewButton2.setVisible(true);
				} else {

					lblCongratulationsYouWon
							.setText(ResourceBundle.getBundle("Etiquetas").getString("congratulationsyou"));

					int random = getRandomNumberInRange(0, 29);
					BLFacade facade = LoginGUI.getBusinessLogic();
					switch (prices[random]) {
					case 1:
						facade.addMoney(user.getDni(), 1, true);
						lblNewLabel.setText("1€");
						break;
					case 2:
						lblNewLabel.setText("0€");
						user.setLastBox(today);
						facade.updateUser(user);
						break;
					case 3:
						lblNewLabel.setText("3€");
						facade.addMoney(user.getDni(), 3, true);
						break;
					case 4:
						lblCongratulationsYouWon.setText(ResourceBundle.getBundle("Etiquetas").getString("other"));

						btnNewButton.setVisible(true);
						break;
					}

				}
			}

		});
		btnNewButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				goBack();
			}
		});
	}

	private static int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	private void goBack() {
		this.setVisible(false);
	}

}
