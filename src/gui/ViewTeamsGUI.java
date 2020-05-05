package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Category;
import domain.Event;
import domain.Team;

public class ViewTeamsGUI extends JFrame {
	private JPanel contentPane;
	private Category categoria;
	private JTextField textField;
	private Team selectedTeam;

	private ComboBoxModel<String> team = new DefaultComboBoxModel<String>();
	private List<Team> teams;

	public ViewTeamsGUI(Category c) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("viewTeams")); //$NON-NLS-1$ //$NON-NLS-2$
		categoria = c;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		BLFacade facade = LoginGUI.getBusinessLogic();

		JLabel label = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Teams")); //$NON-NLS-1$ //$NON-NLS-2$
		label.setBounds(26, 135, 46, 14);
		contentPane.add(label);

		JLabel lblCategory = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Category")); //$NON-NLS-1$ //$NON-NLS-2$
		lblCategory.setBounds(26, 77, 58, 14);
		contentPane.add(lblCategory);

		JLabel lblNewLabel = new JLabel(": " + categoria.getName()); // $NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setBounds(94, 77, 79, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setBounds(213, 92, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LastMatch")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_2.setBounds(213, 179, 79, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NextMatch")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_3.setBounds(213, 221, 79, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("League")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_4.setBounds(213, 135, 46, 14);
		contentPane.add(lblNewLabel_4);

		JLabel nameLabel = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
		nameLabel.setBounds(280, 92, 88, 14);
		contentPane.add(nameLabel);

		JLabel leagueLabel = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
		leagueLabel.setBounds(280, 135, 88, 14);
		contentPane.add(leagueLabel);

		JLabel lastMLabel = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
		lastMLabel.setBounds(288, 179, 88, 14);
		contentPane.add(lastMLabel);

		JLabel nextMLabel = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
		nextMLabel.setBounds(288, 221, 136, 14);
		contentPane.add(nextMLabel);

		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.setBounds(26, 252, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(70, 134, 103, 17);
		contentPane.add(comboBox);
		teams = facade.getTeams(categoria.getId());
		comboBox.setModel(team);
		if (teams != null) {
			for (Team t : teams) {
				((DefaultComboBoxModel<String>) team).addElement(t.getName());
			}
		}
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (team.getSize() > 0) {
					int selectedIndex = comboBox.getSelectedIndex();
					selectedTeam = teams.get(selectedIndex);
					nameLabel.setText(selectedTeam.getName());
					leagueLabel.setText(selectedTeam.getLeague());
					Event last = selectedTeam.getLastEvent();
					if (last != null)
						lastMLabel.setText(last.getDescription());
					else
						lastMLabel.setText("N/A");
					Event next = selectedTeam.getNextEvent();
					if (next != null)
						nextMLabel.setText(next.getDescription());
					else
						nextMLabel.setText("N/A");

				}
			}

		});

	}

	private void close() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}
