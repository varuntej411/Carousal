package com.corpus.carousal.presentation.uiscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.corpus.carousal.R
import com.corpus.carousal.data.viewmodel.LoginViewModel
import com.corpus.carousal.navgraph.Screens

@Composable
fun LoginScreen(navController: NavHostController) {

    val viewModel: LoginViewModel = hiltViewModel()

    val phoneNumber by viewModel.phoneNumber
    val isValid by viewModel.isValid

    Surface {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 40.dp),
            shape = RoundedCornerShape(4.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.elevatedCardColors(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "logo",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                        .height(150.dp)
                        .width(150.dp),
                    alignment = Alignment.Center
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Login Here !",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Black,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp),
                        value = phoneNumber,
                        onValueChange = { viewModel.onPhoneNumberChanged(it)},
                        label = {
                            Text(
                                text = "Enter Mobile no",
                                fontSize = 14.sp,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Black,
                                textAlign = TextAlign.Start,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        placeholder = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Mobile Number",
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Black,
                                textAlign = TextAlign.Start,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedLeadingIconColor = Color.Blue,
                            unfocusedLeadingIconColor = Color.Gray,
                            focusedLabelColor = Color.Blue,
                            unfocusedLabelColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.LightGray,
                            unfocusedPlaceholderColor = Color.Red,
                            errorTextColor = Color.Red,
                            errorContainerColor = Color.White,
                            errorPlaceholderColor = Color.Red,
                            errorLeadingIconColor = Color.Red,
                            errorIndicatorColor = Color.Red,
                            errorLabelColor = Color.White
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Next
                        ),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_phone),
                                contentDescription = ""
                            )
                        },
                        isError = phoneNumber.isNotEmpty() && phoneNumber.length != 10 && !isValid ,
                        singleLine = true
                    )

                    // Show validation error if phone number is invalid
                    if (phoneNumber.isNotEmpty() && phoneNumber.length != 10 && !isValid) {
                        Text(
                            text = "Invalid mobile number",
                            color = Color.Red,
                            style = TextStyle(color = Color.Red)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    ElevatedButton(
                        onClick = {
                            if (isValid && phoneNumber.isNotEmpty()) {
                                navController.navigate(Screens.OTPScreen.route)
                            }
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        shape = RoundedCornerShape(10.dp),
                        enabled = phoneNumber.isNotEmpty() && phoneNumber.length == 10 && isValid
                    ) {
                        Text(
                            text = "Login",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        navController = rememberNavController()
    )
}