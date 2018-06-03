package com.mrunal.android.popularmoviesstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.mrunal.android.popularmoviesstage1.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

  private final MovieAdapterOnClickHandler mClickHandler;
  private final Context mContext;
  private ArrayList<Movie> mMoviesData;

  public MovieAdapter(Context context, MovieAdapterOnClickHandler mClickHandler) {
    mContext = context;
    this.mClickHandler = mClickHandler;
  }

  @NonNull
  @Override
  public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    int layoutIdForListItem = R.layout.movie_list_item;
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(layoutIdForListItem, parent, false);
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {

    Picasso.with(mContext).load(Constants.BASE_IMAGE_URL + Constants.IMAGE_SIZE + mMoviesData.get(position).getPosterPath())
        .into(holder.mMoviePosterImageView);

  }

  @Override
  public int getItemCount() {
    if (mMoviesData == null) {
      return 0;
    }
    return mMoviesData.size();
  }

  public void setMoviesData(ArrayList<Movie> movies) {
    mMoviesData = movies;
    notifyDataSetChanged();
  }

  public interface MovieAdapterOnClickHandler {

    void onClick(Movie movie);
  }

  public class MovieViewHolder extends ViewHolder implements OnClickListener {

    final ImageView mMoviePosterImageView;

    MovieViewHolder(View view) {
      super(view);
      mMoviePosterImageView = view.findViewById(R.id.movie_list_item_iv);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      int adapterPosition = getAdapterPosition();
      Movie movie = mMoviesData.get(adapterPosition);
      mClickHandler.onClick(movie);
    }
  }
}
