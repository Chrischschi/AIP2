package mps.szenario;

import mps.core.auftragsUndAngebotsVerwaltung.Adresse;
import mps.core.auftragsUndAngebotsVerwaltung.EAngebot;
import mps.core.auftragsUndAngebotsVerwaltung.EKunde;
import mps.core.auftragsUndAngebotsVerwaltung.Kunde;
import mps.core.auftragsUndAngebotsVerwaltung.KundeRepository;
import mps.redundant.Config;
import mps.redundant.dispatcher.IDispatcher;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TestClient {
	public static IDispatcher getDispatcher(String dispatcherHost,
			int dispatcherPort) throws RemoteException, NotBoundException {
		Registry dispatcherRegistry = LocateRegistry.getRegistry(
				dispatcherHost, dispatcherPort);
		IDispatcher remoteDispatcher = (IDispatcher) dispatcherRegistry
				.lookup(Config.DISPATCHER_NAME);
		return remoteDispatcher;
	}

	public static void main(String[] args) throws RemoteException,
			NotBoundException {
		if (args.length == 2) {
			IDispatcher remoteDispatcher = getDispatcher(args[0],
					Integer.parseInt(args[1]));

			EKunde k = remoteDispatcher.getRemoteServerInstance().createKunde("SAP", new Adresse("Holunderstrasse",25,null));
			EKunde k2 = remoteDispatcher.getRemoteServerInstance().createKunde("Siemens", new Adresse("Petersallee",10,null));
			
			EAngebot a = remoteDispatcher.getRemoteServerInstance()
					.createAngebot(k,"10.05.2014", "17.05.2014", 50, new Long(1));
			EAngebot a2 = remoteDispatcher.getRemoteServerInstance()
					.createAngebot(k2,"10.05.2014", "17.05.2014", 50, new Long(1));
			EAngebot a3 = remoteDispatcher.getRemoteServerInstance()
					.createAngebot(k2,"10.05.2014", "17.05.2014", 50, new Long(2));

			remoteDispatcher.getRemoteServerInstance().createAngebot(k2,
					"10.05.2014", "17.05.2014", 50, new Long(3));
			remoteDispatcher.getRemoteServerInstance().createAuftrag(false,
					"17.05.2014", a);
			remoteDispatcher.getRemoteServerInstance().createAuftrag(false,
					"17.05.2014", a2);
			remoteDispatcher.getRemoteServerInstance().createAuftrag(false,
					"17.05.2014", a3);
			
			System.out.println("Press any key to send a Transport request to the UPPS web service");
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Kunde k_1 = KundeRepository.getByID(1);
			remoteDispatcher.getRemoteServerInstance().sendeTransportAuftrag(k_1,null);
		} else
			System.err
					.println("please specify the ip adress and port of the server on which the dispatcher is running");

	}
}