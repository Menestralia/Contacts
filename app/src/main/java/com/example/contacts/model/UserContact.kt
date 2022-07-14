package com.example.contacts.model

import com.google.gson.annotations.SerializedName

data class UserContact(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("height")
    var height: Float,
    @SerializedName("biography")
    var biography: String,
    @SerializedName("temperament")
    var temperament: String,
    @SerializedName("educationPeriod")
    var educationPeriod: EducationPeriod
)
data class EducationPeriod(
    var start: String,
    var end: String
)