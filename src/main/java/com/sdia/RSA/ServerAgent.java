package com.sdia.RSA;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ServerAgent extends Agent {
    private String privateKeyOfServer;
    @Override
    protected void setup() {
        privateKeyOfServer = (String) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receivedMsg = receive();
                if (receivedMsg != null) {
                    String message = receivedMsg.getContent();
                    //Decoder le public Key of Server : string vers [] byte
                    byte[] cryptedDecodedMsg = Base64.getDecoder().decode(message);
                    byte[] decodedPrivateKey = Base64.getDecoder().decode(privateKeyOfServer);


                    //Partie de decryptage de message à envoyer
                    try {
                        //Convertir decodedPublicKey en Private Key :
                        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedPrivateKey));
                        //Decrypter le message
                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE, privateKey);
                        byte[] decryptedMsg = cipher.doFinal(cryptedDecodedMsg); //dcrypter le msg
                        String decryptedEncodedMsg = new String(decryptedMsg);//convertion de msg de byte [] en String

                        System.out.println(">> ENCRYPTED RECEIVED MSG : "+receivedMsg.getContent());
                        System.out.println(">> DECRYPTED RECEIVED MSG : "+decryptedEncodedMsg);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
                else{
                  block();}
            }

        });
    }
}
