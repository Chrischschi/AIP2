package mps.redundant;

import mps.core.auftragsUndAngebotsVerwaltung.Angebot;
import mps.core.auftragsUndAngebotsVerwaltung.AngebotRepository;
import mps.core.auftragsUndAngebotsVerwaltung.AngebotService;
import mps.core.auftragsUndAngebotsVerwaltung.AuftragRepository;
import mps.core.auftragsUndAngebotsVerwaltung.AuftragService;
import mps.core.auftragsUndAngebotsVerwaltung.IAuftraege;



public class Mps{


    public Angebot createAngebot(String gueltigAb,String gueltigBis,
			int preis, Long bauteilId){
    	return AngebotService.getInstance().angebotErstellen(null,gueltigAb, gueltigBis, preis, bauteilId);
    }
    
    public void createAuftrag(boolean istAbgeschlossen, String beauftragtAm ,
			Angebot angebot){
    	IAuftraege.getAuftragService().auftragErstellen(beauftragtAm, angebot.getNr());
    }
    
    
}