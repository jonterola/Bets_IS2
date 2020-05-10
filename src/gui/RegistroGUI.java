package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import libs.TextPrompt;

public class RegistroGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfMail;
	private JTextField tfUser;
	private JTextField tfAge;
	private JTextField tfDni;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public RegistroGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Regist"));
		this.setBounds(100, 100, 452, 475);
		this.getContentPane().setLayout(null);

		JLabel txtpnRegistro = new JLabel();
		txtpnRegistro.setBackground(SystemColor.control);
		txtpnRegistro.setFont(new Font("Times New Roman", Font.PLAIN, 29));
		txtpnRegistro.setText(ResourceBundle.getBundle("Etiquetas").getString("Regist"));
		txtpnRegistro.setBounds(140, 11, 146, 40);
		this.getContentPane().add(txtpnRegistro);

		JLabel txtpnCorreoElectronico = new JLabel();
		txtpnCorreoElectronico.setBackground(SystemColor.control);
		txtpnCorreoElectronico.setText(ResourceBundle.getBundle("Etiquetas").getString("Mail"));
		txtpnCorreoElectronico.setBounds(29, 68, 129, 20);
		this.getContentPane().add(txtpnCorreoElectronico);

		JLabel txtpnContrasea = new JLabel();
		txtpnContrasea.setBackground(SystemColor.control);
		txtpnContrasea.setText(ResourceBundle.getBundle("Etiquetas").getString("Pwd"));
		txtpnContrasea.setBounds(29, 192, 129, 20);
		this.getContentPane().add(txtpnContrasea);

		JLabel txtpnNombreDeUsuario = new JLabel();
		txtpnNombreDeUsuario.setText(ResourceBundle.getBundle("Etiquetas").getString("User"));
		txtpnNombreDeUsuario.setBounds(29, 99, 129, 20);
		this.getContentPane().add(txtpnNombreDeUsuario);

		JLabel txtpnDni = new JLabel();
		txtpnDni.setText(ResourceBundle.getBundle("Etiquetas").getString("Dni"));
		txtpnDni.setBounds(29, 161, 129, 20);
		this.getContentPane().add(txtpnDni);

		JLabel txtpnConfirmarContrasea = new JLabel();
		txtpnConfirmarContrasea.setText(ResourceBundle.getBundle("Etiquetas").getString("Pwd2"));
		txtpnConfirmarContrasea.setBounds(29, 223, 129, 20);
		this.getContentPane().add(txtpnConfirmarContrasea);

		JLabel txtpnEdad = new JLabel();
		txtpnEdad.setText(ResourceBundle.getBundle("Etiquetas").getString("Age"));
		txtpnEdad.setBounds(29, 130, 129, 20);
		this.getContentPane().add(txtpnEdad);

		tfMail = new JTextField();
		tfMail.setBounds(181, 68, 206, 20);
		this.getContentPane().add(tfMail);
		tfMail.setColumns(10);

		tfUser = new JTextField();
		tfUser.setColumns(10);
		tfUser.setBounds(181, 99, 206, 20);
		this.getContentPane().add(tfUser);

		tfAge = new JTextField();
		tfAge.setColumns(10);
		tfAge.setBounds(181, 130, 206, 20);
		this.getContentPane().add(tfAge);

		tfDni = new JTextField();
		tfDni.setColumns(10);
		tfDni.setBounds(181, 161, 206, 20);
		this.getContentPane().add(tfDni);

		JLabel textPane = new JLabel();
		textPane.setBackground(SystemColor.control);
		textPane.setBounds(29, 326, 359, 20);
		this.getContentPane().add(textPane);
		textPane.setForeground(Color.RED);

		JButton btnReg = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Regist"));
		btnReg.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtpnEdad.setForeground(Color.BLACK);
				txtpnContrasea.setForeground(Color.BLACK);
				txtpnConfirmarContrasea.setForeground(Color.BLACK);
				txtpnNombreDeUsuario.setForeground(Color.BLACK);
				txtpnCorreoElectronico.setForeground(Color.BLACK);
				txtpnDni.setForeground(Color.BLACK);

				textPane.setText(" ");
				try {
					int age = Integer.parseInt(tfAge.getText());

					if (!tfMail.getText().contains("@") || !tfMail.getText().contains(".")) {
						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("Mailer"));
						txtpnCorreoElectronico.setForeground(Color.RED);
					} else if (tfMail.getText().contains("@sinkingsoft.com")) {
						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("Mailer3"));
						txtpnCorreoElectronico.setForeground(Color.RED);

					} else if (tfUser.getText().length() < 4) {

						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("Userer"));
						txtpnNombreDeUsuario.setForeground(Color.RED);

					} else if (age < 18) {
						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("Ageer"));
						txtpnEdad.setForeground(Color.RED);
					} else if (!checkDNI(tfDni.getText())) {
						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("Dnier"));
						txtpnDni.setForeground(Color.RED);

					} else if (!passwordField.getText().equals(passwordField_1.getText())) {
						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("Pwder1"));
						txtpnContrasea.setForeground(Color.RED);
						txtpnConfirmarContrasea.setForeground(Color.RED);

					} else if (passwordField.getText().length() < 7) {
						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("Pwder2"));
						txtpnContrasea.setForeground(Color.RED);
						txtpnConfirmarContrasea.setForeground(Color.RED);

					} else {
						BLFacade facade = LoginGUI.getBusinessLogic();
						int i = facade.createUser(tfDni.getText(), tfUser.getText(), tfMail.getText(),
								passwordField.getText(), age, textField.getText());
						if (i == 1) {
							textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("Mailer2"));
							txtpnCorreoElectronico.setForeground(Color.RED);
						} else if (i == 2) {
							textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("Userer2"));
							txtpnNombreDeUsuario.setForeground(Color.RED);
						} else if (i == 3) {
							textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("Dnier2"));
							txtpnDni.setForeground(Color.RED);
						} else {
							RegisterSuccessful();
						}
					}
				} catch (Exception e) {
					textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("Ageer2"));
					txtpnEdad.setForeground(Color.RED);
				}
			}

		});

		btnReg.setFont(new Font("Times New Roman", Font.PLAIN, 34));
		btnReg.setBounds(29, 356, 377, 69);
		this.getContentPane().add(btnReg);

		passwordField = new JPasswordField();
		passwordField.setBounds(181, 192, 206, 20);
		this.getContentPane().add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(181, 223, 206, 20);
		this.getContentPane().add(passwordField_1);

		JLabel lblPromotionalCode = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("RegistroGUI.lblPromotionalCode.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblPromotionalCode.setBounds(29, 276, 129, 14);
		getContentPane().add(lblPromotionalCode);

		textField = new JTextField();
		TextPrompt placeholder2 = new TextPrompt("No es obligatorio", textField);
		placeholder2.setText(ResourceBundle.getBundle("Etiquetas").getString("NotObligatory")); //$NON-NLS-1$ //$NON-NLS-2$
		placeholder2.changeAlpha(0.75f);
		placeholder2.changeStyle(Font.ITALIC);
		textField.setBounds(181, 273, 206, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

	}

	private boolean checkDNI(String DNI) {
		if (DNI.length() != 9)
			return false;
		if (!Character.isLetter(DNI.charAt(8)))
			return false;
		try {
			Integer.parseInt(DNI.substring(0, 8));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void RegisterSuccessful() {

		this.setVisible(false);
	}

}
