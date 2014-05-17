package mps.redundant;

import mps.redundant.Config;
import mps.redundant.MonitorGUI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class Monitor implements IMonitor {
    public Dispatcher dispatcher;
    public HashMap<String, Timer> aliveTimer;
    public Registry hawmps1reg;
    public Registry hawmps2reg;

    private Monitor(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.aliveTimer = new HashMap<String, Timer>();
    }

    public static Monitor create(Dispatcher dispatcher) throws RemoteException {
        Monitor monitor = new Monitor(dispatcher);
        IMonitor stub = (IMonitor) UnicastRemoteObject.exportObject(monitor, 0);

        Registry heartBeatRegistry = dispatcher.serverRegistry;
        heartBeatRegistry.rebind(Config.MONITOR_NAME, stub); //treagt den Monitor unter dem namen in die Registry ein
        System.out.println("Namen in Registry:"+ Arrays.toString(dispatcher.serverRegistry.list()));
        return monitor;
    }
    
    public void getMpsServerRegistry(String serverName, String host, int port) throws RemoteException{
    	if(serverName.equals("hawmps1")){
    	hawmps1reg = LocateRegistry.getRegistry(host, port);}
    	else{
    	hawmps2reg = LocateRegistry.getRegistry(host, port);}
    }

    @Override
    public void alive(String serverName) {
        System.out.println("HeartBeat empfangen: " + serverName);
        if (aliveTimer.containsKey(serverName)) {
            aliveTimer.get(serverName).cancel();
            aliveTimer.remove(serverName);
        }
        aliveTimer.put(serverName, startTimer(serverName));
        changeZustand(serverName, MonitorGUI.Zustand.online);


        try {
        	if(serverName.equals("hawmps1")){
            dispatcher.alive((IMpsServer) hawmps1reg.lookup(serverName));}
        	else{
        		dispatcher.alive((IMpsServer) hawmps2reg.lookup(serverName));
        	}
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NotBoundException e) {
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void notAlive(String serverName) {
        aliveTimer.remove(serverName);
        try {
            IMpsServer server = (IMpsServer) dispatcher.serverRegistry.lookup(serverName);
            dispatcher.notAlive(server);
            changeZustand(serverName, MonitorGUI.Zustand.offline);
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NotBoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private Timer startTimer(final String serverName) {
        final Monitor monitor = this;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                monitor.notAlive(serverName);
            }
        }, Config.MONITOR_SERVERTIMEOUT);
        return timer;
    }

    private void changeZustand(String serverName, MonitorGUI.Zustand zustand) {
        IMpsServer derServer = null;
        try {
            for (IMpsServer server : dispatcher.serverList) {
                    if (server.getName().equals(serverName)) {
                        derServer = server;
                    }
            }

            if ((derServer != null && !derServer.isDeaktiviert()) || zustand.equals(MonitorGUI.Zustand.offline)) {
                if (serverName.equals(Config.HAWMPS1_NAME)) {
                        MonitorGUI.getInstance().changeZustandMPS1(zustand);
                } else if (serverName.equals(Config.HAWMPS2_NAME)) {
                        MonitorGUI.getInstance().changeZustandMPS2(zustand);
                } else {
                    throw new UnknownError("Unbekannter Servername: " + serverName);
                }
            }


        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}