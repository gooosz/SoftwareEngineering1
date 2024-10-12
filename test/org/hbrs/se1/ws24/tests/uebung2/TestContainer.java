package org.hbrs.se1.ws24.tests.uebung2;
import static org.junit.jupiter.api.Assertions.*;

import org.hbrs.se1.ws24.exercises.uebung2.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestContainer {
    private Container container0;
    private Container container1;
    private Container container2;
    private ConcreteMember m1;
    private ConcreteMember m2;
    // for testing printing to stdout
    private final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
    private final PrintStream originalStdout = System.out;

    @BeforeEach
    public void setStdout() {
        System.setOut(new PrintStream(stdout));
    }
    @AfterEach
    public void resetStdout() {
        System.setOut(originalStdout);
    }

    @BeforeEach
    public void init() {
        MemberFactory mfactory = new MemberFactory();
        m1 = mfactory.createConcreteMember();
        m2 = mfactory.createConcreteMember();

        /* For TestNoMemberInList */
        container0 = new Container();
        /* For TestOneMemberInList */
        container1 = new Container();
        container1.addMember(m1);
        /* For TestTwoMemberInList */
        container2 = new Container();
        container2.addMember(m1);
        container2.addMember(m2);
    }

    // Zustandsdiagramm vom Container und daraus Testfälle, Äquivalenzklassen in Excel schreiben

    @Test
    public void testNoMemberInList() {
        assertEquals(0, container0.size());
        container0.dump();
        assertEquals("", stdout.toString());
        assertDoesNotThrow(() -> container0.addMember(m1));
        assertEquals(1, container0.size());
    }

    @Test
    public void testOneMemberInList() {
        assertEquals(1, container1.size());
        container1.dump();
        assertEquals("Member (ID = 1)\n", stdout.toString());

        assertThrows(ContainerException.class, () -> container1.addMember(m1));
        assertEquals(1, container1.size());

        assertNotEquals("success", container1.deleteMember(2));
        assertEquals("success", container1.deleteMember(1));
        assertEquals(0, container1.size());

        assertDoesNotThrow(() -> container1.addMember(m2));
        assertEquals(1, container1.size());
    }

    @Test
    public void testTwoMemberInList() {
        assertEquals(2, container2.size());
        container2.dump();
        assertEquals("Member (ID = 1)\nMember (ID = 2)\n", stdout.toString());

        assertThrows(ContainerException.class, () -> container2.addMember(m2));
        assertEquals(2, container2.size());
        assertNotEquals("success", container2.deleteMember(99));

        assertEquals("success", container2.deleteMember(2));
        assertEquals(1, container2.size());
    }
}
