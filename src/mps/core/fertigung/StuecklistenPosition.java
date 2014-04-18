package mps.core.fertigung;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StuecklistenPosition {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long nr;
	@ManyToOne
	private Stueckliste stueckliste;
	@ManyToOne
	private Bauteil bauteil;
	private Long menge;

	public Stueckliste getStueckliste() {
		return stueckliste;
	}

	public void setStueckliste(Stueckliste stueckliste) {
		this.stueckliste = stueckliste;
	}

	public Bauteil getBauteil() {
		return bauteil;
	}

	public void setBauteil(Bauteil bauteil) {
		this.bauteil = bauteil;
	}

	public Long getMenge() {
		return menge;
	}

	public void setMenge(Long menge) {
		this.menge = menge;
	}
	
	public String toString(){
		return "Menge: "+menge+" - "+bauteil.toString()+"\n";
	}
	
	public boolean equals(Object o){
	    boolean result = false;
	    if (o instanceof Bauteil) {
	        StuecklistenPosition that = (StuecklistenPosition) o;
	        result = (this.getMenge() == that.getMenge() 
	        		&& this.getBauteil().equals(that.getBauteil())
	        		&& this.getStueckliste().equals(that.getStueckliste()));
	    }
	    return result;
	}
	
}