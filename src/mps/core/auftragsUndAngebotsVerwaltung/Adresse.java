package mps.core.auftragsUndAngebotsVerwaltung;

public class Adresse {
	
	private String name;
	private int nummer; 
	private String plz;
	
	public Adresse(String name, int nummer, String plz) {
		this.name = name;
		this.nummer = nummer;
		this.plz = plz;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}

}
