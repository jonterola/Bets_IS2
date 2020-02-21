package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Registro {
	@Id
	private String nick; // nombre dentro de la aplicación
	private String pw;
	@Id
	private String dni;
	private boolean admin;
	// private Pronostico pron;

	public Registro(String nick, String pw, String dni) {
		if (checkDNI(dni))
			this.dni = dni;
		else
			System.out.println("DNI incorrecto");

		if (pw.length() < 8)
			System.out.println("El password debe contener como minimo 8 caracteres.");

		this.pw = pw;
		this.nick = nick;
		this.admin = false;
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

	public void setAdmin() {
		this.admin = true;
	}

	@Override
	public String toString() {
		return "Registro [nick=" + nick + ", pw=" + pw + ", dni=" + dni + ", admin=" + admin + "]";
	}

	private boolean checkDNI(String DNI) {
		if (DNI.length() != 9)
			return false;
		if (!Character.isLetter(DNI.charAt(8)))
			return false;
		try {
			Integer.parseInt(DNI.substring(0, 8));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
