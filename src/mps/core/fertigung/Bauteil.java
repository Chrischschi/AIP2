package mps.core.fertigung;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

//import java.util.HashSet;

@Entity
public class Bauteil {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long nr;
	private String name;
	@OneToOne
	private Arbeitsplan arbeitsplan;
	@OneToOne
	private Stueckliste stueckliste;
	@OneToMany
	private Set<Fertigungsauftrag> fertigungsauftragListe = new HashSet<Fertigungsauftrag>();
	@ElementCollection
	private Set<Integer> angebotsListe = new HashSet<Integer>();

	public Long getNr() {
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
			Set<Fertigungsauftrag> fertigungsauftragListe) {
		this.fertigungsauftragListe = fertigungsauftragListe;
	}
	
	public String toString(){
		if(stueckliste!=null && arbeitsplan!=null)
		return "Bauteil Nr: "+nr+" Name: "+ name+"\n"+"["+stueckliste.toString()+"\n"+arbeitsplan.toString()+"]";
		return "Bauteil Nr: "+nr+" Name: "+ name;
	}
	
	public Set<Integer> getAngebotsListe() {
		return angebotsListe;
	}

	public void setAngebotsListe(HashSet<Integer> angebotsListe) {
		this.angebotsListe = angebotsListe;
	}

	@Override
	public boolean equals(Object o){
	    boolean result = false;
	    if (o instanceof Bauteil) {
	        Bauteil that = (Bauteil) o;
	        result = (this.getName().equals(that.getName()));
	    }
	    return result;
	}
	
}