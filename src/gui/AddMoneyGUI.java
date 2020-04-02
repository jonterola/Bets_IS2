package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Registro;
import libs.TextPrompt;

public class AddMoneyGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTarjeta;
	private JTextField textMes;
	private JTextField textAño;
	private JTextField textCVC;
	private JTextField textCantidad;
	private static Registro usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		usuario = new Registro("andergomez", "0266814", "79133379W", "a@asd.com", 19);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AddMoneyGUI frame = new AddMoneyGUI(usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddMoneyGUI(Registro user) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("AddMoneyGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
		usuario = user;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textTarjeta = new JTextField();
		TextPrompt placeholder1 = new TextPrompt("Numero de Tarjeta", textTarjeta);
		placeholder1.setText(ResourceBundle.getBundle("Etiquetas").getString("AddMoneyGUI.placeholder1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		placeholder1.changeAlpha(0.75f);
		placeholder1.changeStyle(Font.ITALIC);
		textTarjeta.setBounds(112, 36, 210, 20);
		contentPane.add(textTarjeta);
		textTarjeta.setColumns(10);
		textTarjeta.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e)

			{
				if (textTarjeta.getText().length() == 16)

					e.consume();
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});

		textMes = new JTextField();
		TextPrompt placeholder2 = new TextPrompt("Mes", textMes);
		placeholder2.setText(ResourceBundle.getBundle("Etiquetas").getString("AddMoneyGUI.placeholder2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		placeholder2.changeAlpha(0.75f);
		placeholder2.changeStyle(Font.ITALIC);
		textMes.setBounds(112, 78, 36, 20);
		contentPane.add(textMes);
		textMes.setColumns(10);
		textMes.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e)

			{
				if (textMes.getText().length() == 2)

					e.consume();
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});

		textAño = new JTextField();
		TextPrompt placeholder3 = new TextPrompt("Año", textAño);
		placeholder3.setText(ResourceBundle.getBundle("Etiquetas").getString("AddMoneyGUI.placeholder3.text")); //$NON-NLS-1$ //$NON-NLS-2$
		placeholder3.changeAlpha(0.75f);
		placeholder3.changeStyle(Font.ITALIC);
		textAño.setColumns(10);
		textAño.setBounds(158, 78, 36, 20);
		contentPane.add(textAño);
		textAño.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e)

			{
				if (textAño.getText().length() == 2)

					e.consume();
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});

		JLabel label = new JLabel("/");
		label.setBounds(151, 81, 12, 14);
		contentPane.add(label);

		textCVC = new JTextField();
		TextPrompt placeholder4 = new TextPrompt("CVC", textCVC);
		placeholder4.changeAlpha(0.75f);
		placeholder4.changeStyle(Font.ITALIC);
		textCVC.setBounds(220, 78, 36, 20);
		contentPane.add(textCVC);
		textCVC.setColumns(10);
		textCVC.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e)

			{
				if (textCVC.getText().length() == 3)

					e.consume();
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});

		textCantidad = new JTextField();
		TextPrompt placeholder5 = new TextPrompt("Cantidad a añadir", textCantidad);
		placeholder5.setText(ResourceBundle.getBundle("Etiquetas").getString("AddMoneyGUI.placeholder5.text")); //$NON-NLS-1$ //$NON-NLS-2$
		placeholder5.changeAlpha(0.75f);
		placeholder5.changeStyle(Font.ITALIC);
		textCantidad.setBounds(151, 158, 130, 20);
		contentPane.add(textCantidad);
		textCantidad.setColumns(10);

		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddBalance")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				long tarjeta;
				int año, anoC, mesC, mes, cvc;
				float cantidad;
				try {
					tarjeta = Long.parseLong(textTarjeta.getText());
					año = Integer.parseInt(textAño.getText());
					mes = Integer.parseInt(textMes.getText());
					cvc = Integer.parseInt(textCVC.getText());
					cantidad = Float.parseFloat(textCantidad.getText());

				} catch (Exception e) {
					System.out.println("ERROR: Intoduce todos los datos");
					return;
				}

				if (textTarjeta.getText().length() != 16) {
					System.out.println("ERROR: Tarjeta no valida");
					return;
				} else if (año < 20 || (año == 20 && mes < 3) || mes < 1 || mes > 12) {
					System.out.println("ERROR: Fecha no valida");
					return;
				} else if (textCVC.getText().length() != 3) {
					System.out.println("ERROR: CVC no valido");
					return;
				}
				usuario.setSaldo(usuario.getSaldo() + cantidad);
				System.out.println(usuario.getSaldo());
				BLFacade facade = LoginGUI.getBusinessLogic();
				facade.updateUser(usuario);
				closeWindow();
			}
		});
		btnNewButton.setBounds(112, 201, 210, 23);
		contentPane.add(btnNewButton);
	}

	private void closeWindow() {
		this.setVisible(false);
	}
}
