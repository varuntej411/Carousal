package com.corpus.carousal.data.viewmodel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corpus.carousal.utils.UserDataStoreImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(dataStore: DataStore<Preferences>) :
    ViewModel() {

    private val userPref = UserDataStoreImpl(dataStore)

    val userLoggedIn = userPref.getUserLoggedIn().stateIn(
        scope = viewModelScope,
        initialValue = false,
        started = SharingStarted.WhileSubscribed()
    )

}