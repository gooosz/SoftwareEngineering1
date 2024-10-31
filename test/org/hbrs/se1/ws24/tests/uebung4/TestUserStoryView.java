package org.hbrs.se1.ws24.tests.uebung4;

import org.hbrs.se1.ws24.exercises.uebung4.Container;
import org.hbrs.se1.ws24.exercises.uebung4.UserStory;
import org.hbrs.se1.ws24.exercises.uebung4.UserStoryException;
import org.hbrs.se1.ws24.exercises.uebung4.UserStoryView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class TestUserStoryView {
	private List<UserStory> liste;
	private UserStory u1;
	// for testing printing to stdout
	private final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
	private final PrintStream originalStdout = System.out;

	@BeforeEach
	public void init() throws UserStoryException {
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
		liste = new ArrayList<>();
		liste.add(u1);
	}

	@BeforeEach
	public void setStdout() {
		System.setOut(new PrintStream(stdout));
	}
	@AfterEach
	public void resetStdout() {
		System.setOut(originalStdout);
	}

	@Test
	public void testDumpAll() {
		String correct = "==== ID: 1 ====\n"
			+ "Title: title\n"
			+ "Akzeptanzkriterium: akzeptanzKriterium\n"
			+ "Projekt: projektZuordnung\n"
			+ "Mehrwert: 1\n"
			+ "Strafe: 1\n"
			+ "Risiko: 1\n"
			+ "Story Points: 1\n"
			+ "Prio: 1.0\n"
			+ "============\n";
		UserStoryView.dump(liste);
		assertEquals(correct, stdout.toString());
	}

	@Test
	public void testPredicate() {
		UserStoryView.dump(liste, _ -> false);
		//String correct = "ID | Title | Akzeptanzkriterium | Projekt | Mehrwert | Strafe | Risiko | Story Points | Priorität";
		assertEquals("", stdout.toString());
	}
}
