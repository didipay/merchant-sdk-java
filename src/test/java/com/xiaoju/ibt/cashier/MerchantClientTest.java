package com.xiaoju.ibt.cashier;

import com.xiaoju.ibt.merchant.client.MerchantClient;
import com.xiaoju.ibt.merchant.model.PayInfo;
import com.xiaoju.ibt.merchant.model.ResponseInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author: xingrufei
 * @CreateTime: 2022-08-18
 */
public class MerchantClientTest {

    @Test
    public void testPayQuery() throws IOException {

        String url = "https://gsapi-sim100-us01-test.intra.xiaojukeji.com/gateway/api/outer/v1/transaction/query";
        PayInfo payInfo = new PayInfo();
        String appId = "100055";
        String merchantId = "1000219";
        String merchantOrderId = "BR_1000219_0a9e4b8662f317e247194f9100000586";
        String payOrderId = "20220810e66ed0b554172425769e8bee3e9781ad78345321";
        String key = "MIIEpAIBAAKCAQEAr7r7q8KfMAktSZE18+2kSm4B7u3gYOglE6EurqjYt/tMmUAr\nq1zKNgXRmqqyahWYPVjLibAYICLM2UituvzTM1ZxV/SEKZvK6yg+z2mwW5QgwUyQ\nlct08epbGTPoVWqlWTTUmHXm2CkfU5yVtV2Q5gu3BX6VCQPqUuSt5oA6j0NwqjTd\neLRNEfKgXwYSjorrV8tAjJ52eRxlDIX0q2fzjMUkBXR9k6KzPRjaw2UV5a8FQhyu\niWW4uEaICA06qOk5smwMK4HLuP3bZ1tvjtzUgGGiLbo4pGlmLKSVwswWno1M1W5y\ntjxYz6aqGRdCIsVYBkMCWlPeEyL+4+ochFlQFQIDAQABAoIBAEJjeqbWHPuxy9kq\nMd7YnaO80aApYAZcTX2P/CniAhXnq3WOJ7FSmdY09o3fBQ5M4Dn07Ng0nbiuTQOd\nkHy+/S887g8TgkaR4+RZTPlg7U57tLAyM8FTnvex2P65fIGgi50a92It0KtDReF5\nm5lsIsy2CkvaGYXRY9ozKHuXYAg1CYn3VlrrokGWwhk9NAjbWyx4PXXrvOm9zuYX\n8FpfuBWJvcuo4tkj9CjlaMbWlI6GxrL/qJj3FZGbPC9xocX+Cou9Siz9jMttOJIo\nMEGVJrHIDn7q9Upx37Facf1iVB2UT97+7JUE2HMiCPyiVwMwXYfNt3yYqJXNeSmf\n+Hmb8cECgYEA8qMR7Ce9fW5zyGi9LvA42L1S/XKf7KvQ6E+rXP18GaW1TiDPQM5L\nBnaj0aoS+uSL/NM3ZQfjDqxORYW2Apn3wqgLb6E3rK0v5pin+H3hZ1AjnFbIlw6b\nXMCmPVo1arClNeqkqWv8C6tHe5sxmmCrt5mCEYD4GhPpqWdTDu0xS8UCgYEAuWiZ\ne9LpyqcSpwjknLVVKfZn58zw5EI/86+0F+C1gu7UKWxYvT21a+/KD+O3Vk/MMrGa\ngSMiFg7l3h/jYaC0GpM5hJ8jHKXFvyFZOfteNXqePa85eBaARUvrHs7YOvWAeJJ6\nq+Uao690wl+L+P3WvgE/4MSDHdVo4u6yg6SSqBECgYEAl1eboD+RdCFm2sRp7FMu\nQhKlJy1OD2OEJMM80f3SYHT5x8ezPmruZROvTuHUgOX5YzOjw8LNn6NeFwM16h17\natFCgNiAT5ae4Cir7NUnuTdFXwjKiV41Df+kMXSJCQkxo5W+K2cNiYHGTpUYtYBC\n23xGowBcwHdXMbEfH5pXOzkCgYB6lV+08HPGmsPIUCbmPTW7csCsD2HNaMfjk+2c\nQ+N+V+1KHepl5CDI5ldQPhx/Im89rRMcAmCM+Pid7bNmXtv1HhXPS2/phujBP4lA\nvqNA9HsfCXUTorzYFCO10MhB4wM3tJEKiYUdZ+nogUeILjwGj8juEL8nwGuCbpL0\nWqDkIQKBgQC33LqO3I2wzrpCTVrTAfuHuda7yhfRW12B/Kt6ADsknv91MmzY5BA8\njl4Xga38E9zUTk07bS65mBN/y2EHoYYxuSzwsyvqHR2943BJExgjvfqQjGseO4Jn\nf6sqHyQXL+ZGZXGv9jRfmqzjpKWNHiakfRUao6t6VB4imPm8jJGd4g==";
        payInfo.setAppId(appId);
        payInfo.setMerchantId(merchantId);
        payInfo.setMerchantOrderId(merchantOrderId);
        payInfo.setPayOrderId(payOrderId);
        MerchantClient merchantClient = new MerchantClient(key);
        ResponseInfo responseInfo = merchantClient.payQuery(url, payInfo);
        System.out.println("responseInfo:" + responseInfo);
    }

