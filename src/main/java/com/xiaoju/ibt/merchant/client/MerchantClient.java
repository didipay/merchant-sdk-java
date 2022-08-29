package com.xiaoju.ibt.merchant.client;

import com.alibaba.fastjson.JSONObject;
import com.xiaoju.ibt.merchant.constants.GatewayConstants;
import com.xiaoju.ibt.merchant.exception.PayException;
import com.xiaoju.ibt.merchant.http.HttpClient;
import com.xiaoju.ibt.merchant.model.PayInfo;
import com.xiaoju.ibt.merchant.model.ResponseInfo;
import com.xiaoju.ibt.merchant.util.JsonUtil;
import com.xiaoju.ibt.merchant.util.SignUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;

import static com.xiaoju.ibt.merchant.constants.GatewayConstants.CONNECT_TIME_OUT;
import static com.xiaoju.ibt.merchant.constants.GatewayConstants.READ_TIME_OUT;

/**
 * @Author: xingrufei
 * @CreateTime: 2022-08-17
 */
public class MerchantClient {

    private final String privateKey;
    private long connectTimeout = CONNECT_TIME_OUT;
    private long readTimeout = READ_TIME_OUT;


    /**
     * @param privateKey
     */
    public MerchantClient(String privateKey) {
        if (StringUtils.isEmpty(privateKey)) {
            throw new PayException("privateKey is empty");
        }
        this.privateKey = privateKey;
    }

