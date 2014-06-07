package mps.core.buchhaltung;



//TODO The whole class
public final class RechnungService implements IRechnungen {
	private static final RechnungService INSTANCE = new RechnungService();
	
	private RechnungService() {}
	
	static RechnungService getInstance() {
		return INSTANCE;
	}

	
	@Override
	public Long rechnungErstellen(int betrag,Long auftragNr) {
		Long rechnungNr = RechnungRepository.createPersistent(betrag,auftragNr);
		
		return rechnungNr;
	}
	
	public void zahlungsEingang(int zahlung, Long rechnungsnummer){
		RechnungRepository.zahlungsEingang(zahlung, rechnungsnummer);
	}
	 
	 

}