package gui;

import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.Category;

public class ViewTeamsGUI extends JFrame {
	private JPanel contentPane;
	private Category categoria;
	private JTextField textField;

	public ViewTeamsGUI(Category c) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("viewTeams")); //$NON-NLS-1$ //$NON-NLS-2$
		categoria = c;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Teams")); //$NON-NLS-1$ //$NON-NLS-2$
		label.setBounds(26, 135, 46, 14);
		contentPane.add(label);

	}

	private void close() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}
