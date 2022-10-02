package com.example.contacts.model

interface UserCardListRemoteData {

    suspend fun getContactsList() : List<UserContactItem>
}