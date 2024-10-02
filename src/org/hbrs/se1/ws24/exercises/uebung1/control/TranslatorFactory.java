package org.hbrs.se1.ws24.exercises.uebung1.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TranslatorFactory {
    /* Factory erzeugt verschiedene Translators */

    public static GermanTranslator createGermanTranslator() {
        GermanTranslator t = new GermanTranslator();
        // for setting the date of creation of translator in format "MMMM/yyyy"
        // with month string representation of the month abbreviated to 3 letters
        LocalDate today = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("LLL/yyyy");
        t.setDate(today.format(format));
        return t;
    }
}
