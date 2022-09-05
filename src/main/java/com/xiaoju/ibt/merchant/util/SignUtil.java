package com.xiaoju.ibt.merchant.util;

import com.xiaoju.ibt.merchant.constants.GatewayConstants;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.TreeMap;

/**
 * Sign util
 *
 * @author gaosai
 */
public class SignUtil {

    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

    private static final String PRI_PRE = "-----BEGIN RSA PRIVATE KEY-----\n";
    private static final String PRI_TAIL = "\n-----END RSA PRIVATE KEY-----";
    private static final String PUB_PRE = "-----BEGIN PUBLIC KEY-----\n";
    private static final String PUB_TAIL = "\n-----END PUBLIC KEY-----";


    /**
     * Build signature
     *
     * @param params
     * @param privateKey
     * @return
     */
    public static String buildSign(Map<String, Object> params, String privateKey) {
        TreeMap<String, String> treeMap = parseParams(params);
        privateKey = privateKey.replace(PRI_PRE, "");
        privateKey = privateKey.replace(PRI_TAIL, "");
        String content = buildContent(treeMap);
        return SHA256WITHRSA.sign(content.getBytes(StandardCharsets.UTF_8), privateKey);
    }

    /**
     * Signature verification
     *
     * @param map       Request parameter
     * @param publicKey Public key
     * @param sign      Signature
     * @return Has not been tampered with
     */
    public static boolean verifySign(Map<String, Object> map, String publicKey, String sign) {
        TreeMap<String, String> treeMap = parseParams(map);
        publicKey = publicKey.replace(PUB_PRE, "");
        publicKey = publicKey.replace(PUB_TAIL, "");
        publicKey = PUB_PRE + publicKey + PUB_TAIL;
        return verifySign(treeMap, publicKey, sign);
    }

    /**
     * Signature verification
     *
     * @param map       Request parameter
     * @param publicKey Public key
     * @param sign      Signature
     * @return Has not been tampered with
     */
    public static boolean verifySign(TreeMap<String, String> map, String publicKey, String sign) {
        String content = buildContent(map);
        return SHA256WITHRSA.verify(content.getBytes(StandardCharsets.UTF_8), publicKey, sign);
    }

    /**
     * Format request parameters
     *
     * @param map
     * @return result
     */
    public static TreeMap<String, String> parseParams(Map<String, Object> map) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            String str = JsonUtil.toString(value);
            if (StringUtils.isNotEmpty(str)) {
                treeMap.put(key, str);
            } else {
                treeMap.put(key, "");
            }
        }
        return treeMap;
    }

    /**
     * 1. Sort the parameters according to the ASCII code of the key from small to large
     * 2. Filter the value whose value is empty and the value whose value is sign
     * 3. Connect the parameters with & in the way of key-value
     *
     * @return content
     */
    private static String buildContent(TreeMap<String, String> paramMap) {
        StringBuilder content = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (GatewayConstants.SIGNATURE.equalsIgnoreCase(entry.getKey())) {
                continue;
            }
            if (content.length() != 0) {
                content.append("&");
            }
            if (StringUtils.isNotEmpty(entry.getValue())) {
                content.append(entry.getKey()).append("=").append(entry.getValue());
            } else {
                content.append(entry.getKey()).append("=");
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("sign content={}", content);
        }
        return content.toString();
    }

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
            if (logger.isDebugEnabled()) {
                logger.debug("verify signature failed", e);
            }
            return false;
        }
    }

    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";


    private static PublicKey getPublicKey(String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(keySpec);
    }
}
