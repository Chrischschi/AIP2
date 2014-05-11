package mps.redundant;

import java.rmi.registry.Registry;


public class Config {
    public static final int REGISTRY_PORT = Registry.REGISTRY_PORT; // standard RegistryPort
    public static final String REGISTRY_HOST = "localhost"; //ist selbe adresse wie die des dispatchers
    public static final String MONITOR_NAME = "monitor";
    public static final String DISPATCHER_NAME = "dispatcher";
    public static final String HAWMPS1_NAME = "hawmps1";
    public static final String HAWMPS2_NAME = "hawmps2";
}