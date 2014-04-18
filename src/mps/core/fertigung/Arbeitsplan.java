package mps.core.fertigung;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Arbeitsplan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long nr;
	@OneToOne
	private Bauteil bauteil;
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(
		    name="ARBEITSPLAN_VORGANG",
		    joinColumns=
		        @JoinColumn(name="ARBEITSPLAN_ID"),
		    inverseJoinColumns=
		        @JoinColumn(name="VORGANG_ID")
		    )
	private List<Vorgang> vorgangListe = new ArrayList<Vorgang>();
	
	

	public Bauteil getBauteil() {
		return bauteil;
	}

	public void setBauteil(Bauteil bauteil) {
		this.bauteil = bauteil;
	}

	public List<Vorgang> getVorgangListe() {
		return vorgangListe;
	}

	public void setVorgangListe(List<Vorgang> vorgangListe) {
		this.vorgangListe = vorgangListe;
	}

	public Long getNr() {
		return nr;
	}
	
	public String toString(){
		StringBuffer vl = new StringBuffer();
		for(Vorgang v:vorgangListe) vl.append(v.toString());
		return "Arbeitsplan Nr: "+nr+"\n"+ vl;
	}
	
	public boolean equals(Object o){
	    boolean result = false;
	    if (o instanceof Arbeitsplan) {
	        Arbeitsplan that = (Arbeitsplan) o;
	        result = (this.getNr() == that.getNr() && this.getBauteil().equals(that.getBauteil()) && this.getVorgangListe().equals(that.getVorgangListe()));
	    }
	    return result;
	}

}