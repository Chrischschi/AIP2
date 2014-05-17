package mps.redundant;

import java.rmi.registry.Registry;


public class Config {
    public static int REGISTRY_PORT; // standard RegistryPort
    public static String REGISTRY_HOST; //ist selbe adresse wie die des dispatchers
    public static final String MONITOR_NAME = "monitor";
    public static final String DISPATCHER_NAME = "dispatcher";
    public static final String HAWMPS1_NAME = "hawmps1";
    public static final String HAWMPS2_NAME = "hawmps2";
    public static final int MONITOR_SERVERTIMEOUT = 10000;
    public static final int HEARTBEAT_INTERVALL = 1000;
}