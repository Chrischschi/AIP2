package transportDienstAdapterREST;

import mps.core.auftragsUndAngebotsVerwaltung.Kunde;
import mps.core.fertigung.Bauteil;
import extern.uppsTransportService.model.TransportRequestData;

public class Util {
	
	public static TransportRequestData packTransportRequest(Kunde k, Bauteil b) {
		TransportRequestData tr = new TransportRequestData();
		tr.address.number = k.getAdresse().getNummer();
		tr.address.postalCode = k.getAdresse().getPlz();
		tr.address.streetName = k.getAdresse().getName();
		
		tr.name.firstName = "";
		tr.name.lastName = k.getName();
	
		tr.deliveryItem.id = b.getNr();
		tr.deliveryItem.itemName = b.getName();
		
		return tr;
	}

}
