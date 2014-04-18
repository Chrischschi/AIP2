package mps.core.auftragsUndAngebotsVerwaltung;

/** Komponente fuer welche diese Schnittstelle entworfen wurde: Fertigung
 * @author Christian
 *
 */
public interface IAuftraege {
	/** Operationen, welche einem benoetigte daten besorgen */
	
	/**
	 * Gibt einem fuer eine gegebene Auftrag-ID 
	 * die ID des dazugehoerigen bauteils.
	 * @param auftragNr ID des Auftrags 
	 * @returns ID des bauteils
	 */
	Long getBauteilIdOfAutrag(Long auftragNr); 
	
	/** gibt eine referenz auf einen auftrag zurück,
	 * diese referenz hat den typ E(xtern)Auftrag, damit
	 * nicht implementierungsdetails aus der komponente raussickern 
	 */
	static EAuftrag getAuftrag(Long auftragNr) {
		return AuftragRepository.readAuftrag(auftragNr);
	}
	
	static IAuftraege getAuftragService() {
		return AuftragService.getInstance();
	}
}
