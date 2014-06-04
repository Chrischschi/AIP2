package mps.core.auftragsUndAngebotsVerwaltung;

import mps.core.fertigung.IFertigung;
import mps.core.rechnung.IRechnungen;
import mps.core.auftragsUndAngebotsVerwaltung.HibernateUtil;

import org.hibernate.Session;

public class AuftragRepository {

	static EAuftrag readAuftrag(Long auftragNr) {
		Session session = HibernateUtil.beginTransaction();

		Auftrag auftrag = (Auftrag) session.get(Auftrag.class, auftragNr);
		HibernateUtil.commitTransaction();

		return auftrag;
	}

	public static Auftrag createPersistent(boolean istAbgeschlossen,
			String beauftragtAm, Angebot angebot) {
		// Create transient object
		Auftrag auftrag = Auftrag.create(istAbgeschlossen, beauftragtAm);

		Session session = HibernateUtil.beginTransaction();

		// Set references
		auftrag.setAngebot(angebot);
		auftrag.setFertigungsauftragNr(null); // currently unknown
		// Set reference back
		angebot.setAuftrag(auftrag);

		// Update the newly created object
		long aid = (long) session.save(auftrag);
		// Update the already existing object
		session.merge(angebot);

		auftrag = (Auftrag) session.get(Auftrag.class, aid);

		Long fertigungsPlanNr = IFertigung.getFertigungService()
				.fertigungsPlanErstellen(auftrag.getNr(),
						auftrag.getAngebot().getBauteilNr());

		Long rechnungNr = IRechnungen.getRechnungService().rechnungErstellen(
				angebot.getPreis(), auftrag.getNr());

		auftrag.setFertigungsauftragNr(fertigungsPlanNr);
		auftrag.setRechnungNr(rechnungNr);

		session.merge(auftrag);

		HibernateUtil.commitTransaction();

		return auftrag;
	}

	/**
	 * Updates the persistent state of an Object in the Database by merging the
	 * object
	 * 
	 * @param auftrag
	 *            - the Persistent Object to be updated
	 */
	static void persistUpdated(Auftrag auftrag) {
		Session session = HibernateUtil.beginTransaction();

		session.merge(auftrag);

		HibernateUtil.commitTransaction();
	}

	public static Auftrag getByID(Long auftragNr) {
		Session session = HibernateUtil.beginTransaction();
	
		Auftrag result = (Auftrag) session.get(Auftrag.class, auftragNr);

		HibernateUtil.commitTransaction();

		return result;

	}

}
