package mps.redundant;

import java.rmi.Remote;
import java.rmi.RemoteException;

import mps.core.auftragsUndAngebotsVerwaltung.Angebot;

public interface IMpsServer extends Remote {
    boolean isDeaktiviert() throws RemoteException;
    void setisDeaktiviert(boolean b) throws RemoteException;
    String getName() throws  RemoteException;
    
	void createAuftrag(boolean b, String string, Angebot a)throws RemoteException;
	Angebot createAngebot(String string, String string2, int i, Long long1)throws RemoteException;
}
