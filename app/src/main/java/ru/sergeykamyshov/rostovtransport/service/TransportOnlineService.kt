package ru.sergeykamyshov.rostovtransport.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class TransportOnlineService : Service() {

    private var mBinder: IBinder = LocalBinder()

//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Log.i("TransportOnlineService", "Start service")
//
//        Thread(Runnable {
//            SystemClock.sleep(5000)
//            Log.i("TransportOnlineService", "Self stop service")
//            stopSelf()
//        }).start()
//
//        return super.onStartCommand(intent, flags, startId)
//    }

    override fun onBind(intent: Intent?): IBinder? = mBinder

    fun getTestMessage() = "hello from service"

    inner class LocalBinder : Binder() {
        fun getService(): TransportOnlineService {
            return this@TransportOnlineService
        }
    }
}