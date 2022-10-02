package com.example.contacts.viewmodel.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.model.UserCardsListRepository
import javax.inject.Inject

class ContactsListViewModelFactory @Inject constructor(
    val userCardsListRepository: UserCardsListRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsListViewModel(userCardsListRepository) as T
    }
}