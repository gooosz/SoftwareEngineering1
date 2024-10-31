package org.hbrs.se1.ws24.tests.uebung4;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws24.exercises.uebung4.UserStory;
import org.hbrs.se1.ws24.exercises.uebung4.Container;
import org.hbrs.se1.ws24.exercises.uebung4.UserStoryException;
import org.hbrs.se1.ws24.exercises.uebung4.UserStoryView;

import java.util.Scanner;

public class Main {
	private static Container<UserStory> container;
	private static PersistenceStrategyStream<UserStory> strategyStream;
	private static Scanner stdin;
	private static int nextUserStoryID;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws PersistenceException, UserStoryException {
		container = (Container<UserStory>) Container.getInstance();
		strategyStream = new PersistenceStrategyStream<>();
		container.setPersistanceStrategy(strategyStream);
		// load container members from last program execution
		container.load();

		nextUserStoryID = 1;	// free id for next user story

		stdin = new Scanner(System.in);
		boolean isRunning = true;
		System.out.println("ManUs - Manage User Stories");
		System.out.println("Enter command...");
		while (isRunning) {
			String befehl = stdin.nextLine();
			switch (befehl) {
				case "enter" -> {
					UserStory story = createUserStoryFromInput();
					container.addMember(story);
				}
				case "delete" -> {
					Integer id = readIDFromInput();
					container.deleteMember(id);
				}
				case "store" -> {
					container.store();
				}
				case "load" -> {
					container.load();
				}
				case "dump" -> {
					UserStoryView.dump(container.getCurrentList());
				}
				case "exit" -> {
					// store persistently
					// exist
					container.store();
					isRunning = false;
				}
				default -> printHelp();
			}
		}
	}

	public static UserStory createUserStoryFromInput() throws UserStoryException {
		System.out.print("Title: ");
		String title = stdin.nextLine();

		System.out.print("Akzeptanzkriterium: ");
		String akzeptanzKriterium = stdin.nextLine();

		System.out.print("Projekt zugeordnet: ");
		String projektZuordnung = stdin.nextLine();

		int value;
		do {
			System.out.print("Mehrwert 1-5: ");
			// this way the \n at end of line gets consumed aswell (wouldnt be the case if nextInt is used)
			String line = stdin.nextLine();
			try {
				value = Integer.parseInt(line);
			} catch (NumberFormatException _) {
				// try again in next iteration
				value = -1;
			}
		} while (!(1 <= value && value <= 5));	// keep trying until input is in correct range

		int strafe;
		do {
			System.out.print("Strafe 1-5: ");
			String line = stdin.nextLine();
			try {
				strafe = Integer.parseInt(line);
			} catch (NumberFormatException _) {
				// try again in next iteration
				strafe = -1;
			}
		} while (!(1 <= strafe && strafe <= 5));	// keep trying until input is in correct range

		int risiko;	// default value
		do {
			System.out.print("Risiko 1-5: ");
			String line = stdin.nextLine();
			try {
				risiko = Integer.parseInt(line);
			} catch (NumberFormatException _) {
				// try again in next iteration
				risiko = -1;
			}
		} while (!(1 <= risiko && risiko <= 5));	// keep trying until input is in correct range

		int storyPoints;
		do {
			System.out.print("Story Points: ");
			String line = stdin.nextLine();
			try {
				storyPoints = Integer.parseInt(line);
			} catch (NumberFormatException _) {
				// try again in next iteration
				storyPoints = -1;
			}
		} while (storyPoints < 0);	// keep trying until input is in correct range

		return new UserStory(
			nextUserStoryID++,
			title,
			akzeptanzKriterium,
			projektZuordnung,
			value,
			strafe,
			risiko,
			storyPoints);
	}

	public static Integer readIDFromInput() {
		int id;
		do {
			System.out.print("ID: ");
			String line = stdin.nextLine();
			try {
				id = Integer.parseInt(line);
			} catch (NumberFormatException _) {
				// try again in next iteration
				id = -1;
			}
		} while (id < 0);	// keep trying until input is in correct range
		return id;
	}

	public static void printHelp() {
		System.out.print(
			"""
			Available commands:
			enter - add a new user story
			delete - delete a user story
			store - store user stories persistently
			load - load user stories from storage
			dump - print all user stories as a table
			exit - exit application
			help - print this help menu
			"""
		);
	}
}
