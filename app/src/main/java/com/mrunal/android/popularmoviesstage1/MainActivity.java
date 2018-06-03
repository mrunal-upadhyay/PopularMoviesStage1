package com.mrunal.android.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mrunal.android.popularmoviesstage1.MovieAdapter.MovieAdapterOnClickHandler;
import com.mrunal.android.popularmoviesstage1.model.Movie;
import com.mrunal.android.popularmoviesstage1.model.MovieResponse;
import com.mrunal.android.popularmoviesstage1.rest.FetchMovieService;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MovieAdapterOnClickHandler {

  private final String TAG = this.getClass().getSimpleName();
  @BindView(R.id.recyclerview_movie)
  RecyclerView mRecyclerView;
  private MovieAdapter mMovieAdapter;
  private Retrofit mRetrofit;
  private ArrayList<Movie> movies;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.d(TAG, "Inside onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
    mRecyclerView.setLayoutManager(gridLayoutManager);
    mRecyclerView.hasFixedSize();
    mMovieAdapter = new MovieAdapter(this, this);
    mRecyclerView.setAdapter(mMovieAdapter);

    if (savedInstanceState == null || !savedInstanceState.containsKey(Constants.MOVIE_BUNDLE_KEY)) {
      loadMovieData(Constants.TOP_RATED);
    } else {
      movies = savedInstanceState.getParcelableArrayList(Constants.MOVIE_BUNDLE_KEY);
      mMovieAdapter.setMoviesData(movies);
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    outState.putParcelableArrayList(Constants.MOVIE_BUNDLE_KEY, movies);
    super.onSaveInstanceState(outState);
  }

  private void loadMovieData(String type) {
    Log.d(TAG, "Inside loadMovieData()");
    if (mRetrofit == null) {
      mRetrofit = new Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    FetchMovieService fetchMovieService = mRetrofit.create(FetchMovieService.class);

    Call<MovieResponse> call = fetchMovieService.fetchMovies(type, Constants.API_KEY);

    call.enqueue(new Callback<MovieResponse>() {
      @Override
      public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
        Log.d(TAG, "Retrofit response: " + response.body().toString());
        movies = response.body().getResults();
        mMovieAdapter.setMoviesData(movies);
      }

      @Override
      public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
        Log.e(TAG, t.toString());
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int itemSelected = item.getItemId();
    switch (itemSelected) {
      case R.id.item_top_rated:
        loadMovieData(Constants.TOP_RATED);
        break;

      case R.id.item_popular:
        loadMovieData(Constants.POPULAR);
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onClick(Movie movie) {
    Context context = this;
    Class destinationClass = MovieDetailActivity.class;
    Intent intent = new Intent(context, destinationClass);
    intent.putExtra(Constants.MOVIE_EXTRA,movie);
    startActivity(intent);

  }
}
