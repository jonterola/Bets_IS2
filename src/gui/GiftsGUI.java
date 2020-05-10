package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Regalo;

public class GiftsGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	private List<Regalo> gifts;
	private DefaultTableModel model;
	BLFacade facade = LoginGUI.getBusinessLogic();

	public GiftsGUI() {
		setBounds(100, 100, 394, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "New column" }));
		table.setBounds(38, 59, 141, 265);

		contentPane.add(table);
		model = (DefaultTableModel) table.getModel();

		updateUI();

		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(219, 59, 60, 14);
		contentPane.add(lblCodigo);

		JLabel lblDinero = new JLabel("Dinero:");
		lblDinero.setBounds(219, 84, 60, 14);
		contentPane.add(lblDinero);

		JLabel lblUsos = new JLabel("Usos:");
		lblUsos.setBounds(219, 109, 60, 14);
		contentPane.add(lblUsos);

		JLabel labelCod = new JLabel("");
		labelCod.setBounds(289, 59, 46, 14);
		contentPane.add(labelCod);

		JLabel labelDinero = new JLabel("");
		labelDinero.setBounds(289, 84, 46, 14);
		contentPane.add(labelDinero);

		JLabel labelUsos = new JLabel("");
		labelUsos.setBounds(289, 109, 46, 14);
		contentPane.add(labelUsos);

		JLabel label = new JLabel("Codigo:");
		label.setBounds(219, 209, 60, 24);
		contentPane.add(label);

		JLabel label_1 = new JLabel("Dinero:");
		label_1.setBounds(219, 244, 60, 24);

		contentPane.add(label_1);
		textField = new JTextField();
		textField.setBounds(282, 209, 86, 24);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(282, 244, 86, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnBorrarRegalo = new JButton("Borrar Regalo");
		btnBorrarRegalo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					facade.removeGift((String) table.getValueAt(table.getSelectedRow(), 0));
					updateUI();
				}
			}
		});
		btnBorrarRegalo.setBounds(219, 146, 116, 23);
		contentPane.add(btnBorrarRegalo);

		JButton btnNewButton = new JButton("A\u00F1adir regalo");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String cod = textField.getText();
				Float cantidad = Float.valueOf(textField_1.getText());
				if (!(cod.isEmpty() || cantidad == 0)) {
					facade.addGift(cod, cantidad);
				}
				updateUI();

			}
		});
		btnNewButton.setBounds(219, 279, 120, 23);
		contentPane.add(btnNewButton);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
					Regalo r = gifts.get(table.getSelectedRow());
					labelDinero.setText(String.valueOf(r.getMoney()));
					labelCod.setText(r.getCod());
					labelUsos.setText(String.valueOf(r.getUses()));
					updateUI();
				}
			}

		});
	}

	private void updateUI() {
		gifts = facade.getGifts();
		model.setRowCount(0);
		for (int i = 0; i < gifts.size(); i++) {
			Regalo r = gifts.get(i);
			model.addRow(new Object[] { r.getCod() });
		}

	}
}
