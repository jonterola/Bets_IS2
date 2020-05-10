package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Bet;
import domain.Category;
import domain.Event;
import domain.Options;
//import domain.Booking;
import domain.Question;
import domain.Regalo;
import domain.Registro;
import domain.Team;
import domain.Transaction;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade {

	@WebMethod
	void addEvent(Team local, Team visitante, Date date) throws QuestionAlreadyExist;

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
	@WebMethod
	Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;

	@WebMethod
	public void updateQuestion(List<Options> op);

	/**
	 * This method retrieves the events of a given date
	 * 
	 * @param date
	 *            in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEvents(Date date);

	@WebMethod
	public Vector<Event> getEvents(Category c, Date date);

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date
	 *            of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getEventsMonth(Date date);

	/**
	 * This method calls the data access to initialize the database with some events
	 * and questions. It is invoked only when the option "initialize" is declared in
	 * the tag dataBaseOpenMode of resources/config.xml file
	 */

	@WebMethod
	public void initializeBD();

	@WebMethod
	public List<Options> getOptionsQuestion(Question q);

	@WebMethod
	public boolean exist(String mail);

	@WebMethod
	public int createUser(String dni, String user, String mail, String pwd, int age, String gift, float mon);

	@WebMethod
	public Registro newLogin(String mail, String pwd);

	@WebMethod
	public void addMoney(String userDni, float cantidad, boolean isBox);

	@WebMethod
	public void updateUser(Registro user);

	@WebMethod
	public List<Registro> getAllUsers();

	@WebMethod
	public void newBet(Bet b);

	@WebMethod
	public List<Bet> getBetOptions(Options o);

	@WebMethod
	public void updateMoney(Bet bet, float cuota);

	@WebMethod
	public void updateQuestion(Question question);

	@WebMethod
	public List<Bet> getBet(String user);

	@WebMethod
	public Options getOption(int id);

	@WebMethod
	public Vector<Event> getEvents(Team t);

	@WebMethod
	public Question getQuestion(int id);

	@WebMethod
	public List<Category> getCategories();

	@WebMethod
	public List<Team> getTeams(int id);

	@WebMethod
	public List<Transaction> getTransactions();

	@WebMethod
	public float getMoneyOverall();

	@WebMethod
	public void statusUser(String DNI, Boolean block);

	@WebMethod
	public void addGift(String cod, float money);

	@WebMethod
	public List<Regalo> getGifts();

	@WebMethod
	public void removeGift(String cod);
}
