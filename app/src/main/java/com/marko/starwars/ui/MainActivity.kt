package com.marko.starwars.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.marko.starwars.R
import com.marko.starwars.base.BaseApp
import com.marko.starwars.di.component.AppComponent

class MainActivity : AppCompatActivity() {

    lateinit var appComponent: AppComponent
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (applicationContext as BaseApp).appComponent
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    fun showAppBar() {
        supportActionBar?.show()
    }

    fun hideAppBar() {
        supportActionBar?.hide()
    }

    fun appBarTitle(title: String) {
        supportActionBar?.title = title
    }
}
