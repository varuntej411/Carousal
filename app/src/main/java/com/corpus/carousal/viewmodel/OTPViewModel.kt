package com.corpus.carousal.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class OTPViewModel : ViewModel() {
    private val _otpCode = mutableStateOf(List(6) { "" })
    val otpCode: State<List<String>> = _otpCode

    private val _isVerified = mutableStateOf(false)
    val isVerified: State<Boolean> = _isVerified

    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

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