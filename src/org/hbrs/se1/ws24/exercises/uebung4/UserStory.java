package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung2.Member;

public class UserStory implements Member {
	private Integer id;
	private String title;
	private String akzeptanzKriterium;	// definition of done
	private String projektZuordnung;
	/*
	 * Kennzahlen für Berechnung von Priorisierung nach Gloger
	 * Priorisierung = (mehrwert + strafe) / (aufwand + risiko)
	*/
	private int value;		// relativer Mehrwert 1-5
	private int relStrafe;		// relative Strafe 1-5
	private int relRisiko;		// relatives Risiko 1-5
	private int storyPoints;	// relativer Aufwand beliebig (evtl. Fibonacci nr)
	private double prio;		// berechnete Priorität, je höher desto wichtiger

	public UserStory(Integer id,
			 String title,
			 String akzeptanzKriterium,
			 String projektZuordnung,
			 int value,
			 int relStrafe,
			 int relRisiko,
			 int storyPoints
			 ) throws UserStoryException {
		this.id = id;
		this.title = title;
		this.akzeptanzKriterium = akzeptanzKriterium;
		this.projektZuordnung = projektZuordnung;
		// Check if Kennzahlen für Priorisierung in akzeptabler Range sind, wenn nicht throw Exception
		if (	   !(1 <= value && value <= 5)
			|| !(1 <= relStrafe && relStrafe <= 5)
			|| !(1 <= relRisiko && relRisiko <= 5)
			|| !(storyPoints >= 0)
		) {
			throw new UserStoryException(
				UserStoryException.ExceptionType.WrongPriorityValue,
				"Kennzahlen für Berechnung von Prioritaetswert liegen in nicht akzeptablem Bereich");
		}
		this.value = value;
		this.relStrafe = relStrafe;
		this.relRisiko = relRisiko;
		this.storyPoints = storyPoints;
		this.prio = calculatePriority();
	}

	@Override
	public Integer getID() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getAkzeptanzKriterium() {
		return akzeptanzKriterium;
	}
	public String getProjektZuordnung() {
		return projektZuordnung;
	}
	/**
	 * @return Wert für Mehrwert
	 */
	public int getValue() {
		return value;
	}
	public int getStrafe() {
		return relStrafe;
	}
	public int getRisiko() {
		return relRisiko;
	}
	public int getStoryPoints() {
		return storyPoints;
	}
	public double getPrio() {
		return prio;
	}

	/**
	 * calculates and sets the prio attribute
	 * Priorisierung = (mehrwert + strafe) / (aufwand + risiko)
	 */
	private double calculatePriority()
	{
		return (double) (value + relStrafe) / (storyPoints + relRisiko);
	}

	@Override
	public String toString() {
		// print all relevant info of User Story
		return "==== ID: " + getID() + " ====\n"
			+ "Title: " + getTitle() + '\n'
			+ "Akzeptanzkriterium: " + getAkzeptanzKriterium() + '\n'
			+ "Projekt: " + getProjektZuordnung() + '\n'
			+ "Mehrwert: " + getValue() + '\n'
			+ "Strafe: " + getStrafe() + '\n'
			+ "Risiko: " + getRisiko() + '\n'
			+ "Story Points: " + getStoryPoints() + '\n'
			+ "Prio: " + getPrio() + '\n'
			+ "============\n";
	}
}
