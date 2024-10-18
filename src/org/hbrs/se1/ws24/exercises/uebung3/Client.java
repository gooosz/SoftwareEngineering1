package org.hbrs.se1.ws24.exercises.uebung3;

import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.hbrs.se1.ws24.exercises.uebung2.MemberFactory;

import java.util.ArrayList;
import java.util.List;

public class Client {
	private Container container;
	private List<Member> mlist;
	// Dependency injection
	public Client(Container container) {
		this.container = container;
		// Create some Member objects and add to Container
		MemberFactory factory = new MemberFactory();
		mlist = new ArrayList<>();
		for (int i=0; i<3; i++) {
			container.addMember(factory.createConcreteMember());
		}
		List<Member> currentMembers = container.getCurrentList();
		// Ausgabe
		MemberView.dump(currentMembers);

	}
}
