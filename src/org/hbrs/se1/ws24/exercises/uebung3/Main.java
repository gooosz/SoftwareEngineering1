package org.hbrs.se1.ws24.exercises.uebung3;

import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;

public class Main {
	public static void main(String[] args) {
		Container container = Container.getInstance();
		PersistenceStrategy<Member> strategy = new PersistenceStrategyStream<>();
		container.setPersistanceStrategy(strategy);
		Client client = new Client(container);
	}
}
