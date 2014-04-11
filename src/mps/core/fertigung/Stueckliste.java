package mps.core.fertigung;

import java.util.ArrayList;

public class Stueckliste {

	private String gueltigAb;
	private String gueltigBis;
	private Bauteil bauteil;
	private ArrayList<StuecklistenPosition> stuecklistenPosition = new ArrayList<StuecklistenPosition>();
	
	public Stueckliste(Bauteil bauteil, StuecklistenPosition stuecklistenPosition) {
		this.bauteil = bauteil;
		this.stuecklistenPosition.add(stuecklistenPosition);
	}

	public String getGueltigAb() {
		return gueltigAb;
	}

	public void setGueltigAb(String gueltigAb) {
		this.gueltigAb = gueltigAb;
	}

	public String getGueltigBis() {
		return gueltigBis;
	}

	public void setGueltigBis(String gueltigBis) {
		this.gueltigBis = gueltigBis;
	}

	public Bauteil getBauteil() {
		return bauteil;
	}

	public void setBauteil(Bauteil bauteil) {
		this.bauteil = bauteil;
	}

	public ArrayList<StuecklistenPosition> getStuecklistenPosition() {
		return stuecklistenPosition;
	}

	public void setStuecklistenPosition(
			ArrayList<StuecklistenPosition> stuecklistenPosition) {
		this.stuecklistenPosition = stuecklistenPosition;
	}
}
