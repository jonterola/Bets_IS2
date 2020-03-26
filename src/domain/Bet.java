package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
public class Bet {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private int id;
	private int optionID;
	private String userDNI;
	private float cantidadApostada;
	private boolean activa;

	public Bet(int optionID, String userDNI, float cantidadApostada) {
		super();
		this.optionID = optionID;
		this.userDNI = userDNI;
		this.cantidadApostada = cantidadApostada;
		this.activa = true;
	}

	public int getId() {
		return id;
	}

	public int getOptionID() {
		return optionID;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public String getUserDNI() {
		return userDNI;
	}

	public float getCantidadApostada() {
		return cantidadApostada;
	}
}
