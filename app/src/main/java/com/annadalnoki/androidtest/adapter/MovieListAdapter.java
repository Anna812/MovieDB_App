package com.annadalnoki.androidtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annadalnoki.androidtest.R;
import com.annadalnoki.androidtest.models.Genre;
import com.annadalnoki.androidtest.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Anna on 17/02/27.
 */

public class MovieListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Movie> movies;
    private List<Genre> genres;
    private Context context;
    private ViewHolder holder;

    public MovieListAdapter(Context context, List<Movie> movies, List<Genre> genres) {
        this.context = context;
        this.movies = movies;
        this.genres = genres;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieList = inflater.inflate(R.layout.movie_card, parent, false);
        holder = new ViewHolder(movieList);
        return holder;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        loadImage(movie);
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getOverview());
        holder.genre.setText(defineGenre(movie));
    }

    private void loadImage(Movie movie) {
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w342/" + movie.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.image);
    }

    private String defineGenre(Movie movie) {
        String result = "";
        for (int temp : movie.getGenreIdList()) {
            for (Genre genre : genres) {
                if(temp == genre.getId()) {
                    result += genre.getName() + ", ";
                }
            }
        }
        return result;
    }
}
