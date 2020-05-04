package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Category;
import domain.Team;

public class EventCreate extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel jLabelQuery = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));

	private JCalendar jCalendar = new JCalendar();

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private ComboBoxModel<String> category = new DefaultComboBoxModel<String>();
	private ComboBoxModel<String> team = new DefaultComboBoxModel<String>();
	private ComboBoxModel<String> team2 = new DefaultComboBoxModel<String>();

	private List<Category> categories;
	private List<Team> teams;
	private List<Team> teams2;

	private Team selectedLocal;
	private Team selectedAway;
	private Category selectedCategory;

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private final JLabel label = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventCreate.label.text")); //$NON-NLS-1$ //$NON-NLS-2$

	public EventCreate() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		jLabelQuery.setBounds(new Rectangle(25, 211, 75, 20));

		BLFacade facade = LoginGUI.getBusinessLogic();
		categories = facade.getCategories();

		JComboBox<String> comboBox1 = new JComboBox<String>();
		JComboBox<String> comboBox2 = new JComboBox<String>();
		JComboBox<String> comboBox3 = new JComboBox<String>();

		comboBox1.setBounds(new Rectangle(401, 122, 121, 20));
		this.getContentPane().add(comboBox1, null);
		comboBox1.setModel(category);
		if (categories != null) {
			for (Category c : categories) {
				((DefaultComboBoxModel<String>) category).addElement(c.getName());
			}
		}
		comboBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (category.getSize() > 0) {
					comboBox2.removeAllItems();
					comboBox3.removeAllItems();
					int selectedIndex1 = comboBox1.getSelectedIndex();
					selectedCategory = categories.get(selectedIndex1);
					teams = facade.getTeams(selectedCategory.getId());
					teams2 = teams;
					if (teams != null) {
						for (Team t : teams) {
							((DefaultComboBoxModel<String>) team).addElement(t.getName());
						}
					}
					if (teams2 != null) {
						for (Team t : teams2) {
							((DefaultComboBoxModel<String>) team2).addElement(t.getName());
						}
					}

				}

			}

		});

		comboBox2.setBounds(new Rectangle(110, 211, 121, 20));
		this.getContentPane().add(comboBox2, null);
		comboBox2.setModel(team);
		comboBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (team.getSize() > 0) {
					int selectedIndex2 = comboBox2.getSelectedIndex();
					selectedLocal = teams.get(selectedIndex2);

				}
			}

		});

		comboBox3.setBounds(new Rectangle(326, 211, 121, 20));
		this.getContentPane().add(comboBox3, null);
		comboBox3.setModel(team2);
		comboBox3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (team2.getSize() > 0) {
					int selectedIndex3 = comboBox3.getSelectedIndex();
					selectedAway = teams2.get(selectedIndex3);
				}
			}

		});

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonCreate.setBounds(new Rectangle(100, 275, 130, 30));
		jButtonCreate.setEnabled(true);

		jButtonCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(275, 275, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(175, 240, 305, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jLabelQuery, null);

		this.getContentPane().add(jCalendar, null);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);

		JLabel lblCategory = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("EventCreate.lblCategory.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblCategory.setBounds(300, 125, 91, 14);
		getContentPane().add(lblCategory);
		label.setBounds(275, 214, 18, 14);

		getContentPane().add(label);

	}

	/*
	 * Less eficient version: too many calls to business logic
	 * 
	 * 
	 * public static void paintDaysWithEvents(JCalendar jCalendar) { // For each day
	 * in current month, it is checked if there are events, and in that // case, the
	 * background color for that day is changed.
	 * 
	 * BLFacade facade = LoginGUI.getBusinessLogic();
	 * 
	 * Calendar calendar = jCalendar.getCalendar();
	 * 
	 * calendar.set(Calendar.DAY_OF_MONTH, 1); calendar.set(Calendar.MILLISECOND,
	 * 0); calendar.set(Calendar.SECOND, 0); calendar.set(Calendar.MINUTE, 0);
	 * calendar.set(Calendar.HOUR_OF_DAY, 0);
	 * 
	 * int offset = calendar.get(Calendar.DAY_OF_WEEK); if
	 * (Locale.getDefault().equals(new Locale("es"))) offset += 4; else offset += 5;
	 * 
	 * int month = calendar.get(Calendar.MONTH); while (month ==
	 * calendar.get(Calendar.MONTH)) { Vector<domain.Event> events =
	 * facade.getEvents(calendar.getTime()); if (events.size() > 0) { // Obtain the
	 * component of the day in the panel of the DayChooser of the // JCalendar. //
	 * The component is located after the decorator buttons of "Sun", "Mon",... or
	 * // "Lun", "Mar"..., // the empty days before day 1 of month, and all the days
	 * previous to each day. // That number of components is calculated with
	 * "offset" and is different in // English and Spanish // Component
	 * o=(Component)
	 * jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; Component o
	 * = (Component) jCalendar.getDayChooser().getDayPanel()
	 * .getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
	 * o.setBackground(Color.CYAN); } calendar.set(Calendar.DAY_OF_MONTH,
	 * calendar.get(Calendar.DAY_OF_MONTH) + 1); } calendar.set(Calendar.MONTH,
	 * month); }
	 * 
	 */

	private void jButtonCreate_actionPerformed(ActionEvent e) {
		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			// Displays an exception if the query field is empty

			if (selectedLocal != null && selectedAway != null) {

				if (selectedLocal.getId() == selectedAway.getId()) {
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("TeamsDifferent"));

				} else {
					// It could be to trigger an exception if the introduced string is not a number

					// Obtain the business logic from a StartWindow class (local or remote)
					BLFacade facade = LoginGUI.getBusinessLogic();
					Date date = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));

					facade.addEvent(selectedLocal, selectedAway, date);

					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("EventCreated"));
				}

			} else
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEvent"));
		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}