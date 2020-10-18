package dataAccess;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Bet;
import domain.Category;
import domain.Event;
import domain.Options;
import domain.Question;
import domain.Regalo;
import domain.Registro;
import domain.Team;
import domain.Transaction;
import exceptions.QuestionAlreadyExist;
import exceptions.UserDoesntExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c;

	public DataAccess(boolean initialize) {
		open(initialize);
	}

	public DataAccess() {
		new DataAccess(false);
	}

	public void open(boolean initializeMode) {

		c = ConfigXML.getInstance();

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode)
			fileName = fileName + ";drop";

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Category c1 = new Category(1, "Football");
			Category c2 = new Category(2, "Basketball");
			db.persist(c1);
			db.persist(c2);

			Team t1 = new Team("Atletico", "La Liga", c1.getId());
			Team t2 = new Team("Athletic", "La Liga", c1.getId());
			Team t3 = new Team("Eibar", "La Liga", c1.getId());
			Team t4 = new Team("Barcelona", "La Liga", c1.getId());
			Team t5 = new Team("Getafe", "La Liga", c1.getId());
			Team t6 = new Team("Celta", "La Liga", c1.getId());
			Team t7 = new Team("Alaves", "La Liga", c1.getId());
			Team t8 = new Team("Deportivo", "La Liga", c1.getId());
			Team t9 = new Team("Espanyol", "La Liga", c1.getId());
			Team t10 = new Team("Villareal", "La Liga", c1.getId());
			Team t11 = new Team("Las Palmas", "La Liga", c1.getId());
			Team t12 = new Team("Sevilla", "La Liga", c1.getId());
			Team t13 = new Team("Malaga", "La Liga", c1.getId());
			Team t14 = new Team("Valencia", "La Liga", c1.getId());
			Team t15 = new Team("Girona", "La Liga", c1.getId());
			Team t16 = new Team("Leganes", "La Liga", c1.getId());
			Team t17 = new Team("Real Sociedad", "La Liga", c1.getId());
			Team t18 = new Team("Levante", "La Liga", c1.getId());
			Team t19 = new Team("Betis", "La Liga", c1.getId());
			Team t20 = new Team("Real Madrid", "La Liga", c1.getId());

			Team t21 = new Team("Lakers", "NBA", c2.getId());
			Team t22 = new Team("Celtics", "NBA", c2.getId());

			Event ev1 = new Event(1, t1, t2, UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, t3, t4, UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, t5, t6, UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, t7, t8, UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, t9, t10, UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, t11, t12, UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, t13, t14, UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, t15, t16, UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, t17, t18, UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, t19, t20, UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, t1, t2, UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, t3, t4, UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, t5, t6, UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, t7, t8, UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, t9, t10, UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, t11, t12, UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, t13, t14, UtilDate.newDate(year, month, 28));
			Event ev18 = new Event(18, t15, t16, UtilDate.newDate(year, month, 28));
			Event ev19 = new Event(19, t17, t18, UtilDate.newDate(year, month, 28));
			Event ev20 = new Event(20, t19, t20, UtilDate.newDate(year, month, 28));

			Event ev21 = new Event(21, t21, t22, UtilDate.newDate(year, month, 17));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
			Question q7;

			Options o1;
			Options o2;
			Options o3;
			Options o4;

			Registro admin = new Registro("admin", "admin", "79133379Q", "admin@sinkingsoft.com", 21);
			Registro user = new Registro("user", "user", "35743378F", "user@gmail.com", 18);

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
				q7 = ev21.addQuestion("¿Quién ganará el partido?", 1);

			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
				q7 = ev21.addQuestion("Who will win the match?", 1);

			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);
				q7 = ev21.addQuestion("Zeinek irabaziko du partidua?", 1);

			}

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
			db.persist(q7);

			db.persist(t1);
			db.persist(t2);
			db.persist(t3);
			db.persist(t4);
			db.persist(t5);
			db.persist(t6);
			db.persist(t7);
			db.persist(t8);
			db.persist(t9);
			db.persist(t10);
			db.persist(t11);
			db.persist(t12);
			db.persist(t13);
			db.persist(t14);
			db.persist(t15);
			db.persist(t16);
			db.persist(t17);
			db.persist(t18);
			db.persist(t19);
			db.persist(t20);
			db.persist(t21);
			db.persist(t22);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);
			db.persist(ev21);

			db.getTransaction().commit();

			db.getTransaction().begin();
			db.persist(admin);
			db.persist(user);
			o1 = new Options(q1.getQuestionNumber(), "Athletic", Float.valueOf("1.1"));
			o2 = new Options(q1.getQuestionNumber(), "X", Float.valueOf("1.6"));
			o3 = new Options(q1.getQuestionNumber(), "Atletico", Float.valueOf("2.7"));

			o4 = new Options(q7.getQuestionNumber(), "Lakers", Float.valueOf("1.1"));

			db.persist(o1);
			db.persist(o2);
			db.persist(o3);
			db.persist(o4);
			db.getTransaction().commit();

			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
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
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		// db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	public void updateQuestion(List<Options> op) {
		TypedQuery<Options> query = db.createQuery("SELECT op FROM Options op WHERE op.questionID= ?1", Options.class);
		query.setParameter(1, op.get(0).getQuestionID());
		List<Options> og = query.getResultList();
		db.getTransaction().begin();
		for (int i = 0; i < og.size(); i++) {
			db.remove(og.get(i));
		}
		db.getTransaction().commit();
		db.getTransaction().begin();
		for (int i = 0; i < op.size(); i++) {
			db.persist(op.get(i));
		}
		db.getTransaction().commit();
	}

	public void addEvent(Team local, Team visitante, Date date) throws QuestionAlreadyExist {
		String nombre = local.getName() + "-" + visitante.getName();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev", Event.class);
		List<Event> events = query.getResultList();
		int id = events.size() + 1;
		System.out.println(">> DataAccess: createEvent=> nombre= " + nombre + " Date= " + date + " id=" + id);
		db.getTransaction().begin();
		Event ev = new Event(id, local, visitante, date);
		db.persist(ev);
		db.getTransaction().commit();
	}

	public int addUser(String dni, String user, String mail, String pwd, int age, String gift, float mon) {

		TypedQuery<Registro> query1 = db.createQuery("SELECT rg FROM Registro rg WHERE rg.mail ='" + mail + "'",
				Registro.class);
		if (!query1.getResultList().isEmpty())
			return 1;
		TypedQuery<Registro> query2 = db.createQuery("SELECT rg FROM Registro rg WHERE rg.nick ='" + user + "'",
				Registro.class);
		if (!query2.getResultList().isEmpty())
			return 2;
		TypedQuery<Registro> query3 = db.createQuery("SELECT rg FROM Registro rg WHERE rg.dni ='" + dni + "'",
				Registro.class);
		if (!query3.getResultList().isEmpty())
			return 3;

		Registro u = new Registro(user, pwd, dni, mail, age);
		db.getTransaction().begin();
		if (mon > 0) {
			u.setSaldo(mon);
		}
		TypedQuery<Regalo> query4 = db.createQuery("SELECT gf FROM Regalo gf WHERE gf.cod ='" + gift + "'",
				Regalo.class);
		if (!query4.getResultList().isEmpty()) {
			Regalo r = query4.getResultList().get(0);
			u.setSaldo(u.getSaldo() + r.getMoney());
			r.sum();
			db.remove(r);
			db.getTransaction().commit();
			db.getTransaction().begin();
			db.persist(r);
			db.getTransaction().commit();
			db.getTransaction().begin();
		}

		db.persist(u);
		db.getTransaction().commit();
		return 0;
	}

	public boolean exist(String mail) {
		TypedQuery<Registro> query1 = db.createQuery("SELECT rg FROM Registro rg WHERE rg.mail ='" + mail + "'",
				Registro.class);
		if (!query1.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public Registro login(String mail, String pwd) {
		TypedQuery<Registro> query = db.createQuery(
				"SELECT rg FROM Registro rg WHERE rg.mail ='" + mail + "' AND rg.pw = '" + pwd + "'", Registro.class);
		List<Registro> events = query.getResultList();

		if (events.isEmpty()) {
			return null;
		} else {
			return events.get(0);
		}
	}

	public List<Event> getEvents(Category c) {
		int cat = c.getId();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.local.getCategory()=?1",
				Event.class);
		query.setParameter(1, cat);
		List<Event> events = query.getResultList();
		return events;
	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	public Vector<Event> getEvents(Category c, Date date) {
		int cat = c.getId();
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery(
				"SELECT ev FROM Event ev WHERE ev.eventDate=?1 AND ev.local.getCategory()=?2", Event.class);
		query.setParameter(1, date);
		query.setParameter(2, cat);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	public List<Team> getTeams(int id) {
		TypedQuery<Team> query = db.createQuery("SELECT t FROM Team t WHERE t.category=?1", Team.class);
		query.setParameter(1, id);
		List<Team> teams = query.getResultList();
		return teams;
	}

	public List<Category> getCategories() {
		TypedQuery<Category> query = db.createQuery("SELECT c FROM Category c", Category.class);
		List<Category> categories = query.getResultList();
		return categories;
	}

	public Vector<Event> getEvents(Team t) {
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.local=?1 OR ev.visitante=?1",
				Event.class);
		query.setParameter(1, t);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public List<Options> getOptionsQuestion(Question q) {
		TypedQuery<Options> query = db.createQuery("SELECT op FROM Options op WHERE op.questionID= ?1", Options.class);
		query.setParameter(1, q.getQuestionNumber());
		List<Options> op = query.getResultList();
		return op;
	}

	public List<Bet> getBetOptions(Options o) {
		TypedQuery<Bet> query = db.createQuery("SELECT b FROM Bet b WHERE b.optionID= ?1", Bet.class);
		query.setParameter(1, o.getId());
		List<Bet> bet = query.getResultList();
		return bet;
	}

	public void updateMoney(Bet bet, float cuota) {
		float profit = cuota * bet.getCantidadApostada();
		TypedQuery<Registro> query = db.createQuery("SELECT r FROM Registro r WHERE r.dni= ?1", Registro.class);
		query.setParameter(1, bet.getUserDNI());
		Registro user = query.getSingleResult();
		float saldo = user.getSaldo();
		db.getTransaction().begin();
		System.out.println(user.getSaldo());
		user.setSaldo(saldo + profit);
		System.out.println(user.getSaldo());
		db.getTransaction().commit();
	}

	public void updateQuestion(Question q) {
		db.getTransaction().begin();
		Question qu = db.find(Question.class, q);
		qu.setFinished(true);
		db.getTransaction().commit();
	}

	public void addMoney(String userDni, float cantidad, boolean isBox) {
		TypedQuery<Registro> query = db.createQuery("SELECT us FROM Registro us WHERE us.dni= ?1", Registro.class);
		query.setParameter(1, userDni);
		List<Registro> og = query.getResultList();
		Registro user = og.get(0);
		db.getTransaction().begin();
		for (int i = 0; i < og.size(); i++) {
			db.remove(og.get(i));
		}
		db.getTransaction().commit();
		db.getTransaction().begin();
		user.setSaldo(user.getSaldo() + cantidad);
		if (isBox) {
			user.setLastBox(new Date());
		} else {
			Transaction t = new Transaction(cantidad, new Date(), user.getDni());
			db.persist(t);
		}
		db.persist(user);
		db.getTransaction().commit();
	}

	public void newBet(Bet b) {
		db.getTransaction().begin();
		db.persist(b);
		db.getTransaction().commit();
	}

	public List<Bet> getBet(String user) {
		TypedQuery<Bet> query = db.createQuery("SELECT rg FROM Bet rg WHERE rg.userDNI ='" + user + "'", Bet.class);
		List<Bet> og = query.getResultList();
		return og;
	}

	public Options getOption(int id) {
		TypedQuery<Options> query = db.createQuery("SELECT rg FROM Options rg WHERE rg.id = " + id, Options.class);
		List<Options> og = query.getResultList();
		return og.get(0);
	}

	public Question getQuestion(int id) {
		TypedQuery<Question> query = db.createQuery("SELECT rg FROM Question rg WHERE rg.questionNumber = " + id,
				Question.class);
		List<Question> og = query.getResultList();
		return og.get(0);
	}

	public void updateUser(Registro user) {
		TypedQuery<Registro> query = db.createQuery("SELECT us FROM Registro us WHERE us.dni= ?1", Registro.class);
		query.setParameter(1, user.getDni());
		List<Registro> og = query.getResultList();
		db.getTransaction().begin();
		for (int i = 0; i < og.size(); i++) {
			db.remove(og.get(i));
		}
		db.getTransaction().commit();
		db.getTransaction().begin();
		db.persist(user);
		db.getTransaction().commit();
	}

	public List<Transaction> getTransactions() {
		TypedQuery<Transaction> query = db.createQuery("SELECT tc FROM Transaction tc", Transaction.class);
		return query.getResultList();
	}

	public float getMoneyOverall() {
		float total = 0;
		TypedQuery<Registro> query = db.createQuery("SELECT us FROM Registro us", Registro.class);
		List<Registro> og = query.getResultList();
		for (int i = 0; i < og.size(); i++) {
			total += og.get(i).getSaldo();
		}
		TypedQuery<Bet> query2 = db.createQuery("SELECT rg FROM Bet rg WHERE rg.activa = true", Bet.class);
		List<Bet> og2 = query2.getResultList();
		for (int i = 0; i < og2.size(); i++) {
			total += og2.get(i).getCantidadApostada();
		}
		return total;
	}

	public List<Registro> getAllUsers() {
		TypedQuery<Registro> query = db.createQuery("SELECT us FROM Registro us WHERE us.admin = false",
				Registro.class);
		return query.getResultList();

	}

	public void statusUser(String DNI, Boolean block) {
		TypedQuery<Registro> query = db.createQuery("SELECT us FROM Registro us WHERE us.dni= ?1", Registro.class);
		query.setParameter(1, DNI);
		List<Registro> og = query.getResultList();
		Registro us = og.get(0);
		us.setBlock(block);
		db.getTransaction().begin();
		db.remove(us);
		db.getTransaction().commit();
		db.getTransaction().begin();
		db.persist(us);
		db.getTransaction().commit();
	}

	public void addGift(String cod, float money) {
		Regalo r = new Regalo(cod, money);
		db.getTransaction().begin();
		db.persist(r);
		db.getTransaction().commit();
	}

	public void removeGift(String cod) {
		TypedQuery<Regalo> query = db.createQuery("SELECT r FROM Regalo r WHERE r.cod= ?1", Regalo.class);
		query.setParameter(1, cod);
		List<Regalo> rq = query.getResultList();
		db.getTransaction().begin();
		db.remove(rq.get(0));
		db.getTransaction().commit();
	}

	public Registro getUser(String dni) throws UserDoesntExist {

		TypedQuery<Registro> query = db.createQuery("SELECT r FROM Registro r WHERE r.dni= ?1", Registro.class);
		query.setParameter(1, dni);
		List<Registro> rq = query.getResultList();
		if (query.getResultList().isEmpty())
			throw new UserDoesntExist();
		return rq.get(0);
	}

	public void removeUser(String dni) {

		TypedQuery<Registro> query = db.createQuery("SELECT r FROM Registro r WHERE r.dni= ?1", Registro.class);
		query.setParameter(1, dni);
		List<Registro> rq = query.getResultList();
		if (!query.getResultList().isEmpty()) {
			db.getTransaction().begin();
			db.remove(rq.get(0));
			db.getTransaction().commit();
		}
	}

	public List<Regalo> getGifts() {
		TypedQuery<Regalo> query = db.createQuery("SELECT us FROM Regalo us", Regalo.class);
		return query.getResultList();
	}
}
