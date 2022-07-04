package ru.surfstudio.summerschool.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.core.app.NotificationCompat
import ru.surfstudio.summerschool.R

class PlayerService : Service() {

    private lateinit var player: MediaPlayer

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ServiceManager.isServiceRunning = true
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        player.isLooping = true
        player.start()
        startForeground(1, getNotification())
        return START_STICKY
    }

    override fun onDestroy() {
        ServiceManager.isServiceRunning = false
        super.onDestroy()
        player.stop()
        player.release()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun getNotification(): Notification {
        val intent = Intent(this, PlayerServiceActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder = NotificationCompat.Builder(this, "channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("Test content")
            .setContentTitle("Test title")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        createNotificationChannel()
        return builder.build()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("channel", "Test channel name", importance).apply {
                description = "Hmmm"
            }
            // Register the channel with the system
            val notificationManager: NotificationManager = getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}