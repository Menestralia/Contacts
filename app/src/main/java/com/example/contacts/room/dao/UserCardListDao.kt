package com.example.contacts.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserCardListDao {

    @Query("SELECT * FROM ${UserCardListEntity.TABLE_NAME}")
    suspend fun loadAllUserCards(): List<UserCardListEntity>

    @Insert(entity = UserCardListEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserCard(userCardListEntity: UserCardListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun saveAllUserCards(entities: List<UserCardListEntity>)
}