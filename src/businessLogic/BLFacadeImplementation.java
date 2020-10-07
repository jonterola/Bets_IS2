package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Bet;
import domain.Category;
import domain.Event;
import domain.Options;
import domain.Question;
import domain.Regalo;
import domain.Registro;
import domain.Team;
import domain.Transaction;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			dbManager.open(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}

	}

	public BLFacadeImplementation(DataAccess da) {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();
		}
		dbManager = da;
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event
	 *            to which question is added
	 * @param question
	 *            text of the question
	 * @param betMinimum
	 *            minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished
	 *             if current data is after data of the event
	 * @throws QuestionAlreadyExist
	 *             if the same question already exists for the event
	 */
	@Override
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);

		dbManager.close();

		return qry;
	};

	@Override
	@WebMethod
	public void updateQuestion(List<Options> op) {

		// The minimum bed must be greater than 0
		dbManager.open(false);
		dbManager.updateQuestion(op);
		dbManager.close();
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date
	 *            in which events are retrieved
	 * @return collection of events
	 */
	@Override
	@WebMethod
	public Vector<Event> getEvents(Date date) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	@Override
	@WebMethod
	public Vector<Event> getEvents(Category c, Date date) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(c, date);
		dbManager.close();
		return events;
	}

	@Override
	@WebMethod
	public List<Team> getTeams(int id) {
		dbManager.open(false);
		List<Team> teams = dbManager.getTeams(id);
		dbManager.close();
		return teams;
	}

	@Override
	@WebMethod
	public List<Category> getCategories() {
		dbManager.open(false);
		List<Category> categories = dbManager.getCategories();
		dbManager.close();
		return categories;
	}

	@Override
	@WebMethod
	public Vector<Event> getEvents(Team t) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(t);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date
	 *            of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@Override
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	@Override
	@WebMethod
	public List<Options> getOptionsQuestion(Question q) {
		dbManager.open(false);
		List<Options> op = dbManager.getOptionsQuestion(q);
		dbManager.close();
		return op;
	}

	@Override
	@WebMethod
	public void addEvent(Team local, Team visitante, Date date) throws QuestionAlreadyExist {
		dbManager.open(false);
		dbManager.addEvent(local, visitante, date);
		dbManager.close();
	}

	@Override
	@WebMethod
	public List<Bet> getBetOptions(Options o) {
		dbManager.open(false);
		List<Bet> bets = dbManager.getBetOptions(o);
		dbManager.close();
		return bets;
	}

	@Override
	@WebMethod
	public void updateMoney(Bet bet, float cuota) {
		dbManager.open(false);
		dbManager.updateMoney(bet, cuota);
		dbManager.close();
	}

	@Override
	public void updateQuestion(Question q) {
		dbManager.open(false);
		dbManager.updateQuestion(q);
		dbManager.close();
	}

	@Override
	@WebMethod
	public int createUser(String dni, String user, String mail, String pwd, int age, String gift, float mon) {
		dbManager.open(false);
		int i = dbManager.addUser(dni, user, mail, pwd, age, gift, mon);
		dbManager.close();
		return i;

	}

	@Override
	@WebMethod
	public boolean exist(String mail) {
		dbManager.open(false);
		boolean sol = dbManager.exist(mail);
		return sol;
	}

	@Override
	@WebMethod
	public Registro newLogin(String mail, String pwd) {
		Registro resul = null;
		dbManager.open(false);
		resul = dbManager.login(mail, pwd);
		dbManager.close();
		return resul;
	}

	@Override
	@WebMethod
	public void addMoney(String userDni, float cantidad, boolean isBox) {
		dbManager.open(false);
		dbManager.addMoney(userDni, cantidad, isBox);
		dbManager.close();
	}

	@Override
	@WebMethod
	public void updateUser(Registro user) {
		dbManager.open(false);
		dbManager.updateUser(user);
		dbManager.close();
	}

	@Override
	@WebMethod
	public List<Transaction> getTransactions() {
		dbManager.open(false);
		List<Transaction> lista = dbManager.getTransactions();
		dbManager.close();
		return lista;
	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@Override
	@WebMethod
	public void initializeBD() {
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@Override
	@WebMethod
	public void newBet(Bet b) {
		dbManager.open(false);
		dbManager.newBet(b);
		dbManager.close();
	}

	@Override
	public List<Bet> getBet(String user) {
		dbManager.open(false);
		List<Bet> sol = dbManager.getBet(user);
		dbManager.close();
		return sol;
	}

	@Override
	public Options getOption(int id) {
		dbManager.open(false);
		Options sol = dbManager.getOption(id);
		dbManager.close();
		return sol;
	}

	@Override
	public Question getQuestion(int id) {
		dbManager.open(false);
		Question sol = dbManager.getQuestion(id);
		dbManager.close();
		return sol;
	}

	@Override
	public List<Registro> getAllUsers() {
		dbManager.open(false);
		List<Registro> us = dbManager.getAllUsers();
		dbManager.close();
		return us;

	}

	@Override
	public float getMoneyOverall() {
		dbManager.open(false);
		float total = dbManager.getMoneyOverall();
		dbManager.close();
		return total;
	}

	@Override
	public void statusUser(String DNI, Boolean block) {
		dbManager.open(false);
		dbManager.statusUser(DNI, block);
		dbManager.close();
	}

	@Override
	public void addGift(String cod, float money) {
		dbManager.open(false);
		dbManager.addGift(cod, money);
		dbManager.close();
	}

	@Override
	public void removeGift(String cod) {
		dbManager.open(false);
		dbManager.removeGift(cod);
		dbManager.close();
	}

	@Override
	public List<Regalo> getGifts() {
		dbManager.open(false);
		List<Regalo> us = dbManager.getGifts();
		dbManager.close();
		return us;
	}
}
