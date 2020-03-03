package domain;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Options {
	@Id
	private int id;
	private int questionID;
	private String option;
	private float odds;

	public Options(int questionID, String option, float odds) {
		super();
		Random r = new Random();
		this.questionID = questionID;
		this.id = r.nextInt();
		this.option = option;
		this.odds = odds;
	}

	public int getQuestionID() {
		return questionID;
	}

	public String getOption() {
		return option;
	}

	public float getOdds() {
		return odds;
	}

}
