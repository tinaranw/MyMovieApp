package com.uc.apiapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.apiapp.R;
import com.uc.apiapp.model.Movie;
import com.uc.apiapp.ui.main.movie.MovieFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewViewHolder> {

    private final Context context;
    private List<Movie> listMovie;
    private List<Movie> getListMovie() {
        return listMovie;
    }
    public void setListMovie(List<Movie> listMovie) {
        this.listMovie = listMovie;
    }
    public MovieAdapter(Context context) {
        this.listMovie = new ArrayList<Movie>();
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MovieAdapter.CardViewViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.CardViewViewHolder holder, int position) {
        Movie movie = getListMovie().get(position);
        Glide.with(context).load(movie.getBackdrop()).centerCrop().into(holder.item_img);
        holder.item_title.setText(movie.getTitle());
        holder.item_date.setText(movie.getReleaseDate());
        holder.item_vote.setText(movie.getRating());
//        holder.itemView.setOnClickListener(view -> {
//            MovieFragmentDirections.ActionDetailFragment action = MovieFragmentDirections.actionDetailFragment(movie, null);
//            Navigation.findNavController(view).navigate(action);
//        });
        Log.d("Movie",movie.getPoster());
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }


    class CardViewViewHolder extends RecyclerView.ViewHolder{
        TextView item_title, item_date, item_vote;
        ImageView item_img;

        CardViewViewHolder(View itemView) {
            super(itemView);
            item_title = itemView.findViewById(R.id.item_title);
            item_date = itemView.findViewById(R.id.item_date);
            item_vote = itemView.findViewById(R.id.item_rating);
            item_img = itemView.findViewById(R.id.item_bg);
        }
    }
}
