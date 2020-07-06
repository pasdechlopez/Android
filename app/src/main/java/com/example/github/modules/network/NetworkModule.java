package com.example.github.modules.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.net.CookieHandler;
import java.net.CookieManager;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String API_GITHUB_BASE_URL = "https://api.github.com/";

    private static final String AUTH_TOKEN = "token ab09e758eeba73cd54937ed1f22eeb81c6f9d250";

    private static IApi apiInstance = null;

    public static IApi getApi() {
        synchronized (NetworkModule.class) {
            if (apiInstance == null) {
                apiInstance = createRetrofit().create(IApi.class);
            }

            return apiInstance;
        }
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(createRxCallAdapterFactory())
                .addConverterFactory(createConverterFactory())
                .baseUrl(API_GITHUB_BASE_URL)
                .client(createHttpClient())
                .build();
    }

    private static CallAdapter.Factory createRxCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    private static OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(createAuthInterceptor())
                .cookieJar(createCookieJar())
                .followRedirects(false)
                .build();
    }

    private static Interceptor createAuthInterceptor() {
        return chain -> {
            final Request request = chain.request();
            final Request requestWithToken = request
                    .newBuilder()
                    .addHeader(AUTHORIZATION_HEADER, AUTH_TOKEN)
                    .build();

            return chain.proceed(requestWithToken);
        };
    }

    private static CookieJar createCookieJar() {
        return new JavaNetCookieJar(createCookieManager());
    }

    private static CookieHandler createCookieManager() {
        return new CookieManager();
    }

    private static Converter.Factory createConverterFactory() {
        return GsonConverterFactory.create();
    }
}
