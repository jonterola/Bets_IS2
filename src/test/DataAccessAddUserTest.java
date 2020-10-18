package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Registro;
import exceptions.UserDoesntExist;

class DataAccessAddUserTest {
	private static DataAccess sut;

	private String dniMalo = "11111111A";
	private String dniBueno = "12345678A";
	private String userMalo = "alejo";
	private String userBueno = "alejo43";
	private String mailMalo = "alj@gmail.com";
	private String mailBueno = "alj2@gmail.com";
	private String pwd = "aaaaaaa";
	private int edad = 22;
	private String giftConDinero = "regalo1";
	private int dinero = 15;

	@BeforeAll
	public static void init() {
		sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	}

	@Test
	@DisplayName("El Mail esta en la base de datos")
	void testAddUser1() {
		sut.addUser(dniBueno, userBueno, mailMalo, pwd, edad, giftConDinero, dinero);
		int obtained = sut.addUser(dniBueno, userBueno, mailMalo, pwd, edad, giftConDinero, dinero);
		int expected = 1;
		assertEquals(expected, obtained);
		sut.removeUser(dniBueno);
	}

	@Test
	@DisplayName("El Usuario  esta en la base de datos")
	void testAddUser2() {
		sut.addUser(dniBueno, userMalo, mailMalo, pwd, edad, giftConDinero, dinero);
		int obtained = sut.addUser(dniBueno, userMalo, mailBueno, pwd, edad, giftConDinero, dinero);
		int expected = 2;
		assertEquals(expected, obtained);
		sut.removeUser(dniBueno);
	}

	@Test
	@DisplayName("El dni esta en la base de datos")
	void testAddUser3() {
		sut.addUser(dniMalo, userMalo, mailMalo, pwd, edad, giftConDinero, dinero);
		int obtained = sut.addUser(dniMalo, userBueno, mailBueno, pwd, edad, giftConDinero, dinero);
		int expected = 3;
		assertEquals(expected, obtained);
		sut.removeUser(dniMalo);
	}

	@Test
	@DisplayName("Se crea el user con saldo en el monedero y sin promocion")
	void testAddUser4() {
		int obtained = sut.addUser(dniBueno, userBueno, mailBueno, pwd, edad, "", dinero);
		int expected = 0;
		assertEquals(expected, obtained);
		Registro r = null;
		try {
			r = sut.getUser(dniBueno);
		} catch (UserDoesntExist e) {
			fail();
		}
		assertEquals(r.getSaldo(), dinero);
		sut.removeUser(dniBueno);
	}

	@Test
	@DisplayName("Se crea el user sin saldo en el monedero y sin promocion")
	void testAddUser5() {
		int obtained = sut.addUser(dniBueno, userBueno, mailBueno, pwd, edad, "", 0);
		int expected = 0;
		assertEquals(expected, obtained);
		Registro r = null;
		try {
			r = sut.getUser(dniBueno);
		} catch (UserDoesntExist e) {
			fail();
		}
		assertEquals(r.getSaldo(), 0);
		sut.removeUser(dniBueno);
	}

	@Test
	@DisplayName("Se crea el user sin saldo en el monedero y con promocion")
	void testAddUser6() {
		sut.addGift(giftConDinero, 10);
		int obtained = sut.addUser(dniBueno, userBueno, mailBueno, pwd, edad, giftConDinero, 0);
		int expected = 0;
		assertEquals(expected, obtained);
		Registro r = null;
		try {
			r = sut.getUser(dniBueno);
		} catch (UserDoesntExist e) {
			fail();
		}
		assertEquals(10, r.getSaldo());
		sut.removeUser(dniBueno);
		sut.removeGift(giftConDinero);
	}

	@Test
	@DisplayName("Se crea el user con saldo en el monedero y con promocion")
	void testAddUser7() {
		sut.addGift(giftConDinero, 10);
		int obtained = sut.addUser(dniBueno, userBueno, mailBueno, pwd, edad, giftConDinero, dinero);
		int expected = 0;
		assertEquals(expected, obtained);
		Registro r = null;
		try {
			r = sut.getUser(dniBueno);
		} catch (UserDoesntExist e) {
			fail();
		}
		assertEquals(10 + dinero, r.getSaldo());
		sut.removeUser(dniBueno);
		sut.removeGift(giftConDinero);

	}
}
