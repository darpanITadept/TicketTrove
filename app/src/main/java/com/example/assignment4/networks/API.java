package com.example.assignment4.networks;

import com.example.assignment4.models.DataResponseArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    // TODO: base url of the api
    public final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    // TODO: endpoints we want to connect to
    @GET("now_playing?api_key=bb2b93e32c17f5c5c3469c0a14b01740&lang%20uage=en-US&page=1&region=CA")
    public Call<DataResponseArray> getMovies();
}