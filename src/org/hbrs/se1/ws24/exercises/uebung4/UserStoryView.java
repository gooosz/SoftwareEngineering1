package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung2.Member;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class UserStoryView {
	/**
	 * prints UserStory in List to stdout if predicate is true
	 *
	 * ID | Title | Akzeptanzkriterium | Projekt | Mehrwert | Strafe | Risiko | Story Points | Priorität
	 *
	 *
	 */
	public static void dump(List<UserStory> liste, Predicate<UserStory> predicate) {
		liste.stream()
		     .filter(predicate)
		     .forEach(System.out::print);
	}

	/**
	 * prints all User Stories to stdout
	 */
	public static void dump(List<UserStory> liste) {
		dump(liste, _ -> true);
	}
}
