package com.xiaoju.ibt.merchant.util;

import com.alibaba.fastjson.JSONObject;
import com.xiaoju.ibt.merchant.constants.GatewayConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

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
 * 签名工具
 *
 * @author gaosai
 */
@Slf4j
public class SignUtil {

    /**
     * 构建签名
     *
     * @param jsonObject
     * @param privateKey
     * @return
     */
    public static String buildSign(JSONObject jsonObject, String privateKey) {
        TreeMap<String, String> treeMap = parseParams(jsonObject, null);
        String content = buildContent(treeMap);
        return SHA256WITHRSA.sign(content.getBytes(StandardCharsets.UTF_8), privateKey);
    }

    /**
     * 验签
     *
     * @param jsonObject 请求参数
     * @param map        请求参数
     * @param publicKey  公钥
     * @param sign       请求中的签名
     * @return 是否未被篡改
     */
    public static boolean verifySign(JSONObject jsonObject, Map<String, String> map, String publicKey, String sign) {
        TreeMap<String, String> treeMap = parseParams(jsonObject, map);
        return verifySign(treeMap, publicKey, sign);
    }

    /**
     * 验签
     *
     * @param map       请求参数
     * @param publicKey 公钥
     * @param sign      请求中的签名
     * @return 是否未被篡改
     */
    public static boolean verifySign(TreeMap<String, String> map, String publicKey, String sign) {
        String content = buildContent(map);
        return SHA256WITHRSA.verify(content.getBytes(StandardCharsets.UTF_8), publicKey, sign);
    }

    /**
     * 格式化请求参数
     *
     * @param jsonObject
     * @param map
     * @return
     */
    public static TreeMap<String, String> parseParams(JSONObject jsonObject, Map<String, String> map) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            String str = JsonUtil.toString(value);
            if (StringUtils.isNotEmpty(str)) {
                treeMap.put(key, str);
            } else {
                treeMap.put(key, "");
            }
        }
        if (map != null) {
            treeMap.putAll(map);
        }
        return treeMap;
    }

    /**
     * 1、将参数按照key的ASCII码从小到大排序
     * 2、过滤value为空的值和为sign的值
     * 3、将参数按照key-value的方式用&连接
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
        log.info("sign content={}", content.toString());
        return content.toString();
    }

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
    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";


    private static PublicKey getPublicKey(String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(keySpec);
    }
}
