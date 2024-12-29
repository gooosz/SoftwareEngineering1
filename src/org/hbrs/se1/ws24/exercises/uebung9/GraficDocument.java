package org.hbrs.se1.ws24.exercises.uebung9;

public class GraficDocument extends CoreDocument{
	private String url;

	public GraficDocument(String url) {
		this.url = url;
	}

	@Override
	public Integer getGroesse() {
		// constant size
		return 1200;
	}
}