    @Test
    public void testPrepay() throws IOException {

        String url = "https://gsapi-sim100-us01-test.intra.xiaojukeji.com/gateway/api/outer/v1/transaction/prePay";
        PayInfo payInfo = new PayInfo();
        String appId = "100055";
        String merchantId = "1000219";
        String merchantOrderId = "BR_1000219_0a9e4b8662f317e247194f9100000586";
        String payOrderId = "20220810e66ed0b554172425769e8bee3e9781ad78345321";
        String key = "MIIEpAIBAAKCAQEAr7r7q8KfMAktSZE18+2kSm4B7u3gYOglE6EurqjYt/tMmUAr\nq1zKNgXRmqqyahWYPVjLibAYICLM2UituvzTM1ZxV/SEKZvK6yg+z2mwW5QgwUyQ\nlct08epbGTPoVWqlWTTUmHXm2CkfU5yVtV2Q5gu3BX6VCQPqUuSt5oA6j0NwqjTd\neLRNEfKgXwYSjorrV8tAjJ52eRxlDIX0q2fzjMUkBXR9k6KzPRjaw2UV5a8FQhyu\niWW4uEaICA06qOk5smwMK4HLuP3bZ1tvjtzUgGGiLbo4pGlmLKSVwswWno1M1W5y\ntjxYz6aqGRdCIsVYBkMCWlPeEyL+4+ochFlQFQIDAQABAoIBAEJjeqbWHPuxy9kq\nMd7YnaO80aApYAZcTX2P/CniAhXnq3WOJ7FSmdY09o3fBQ5M4Dn07Ng0nbiuTQOd\nkHy+/S887g8TgkaR4+RZTPlg7U57tLAyM8FTnvex2P65fIGgi50a92It0KtDReF5\nm5lsIsy2CkvaGYXRY9ozKHuXYAg1CYn3VlrrokGWwhk9NAjbWyx4PXXrvOm9zuYX\n8FpfuBWJvcuo4tkj9CjlaMbWlI6GxrL/qJj3FZGbPC9xocX+Cou9Siz9jMttOJIo\nMEGVJrHIDn7q9Upx37Facf1iVB2UT97+7JUE2HMiCPyiVwMwXYfNt3yYqJXNeSmf\n+Hmb8cECgYEA8qMR7Ce9fW5zyGi9LvA42L1S/XKf7KvQ6E+rXP18GaW1TiDPQM5L\nBnaj0aoS+uSL/NM3ZQfjDqxORYW2Apn3wqgLb6E3rK0v5pin+H3hZ1AjnFbIlw6b\nXMCmPVo1arClNeqkqWv8C6tHe5sxmmCrt5mCEYD4GhPpqWdTDu0xS8UCgYEAuWiZ\ne9LpyqcSpwjknLVVKfZn58zw5EI/86+0F+C1gu7UKWxYvT21a+/KD+O3Vk/MMrGa\ngSMiFg7l3h/jYaC0GpM5hJ8jHKXFvyFZOfteNXqePa85eBaARUvrHs7YOvWAeJJ6\nq+Uao690wl+L+P3WvgE/4MSDHdVo4u6yg6SSqBECgYEAl1eboD+RdCFm2sRp7FMu\nQhKlJy1OD2OEJMM80f3SYHT5x8ezPmruZROvTuHUgOX5YzOjw8LNn6NeFwM16h17\natFCgNiAT5ae4Cir7NUnuTdFXwjKiV41Df+kMXSJCQkxo5W+K2cNiYHGTpUYtYBC\n23xGowBcwHdXMbEfH5pXOzkCgYB6lV+08HPGmsPIUCbmPTW7csCsD2HNaMfjk+2c\nQ+N+V+1KHepl5CDI5ldQPhx/Im89rRMcAmCM+Pid7bNmXtv1HhXPS2/phujBP4lA\nvqNA9HsfCXUTorzYFCO10MhB4wM3tJEKiYUdZ+nogUeILjwGj8juEL8nwGuCbpL0\nWqDkIQKBgQC33LqO3I2wzrpCTVrTAfuHuda7yhfRW12B/Kt6ADsknv91MmzY5BA8\njl4Xga38E9zUTk07bS65mBN/y2EHoYYxuSzwsyvqHR2943BJExgjvfqQjGseO4Jn\nf6sqHyQXL+ZGZXGv9jRfmqzjpKWNHiakfRUao6t6VB4imPm8jJGd4g==";
        payInfo.setAppId(appId);
        payInfo.setMerchantId(merchantId);
        payInfo.setMerchantOrderId(merchantOrderId);
        payInfo.setPayOrderId(payOrderId);
        payInfo.setTotalAmount("12000");
        payInfo.setCurrency("BRL");
        MerchantClient merchantClient = new MerchantClient(key);
        ResponseInfo responseInfo = merchantClient.prePay(url, payInfo);
        System.out.println("responseInfo:" + responseInfo);
    }

