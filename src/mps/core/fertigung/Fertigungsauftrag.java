package mps.core.fertigung;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Fertigungsauftrag {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long nr;
	private Long auftragsNr;
	@ManyToOne
	private Bauteil bauteil;

	public Long getAuftragsNr() {
		return auftragsNr;
	}

	public void setAuftrag(Long auftragsNr) {
		this.auftragsNr = auftragsNr;
	}

	public Bauteil getBauteil() {
		return bauteil;
	}

	public void setBauteil(Bauteil bauteil) {
		this.bauteil = bauteil;
	}

	public Long getNr() {
		return nr;
	}
	
	
}