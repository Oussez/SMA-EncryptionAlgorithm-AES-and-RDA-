package com.sdia.RSA;

import java.lang.reflect.Array;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;

public class EncoderRSAKeys {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        KeyPair keyPair = GeneratedRSAKeys.generatedKeys();
        PrivateKey prvKey = keyPair.getPrivate();
        PublicKey pbcKey = keyPair.getPublic();
        System.out.println("** "+ Arrays.toString(prvKey.getEncoded()));//afficher un tableau sans foreach : Arrays.toString(TableX)
        //Partie d'encodage : convertir une clé des octets vers String
        String encodedPrivateKey = Base64.getEncoder().encodeToString(prvKey.getEncoded());
        String encodedPublicKey = Base64.getEncoder().encodeToString(pbcKey.getEncoded());
        System.out.println(">> Public Key : "+encodedPublicKey);
        System.out.println(">> Private Key : "+encodedPrivateKey);




    }
}
