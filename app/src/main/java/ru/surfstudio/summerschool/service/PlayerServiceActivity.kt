package ru.surfstudio.summerschool.service

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.surfstudio.summerschool.R
import ru.surfstudio.summerschool.databinding.ActivityServiceBinding

class PlayerServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnServiceControl.setOnClickListener {
            resolveServiceAction()
            updateServiceButton()
        }
    }

    private fun resolveServiceAction() {
        val intent = Intent(this, PlayerService::class.java)
        if (ServiceManager.isServiceRunning) {
            stopService(intent)
        } else {
            startService(intent)
        }
    }

    private fun updateServiceButton(): Unit = binding.btnServiceControl.run {
        if (ServiceManager.isServiceRunning) {
            setText(R.string.btn_start_service)
            setIconResource(R.drawable.ic_baseline_play_arrow_24)
        } else {
            setText(R.string.btn_stop_service)
            setIconResource(R.drawable.ic_baseline_pause_24)
        }
    }
}