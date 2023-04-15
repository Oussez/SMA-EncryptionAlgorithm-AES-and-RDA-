package com.sdia.RSA;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GeneratedRSAKeys {
    public static KeyPair generatedKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(512); //taille de clé générée : 128, 256 ou 512 bits
        KeyPair keyPair = generator.generateKeyPair(); //keyPair = public key + private key
        return keyPair;
    }
}
