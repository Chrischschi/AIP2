package mps.core.fertigung;
/** Komponente fuer welche diese Schnittstelle entworfen wurde: AuftragsUndAngebotsVerwaltung, Fassade
 * @author Christian, Nick, Krystian
 *
 */
public interface IFertigung {
	/**Systemoperation*/
	
	/** Erstellt fuer eine gegebene auftrags-ID einen Fertigungsplan
	 *  beziehungsweise Fertigungsauftrag und gibt dessen ID zurueck.
	 * @param auftragNr ID des auftrags 
	 * @return ID des Fertigungsplans/Fertigungsauftrags
	 */
	long fertigungsAuftragErstellen(long auftragNr);
	
	/**Weitere Operationen*/
	//void fertige(long fertigungsAuftragId); //soll durch was sinnvolleres ersetzt werden oder komplett gestrichen werden
}
