package org.hbrs.se1.ws24.tests.uebung1;
import static org.junit.jupiter.api.Assertions.*;

import org.hbrs.se1.ws24.exercises.uebung1.control.Translator;
import org.hbrs.se1.ws24.exercises.uebung1.control.TranslatorFactory;
import org.junit.jupiter.api.Test;

public class GermanTranslatorTest {
    /*
     * TODO: Factory Pattern soll angewendet werden
     * um GermanTranslator in Client zu erzeugen
     *
     * Blackbox-Testing:
     * Input{1} -> Black Box -> Output{ist, soll{1}}
     * Man bildet für die Tests Äquivalenzklassen, also
     * die verschiedenen Inputs werden gruppiert und dann pro Gruppe/Klasse
     * mind. 1 Test Case implementieren
     * z.B. Klassen: {gültige Eingaben, ungültige Eingaben}
     * damit die Tests nicht zu fein sind / redundant, man minimiert Anzahl von Testfälle
     *
     * Wenn man Oder zwischen Bedingungen macht sind das 2 Klassen
     * Randbedingungen der Klassen testen auch (zusätzlich) sinnvoll
     *
     * UML: jeweils vor Attribut/Methode + für public, - für private
     * ----------------
     *     <Klasse>
     *       Name
     * ----------------
     *    Attribute
     * ----------------
     *    Methoden
     * ----------------
     *
     * static:      unterstrichen   (umlet: _method_)
     * abstract:    italics         (umlet: /method/)
     * public:      +
     * private:     -
     * protected:   #
     * durchgezogener Pfeil mit weißem Dreieck: Vererbung
     * gestrichelter  Pfeil mit weißem Dreieck: implements
     * gestrichelter  Pfeil mit ^ als Dreieck:  benutzt, Dependency
    */

    @Test
    public void testValidTranslation() {
        Translator translator = TranslatorFactory.createGermanTranslator();
        assertEquals("zwei", translator.translateNumber(2));
    }

    @Test
    public void testLessThanValidRange() {
        Translator translator = TranslatorFactory.createGermanTranslator();
        assertNotEquals("null", translator.translateNumber(0));
    }

    @Test
    public void testGreaterThanValidRange() {
        Translator translator = TranslatorFactory.createGermanTranslator();
        assertNotEquals("zwölf", translator.translateNumber(12));
    }
}