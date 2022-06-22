package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.lifecycle.main.LifecycleActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(R.string.app_name)

        with(binding) {
            btnLifecycle.setOnClickListener { open<LifecycleActivity>() }
        }
    }

    private inline fun <reified ActivityGeneric : Activity> open() {
        val activityClassToOpen = ActivityGeneric::class.java
        val newIntent = Intent(this, activityClassToOpen)
        startActivity(newIntent)
    }
}