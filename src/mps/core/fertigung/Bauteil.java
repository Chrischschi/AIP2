package mps.core.fertigung;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import java.util.ArrayList;

@Entity
public class Bauteil {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nr;
	private String name;
	private Arbeitsplan arbeitsplan;
	private Stueckliste stueckliste;
	private ArrayList<Fertigungsauftrag> fertigungsauftragListe = new ArrayList<Fertigungsauftrag>();
	private ArrayList<StuecklistenPosition> stuecklistenPosition = new ArrayList<StuecklistenPosition>();
	private ArrayList<Integer> angebotsListe = new ArrayList<Integer>();

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

	public ArrayList<Fertigungsauftrag> getFertigungsauftragListe() {
		return fertigungsauftragListe;
	}

	public void setFertigungsauftragListe(
			ArrayList<Fertigungsauftrag> fertigungsauftragListe) {
		this.fertigungsauftragListe = fertigungsauftragListe;
	}

	public ArrayList<StuecklistenPosition> getStuecklistenPosition() {
		return stuecklistenPosition;
	}

	public void setStuecklistenPosition(
			ArrayList<StuecklistenPosition> stuecklistenPosition) {
		this.stuecklistenPosition = stuecklistenPosition;
	}

	public ArrayList<Integer> getAngebotsListe() {
		return angebotsListe;
	}

	public void setAngebotsListe(ArrayList<Integer> angebotsListe) {
		this.angebotsListe = angebotsListe;
	}

}
