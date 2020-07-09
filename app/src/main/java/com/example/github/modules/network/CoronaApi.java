package com.example.github.modules.network;

import com.example.github.modules.network.models.CoronaStatistics;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoronaApi {

    @GET("v2/key-value-stores/1brJ0NLbQaJKPTWMO/records/LATEST")
    Single<CoronaStatistics> getCities(@Query("disableRedirect") boolean param);

    @GET("getcity")
    Single<CoronaStatistics> getCity(@Query("disableRedirect") boolean param);

}
