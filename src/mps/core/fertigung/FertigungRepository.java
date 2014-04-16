package mps.core.fertigung;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class FertigungRepository {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	private static Session session;

	public static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
	
	
	public static void open(){
		sessionFactory = createSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();	
	}
	
	public static void close(){
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}

	public static void erstelleBauteil(String name){
		Bauteil b = new Bauteil();
		b.setName(name);
		open();
		session.save(b);
		close();
		}
	
	
	
	
	public void bauteilAddStueckliste(int bauteilNr, String gueltigAb,String gueltigBis, StuecklistenPosition sp){
		Stueckliste s = new Stueckliste();
		s.setGueltigAb(gueltigAb);
		s.setGueltigBis(gueltigBis);
		Set<StuecklistenPosition> spn = s.getStuecklistenPosition();
		spn.add(sp);
		s.setStuecklistenPosition((HashSet<StuecklistenPosition>)spn);
		sp.setStueckliste(s);
		open();
		Bauteil b = (Bauteil) session.load(Bauteil.class, bauteilNr);
		b.setStueckliste(s);
		s.setBauteil(b);
		session.save(b);
		session.save(s);
		close();
	}
	
	
	public static void main(String[] args) {
//----------------------Test get und save------------------------

	Arbeitsplan a = new Arbeitsplan();
	Vorgang v = new Vorgang();
	Vorgang v2 = new Vorgang();
	ArrayList<Vorgang> hs = new ArrayList<Vorgang>();
	v.setMaschinenzeit(10);
	hs.add(v);
	hs.add(v2);
	a.setVorgangListe(hs);
	v.setArbeitsplan(a);
	v2.setArbeitsplan(a);
	
		
		sessionFactory = createSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
	//	Bauteil c = (Bauteil) session.get(Bauteil.class, 1);
	//	c.setName("heueu");
		session.save(a);
		session.save(v);
		Arbeitsplan c = (Arbeitsplan) session.load(Arbeitsplan.class, 1);
		Vorgang d = (Vorgang) session.load(Vorgang.class, 1);
		Vorgang test = (Vorgang) c.getVorgangListe().toArray()[0];
		System.out.println(test.getMaschinenzeit());
		System.out.println(d.getArbeitsplan());
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
	}
}