package com.xiaoju.ibt.merchant.constants;

/**
 *
 * @Author: xingrufei
 */
public class Constants {

    /**
     PAY QUERY URL
     */
    public static final String PAY_QUERY_URL = "/gateway/api/outer/v1/transaction/query";
    /**
     PREPAY URL
     */
    public static final String PREPAY_URL = "/gateway/api/outer/v1/transaction/prePay";
    /**
     CLOSE TRADE URL
     */
    public static final String CLOSE_TRADE_URL = "/gateway/api/outer/v1/transaction/close";

    /**
     * REFUND URL
     */
    public static final String REFUND_URL = "/gateway/api/outer/v1/transaction/refund";

    /**
     * REFUND QUERY URL
     */
    public static final String REFUND_QUERY_URL = "/gateway/api/outer/v1/transaction/refund/query";
    /**
     * DEFAULT DOMAIN
     */
    public static final String DEFAULT_DOMAIN = "https://api.99pay.com.br";
}
