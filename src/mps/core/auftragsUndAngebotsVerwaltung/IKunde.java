package mps.core.auftragsUndAngebotsVerwaltung;

public interface IKunde {
	
	static IKunde getKundeService() {
		return KundeService.getInstance();
	}

	EKunde kundeErstellen(String name, String adresse);
}
