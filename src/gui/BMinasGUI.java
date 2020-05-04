package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.Registro;

public class BMinasGUI extends JFrame {

	private JPanel contentPane;
	private Registro user;
	private double apuesta;
	int random = getRandomNumberInRange(1, 9);
	int cont = 1;

	public BMinasGUI(Registro usr, double apuesta) {
		user = usr;
		this.apuesta = apuesta;
		try {
			Init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Init() {
		setBounds(100, 100, 299, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button_8 = new JButton();
		button_8.setBounds(109, 339, 70, 70);
		contentPane.add(button_8);
		button_8.setIcon(new ImageIcon("pngocean.com.png"));
		JButton button_9 = new JButton();
		button_9.setBounds(189, 339, 70, 70);
		contentPane.add(button_9);
		button_9.setIcon(new ImageIcon("pngocean.com.png"));
		JButton button_1 = new JButton();

		button_1.setBounds(29, 177, 70, 70);
		contentPane.add(button_1);
		button_1.setIcon(new ImageIcon("pngocean.com.png"));
		JButton button_2 = new JButton();
		button_2.setBounds(109, 177, 70, 70);
		contentPane.add(button_2);
		button_2.setIcon(new ImageIcon("pngocean.com.png"));
		JButton button_3 = new JButton();
		button_3.setBounds(189, 177, 70, 70);
		contentPane.add(button_3);
		button_3.setIcon(new ImageIcon("pngocean.com.png"));
		JButton button_4 = new JButton();
		button_4.setBounds(29, 258, 70, 70);
		contentPane.add(button_4);
		button_4.setIcon(new ImageIcon("pngocean.com.png"));
		JButton button_5 = new JButton();
		button_5.setBounds(109, 258, 70, 70);
		contentPane.add(button_5);
		button_5.setIcon(new ImageIcon("pngocean.com.png"));
		JButton button_6 = new JButton();
		button_6.setBounds(189, 258, 70, 70);
		contentPane.add(button_6);
		button_6.setIcon(new ImageIcon("pngocean.com.png"));
		JButton button_7 = new JButton();
		button_7.setBounds(29, 339, 70, 70);
		contentPane.add(button_7);
		button_7.setIcon(new ImageIcon("pngocean.com.png"));
		JLabel lblBuscaminas = new JLabel("BUSCAMINAS");
		lblBuscaminas.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBuscaminas.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscaminas.setBounds(10, 11, 249, 25);
		contentPane.add(lblBuscaminas);

		JButton btnHelp = new JButton("help");
		btnHelp.setBounds(10, 15, 70, 23);
		contentPane.add(btnHelp);

		JLabel ganado = new JLabel(Double.toString(apuesta));
		ganado.setBounds(205, 19, 68, 14);
		contentPane.add(ganado);

		JButton boom = new JButton("");
		boom.setBounds(90, 58, 108, 108);
		contentPane.add(boom);

		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont++;
				if (random == 1) {
					boom.setIcon(new ImageIcon("boom.png"));
					button_1.setEnabled(false);
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					button_5.setEnabled(false);
					button_6.setEnabled(false);
					button_7.setEnabled(false);
					button_8.setEnabled(false);
					button_9.setEnabled(false);
				} else if (cont == 8) {
					System.out.println("felicitaciones");
				} else {
					ganado.setText(Double.toString(apuesta));
				}
				apuesta = apuesta * (0.1 * cont + 1);
				contentPane.add(ganado);

				button_1.setEnabled(false);
			}
		});
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont++;
				if (random == 2) {
					boom.setIcon(new ImageIcon("boom.png"));
					button_1.setEnabled(false);
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					button_5.setEnabled(false);
					button_6.setEnabled(false);
					button_7.setEnabled(false);
					button_8.setEnabled(false);
					button_9.setEnabled(false);
				} else if (cont == 8) {
					System.out.println("felicitaciones");
				} else {
					ganado.setText(Double.toString(apuesta));
				}
				apuesta = apuesta * (0.1 * cont + 1);

				button_2.setEnabled(false);

			}
		});
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont++;
				if (random == 3) {
					boom.setIcon(new ImageIcon("boom.png"));
					button_1.setEnabled(false);
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					button_5.setEnabled(false);
					button_6.setEnabled(false);
					button_7.setEnabled(false);
					button_8.setEnabled(false);
					button_9.setEnabled(false);
				} else if (cont == 8) {
					System.out.println("felicitaciones");
				} else {
					ganado.setText(Double.toString(apuesta));
				}
				apuesta = apuesta * (0.1 * cont + 1);

				button_3.setEnabled(false);
			}
		});
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont++;
				if (random == 4) {
					boom.setIcon(new ImageIcon("boom.png"));
					button_1.setEnabled(false);
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					button_5.setEnabled(false);
					button_6.setEnabled(false);
					button_7.setEnabled(false);
					button_8.setEnabled(false);
					button_9.setEnabled(false);
				} else if (cont == 8) {
					System.out.println("felicitaciones");
				} else {
					ganado.setText(Double.toString(apuesta));
				}
				apuesta = apuesta * (0.1 * cont + 1);

				button_4.setEnabled(false);
			}
		});
		button_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont++;
				if (random == 5) {
					boom.setIcon(new ImageIcon("boom.png"));
					button_1.setEnabled(false);
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					button_5.setEnabled(false);
					button_6.setEnabled(false);
					button_7.setEnabled(false);
					button_8.setEnabled(false);
					button_9.setEnabled(false);
				} else if (cont == 8) {
					System.out.println("felicitaciones");
				} else {
					ganado.setText(Double.toString(apuesta));
				}
				apuesta = apuesta * (0.1 * cont + 1);

				button_5.setEnabled(false);
			}
		});
		button_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont++;
				if (random == 6) {
					boom.setIcon(new ImageIcon("boom.png"));
					button_1.setEnabled(false);
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					button_5.setEnabled(false);
					button_6.setEnabled(false);
					button_7.setEnabled(false);
					button_8.setEnabled(false);
					button_9.setEnabled(false);
				} else if (cont == 8) {
					System.out.println("felicitaciones");
				} else {
					ganado.setText(Double.toString(apuesta));
				}
				button_6.setEnabled(false);
				apuesta = apuesta * (0.1 * cont + 1);
			}
		});
		button_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont++;
				if (random == 7) {
					boom.setIcon(new ImageIcon("boom.png"));
					button_1.setEnabled(false);
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					button_5.setEnabled(false);
					button_6.setEnabled(false);
					button_7.setEnabled(false);
					button_8.setEnabled(false);
					button_9.setEnabled(false);
				} else if (cont == 8) {
					System.out.println("felicitaciones");
				} else {
					ganado.setText(Double.toString(apuesta));
				}
				apuesta = apuesta * (0.1 * cont + 1);

				button_7.setEnabled(false);
			}
		});
		button_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont++;
				if (random == 8) {
					boom.setIcon(new ImageIcon("boom.png"));
					button_1.setEnabled(false);
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					button_5.setEnabled(false);
					button_6.setEnabled(false);
					button_7.setEnabled(false);
					button_8.setEnabled(false);
					button_9.setEnabled(false);
				} else if (cont == 8) {
					System.out.println("felicitaciones");
				} else {
					ganado.setText(Double.toString(apuesta));
				}
				apuesta = apuesta * (0.1 * cont + 1);

				button_8.setEnabled(false);

			}
		});
		button_9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cont++;
				if (random == 9) {
					boom.setIcon(new ImageIcon("boom.png"));
					button_1.setEnabled(false);
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					button_5.setEnabled(false);
					button_6.setEnabled(false);
					button_7.setEnabled(false);
					button_8.setEnabled(false);
					button_9.setEnabled(false);
				} else if (cont == 8) {
					System.out.println("felicitaciones");
				} else {
					ganado.setText(Double.toString(apuesta));
				}
				apuesta = apuesta * (0.1 * cont + 1);

				button_9.setEnabled(false);
			}
		});

	}

	private static int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
