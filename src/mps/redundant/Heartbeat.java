package mps.redundant;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import mps.redundant.Config;


public class Heartbeat extends Thread{
    private String serverName;
    private String dispatcherHost;
    private int dispatcherPort;

    public Heartbeat(String serverName, String dispatcherHost, int dispatcherPort){
      this.serverName = serverName;
      this.dispatcherHost = dispatcherHost;
      this.dispatcherPort = dispatcherPort;
    }


   public void run(){
       while(!this.isInterrupted()){
           try {
               Registry monitorRegistry = LocateRegistry.getRegistry(dispatcherHost, dispatcherPort);
               IMonitor monitor = (IMonitor)monitorRegistry.lookup(Config.MONITOR_NAME);
               monitor.alive(serverName);
               System.out.println("HeartBeat gesendet von "+ serverName);
               sleep(Config.HEARTBEAT_INTERVALL);
           } catch (RemoteException e) {
               e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           } catch (InterruptedException e) {
        	   Thread.currentThread().interrupt();
               e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           } catch (NotBoundException e) {
               e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           }
       }
   }
}