package com.tinoba.template.service

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.*
import com.tinoba.domain.interactor.SaveImageUrlUseCase
import com.tinoba.domain.model.PhotoLocation
import com.tinoba.template.appliaction.WalkingTrackerApplication
import com.tinoba.template.appliaction.WalkingTrackerApplication.Companion.CHANNEL_ID
import com.tinoba.template.dagger.application.module.ThreadingModule
import com.tinoba.template.ui.home.HomeActivity
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named

class LocationService : Service() {

    companion object {
        private const val SMALLEST_DISPLACEMENT_METERS = 100f
        private const val INTERVAL_MILLISECONDS = 1000L
        private const val FASTEST_INTERVAL_MILLISECONDS = 1000L
    }

    @Inject
    lateinit var saveImageUrlUseCase: SaveImageUrlUseCase

    @Inject
    @field:Named(ThreadingModule.MAIN_SCHEDULER)
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    @field:Named(ThreadingModule.BACKGROUND_SCHEDULER)
    lateinit var backgroundScheduler: Scheduler

    private lateinit var googleApiClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var locationCallback: LocationCallback

    override fun onCreate() {
        super.onCreate()

        WalkingTrackerApplication.from(this).getApplicationComponent().inject(this)
        compositeDisposable = CompositeDisposable()
    }

    private fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearSubscriptions() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, HomeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )

        val notification = NotificationCompat
            .Builder(this, CHANNEL_ID)
            .setContentTitle(getString(com.tinoba.template.R.string.notification_title))
            .setSmallIcon(com.tinoba.template.R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        googleApiClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create()
            .setSmallestDisplacement(SMALLEST_DISPLACEMENT_METERS)
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
            .setInterval(INTERVAL_MILLISECONDS)
            .setFastestInterval(FASTEST_INTERVAL_MILLISECONDS)

        locationCallback = object : LocationCallback() {

            /*
            When we receive the location update, we take the last location, get an image
            url for it and store it to the database.
             */
            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult == null || locationResult.lastLocation == null) {
                    return
                }

                val location = locationResult.lastLocation
                addSubscription(
                    saveImageUrlUseCase.execute(
                        PhotoLocation(
                            location.latitude,
                            location.longitude
                        )
                    )
                        .subscribeOn(backgroundScheduler)
                        .observeOn(mainThreadScheduler)
                        .subscribe({}, Throwable::printStackTrace)
                )
            }
        }

        startLocationUpdates()

        return START_NOT_STICKY
    }

    /**
     * Permissions are requested before starting service, we don't want a service
     * to start at all if user does not have a permission
     */
    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        googleApiClient.requestLocationUpdates(
            locationRequest, locationCallback, null
        )
    }

    override fun onBind(intent: Intent?) = null

    private fun stopLocationUpdates() {
        googleApiClient.removeLocationUpdates(locationCallback)
    }

    override fun onDestroy() {
        clearSubscriptions()
        stopLocationUpdates()
        super.onDestroy()
    }
}
