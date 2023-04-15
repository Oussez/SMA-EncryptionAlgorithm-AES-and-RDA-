package com.sdia.RSA;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class ServerContainer {
    public static void main(String[] args) throws ControllerException {
        Runtime runtime = Runtime.instance();//Define the runtime of the plateforme
        ProfileImpl profile = new ProfileImpl(); //the profile of the plateforme
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer subContainer = runtime.createAgentContainer(profile);
        subContainer.start();
        System.out.println(">> The serverContainer is ON...");
        String publicKeyOfCustomer = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK3N/wlLGp4GN0ZuII1Q99O/wP/tWAWhzuUGuV25FTs9kI4CVVBqjlPXwpQs73/TEoDezGqy5o6jov/Adory78MCAwEAAQ==";
        String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAgrJBbm6iROzzTw61MIBez7G4/FDC5Jmfd8GK3ya+fgNVSe4lBZfhJ8pVRZZcG1FtOiSM5DyNmz4CLSCfghfekQIDAQABAkAR5BZHkxGCNJrh+aDIBj/0V98mJbq9fGIeDKUsAwEUqBS7AWGKaU+ZvMoUr5lR1cihoqZ1EzJMq5RGvi0xapeZAiEA/UQ56ZMpezgHBv0wPpCkdXEPGepRKtnYeXUMbU1Spd8CIQCEG15h4WjOD4VoiAD3jH1M85V5mrKKzcjPXQTsCjOpjwIhANHXYZRh1hC9zzS/OuXbuieEUVrLhgzXCkE39d/qHPxJAiAA1BcccAhzOl+wzyGKa+QhRmnKkHDMNAwtWLz+KfY2+wIhAKBzPnoUdX+yPZL5ZihqVQRae20wEytj3XTKIw3sJ1ZD";
        AgentController agent = subContainer.createNewAgent("server","com.sdia.RSA.ServerAgent", new Object[]{privateKey,publicKeyOfCustomer});
        agent.start();


    }
}
