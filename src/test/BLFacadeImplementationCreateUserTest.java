package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

class BLFacadeImplementationCreateUserTest {
	private BLFacadeImplementation sut;
	@Mock
	private DataAccess dAccess;

	private String dniBueno = "12345678A";
	private String userBueno = "alejo43";
	private String mailBueno = "alj2@gmail.com";
	private String pwd = "aaaaaaa";
	private int edad = 22;
	private String giftConDinero = "regalo1";
	private int dinero = 15;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		sut = new BLFacadeImplementation(dAccess);
	}

	@Test
	void testCreateUser() {
		Mockito.doReturn(0).when(dAccess).addUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyInt());
		int obtained = sut.createUser(dniBueno, userBueno, mailBueno, pwd, edad, giftConDinero, dinero);
		assertEquals(0, obtained);
	}

}
