package com.example.contacts.api

import android.util.Log
import com.example.contacts.model.UserContact
import com.example.contacts.model.UserContactItem
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class UserContacts {

    private var contactsApi: ContactsApi

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        contactsApi = retrofit.create(ContactsApi::class.java)
    }

    suspend fun getContactsList(): List<UserContactItem> {
        return coroutineScope {
            mutableListOf(
                loadFirstSourceList()
            ).apply {
                add(loadSecondSourceList())
                add(loadThirdSourceList())
            }.awaitAll().map { mapToUserContactItemList(it) }
        }
            .flatten()
    }

    private fun CoroutineScope.loadFirstSourceList() = async {
        val response = contactsApi.getFirstSourceContacts()
        val contactList = mutableListOf<UserContact>()
        if (response.isSuccessful) {
            var userItems: List<UserContact> = response.body()
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
            var userItems: List<UserContact> = response.body()
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
            var userItems: List<UserContact> = response.body()
                ?: mutableListOf()
            contactList.addAll(userItems)
        } else {
            throw Exception(response.body().toString())
        }
        return@async contactList
    }

    private fun mapToUserContactItemList(contacts: List<UserContact>): List<UserContactItem> {
        return contacts.map { mapToUserItem(it) }
    }

    private fun mapToUserItem(contact: UserContact): UserContactItem {
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

