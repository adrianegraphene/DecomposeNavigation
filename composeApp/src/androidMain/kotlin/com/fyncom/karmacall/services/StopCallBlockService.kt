package com.fyncom.karmacall.services

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log

class StopCallBlockService: BroadcastReceiver() {
    private val TAG: String = StopCallBlockService::class.java.simpleName
    private val prefs: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
//    private val API_KEY: String = BuildConfig.API_KEY

    //todo figured out how to move from SharedPrefs to Room or other...
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: " + intent!!.action)
        // Call your method to stop the services
//        prefs = context!!.getSharedPreferences("prefs", Context.MODE_PRIVATE)
//        Utils.stopBackgroundReceiverService(context, prefs, CallControlOnFragment::class.java)
//        Log.d(com.fyncom.robocash.utils.Utils.TAG, "STOPPING background Receiver Service from: " + classToHandle.getSimpleName())
//        stopServiceIfRunning(context, RunningService::class.java, prefs)
//        stopServiceIfRunning(context, BackgroundReceiverService::class.java, prefs)
//        stopServiceIfRunning(context, CallScreenService::class.java, prefs)
//        editor = prefs.edit()
//        editor.putBoolean("stopScreening", true)
//        editor.apply()
    }

    private fun stopServiceIfRunning(context: Context, serviceClass: Class<*>, prefs: SharedPreferences) {
        if (isServiceRunning(context, serviceClass)) {
            Log.d(TAG, "STOPPING stopServiceIfRunning for: " + serviceClass.simpleName)
//            editor = prefs.edit()
//            editor.putBoolean("userStopped", true)
//            editor.apply()
            val stopIntent = Intent(context, serviceClass)
            stopIntent.setAction(RunningService.Actions.STOP.name)
            context.stopService(stopIntent)
        }
    }

    fun isServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) return true
        }
        return false
    }

}