package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
public class Transaction {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private int transactionId;

	public int getTransactionId() {
		return transactionId;
	}

	private float Cantidad;
	private Date transactionDate;
	private String idUser;

	public Transaction(float cantidad, Date transactionDate, String idUser) {
		super();
		Cantidad = cantidad;
		this.transactionDate = transactionDate;
		this.idUser = idUser;
	}

	public float getCantidad() {
		return Cantidad;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public String getIdUser() {
		return idUser;
	}

}
