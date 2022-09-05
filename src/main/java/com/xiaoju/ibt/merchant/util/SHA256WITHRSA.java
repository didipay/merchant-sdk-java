package com.xiaoju.ibt.merchant.util;

import com.xiaoju.ibt.merchant.exception.PayException;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guochen
 * @date 2021/11/21 15:22
 */
public class SHA256WITHRSA {

    private static final Logger logger = LoggerFactory.getLogger(SHA256WITHRSA.class);

    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";
    /**
     * When the number of key bits is 2048, the maximum decryption length
     * should be 256, 128 corresponds to 1024, and 256 corresponds to 2048
     */
    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    /**
     * Digitally sign information with a private key
     *
     * @param plainText  Encrypted data
     * @param privateKey Private key - base64 encrypted
     * @return String || null
     */
    public static String sign(byte[] plainText, String privateKey) {
        try {
            if (StringUtils.isBlank(privateKey)) {
                if(logger.isDebugEnabled()) {
                    logger.debug("signature failed. private key is empty");
                }
                throw new PayException("signature failed. private key is empty");
            }
            // Get private key object
            PrivateKey priKey = getPkcs1PrivateKey(privateKey);
            if (priKey == null) {
                if(logger.isDebugEnabled()) {
                    logger.error("signature failed. private key read failed");
                }
                throw new PayException("signature failed. private key read failed");
            }
            // Digitally sign information with a private key
            Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            sign.initSign(priKey);
            sign.update(plainText);
            return Base64.encodeBase64String(sign.sign());
        } catch (Exception e) {
            if(logger.isDebugEnabled()) {
                logger.debug("signature", e);
            }
            throw new PayException("signature failed");
        }
    }

    /**
     * Signature verification
     *
     * @param plainText Plaintext
     * @param publicKey Public key
     * @param sign      Sign
     * @return
     */
    public static boolean verify(byte[] plainText, String publicKey, String sign) {
        try {
            // get public key object
            PublicKey pubKey = getPublicKey(publicKey);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(plainText);
            // signature verification
            return signature.verify(Base64.decodeBase64(sign));
        } catch (Throwable e) {
            if(logger.isDebugEnabled()) {
                logger.debug("verify signature failed", e);
            }
            return false;
        }
    }

    /**
     * Parse public key object
     *
     * @param publicKey Public key string
     * @return Public key object
     * @throws InvalidKeySpecException
     */
    private static PublicKey getPublicKey(String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(keySpec);
    }


    /**
     * Parse private key object
     *
     * @param privateKey Private key string
     * @return Private key object
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPkcs1PrivateKey(String privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        RSAPrivateKeyStructure rsaPrivateKeyStructure = new RSAPrivateKeyStructure((ASN1Sequence) ASN1Sequence.fromByteArray(keyBytes));
        RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(rsaPrivateKeyStructure.getModulus(), rsaPrivateKeyStructure.getPrivateExponent());
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(rsaPrivateKeySpec);
    }

    public static PrivateKey getPkcs8PrivateKey(String privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return keyFactory.generatePrivate(keySpec);
    }


    /**
     * Generate key
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> genKey() throws Exception {
        Map<String, String> keyMap = new HashMap<>();
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        SecureRandom random = new SecureRandom();
        // Initial encryption, 512 bits have been cracked, use 1024 bits, preferably 2048 bits
        keygen.initialize(1024, random);
        // get key pair
        KeyPair kp = keygen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
        String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
        String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
        keyMap.put(PUBLIC_KEY, publicKeyString);
        keyMap.put(PRIVATE_KEY, privateKeyString);
        return keyMap;
    }

}
