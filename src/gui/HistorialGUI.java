package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bet;
import domain.Options;
import domain.Question;
import domain.Registro;

public class HistorialGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

		JLabel lblHistorialDeApuestas = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("HistorialGUI.lblHistorialDeApuestas.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblHistorialDeApuestas.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialDeApuestas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHistorialDeApuestas.setBounds(124, 11, 180, 27);
		contentPane.add(lblHistorialDeApuestas);

		JButton btnAtras = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back")); //$NON-NLS-1$ //$NON-NLS-2$
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
			modelo.addElement(
					facade.getQuestion(facade.getOption(b.getOptionID()).getQuestionID()).getEvent().getDescription());
		}
		apuestas.setModel(modelo);

		JLabel lblCantidadApostada = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("HistorialGUI.lblCantidadApostada.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblCantidadApostada.setBounds(200, 49, 147, 14);
		contentPane.add(lblCantidadApostada);

		JLabel lblF = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("HistorialGUI.lblF.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblF.setBounds(200, 89, 89, 14);
		contentPane.add(lblF);

		JLabel lblApuestaRealizada = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("HistorialGUI.lblApuestaRealizada.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblApuestaRealizada.setBounds(200, 127, 147, 14);
		contentPane.add(lblApuestaRealizada);

		JLabel lblEstaActiva = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("HistorialGUI.lblEstaActiva.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblEstaActiva.setBounds(200, 172, 147, 14);
		contentPane.add(lblEstaActiva);

		JLabel Capostada = new JLabel("");
		Capostada.setBounds(345, 49, 67, 14);
		contentPane.add(Capostada);

		JLabel Fecha = new JLabel("");
		Fecha.setBounds(333, 89, 79, 14);
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
				int i = apuestas.getSelectedIndex();
				Options o = facade.getOption(bets.get(i).getOptionID());
				Question q = facade.getQuestion(facade.getOption(bets.get(i).getOptionID()).getQuestionID());
				Capostada.setText(String.valueOf(bets.get(i).getCantidadApostada()));
				Fecha.setText(q.getEvent().getEventDate().toString());
				ARealizada.setText(o.getOption());
				if (q.isFinished()) {
					Activa.setText("No");
				} else {
					Activa.setText("Si");
				}

			}
		});
	}

	private void atras() {
		this.setVisible(false);
	}
}
