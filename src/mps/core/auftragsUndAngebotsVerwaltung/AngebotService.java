package mps.core.auftragsUndAngebotsVerwaltung;

//Enum singleton pattern
public final class AngebotService implements IAngebote{
	
	private static final AngebotService INSTANCE = new AngebotService();
	
	private AngebotService() {}
	
	static AngebotService getInstance() {
		return INSTANCE;
	}
	
	//SystemOperation
	public Angebot angebotErstellen(Long kundenNr,String gueltigAb, String gueltigBis, Integer preis , Long bauteilNr) {
		return AngebotRepository.createPersistent(gueltigAb, gueltigBis, preis, bauteilNr);
	}
	
	

}
