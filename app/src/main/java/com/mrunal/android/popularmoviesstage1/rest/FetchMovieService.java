package com.mrunal.android.popularmoviesstage1.rest;

import com.mrunal.android.popularmoviesstage1.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FetchMovieService {

  @GET("3/movie/{preference}")
  Call<MovieResponse> fetchMovies(@Path("preference") String preference, @Query("api_key") String apiKey);

}
