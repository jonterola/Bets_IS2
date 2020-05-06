package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AskRegisterGUI extends JFrame {

	private JPanel contentPane;

	public AskRegisterGUI(UsuarioGUI u) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("AskRegisterGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 350, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel textLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AskRegister"));
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setBounds(10, 22, 314, 36);
		contentPane.add(textLabel);

		JButton yesButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Yes")); //$NON-NLS-1$ //$NON-NLS-2$
		yesButton.setBounds(58, 115, 66, 23);
		contentPane.add(yesButton);
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AskRegisterGUI.this.setVisible(false);
				u.setVisible(false);
				LoginGUI log = new LoginGUI();
				log.setVisible(true);
			}

		});

		JButton noButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Later")); //$NON-NLS-1$ //$NON-NLS-2$
		noButton.setBounds(198, 115, 66, 23);
		contentPane.add(noButton);
		noButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AskRegisterGUI.this.setVisible(false);
			}

		});

		JLabel textLabel2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AskRegister2")); //$NON-NLS-1$ //$NON-NLS-2$
		textLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel2.setBounds(10, 69, 314, 23);
		contentPane.add(textLabel2);

	}

	private void close() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}