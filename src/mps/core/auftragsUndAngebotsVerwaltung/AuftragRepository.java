package mps.core.auftragsUndAngebotsVerwaltung;

import mps.core.fertigung.IFertigung;

import org.hibernate.Session;

public class AuftragRepository {

	 static EAuftrag readAuftrag(Long auftragNr) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Auftrag auftrag = (Auftrag) session.get(Auftrag.class, auftragNr);
		
		session.getTransaction().commit();
		
		return auftrag;
	}

	 
	public static Auftrag createPersistent(boolean istAbgeschlossen, String beauftragtAm ,
			Angebot angebot) {
		//Create transient object
		Auftrag auftrag = Auftrag.create(istAbgeschlossen,beauftragtAm);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//Set references
		auftrag.setAngebot(angebot);
		auftrag.setFertigungsauftragNr(null); //currently unknown
		//Set reference back
		angebot.setAuftrag(auftrag);
		
		//Update the newly created object
		long aid=(long)session.save(auftrag);
		//Update the already existing object
		session.merge(angebot);
		
		auftrag= (Auftrag) session.get(Auftrag.class, aid);
		
		Long fertigungsPlanNr = IFertigung.getFertigungService().fertigungsPlanErstellen(auftrag.getNr(),auftrag.getAngebot().getBauteilNr());

		auftrag.setFertigungsauftragNr(fertigungsPlanNr);
		session.merge(auftrag);
		
		session.getTransaction().commit();
		
		return auftrag;
	}

	/** Updates the persistent state of an Object in the Database
	 * by merging the object
	 * @param auftrag - the Persistent Object to be updated
	 */
	 static void persistUpdated(Auftrag auftrag) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.merge(auftrag);
		
		session.getTransaction().commit(); 
	}

}
