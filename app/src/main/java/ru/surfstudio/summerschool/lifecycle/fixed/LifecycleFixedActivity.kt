package ru.surfstudio.summerschool.lifecycle.fixed

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isVisible
import ru.surfstudio.summerschool.R
import ru.surfstudio.summerschool.databinding.ActivityExampleLifecycleBinding

private const val ARG_COUNTER = "argCounter"

class LifecycleFixedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExampleLifecycleBinding

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logEvent("onCreate")
        initViews()
        // finish()
    }

    private fun logEvent(event: String) {
        if (logsEnabled()) {
            Toast.makeText(this, event, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        logEvent("onStart")
    }

    override fun onResume() {
        super.onResume()
        logEvent("onResume")
    }

    override fun onPause() {
        super.onPause()
        logEvent("onPause")
    }

    override fun onStop() {
        super.onStop()
        logEvent("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        logEvent("restarting")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            logEvent("onDestroy")
        } else {
            logEvent("onRestart")
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

    private fun initViews() {
        binding = ActivityExampleLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(R.string.title_lifecycle_fixed)

        updateText()
        binding.btnClick.setOnClickListener {
            counter++
            updateText()

        }
        binding.switchLogs.isVisible = true
        binding.switchLogs.isChecked = logsEnabled()
        binding.switchLogs.setOnClickListener {
            updateLogs((it as SwitchCompat).isChecked)
        }
    }

    private fun updateText() {
        binding.tvExample.text = getString(R.string.example, counter)
    }

    private fun updateLogs(checked: Boolean) {
        val preferences = getPreferences(Context.MODE_PRIVATE)
        preferences.edit()
            .putBoolean("areLogsEnabled", checked)
            .apply()
    }

    private fun logsEnabled(): Boolean {
        val preferences = getPreferences(Context.MODE_PRIVATE)
        return preferences.getBoolean("areLogsEnabled", false)
    }
}