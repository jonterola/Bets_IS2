package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.Registro;

public class BMinas1GUI extends JFrame {

	private JPanel contentPane;
	private Registro user;
	private JTextField textField;

	public BMinas1GUI(Registro usr) {
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
		setBounds(100, 100, 450, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBoom = new JLabel("BOOM!");
		lblBoom.setFont(new Font("Goudy Stout", Font.PLAIN, 46));
		lblBoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoom.setBounds(10, 11, 414, 83);
		contentPane.add(lblBoom);

		JLabel lblApuesta = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("bet"));
		lblApuesta.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
		lblApuesta.setBounds(113, 104, 81, 20);
		contentPane.add(lblApuesta);

		textField = new JTextField();
		textField.setBounds(204, 105, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnPlay = new JButton(ResourceBundle.getBundle("Etiquetas").getString("play"));

		btnPlay.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		btnPlay.setBounds(129, 187, 161, 50);
		contentPane.add(btnPlay);

		JButton btnHelp = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
		btnHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnHelp.setBounds(170, 248, 89, 23);
		contentPane.add(btnHelp);

		JLabel lblKk = new JLabel("");
		lblKk.setHorizontalAlignment(SwingConstants.CENTER);
		lblKk.setBounds(10, 162, 414, 14);
		contentPane.add(lblKk);

		JLabel label = new JLabel(Float.toString(user.getSaldo())); // $NON-NLS-1$ //$NON-NLS-2$
		label.setBounds(194, 137, 46, 14);
		contentPane.add(label);
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				float apuesta;
				try {
					apuesta = Float.valueOf(textField.getText());
					if (apuesta > user.getSaldo()) {
						lblKk.setText(ResourceBundle.getBundle("Etiquetas").getString("saldo"));
					} else {
						user.setSaldo(user.getSaldo() - apuesta);
						jugar(apuesta);
					}

				} catch (Exception e) {
					lblKk.setText(ResourceBundle.getBundle("Etiquetas").getString("number"));
				}

			}
		});
	}

	private void jugar(float apuesta) {
		this.setVisible(false);
		BMinasGUI b = new BMinasGUI(user, apuesta);
		b.setVisible(true);
	}

	private void back() {
		this.setVisible(false);
	}
}
