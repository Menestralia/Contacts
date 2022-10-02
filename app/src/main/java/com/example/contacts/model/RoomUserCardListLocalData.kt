package com.example.contacts.model

import com.example.contacts.room.dao.UserCardListDao
import com.example.contacts.room.dao.UserCardListEntity

class RoomUserCardListLocalData(
    private val userCardListDao: UserCardListDao
) : UserCardListLocalData {
    override suspend fun loadAllData(): List<UserCardListEntity> =
        userCardListDao.loadAllUserCards()

    override suspend fun saveRemoteData(data: List<UserContactItem>) {
        userCardListDao.saveAllUserCards(
            data.map {
                UserCardListEntity(
                    id = it.id,
                    name = it.name,
                    phone = it.phone,
                    height = it.height,
                    biography = it.biography,
                    temperament = it.temperament,
                    educationPeriod = it.educationPeriodStart + " " + it.educationPeriodEnd
                )
            }
        )
    }
}