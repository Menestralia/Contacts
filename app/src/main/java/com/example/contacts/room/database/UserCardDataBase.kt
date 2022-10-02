package com.example.contacts.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contacts.room.dao.UserCardListDao
import com.example.contacts.room.dao.UserCardListEntity

@Database(
    entities = [UserCardListEntity::class],
    version = 1,
    exportSchema = true
)
abstract class UserCardDataBase: RoomDatabase() {

    abstract fun userCardListDao(): UserCardListDao

    companion object {
        const val DATABASE_NAME = "USER_CARD_DATABASE"
    }
}