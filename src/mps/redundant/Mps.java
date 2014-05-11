package mps.redundant;

import mps.core.auftragsUndAngebotsVerwaltung.Angebot;
import mps.core.auftragsUndAngebotsVerwaltung.AngebotRepository;
import mps.core.auftragsUndAngebotsVerwaltung.AuftragRepository;



public class Mps{


    public Angebot createAngebot(String gueltigAb,String gueltigBis,
			int preis, Long bauteilId){
    	return AngebotRepository.createPersistent(gueltigAb, gueltigBis, preis, bauteilId);
    }
    
    public void createAuftrag(boolean istAbgeschlossen, String beauftragtAm ,
			Angebot angebot){
    	AuftragRepository.createPersistent(istAbgeschlossen, beauftragtAm, angebot);
    }
    
    
}