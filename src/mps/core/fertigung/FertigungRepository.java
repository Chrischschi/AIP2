package mps.core.fertigung;


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
	
	public static void erstelleStueckliste(String gueltigAb,String gueltigBis){
		Stueckliste s = new Stueckliste();
		s.setGueltigAb(gueltigAb);
		s.setGueltigBis(gueltigBis);
		open();
		session.save(s);
		close();
	}
	
	public static void erstelleStuecklistenPosition(int menge){
		StuecklistenPosition sp = new StuecklistenPosition();
		sp.setMenge(menge);
		open();
		session.save(sp);
		close();
	}
	
	public static void assoziationStueckListeStuecklistenPosition(int s_id, int sp_id){
		open();
		Stueckliste s = (Stueckliste) session.load(Stueckliste.class, s_id);
		StuecklistenPosition sp = (StuecklistenPosition) session.load(StuecklistenPosition.class, sp_id);
		Set<StuecklistenPosition> temp = s.getStuecklistenPosition();
		temp.add(sp);
		s.setStuecklistenPosition(temp);
		sp.setStueckliste(s);
		session.merge(s);
		session.merge(sp);
		close();
	}
	
	
	public static void assoziationBauteilStuecklistenPosition(int b_id, int sp_id){
		open();
		Bauteil b = (Bauteil) session.load(Bauteil.class, b_id);
		StuecklistenPosition sp = (StuecklistenPosition) session.load(StuecklistenPosition.class, sp_id);
		Set<StuecklistenPosition> temp = b.getStuecklistenPosition();
		temp.add(sp);
		b.setStuecklistenPosition( temp);
		sp.setBauteil(b);
		session.merge(b);
		session.merge(sp);
		close();
	}
	
	public static void assoziationBauteilStueckliste(int b_id, int s_id){
		open();
		Bauteil b = (Bauteil) session.load(Bauteil.class, b_id);
		Stueckliste s = (Stueckliste) session.load(Stueckliste.class, s_id);
		b.setStueckliste(s);
		s.setBauteil(b);
		session.merge(b);
		session.merge(s);
		close();
	}
	
	
	public static void main(String[] args) {
//----------------------Test Datenbankbefuellung------------------------

		erstelleBauteil("Mähdrescher");
		erstelleStueckliste("heute","morgen");
		erstelleStuecklistenPosition(1);
		erstelleStuecklistenPosition(3);
		assoziationBauteilStueckliste(1,1);
		assoziationStueckListeStuecklistenPosition(1,1);
		assoziationStueckListeStuecklistenPosition(1,2);
		assoziationBauteilStuecklistenPosition(1,1);
		assoziationBauteilStuecklistenPosition(1,2);
		
		
//	Arbeitsplan a = new Arbeitsplan();
//	Vorgang v = new Vorgang();
//	Vorgang v2 = new Vorgang();
//	ArrayList<Vorgang> hs = new ArrayList<Vorgang>();
//	v.setMaschinenzeit(10);
//	hs.add(v);
//	hs.add(v2);
//	a.setVorgangListe(hs);
//	v.setArbeitsplan(a);
//	v2.setArbeitsplan(a);
//	
//		
//		sessionFactory = createSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		
//		
//		session.save(a);
//		session.save(v);
//		Arbeitsplan c = (Arbeitsplan) session.load(Arbeitsplan.class, 1);
//		Vorgang d = (Vorgang) session.load(Vorgang.class, 1);
//		Vorgang test = (Vorgang) c.getVorgangListe().toArray()[0];
//		System.out.println(test.getMaschinenzeit());
//		System.out.println(d.getArbeitsplan());
//		
//		session.getTransaction().commit();
//		session.close();
//		sessionFactory.close();
		
	}
}