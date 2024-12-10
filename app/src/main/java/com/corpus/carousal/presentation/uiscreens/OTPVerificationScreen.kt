package com.corpus.carousal.presentation.uiscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.corpus.carousal.data.viewmodel.OTPViewModel
import com.corpus.carousal.navgraph.RootNavGraph

@Composable
fun OTPVerificationScreen(
    navController: NavHostController,
    viewModel: OTPViewModel = viewModel()
) {
    val otpCode = viewModel.otpCode
    val isVerified = viewModel.isVerified
    val errorMessage = viewModel.errorMessage

    val focusRequesters = List(6) { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // OTP Input Fields
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0 until 6) {
                OTPInputField(
                    modifier = Modifier
                        .width(50.dp)
                        .height(60.dp),
                    index = i,
                    value = otpCode.value.getOrNull(i)?.toString() ?: "",
                    onValueChange = { newValue ->
                        viewModel.onOtpValueChange(i, newValue)
                        if (newValue.isNotEmpty() && i < 5) {
                            focusRequesters[i + 1].requestFocus() // Move to next field
                        }
                    },
                    focusRequester = focusRequesters.get(i)
                )

            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // OTP Verification Button
        Button(onClick = {
            viewModel.verifyOTP()
        }) {
            Text(text = "Verify OTP")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Error or Success Message
        if (errorMessage.value.isNotEmpty()) {
            Text(text = errorMessage.value, color = MaterialTheme.colorScheme.error)
        }

        if (isVerified.value) {
            Text(text = "OTP Verified Successfully!", color = MaterialTheme.colorScheme.primary)
            navController.popBackStack()
            navController.navigate(RootNavGraph.HOME_GRAPH)
        }
    }
}

@Composable
fun OTPInputField(
    modifier: Modifier = Modifier,
    index: Int,
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = modifier.focusRequester(focusRequester),
        singleLine = true,
        textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 16.sp)
    )

}

@Preview
@Composable
fun PreviewOTPScreen() {
    OTPVerificationScreen(
        navController = rememberNavController()
    )
}