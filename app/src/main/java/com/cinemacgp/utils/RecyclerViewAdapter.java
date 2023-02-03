package com.cinemacgp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cinemacgp.android.R;
import com.cinemacgp.models.Movie;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Movie> movies;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    public RecyclerViewAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.titleTextView.setText(movie.getTitle());
        holder.releasedTextView.setText("Released\t: " + movie.getReleaseDate());
        holder.popularityTextView.setText("Popularity\t: " + String.valueOf(movie.getPopularity()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public Movie getItem(int id) {
        return movies.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView;
        private TextView releasedTextView;
        private TextView popularityTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titleTextView = itemView.findViewById(R.id.title);
            this.releasedTextView = itemView.findViewById(R.id.released);
            this.popularityTextView = itemView.findViewById(R.id.popularity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null){
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}