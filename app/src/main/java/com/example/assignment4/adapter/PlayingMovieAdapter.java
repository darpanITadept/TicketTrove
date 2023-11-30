package com.example.assignment4.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.assignment4.databinding.MovieDisplayRowLayoutBinding;
import com.example.assignment4.db.MyDatabase;
import com.example.assignment4.models.Movie;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class PlayingMovieAdapter extends RecyclerView.Adapter<PlayingMovieAdapter.ItemViewHolder> {
    static final String TAG = "tag";

    private final Context context;
    private ArrayList<Movie> movieInfo = new ArrayList<>();
    MovieDisplayRowLayoutBinding binding;


    public PlayingMovieAdapter(Context context, ArrayList<Movie> items) {
        this.movieInfo = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(MovieDisplayRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Movie currentItem = movieInfo.get(position);
        holder.bind(context, currentItem);
    }

    @Override
    public int getItemCount() {
        Log.d("EquipmentAdapter", "getItemCount: Number of items " + movieInfo.size());
        return movieInfo.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        MovieDisplayRowLayoutBinding itemBinding;
        private MyDatabase db;
        Movie movieClicked = new Movie();

        public ItemViewHolder(MovieDisplayRowLayoutBinding binding) {
            super(binding.getRoot());
            this.itemBinding = binding;
        }

        public void bind(Context context, Movie currentItem) {
//           TODO: Update the UI for the row
            itemBinding.tvMovieName.setText(currentItem.getOriginal_title());
            itemBinding.tvReleasedDate.setText("Released: " + currentItem.getRelease_date());
            itemBinding.tvPlotSummary.setText(currentItem.getOverview());
            itemBinding.tvPercentage.setText(String.valueOf(currentItem.getVote_average() * 10) + "%");

            itemBinding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int flag = -1;
                    int l_id = -1;

                    db = MyDatabase.getDatabase(context.getApplicationContext());

                    List<Movie> movieList = db.movieDAO().getAllMovies();

                    for (int i = 0; i < movieList.size(); i++) {
                        if (movieList.get(i).getOriginal_title().equals(currentItem.getOriginal_title())) {
                            flag = i;
                            l_id = movieList.get(i).getMovieID();
                        }
                    }
                    if (flag == -1) {
                        db.movieDAO().insertMovie(currentItem);

                    } else if (flag > -1 && l_id > -1) {

                        Movie movieItemToUpdate = db.movieDAO().getMovieById(l_id);

                        movieItemToUpdate.setNumTicketsBought(movieItemToUpdate.getNumTicketsBought() + 1);

                        db.movieDAO().updateMovie(movieItemToUpdate);
                    }

                    Snackbar.make(itemBinding.getRoot(), "Ticket Purchased", Snackbar.LENGTH_SHORT)
                            .show();
                }
            });

            String imagePath = "https://image.tmdb.org/t/p/w500" + currentItem.getPoster_path();
            RequestOptions options = new RequestOptions();
            options.fitCenter();

            // load the image
            Glide.with(context).load(imagePath).apply(options).into(itemBinding.ivMoviePoster);
        }
    }
}
