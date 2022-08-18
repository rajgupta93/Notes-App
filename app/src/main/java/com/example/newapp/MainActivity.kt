package com.example.newapp

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var fab: FloatingActionButton
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController =  findNavController(R.id.fragmentContainerView);
        setupActionBarWithNavController(navController);

    }

    override fun onNavigateUp(): Boolean {
        return super.onNavigateUp() || navController.navigateUp();
    }
}