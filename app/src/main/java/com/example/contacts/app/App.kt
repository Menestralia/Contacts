package com.example.contacts.app

import android.app.Application
import com.example.contacts.di.AppComponent
import com.example.contacts.di.AppModule
import com.example.contacts.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }

}