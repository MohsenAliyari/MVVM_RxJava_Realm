package com.example.mvvm_rxjava_realm.services;

import com.example.mvvm_rxjava_realm.activities.baseActivity.model.GenerallRespons;
import com.example.mvvm_rxjava_realm.activities.detailsActivity.model.DetailsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface ApiInterface {


    /**
     * get movies list
     *
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @GET(".")
    Call<GenerallRespons> getMovies(@Query("apikey") String apiKey,
                                    @Query("s") String source);

    /**
     * get movie information
     *
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @GET(".")
    Call<DetailsModel> getMovieInfo(@Query("apikey") String apiKey,
                                    @Query("i") String imdbID);


}
