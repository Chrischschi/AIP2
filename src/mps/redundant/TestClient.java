package mps.redundant;


import mps.core.auftragsUndAngebotsVerwaltung.Angebot;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class TestClient {
    public static IDispatcher getDispatcher() throws RemoteException, NotBoundException {
        Registry dispatcherRegistry = LocateRegistry.getRegistry(Config.REGISTRY_HOST, Config.REGISTRY_PORT);
        IDispatcher remoteDispatcher = (IDispatcher)dispatcherRegistry.lookup(Config.DISPATCHER_NAME);
        return remoteDispatcher;
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
    	if(args.length == 2){
			Config.REGISTRY_HOST = args[0];
    		Config.REGISTRY_PORT = Integer.parseInt(args[1]);
        IDispatcher remoteDispatcher = getDispatcher();
        Angebot a = remoteDispatcher.getRemoteServerInstance().createAngebot("10.05.2014", "17.05.2014", 50, new Long(1));
        Angebot a2 = remoteDispatcher.getRemoteServerInstance().createAngebot("10.05.2014", "17.05.2014", 50, new Long(1));
        Angebot a3 = remoteDispatcher.getRemoteServerInstance().createAngebot("10.05.2014", "17.05.2014", 50, new Long(2));
        remoteDispatcher.getRemoteServerInstance().createAngebot("10.05.2014", "17.05.2014", 50, new Long(3));
        remoteDispatcher.getRemoteServerInstance().createAuftrag(false, "17.05.2014", a);
        remoteDispatcher.getRemoteServerInstance().createAuftrag(false, "17.05.2014", a2);
        remoteDispatcher.getRemoteServerInstance().createAuftrag(false, "17.05.2014", a3);
    	}
    	else System.err.println("please specify the ip adress and port of the server on which the dispatcher is running");

    }
}