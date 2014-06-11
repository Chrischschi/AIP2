package transportDienstAdapterREST;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import extern.uppsTransportService.model.TransportRequestData;

public class RESTConnector {
	
	
	public static String SCENARIO_URI = "http://localhost:8080/uppsRESTApi/";
	public static String SCENARIO_PATH = "transport/request";
	
	//resources
	private WebTarget target;
	private Client client;
	
	//Data
	private String uri; //supply the uri of the web service you want to connect to 
	
	public RESTConnector() {
		this(SCENARIO_URI);
	}
	
	public RESTConnector(String uri) {
		this.uri = uri;
		this.client = ClientBuilder.newClient();
		this.client.register(new org.glassfish.jersey.moxy.json.MoxyJsonFeature());
		
		this.target = this.client.target(this.uri);
	}
	
	
	public long submitTransportRequest(TransportRequestData tr) {
		//convert data to json (our message body)
		Entity<TransportRequestData> payload = Entity.xml(tr);
		
		//send message via POST method
		Response response = target.path(SCENARIO_PATH).request().post(payload);
		
		//transport request submitted to web service.
		
		//read id from the server response
		long transportRequestId = response.readEntity(Long.class);
		
		return transportRequestId;
	}

}
