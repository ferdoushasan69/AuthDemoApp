package com.example.authdemo.presentation.sign_up

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.authdemo.R
import com.example.authdemo.data.model.SignUpRequest
import com.example.authdemo.presentation.component.CustomButton
import com.example.authdemo.presentation.component.CustomTextField
import com.example.authdemo.presentation.navigation.Screen
import com.example.authdemo.utils.Resource

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val uiState by viewModel.signUpState.collectAsState()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        // Pass loading state to SignUpContent
        SignUpContent(
            onSignUp = { signUpRequest ->
                viewModel.signUp(
                    username = signUpRequest.username,
                    email = signUpRequest.email,
                    password = signUpRequest.password,
                )
            },
            isProcessing = uiState is Resource.Loading,
            onLoginPage = {
                navHostController.navigate(Screen.Login)
            }
        )

        // Observe API result
        when (uiState) {
            is Resource.Success -> {
                Toast.makeText(context, "Sign up success!", Toast.LENGTH_SHORT).show()
                navHostController.navigate(Screen.Login) {
                    popUpTo(Screen.SignUp) { inclusive = true }
                }
            }

            is Resource.Error -> {
                val error = (uiState as Resource.Error).message
                Log.d("TAG", "SignUpScreen: $error")
                Toast.makeText(context, error ?: "Sign up failed", Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }
}


@Composable
fun SignUpContent(
    onSignUp: (SignUpRequest) -> Unit,
    isProcessing: Boolean = false,
    onLoginPage: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var retypePassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    var firstNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var rePassError by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.create_your_account),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(Modifier.height(26.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = firstName,
            onValueChange = {
                firstName = it
                firstNameError = false
            },
            text = stringResource(R.string.user_name),
            placeHolder = stringResource(R.string.john),
            isError = firstNameError,
            errorText = "Invalid first name"
        )
        Spacer(Modifier.width(8.dp))
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
                emailError = false

            },
            text = stringResource(R.string.email),
            placeHolder = stringResource(R.string.john_doe_example_com),
            isError = emailError,
            errorText = "Invalid email"
        )
        Spacer(Modifier.height(12.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
                passwordError = false

            },
            text = stringResource(R.string.password),
            placeHolder = stringResource(R.string.enter_your_password),
            isError = passwordError,
            errorText = "Invalid password"
        )
        Spacer(Modifier.height(12.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = retypePassword,
            onValueChange = {
                retypePassword = it
                rePassError = false

            },
            text = stringResource(R.string.retype_password),
            placeHolder = stringResource(R.string.enter_your_retype_password),
            isError = rePassError,
            errorText = "password doesn't match"
        )
        Spacer(Modifier.height(26.dp))
        CustomButton(
            buttonText = stringResource(R.string.sign_up),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val signUp = SignUpRequest(
                    username = firstName.lowercase(), // unique username
                    email = email,
                    password = password,

                    )
                if (firstName.isBlank() || email.isBlank() || password.isBlank() || retypePassword.isBlank()) {
                    firstNameError = true
                    emailError = true
                    passwordError = true
                    rePassError = true
                    return@CustomButton
                }
                if (firstName.isBlank()) {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    firstNameError = true
                    return@CustomButton
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailError = true
                    return@CustomButton
                }

                if (password.length < 6) {
                    Toast.makeText(context, "Password must be at least 6 char", Toast.LENGTH_SHORT)
                        .show()
                    passwordError = true
                    return@CustomButton
                }

                if (password != retypePassword) {
                    Toast.makeText(context, "Password doesn't match", Toast.LENGTH_SHORT).show()
                    rePassError = true
                    return@CustomButton
                }
                onSignUp(signUp)
                firstName = ""
                email = ""
                password = ""
                retypePassword = ""
            },
            isProcessing = isProcessing
        )
        Spacer(Modifier.height(16.dp))

        OutlinedButton(onClick = onLoginPage, modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()) {
            Text(
                stringResource(R.string.or_sign_in_to_your_existing_account),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Blue.copy(.5f)
            )
        }
        Spacer(Modifier.height(8.dp))

    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignUpScreenPreView() {


}