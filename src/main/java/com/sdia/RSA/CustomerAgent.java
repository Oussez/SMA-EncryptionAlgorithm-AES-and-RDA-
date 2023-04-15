package com.sdia.RSA;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class CustomerAgent extends Agent {
    private String publicKeyOfServer;
    @Override
    protected void setup() {
        publicKeyOfServer = (String) getArguments()[0];
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                String message="My current sold : 13.000DH";
                //Decoder le public Key of Server
                byte [] decodedPublicKey = Base64.getDecoder().decode(publicKeyOfServer);

                //Partie de cryptage de message à envoyer
                try {
                    //Convertir decodedPublicKey en Public Key :
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodedPublicKey));
                    //Crypter le message
                    Cipher cipher=Cipher.getInstance("RSA");
                    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                    byte[] cryptedMsg = cipher.doFinal(message.getBytes()); //crypter le msg
                    String cryptedEncodedMsg = Base64.getEncoder().encodeToString(cryptedMsg);

                    msg.addReceiver(new AID( "server", AID.ISLOCALNAME));
                    msg.setContent(cryptedEncodedMsg);
                    send(msg);
                    System.out.println(">> msg has been sent");

                } catch (Exception e) {
                    e.getStackTrace();
                }


            }
        });
    }
}
