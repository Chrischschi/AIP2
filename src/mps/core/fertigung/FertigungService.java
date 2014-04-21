package mps.core.fertigung;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		Path filePath = Paths.get("MPSFertigungsplan.txt"); //TODO eventuell ein unterverzeichnis "outputFiles" einfuehren
		File file = filePath.toAbsolutePath().toFile();
		FertigungRepository.open();
			//Using Java 7 feature try-with-resources to close writer after the IO operation
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true)); ) {
	            writer.write(FertigungRepository.read(Fertigungsauftrag.class, fid).toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } //finally block not needed, because writer is closed after the try clock
	    FertigungRepository.close();
	}
}
