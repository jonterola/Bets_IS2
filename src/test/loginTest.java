package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;

public class loginTest {

	private static DataAccess db;

	@BeforeAll
	public static void setup() {
		db = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		db.addUser("79133379Q", "andergo", "agomez302@ikasle.ehu.eus", "contrasegura", 21, "", 0);
	}

	@Test
	void test1() {
		assertNull(db.login("agomez302@ikasle.ehu.eu", "contrasegura"));
	}

	@Test
	void test2() {
		assertNull(db.login("agomez302@ikasle.ehu.eus", "contrasegur"));

	}

	@Test
	void test3() {
		assertNotNull(db.login("agomez302@ikasle.ehu.eus", "contrasegura"));
	}
}
