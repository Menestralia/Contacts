package com.example.contacts.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class, ServiceModule::class, CommonModule::class, RoomModule::class])
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context = context
}