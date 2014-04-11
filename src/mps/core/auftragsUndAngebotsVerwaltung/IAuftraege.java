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
	long getBauteilIdOfAutrag(long auftragNr); 
}
