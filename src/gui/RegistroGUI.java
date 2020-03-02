package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import businessLogic.BLFacade;

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
		this.setTitle("Registro");
		this.setBounds(100, 100, 450, 429);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel txtpnRegistro = new JLabel();
		txtpnRegistro.setBackground(SystemColor.control);
		txtpnRegistro.setFont(new Font("Times New Roman", Font.PLAIN, 29));
		txtpnRegistro.setText("REGISTRO");
		txtpnRegistro.setBounds(140, 11, 146, 40);
		this.getContentPane().add(txtpnRegistro);

		JLabel txtpnCorreoElectronico = new JLabel();
		txtpnCorreoElectronico.setBackground(SystemColor.control);
		txtpnCorreoElectronico.setText("Correo Electronico:");
		txtpnCorreoElectronico.setBounds(29, 68, 129, 20);
		this.getContentPane().add(txtpnCorreoElectronico);

		JLabel txtpnContrasea = new JLabel();
		txtpnContrasea.setBackground(SystemColor.control);
		txtpnContrasea.setText("Contrase\u00F1a:");
		txtpnContrasea.setBounds(29, 192, 129, 20);
		this.getContentPane().add(txtpnContrasea);

		JLabel txtpnNombreDeUsuario = new JLabel();
		txtpnNombreDeUsuario.setText("Nombre de usuario:");
		txtpnNombreDeUsuario.setBounds(29, 99, 129, 20);
		this.getContentPane().add(txtpnNombreDeUsuario);

		JLabel txtpnDni = new JLabel();
		txtpnDni.setText("DNI:");
		txtpnDni.setBounds(29, 161, 129, 20);
		this.getContentPane().add(txtpnDni);

		JLabel txtpnConfirmarContrasea = new JLabel();
		txtpnConfirmarContrasea.setText("Confirmar Contrase\u00F1a:");
		txtpnConfirmarContrasea.setBounds(29, 223, 129, 20);
		this.getContentPane().add(txtpnConfirmarContrasea);

		JLabel txtpnEdad = new JLabel();
		txtpnEdad.setText("Edad:");
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
		textPane.setBounds(29, 254, 359, 20);
		this.getContentPane().add(textPane);
		textPane.setForeground(Color.RED);

		JButton btnReg = new JButton("REGISTRARSE");
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
						textPane.setText("NO EXISTE EL CORREO");
						txtpnCorreoElectronico.setForeground(Color.RED);
					} else if (tfUser.getText().length() < 4) {
						textPane.setText("EL NOMBRE DE USUARIO DEBE TENER AL MENOS 4 CARACTERES");
						txtpnNombreDeUsuario.setForeground(Color.RED);

					} else if (age < 18) {
						textPane.setText("DEBES SER MAYOR DE EDAD");
						txtpnEdad.setForeground(Color.RED);
					} else if (!checkDNI(tfDni.getText())) {
						textPane.setText("EL DNI ES ERRONEO");
						txtpnDni.setForeground(Color.RED);

					} else if (!passwordField.getText().equals(passwordField_1.getText())) {
						textPane.setText("LAS CONTRASEÑAS NO COINCIDEN");
						txtpnContrasea.setForeground(Color.RED);
						txtpnConfirmarContrasea.setForeground(Color.RED);

					} else if (passwordField.getText().length() < 7) {
						textPane.setText("LA CONTRASEÑA DEBE TENER AL MENOS 7 CARACTERES");
						txtpnContrasea.setForeground(Color.RED);
						txtpnConfirmarContrasea.setForeground(Color.RED);

					} else {
						System.out.println("todo ok"); // meter en base de datos y cambiar de interfaz
						BLFacade facade = MainGUI.getBusinessLogic();
						facade.createUser(tfDni.getText(), tfUser.getText(), tfMail.getText(), passwordField.getText(),
								age);
					}
				} catch (Exception e) {
					e.printStackTrace();
					textPane.setText("LA EDAD ES SOLO UN NUMERO");
					txtpnEdad.setForeground(Color.RED);
				}
			}

		});

		btnReg.setFont(new Font("Times New Roman", Font.PLAIN, 34));
		btnReg.setBounds(29, 284, 377, 69);
		this.getContentPane().add(btnReg);

		passwordField = new JPasswordField();
		passwordField.setBounds(181, 192, 206, 20);
		this.getContentPane().add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(181, 223, 206, 20);
		this.getContentPane().add(passwordField_1);

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
}
