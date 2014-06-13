package mps.core.fertigung.dao;

import org.hibernate.HibernateException;

import mps.core.fertigung.Fertigungsauftrag;
import mps.core.fertigung.dao.HibernateUtil;

public class FertigungsauftragManager {

    private static FertigungsauftragDAO fertigungsauftragDAO = new FertigungsauftragDAO();
	
	public static long saveFertigungsauftrag(Fertigungsauftrag v){
        try {
            long id = fertigungsauftragDAO.save(v);
            return id;
        } catch (HibernateException ex) {
            System.out.println("Fertigungsauftrag konnte nicht gespeichert werden");
            HibernateUtil.rollbackTransaction();
        }
        return -1;
	}
	
	
	public static Fertigungsauftrag loadFertigungsauftrag(Long id){
        try {
            Fertigungsauftrag res = fertigungsauftragDAO.findByID(Fertigungsauftrag.class, id);
            return res;
        } catch (HibernateException ex) {
            System.out.println("Fertigungsauftrag konnte nicht geladen werden");
            HibernateUtil.rollbackTransaction();
        }
        return null;
	}
	
}
