package ru.surfstudio.summerschool.lifecycle.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.surfstudio.summerschool.R
import ru.surfstudio.summerschool.databinding.ActivityLifecycleBinding
import ru.surfstudio.summerschool.lifecycle.fixed.LifecycleFixedActivity
import ru.surfstudio.summerschool.lifecycle.usual.LifecycleUsualActivity
import ru.surfstudio.summerschool.lifecycle.viewmodel.LifecycleViewModelActivity

class LifecycleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLifecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(R.string.title_lifecycle)

        with(binding) {
            btnDefaultLifecycle.setOnClickListener { open<LifecycleUsualActivity>() }
            btnFixedLifecycle.setOnClickListener { open<LifecycleFixedActivity>() }
            btnViewModel.setOnClickListener { open<LifecycleViewModelActivity>() }
        }
    }

    private inline fun <reified ActivityGeneric : Activity> open() {
        val activityClassToOpen = ActivityGeneric::class.java
        val newIntent = Intent(this, activityClassToOpen)
        startActivity(newIntent)
    }
}