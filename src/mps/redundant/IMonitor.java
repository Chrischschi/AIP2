package mps.redundant;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMonitor extends Remote {
    public void alive(String serverName) throws RemoteException, NotBoundException;
}