package com.xiaoju.ibt.merchant.model;


public class PayParameter {

    private String agencyId;
    private String notifyUrl;
    private String merchantOrderId;
    private String payOrderId;
    private String merchantRefundId;
    private String amount;
    private String totalAmount;
    private String currency;
    private String timeExpire;
    private String merchantData;
    private String fromType;
    private String extKv;
    private String goodsDetail;
    private String extraRiskInfo;
    private String payer;
    private String returnUrl;

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getMerchantRefundId() {
        return merchantRefundId;
    }

    public void setMerchantRefundId(String merchantRefundId) {
        this.merchantRefundId = merchantRefundId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getMerchantData() {
        return merchantData;
    }

    public void setMerchantData(String merchantData) {
        this.merchantData = merchantData;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public String getExtKv() {
        return extKv;
    }

    public void setExtKv(String extKv) {
        this.extKv = extKv;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public String getExtraRiskInfo() {
        return extraRiskInfo;
    }

    public void setExtraRiskInfo(String extraRiskInfo) {
        this.extraRiskInfo = extraRiskInfo;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String agencyId;
        private String notifyUrl;
        private String merchantOrderId;
        private String payOrderId;
        private String merchantRefundId;
        private String amount;
        private String totalAmount;
        private String currency;
        private String timeExpire;
        private String merchantData;
        private String fromType;
        private String extKv;
        private String goodsDetail;
        private String extraRiskInfo;
        private String payer;
        private String returnUrl;

        public Builder agencyId(String agencyId) {
            this.agencyId = agencyId;
            return this;
        }

        public Builder notifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
            return this;
        }

        public Builder merchantOrderId(String merchantOrderId) {
            this.merchantOrderId = merchantOrderId;
            return this;
        }

        public Builder payOrderId(String payOrderId) {
            this.payOrderId = payOrderId;
            return this;
        }

        public Builder merchantRefundId(String merchantRefundId) {
            this.merchantRefundId = merchantRefundId;
            return this;
        }

        public Builder amount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder totalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder timeExpire(String timeExpire) {
            this.timeExpire = timeExpire;
            return this;
        }

        public Builder merchantData(String merchantData) {
            this.merchantData = merchantData;
            return this;
        }

        public Builder fromType(String fromType) {
            this.fromType = fromType;
            return this;
        }

        public Builder extKv(String extKv) {
            this.extKv = extKv;
            return this;
        }

        public Builder goodsDetail(String goodsDetail) {
            this.goodsDetail = goodsDetail;
            return this;
        }

        public Builder extraRiskInfo(String extraRiskInfo){
            this.extraRiskInfo = extraRiskInfo;
            return this;
        }

        public Builder payer(String payer) {
            this.payer = payer;
            return this;
        }

        public Builder returnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
            return this;
        }

        public PayParameter build(){
            PayParameter payParameter = new PayParameter();
            payParameter.setAgencyId(agencyId);
            payParameter.setNotifyUrl(notifyUrl);
            payParameter.setMerchantOrderId(merchantOrderId);
            payParameter.setPayOrderId(payOrderId);
            payParameter.setAmount(amount);
            payParameter.setTotalAmount(totalAmount);
            payParameter.setCurrency(currency);
            payParameter.setFromType(fromType);
            payParameter.setExtKv(extKv);
            payParameter.setGoodsDetail(goodsDetail);
            payParameter.setExtraRiskInfo(extraRiskInfo);
            payParameter.setPayer(payer);
            payParameter.setTimeExpire(timeExpire);
            payParameter.setMerchantData(merchantData);
            payParameter.setReturnUrl(returnUrl);
            payParameter.setMerchantRefundId(merchantRefundId);
            return payParameter;
        }

    }

}
