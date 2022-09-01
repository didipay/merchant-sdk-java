package com.xiaoju.ibt.merchant.http;

import com.xiaoju.ibt.merchant.exception.PayException;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * http client
 * @Author: xingrufei
 */
public class HttpClient {

    private static Logger logger = LoggerFactory.getLogger(HttpClient.class);

    public static String sendHttpRequest(String url, long connectTimeout, long readTimeout, String data){

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .build();

        // 构造 Content-Type 头
        MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");


        // 构造请求数据
        RequestBody requestBody = RequestBody.create(mediaType, data);

        // 构建 Request 对象
        Request request = new Request.Builder()
                // post 方法中传入 构造的对象
                .post(requestBody)
                .url(url)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            }
            if(logger.isDebugEnabled()) {
                logger.debug("response code:{}", response.code());
            }
            return response.body().string();
        } catch (IOException e) {
            if(logger.isDebugEnabled()) {
                logger.debug("send http request error,url:{},requestBody:{}", url, requestBody);
            }
            throw new PayException("send request error",e);
        }

    }

}
