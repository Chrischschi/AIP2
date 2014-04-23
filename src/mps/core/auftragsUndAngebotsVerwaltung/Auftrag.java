package mps.core.auftragsUndAngebotsVerwaltung;

import mps.core.fertigung.Fertigungsauftrag;

public class Auftrag implements EAuftrag {
	
	/** Attribute */
	private Long nr; 
	private Boolean istAbgeschlossen;
	private String beauftragtAm;
	
	/** Referenzen */
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

}
