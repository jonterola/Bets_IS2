package domain;

import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import businessLogic.BLFacade;
import gui.LoginGUI;

@Entity
public class Team {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String league;
	private int category;
	private Event lastEvent;
	private Event nextEvent;

	public Team(String name, String league, int category) {
		this.name = name;
		this.league = league;
		this.category = category;

	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Event getLastEvent() {
		lastEvent = getLast();
		return lastEvent;
	}

	public Event getNextEvent() {
		nextEvent = getNext();
		return nextEvent;
	}

	private Event getLast() {
		boolean finish;
		Vector<Event> finished = new Vector<Event>();
		BLFacade facade = LoginGUI.getBusinessLogic();
		Vector<Event> events = facade.getEvents(this);
		for (int i = 0; i < events.size(); i++) {
			finish = false;
			for (Question q : events.get(i).getQuestions()) {
				if (q.isFinished()) {
					finish = true;
					break;
				}
			}
			if (finish)
				finished.add(events.get(i));
		}
		if (finished.size() > 0) {
			Event last = finished.get(0);
			for (int j = 1; j < finished.size(); j++) {
				if (last.getEventDate().compareTo(finished.get(j).getEventDate()) < 0)
					last = finished.get(j);
			}

			return last;
		}
		return null;
	}

	private Event getNext() {
		boolean finish;
		Vector<Event> notFinished = new Vector<Event>();
		BLFacade facade = LoginGUI.getBusinessLogic();
		Vector<Event> events = facade.getEvents(this);
		for (int i = 0; i < events.size(); i++) {
			finish = false;
			for (Question q : events.get(i).getQuestions()) {
				if (q.isFinished()) {
					finish = true;
					break;
				}
			}
			if (!finish)
				notFinished.add(events.get(i));
		}
		if (notFinished.size() > 0) {

			Event next = notFinished.get(0);
			for (int j = 1; j < notFinished.size(); j++) {
				if (next.getEventDate().compareTo(notFinished.get(j).getEventDate()) > 0)
					next = notFinished.get(j);
			}
			return next;
		}
		return null;
	}

}
