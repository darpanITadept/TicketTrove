package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.assignment4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    static final String TAG = "tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // setup the bottom navigation menu
//        // - get a reference to the FragmentContainerView
//        // - use the FragmentContainerView to retrieve a reference to the Navigation Controller
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//        // - Associate the bottom navigation view component with the navigation controller
        NavController navController = navHostFragment.getNavController();
//        // - The Navigation Controller will automatically manage switching between fragments when
//        // menu options are clicked
        NavigationUI.setupWithNavController(binding.bottomNavView,navController);
    }
}
