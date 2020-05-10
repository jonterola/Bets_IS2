package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Registro;

public class BanGUI extends JFrame {

	private JPanel contentPane;
	private JTable tableUnblock;
	private JTable tableBlock;

	private List<Registro> users;
	private DefaultTableModel modelBlock;
	private DefaultTableModel modelUnBlock;
	BLFacade facade = LoginGUI.getBusinessLogic();

	public BanGUI() {
		setResizable(false);
		setTitle("Block/Unblock Users");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 735, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tableUnblock = new JTable();
		tableUnblock.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Nombre" }));
		tableUnblock.setBounds(67, 48, 203, 310);
		contentPane.add(tableUnblock);

		tableBlock = new JTable();
		tableBlock.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Nombre" }));

		tableBlock.setBounds(450, 48, 210, 310);
		contentPane.add(tableBlock);

		modelBlock = (DefaultTableModel) tableBlock.getModel();
		modelUnBlock = (DefaultTableModel) tableUnblock.getModel();

		updateUI();

		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableUnblock.getSelectedRow() != -1) {
					facade.statusUser((String) tableUnblock.getValueAt(tableUnblock.getSelectedRow(), 0), true);
					updateUI();
				}
			}
		});
		button.setBounds(316, 138, 89, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableBlock.getSelectedRow() != -1) {
					facade.statusUser((String) tableBlock.getValueAt(tableBlock.getSelectedRow(), 0), false);
					updateUI();
				}
			}
		});
		button_1.setBounds(316, 235, 89, 23);
		contentPane.add(button_1);

		JLabel lblUsers = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Users"));
		lblUsers.setBounds(147, 23, 46, 14);
		contentPane.add(lblUsers);

		JLabel lblBlockedUsers = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BlockedUsers"));
		lblBlockedUsers.setBounds(513, 23, 89, 14);
		contentPane.add(lblBlockedUsers);
	}

	private void updateUI() {
		users = facade.getAllUsers();
		modelUnBlock.setRowCount(0);
		modelBlock.setRowCount(0);
		for (int i = 0; i < users.size(); i++) {
			Registro us = users.get(i);
			if (!us.isBlocked()) {
				modelUnBlock.addRow(new Object[] { us.getDni(), us.getNick() });
			} else {
				modelBlock.addRow(new Object[] { us.getDni(), us.getNick() });
			}
		}

	}
}
