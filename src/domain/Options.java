package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Options {

	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private int id;
	private int questionID;
	private String option;
	private float odds;

	public Options(int questionID, String option, float odds) {
		super();
		this.questionID = questionID;
		this.option = option;
		this.odds = odds;
	}

	public int getQuestionID() {
		return questionID;
	}

	public int getId() {
		return id;
	}

	public String getOption() {
		return option;
	}

	public float getOdds() {
		return odds;
	}

}
