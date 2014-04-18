package mps.core.fertigung;


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

	/** Create Operations */
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
	
	// CRUD OPERATIONS ---------------------------------
	
	public static long create(Object o){
		long id = (long) session.save(o);
		return id;
	}
	
	public static <T> T read(Class<T> t, long id){
		@SuppressWarnings("unchecked")
		T object = (T) session.load(t, id);
		return object;
	}
	public static void update(Object o){
		session.merge(o);
		
	}
	
	public static <T> void delete(Class<T> t, long id){
		@SuppressWarnings("unchecked")
		T object = (T) session.load(t, id);
		session.delete(object);
	}

	// --------------------------------------------------
	
	// DATENTYP IN DATENBANK ERSTELLEN ------------------
	
	public static void erstelleBauteil(String name){
		open();
		Bauteil b = new Bauteil();
		b.setName(name);
		create(b);
		close();
		}
	
	public static void erstelleStueckliste(String gueltigAb,String gueltigBis){
		open();
		Stueckliste s = new Stueckliste();
		s.setGueltigAb(gueltigAb);
		s.setGueltigBis(gueltigBis);
		create(s);
		close();
	}
	
	public static void erstelleStuecklistenPosition(long menge){
		open();
		StuecklistenPosition sp = new StuecklistenPosition();
		sp.setMenge(menge);
		create(sp);
		close();
	}
	
	public static void erstelleArbeitsplan(){
		open();
		Arbeitsplan a = new Arbeitsplan();
		create(a);
		close();
	}
	
	public static void erstelleVorgang(VorgangArtTyp typ, long ruestzeit, long maschinenzeit, long personenzeit){
		open();
		Vorgang v = new Vorgang();
		v.setVorgangArtTyp(typ);
		v.setRuestzeit(ruestzeit);
		v.setMaschinenzeit(maschinenzeit);
		v.setPersonenzeit(personenzeit);
		create(v);
		close();
	}
	
	// --------------------------------------------------
	
	// ASSOZIATIONEN IN DATENBANK ERSTELLEN ------------------
	
	public static void assoziationBauteilArbeitsplan(long b_id, long a_id){
		open();
		Bauteil b = read(Bauteil.class,b_id);
		Arbeitsplan a = read(Arbeitsplan.class,a_id);
		b.setArbeitsplan(a);
		a.setBauteil(b);
		update(b);
		update(a);
		close();
	}
	
	public static void assoziationArbeitsplanVorgang(long a_id, long v_id){
		open();
		Arbeitsplan a = read(Arbeitsplan.class,a_id);
		Vorgang v = read(Vorgang.class,v_id);
		List<Vorgang> temp = a.getVorgangListe();
		temp.add(v);
		a.setVorgangListe(temp);
		v.setArbeitsplan(a);
		update(a);
		update(v);
		close();
	}
	
	public static void assoziationStueckListeStuecklistenPosition(long s_id, long sp_id){
		open();
		Stueckliste s = read(Stueckliste.class,s_id);
		StuecklistenPosition sp = read(StuecklistenPosition.class,sp_id);
		Set<StuecklistenPosition> temp = s.getStuecklistenPosition();
		temp.add(sp);
		s.setStuecklistenPosition(temp);
		sp.setStueckliste(s);
		update(s);
		update(sp);
		close();
	}
	
	
	public static void assoziationBauteilStuecklistenPosition(long b_id, long sp_id){
		open();
		Bauteil b = read(Bauteil.class,b_id);
		StuecklistenPosition sp = read(StuecklistenPosition.class,sp_id);
		Set<StuecklistenPosition> temp = b.getStuecklistenPosition();
		temp.add(sp);
		b.setStuecklistenPosition( temp);
		sp.setBauteil(b);
		update(b);
		update(sp);
		close();
	}
	
	public static void assoziationBauteilStueckliste(long b_id, long s_id){
		open();
		Bauteil b = read(Bauteil.class,b_id);
		Stueckliste s = read(Stueckliste.class,s_id);
		b.setStueckliste(s);
		s.setBauteil(b);
		update(b);
		update(s);
		close();
	}
	
	// --------------------------------------------------
	
	public static void main(String[] args) {
//----------------------Test Datenbankbefuellung------------------------	
	szenario();	
	}

	
	static void szenario(){
		erstelleBauteil("Maehdrescher");
		erstelleBauteil("Motor");
		erstelleBauteil("Reifen");
		erstelleBauteil("Schrauben");
		erstelleStueckliste("heute","morgen");
		erstelleStueckliste("heute","morgen");
		erstelleStuecklistenPosition(1);
		erstelleStuecklistenPosition(4);
		erstelleStuecklistenPosition(20);
		erstelleArbeitsplan();
		erstelleArbeitsplan();
		erstelleVorgang(VorgangArtTyp.MONTAGE, 10, 15, 20);
		erstelleVorgang(VorgangArtTyp.MONTAGE, 30, 30, 30);
		erstelleVorgang(VorgangArtTyp.MONTAGE, 35, 40, 50);
		erstelleVorgang(VorgangArtTyp.BEREITSTELLUNG, 1, 2, 3);
		erstelleVorgang(VorgangArtTyp.BEREITSTELLUNG, 4, 5, 6);
		assoziationBauteilStueckliste(1,1);
		assoziationBauteilStueckliste(2,2);
		assoziationStueckListeStuecklistenPosition(1,1);
		assoziationStueckListeStuecklistenPosition(1,2);
		assoziationStueckListeStuecklistenPosition(2,3);
		assoziationBauteilStuecklistenPosition(2,1);
		assoziationBauteilStuecklistenPosition(3,2);
		assoziationBauteilStuecklistenPosition(4,3);
		assoziationBauteilArbeitsplan(1,1);
		assoziationBauteilArbeitsplan(2,2);
		assoziationArbeitsplanVorgang(1,1);
		assoziationArbeitsplanVorgang(1,2);
		assoziationArbeitsplanVorgang(1,3);
		assoziationArbeitsplanVorgang(2,4);
		assoziationArbeitsplanVorgang(2,5);
		FertigungService fs = new FertigungService();
    	fs.fertigungsPlanErstellen(1);

	}
}
