package com.example.contacts.api

import com.example.contacts.mapping.ToUserContactItemListMapper
import com.example.contacts.model.UserContact
import com.example.contacts.model.UserContactItem
import kotlinx.coroutines.*
import java.lang.Exception


class UserContactsInteractor(
    val contactsApi: ContactsApi,
    val userContactItemListMapper: ToUserContactItemListMapper
) {

    suspend fun getContactsList(): List<UserContactItem> {
        return coroutineScope {
            mutableListOf(
                loadFirstSourceList()
            ).apply {
                add(loadSecondSourceList())
                add(loadThirdSourceList())
            }.awaitAll().map { userContactItemListMapper.map(it) }
        }
            .flatten()
    }

    private fun CoroutineScope.loadFirstSourceList() = async {
        val response = contactsApi.getFirstSourceContacts()
        val contactList = mutableListOf<UserContact>()
        if (response.isSuccessful) {
            val userItems: List<UserContact> = response.body()
                ?: mutableListOf()
            contactList.addAll(userItems)
        } else {
            throw Exception(response.body().toString())
        }
        return@async contactList
    }

    private fun CoroutineScope.loadSecondSourceList() = async {
        val response = contactsApi.getSecondSourceContacts()
        val contactList = mutableListOf<UserContact>()
        if (response.isSuccessful) {
            val userItems: List<UserContact> = response.body()
                ?: mutableListOf()
            contactList.addAll(userItems)
        } else {
            throw Exception(response.body().toString())
        }
        return@async contactList
    }

    private fun CoroutineScope.loadThirdSourceList() = async {
        val response = contactsApi.getThirdSourceContacts()
        val contactList = mutableListOf<UserContact>()
        if (response.isSuccessful) {
            val userItems: List<UserContact> = response.body()
                ?: mutableListOf()
            contactList.addAll(userItems)
        } else {
            throw Exception(response.body().toString())
        }
        return@async contactList
    }
}

