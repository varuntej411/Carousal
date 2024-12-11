package com.corpus.carousal.data.viewmodel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corpus.carousal.utils.UserDataStoreImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(dataStore: DataStore<Preferences>): ViewModel() {

    private val userPref = UserDataStoreImpl(dataStore)

    fun deleteUserLogin() {
        viewModelScope.launch {
            userPref.deleteUserLoggedIn()
        }
    }
}