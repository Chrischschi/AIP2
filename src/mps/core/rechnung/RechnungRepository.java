package mps.core.rechnung;


import org.hibernate.Session;

import mps.core.rechnung.HibernateUtil;

public class RechnungRepository {

	public static Long createPersistent(int betrag, Long auftragNr) {
		
		
		//Create Transient object
		Rechnung r = Rechnung.create(betrag, auftragNr);
		
		//Begin Persistence Context
		Session session = HibernateUtil.beginTransaction();
		
		
		//Persist the object
		Long rechnungNr = (Long) session.save(r);
		
		//Commit the changes and end the persistence context (implicitly)
		HibernateUtil.commitTransaction();
		
		return rechnungNr;
	}

	public static Rechnung getByID(Long rechnungNr) {
		Session session = HibernateUtil.beginTransaction();
		Rechnung result = (Rechnung) session.get(Rechnung.class, rechnungNr);
		
		HibernateUtil.commitTransaction();
		
		return result;
		
	}

}