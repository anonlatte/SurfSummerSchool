package com.example.myapplication.lifecycle.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityExampleLifecycleBinding

class LifecycleViewModelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExampleLifecycleBinding

    private val viewModel by lazy { ViewModelProvider(this).get(LifecycleViewModel::class.java) }

    // private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExampleLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(R.string.title_lifecycle_view_model)

        updateText()
        binding.btnClick.setOnClickListener {
            viewModel.counter++
            updateText()
        }
    }

    private fun updateText() {
        binding.tvExample.text = getString(R.string.example, viewModel.counter)
    }
}