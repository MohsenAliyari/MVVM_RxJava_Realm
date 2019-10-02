package com.example.mvvm_rxjava_realm.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;


public class RetrofitHandler {

    private static String BASE_URL = "http://www.omdbapi.com/";
    public static Retrofit retrofit = null;

    /**
     * Created Retrofit Instance as a Singleton Instance
     */
    private static final Retrofit getRetrofitInstance() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder().setLenient().create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;

    }

    public static ApiInterface getApiService(){
        return getRetrofitInstance().create(ApiInterface.class);
    }


}
