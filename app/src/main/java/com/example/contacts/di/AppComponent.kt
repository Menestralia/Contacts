package com.example.contacts.di

import com.example.contacts.fragment.ContactListFragment
import com.example.contacts.fragment.ContactPageFragment
import dagger.*
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: ContactPageFragment)
    fun inject(activity: ContactListFragment)
}
