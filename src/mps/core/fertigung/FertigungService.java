package mps.core.fertigung;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Set;


public class FertigungService implements IFertigung {
	public long fertigungsPlanErstellen(long auftragNr){
		FertigungRepository.open();
		
		//TODO when Auftrag done
		long bid = 1;
		//long bid = IAuftraege.getAuftragService().getBauteilIdOfAutrag(auftragNr);
		//IAuftraege a = FertigungRepository.read(IAuftraege.class,auftragNr);
		
		Fertigungsauftrag f = new Fertigungsauftrag();
		long fid = FertigungRepository.create(f);
		Bauteil b = FertigungRepository.read(Bauteil.class, bid);
		f = FertigungRepository.read(Fertigungsauftrag.class, fid);
		f.setAuftrag(auftragNr);
		f.setBauteil(b);
		Set<Fertigungsauftrag> fl= b.getFertigungsauftragListe();
		fl.add(f);
		b.setFertigungsauftragListe(fl);
		FertigungRepository.update(f);
		FertigungRepository.update(b);
		FertigungRepository.close();
		
		fertigungsplanDrucken(fid);
		
		return fid;
	}
	
	private void fertigungsplanDrucken(long fid){
		FertigungRepository.open();
	       BufferedWriter writer = null;
	        try {
	            writer = new BufferedWriter(new FileWriter("C:/test.txt",true));
	            writer.write(FertigungRepository.read(Fertigungsauftrag.class, fid).toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                // Close the writer regardless of what happens...
	                writer.close();
	            } catch (Exception e) {
	            }
	        }
	        FertigungRepository.close();
	}
}
