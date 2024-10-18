package org.hbrs.se1.ws24.tests.uebung3;

import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.hbrs.se1.ws24.exercises.uebung2.MemberFactory;
import org.hbrs.se1.ws24.exercises.uebung3.Container;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestContainer {
	private Container container;
	private Member m1;

	@BeforeEach
	public void init() {
		container = Container.createNotSingleton();
		MemberFactory factory = new MemberFactory();
		m1 = factory.createConcreteMember();
	}

	@Test
	public void testNoPersistanceStrategySet() {
		container.addMember(m1);
		assertThrows(PersistenceException.class, () -> container.store());
		assertThrows(PersistenceException.class, () -> container.load());
		try {
			container.store();
		} catch (PersistenceException e) {
			assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet, e.getExceptionTypeType());
		}
	}

	@Test
	public void testPeristanceStrategyMongoDBNotImplementedYet() {
		container.setPersistanceStrategy(new PersistenceStrategyMongoDB<>());
		assertThrows(PersistenceException.class, () -> container.store());
		assertThrows(PersistenceException.class, () -> container.load());
		try {
			container.store();
		} catch (PersistenceException e) {
			assertEquals(PersistenceException.ExceptionType.ImplementationNotAvailable, e.getExceptionTypeType());
		}
	}

	@Test
	public void testPersistanceStreamWrongDirectory() {
		PersistenceStrategyStream<Member> strategy = new PersistenceStrategyStream<>();
		strategy.setLocation("/");	// directory
		container.setPersistanceStrategy(strategy);
		assertThrows(PersistenceException.class, () -> container.store());
		assertThrows(PersistenceException.class, () -> container.load());
		try {
			container.store();
		} catch (PersistenceException e) {
			assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, e.getExceptionTypeType());
		}
	}

	@Test
	public void testRoundTrip() {
		PersistenceStrategyStream<Member> strategy = new PersistenceStrategyStream<>();
		container.setPersistanceStrategy(strategy);
		/*
		 * Round Trip:
		 * - add Object + asserts
		 * - store Container persistently + asserts
		 * - delete Object from Container + asserts
		 * - load Container + asserts
		*/
		assertDoesNotThrow(() -> container.addMember(m1));
		assertEquals(1, container.size());

		assertDoesNotThrow(() -> container.store());

		assertEquals("success", container.deleteMember(m1.getID()));
		assertEquals(0, container.size());

		assertDoesNotThrow(() -> container.load());
		assertEquals(1, container.size());
	}
}
