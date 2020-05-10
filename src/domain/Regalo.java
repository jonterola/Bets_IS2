package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Regalo {
	@Id
	private String cod;
	private float money;
	private int uses;

	public String getCod() {
		return cod;
	}

	public float getMoney() {
		return money;
	}

	public int getUses() {
		return uses;
	}

	public Regalo(String cod, float money) {
		super();
		this.cod = cod;
		this.money = money;
		this.uses = 0;
	}

	public void sum() {
		this.uses++;
	};
}
