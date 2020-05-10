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
import domain.Transaction;

public class FinancesGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public FinancesGUI() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 752, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDineroIng = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DineroIngresado"));
		lblDineroIng.setBounds(39, 34, 129, 14);
		contentPane.add(lblDineroIng);

		JLabel lblDineroTotal = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DineroTotal"));
		lblDineroTotal.setBounds(39, 59, 109, 14);
		contentPane.add(lblDineroTotal);

		JLabel lblBalanceTotal = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BalanceTotal"));
		lblBalanceTotal.setBounds(39, 84, 98, 14);
		contentPane.add(lblBalanceTotal);

		JLabel labelDI = new JLabel("");
		labelDI.setBounds(148, 34, 46, 14);
		contentPane.add(labelDI);

		JLabel labelDT = new JLabel("");
		labelDT.setBounds(148, 59, 46, 14);
		contentPane.add(labelDT);

		JLabel labelBT = new JLabel("");
		labelBT.setBounds(147, 84, 46, 14);
		contentPane.add(labelBT);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Cantidad", "Fecha", "DNI Usuario" }));
		table.setBounds(64, 129, 607, 178);
		contentPane.add(table);
		BLFacade db = LoginGUI.getBusinessLogic();
		List<Transaction> tr = db.getTransactions();
		float totalMoney = db.getMoneyOverall();
		labelDT.setText(String.valueOf(totalMoney));
		float trTotal = 0;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = 0; i < tr.size(); i++) {
			trTotal += tr.get(i).getCantidad();
			model.addRow(new Object[] { String.valueOf(tr.get(i).getCantidad()),
					tr.get(i).getTransactionDate().toString(), tr.get(i).getIdUser() });
		}
		labelDI.setText(String.valueOf(trTotal));
		labelBT.setText(String.valueOf(trTotal - totalMoney));

		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		btnNewButton.setBounds(210, 342, 307, 57);
		contentPane.add(btnNewButton);

	}

	void close() {
		this.setVisible(false);
	}
}
