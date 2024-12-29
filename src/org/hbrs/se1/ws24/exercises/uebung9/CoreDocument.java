package org.hbrs.se1.ws24.exercises.uebung9;

public abstract class CoreDocument implements Document {
	private Integer id;

	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}

	@Override
	public abstract Integer getGroesse();
}
