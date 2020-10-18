package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Registro;
import exceptions.UserDoesntExist;

class DataAccessAddMoneyTest {
	private static DataAccess sut;
	String u1DNI = "12345678T";
	String u1Name = "Jon";
	String u1Correo = "holasoyJon@gmail.com";
	String u1Pass = "buenastardes1";

	@BeforeAll
	public static void init() {
		sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	}

	@Test
	@DisplayName("IngresoPorCajaSorpresaTest1")
	public void addMoneyIsBoxTest() {
		sut.addUser(u1DNI, u1Name, u1Correo, u1Pass, 22, "", 0);
		sut.addMoney(u1DNI, 1, true);
		Registro user = null;
		try {
			user = sut.getUser(u1DNI);
		} catch (UserDoesntExist e) {
			fail("Imposible!");
		} finally {
			float obtained = user.getSaldo();
			assertEquals(1, obtained);
			int expectedLastBox = 2020;
			// En java el Year del tipo Date se expresa como la diferencia entre el año
			// escogido y 1900
			assertEquals(expectedLastBox, 1900 + user.getLastBox().getYear());
			sut.removeUser(u1DNI);
		}
	}

	@Test
	@DisplayName("IngresoPropioTest2")
	public void addMoneyIsNotBoxTest() {
		sut.addUser(u1DNI, u1Name, u1Correo, u1Pass, 22, "", 0);
		sut.addMoney(u1DNI, 1, false);
		Registro user = null;
		try {
			user = sut.getUser(u1DNI);
		} catch (UserDoesntExist e) {
			fail("Imposible!");
		} finally {
			float obtained = user.getSaldo();
			assertEquals(1, obtained);
			assertNotNull(sut.getTransactions().get(0));
			sut.removeUser(u1DNI);
		}
	}

	@Test
	@DisplayName("DNINotInDBTest3")
	public void addMoneyDNINotInDB() {
		u1DNI = "11111111A";

		assertThrows(RuntimeException.class, () -> sut.addMoney(u1DNI, 1, false));

	}

	@Test
	@DisplayName("NegativeQuantityTest4")
	public void addMoneyQuantityNegative() {

		assertThrows(RuntimeException.class, () -> sut.addMoney(u1DNI, -1, false));

	}

}