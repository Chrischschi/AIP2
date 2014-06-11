package mps.core.auftragsUndAngebotsVerwaltung;

public interface IKunden {
	
	static IKunden getKundeService() {
		return KundeService.getInstance();
	}

	EKunde kundeErstellen(String name, Adresse adresse);
}
