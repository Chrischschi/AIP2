package extern.uppsTransportService.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import mps.core.auftragsUndAngebotsVerwaltung.Kunde;
import mps.core.fertigung.Bauteil;


@XmlRootElement
public class TransportRequestData {
	
	public TransportRequestData() {};
	
	@XmlElement
	public Kunde deliveryReciever;
	@XmlElement
	public Bauteil deliveryItem;

}
