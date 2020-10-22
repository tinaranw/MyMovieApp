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
import com.uc.apiapp.model.TvShow;
import com.uc.apiapp.ui.main.tvShow.TvShowFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.CardViewViewHolder> {

    private final Context context;
    private List<TvShow> listTvShow;
    private List<TvShow> getListTvShow() {
        return listTvShow;
    }
    public void setListTvShow(List<TvShow> listTvShow) {
        this.listTvShow = listTvShow;
    }
    public TvShowAdapter(Context context) {
        this.listTvShow = new ArrayList<TvShow>();
        this.context = context;
    }

    @NonNull
    @Override
    public TvShowAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TvShowAdapter.CardViewViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final TvShowAdapter.CardViewViewHolder holder, int position) {
        TvShow tvShow = getListTvShow().get(position);
        Glide.with(context).load(tvShow.getBackdrop()).into(holder.item_img);
        holder.item_title.setText(tvShow.getTitle());
        holder.item_date.setText(tvShow.getReleaseDate());
        holder.item_vote.setText(tvShow.getRating());
        holder.itemView.setOnClickListener(view -> {
            TvShowFragmentDirections.ActionTvToDetail action = TvShowFragmentDirections.actionTvToDetail(null,tvShow);
            Navigation.findNavController(view).navigate(action);
        });
    }
    @NonNull
    @Override
    public int getItemCount() {
        return getListTvShow().size();
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
