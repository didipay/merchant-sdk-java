package com.xiaoju.ibt.merchant.client;

import com.xiaoju.ibt.merchant.constants.GatewayConstants;
import com.xiaoju.ibt.merchant.exception.PayException;
import com.xiaoju.ibt.merchant.http.HttpClient;
import com.xiaoju.ibt.merchant.model.PayParameter;
import com.xiaoju.ibt.merchant.model.ResponseInfo;
import com.xiaoju.ibt.merchant.util.JsonUtil;
import com.xiaoju.ibt.merchant.util.SignUtil;
import com.xiaoju.ibt.merchant.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.xiaoju.ibt.merchant.constants.Constants.*;
import static com.xiaoju.ibt.merchant.constants.GatewayConstants.CONNECT_TIME_OUT;
import static com.xiaoju.ibt.merchant.constants.GatewayConstants.READ_TIME_OUT;

public class MerchantClient {

    private final String appId;
    private final String merchantId;
    private final String privateKey;
    private String domain = DEFAULT_DOMAIN;
    private long connectTimeout = CONNECT_TIME_OUT;
    private long readTimeout = READ_TIME_OUT;

    /**
     * @param appId      String
     * @param merchantId String
     * @param privateKey String
     * @throws PayException
     */
    public MerchantClient(String appId, String merchantId, String privateKey) {
        checkBasicInfo(appId, merchantId, privateKey);
        this.appId = appId;
        this.merchantId = merchantId;
        this.privateKey = privateKey;
    }

    /**
     * @param appId      String
     * @param merchantId String
     * @param privateKey String
     * @param domain     String
     * @throws PayException
     */
    public MerchantClient(String appId, String merchantId, String privateKey, String domain) {
        checkBasicInfo(appId, merchantId, privateKey);
        if (StringUtils.isEmpty(domain)) {
            throw new PayException("domain is empty");
        }
        this.appId = appId;
        this.merchantId = merchantId;
        this.privateKey = privateKey;
        this.domain = domain;
    }

    /**
     * @param appId          String
     * @param merchantId     String
     * @param privateKey     String
     * @param domain         String
     * @param connectTimeout long
     * @param readTimeout    long
     * @throws PayException
     */
    public MerchantClient(String appId, String merchantId, String privateKey, String domain, long connectTimeout, long readTimeout) {
        checkBasicInfo(appId, merchantId, privateKey);
        if (StringUtils.isEmpty(domain)) {
            throw new PayException("domain is empty");
        }
        if (connectTimeout < 0) {
            throw new PayException("connect timeout is negative");
        }
        if (readTimeout < 0) {
            throw new PayException("read timeout is negative");
        }
        this.appId = appId;
        this.merchantId = merchantId;
        this.privateKey = privateKey;
        this.domain = domain;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String appId;
        private String merchantId;
        private String privateKey;
        private String domain = DEFAULT_DOMAIN;
        private long connectTimeout = CONNECT_TIME_OUT;
        private long readTimeout = READ_TIME_OUT;

        public Builder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public Builder merchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder privateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public MerchantClient build() {
            return new MerchantClient(appId, merchantId, privateKey, domain, connectTimeout, readTimeout);
        }

    }

    /**
     * Merchant Places an Order
     *
     * @param payParameter
     * @see <a href="https://didipay.didiglobal.com/developer/docs/en/">didipay</a>
     */
    public ResponseInfo prePay(PayParameter payParameter) {

        checkParam(payParameter);

        String url = domain + PREPAY_URL;

        Map<String, Object> params = new HashMap<>();
        params.put(GatewayConstants.APP_ID, appId);
        params.put(GatewayConstants.MERCHANT_ID, merchantId);
        params.put(GatewayConstants.MERCHANT_ORDER_ID, payParameter.getMerchantOrderId());
        params.put(GatewayConstants.TOTAL_AMOUNT, payParameter.getTotalAmount());
        params.put(GatewayConstants.CURRENCY, payParameter.getCurrency());
        if (!Objects.isNull(payParameter.getAgencyId())) {
            params.put(GatewayConstants.AGENCY_ID, payParameter.getAgencyId());
        }
        if (!Objects.isNull(payParameter.getNotifyUrl())) {
            params.put(GatewayConstants.NOTIFY_URL, payParameter.getNotifyUrl());
        }
        if (!Objects.isNull(payParameter.getTimeExpire())) {
            params.put(GatewayConstants.TIME_EXPIRE, payParameter.getTimeExpire());
        }
        if (!Objects.isNull(payParameter.getMerchantData())) {
            params.put(GatewayConstants.MERCHANT_DATA, payParameter.getMerchantData());
        }
        if (!Objects.isNull(payParameter.getFromType())) {
            params.put(GatewayConstants.FROM_TYPE, payParameter.getFromType());
        }
        if (!Objects.isNull(payParameter.getExtKv())) {
            params.put(GatewayConstants.EXT_KV, payParameter.getExtKv());
        }
        if (!Objects.isNull(payParameter.getGoodsDetail())) {
            params.put(GatewayConstants.GOODS_DETAIL, payParameter.getGoodsDetail());
        }
        if (!Objects.isNull(payParameter.getPayer())) {
            params.put(GatewayConstants.PAYER, payParameter.getPayer());
        }
        if (!Objects.isNull(payParameter.getReturnUrl())) {
            params.put(GatewayConstants.RETURN_URL, payParameter.getReturnUrl());
        }

        return sendRequest(url, params);
    }

