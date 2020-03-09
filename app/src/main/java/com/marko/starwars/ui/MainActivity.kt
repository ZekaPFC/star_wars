package com.marko.starwars.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marko.starwars.DI.component.AppComponent
import com.marko.starwars.R
import com.marko.starwars.base.BaseApp

class MainActivity : AppCompatActivity() {

    lateinit var appComponent: AppComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (applicationContext as BaseApp).appComponent
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
