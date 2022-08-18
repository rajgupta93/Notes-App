package com.example.newapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.newapp.databinding.ActivitySplashBinding
import org.w3c.dom.Text
private lateinit var binding: ActivitySplashBinding
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Handler(Looper.getMainLooper()).postDelayed({
            //doSomethingHere()
        val intent = Intent(this,MainActivity::class.java);
        startActivity(intent);
            finish()

        }, 3000)


    }
}