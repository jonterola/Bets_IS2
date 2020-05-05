package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Registro;

public class Usuario1GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginGUI log;
	private Registro user;

	public Usuario1GUI(LoginGUI lo, Registro usr) {
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

		JButton btnLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Usuario1GUI.btnLogout.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		btnLogout.setBounds(320, 11, 104, 35);
		contentPane.add(btnLogout);

		JButton btnApuesta = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ViewEvents")); //$NON-NLS-1$ //$NON-NLS-2$
		btnApuesta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UsuarioGUI u = new UsuarioGUI(user);
				u.setVisible(true);
			}
		});
		btnApuesta.setBounds(10, 59, 414, 35);
		contentPane.add(btnApuesta);

		JButton btnHistorial = new JButton(
				ResourceBundle.getBundle("Etiquetas").getString("Usuario1GUI.btnHistorial.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnHistorial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HistorialGUI h = new HistorialGUI(user);
				h.setVisible(true);
			}
		});
		btnHistorial.setBounds(10, 107, 414, 35);
		contentPane.add(btnHistorial);

		JButton btnRMonedero = new JButton(
				ResourceBundle.getBundle("Etiquetas").getString("Usuario1GUI.btnRMonedero.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnRMonedero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddMoneyGUI m = new AddMoneyGUI(user);
				m.setVisible(true);
			}
		});
		btnRMonedero.setBounds(10, 153, 414, 35);
		contentPane.add(btnRMonedero);

		JButton btnGames = new JButton("Games"); //$NON-NLS-1$ //$NON-NLS-2$
		btnGames.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GamesGUI g = new GamesGUI(user);
				g.setVisible(true);
			}
		});
		btnGames.setBounds(10, 199, 414, 35);
		contentPane.add(btnGames);
	}

	private void logout() {
		this.setVisible(false);
		log.setVisible(true);
	}
}
