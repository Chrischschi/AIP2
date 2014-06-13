package mps.szenario;

import mps.redundant.server.Mps;

public class TestDatenCreateKunde {
	
	public static void main(String[] args) {

		Mps mps = new Mps();
		mps.createKunde("SAP", "Holunderstrasse 25");
		mps.createKunde("Siemens", "Petersallee 10");
	}

}
