package com.android.moviecrackers.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static Retrofit getRetrofitInstance(String baseUrl) {

        // Conversion format of the response from the server
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        /*
            This will enable to call server and building connection to server
        */
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

         /*
             It will provide the timeout for the network calls for connection, read and write
          */
    private static OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(15, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);

        return okhttpClientBuilder.build();
    }

    // Which established the end point function definition like get, post, delete and patch
    public static ApiServices getServiceApi(String baseUrl) {
        return getRetrofitInstance(baseUrl).create(ApiServices.class);
    }
}
