package com.tinoba.template.appliaction

import android.app.Application
import android.content.Context
import com.tinoba.template.dagger.ComponentFactory
import com.tinoba.template.dagger.application.ApplicationComponent
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build



class WalkingTrackerApplication : Application() {

    companion object {
        const val CHANNEL_ID = "exampleServiceChannel"
        fun from(context: Context): WalkingTrackerApplication {
            return context.applicationContext as WalkingTrackerApplication
        }
    }

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = ComponentFactory.createApplicationComponent(this)
        applicationComponent.inject(this)
        createNotificationChannel()
    }

    fun getApplicationComponent() = applicationComponent

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Example Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }
}