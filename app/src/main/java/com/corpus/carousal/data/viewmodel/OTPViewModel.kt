package com.corpus.carousal.data.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corpus.carousal.utils.UserDataStoreImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor(private val dataStore: DataStore<Preferences>) : ViewModel() {
    private val _otpCode = mutableStateOf(List(6) { "" })
    val otpCode: State<List<String>> = _otpCode

    private val _isVerified = mutableStateOf(false)
    val isVerified: State<Boolean> = _isVerified

    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    private val userPref = UserDataStoreImpl(dataStore)

    fun saveUserLoggedIn(userLoggedIn: Boolean) {
        viewModelScope.launch {
            userPref.saveUserLoggedIn(userLoggedIn)
        }
    }

    fun onOtpValueChange(index: Int, newValue: String) {
        val updatedOtpCode = otpCode.value.toMutableList()
        updatedOtpCode[index] = newValue
        _otpCode.value = updatedOtpCode
    }

    fun verifyOTP() {
        val enteredOtp = otpCode.value.joinToString("")
        val correctOtp = "123456" // Simulate a correct OTP for this example

        if (enteredOtp == correctOtp) {
            _isVerified.value = true
            _errorMessage.value = ""
        } else {
            _isVerified.value = false
            _errorMessage.value = "Invalid OTP. Please try again with 123456."
        }
    }
}