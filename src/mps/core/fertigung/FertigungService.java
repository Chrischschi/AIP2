package mps.core.fertigung;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;


import mps.core.fertigung.dao.BauteilManager;
import mps.core.fertigung.dao.FertigungsauftragManager;

public class FertigungService implements IFertigung {
	public Long fertigungsPlanErstellen(Long auftragNr){
		//TODO when Auftrag done
		long bid = 1;
		//long bid = IAuftraege.getAuftragService().getBauteilIdOfAutrag(auftragNr);
		//IAuftraege a = FertigungRepository.read(IAuftraege.class,auftragNr);
		
		Fertigungsauftrag f = new Fertigungsauftrag();
		//long fid = FertigungsauftragManager.
		Bauteil b = BauteilManager.loadBauteil(bid);
		f.setAuftrag(auftragNr);
		f.setBauteil(b);
		long fid = FertigungsauftragManager.saveFertigungsauftrag(f);
		
		fertigungsplanDrucken(fid, f);
		
		return fid;
	}
	private void fertigungsplanDrucken(Long fid, Fertigungsauftrag f){
		Path filePath = Paths.get("MPSFertigungsplan.txt"); //TODO eventuell ein unterverzeichnis "outputFiles" einfuehren
		File file = filePath.toAbsolutePath().toFile();
		String output = FertigungsauftragManager.loadFertigungsauftrag(fid).toString();
			//Using Java 7 feature try-with-resources to close writer after the IO operation
	        try(Writer out = new BufferedWriter(new OutputStreamWriter(
        			new FileOutputStream(file), Charset.forName("UTF-8")))){
	        	
	        	out.append(output);
	        	out.flush();
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
