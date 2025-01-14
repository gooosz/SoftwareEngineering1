package org.hbrs.se1.ws24.tests.uebung10;

public class Stack<E> extends java.util.Stack<E> {
	private int n; // max amount of elements

	public Stack(int n) {
		super();
		this.n = n;
	}

	// pushes item into stack if elements+1 < max amount of elements in stack
	// returns null if stack is full
	public E push(E item) {
		if (super.size() < n) {
			return super.push(item);
		}
		return null;
	}
}
