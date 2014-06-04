package mps.core.auftragsUndAngebotsVerwaltung;

import org.hibernate.Session;
import mps.core.auftragsUndAngebotsVerwaltung.HibernateUtil;

public class AngebotRepository {

	public static Angebot createPersistent(Kunde kunde, String gueltigAb,String gueltigBis,
			Integer preis, Long bauteilId) {
		
		
		//Create Transient object
		Angebot a = Angebot.create(kunde, gueltigAb,gueltigBis,preis);
		
		//Begin Persistence Context
		Session session = HibernateUtil.beginTransaction();
		
		//set references
		a.setBauteilNr(bauteilId);
		
		//Persist the object
		session.save(a);
		
		//Commit the changes and end the persistence context (implicitly)
		HibernateUtil.commitTransaction();
		
		return a;
	}

	public static Angebot getByID(Long angebotNr) {
		Session session = HibernateUtil.beginTransaction();
		
		Angebot result = (Angebot) session.get(Angebot.class, angebotNr);
		
		HibernateUtil.commitTransaction();
		
		return result;
		
	}

}
