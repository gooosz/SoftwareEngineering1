package org.hbrs.se1.ws24.exercises.uebung9;

import java.util.ArrayList;
import java.util.List;

public class ComplexDocument implements Document{
	private List<Document> docs;

	public ComplexDocument() {
		docs = new ArrayList<>();
	}

	public void addDocument(Document d)  {
		docs.add(d);
	}

	public void removeDocument(Document d)  {
		docs.remove(d);
	}

	@Override
	public Integer getGroesse() {
		// iterate over documents
		Integer sum = 0;
		for (Document d : docs) {
			sum += d.getGroesse();
		}
		return sum;
	}
}
