package com.sdia.RSA;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class CustomerContainer {
    public static void main(String[] args) throws ControllerException {
        Runtime runtime = Runtime.instance();//Define the runtime of the plateforme
        ProfileImpl profile = new ProfileImpl(false); //the profile of the plateforme
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer subContainer = runtime.createAgentContainer(profile);
        subContainer.start();
        System.out.println(">> The customerContainer is ON...");
        //Partie des clés : les clés sont encodées ( en string )
        String publicKeyOfServer = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIKyQW5uokTs808OtTCAXs+xuPxQwuSZn3fBit8mvn4DVUnuJQWX4SfKVUWWXBtRbTokjOQ8jZs+Ai0gn4IX3pECAwEAAQ==";
        String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEArc3/CUsangY3Rm4gjVD307/A/+1YBaHO5Qa5XbkVOz2QjgJVUGqOU9fClCzvf9MSgN7MarLmjqOi/8B2ivLvwwIDAQABAkAoYoEsK6NXrKITX2t46qNrNFcoIIIfbOWiGdngiS0BwnzfM1qUl2ZW5btBDsdJ7g0VKQ0ihDFmMdu/oEf8mpshAiEAsVpqYBVyLxCtqwOR29lhFO3JF5idDTGGXiwp3uAndvcCIQD64L6X9oyY2jHJ/CmyVd0gsICE8yW4X66ecER+TRRelQIgAuantsqjA4ISVSnJyP7VR7JcB7bHWt/kgsXWKX8hJiECIQCCEKyfalJAGLdBabtQWtW20er/gnOm3+xO9hN8i4eo9QIgefjt4PMo6H0B1XhszAtM+r9twa4joh24bD3BKnIhMTY=";

        AgentController agent = subContainer.createNewAgent("customer","com.sdia.RSA.CustomerAgent", new Object[]{publicKeyOfServer});
        agent.start();

    }
}
