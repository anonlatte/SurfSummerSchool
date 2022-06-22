package com.example.myapplication.lifecycle.fixed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityExampleLifecycleBinding

private const val ARG_COUNTER = "argCounter"

class LifecycleFixedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExampleLifecycleBinding

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExampleLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(R.string.title_lifecycle_fixed)

        updateText()
        binding.btnClick.setOnClickListener {
            counter++
            updateText()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        counter = savedInstanceState.getInt(ARG_COUNTER)
        updateText()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(ARG_COUNTER, counter)
        super.onSaveInstanceState(outState)
    }

    private fun updateText() {
        binding.tvExample.text = getString(R.string.example, counter)
    }
}