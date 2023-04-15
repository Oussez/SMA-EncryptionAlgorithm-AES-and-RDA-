package com.sdia.AES;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CustomerAgent extends Agent {
    private String codedPublicKey;
    @Override
    protected void setup() {
        codedPublicKey = (String) getArguments()[0];
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                String message="My current balance is 16.000dh";

                //Partie de cryptage de message à envoyer
                try {
                    //Convertir codedPublicKey en SecretKey :
                    SecretKey publicKeyAES = new SecretKeySpec(codedPublicKey.getBytes(), "AES");
                    //Crypter le message
                    Cipher cipherA=Cipher.getInstance("AES/ECB/PKCS5Padding");
                    cipherA.init(Cipher.ENCRYPT_MODE, publicKeyAES);
                    byte[] cryptedMsg = cipherA.doFinal(message.getBytes()); //crypter le msg
                    String cryptedEncodedMsg = Base64.getEncoder().encodeToString(cryptedMsg);

                    msg.addReceiver(new AID( "server", AID.ISLOCALNAME));
                    msg.setContent(cryptedEncodedMsg);
                    send(msg);
                    System.out.println(">> msg has been sent");

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
