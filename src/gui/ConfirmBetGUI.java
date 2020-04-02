package gui;

import java.awt.Color;
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

import businessLogic.BLFacade;
import domain.Bet;
import domain.Event;
import domain.Options;
import domain.Question;
import domain.Registro;

public class ConfirmBetGUI extends JFrame {

	private JPanel contentPane;
	private Options option;
	private Registro user;
	private Event event;
	private Question question;
	private JTextField textField;

	public ConfirmBetGUI(Event evento1, Question q, Registro user1, Options opcion1) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ConfirmBetGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
		event = evento1;
		question = q;
		user = user1;
		option = opcion1;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSelectedEvent = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("ConfirmBetGUI.lblSelectedEvent.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblSelectedEvent.setBounds(67, 39, 102, 14);
		contentPane.add(lblSelectedEvent);

		JLabel lblQuestion = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("ConfirmBetGUI.lblQuestion.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblQuestion.setBounds(67, 64, 89, 14);
		contentPane.add(lblQuestion);

		JLabel lblEvent = new JLabel("");
		lblEvent.setBounds(210, 39, 214, 14);
		contentPane.add(lblEvent);

		JLabel lblQ = new JLabel("");
		lblQ.setBounds(210, 64, 214, 14);
		contentPane.add(lblQ);

		JLabel lblOdd = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ConfirmBetGUI.lblOdd.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblOdd.setBounds(67, 89, 46, 14);
		contentPane.add(lblOdd);

		JLabel lblO = new JLabel("");
		lblO.setBounds(210, 89, 102, 14);
		contentPane.add(lblO);

		JLabel lblAvaiableMoney = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("UsuarioGUI.lblSaldoDiponible.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblAvaiableMoney.setBounds(67, 114, 102, 14);
		contentPane.add(lblAvaiableMoney);

		JLabel lblMoney = new JLabel("");
		lblMoney.setBounds(210, 114, 46, 14);
		contentPane.add(lblMoney);

		textField = new JTextField();
		textField.setBounds(210, 139, 46, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblMoneyToBet = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("ConfirmBetGUI.lblMoneyToBet.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblMoneyToBet.setBounds(67, 142, 102, 14);
		contentPane.add(lblMoneyToBet);

		JButton btnNewButton = new JButton(
				ResourceBundle.getBundle("Etiquetas").getString("UsuarioGUI.btnRealizarApuesta.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.setBounds(67, 175, 312, 57);
		contentPane.add(btnNewButton);

		lblEvent.setText(event.getDescription());
		lblQ.setText(question.getQuestion());
		lblO.setText(option.getOption() + "; " + String.valueOf(option.getOdds()));
		lblMoney.setText(String.valueOf(user.getSaldo()));

		JLabel lblMSGBOX = new JLabel("");
		lblMSGBOX.setHorizontalAlignment(SwingConstants.CENTER);
		lblMSGBOX.setBounds(143, 243, 169, 14);
		contentPane.add(lblMSGBOX);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				float desired = Float.valueOf(textField.getText());
				if (desired > user.getSaldo()) {
					lblMSGBOX.setForeground(Color.RED);
					lblMSGBOX.setText("ERROR: Not enough money");
				} else if (desired < 0.1f) {
					lblMSGBOX.setForeground(Color.RED);
					lblMSGBOX.setText("ERROR: Minimum bet 0.10");
				} else {
					Bet b = new Bet(option.getId(), user, desired);
					BLFacade db = LoginGUI.getBusinessLogic();
					db.newBet(b);
					close();
				}
			}

		});

	}

	private void close() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}
