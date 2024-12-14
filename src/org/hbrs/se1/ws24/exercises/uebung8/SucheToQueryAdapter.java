package org.bonn.se1.al

import org.bonn.se1.externe.reise.*;

class SucheToQueryAdapter implements HotelSuche {
	private Reiseanbieter r;

	private QueryObject suchAuftragToQueryObject(SuchAuftrag s) {
		return new QueryObject();
	}

	private SuchErgebnis queryResultToSuchErgebnis(QueryResult q) {
		return new SuchErgebnis();
	}

	public SuchErgebnis sucheHotels(SuchAuftrag s) {
		QueryObject queryObject = suchAuftragToQueryObject(s);
		QueryResult queryResult = r.executeQuery(queryObject);
		return queryResultToSuchErgebnis(queryResult);
	}
}
