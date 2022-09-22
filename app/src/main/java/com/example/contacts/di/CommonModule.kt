package com.example.contacts.di

import com.example.contacts.api.ContactsApi
import com.example.contacts.api.UserContactsInteractor
import com.example.contacts.mapping.ToUserContactItemListMapper
import com.example.contacts.mapping.ToUserContactItemListMapperImpl
import com.example.contacts.mapping.ToUserItemMapper
import com.example.contacts.mapping.ToUserItemMapperImpl
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    @Provides
    fun providesUserContactsInteractor(
        contactsApi: ContactsApi,
        userContactItemListMapper: ToUserContactItemListMapper
    ): UserContactsInteractor =
        UserContactsInteractor(contactsApi, userContactItemListMapper)

    @Provides
    fun providesToUserContactItemListMapper(userItemMapper: ToUserItemMapper): ToUserContactItemListMapper =
        ToUserContactItemListMapperImpl(userItemMapper)

    @Provides
    fun providesToUserItemMapper(): ToUserItemMapper =
        ToUserItemMapperImpl()
}