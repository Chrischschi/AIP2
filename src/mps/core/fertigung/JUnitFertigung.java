package mps.core.fertigung;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static mps.core.fertigung.FertigungRepository.*;

public class JUnitFertigung {

	Bauteil maehdrescher = new Bauteil();
	Bauteil motor = new Bauteil();
	Bauteil reifen = new Bauteil();
	Stueckliste stueckliste1 = new Stueckliste();
	StuecklistenPosition stposition1 = new StuecklistenPosition();
	StuecklistenPosition stposition2 = new StuecklistenPosition();
	Arbeitsplan arbeitsplan1 = new Arbeitsplan();
	Vorgang vorgang1 = new Vorgang();
	Vorgang vorgang2 = new Vorgang();
	Vorgang vorgang3 = new Vorgang();
	Fertigungsauftrag fertigungsauftrag1 = new Fertigungsauftrag();
	
	@Before
	public void setup(){
		erstelleBauteil("Maehdrescher");
		erstelleBauteil("Motor");
		erstelleBauteil("Reifen");
		erstelleStueckliste("23-04-2014","24-04-2014");
		erstelleStuecklistenPosition(1);
		erstelleStuecklistenPosition(4);
		erstelleArbeitsplan();
		erstelleVorgang(VorgangArtTyp.MONTAGE, 10, 15, 20);
		erstelleVorgang(VorgangArtTyp.MONTAGE, 30, 30, 30);
		erstelleVorgang(VorgangArtTyp.MONTAGE, 35, 40, 50);
		assoziationBauteilStueckliste(1,1);
		assoziationStueckListeStuecklistenPosition(1,1);
		assoziationStueckListeStuecklistenPosition(1,2);
		assoziationBauteilStuecklistenPosition(2,1);
		assoziationBauteilStuecklistenPosition(3,2);
		assoziationBauteilArbeitsplan(1,1);
		assoziationArbeitsplanVorgang(1,1);
		assoziationArbeitsplanVorgang(1,2);
		assoziationArbeitsplanVorgang(1,3);
		FertigungService fs = new FertigungService();
    	fs.fertigungsPlanErstellen(new Long(1));
    	
		
		maehdrescher.setName("Maehdrescher");
		maehdrescher.setArbeitsplan(arbeitsplan1);
		maehdrescher.setStueckliste(stueckliste1);
		Set<Fertigungsauftrag> fertigungsauftragListe = new HashSet<Fertigungsauftrag>();
		fertigungsauftragListe.add(fertigungsauftrag1);
		maehdrescher.setFertigungsauftragListe(fertigungsauftragListe);
		
		motor.setName("Motor");
		Set<StuecklistenPosition> stpositionListe1 = new HashSet<StuecklistenPosition>();
		stpositionListe1.add(stposition1);
		motor.setStuecklistenPosition(stpositionListe1);
		
		reifen.setName("Reifen");
		Set<StuecklistenPosition> stpositionListe2 = new HashSet<StuecklistenPosition>();
		stpositionListe2.add(stposition2);
		motor.setStuecklistenPosition(stpositionListe2);
		
		stueckliste1.setGueltigAb("23-04-2014");
		stueckliste1.setGueltigBis("24-04-2014");
		stueckliste1.setBauteil(maehdrescher);
		Set<StuecklistenPosition> stpositionListe3 = new HashSet<StuecklistenPosition>();
		stpositionListe3.add(stposition1);
		stpositionListe3.add(stposition2);
		stueckliste1.setStuecklistenPosition(stpositionListe3);
		
		stposition1.setMenge(1l);
		stposition1.setBauteil(motor);
		stposition1.setStueckliste(stueckliste1);
		
		stposition2.setMenge(4l);
		stposition2.setBauteil(reifen);
		stposition2.setStueckliste(stueckliste1);
		
		arbeitsplan1.setBauteil(maehdrescher);
		List<Vorgang> vorgangListe1 = new ArrayList<Vorgang>();
		vorgangListe1.add(vorgang1);
		vorgangListe1.add(vorgang2);
		vorgangListe1.add(vorgang3);
		arbeitsplan1.setVorgangListe(vorgangListe1);
		
		vorgang1.setArbeitsplan(arbeitsplan1);
		vorgang1.setRuestzeit(10l);
		vorgang1.setMaschinenzeit(15l);
		vorgang1.setPersonenzeit(20l);
		vorgang1.setVorgangArtTyp(VorgangArtTyp.MONTAGE);
		
		vorgang2.setArbeitsplan(arbeitsplan1);
		vorgang2.setRuestzeit(30l);
		vorgang2.setMaschinenzeit(30l);
		vorgang2.setPersonenzeit(30l);
		vorgang2.setVorgangArtTyp(VorgangArtTyp.MONTAGE);
		
		vorgang3.setArbeitsplan(arbeitsplan1);
		vorgang3.setRuestzeit(35l);
		vorgang3.setMaschinenzeit(40l);
		vorgang3.setPersonenzeit(50l);
		vorgang3.setVorgangArtTyp(VorgangArtTyp.MONTAGE);
    	
	}
	
	@Test
	public void testEquals() {
		open();
		assertEquals(read(Bauteil.class,1),maehdrescher);
		assertEquals(read(Bauteil.class,2),motor);
		assertEquals(read(Bauteil.class,3),reifen);
		assertEquals(read(Stueckliste.class,1),stueckliste1);
		assertEquals(read(StuecklistenPosition.class,1),stposition1);
		assertEquals(read(StuecklistenPosition.class,2),stposition2);
		assertEquals(read(Arbeitsplan.class,1),arbeitsplan1);
		assertEquals(read(Vorgang.class,1),vorgang1);
		assertEquals(read(Vorgang.class,2),vorgang2);
		assertEquals(read(Vorgang.class,3),vorgang3);
		close();
	}
	
//	@After
//	public void setdown(){
//		open();
//		getSession().createSQLQuery("DROP SCHEMA mps");
//		close();
//	}
}
