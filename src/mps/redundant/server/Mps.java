package mps.redundant.server;

import mps.core.auftragsUndAngebotsVerwaltung.EAngebot;
import mps.core.auftragsUndAngebotsVerwaltung.IAngebote;
import mps.core.auftragsUndAngebotsVerwaltung.IAuftraege;

/**
 * MPS ist sozusagen die Fassade, welche die bisherige "Buisness Logik"
 * anbietet.
 */
public class Mps {

	/**
	 * @param gueltigAb
	 * @param gueltigBis
	 * @param preis
	 * @param bauteilId
	 * @return
	 * 
	 *         Erzeugt ein angebot welches in der Datenbank hinterlegt wird.
	 */
	public EAngebot createAngebot(String gueltigAb, String gueltigBis,
			int preis, Long bauteilId) {
		return IAngebote.getAngebotService().angebotErstellen(null, gueltigAb,
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

}