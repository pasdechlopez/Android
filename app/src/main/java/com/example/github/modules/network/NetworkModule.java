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
    private static final String API_CORONA_BASE_URL = "https://api.apify.com/";

    private static final String AUTH_TOKEN = "token ab09e758eeba73cd54937ed1f22eeb81c6f9d250";

    private static IApi apiInstance = null;

    private static CoronaApi coronaApiInstance = null;

    public static IApi getApi() {
        synchronized (NetworkModule.class) {
            if (apiInstance == null) {
                apiInstance = createRetrofit(API_GITHUB_BASE_URL).create(IApi.class);
            }
            return apiInstance;
        }
    }

    public static CoronaApi getCoronaApi() {
        synchronized (NetworkModule.class) {
            if (coronaApiInstance == null) {
                coronaApiInstance = createRetrofit(API_CORONA_BASE_URL).create(CoronaApi.class);
            }
            return coronaApiInstance;
        }
    }

    private static Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(createRxCallAdapterFactory())
                .addConverterFactory(createConverterFactory())
                .baseUrl(baseUrl)
                .client(createHttpClient(baseUrl))
                .build();
    }

    private static CallAdapter.Factory createRxCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    private static OkHttpClient createHttpClient(String baseUrl) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .cookieJar(createCookieJar())
                .followRedirects(false);

        if (baseUrl.equals(API_GITHUB_BASE_URL)) {
            builder.addInterceptor(createAuthInterceptor());
        }

        return builder.build();
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
