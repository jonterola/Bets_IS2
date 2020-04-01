package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Options;
import domain.Question;
import domain.Registro;

public class UsuarioGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));

	private JButton jButtonClose = new JButton(
			ResourceBundle.getBundle("Etiquetas").getString("UsuarioGUI.btnApostar.text")); //$NON-NLS-1$ //$NON-NLS-2$

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private ComboBoxModel<String> options = new DefaultComboBoxModel<String>();

	private List<Options> opciones;

	private JTable tableEvents = new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),

	};
	private String[] columnNamesQueries = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query"),

	};
	private final JLabel lblCuota = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("UsuarioGUI.lblCuota.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblNewLabel = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
	private Options selectedOption;
	private Registro user;
	private Event event;
	private Question question;

	public UsuarioGUI(Registro user2) {
		user = user2;
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries")); //$NON-NLS-1$ //$NON-NLS-2$

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(138, 248, 406, 14);
		jLabelEvents.setBounds(295, 19, 49, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(514, 303, 149, 25);
		getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (options.getSize() > 0) {
					int selectedIndex = comboBox.getSelectedIndex();
					selectedOption = opciones.get(selectedIndex);
					float cuota = selectedOption.getOdds();
					lblNewLabel.setText(String.valueOf(cuota));
				}
			}

		});

		jButtonClose.setBounds(new Rectangle(274, 419, 130, 30));

		jButtonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConfirmBetGUI c = new ConfirmBetGUI(event, question, user, selectedOption);
				c.setVisible(true);
			}
		});

		this.getContentPane().add(jButtonClose, null);

		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertychangeevent) {

				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay = UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade = LoginGUI.getBusinessLogic();

						Vector<domain.Event> events = facade.getEvents(firstDay);

						if (events.isEmpty())
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": "
									+ dateformat1.format(calendarMio.getTime()));
						else
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarMio.getTime()));
						for (domain.Event ev : events) {
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events " + ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						// shown
						// in
						// JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			}
		});

		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(138, 274, 355, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				event = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
				Vector<Question> queries = event.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries") + ": "
							+ event.getDescription());
				else
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent") + " "
							+ event.getDescription());

				for (domain.Question q : queries) {
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		comboBox.setModel(options);

		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox.removeAllItems();
				int i = tableQueries.getSelectedRow();
				question = (Question) tableModelQueries.getValueAt(i, 2);
				System.out.println(question.toString());
				BLFacade facade = LoginGUI.getBusinessLogic();
				opciones = facade.getOptionsQuestion(question);
				System.out.println(opciones.toString());
				if (opciones != null) {
					System.out.println(opciones.toString());
					for (Options op : opciones) {
						((DefaultComboBoxModel<String>) options).addElement(op.getOption());
					}
				}
			}

		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);

		JButton btnLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("UsuarioGUI.btnLogout.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnLogout.setBounds(585, 12, 89, 23);
		getContentPane().add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
			}

		});

		JLabel lblPronostico = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("UsuarioGUI.lblPronostico.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblPronostico.setBounds(513, 273, 101, 30);
		getContentPane().add(lblPronostico);
		lblCuota.setBounds(514, 339, 65, 14);

		getContentPane().add(lblCuota);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(583, 339, 80, 14);

		getContentPane().add(lblNewLabel);

		JLabel lblSaldoDiponible = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("UsuarioGUI.lblSaldoDiponible.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblSaldoDiponible.setBounds(397, 20, 96, 14);
		getContentPane().add(lblSaldoDiponible);

		JLabel lblSaldo = new JLabel(String.valueOf(user.getSaldo())); // $NON-NLS-1$ //$NON-NLS-2$
		lblSaldo.setBounds(498, 20, 46, 14);
		getContentPane().add(lblSaldo);

	}

	public void logout() {
		this.setVisible(false);

	}

}
