package mps.redundant.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import mps.core.auftragsUndAngebotsVerwaltung.EAngebot;

public interface IMpsServer extends Remote {
	boolean isDeaktiviert() throws RemoteException;

	void setisDeaktiviert(boolean b) throws RemoteException;

	String getName() throws RemoteException;

	void createAuftrag(boolean b, String string, EAngebot a)
			throws RemoteException;

	EAngebot createAngebot(String string, String string2, int i, Long long1)
			throws RemoteException;
}