    @Test
    public void testRefund() throws IOException {

        String url = "https://gsapi-sim100-us01-test.intra.xiaojukeji.com/gateway/api/outer/v1/transaction/refund";
        PayInfo payInfo = new PayInfo();
        String appId = "100055";
        String merchantId = "1000219";
        String merchantOrderId = "BR_1000219_0a9e4b8662f475e19e7f2bf000001686";
        String payOrderId = "20220811aaee164f45e8d93b2f39bea3c3b0ec6322806801";
        String merchantRefundId = "202111089807008301428529";
        String key = "MIIEpAIBAAKCAQEAr7r7q8KfMAktSZE18+2kSm4B7u3gYOglE6EurqjYt/tMmUAr\nq1zKNgXRmqqyahWYPVjLibAYICLM2UituvzTM1ZxV/SEKZvK6yg+z2mwW5QgwUyQ\nlct08epbGTPoVWqlWTTUmHXm2CkfU5yVtV2Q5gu3BX6VCQPqUuSt5oA6j0NwqjTd\neLRNEfKgXwYSjorrV8tAjJ52eRxlDIX0q2fzjMUkBXR9k6KzPRjaw2UV5a8FQhyu\niWW4uEaICA06qOk5smwMK4HLuP3bZ1tvjtzUgGGiLbo4pGlmLKSVwswWno1M1W5y\ntjxYz6aqGRdCIsVYBkMCWlPeEyL+4+ochFlQFQIDAQABAoIBAEJjeqbWHPuxy9kq\nMd7YnaO80aApYAZcTX2P/CniAhXnq3WOJ7FSmdY09o3fBQ5M4Dn07Ng0nbiuTQOd\nkHy+/S887g8TgkaR4+RZTPlg7U57tLAyM8FTnvex2P65fIGgi50a92It0KtDReF5\nm5lsIsy2CkvaGYXRY9ozKHuXYAg1CYn3VlrrokGWwhk9NAjbWyx4PXXrvOm9zuYX\n8FpfuBWJvcuo4tkj9CjlaMbWlI6GxrL/qJj3FZGbPC9xocX+Cou9Siz9jMttOJIo\nMEGVJrHIDn7q9Upx37Facf1iVB2UT97+7JUE2HMiCPyiVwMwXYfNt3yYqJXNeSmf\n+Hmb8cECgYEA8qMR7Ce9fW5zyGi9LvA42L1S/XKf7KvQ6E+rXP18GaW1TiDPQM5L\nBnaj0aoS+uSL/NM3ZQfjDqxORYW2Apn3wqgLb6E3rK0v5pin+H3hZ1AjnFbIlw6b\nXMCmPVo1arClNeqkqWv8C6tHe5sxmmCrt5mCEYD4GhPpqWdTDu0xS8UCgYEAuWiZ\ne9LpyqcSpwjknLVVKfZn58zw5EI/86+0F+C1gu7UKWxYvT21a+/KD+O3Vk/MMrGa\ngSMiFg7l3h/jYaC0GpM5hJ8jHKXFvyFZOfteNXqePa85eBaARUvrHs7YOvWAeJJ6\nq+Uao690wl+L+P3WvgE/4MSDHdVo4u6yg6SSqBECgYEAl1eboD+RdCFm2sRp7FMu\nQhKlJy1OD2OEJMM80f3SYHT5x8ezPmruZROvTuHUgOX5YzOjw8LNn6NeFwM16h17\natFCgNiAT5ae4Cir7NUnuTdFXwjKiV41Df+kMXSJCQkxo5W+K2cNiYHGTpUYtYBC\n23xGowBcwHdXMbEfH5pXOzkCgYB6lV+08HPGmsPIUCbmPTW7csCsD2HNaMfjk+2c\nQ+N+V+1KHepl5CDI5ldQPhx/Im89rRMcAmCM+Pid7bNmXtv1HhXPS2/phujBP4lA\nvqNA9HsfCXUTorzYFCO10MhB4wM3tJEKiYUdZ+nogUeILjwGj8juEL8nwGuCbpL0\nWqDkIQKBgQC33LqO3I2wzrpCTVrTAfuHuda7yhfRW12B/Kt6ADsknv91MmzY5BA8\njl4Xga38E9zUTk07bS65mBN/y2EHoYYxuSzwsyvqHR2943BJExgjvfqQjGseO4Jn\nf6sqHyQXL+ZGZXGv9jRfmqzjpKWNHiakfRUao6t6VB4imPm8jJGd4g==";
        payInfo.setAppId(appId);
        payInfo.setMerchantId(merchantId);
        payInfo.setMerchantOrderId(merchantOrderId);
        payInfo.setPayOrderId(payOrderId);
        payInfo.setMerchantRefundId(merchantRefundId);
        payInfo.setAmount("500");
        MerchantClient merchantClient = new MerchantClient(key);
        ResponseInfo responseInfo = merchantClient.refund(url, payInfo);
        System.out.println("responseInfo:" + responseInfo);
    }

