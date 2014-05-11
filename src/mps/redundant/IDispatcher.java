package mps.redundant;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IDispatcher extends Remote {
    public IMpsServer getRemoteServerInstance() throws RemoteException;

}