package com.sdia.AES;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class CustomerContainer {
    public static void main(String[] args) throws ControllerException {
        Runtime runtime = Runtime.instance();//Define the runtime of the plateforme
        ProfileImpl profile = new ProfileImpl(); //the profile of the plateforme
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer subContainer = runtime.createAgentContainer(profile);
        subContainer.start();
        System.out.println(">> The customerContainer is ON...");
        //Partie des clés : les clés sont encodées ( en string )
        String publicKey = "1234566543219870";
        AgentController agent = subContainer.createNewAgent("customer","com.sdia.AES.CustomerAgent", new Object[]{publicKey});
        agent.start();
    }
}
