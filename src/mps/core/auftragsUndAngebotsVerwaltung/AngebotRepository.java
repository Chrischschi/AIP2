package mps.core.auftragsUndAngebotsVerwaltung;

import org.hibernate.Session;

public class AngebotRepository {

	public static Angebot createPersistent(String gueltigAb,String gueltigBis,
			Integer preis, Long bauteilId) {
		//Create Transient object
		Angebot a = Angebot.create(gueltigAb,gueltigBis,preis);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//Begin Persistence Context
		session.beginTransaction(); 
		
		//set references
		a.setBauteilNr(bauteilId);
		
		//Persist the object
		session.save(a);
		
		//Commit the changes and end the persistence context (implicitly)
		session.getTransaction().commit();
		
		return a;
	}

}
