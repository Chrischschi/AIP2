package mps.core.auftragsUndAngebotsVerwaltung;


//TODO The whole class
public final class AuftragService implements IAuftraege {
	private static final AuftragService INSTANCE = new AuftragService();
	
	private AuftragService() {}
	
	static AuftragService getInstance() {
		return INSTANCE;
	}

	
	@Override
	public Auftrag auftragErstellen(String beauftragtAm,Long angebotNr) {
		Angebot angebot = AngebotRepository.getByID(angebotNr);
		Auftrag auftrag = AuftragRepository.createPersistent(false, beauftragtAm, angebot);
		
		return auftrag;
	}
	 
	 

}
