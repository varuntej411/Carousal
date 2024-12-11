package com.corpus.carousal.data.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> = _phoneNumber

    // State for validation result
    private val _isValid = mutableStateOf(true)
    val isValid: State<Boolean> = _isValid


    // Function to update phone number and validate it
    fun onPhoneNumberChanged(newNumber: String) {
        _phoneNumber.value = newNumber
        _isValid.value = validatePhoneNumber(newNumber)
    }

    // Function to validate phone number using a regex
    private fun validatePhoneNumber(phoneNumber: String): Boolean {
        // Simple regex to validate phone numbers with optional '+' at the start and 10 to 15 digits
        val regex = "^[+]?[0-9]{10}$".toRegex()
        return phoneNumber.matches(regex)
    }

}