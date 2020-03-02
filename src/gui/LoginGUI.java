package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import businessLogic.BLFacade;

public class LoginGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tMail;
	private JPasswordField tPwd;
	protected JLabel jLabelSelectOption;

	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel;

	private JLabel txtpnLogin;
	private JLabel txtpnCorreoElectronico;
	private JLabel txtpnContrasea;

	private JLabel errors;
	private JButton btnLogin;
	private JButton reg;

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {

		this.setBounds(100, 100, 450, 382);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
//		getPanel();
		txtpnLogin = new JLabel();
		txtpnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		txtpnLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		txtpnLogin.setBounds(23, 11, 200, 42);
		this.getContentPane().add(txtpnLogin);

		txtpnCorreoElectronico = new JLabel();
		txtpnCorreoElectronico.setText(ResourceBundle.getBundle("Etiquetas").getString("Mail"));
		txtpnCorreoElectronico.setBounds(23, 76, 127, 20);
		this.getContentPane().add(txtpnCorreoElectronico);

		txtpnContrasea = new JLabel();
		txtpnContrasea.setText(ResourceBundle.getBundle("Etiquetas").getString("Pwd"));
		txtpnContrasea.setBounds(23, 123, 127, 20);
		this.getContentPane().add(txtpnContrasea);

		errors = new JLabel();
		errors.setBounds(23, 173, 384, 20);
		this.getContentPane().add(errors);

		tMail = new JTextField();
		tMail.setBounds(160, 76, 245, 20);
		this.getContentPane().add(tMail);
		tMail.setColumns(10);

		tPwd = new JPasswordField();
		tPwd.setBounds(160, 123, 245, 20);
		this.getContentPane().add(tPwd);

		btnLogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				errors.setText(" ");
				BLFacade facade = MainGUI.getBusinessLogic();
				if (facade.newLogin(tMail.getText(), tPwd.getText())) {
					loginSuccessful();
				} else {
					errors.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginEr"));
				}
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		btnLogin.setBounds(66, 214, 301, 58);
		this.getContentPane().add(btnLogin);

		reg = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Regist"));
		reg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				goToRegister();
			}
		});
		reg.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		reg.setBounds(280, 11, 127, 42);
		getContentPane().add(reg);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-21, 300, 479, 50);
		getContentPane().add(panel_1);

		JRadioButton radioButton = getRdbtnNewRadioButton();
		panel_1.add(radioButton);

		JRadioButton radioButton_1 = getRdbtnNewRadioButton_1();
		panel_1.add(radioButton_1);

		JRadioButton radioButton_2 = getRdbtnNewRadioButton_2();
		panel_1.add(radioButton_2);

	}

	private void loginSuccessful() {
		MainGUI m = new MainGUI();
		m.setVisible(true);
		this.setVisible(false);
	}

	private void goToRegister() {
		RegistroGUI r = new RegistroGUI();
		r.setVisible(true);

	}

	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton);

		}
		return rdbtnNewRadioButton;
	}

	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);

		}
		return rdbtnNewRadioButton_1;
	}

	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);

		}
		return rdbtnNewRadioButton_2;
	}

//	private JPanel getPanel() {
//		if (panel == null) {
//			panel = new JPanel();
//			panel.add(getRdbtnNewRadioButton_1());
//			panel.add(getRdbtnNewRadioButton_2());
//			panel.add(getRdbtnNewRadioButton());
//		}
//		return panel;
//	}

	private void redibujar() {
		txtpnLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		txtpnCorreoElectronico.setText(ResourceBundle.getBundle("Etiquetas").getString("Mail"));
		txtpnContrasea.setText(ResourceBundle.getBundle("Etiquetas").getString("Pwd"));
		btnLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		reg.setText(ResourceBundle.getBundle("Etiquetas").getString("Regist"));

		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
}
