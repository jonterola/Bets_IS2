package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

class AddMoneyMockitoTest {
	private BLFacadeImplementation sut;
	@Mock
	private DataAccess dAccess;

	String u1DNI = "12345678T";
	String u1Name = "Jon";
	String u1Correo = "holasoyJon@gmail.com";
	String u1Pass = "buenastardes1";

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		sut = new BLFacadeImplementation(dAccess);
	}

	@Test
	void testCreateUser() {
		sut.addMoney(u1DNI, 1, false);
		ArgumentCaptor<String> userCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Float> quantityCaptor = ArgumentCaptor.forClass(Float.class);
		ArgumentCaptor<Boolean> isBoxCaptor = ArgumentCaptor.forClass(Boolean.class);

		Mockito.verify(dAccess, Mockito.times(1)).addMoney(userCaptor.capture(), quantityCaptor.capture(),
				isBoxCaptor.capture());

		assertEquals(userCaptor.getValue(), u1DNI);
		assertEquals(quantityCaptor.getValue(), 1);
		assertEquals(isBoxCaptor.getValue(), false);

	}
}