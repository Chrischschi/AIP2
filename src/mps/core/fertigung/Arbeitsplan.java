package mps.core.fertigung;

import java.util.ArrayList;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Arbeitsplan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nr;
	private Bauteil bauteil;
	private ArrayList<Vorgang> vorgangListe = new ArrayList<Vorgang>();
	
	public Arbeitsplan(Bauteil bauteil, Vorgang vorgang) {
	this.bauteil = bauteil;
	vorgangListe.add(vorgang);
	}

	public Bauteil getBauteil() {
		return bauteil;
	}

	public void setBauteil(Bauteil bauteil) {
		this.bauteil = bauteil;
	}

	public ArrayList<Vorgang> getVorgangListe() {
		return vorgangListe;
	}

	public void setVorgangListe(ArrayList<Vorgang> vorgangListe) {
		this.vorgangListe = vorgangListe;
	}

	public int getNr() {
		return nr;
	}
	

}
