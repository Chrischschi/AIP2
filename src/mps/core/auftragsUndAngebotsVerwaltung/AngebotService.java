package mps.core.auftragsUndAngebotsVerwaltung;

//Enum singleton pattern
public enum AngebotService {
	
	INSTANCE; //the instance
	
	public static AngebotService getInstance() {
		return INSTANCE;
	}
	
	//SystemOperation
	public Angebot angebotErstellen(Long kundenNr,String gueltigAb, String gueltigBis, Integer preis , Long bauteilNr) {
		return AngebotRepository.createPersistent(gueltigAb, gueltigBis, preis, bauteilNr);
	}
	
	

}
