package com.example.contacts.di

import com.example.contacts.fragment.ContactListFragment
import com.example.contacts.fragment.ContactPageFragment
import dagger.*

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: ContactPageFragment)
    fun inject(activity: ContactListFragment)
}
