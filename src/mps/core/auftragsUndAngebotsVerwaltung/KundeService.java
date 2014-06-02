package mps.core.auftragsUndAngebotsVerwaltung;

//Enum singleton pattern
public final class KundeService implements IKunde{
	
	private static final KundeService INSTANCE = new KundeService();
	
	private KundeService() {}
	
	static KundeService getInstance() {
		return INSTANCE;
	}
	
	//SystemOperation
	public Kunde kundeErstellen(String name,String adresse) {
		return KundeRepository.createPersistent(name, adresse);
	}
	
	

}