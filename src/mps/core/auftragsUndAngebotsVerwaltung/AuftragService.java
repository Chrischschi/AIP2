package mps.core.auftragsUndAngebotsVerwaltung;

//TODO The whole class
public final class AuftragService implements IAuftraege {
	private static final AuftragService INSTANCE = new AuftragService();
	
	private AuftragService() {}
	
	static AuftragService getInstance() {
		return INSTANCE;
	}

	@Override
	public Long getBauteilIdOfAutrag(Long auftragNr) {
		Auftrag auftrag = (Auftrag) AuftragRepository.readAuftrag(auftragNr);
		return auftrag.getAngebot().getBauteilNr(); //Quick and dirty
	}
	
	public Auftrag auftragErstellen(Long angebotNr) {
		
	}
	 
	 

}
