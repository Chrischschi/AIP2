package mps.core.fertigung;

public class StuecklistenPosition {
	private Stueckliste stueckliste;
	private Bauteil bauteil;
	private int menge;
	
	public StuecklistenPosition(Stueckliste stueckliste, Bauteil bauteil) {
		this.bauteil = bauteil;
		this.stueckliste = stueckliste;
	}

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

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}
	
}
