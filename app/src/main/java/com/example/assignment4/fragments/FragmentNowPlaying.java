package com.example.assignment4.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment4.R;
import com.example.assignment4.adapter.PlayingMovieAdapter;
import com.example.assignment4.databinding.ActivityMainBinding;
import com.example.assignment4.databinding.FragmentNowPlayingBinding;
import com.example.assignment4.models.DataResponseArray;
import com.example.assignment4.models.Movie;
import com.example.assignment4.networks.API;
import com.example.assignment4.networks.RetrofitClient;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentNowPlaying extends Fragment {
    private final String TAG = "tag";

    private FragmentNowPlayingBinding binding;
    ActivityMainBinding binding1;
    private PlayingMovieAdapter adapter;
    private ArrayList<Movie> itemsToSend = new ArrayList<>();

    private API api;

    public FragmentNowPlaying() {
        super(R.layout.fragment_now_playing);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // api
        this.api = RetrofitClient.getInstance().getApi();

        Call<DataResponseArray> results = api.getMovies();

        results.enqueue(new Callback<DataResponseArray>() {
            @Override
            public void onResponse(Call<DataResponseArray> call, Response<DataResponseArray> response) {

                if (response.isSuccessful() == false) {
                    Log.d(TAG, "Error from API with response code: " + response.code());
                    return;
                }

                DataResponseArray allMoviesInfo = response.body();
                ArrayList<Movie> mv = allMoviesInfo.getResults();

                itemsToSend.clear();
                itemsToSend.addAll(mv);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DataResponseArray> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });


        // setup the adapter
        this.adapter = new PlayingMovieAdapter(getContext(), itemsToSend);

        // set up recycler view
        binding.rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvMovies.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    // lifecycle functions - required for configuring view bindings
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNowPlayingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}