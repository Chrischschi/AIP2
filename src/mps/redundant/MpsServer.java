package mps.redundant;

import mps.redundant.Config;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class MpsServer extends Mps implements IMpsServer{
    private String serverName;
    private Heartbeat heartBeat;
    private boolean isDeaktiviert = false;

    private MpsServer(String serverName, Heartbeat heartBeat) {
        this.serverName = serverName;
        this.heartBeat = heartBeat;
    }

    public static MpsServer create(String serverName) throws RemoteException {
        Heartbeat heartBeat = new Heartbeat(serverName);
        MpsServer mpsSever = new MpsServer(serverName, heartBeat);
        IMpsServer stub = (IMpsServer)UnicastRemoteObject.exportObject(mpsSever, 0); //Damit wird der Server in die RMI-Runtime eingetragen, praktisch die server schleife

        Registry registry = LocateRegistry.getRegistry(Config.REGISTRY_HOST, Config.REGISTRY_PORT); //holt sich die entfernte registry
        registry.rebind(serverName, stub); //treagt den MpsServer unter dem ServerName in die Registry ein

        heartBeat.start();

        return mpsSever;
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
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
//    	 if (System.getSecurityManager() == null) {
//             System.setSecurityManager(new SecurityManager());
//         }
//         try {
        MpsServer serverEins = MpsServer.create(Config.HAWMPS1_NAME);
        MpsServer.create(Config.HAWMPS2_NAME);
        
        // TEST CODE 
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        serverEins.heartBeat.interrupt();
        //END TEST CODE
        
        System.out.println("Cleaning up mps1");
        serverEins = null;
        System.gc();
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Restarting mps1");
        serverEins = MpsServer.create(Config.HAWMPS1_NAME);
        
//         } catch (Exception e) {
//             System.err.println("MpsServer exception:");
//             e.printStackTrace();
//         }
    }

}