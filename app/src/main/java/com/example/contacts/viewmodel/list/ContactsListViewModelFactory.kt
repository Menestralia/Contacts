package com.example.contacts.viewmodel.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.api.UserContactsInteractor
import javax.inject.Inject

class ContactsListViewModelFactory @Inject constructor(
    val userContactsInteractor: UserContactsInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsListViewModel(userContactsInteractor) as T
    }
}