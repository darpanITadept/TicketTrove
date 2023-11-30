package com.example.assignment4.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment4.R;
import com.example.assignment4.adapter.PurchaseMovieTicketAdapter;
import com.example.assignment4.databinding.FragmentMyTicketBinding;
import com.example.assignment4.db.MyDatabase;
import com.example.assignment4.listeners.RowItemClickListener;
import com.example.assignment4.models.Movie;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FragmentMyTicket extends Fragment implements RowItemClickListener {
    private final String TAG = "tag";
    private FragmentMyTicketBinding binding;
    private MyDatabase db;
    PurchaseMovieTicketAdapter adapter;
    private ArrayList<Movie> itemsToSend = new ArrayList<>();
    List<Movie> movieList;

    public FragmentMyTicket() {
        super(R.layout.fragment_my_ticket);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.db = MyDatabase.getDatabase(getContext());

        movieList = this.db.movieDAO().getAllMovies();

        if(movieList.size() == 0)
        {
            binding.tvDescOfTicket.setText("You Do Not Have Any Tickets");
        }
        else{
            binding.tvDescOfTicket.setText("");
        }
        itemsToSend.addAll(movieList);

//         setup the adapter
        this.adapter = new PurchaseMovieTicketAdapter(getContext(), itemsToSend, FragmentMyTicket.this);

        // configure recycler view
        this.binding.rvPurchaseMovieTicket.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.rvPurchaseMovieTicket.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        // set up recycler view
        binding.rvPurchaseMovieTicket.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPurchaseMovieTicket.setAdapter(adapter);
    }

    // lifecycle functions - required for configuring view bindings
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyTicketBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRowItemClicked(Movie movie) {
        db.movieDAO().deleteMovie(movie);

        List<Movie> movieList = this.db.movieDAO().getAllMovies();

        itemsToSend.clear();
        itemsToSend.addAll(movieList);
        adapter.notifyDataSetChanged();

        if (movieList.size() == 0) {
            Snackbar.make(binding.getRoot(), "No Movie found", Snackbar.LENGTH_SHORT)
                    .show();
            binding.tvDescOfTicket.setText("You Do Not Have Any Tickets");
        }
        else{
            binding.tvDescOfTicket.setText("");
        }

        Snackbar.make(binding.getRoot(), "Deleted!", Snackbar.LENGTH_SHORT)
                .show();
    }

}
