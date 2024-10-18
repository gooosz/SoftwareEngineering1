package org.hbrs.se1.ws24.exercises.uebung3;

import org.hbrs.se1.ws24.exercises.uebung2.Member;

import java.util.List;

public class MemberView {
	/**
	 * prints all Members to stdout
	 */
	public static void dump(List<Member> liste) {
		liste.forEach(System.out::println);
	}
}
