package transportDienstAdapterREST;

import static org.junit.Assert.*;
import mps.core.auftragsUndAngebotsVerwaltung.KundeRepository;
import mps.core.fertigung.Bauteil;

import org.junit.Test;

import extern.uppsTransportService.model.TransportRequestData;

public class RESTConnectorTest {

	@Test
	public void testSubmitTransportRequest() {
		RESTConnector c = new RESTConnector();
		TransportRequestData tr = Util.packTransportRequest(KundeRepository.getByID(1), Bauteil.erstelleBauteil("lawnmower", null, null));
		long trId = c.submitTransportRequest(tr);
		
		assertTrue(trId > 0);
	}

}
