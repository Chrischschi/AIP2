package mps.core.auftragsUndAngebotsVerwaltung;

public class AngebotService {
	
	//SystemOperation
	public Angebot angebotErstellen(Long kundenNr,String gueltigAb, String gueltigBis, Integer preis , Long bauteilNr) {
		return AngebotRepository.createPersistent(gueltigAb, gueltigBis, preis, bauteilNr);
	}
	
	

}
