package mps.core.fertigung;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Arbeitsplan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nr;
	@OneToOne
	private Bauteil bauteil;
	@OneToMany
	@JoinTable(
		    name="ARBEITSPLAN_VORGANG",
		    joinColumns=
		        @JoinColumn(name="ARBEITSPLAN_ID"),
		    inverseJoinColumns=
		        @JoinColumn(name="VORGANG_ID")
		    )
	private Set<Vorgang> vorgangListe = new HashSet<Vorgang>();
	
	

	public Bauteil getBauteil() {
		return bauteil;
	}

	public void setBauteil(Bauteil bauteil) {
		this.bauteil = bauteil;
	}

	public Set<Vorgang> getVorgangListe() {
		return vorgangListe;
	}

	public void setVorgangListe(HashSet<Vorgang> vorgangListe) {
		this.vorgangListe = vorgangListe;
	}

	public int getNr() {
		return nr;
	}
	

}