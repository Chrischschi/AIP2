package transportDienstAdapterREST;

import static org.junit.Assert.*;
import mps.core.auftragsUndAngebotsVerwaltung.KundeRepository;
import mps.core.fertigung.dao.BauteilManager;

import org.junit.Test;


public class RESTConnectorTest {

	@Test
	public void testSubmitTransportRequest() {
		RESTConnector c = new RESTConnector();
		
		long trId = c.submitTransportRequest(KundeRepository.getByID(1L),BauteilManager.loadBauteil(1L));
		
		assertTrue(trId > 0);
	}

}
