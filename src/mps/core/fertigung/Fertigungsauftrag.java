package mps.core.fertigung;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Fertigungsauftrag {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nr;
	private int auftragsNr;
	private Bauteil bauteil;
	
	public Fertigungsauftrag(int auftragsNr, Bauteil bauteil) {
		this.auftragsNr = auftragsNr;
		this.bauteil = bauteil;
	}

	public int getAuftragsNr() {
		return auftragsNr;
	}

	public void setAuftrag(int auftragsNr) {
		this.auftragsNr = auftragsNr;
	}

	public Bauteil getBauteil() {
		return bauteil;
	}

	public void setBauteil(Bauteil bauteil) {
		this.bauteil = bauteil;
	}

	public int getNr() {
		return nr;
	}
	
	
}
