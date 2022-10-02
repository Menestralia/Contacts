package com.example.contacts.room.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contacts.room.dao.UserCardListEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class UserCardListEntity(
    @PrimaryKey @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "phone")
    var phone: String,
    @ColumnInfo(name = "height")
    var height: Float,
    @ColumnInfo(name = "biography")
    var biography: String,
    @ColumnInfo(name = "temperament")
    var temperament: String,
    @ColumnInfo(name = "educationPeriod")
    var educationPeriod: String
) {
    companion object {
        const val TABLE_NAME = "USER_CARD_LIST_TABlE_NAME"
    }
}