package com.fyncom.karmacall.services

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.fyncom.karmacall.R

class RunningService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

            when(intent?.action) {
                Actions.START.name -> start()
                Actions.STOP.name -> stopSelf()
            }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {

        // Create a PendingIntent for stopping the service

        // Create a PendingIntent for stopping the service
        val stopIntent = Intent(this, StopCallBlockService::class.java)
        val stopPendingIntent = PendingIntent.getBroadcast(this, 0, stopIntent, PendingIntent.FLAG_IMMUTABLE)
        val stopAction = NotificationCompat.Action.Builder(R.drawable.ic_stop_custom, getString(R.string.stopBlockingCalls), stopPendingIntent).build()

        // Prepare the notification text and styles
        val contentTitle = getString(R.string.app_name) + " " + getString(R.string.protectedWord)
        val contentText = getString(R.string.readyToHandle)
        val bigTextStyle = NotificationCompat.BigTextStyle()
            .bigText(getString(R.string.press) + " " + getString(R.string.stopBlockingCalls) + " " + getString(R.string.toStop) + " " + getString(R.string.app_name))
            .setBigContentTitle(contentTitle)

        // Build the notification
/*
        return NotificationCompat.Builder(this, com.fyncom.robocash.services.BackgroundReceiverService.NOTIFICATION_CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_karmacall_logo_transparent_inverse)
//            .setContentTitle(contentTitle)
//            .setContentText(contentText)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setPriority(NotificationCompat.PRIORITY_LOW)
//            .setStyle(bigTextStyle)
//            .addAction(stopAction)
            .setContentIntent(launchAppPendingIntent)
            .build()
*/

        val notification = NotificationCompat.Builder(this, "KarmaCall")
            .setSmallIcon(R.drawable.ic_karmacall_logo_transparent_inverse)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .setStyle(bigTextStyle)
            .addAction(stopAction)
            .build()

        startForeground(1, notification)
    }

    enum class Actions {
        START,
        STOP
    }
}