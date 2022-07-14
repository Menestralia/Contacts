package com.example.contacts.api

import com.example.contacts.model.UserContact
import retrofit2.Response
import retrofit2.http.GET

interface ContactsApi {
    @GET("/SkbkonturMobile/mobile-test-droid/master/json/generated-01.json")
    suspend fun getFirstSourceContacts(): Response<List<UserContact>?>
    @GET("/SkbkonturMobile/mobile-test-droid/master/json/generated-02.json")
    suspend fun getSecondSourceContacts(): Response<List<UserContact>?>
    @GET("/SkbkonturMobile/mobile-test-droid/master/json/generated-03.json")
    suspend fun getThirdSourceContacts(): Response<List<UserContact>?>

}