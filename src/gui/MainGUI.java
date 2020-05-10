package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * @author Software Engineering teachers
 */
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Event;
import domain.Registro;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private JButton jButtonCreateEvent = null;
	private JButton jButtonAddResult = null;

	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnNewButton;
	private LoginGUI log;
	private Registro login;
	private JButton btnViewFinances;
	private JButton btnBlockunblockUsers;
	private JButton btnGifts;

	/**
	 * This is the default constructor
	 */
	public MainGUI(LoginGUI lo, Registro login1) {
		super();
		log = lo;
		login = login1;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					// if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(
							"Error: " + e1.toString() + " , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(547, 459);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridLayout(9, 1, 0, 0));
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton4());
			jContentPane.add(getBoton2());
			jContentPane.add(getBoton5());
			jContentPane.add(getBtnViewFinances());
			jContentPane.add(getBtnBlockunblockUsers());
			jContentPane.add(getBtnGifts());
			jContentPane.add(getBtnNewButton());

		}
		return jContentPane;
	}

	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BLFacade facade = LoginGUI.getBusinessLogic();
					// Vector<Event> events=facade.getAllEvents();
					JFrame a = new CreateQuestionGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionsGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}

	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton4() {
		if (jButtonCreateEvent == null) {
			jButtonCreateEvent = new JButton();
			jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
			jButtonCreateEvent.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BLFacade facade = LoginGUI.getBusinessLogic();
					// Vector<Event> events=facade.getAllEvents();
					JFrame a = new EventCreate();
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateEvent;
	}

	private JButton getBoton5() {
		if (jButtonAddResult == null) {
			jButtonAddResult = new JButton();
			jButtonAddResult.setText(ResourceBundle.getBundle("Etiquetas").getString("AddResult"));
			jButtonAddResult.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BLFacade facade = LoginGUI.getBusinessLogic();
					// Vector<Event> events=facade.getAllEvents();
					JFrame a = new AddResultGUI(login);
					a.setVisible(true);
				}
			});
		}
		return jButtonAddResult;
	}

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}

	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}

	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}

	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		jButtonAddResult.setText(ResourceBundle.getBundle("Etiquetas").getString("AddResult"));

		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("UsuarioGUI.btnLogout.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					logout();
				}
			});
		}
		return btnNewButton;
	}

	private void logout() {
		this.setVisible(false);
		log.setVisible(true);
	}

	private JButton getBtnViewFinances() {
		if (btnViewFinances == null) {
			btnViewFinances = new JButton(
					ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnViewFinances.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnViewFinances.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame a = new FinancesGUI();
					a.setVisible(true);
				}
			});
		}
		return btnViewFinances;
	}

	private JButton getBtnBlockunblockUsers() {
		if (btnBlockunblockUsers == null) {
			btnBlockunblockUsers = new JButton(
					ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnBlockunblockUsers.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnBlockunblockUsers.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new BanGUI();
					a.setVisible(true);
				}
			});
		}
		return btnBlockunblockUsers;
	}

	private JButton getBtnGifts() {
		if (btnGifts == null) {
			btnGifts = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnGifts.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnGifts.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame a = new GiftsGUI();
					a.setVisible(true);
				}
			});
		}
		return btnGifts;
	}
} // @jve:decl-index=0:visual-constraint="0,0"
