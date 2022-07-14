package com.example.contacts.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.state.StateListFragment
import com.example.contacts.model.UserContactItem
import com.example.contacts.api.UserContacts
import com.example.contacts.fragment.ContactListFragment.Companion.TAG
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class ContactsListViewModel : ViewModel() {

    private var contactsListMutable: MutableLiveData<List<UserContactItem>?> = MutableLiveData()
    var contactsList: LiveData<List<UserContactItem>?> = contactsListMutable
    private var filterContactsListMutable: MutableLiveData<List<UserContactItem>?> = MutableLiveData()
    var filterContactsList: LiveData<List<UserContactItem>?> = filterContactsListMutable
    val selectedContact = MutableLiveData<UserContactItem?>()
    var stateList = MutableLiveData<StateListFragment>()
    var errorLoading: String? = null
    private var timerBeforeNextLoad: Disposable? = null
    private var TIME_BEFORE_NEXT_LOAD = 60
    private var isTimerExpired = true

    fun loadContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            updateContacts()
        }
    }

    fun setDefaultState() {
        stateList.postValue(StateListFragment.DEFAULT)
    }

    fun setStateSearching() {
        stateList.postValue(StateListFragment.FILTER_ITEMS)
    }

    fun filterContacts(searchStringText: String) =
        flow {
            val newListItems = contactsListMutable.value?.filter {
                it.name.lowercase().contains(searchStringText.lowercase()) ||
                        getPhoneNumbers(it.phone).contains(searchStringText)
            }
            Log.e(TAG, newListItems.toString())
            filterContactsListMutable.postValue(newListItems)
            if(searchStringText.isEmpty())
                setDefaultState()
            else setStateSearching()
            emit(newListItems)
    }

    private fun getPhoneNumbers(phoneNumber: String) = phoneNumber
        .replace("(","")
        .replace(")","")
        .replace("+", "")
        .replace(" ","")

    private suspend fun updateContacts() {
        stateList.postValue(StateListFragment.PROCESSING)
        val contacts = getContacts()?.toMutableList()
        contacts?.let { contactsListMutable.postValue(it) }
        Log.e(TAG, "contacts: ${contactsListMutable.value?.size}")
        if (!contacts.isNullOrEmpty())
            stateList.postValue(StateListFragment.DEFAULT)
        updateTimerBeforeNextLoading()
    }

    private suspend fun getContacts(): List<UserContactItem>? {
        var userContacts = UserContacts()
        var contactsList = mutableListOf<UserContactItem>()
        try {
            val list = userContacts.getContactsList()
            contactsList.addAll(list)

        } catch (exception: Exception) {
            stateList.postValue(StateListFragment.ERROR)
            errorLoading = exception.message
        }
        return contactsList
    }

    private fun updateTimerBeforeNextLoading() {
        isTimerExpired = false
        timerBeforeNextLoad = Observable
            .timer(TIME_BEFORE_NEXT_LOAD.toLong(), TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io()).subscribe {
                    isTimerExpired = true
            }
    }

    fun isTimerExpired() = isTimerExpired

    fun getContactList() = contactsList.value

    fun getFilterList() = filterContactsList.value

    fun onContactSelected(contact: UserContactItem?) {
        selectedContact.postValue(contact)
    }

}