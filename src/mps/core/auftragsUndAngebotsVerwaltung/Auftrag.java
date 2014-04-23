package mps.core.auftragsUndAngebotsVerwaltung;

import mps.core.fertigung.Fertigungsauftrag;

public class Auftrag implements EAuftrag {
	
	/** Attribute */
	private Long nr; 
	private Boolean istAbgeschlossen;
	private String beauftragtAm;
	
	/** Referenzen */
	private Fertigungsauftrag fertigungsAuftrag; //Objektreferenz oder doch besser long?

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
	 * @return the fertigungsAuftrag
	 */
	public Fertigungsauftrag getFertigungsAuftrag() {
		return fertigungsAuftrag;
	}

	/**
	 * @param fertigungsAuftrag the fertigungsAuftrag to set
	 */
	public void setFertigungsAuftrag(Fertigungsauftrag fertigungsAuftrag) {
		this.fertigungsAuftrag = fertigungsAuftrag;
	}

}
