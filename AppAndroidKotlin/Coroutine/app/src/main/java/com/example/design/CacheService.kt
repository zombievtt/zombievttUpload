package com.example.design

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder

class CacheService : Service() {
    val TAG = CacheService::class.java.simpleName
    override fun onBind(intent: Intent?): IBinder? {
        return null //Todo: return null -> 沒有綁定
    }

    //todo: Ctrl + O -> 決定要覆寫的方法"onStartCommand"
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ") //Todo: logd
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.d(TAG, "onStart: ")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")

    }
}