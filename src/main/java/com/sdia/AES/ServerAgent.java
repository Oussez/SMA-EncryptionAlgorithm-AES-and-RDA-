package com.sdia.AES;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ServerAgent extends Agent {
    private String codedPublicKey;
    @Override
    protected void setup() {
        codedPublicKey = (String) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receivedMsg = receive();
                if (receivedMsg != null) {
                    String cryptedCodedMsg = receivedMsg.getContent();
                    byte[] cryptedDecodedMsg = Base64.getDecoder().decode(cryptedCodedMsg ); //convertir vers une chaine d'octets ( byte [] )

                    //Partie de decryptage de message reçu
                    try {
                        //Convertir codedPublicKey en SecretKey :
                        SecretKey publicKeyAES = new SecretKeySpec(codedPublicKey.getBytes(), "AES");
                        //Crypter le message
                        Cipher cipher=Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE, publicKeyAES);
                        byte[] decryptedMsg = cipher.doFinal(cryptedDecodedMsg); //decrypter le msg
                        String finalMsg = new String(decryptedMsg);

                        System.out.println(">> ENCRYPTED RECEIVED MSG : "+receivedMsg.getContent());
                        System.out.println(">> DECRYPTED RECEIVED MSG : "+finalMsg);

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
