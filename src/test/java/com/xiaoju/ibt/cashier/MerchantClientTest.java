package com.xiaoju.ibt.cashier;

import com.xiaoju.ibt.merchant.client.MerchantClient;
import com.xiaoju.ibt.merchant.model.PayParameter;
import com.xiaoju.ibt.merchant.model.ResponseInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author: xingrufei
 * @CreateTime: 2022-09-07
 */
public class MerchantClientTest {

    private static final String PRIVATE_KEY_PATH = "/home/runner/work/merchant-sdk-java/merchant-sdk-java/app_private_key.pem";

    @Test
    public void testPayQuery() {

        String domain = "https://merchant-server-merchant-sdk-bmtfqitgrw.cn-hangzhou.fcapp.run";
        String appId = "appId";
        String merchantId = "merchantId";
        String merchantOrderId = "merchantOrderId";
        String payOrderId = "payOrderId";
        String key = readFile(PRIVATE_KEY_PATH);
        PayParameter payParameter = PayParameter.builder()
                .merchantOrderId(merchantOrderId)
                .payOrderId(payOrderId)
                .build();
        MerchantClient merchantClient = MerchantClient.builder().appId(appId)
                .merchantId(merchantId).privateKey(key)
                .domain(domain).build();
        ResponseInfo responseInfo = merchantClient.payQuery(payParameter);
        System.out.println("responseInfo:" + responseInfo);
        Assertions.assertEquals(200, responseInfo.getReturnNo());
        Assertions.assertEquals("success", responseInfo.getReturnMsg());
    }

    @Test
    public void testPrepay() {

        String domain = "https://merchant-server-merchant-sdk-bmtfqitgrw.cn-hangzhou.fcapp.run";
        String appId = "appId";
        String merchantId = "merchantId";
        String merchantOrderId = "merchantOrderId";
        String payOrderId = "payOrderId";
        String key = readFile(PRIVATE_KEY_PATH);
        PayParameter payParameter = PayParameter.builder()
                .merchantOrderId(merchantOrderId)
                .payOrderId(payOrderId)
                .notifyUrl("https://www.didiglobal.com")
                .totalAmount("12000")
                .currency("BRL")
                .build();

        MerchantClient merchantClient = MerchantClient.builder().appId(appId)
                .merchantId(merchantId).privateKey(key)
                .domain(domain).build();
        ResponseInfo responseInfo = merchantClient.prePay(payParameter);
        Assertions.assertEquals(200, responseInfo.getReturnNo());
        Assertions.assertEquals("success", responseInfo.getReturnMsg());
    }

    @Test
    public void testRefund() {

        String domain = "https://merchant-server-merchant-sdk-bmtfqitgrw.cn-hangzhou.fcapp.run";
        String appId = "appId";
        String merchantId = "merchantId";
        String merchantOrderId = "merchantOrderId";
        String payOrderId = "payOrderId";
        String merchantRefundId = "merchantRefundId";
        String key = readFile(PRIVATE_KEY_PATH);
        PayParameter payParameter = PayParameter.builder()
                .merchantOrderId(merchantOrderId)
                .payOrderId(payOrderId)
                .merchantRefundId(merchantRefundId)
                .amount("500")
                .build();

        MerchantClient merchantClient = MerchantClient.builder().appId(appId)
                .merchantId(merchantId).privateKey(key)
                .domain(domain).build();
        ResponseInfo responseInfo = merchantClient.refund(payParameter);
        Assertions.assertEquals(200, responseInfo.getReturnNo());
        Assertions.assertEquals("success", responseInfo.getReturnMsg());
    }

    @Test
    public void testRefundQuery() {

        String domain = "https://merchant-server-merchant-sdk-bmtfqitgrw.cn-hangzhou.fcapp.run";
        String appId = "appId";
        String merchantId = "merchantId";
        String merchantOrderId = "merchantOrderId";
        String payOrderId = "payOrderId";
        String key = readFile(PRIVATE_KEY_PATH);
        PayParameter payParameter = PayParameter.builder()
                .merchantOrderId(merchantOrderId)
                .payOrderId(payOrderId)
                .amount("500")
                .build();

        MerchantClient merchantClient = MerchantClient.builder().appId(appId)
                .merchantId(merchantId).privateKey(key)
                .domain(domain).build();
        ResponseInfo responseInfo = merchantClient.refundQuery(payParameter);
        Assertions.assertEquals(200, responseInfo.getReturnNo());
        Assertions.assertEquals("success", responseInfo.getReturnMsg());
    }

    @Test
    public void testCloseTrade() {

        String domain = "https://merchant-server-merchant-sdk-bmtfqitgrw.cn-hangzhou.fcapp.run";
        String appId = "appId";
        String merchantId = "merchantId";
        String merchantOrderId = "merchantOrderId";
        String payOrderId = "payOrderId";
        String key = readFile(PRIVATE_KEY_PATH);
        PayParameter payParameter = PayParameter.builder()
                .merchantOrderId(merchantOrderId)
                .payOrderId(payOrderId)
                .build();

        MerchantClient merchantClient = MerchantClient.builder().appId(appId)
                .merchantId(merchantId).privateKey(key)
                .domain(domain).build();
        ResponseInfo responseInfo = merchantClient.closeTrade(payParameter);
        Assertions.assertEquals(200, responseInfo.getReturnNo());
        Assertions.assertEquals("success", responseInfo.getReturnMsg());
    }

    private String readFile(String filePath) {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }


}
