package com.tinoba.template.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tinoba.template.R
import com.tinoba.template.base.BaseActivity
import com.tinoba.template.base.ScopedPresenter
import com.tinoba.template.dagger.activity.ActivityComponent
import com.tinoba.template.service.LocationService
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    companion object {

        private const val FINE_LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    @Inject
    lateinit var presenter: HomeContract.Presenter

    @Inject
    lateinit var adapter: LocationImageAdapter

    override fun inject(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun getPresenter(): ScopedPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initRecyclerView()
        setClickListeners()
        presenter.clearLocations()
    }

    override fun onResume() {
        super.onResume()

        presenter.observeLocations()
    }

    private fun setClickListeners() {
        serviceButton.setOnClickListener { checkLocationPermission() }
    }

    private fun startService() {
        val serviceIntent = Intent(this, LocationService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)
        serviceButton.setText(R.string.stop_service)
        serviceButton.setOnClickListener { stopService() }
    }

    private fun stopService() {
        val serviceIntent = Intent(this, LocationService::class.java)
        stopService(serviceIntent)

        serviceButton.setText(R.string.start_service)
        serviceButton.setOnClickListener { checkLocationPermission() }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = createLinearLayoutManager()
        recyclerView.adapter = adapter
    }

    private fun checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), FINE_LOCATION_PERMISSION_REQUEST_CODE)
            } else {
                startService()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startService()
        }
    }

    private fun createLinearLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun render(photoUrls: List<String>) {
        if (adapter.itemCount != photoUrls.size){
            adapter.submitList(photoUrls)
        }
    }

    override fun onDestroy() {
        stopService()
        super.onDestroy()
    }
}

