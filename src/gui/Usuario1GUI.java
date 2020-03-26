package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Usuario1GUI extends JFrame {

	private JPanel contentPane;
	private LoginGUI log;
	private String user; // mail

	public Usuario1GUI(LoginGUI lo, String usr) {
		log = lo;
		user = usr;
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jbInit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		btnLogout.setBounds(320, 11, 104, 35);
		contentPane.add(btnLogout);

		JButton btnApuesta = new JButton("Realizar apuesta");
		btnApuesta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UsuarioGUI u = new UsuarioGUI();
				u.setVisible(true);
			}
		});
		btnApuesta.setBounds(10, 59, 414, 35);
		contentPane.add(btnApuesta);

		JButton btnHistorial = new JButton("Historial de apuestas");
		btnHistorial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HistorialGUI h = new HistorialGUI(user);
				h.setVisible(true);
			}
		});
		btnHistorial.setBounds(10, 107, 414, 35);
		contentPane.add(btnHistorial);

		JButton btnRMonedero = new JButton("Recargar Monedero");
		btnRMonedero.setBounds(10, 153, 414, 35);
		contentPane.add(btnRMonedero);
	}

	private void logout() {
		this.setVisible(false);
		log.setVisible(true);
	}
}
