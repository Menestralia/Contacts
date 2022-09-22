package com.example.contacts.mapping

import com.example.contacts.model.UserContact
import com.example.contacts.model.UserContactItem

interface ToUserContactItemListMapper {

    fun map(contacts: List<UserContact>): List<UserContactItem>

}