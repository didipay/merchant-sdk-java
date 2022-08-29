package com.xiaoju.ibt.merchant.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;

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
@Slf4j
public class SHA256WITHRSA {

    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";
    /**
     * 当密钥位数为2048时，最大解密长度应为256，128 对应 1024，256对应2048
     */
    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    /**
     * 用私钥对信息进行数字签名
     *
     * @param plainText  加密数据
     * @param privateKey 私钥-base64加密的
     * @return String || null
     */
    public static String sign(byte[] plainText, String privateKey) {
        try {
            if (StringUtils.isBlank(privateKey)) {
                log.error("signature failed. private key is empty");
                throw new RuntimeException("signature failed. private key is empty");
            }
            // 获取私钥对象
            PrivateKey priKey = getPkcs1PrivateKey(privateKey);
            if (priKey == null) {
                log.error("signature failed. private key read failed");
                throw new RuntimeException("signature failed. private key read failed");
            }
            // 用私钥对信息进行数字签名
            Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            sign.initSign(priKey);
            sign.update(plainText);
            return Base64.encodeBase64String(sign.sign());
        } catch (Exception e) {
            log.error("signature", e);
            throw new RuntimeException("signature failed");
        }
    }

    /**
     * 验签
     *
     * @param plainText 明文
     * @param publicKey 公钥
     * @param sign      签名
     * @return
     */
    public static boolean verify(byte[] plainText, String publicKey, String sign) {
        try {
            // 获取公钥对象
            PublicKey pubKey = getPublicKey(publicKey);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(plainText);
            // 验签
            return signature.verify(Base64.decodeBase64(sign));
        } catch (Throwable e) {
            log.error("verify signature failed", e);
            return false;
        }
    }

    /**
     * 解析公钥对象
     *
     * @param publicKey 公钥字符串
     * @return 公钥对象
     * @throws InvalidKeySpecException
     */
    private static PublicKey getPublicKey(String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(keySpec);
    }


    /**
     * 解析私钥对象
     *
     * @param privateKey 私钥字符串
     * @return 私钥对象
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
     * 生成密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> genKey() throws Exception {
        Map<String, String> keyMap = new HashMap<>();
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        SecureRandom random = new SecureRandom();
        // 初始加密，512位已被破解，用1024位,最好用2048位
        keygen.initialize(1024, random);
        // 取得密钥对
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
