package com.corpus.carousal

import android.media.MediaRouter.RouteGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.corpus.carousal.navgraph.RootNavGraph
import com.corpus.carousal.navgraph.Screens
import com.corpus.carousal.viewmodel.OTPViewModel

@Composable
fun OTPVerificationScreen(
    navController: NavHostController,
    viewModel: OTPViewModel = viewModel()
) {
    val otpCode = viewModel.otpCode
    val isVerified = viewModel.isVerified
    val errorMessage = viewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // OTP Input Fields
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            for (i in 0 until 6) {
                OTPInputField(
                    modifier = Modifier.width(40.dp),
                    index = i,
                    value = otpCode.value.getOrNull(i)?.toString() ?: "",
                    onValueChange = { newValue -> viewModel.onOtpValueChange(i, newValue) }
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
    onValueChange: (String) -> Unit
) {
    TextField(
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
        keyboardActions = KeyboardActions(
            onNext = { /* Handle next focus if necessary */ }
        ),
        modifier = modifier,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge
    )
}

@Preview
@Composable
fun PreviewOTPScreen() {
    OTPVerificationScreen(
        navController = rememberNavController()
    )
}