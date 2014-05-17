package mps.redundant;

import mps.redundant.Config;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class MpsServer extends Mps implements IMpsServer{
    private String serverName;
    private Heartbeat heartBeat;
    private boolean isDeaktiviert = false;
    public Registry serverRegistry;

    private MpsServer(String serverName, Heartbeat heartBeat, int ownPort) throws RemoteException {
        this.serverName = serverName;
        this.heartBeat = heartBeat;
        this.serverRegistry = LocateRegistry.createRegistry(ownPort);
        IMpsServer stub = (IMpsServer)UnicastRemoteObject.exportObject(this, 0); //Damit wird der Server in die RMI-Runtime eingetragen, praktisch die server schleife
        serverRegistry.rebind(serverName, stub); //treagt den MpsServer unter dem ServerName in die Registry ein
    }

    public static MpsServer create(String serverName,String ownHost, int ownPort, String dispatcherHost, int dispatcherPort) throws RemoteException, NotBoundException {
        Heartbeat heartBeat = new Heartbeat(serverName, dispatcherHost, dispatcherPort);
        MpsServer mpsServer = new MpsServer(serverName, heartBeat,ownPort); 

       
        Registry dispatcherRegistry = LocateRegistry.getRegistry(dispatcherHost, dispatcherPort); //holt sich die entfernte registry
        IMonitor monitor = (IMonitor) dispatcherRegistry.lookup(Config.MONITOR_NAME);
        monitor.getMpsServerRegistry(serverName, ownHost, ownPort);
        heartBeat.start();

        return mpsServer;
    }

    @Override
    public boolean isDeaktiviert() {
        return isDeaktiviert;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setisDeaktiviert(boolean b) {
        isDeaktiviert = b;
    }

    @Override
    public String getName() throws RemoteException {
        return serverName;
    }
    
    
    
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
//    	 if (System.getSecurityManager() == null) {
//             System.setSecurityManager(new SecurityManager());
//         }
//         try {
    	if(args.length == 5){
    		if(args[0].equals(Config.HAWMPS1_NAME)) {
    			MpsServer.create(Config.HAWMPS1_NAME,args[1],Integer.parseInt(args[2]),args[3],Integer.parseInt(args[4]));
    		}
    		else if(args[0].equals(Config.HAWMPS2_NAME)) {
    			MpsServer.create(Config.HAWMPS2_NAME,args[1],Integer.parseInt(args[2]),args[3],Integer.parseInt(args[4]));
    		}
    		
    		else System.err.println("choose \"hawmps1\" or \"hawmps2\" ");
    	}
    	else System.err.println("Parameters: Servername, Server Adress, Server Port,Dispatcher Adress, Dispatcher Port");
//      } catch (Exception e) {
//      System.err.println("Dispatcher exception:");
//      e.printStackTrace();
//  }
    	
    }
}