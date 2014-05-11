package mps.redundant;

import mps.redundant.Config;
import mps.redundant.MonitorGUI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;



public class Dispatcher implements IDispatcher{
    public Registry serverRegistry;
    public ArrayList<IMpsServer> serverList;
    public int roundRobinCounter = 0;
    public static Dispatcher dispatcher = null;

    public Dispatcher() throws RemoteException {
        dispatcher = this;
        this.serverRegistry = LocateRegistry.createRegistry(Config.REGISTRY_PORT);
        this.serverList = new ArrayList<IMpsServer>();
    }

    public static Dispatcher create() throws RemoteException {
        if(dispatcher == null){
            dispatcher = new Dispatcher();
            IDispatcher stub = (IDispatcher) UnicastRemoteObject.exportObject(dispatcher, 0);

            Registry dispatcherRegistry = dispatcher.serverRegistry;
            dispatcherRegistry.rebind(Config.DISPATCHER_NAME, stub); //treagt den dispatcher unter dem namen in die Registry ein
        }
        return dispatcher;
    }

    @Override
    public IMpsServer getRemoteServerInstance() throws RemoteException{
        try {
            System.out.println("Namen in Registry:"+ Arrays.toString(serverRegistry.list()));
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return getNextRemoteObject();
    }

    private synchronized IMpsServer getNextRemoteObject(){
        IMpsServer server = null;
        try {
            server = getNextActiveServer();
            while (server == null) {
                Thread.sleep(5000);
                server = getNextActiveServer();
            }
            System.out.println(server.getName());
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return server;
    }

    private IMpsServer getNextActiveServer() throws RemoteException {
       roundRobinCounter %= serverList.size();
       IMpsServer server;
       while(roundRobinCounter < serverList.size()) {
            server = serverList.get(roundRobinCounter);
            if (!server.isDeaktiviert()) {
                //TODO dirty aber geht erstmal nicht besser
                if(server.getName().equals(Config.HAWMPS1_NAME)){MonitorGUI.getInstance().erhoeheAnfragenVonMPS1();}
                if(server.getName().equals(Config.HAWMPS2_NAME)){MonitorGUI.getInstance().erhoeheAnfragenVonMPS2();}
                roundRobinCounter++;
                return server;
            }
           roundRobinCounter++;
           if(roundRobinCounter == serverList.size()) roundRobinCounter = 0;
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public synchronized void alive(IMpsServer serverInstance){
        if(!serverList.contains(serverInstance)){
            serverList.add(serverInstance);
        }
    }

    public synchronized void notAlive(IMpsServer serverInstance){
        if(serverList.contains(serverInstance)){
            serverList.remove(serverInstance);
        }
    }

    public void deaktiviereServerInstanz(String servername,boolean b) {
        for (IMpsServer server : serverList) {
            try{
                if (server.getName().equals(servername)) {
                        server.setisDeaktiviert(b);

                }
            } catch (RemoteException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public boolean isServerOnline(String servername) {
        for (IMpsServer iMpsServer : serverList) {
            try {
                if (servername.equals(iMpsServer.getName()))
                    return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    
    public static void main(String[] args) throws RemoteException, InterruptedException, NotBoundException {
//    	   if (System.getSecurityManager() == null) {
//               System.setSecurityManager(new SecurityManager());
//           }
//           try {
        JFrame frame = new JFrame("MonitorGUI");
        MonitorGUI x = new MonitorGUI(frame);
        frame.setContentPane(x.monitorGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        Dispatcher dispatcher = Dispatcher.create();
        Monitor.create(dispatcher);

        //damit die mps server s1 und s2 eingetragen sind
        Thread.sleep(1500);
//           } catch (Exception e) {
//               System.err.println("Dispatcher exception:");
//               e.printStackTrace();
//           }
    }
    
    
    
}