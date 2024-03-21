package com.example.music

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MyApplication : Application() {

    private var mFirebaseDatabase: FirebaseDatabase? = null

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        mFirebaseDatabase = FirebaseDatabase.getInstance(FIREBASE_URL)
        createChannelNotification()
    }

    private fun createChannelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_MIN)
            channel.setSound(null, null)
            val manager: NotificationManager = getSystemService<NotificationManager>(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    fun getSongsDatabaseReference(): DatabaseReference? {
        return mFirebaseDatabase?.getReference("/songs")
    }

    fun getFeedbackDatabaseReference(): DatabaseReference? {
        return mFirebaseDatabase?.getReference("/feedback")
    }

    fun getCountViewDatabaseReference(songId: Int): DatabaseReference? {
        return mFirebaseDatabase?.getReference("/songs/$songId/count")
    }

    companion object {
        const val CHANNEL_ID: String = "channel_music_basic_id"
        const val CHANNEL_NAME: String = "channel_music_basic_name"
        operator fun get(context: Context?): MyApplication {
            return context?.applicationContext as MyApplication
        }
        // Firebase url
        const val FIREBASE_URL: String = "https://musicbasic-251ca-default-rtdb.firebaseio.com"
    }
}