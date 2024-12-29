package org.hbrs.se1.ws24.exercises.uebung9;

public class Buchung {
	private String id;
	private String reiseZiel;
	private String datum;

	public Buchung(String id, String reiseZiel, String datum) {
		this.id = id;
		this.reiseZiel = reiseZiel;
		this.datum = datum;
	}

	public String getID() {
		return id;
	}

	public String getReiseZiel() {
		return reiseZiel;
	}

	public String getDatum() {
		return datum;
	}
}
