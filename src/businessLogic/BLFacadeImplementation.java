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
import domain.Event;
import domain.Options;
import domain.Question;
import domain.Registro;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}

	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	@Override
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		DataAccess dBManager = new DataAccess();
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dBManager.createQuestion(event, question, betMinimum);

		dBManager.close();

		return qry;
	};

	@Override
	@WebMethod
	public void updateQuestion(List<Options> op) {

		// The minimum bed must be greater than 0
		DataAccess dBManager = new DataAccess();
		dBManager.updateQuestion(op);
		dBManager.close();
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@Override
	@WebMethod
	public Vector<Event> getEvents(Date date) {
		DataAccess dbManager = new DataAccess();
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@Override
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		DataAccess dbManager = new DataAccess();
		Vector<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	@Override
	@WebMethod
	public List<Options> getOptionsQuestion(Question q) {
		DataAccess dbManager = new DataAccess();
		List<Options> op = dbManager.getOptionsQuestion(q);
		dbManager.close();
		return op;
	}

	@Override
	@WebMethod
	public void addEvent(String nombre, Date date) throws QuestionAlreadyExist {
		DataAccess dbManager = new DataAccess();
		dbManager.addEvent(nombre, date);
		dbManager.close();
	}

	@Override
	@WebMethod
	public List<Bet> getBetOptions(Options o) {
		DataAccess dbManager = new DataAccess();
		List<Bet> bets = dbManager.getBetOptions(o);
		dbManager.close();
		return bets;
	}

	@Override
	@WebMethod
	public void updateMoney(Bet bet, float cuota) {
		DataAccess dbManager = new DataAccess();
		dbManager.updateMoney(bet, cuota);
		dbManager.close();
	}

	@Override
	public void updateQuestion(Question q) {
		DataAccess dbManager = new DataAccess();
		dbManager.updateQuestion(q);
		dbManager.close();
	}

	@Override
	@WebMethod
	public int createUser(String dni, String user, String mail, String pwd, int age) {
		DataAccess dbManager = new DataAccess();
		int i = dbManager.addUser(dni, user, mail, pwd, age);
		dbManager.close();
		return i;

	}

	@Override
	@WebMethod
	public Registro newLogin(String mail, String pwd) {
		Registro resul = null;
		DataAccess dbManager = new DataAccess();
		resul = dbManager.login(mail, pwd);
		dbManager.close();
		return resul;
	}

	@Override
	@WebMethod
	public void updateUser(Registro user) {
		DataAccess dbManager = new DataAccess();
		dbManager.updateUser(user);
		dbManager.close();
	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@Override
	@WebMethod
	public void initializeBD() {
		DataAccess dBManager = new DataAccess();
		dBManager.initializeDB();
		dBManager.close();
	}

	@Override
	@WebMethod
	public void newBet(Bet b) {
		DataAccess dbManager = new DataAccess();
		dbManager.newBet(b);
		dbManager.close();
	}

	@Override
	public List<Bet> getBet(String user) {
		DataAccess dbManager = new DataAccess();
		List<Bet> sol = dbManager.getBet(user);
		return sol;

	}

	@Override
	public Options getOption(int id) {
		DataAccess dbManager = new DataAccess();
		Options sol = dbManager.getOption(id);
		return sol;
	}

	@Override
	public Question getQuestion(int id) {
		DataAccess dbManager = new DataAccess();
		Question sol = dbManager.getQuestion(id);
		return sol;
	}

}
