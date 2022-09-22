package com.example.contacts.mapping

import com.example.contacts.model.UserContact
import com.example.contacts.model.UserContactItem

interface ToUserItemMapper {
    fun map(contact: UserContact): UserContactItem
}