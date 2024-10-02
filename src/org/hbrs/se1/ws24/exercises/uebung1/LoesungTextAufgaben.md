# Meine Antworten auf die Text-Fragen in Übung 1

## 1)

### a)
Die Factory Klasse sollte im gleichen Package wie GermanTranslator liegen
weil die Factory diese Instantiiert und somit logisch zu GermanTranslator dazugehört.
Das ist aber nicht zwingend notwendig, also die Factory kann auch in einem anderen Package liegen
muss dann aber das Package, wo GermanTranslator liegt, importieren

### b)
Durch das Factory-Pattern werden ähnliche Klassen (Bspw. alle Klassen die dasselbe Interface implementieren)
nur in einer Klasse (in verschiedenen Methoden darin) erstellt.
Das hat den VOrteil, dass falls ein neues Objekt nicht nur durch new erzeugt werden muss sondern
auch weitere Sachen erfolgen müssen (zu Datenbank hinzufüge, Attribute setzen, etc.), diese in einer Methode gekapselt sind
sodass das Erstellen eines Objekt mit allen Sachen nur durch einen simplen Methodenaufruf erfolgt

### c)
Das Interface muss public gemacht werden, damit die benutzte Methode des Interfaces Translator (in GermanTranslator implementiert)
auch in der Test Klasse, die in einem anderen Package liegt, aufgerufen werden kann


## 3)

### a)
Wenn die Test Klasse von der getesteten Klasse getrennt ist, hat man Code und Anwendung (Test)
sauber getrennt, sodass die Test Klasse wirklich nur die Klasse testet
und die Klasse nur den funktionalen Code enthält.

### b)
Man benutzt Äquivalenzklassen zum Testen damit die Testfälle nach Ähnlichkeit/Äquivalenz gruppiert werden
und somit pro tatsächlich unterschiedlichem Testfall nur mind. ein Test geschrieben werden muss
und nicht pro jeden auftretenden Fall, dadurch minimiert man die Anzahl der benötigten Testfälle

### c)
Um zu testen, ob der Client.display() die korrekte Ausgabe (ein String) erzeugt,
muss man sich anschauen welche verschiedenen Strings erzeugt werden könnten von der Methode,
weil es könnte sein, dass evtl. eine zusätzliche Erklärung mit der Translation ausgegeben wird.
Man muss also um die Methode auf korrekte Ausgabe (Äquivalenz zu einer erwarteten Ausgabe) zu testen
in die Implementierung der Methode schauen.
Dadurch ist das kein Black Box Test mehr, weil man schaut was in der Black Box vorgeht