package com.example.assignment4.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.assignment4.databinding.PurchaseMovieRowLayoutBinding;
import com.example.assignment4.db.MyDatabase;
import com.example.assignment4.listeners.RowItemClickListener;
import com.example.assignment4.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class PurchaseMovieTicketAdapter extends RecyclerView.Adapter<PurchaseMovieTicketAdapter.ItemViewHolder> {
    static final String TAG = "tag";

    private final Context context;
    private List<Movie> movieInfo = new ArrayList<>();
    PurchaseMovieRowLayoutBinding binding;

    private final RowItemClickListener clickListener;

    public PurchaseMovieTicketAdapter(Context context, List<Movie> items, RowItemClickListener clickListener) {
        this.movieInfo = items;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(PurchaseMovieRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Movie currentItem = movieInfo.get(position);
        holder.bind(context, currentItem,clickListener);
    }

    @Override
    public int getItemCount() {
        Log.d("EquipmentAdapter", "getItemCount: Number of items " + movieInfo.size());
        return movieInfo.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        PurchaseMovieRowLayoutBinding itemBinding;
        private MyDatabase db;

        public ItemViewHolder(PurchaseMovieRowLayoutBinding binding) {
            super(binding.getRoot());
            this.itemBinding = binding;
        }

        public void bind(Context context, Movie currentItem, RowItemClickListener clickListener) {
//           TODO: Update the UI for the row
            itemBinding.tvMovieName.setText(currentItem.getOriginal_title());
            itemBinding.tvTicketsBought.setText(String.valueOf("Tickets Purchased: "+currentItem.getNumTicketsBought()));

            itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onRowItemClicked(currentItem);
                }
            });
        }
    }
}
