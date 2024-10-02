package org.hbrs.se1.ws24.exercises.uebung1.view;

import org.hbrs.se1.ws24.exercises.uebung1.control.Translator;
import org.hbrs.se1.ws24.exercises.uebung1.control.TranslatorFactory;

public class Client {

		/**
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		 void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!
			 // String var1 = translator.translateNumber(num)
			 Translator translator = TranslatorFactory.createGermanTranslator();
			 String translatedNumber = translator.translateNumber(aNumber);
			 System.out.println("Das Ergebnis der Berechnung: " +
					 translatedNumber +
					 "[das Ergebnis an dieser Stelle]" );
		 }
}





