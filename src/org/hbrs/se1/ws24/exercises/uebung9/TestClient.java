package org.hbrs.se1.ws24.exercises.uebung9;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

public class TestClient {
	public static void main(String[] args) {
		Integer nextID = 1;
		GraficDocument g = new GraficDocument("TestGrafic");
		g.setID(nextID++);
		TextDocument t_utf8 = new TextDocument("hello", TextDocument.Encoding.UTF8);
		t_utf8.setID(nextID++);
		TextDocument t_utf16 = new TextDocument("hello", TextDocument.Encoding.UTF16);
		t_utf16.setID(nextID++);
		TextDocument t_utf32 = new TextDocument("hello", TextDocument.Encoding.UTF32);
		t_utf32.setID(nextID++);

		// Groeße: 1205 Bytes
		ComplexDocument c1 = new ComplexDocument();
		c1.addDocument(g);
		c1.addDocument(t_utf8);

		// Groeße: 1235 Bytes
		ComplexDocument c2 = new ComplexDocument();
		c2.addDocument(c1);
		c2.addDocument(t_utf16);
		c2.addDocument(t_utf32);

		assert(c1.getGroesse() == 1205);
		assert(c2.getGroesse() == 1235);
	}
}
