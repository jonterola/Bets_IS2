package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bet;
import domain.Registro;

public class HistorialGUI extends JFrame {

	private JPanel contentPane;
	private Registro user;

	public HistorialGUI(Registro user2) {
		user = user2;
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

		JList<String> apuestas = new JList<String>();
		apuestas.setBounds(10, 40, 180, 210);
		contentPane.add(apuestas);
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		BLFacade facade = LoginGUI.getBusinessLogic();

		List<Bet> bets = facade.getBet(user.getDni());
		for (Bet b : bets) {
			// modelo.addElement(facade.getQuestion(facade.getOption(b.getOptionID()).getQuestionID()).getQuestion());
			modelo.addElement(facade.getOption(b.getOptionID()).getOption());
		}
		apuestas.setModel(modelo);

		JLabel lblCantidadApostada = new JLabel("Cantidad apostada");
		lblCantidadApostada.setBounds(200, 49, 147, 14);
		contentPane.add(lblCantidadApostada);

		JLabel lblF = new JLabel("Fecha");
		lblF.setBounds(200, 89, 89, 14);
		contentPane.add(lblF);

		JLabel lblApuestaRealizada = new JLabel("Apuesta realizada");
		lblApuestaRealizada.setBounds(200, 127, 147, 14);
		contentPane.add(lblApuestaRealizada);

		JLabel lblEstaActiva = new JLabel("Esta activa?");
		lblEstaActiva.setBounds(200, 172, 147, 14);
		contentPane.add(lblEstaActiva);

		JLabel Capostada = new JLabel("");
		Capostada.setBounds(345, 49, 67, 14);
		contentPane.add(Capostada);

		JLabel Fecha = new JLabel("");
		Fecha.setBounds(345, 89, 67, 14);
		contentPane.add(Fecha);

		JLabel ARealizada = new JLabel("");
		ARealizada.setBounds(345, 127, 67, 14);
		contentPane.add(ARealizada);

		JLabel Activa = new JLabel("");
		Activa.setBounds(345, 172, 67, 14);
		contentPane.add(Activa);

		apuestas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
	}

	private void atras() {
		this.setVisible(false);
	}
}
