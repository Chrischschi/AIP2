package mps.core.auftragsUndAngebotsVerwaltung;

import org.hibernate.Session;

public class KundeRepository {

	public static Kunde createPersistent(String name, String adresse) {
		//Create Transient object
		Kunde a = Kunde.create(name,adresse);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//Begin Persistence Context
		session.beginTransaction(); 
		
		//Persist the object
		session.save(a);
		
		//Commit the changes and end the persistence context (implicitly)
		session.getTransaction().commit();
		
		return a;
	}

	public static Kunde getByID(Long kundenNr) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		Kunde result = (Kunde) session.get(Kunde.class, kundenNr);
		
		session.getTransaction().commit();
		
		return result;
		
	}

}