    /**
     * @param privateKey
     * @param connectTimeout
     * @param readTimeout
     */
    public MerchantClient(String privateKey, long connectTimeout, long readTimeout) {
        if (StringUtils.isEmpty(privateKey)) {
            throw new PayException("privateKey is empty");
        }
        if (connectTimeout < 0) {
            throw new PayException("connect timeout is negative");
        }
        if (readTimeout < 0) {
            throw new PayException("read timeout is negative");
        }
        this.privateKey = privateKey;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    /**
     * 发单接口
     *
     * @param url
     * @param payInfo
     */
    public ResponseInfo prePay(String url, PayInfo payInfo) throws IOException {

        checkParam(payInfo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(GatewayConstants.APP_ID, payInfo.getAppId());
        jsonObject.put(GatewayConstants.MERCHANT_ID, payInfo.getMerchantId());
        jsonObject.put(GatewayConstants.MERCHANT_ORDER_ID, payInfo.getMerchantOrderId());
        jsonObject.put(GatewayConstants.TOTAL_AMOUNT, payInfo.getTotalAmount());
        jsonObject.put(GatewayConstants.CURRENCY, payInfo.getCurrency());
        if (!Objects.isNull(payInfo.getAgencyId())) {
            jsonObject.put(GatewayConstants.AGENCY_ID, payInfo.getAgencyId());
        }
        if (!Objects.isNull(payInfo.getNotifyUrl())) {
            jsonObject.put(GatewayConstants.NOTIFY_URL, payInfo.getNotifyUrl());
        }
        if (!Objects.isNull(payInfo.getTimeExpire())) {
            jsonObject.put(GatewayConstants.TIME_EXPIRE, payInfo.getTimeExpire());
        }
        if (!Objects.isNull(payInfo.getMerchantData())) {
            jsonObject.put(GatewayConstants.MERCHANT_DATA, payInfo.getMerchantData());
        }
        if (!Objects.isNull(payInfo.getFromType())) {
            jsonObject.put(GatewayConstants.FROM_TYPE, payInfo.getFromType());
        }
        if (!Objects.isNull(payInfo.getGoodsDetail())) {
            jsonObject.put(GatewayConstants.GOODS_DETAIL, payInfo.getGoodsDetail());
        }
        if (!Objects.isNull(payInfo.getPayer())) {
            jsonObject.put(GatewayConstants.PAYER, payInfo.getPayer());
        }
        if (!Objects.isNull(payInfo.getReturnUrl())) {
            jsonObject.put(GatewayConstants.RETURN_URL, payInfo.getReturnUrl());
        }
        String sign = SignUtil.buildSign(jsonObject, privateKey);
        jsonObject.put(GatewayConstants.SIGNATURE, sign);
        String requestBody = jsonObject.toJSONString();
        String response = HttpClient.sendHttpRequest(url, connectTimeout, readTimeout, requestBody);
        ResponseInfo responseInfo = JsonUtil.toObject(response, ResponseInfo.class);

        return responseInfo;
    }

    /**
     * 支付查询接口
     *
     * @param url
     * @param payInfo
     */
    public ResponseInfo payQuery(String url, PayInfo payInfo) throws IOException {

        checkPayQuery(payInfo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(GatewayConstants.APP_ID, payInfo.getAppId());
        jsonObject.put(GatewayConstants.MERCHANT_ID, payInfo.getMerchantId());
        jsonObject.put(GatewayConstants.MERCHANT_ORDER_ID, payInfo.getMerchantOrderId());
        jsonObject.put(GatewayConstants.PAY_ORDER_ID, payInfo.getPayOrderId());
        String sign = SignUtil.buildSign(jsonObject, privateKey);
        jsonObject.put(GatewayConstants.SIGNATURE, sign);
        String requestBody = jsonObject.toJSONString();
        String response = HttpClient.sendHttpRequest(url, connectTimeout, readTimeout, requestBody);
        ResponseInfo responseInfo = JsonUtil.toObject(response, ResponseInfo.class);

        return responseInfo;
    }

    /**
     * 退款接口
     *
     * @param url
     * @param payInfo
     */
    public ResponseInfo refund(String url, PayInfo payInfo) throws IOException {

        checkRefund(payInfo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(GatewayConstants.APP_ID, payInfo.getAppId());
        jsonObject.put(GatewayConstants.MERCHANT_ID, payInfo.getMerchantId());
        jsonObject.put(GatewayConstants.MERCHANT_ORDER_ID, payInfo.getMerchantOrderId());
        jsonObject.put(GatewayConstants.PAY_ORDER_ID, payInfo.getPayOrderId());
        jsonObject.put(GatewayConstants.MERCHANT_REFUND_ID, payInfo.getMerchantRefundId());
        jsonObject.put(GatewayConstants.AMOUNT, payInfo.getAmount());
        String sign = SignUtil.buildSign(jsonObject, privateKey);
        jsonObject.put(GatewayConstants.SIGNATURE, sign);
        String requestBody = jsonObject.toJSONString();
        String response = HttpClient.sendHttpRequest(url, connectTimeout, readTimeout, requestBody);
        ResponseInfo responseInfo = JsonUtil.toObject(response, ResponseInfo.class);

        return responseInfo;
    }

    /**
     * 退款查询接口
     *
     * @param url
     * @param payInfo
     */
    public ResponseInfo refundQuery(String url, PayInfo payInfo) throws IOException {

        checkPayQuery(payInfo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(GatewayConstants.APP_ID, payInfo.getAppId());
        jsonObject.put(GatewayConstants.MERCHANT_ID, payInfo.getMerchantId());
        jsonObject.put(GatewayConstants.MERCHANT_ORDER_ID, payInfo.getMerchantOrderId());
        jsonObject.put(GatewayConstants.PAY_ORDER_ID, payInfo.getPayOrderId());
        String sign = SignUtil.buildSign(jsonObject, privateKey);
        jsonObject.put(GatewayConstants.SIGNATURE, sign);
        String requestBody = jsonObject.toJSONString();
        String response = HttpClient.sendHttpRequest(url, connectTimeout, readTimeout, requestBody);
        ResponseInfo responseInfo = JsonUtil.toObject(response, ResponseInfo.class);

        return responseInfo;
    }

    private void checkParam(PayInfo payInfo) {

        checkBasicInfo(payInfo);

        if (StringUtils.isEmpty(payInfo.getCurrency())) {
            throw new PayException("currency is empty");
        }

        if (StringUtils.isEmpty(payInfo.getTotalAmount())) {
            throw new PayException("totalAmount is empty");
        }

    }

    public void checkPayQuery(PayInfo payInfo) {

        checkBasicInfo(payInfo);

        if (StringUtils.isEmpty(payInfo.getPayOrderId())) {
            throw new PayException("payOrderId is empty");
        }

    }

    public void checkRefund(PayInfo payInfo) {

        checkBasicInfo(payInfo);

        if (StringUtils.isEmpty(payInfo.getPayOrderId())) {
            throw new PayException("payOrderId is empty");
        }

        if (StringUtils.isEmpty(payInfo.getMerchantRefundId())) {
            throw new PayException("merchantRefundId is empty");
        }

        if (StringUtils.isEmpty(payInfo.getAmount())) {
            throw new PayException("amount is empty");
        }

    }

    public void checkBasicInfo(PayInfo payInfo) {

        if (Objects.isNull(payInfo)) {
            throw new PayException("payInfo is null");
        }

        if (StringUtils.isEmpty(payInfo.getAppId())) {
            throw new PayException("appId is empty");
        }

        if (StringUtils.isEmpty(payInfo.getMerchantId())) {
            throw new PayException("merchantId is empty");
        }

        if (StringUtils.isEmpty(payInfo.getMerchantOrderId())) {
            throw new PayException("merchantOrderId is empty");
        }
    }

}
