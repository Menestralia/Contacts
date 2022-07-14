package com.example.contacts.model

data class UserContactItem(
    val id: String,
    val name: String,
    val phone: String,
    val height: Float,
    val biography: String,
    val temperament: String,
    val educationPeriodStart: String,
    val educationPeriodEnd: String
)