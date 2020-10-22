package com.uc.apiapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.apiapp.R;
import com.uc.apiapp.model.Cast;
import com.uc.apiapp.util.Constants;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private Context context;
    private List<Cast> castData;

    public CastAdapter(Context context) {
        this.context = context;
    }

    public void setCastData(List<Cast> castData) {
        this.castData = castData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cast cast = castData.get(position);
        Glide.with(context).load(Constants.BASE_IMAGE_URL + cast.getProfile_img()).into(holder.profile_image);
        holder.name.setText(cast.getName());
        holder.role.setText(cast.getCharacter());
    }

    @Override
    public int getItemCount() {
        return castData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_image;
        TextView name, role;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.cast_profileimg);
            name = itemView.findViewById(R.id.cast_name);
            role = itemView.findViewById(R.id.cast_role);
        }
    }
}