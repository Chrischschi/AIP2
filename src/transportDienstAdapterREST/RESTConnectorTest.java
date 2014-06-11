package transportDienstAdapterREST;

import static org.junit.Assert.*;
import mps.core.auftragsUndAngebotsVerwaltung.Kunde;
import mps.core.auftragsUndAngebotsVerwaltung.KundeRepository;
import mps.core.fertigung.Bauteil;
import mps.core.fertigung.dao.BauteilManager;

import org.junit.Test;

import extern.uppsTransportService.model.TransportRequestData;

public class RESTConnectorTest {

	@Test
	public void testSubmitTransportRequest() {
		RESTConnector c = new RESTConnector();
		TransportRequestData tr = new TransportRequestData(); 
		tr.deliveryItem = BauteilManager.loadBauteil(1L);
		tr.deliveryReciever = KundeRepository.getByID(1L);
		
		long trId = c.submitTransportRequest(tr);
		
		assertTrue(trId > 0);
	}

}
