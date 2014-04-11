package mps.core.fertigung;

public class Vorgang {
	private Arbeitsplan arbeitsplan;
	private int ruestzeit;
	private int maschinenzeit;
	private int personenzeit;
	private VorgangArtTyp vorgangArtTyp;
	
	public Vorgang(Arbeitsplan arbeitsplan) {
		this.arbeitsplan = arbeitsplan;
	}

	public Arbeitsplan getArbeitsplan() {
		return arbeitsplan;
	}

	public void setArbeitsplan(Arbeitsplan arbeitsplan) {
		this.arbeitsplan = arbeitsplan;
	}

	public int getRuestzeit() {
		return ruestzeit;
	}

	public void setRuestzeit(int ruestzeit) {
		this.ruestzeit = ruestzeit;
	}

	public int getMaschinenzeit() {
		return maschinenzeit;
	}

	public void setMaschinenzeit(int maschinenzeit) {
		this.maschinenzeit = maschinenzeit;
	}

	public int getPersonenzeit() {
		return personenzeit;
	}

	public void setPersonenzeit(int personenzeit) {
		this.personenzeit = personenzeit;
	}

	public VorgangArtTyp getVorgangArtTyp() {
		return vorgangArtTyp;
	}

	public void setVorgangArtTyp(VorgangArtTyp vorgangArtTyp) {
		this.vorgangArtTyp = vorgangArtTyp;
	}

}
