package extern.uppsTransportService.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/* Provided by UPPS */
@XmlRootElement
public class TransportRequestData {
	
	@XmlElement
	public Name name; 
	@XmlElement
	public Address address; 
	@XmlElement
	public DeliveryItem deliveryItem;

}
