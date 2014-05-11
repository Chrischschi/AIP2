package mps.redundant;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import mps.redundant.Config;


public class Heartbeat extends Thread{
    private String serverName;
    public static final int hearBeatInterval = 1000; //in Millisekunden

    public Heartbeat(String serverName){
      this.serverName = serverName;
    }


   public void run(){
       while(!interrupted()){
           try {
               Registry monitorRegistry = LocateRegistry.getRegistry(Config.REGISTRY_HOST, Config.REGISTRY_PORT);
               IMonitor monitor = (IMonitor)monitorRegistry.lookup(Config.MONITOR_NAME);
               monitor.alive(serverName);
               System.out.println("HeartBeat gesendet von "+ serverName);
               sleep(hearBeatInterval);
           } catch (RemoteException e) {
               e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           } catch (InterruptedException e) {
               e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           } catch (NotBoundException e) {
               e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           }
       }
   }
}