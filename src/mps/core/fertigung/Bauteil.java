package mps.core.fertigung;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

//import java.util.HashSet;

@Entity
public class Bauteil {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nr;
	private String name;
	@OneToOne
	private Arbeitsplan arbeitsplan;
	@OneToOne
	private Stueckliste stueckliste;
	@OneToMany
	@JoinTable(
		    name="BAUTEIL_FERTIGUNGSAUFTRAG",
		    joinColumns=
		        @JoinColumn(name="BAUTEIL_ID"),
		    inverseJoinColumns=
		        @JoinColumn(name="FERTIGUNGSAUFTRAG_ID")
		    )
	private Set<Fertigungsauftrag> fertigungsauftragListe = new HashSet<Fertigungsauftrag>();
	@OneToMany
	@JoinTable(
		    name="BAUTEIL_STUECKLISTENPOSITION",
		    joinColumns=
		        @JoinColumn(name="BAUTEIL_ID"),
		    inverseJoinColumns=
		        @JoinColumn(name="STUECKLISTENPOSITION_ID")
		    )
	private Set<StuecklistenPosition> stuecklistenPosition = new HashSet<StuecklistenPosition>();
	//private Set<Integer> angebotsListe = new HashSet<Integer>();

	public int getNr() {
		return nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Arbeitsplan getArbeitsplan() {
		return arbeitsplan;
	}

	public void setArbeitsplan(Arbeitsplan arbeitsplan) {
		this.arbeitsplan = arbeitsplan;
	}

	public Stueckliste getStueckliste() {
		return stueckliste;
	}

	public void setStueckliste(Stueckliste stueckliste) {
		this.stueckliste = stueckliste;
	}

	public Set<Fertigungsauftrag> getFertigungsauftragListe() {
		return fertigungsauftragListe;
	}

	public void setFertigungsauftragListe(
			HashSet<Fertigungsauftrag> fertigungsauftragListe) {
		this.fertigungsauftragListe = fertigungsauftragListe;
	}

	public Set<StuecklistenPosition> getStuecklistenPosition() {
		return stuecklistenPosition;
	}

	public void setStuecklistenPosition(
			HashSet<StuecklistenPosition> stuecklistenPosition) {
		this.stuecklistenPosition = stuecklistenPosition;
	}

//	public Set<Integer> getAngebotsListe() {
//		return angebotsListe;
//	}
//
//	public void setAngebotsListe(HashSet<Integer> angebotsListe) {
//		this.angebotsListe = angebotsListe;
//	}

}