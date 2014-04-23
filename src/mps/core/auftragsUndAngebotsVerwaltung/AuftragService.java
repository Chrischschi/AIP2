package mps.core.auftragsUndAngebotsVerwaltung;

//TODO The whole class
public class AuftragService implements IAuftraege {
	private static final AuftragService INSTANCE = new AuftragService();
	
	private AuftragService() {}
	
	static AuftragService getInstance() {
		return INSTANCE;
	}

	@Override
	public Long getBauteilIdOfAutrag(Long auftragNr) {
		// TODO Auto-generated method stub
		return new Long(0); 
	}
	 
	 

}
