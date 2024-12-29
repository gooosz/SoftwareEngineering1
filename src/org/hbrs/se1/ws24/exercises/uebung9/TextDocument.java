package org.hbrs.se1.ws24.exercises.uebung9;

public class TextDocument extends CoreDocument{
	private String inhalt;
	private Encoding enc;

	public enum Encoding {
		UTF8,
		UTF32,
		UTF16
	}

	public TextDocument(String inhalt, Encoding enc) {
		this.inhalt = inhalt;
		this.enc = enc;
	}

	/**
	 * @return amount of Bytes needed to store the inhalt in the given encoding
	 */
	@Override
	public Integer getGroesse() {
		Integer bytesPerChar = switch (enc) {
			case Encoding.UTF8 -> 1;
			case Encoding.UTF16 -> 2;
			case Encoding.UTF32 -> 4;
		};
		return inhalt.length() * bytesPerChar;
	}
}
