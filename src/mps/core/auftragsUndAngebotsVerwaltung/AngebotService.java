package mps.core.auftragsUndAngebotsVerwaltung;

//Enum singleton pattern
public final class AngebotService implements IAngebote{
	
	private static final AngebotService INSTANCE = new AngebotService();
	
	private AngebotService() {}
	
	static AngebotService getInstance() {
		return INSTANCE;
	}
	
	//SystemOperation
	public Angebot angebotErstellen(Long kundeNr,String gueltigAb, String gueltigBis, Integer preis , Long bauteilNr) {
		Kunde kunde = KundeRepository.getByID(kundeNr);
		return AngebotRepository.createPersistent(kunde,gueltigAb, gueltigBis, preis, bauteilNr);
	}
	
	

}
