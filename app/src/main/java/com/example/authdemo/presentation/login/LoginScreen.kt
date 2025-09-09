package com.example.authdemo.presentation.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.authdemo.R
import com.example.authdemo.presentation.component.CustomButton
import com.example.authdemo.presentation.component.CustomTextField
import com.example.authdemo.presentation.navigation.Screen
import com.example.authdemo.utils.Resource
import kotlin.math.log

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current

    LoginContent(
        onLogin = { email, password ->
            viewModel.login(email = email, password = password)
        },
        isProcessing = loginState is Resource.Loading,
        onSignUpPage = {
            navHostController.navigate(Screen.SignUp)
        }
    )

    when (loginState) {
        is Resource.Success -> {
            Toast.makeText(context, "Login success!", Toast.LENGTH_SHORT).show()
            navHostController.navigate(Screen.Home) {
                popUpTo(Screen.Login) { inclusive = true }
            }

        }

            is Resource.Error -> {
                val error = (loginState as Resource.Error).message
                Toast.makeText(context, error ?: "Email or password not found", Toast.LENGTH_SHORT).show()
        }

        else -> {}
    }
}

@Composable
fun LoginContent(
    onLogin: (String, String) -> Unit,
    isProcessing: Boolean = false,
    onSignUpPage : ()-> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.sign_in_to_your_account),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(Modifier.height(26.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            isError = emailError,
            onValueChange = {
                email = it
                emailError = false
            },
            text = stringResource(R.string.email_or_phone_number),
            placeHolder = stringResource(R.string.john_doe_example_com),
            errorText = "Invalid email or phone number"
        )
        Spacer(Modifier.height(16.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            isError = passwordError,
            onValueChange = {
                password = it
                passwordError = false
            },
            text = stringResource(R.string.password),
            placeHolder = stringResource(R.string.enter_your_password),
            errorText = "Invalid password"
        )
        Spacer(Modifier.height(26.dp))

        CustomButton(
            buttonText = stringResource(R.string.sign_in),
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                if (email.isBlank() || password.isBlank()) {
                    Toast.makeText(context, "Input can't be empty", Toast.LENGTH_SHORT).show()
                    emailError = email.isBlank()
                    passwordError = password.isBlank()
                    return@CustomButton
                }
                onLogin(email, password)
                email = ""
                password = ""

            },
            enabled = !isProcessing,
            isProcessing = isProcessing
        )
        Spacer(Modifier.height(16.dp))

        OutlinedButton(onClick = onSignUpPage, modifier = Modifier.height(56.dp).fillMaxWidth()) {
            Text(
                stringResource(R.string.or_create_a_new_account),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Blue.copy(.5f)
            )
        }
        Spacer(Modifier.height(8.dp))
    }
}


@Preview(showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
//    LoginScreen()


}