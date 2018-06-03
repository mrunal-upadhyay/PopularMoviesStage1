package com.mrunal.android.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mrunal.android.popularmoviesstage1.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

  @BindView(R.id.iv_poster)
  ImageView mPosterIv;
  @BindView(R.id.tv_title)
  TextView mTitleTv;
  @BindView(R.id.tv_user_rating)
  TextView mUserRatingTv;
  @BindView(R.id.tv_release_date)
  TextView mReleaseDateTv;
  @BindView(R.id.tv_description)
  TextView mDescriptionTv;
  Movie mMovie;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_detail);
    ButterKnife.bind(this);

    if (savedInstanceState == null || !savedInstanceState
        .containsKey(Constants.MOVIE_DETAILS_BUNDLE_KEY)) {
      Intent intent = getIntent();
      if (intent.hasExtra(Constants.MOVIE_EXTRA)) {
        mMovie = intent.getParcelableExtra(Constants.MOVIE_EXTRA);
        populateUi();
      }
    } else {
      mMovie = savedInstanceState.getParcelable(Constants.MOVIE_DETAILS_BUNDLE_KEY);
      populateUi();
    }
  }

  private void populateUi() {
    Picasso.with(this)
        .load(Constants.BASE_IMAGE_URL + Constants.IMAGE_SIZE + mMovie.getPosterPath())
        .into(mPosterIv);
    mTitleTv.setText(mMovie.getTitle());
    mUserRatingTv.setText(mMovie.getUserRating());
    mReleaseDateTv.setText(mMovie.getReleaseDate());
    mDescriptionTv.setText(mMovie.getOverview());
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    outState.putParcelable(Constants.MOVIE_DETAILS_BUNDLE_KEY, mMovie);
    super.onSaveInstanceState(outState);
  }
}
