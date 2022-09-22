package com.example.contacts.mapping

import com.example.contacts.model.UserContact
import com.example.contacts.model.UserContactItem

class ToUserContactItemListMapperImpl(val userItemMapper: ToUserItemMapper) : ToUserContactItemListMapper {
    override fun map(contacts: List<UserContact>): List<UserContactItem>  {
        return contacts.map { userItemMapper.map(it) }
    }
}