package com.example.contacts.di

import com.example.contacts.api.UserContactsInteractor
import com.example.contacts.model.UserCardsListRepository
import com.example.contacts.viewmodel.page.ContactPageViewModelFactory
import com.example.contacts.viewmodel.list.ContactsListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideContactPageViewModelFactory(): ContactPageViewModelFactory =
        ContactPageViewModelFactory()

    @Provides
    fun provideContactsListViewModelFactory(userCardsListRepository: UserCardsListRepository): ContactsListViewModelFactory =
        ContactsListViewModelFactory(userCardsListRepository)
}