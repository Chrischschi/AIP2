package mps.redundant.server;

import transportDienstAdapterREST.RESTConnector;
import mps.core.auftragsUndAngebotsVerwaltung.EAngebot;
import mps.core.auftragsUndAngebotsVerwaltung.EAuftrag;
import mps.core.auftragsUndAngebotsVerwaltung.EKunde;
import mps.core.auftragsUndAngebotsVerwaltung.IKunden;
import mps.core.auftragsUndAngebotsVerwaltung.IAngebote;
import mps.core.auftragsUndAngebotsVerwaltung.IAuftraege;
import mps.core.auftragsUndAngebotsVerwaltung.Kunde;
import mps.core.buchhaltung.IRechnungen;
import mps.core.fertigung.Bauteil;

/**
 * MPS ist sozusagen die Fassade, welche die bisherige "Buisness Logik"
 * anbietet.
 */
public class Mps {

	/**
	 * @param name
	 * @param adresse
	 * @return
	 * 
	 *         Erzeugt einen Kunden welcher in der Datenbank hinterlegt wird.
	 */
	public EKunde createKunde(String name, String adresse) {
		return IKunden.getKundeService().kundeErstellen(name,adresse);
	}
	
	/**
	 * @param gueltigAb
	 * @param gueltigBis
	 * @param preis
	 * @param bauteilId
	 * @return
	 * 
	 *         Erzeugt ein angebot welches in der Datenbank hinterlegt wird.
	 */
	public EAngebot createAngebot(EKunde kunde, String gueltigAb, String gueltigBis,
			int preis, Long bauteilId) {
		return IAngebote.getAngebotService().angebotErstellen(kunde.getNr(), gueltigAb,
				gueltigBis, preis, bauteilId);
	}

	/**
	 * @param istAbgeschlossen
	 * @param beauftragtAm
	 * @param angebot
	 * 
	 *            Erzeugt einen zum Angebot gehoerigen Auftrag welcher in der
	 *            Datenbank hinterlegt wird. Sideeffect: Es wird ein
	 *            Fertigungsplan zum Auftrag erzeugt.
	 */
	public void createAuftrag(boolean istAbgeschlossen, String beauftragtAm,
			EAngebot angebot) {
		IAuftraege.getAuftragService().auftragErstellen(beauftragtAm,
				angebot.getNr());
	}
	
	public void zahlungsEingang(int zahlung,Long rechnungsnummer){
		IRechnungen.getRechnungService().zahlungsEingang(zahlung, rechnungsnummer);
	}
	
	
	public long liefereAuftragAus(EAuftrag auftrag) {
		return 0;
		
	}

}