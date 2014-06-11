package mps.core.auftragsUndAngebotsVerwaltung;

import org.hibernate.Session;
import mps.core.auftragsUndAngebotsVerwaltung.HibernateUtil;

public class KundeRepository {

	public static Kunde createPersistent(String name, Adresse adresse) {
		//Create Transient object
		Kunde a = Kunde.create(name,adresse);
		
		//Begin Persistence Context
		Session session = HibernateUtil.beginTransaction();
		
		//Persist the object
		session.save(a);
		
		//Commit the changes and end the persistence context (implicitly)
		HibernateUtil.commitTransaction();
		
		return a;
	}

	public static Kunde getByID(Long kundenNr) {
		Session session = HibernateUtil.beginTransaction();
		
		Kunde result = (Kunde) session.get(Kunde.class, kundenNr);
		
		HibernateUtil.commitTransaction();
		
		return result;
		
	}

}