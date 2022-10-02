package com.example.contacts.di

import android.content.Context
import androidx.room.Room
import com.example.contacts.room.database.UserCardDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            UserCardDataBase::class.java,
            UserCardDataBase.DATABASE_NAME
        ).build()
}