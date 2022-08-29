package com.xiaoju.ibt.merchant.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * http client
 * @Author: xingrufei
 */
@Slf4j
public class HttpClient {

    public static String sendHttpRequest(String url, long connectTimeout, long readTimeout, String data) throws IOException {

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
            log.error("response code:{}", response.code());
            return response.body().string();
        } catch (IOException e) {
            log.error("send http request error,url:{},requestBody:{}", url, requestBody);
            throw e;
        }

    }

}
