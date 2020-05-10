package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Registro {
	@Id
	private String nick; // nombre dentro de la aplicación
	@Id
	private String mail;
	private String pw;
	@Id
	private String dni;
	private boolean admin;
	private int edad;
	private float saldo;
	private boolean block;
	// private Pronostico pron;

	public Registro(String nick, String pw, String dni, String mail, int edad) {
		this.dni = dni;
		this.pw = pw;
		this.nick = nick;
		if (mail.contains("@sinkingsoft.com")) {
			this.admin = true;
		} else {
			this.admin = false;
		}
		this.block = false;
		this.mail = mail;
		this.edad = edad;
		this.saldo = 0f;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public boolean isAdmin() {
		return admin;
	}

	public boolean isBlocked() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

	@Override
	public String toString() {
		return "Registro [nick=" + nick + ", pw=" + pw + ", dni=" + dni + ", admin=" + admin + "]";
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
