package org.hbrs.se1.ws24.tests.uebung10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
	private Stack<Integer> stack;

	@BeforeEach
	public void init() {
		stack = new Stack<>(4);
	}

	@Test
	public void testEmpty() {
		assertTrue(stack.empty());
	}

	@Test
	public void testFilled() {
		// Testfall 1: Check auf leeren stack
		assertTrue(stack.empty(), "Testfall 1");

		stack.push(1);
		stack.push(2);
		stack.push(3);

		// Testfall 2: Check ob elemente hinzugefügt wurden
		assertEquals(3, stack.size(), "Testfall 2");
	}

	@Test
	public void testFull() {
		// Testfall 1: Check auf leeren stack
		assertTrue(stack.empty(), "Testfall 1");

		assertNotNull(stack.push(1));
		assertNotNull(stack.push(2));
		assertNotNull(stack.push(3));
		assertNotNull(stack.push(4));

		// Testfall 2: Check ob elemente hinzugefügt wurden
		assertEquals(4, stack.size(), "Testfall 2");

		// Testfall 3: Check ob push fehlschlägt
		assertNull(stack.push(5), "Testfall 3");
		assertEquals(4, stack.size());
	}
}
