package com.example.contacts.di

import com.example.contacts.api.ContactsApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ServiceModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun providesContactsApi(retrofit: Retrofit): ContactsApi =
        retrofit.create(ContactsApi::class.java)

}