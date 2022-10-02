package com.example.contacts.model

import com.example.contacts.room.dao.UserCardListEntity

interface UserCardListLocalData {

    suspend fun loadAllData(): List<UserCardListEntity>

    suspend fun saveRemoteData(data: List<UserContactItem>)
}