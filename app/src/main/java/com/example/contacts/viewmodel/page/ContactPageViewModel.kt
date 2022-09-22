package com.example.contacts.viewmodel.page

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

class ContactPageViewModel : ViewModel() {

    @SuppressLint("SimpleDateFormat")
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