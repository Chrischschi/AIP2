package mps.core.fertigung;


import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class FertigungRepository {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	public static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}


	public static void main(String[] args) {
//----------------------Test get und save------------------------

	Arbeitsplan a = new Arbeitsplan();
	Vorgang v = new Vorgang();
	HashSet<Vorgang> hs = new HashSet<Vorgang>();
	hs.add(v);
	a.setVorgangListe(hs);
	v.setArbeitsplan(a);
	
		
		SessionFactory sessionFactory = createSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
	//	Bauteil c = (Bauteil) session.get(Bauteil.class, 1);
	//	c.setName("heueu");
		session.save(a);
		session.save(v);
		
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
	}
}