    @Test
    public void testRefundQuery() throws IOException {

        String url = "https://gsapi-sim100-us01-test.intra.xiaojukeji.com/gateway/api/outer/v1/transaction/refund/query";
        PayInfo payInfo = new PayInfo();
        String appId = "100055";
        String merchantId = "1000219";
        String merchantOrderId = "BR_1000219_0a9e4b8662f475e19e7f2bf000001686";
        String payOrderId = "20220811aaee164f45e8d93b2f39bea3c3b0ec6322806801";
        String key = "MIIEpAIBAAKCAQEAr7r7q8KfMAktSZE18+2kSm4B7u3gYOglE6EurqjYt/tMmUAr\nq1zKNgXRmqqyahWYPVjLibAYICLM2UituvzTM1ZxV/SEKZvK6yg+z2mwW5QgwUyQ\nlct08epbGTPoVWqlWTTUmHXm2CkfU5yVtV2Q5gu3BX6VCQPqUuSt5oA6j0NwqjTd\neLRNEfKgXwYSjorrV8tAjJ52eRxlDIX0q2fzjMUkBXR9k6KzPRjaw2UV5a8FQhyu\niWW4uEaICA06qOk5smwMK4HLuP3bZ1tvjtzUgGGiLbo4pGlmLKSVwswWno1M1W5y\ntjxYz6aqGRdCIsVYBkMCWlPeEyL+4+ochFlQFQIDAQABAoIBAEJjeqbWHPuxy9kq\nMd7YnaO80aApYAZcTX2P/CniAhXnq3WOJ7FSmdY09o3fBQ5M4Dn07Ng0nbiuTQOd\nkHy+/S887g8TgkaR4+RZTPlg7U57tLAyM8FTnvex2P65fIGgi50a92It0KtDReF5\nm5lsIsy2CkvaGYXRY9ozKHuXYAg1CYn3VlrrokGWwhk9NAjbWyx4PXXrvOm9zuYX\n8FpfuBWJvcuo4tkj9CjlaMbWlI6GxrL/qJj3FZGbPC9xocX+Cou9Siz9jMttOJIo\nMEGVJrHIDn7q9Upx37Facf1iVB2UT97+7JUE2HMiCPyiVwMwXYfNt3yYqJXNeSmf\n+Hmb8cECgYEA8qMR7Ce9fW5zyGi9LvA42L1S/XKf7KvQ6E+rXP18GaW1TiDPQM5L\nBnaj0aoS+uSL/NM3ZQfjDqxORYW2Apn3wqgLb6E3rK0v5pin+H3hZ1AjnFbIlw6b\nXMCmPVo1arClNeqkqWv8C6tHe5sxmmCrt5mCEYD4GhPpqWdTDu0xS8UCgYEAuWiZ\ne9LpyqcSpwjknLVVKfZn58zw5EI/86+0F+C1gu7UKWxYvT21a+/KD+O3Vk/MMrGa\ngSMiFg7l3h/jYaC0GpM5hJ8jHKXFvyFZOfteNXqePa85eBaARUvrHs7YOvWAeJJ6\nq+Uao690wl+L+P3WvgE/4MSDHdVo4u6yg6SSqBECgYEAl1eboD+RdCFm2sRp7FMu\nQhKlJy1OD2OEJMM80f3SYHT5x8ezPmruZROvTuHUgOX5YzOjw8LNn6NeFwM16h17\natFCgNiAT5ae4Cir7NUnuTdFXwjKiV41Df+kMXSJCQkxo5W+K2cNiYHGTpUYtYBC\n23xGowBcwHdXMbEfH5pXOzkCgYB6lV+08HPGmsPIUCbmPTW7csCsD2HNaMfjk+2c\nQ+N+V+1KHepl5CDI5ldQPhx/Im89rRMcAmCM+Pid7bNmXtv1HhXPS2/phujBP4lA\nvqNA9HsfCXUTorzYFCO10MhB4wM3tJEKiYUdZ+nogUeILjwGj8juEL8nwGuCbpL0\nWqDkIQKBgQC33LqO3I2wzrpCTVrTAfuHuda7yhfRW12B/Kt6ADsknv91MmzY5BA8\njl4Xga38E9zUTk07bS65mBN/y2EHoYYxuSzwsyvqHR2943BJExgjvfqQjGseO4Jn\nf6sqHyQXL+ZGZXGv9jRfmqzjpKWNHiakfRUao6t6VB4imPm8jJGd4g==";
        payInfo.setAppId(appId);
        payInfo.setMerchantId(merchantId);
        payInfo.setMerchantOrderId(merchantOrderId);
        payInfo.setPayOrderId(payOrderId);
        payInfo.setPayOrderId(payOrderId);
        MerchantClient merchantClient = new MerchantClient(key);
        ResponseInfo responseInfo = merchantClient.refundQuery(url, payInfo);
        System.out.println("responseInfo:" + responseInfo);
    }


}
