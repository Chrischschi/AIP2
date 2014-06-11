package mps.core.auftragsUndAngebotsVerwaltung;

public interface EKunde {
	Long getNr(); 
	
	String getName();
	
	Adresse getAdresse();
	
	void setName(String name);
	
	void setAdresse(Adresse adresse);
	
}
