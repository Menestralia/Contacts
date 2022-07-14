package com.example.contacts.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.contacts.model.UserContactItem
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

class ContactPageViewModel(contactItem: UserContactItem) : ViewModel() {
    private val userContact = contactItem

    fun getContactItem() = userContact

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(date: String): String {
        val dateInstant = Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(date))
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        val newDate = Date.from(dateInstant)
        return formatter.format(newDate)
    }

    fun onCallPhoneNumber(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL);
        intent.data = Uri.parse("tel:$phoneNumber");
        startActivity(context, intent, null);
    }
}