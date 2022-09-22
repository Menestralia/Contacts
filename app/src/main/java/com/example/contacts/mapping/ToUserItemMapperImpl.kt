package com.example.contacts.mapping

import com.example.contacts.model.UserContact
import com.example.contacts.model.UserContactItem

class ToUserItemMapperImpl: ToUserItemMapper {
    override fun map(contact: UserContact): UserContactItem {
        return UserContactItem(
            id = contact.id,
            name = contact.name,
            phone = contact.phone,
            height = contact.height,
            biography = contact.biography,
            temperament = contact.temperament,
            educationPeriodStart = contact.educationPeriod.start,
            educationPeriodEnd = contact.educationPeriod.end
        )
    }
}