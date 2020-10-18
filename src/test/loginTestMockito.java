package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Registro;

public class loginTestMockito {
	@Mock
	DataAccess dataAccess = Mockito.mock(DataAccess.class);
	BLFacade sut;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		sut = new BLFacadeImplementation(dataAccess);
	}

	@Test
	void test1() {
		Mockito.doReturn(null).when(dataAccess).login("agomez302@ikasle.ehu.eu", "contrasegura");
		assertNull(sut.newLogin("agomez302@ikasle.ehu.eu", "contrasegura"));
	}

	@Test
	void test2() {
		Mockito.doReturn(null).when(dataAccess).login("agomez302@ikasle.ehu.eus", "contrasegur");
		assertNull(sut.newLogin("agomez302@ikasle.ehu.eus", "contrasegur"));

	}

	@Test
	void test3() {
		// Al usar la version de mockito 1.9.5 da un error de illegal reflective access,
		// este error se soluciona usando la ultima version (3.5.13)
		Registro u = new Registro("andergomez", "contrasegura", "79133379Q", "agomez302@ikasle.ehu.eus", 21);
		Mockito.doReturn(u).when(dataAccess).login("agomez302@ikasle.ehu.eus", "contrasegura");
		assertNotNull(sut.newLogin("agomez302@ikasle.ehu.eus", "contrasegura"));
	}
}
