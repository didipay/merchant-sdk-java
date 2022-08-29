package com.xiaoju.ibt.merchant.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: xingrufei
 * @CreateTime: 2022-08-17
 */
public class RefundData {

    @JsonProperty("merchant_refund_id")
    private String merchantRefundId;
    @JsonProperty("refunds_status")
    private String refundsStatus;
    private String amount;

    public String getMerchantRefundId() {
        return merchantRefundId;
    }

    public void setMerchantRefundId(String merchantRefundId) {
        this.merchantRefundId = merchantRefundId;
    }

    public String getRefundsStatus() {
        return refundsStatus;
    }

    public void setRefundsStatus(String refundsStatus) {
        this.refundsStatus = refundsStatus;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
