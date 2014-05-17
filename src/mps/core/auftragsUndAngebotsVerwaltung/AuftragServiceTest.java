package mps.core.auftragsUndAngebotsVerwaltung;

import static org.junit.Assert.*;

import org.junit.Test;

public class AuftragServiceTest {
	
	//Technical Tests
	@Test
	public void testGetInstance() {
		AuftragService serviceA = AuftragService.getInstance();
		AuftragService serviceB = AuftragService.getInstance();
		assertTrue("There is only one AuftragService",serviceA == serviceB);
	}

	@Test
	public void testEquals() {
		AuftragService serviceA = AuftragService.getInstance();
		AuftragService serviceB = AuftragService.getInstance();
		assertEquals("Only reference equality needed because the class is a singleton",serviceA,serviceB);
	}
	
	//Domain-Specific Tests
	@Test
	public void testGetBauteilIdOfAutrag() {
		/* Angenommen, wir haben das angebot, das mit dem 
		 * bauteil mit der ID 1 assoziiert ist 
		 * (dieses bauteil hat den namen "Kolben") 
		 * */ 
		Angebot angebot = AngebotRepository.createPersistent("23.04.2014","24.04.2014",40,1L);
		//erstelle auftrag und assoziiere mit angebot
		Auftrag auftragZuAngebot = AuftragRepository.createPersistent(false,"23.04.2014",angebot);
		
		AuftragService as = AuftragService.getInstance();
		
		Long bauteilId = as.getBauteilIdOfAutrag(auftragZuAngebot.getNr());
		
		Long expected = angebot.getBauteilNr();
			
		assertEquals("testGetBauteilIdOfAuftrag",expected,bauteilId);
	}
	
	@Test
	public void testAuftragErstellen1() {
		AngebotService as = AngebotService.INSTANCE;
		Angebot angebot = as.angebotErstellen(null, "17.5", "18.5", 1, 20L);
		
		AuftragService auftragService = AuftragService.getInstance();
		Auftrag auftragMitAngebot = auftragService.auftragErstellen("16.5",angebot.getNr());
		
		//Check if the association has been made correctly 
		assertEquals(angebot,auftragMitAngebot.getAngebot());
	}

}
