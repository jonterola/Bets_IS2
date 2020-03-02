package gui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Options;
import domain.Question;

public class OddsGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Question question;

	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JTable table;
	private DefaultTableModel modelo;

	public OddsGUI(Question questio) {
		setSize(433, 206);
		setResizable(false);
		setAlwaysOnTop(true);
		try {
			question = questio;
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 12, 277, 107);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "Opcion", "Cuota" }));
		modelo = (DefaultTableModel) table.getModel();
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("+"); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modelo.addRow(new String[] { null, null });
			}
		});
		btnNewButton.setBounds(365, 25, 48, 77);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton(
				ResourceBundle.getBundle("Etiquetas").getString("OddsGUI.btnNewButton_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		btnNewButton_1.setBounds(144, 127, 140, 37);
		getContentPane().add(btnNewButton_1);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

	}

	private void guardar() {
		// TODO Auto-generated method stub
		Vector<Options> op = new Vector<Options>();
		try {
			for (int i = 0; i < modelo.getRowCount(); i++) {
				op.add(new Options((String) modelo.getValueAt(i, 0), Float.valueOf((String) modelo.getValueAt(i, 1))));
			}
			BLFacade facade = MainGUI.getBusinessLogic();

			facade.updateQuestion(question, op);
			this.setVisible(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}