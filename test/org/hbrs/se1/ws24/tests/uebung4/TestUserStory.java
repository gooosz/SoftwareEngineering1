package org.hbrs.se1.ws24.tests.uebung4;

import org.hbrs.se1.ws24.exercises.uebung4.UserStory;
import org.hbrs.se1.ws24.exercises.uebung4.UserStoryException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserStory {
	private UserStory u;

	@BeforeEach
	public void init() throws UserStoryException {
		u = new UserStory(
			1,
			"title",
			"akzeptanzKriterium",
			"projektZuordnung",
			1,
			1,
			2,
			2
		);
	}

	@Test
	public void testConstructorCorrectValuesForPriority() {
		assertDoesNotThrow(() -> u = new UserStory(
			1,
			"title",
			"akzeptanzKriterium",
			"projektZuordnung",
			1,
			1,
			1,
			1
		));
	}

	@Test
	public void testConstructorThrowsWrongValuesForPriority() {
		assertThrows(UserStoryException.class, () -> u = new UserStory(
			1,
			"title",
			"akzeptanzKriterium",
			"projektZuordnung",
			-3,
			0,
			6,
			-5
		));
	}

	@Test
	public void testCalculatePriority() {
		// priority gets calculated in constructor so test correct value via getter of prio
		assertEquals(0.5, u.getPrio());
	}
}
