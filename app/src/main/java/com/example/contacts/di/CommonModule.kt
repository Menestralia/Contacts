package com.example.contacts.di

import com.example.contacts.api.ContactsApi
import com.example.contacts.api.UserContactsInteractor
import com.example.contacts.mapping.ToUserContactItemListMapper
import com.example.contacts.mapping.ToUserContactItemListMapperImpl
import com.example.contacts.mapping.ToUserItemMapper
import com.example.contacts.mapping.ToUserItemMapperImpl
import com.example.contacts.model.*
import com.example.contacts.room.database.UserCardDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommonModule {

    @Provides
    @Singleton
    fun providesUserCardListLocalData(userCardDataBase: UserCardDataBase): UserCardListLocalData =
        RoomUserCardListLocalData(userCardDataBase.userCardListDao())

    @Provides
    @Singleton
    fun providesUserCardsListRepository(
        userCardListLocalData: UserCardListLocalData,
        userCardListRemoteData: UserCardListRemoteData
    ): UserCardsListRepository =
        UserCardsListRepository(userCardListLocalData, userCardListRemoteData)

    @Provides
    @Singleton
    fun providesUserContactsInteractor(
        contactsApi: ContactsApi,
        userContactItemListMapper: ToUserContactItemListMapper
    ): UserCardListRemoteData =
        UserContactsInteractor(contactsApi, userContactItemListMapper)

    @Provides
    @Singleton
    fun providesToUserContactItemListMapper(userItemMapper: ToUserItemMapper): ToUserContactItemListMapper =
        ToUserContactItemListMapperImpl(userItemMapper)

    @Provides
    @Singleton
    fun providesToUserItemMapper(): ToUserItemMapper =
        ToUserItemMapperImpl()
}