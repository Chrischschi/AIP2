package mps.core.rechnung;


public interface IRechnungen {
	static IRechnungen getRechnungService() {
		return RechnungService.getInstance();
	}

	Long rechnungErstellen(int betrag, Long auftragNr);
}
