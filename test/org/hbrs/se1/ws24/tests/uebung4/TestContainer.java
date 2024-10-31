package org.hbrs.se1.ws24.tests.uebung4;


import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws24.exercises.uebung4.Container;
import org.hbrs.se1.ws24.exercises.uebung4.UserStory;
import org.hbrs.se1.ws24.exercises.uebung4.UserStoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestContainer {
	private Container<UserStory> container;
	private UserStory u1;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void init() throws UserStoryException {
		container = (Container<UserStory>) Container.createNotSingleton();
		u1 = new UserStory(
			1,
			"title",
			"akzeptanzKriterium",
			"projektZuordnung",
			1,
			1,
			1,
			1
		);
	}

	@Test
	public void testNoPersistanceStrategySet() {
		container.addMember(u1);
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
		PersistenceStrategyStream<UserStory> strategy = new PersistenceStrategyStream<>();
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
		PersistenceStrategyStream<UserStory> strategy = new PersistenceStrategyStream<>();
		container.setPersistanceStrategy(strategy);
		/*
		 * Round Trip:
		 * - add Object + asserts
		 * - store Container persistently + asserts
		 * - delete Object from Container + asserts
		 * - load Container + asserts
		*/
		assertDoesNotThrow(() -> container.addMember(u1));
		assertEquals(1, container.size());

		assertDoesNotThrow(() -> container.store());

		assertTrue(container.deleteMember(u1.getID()));
		assertEquals(0, container.size());

		assertDoesNotThrow(() -> container.load());
		assertEquals(1, container.size());
	}
}
