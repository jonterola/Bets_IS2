package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
public class Options {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private int id;
	private int questionID;
	private String option;
	private float odds;

	public Options(int questionID, String option, float odds) {
		super();
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
