package crypto;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class DSA {

    public static KeyPair generateKeyPair(int n) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(n);
        KeyPair pair = keyPairGen.generateKeyPair();
        return pair;
    }

    public static byte[] signObject(Serializable object, PrivateKey privateKey)
            throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, IOException {

        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privateKey);
        byte[] bytes =Helpers.objectToByteArray(object);
        sign.update(bytes);
        byte[] signature = sign.sign();
        return signature;
    }

    public static boolean verifyObject(Serializable object, byte[] signature, PublicKey publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, IOException {
        
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initVerify(publicKey);
        byte[] bytes = Helpers.objectToByteArray(object);
        sign.update(bytes);
        return sign.verify(signature);
    }

}