    /**
     * Payment Query
     *
     * @param payParameter
     * @see <a href="https://didipay.didiglobal.com/developer/docs/en/">didipay</a>
     */
    public ResponseInfo payQuery(PayParameter payParameter) {

        checkPayQuery(payParameter);

        String url = domain + PAY_QUERY_URL;

        Map<String, Object> params = new HashMap<>();
        params.put(GatewayConstants.APP_ID, appId);
        params.put(GatewayConstants.MERCHANT_ID, merchantId);
        params.put(GatewayConstants.MERCHANT_ORDER_ID, payParameter.getMerchantOrderId());
        params.put(GatewayConstants.PAY_ORDER_ID, payParameter.getPayOrderId());

        return sendRequest(url, params);
    }

    /**
     * Request Refund
     *
     * @param payParameter
     * @see <a href="https://didipay.didiglobal.com/developer/docs/en/">didipay</a>
     */
    public ResponseInfo refund(PayParameter payParameter) {

        checkRefund(payParameter);

        String url = domain + REFUND_URL;

        Map<String, Object> params = new HashMap<>();
        params.put(GatewayConstants.APP_ID, appId);
        params.put(GatewayConstants.MERCHANT_ID, merchantId);
        params.put(GatewayConstants.MERCHANT_ORDER_ID, payParameter.getMerchantOrderId());
        params.put(GatewayConstants.PAY_ORDER_ID, payParameter.getPayOrderId());
        params.put(GatewayConstants.MERCHANT_REFUND_ID, payParameter.getMerchantRefundId());
        params.put(GatewayConstants.AMOUNT, payParameter.getAmount());

        return sendRequest(url, params);
    }

    /**
     * Refund Query
     *
     * @param payParameter
     * @see <a href="https://didipay.didiglobal.com/developer/docs/en/">didipay</a>
     */
    public ResponseInfo refundQuery(PayParameter payParameter) {

        checkPayQuery(payParameter);

        String url = domain + REFUND_QUERY_URL;

        Map<String, Object> params = new HashMap<>();
        params.put(GatewayConstants.APP_ID, appId);
        params.put(GatewayConstants.MERCHANT_ID, merchantId);
        params.put(GatewayConstants.MERCHANT_ORDER_ID, payParameter.getMerchantOrderId());
        params.put(GatewayConstants.PAY_ORDER_ID, payParameter.getPayOrderId());

        return sendRequest(url, params);
    }

    /**
     * Close Trade
     *
     * @param payParameter
     * @see <a href="https://didipay.didiglobal.com/developer/docs/en/">didipay</a>
     */
    public ResponseInfo closeTrade(PayParameter payParameter) {

        checkMerchantOrderId(payParameter);

        String url = domain + CLOSE_TRADE_URL;

        Map<String, Object> params = new HashMap<>();
        params.put(GatewayConstants.APP_ID, appId);
        params.put(GatewayConstants.MERCHANT_ID, merchantId);
        params.put(GatewayConstants.MERCHANT_ORDER_ID, payParameter.getMerchantOrderId());

        return sendRequest(url, params);
    }

    public ResponseInfo sendRequest(String url, Map<String, Object> params) {

        String sign = SignUtil.buildSign(params, privateKey);
        params.put(GatewayConstants.SIGNATURE, sign);
        String requestBody = JsonUtil.toString(params);
        String response = HttpClient.sendHttpRequest(url, "POST", connectTimeout, readTimeout, requestBody);

        return JsonUtil.toObject(response, ResponseInfo.class);
    }

    private void checkParam(PayParameter payParameter) {

        checkMerchantOrderId(payParameter);

        if (StringUtils.isEmpty(payParameter.getCurrency())) {
            throw new PayException("currency is empty");
        }

        if (StringUtils.isEmpty(payParameter.getTotalAmount())) {
            throw new PayException("totalAmount is empty");
        }

    }

    private void checkPayQuery(PayParameter payParameter) {

        checkMerchantOrderId(payParameter);

        if (StringUtils.isEmpty(payParameter.getPayOrderId())) {
            throw new PayException("payOrderId is empty");
        }

    }

    private void checkRefund(PayParameter payParameter) {

        checkMerchantOrderId(payParameter);

        if (StringUtils.isEmpty(payParameter.getPayOrderId())) {
            throw new PayException("payOrderId is empty");
        }

        if (StringUtils.isEmpty(payParameter.getMerchantRefundId())) {
            throw new PayException("merchantRefundId is empty");
        }

        if (StringUtils.isEmpty(payParameter.getAmount())) {
            throw new PayException("amount is empty");
        }

    }

    private void checkMerchantOrderId(PayParameter payParameter) {

        if (Objects.isNull(payParameter)) {
            throw new PayException("payInfo is null");
        }

        if (StringUtils.isEmpty(payParameter.getMerchantOrderId())) {
            throw new PayException("merchantOrderId is empty");
        }

    }

    private void checkBasicInfo(String appId, String merchantId, String privateKey) {

        if (StringUtils.isEmpty(appId)) {
            throw new PayException("appId is empty");
        }

        if (StringUtils.isEmpty(merchantId)) {
            throw new PayException("merchantId is empty");
        }

        if (StringUtils.isEmpty(privateKey)) {
            throw new PayException("privateKey is empty");
        }
    }

}
