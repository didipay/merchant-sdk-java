package com.xiaoju.ibt.merchant.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author: xingrufei
 * @CreateTime: 2022-08-17
 */
public class ResponseInfo {

    @JsonProperty("return_no")
    private int returnNo;
    @JsonProperty("return_msg")
    private String returnMsg;
    @JsonProperty("error_no")
    private int errNo;
    @JsonProperty("error_msg")
    private String errMsg;
    @JsonProperty("pay_status")
    private String payStatus;
    @JsonProperty("pay_order_id")
    private String payOrderId;
    @JsonProperty("merchant_order_id")
    private String merchantOrderId;
    @JsonProperty("refund_order_id")
    private String refundOrderId;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("order_create_time")
    private String orderCreateTime;
    @JsonProperty("order_pay_time")
    private String orderPayTime;
    @JsonProperty("payment_url")
    private String paymentUrl;
    private List<RefundData> refunds;
    @JsonProperty("extra_data")
    private ExtraData extraData;
    private String sign;

    public int getReturnNo() {
        return returnNo;
    }

    public void setReturnNo(int returnNo) {
        this.returnNo = returnNo;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public String getRefundOrderId() {
        return refundOrderId;
    }

    public void setRefundOrderId(String refundOrderId) {
        this.refundOrderId = refundOrderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(String orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public List<RefundData> getRefunds() {
        return refunds;
    }

    public void setRefunds(List<RefundData> refunds) {
        this.refunds = refunds;
    }

    public ExtraData getExtraData() {
        return extraData;
    }

    public void setExtraData(ExtraData extraData) {
        this.extraData = extraData;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "returnNo=" + returnNo +
                ", returnMsg='" + returnMsg + '\'' +
                ", errNo=" + errNo +
                ", errMsg='" + errMsg + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", payOrderId='" + payOrderId + '\'' +
                ", merchantOrderId='" + merchantOrderId + '\'' +
                ", refundOrderId='" + refundOrderId + '\'' +
                ", amount='" + amount + '\'' +
                ", orderCreateTime='" + orderCreateTime + '\'' +
                ", orderPayTime='" + orderPayTime + '\'' +
                ", paymentUrl='" + paymentUrl + '\'' +
                ", refunds=" + refunds +
                ", extraData=" + extraData +
                ", sign='" + sign + '\'' +
                '}';
    }
}
