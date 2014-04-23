package mps.core.auftragsUndAngebotsVerwaltung;


public class Auftrag implements EAuftrag {
	
	/** Attribute */
	private Long nr; 
	private Boolean istAbgeschlossen;
	private String beauftragtAm;
	
	/** Referenzen */
	private Angebot angebot = null;
	private Long fertigungsauftragNr; 

	@Override
	public Long getNr() {
		// TODO Auto-generated method stub
		return nr;
	}
	
	@SuppressWarnings("unused")
	private void setNr(Long nr) {
		this.nr = nr; 
	}

	@Override
	public boolean getIstAbgeschlossen() {
		return istAbgeschlossen;
	}

	@Override
	public void setIstAbgeschlossen(boolean istAbgeschlossen) {
		this.istAbgeschlossen = istAbgeschlossen;
	}

	@Override
	public String getBeauftragtAm() {
		return beauftragtAm;
	}

	@Override
	public void setBeauftragtAm(String beauftragtAm) {
		this.beauftragtAm = beauftragtAm;
	}

	/**
	 * @return the ID of the Fertigungsauftrag
	 */
	public Long getFertigungsauftragNr() {
		return fertigungsauftragNr;
	}

	/**
	 * @param fertigungsAuftrag the ID of the Fertigungsauftrag to set
	 */
	public void setFertigungsauftragNr(Long fertigungsauftragNr) {
		this.fertigungsauftragNr = fertigungsauftragNr;
	}

	public Angebot getAngebot() {
		return angebot;
	}

	public void setAngebot(Angebot angebot) {
		this.angebot = angebot;
	}
	
	@Override 
	/** use business-key equality for implementing equals */
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Auftrag)) return false;
		Auftrag that = (Auftrag)o;
		return this.getIstAbgeschlossen() == that.getIstAbgeschlossen() && 
			   this.getBeauftragtAm().equals(that.getBeauftragtAm());
	}

	public static Auftrag create(boolean istAbgeschlossen, String beauftragtAm) {
		Auftrag auftrag = new Auftrag();
		auftrag.setIstAbgeschlossen(istAbgeschlossen);
		auftrag.setBeauftragtAm(beauftragtAm);
		
		return auftrag;
	}

}
