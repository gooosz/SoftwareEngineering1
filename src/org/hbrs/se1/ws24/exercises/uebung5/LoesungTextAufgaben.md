# 2)

## a)

- small ist nicht erfüllt wegen dem "und" in der Beschreibung der User Story

Besser: <br/>
User Story 1:
Als Student möchte ich nach Projektausschreibungen suchen können <br/>
Akzeptanz: <br/>
- Alle Projektausschreibungen anzeigen die das gesuchtes Wort enthalten <br/>
- Suche nach Skills in Projektausschreibungen <br/>
- Suche nach Projektausschreibungen von bestimmten Unternehmen 

User Story 2:
Als Student möchte ich mich auf Projektausschreibungen bewerben können <br/>
Akzeptanz: <br/>
- System fragt Bewerbungsunterlagen ab <br/>
- Unternehmen bekommt Bewerbungsunterlagen zugeschickt



## c)

Basis Ereignisfluss: Bewerbung auf Projektausschreibung <br/>
Vorbedingung: <br/>
- Student ist im System eingeloggt <br/>
- Projektausschreibung existiert

1. Student drückt auf "Jetzt bewerben" Button
2. SYSTEM fordert Benutzerdaten im SIS an
3. SYSTEM bereitet Dokument vor, das die empfangenen Benutzerdaten enthält
4. SYSTEM sucht Kontaktdaten des Unternehmens für Bewerbung heraus
5. SYSTEM schickt Bewerberdokument an Unternehmen
6. Student bekommt Dialog "Erfolgreiche Bewerbung" angezeigt

Nachbedingung: <br/>
- Unternehmen hat Bewerbung erhalten <br/>
- Student hat Projektausschreibung in Liste "bereits beworben" stehen<br/>

Alternativer Ereignisfluss: Fehlerhafte Stammdaten <br/>
Vorbedingung: <br/>
- Student hat auf "Jetzt bewerben" Button gedrückt <br/>
- SYSTEM hat fehlerhafte Stammdaten erhalten

1. Markiere falsche Stammdaten rot
2. Zeige Student die falschen Stammdaten
3. Zeige Student Dialog "Bewerbung fehlerhaft, fehlerhafte Stammdaten"
4. Fordere Student auf fehlerhafte Daten zu aktualisieren
5. Schließe Bewerbungsdialog mit Fehlermeldung

Nachbedingung: <br/>
- Bewerbung wurde nicht abgeschickt <br/>
- Student muss fehlerhafte Stammdaten verbessern <br/>

