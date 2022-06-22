package ru.surfstudio.summerschool.lifecycle.usual

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.surfstudio.summerschool.R
import ru.surfstudio.summerschool.databinding.ActivityExampleLifecycleBinding

class LifecycleUsualActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExampleLifecycleBinding

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExampleLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(R.string.title_lifecycle_usual)

        updateText()
        binding.btnClick.setOnClickListener {
            counter++
            updateText()
        }
    }

    private fun updateText() {
        binding.tvExample.text = getString(R.string.example, counter)
    }
}