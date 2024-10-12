package org.hbrs.se1.ws24.exercises.uebung2;

public class MemberFactory {
	/*
	 * Needed to store the state of free IDs
	 * because it is disallowed to set the ID of Member in Container class
	 * and static variable in ConcreteMember is bad to test against
	*/

	private int nextFreeID;
	public MemberFactory() {
		nextFreeID = 1;
	}

	public ConcreteMember createConcreteMember() {
		ConcreteMember m =  new ConcreteMember(nextFreeID);
		nextFreeID++;
		return m;
	}
}
