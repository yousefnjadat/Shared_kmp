package com.example.shared_kmp.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shared_kmp.common.Result
import com.example.shared_kmp.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState by viewModel.loginState.collectAsState()
    LaunchedEffect(loginState) {
        if (loginState is Result.Success) {
            onLoginSuccess()
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (loginState) {
            is Result.Init -> {
                Text("Log In")
            }

            is Result.Loading -> {
                CircularProgressIndicator()
                Text("Logging in...")
            }

            is Result.Success -> {
                val data = (loginState as Result.Success).data
                Text("Login Successful", color = MaterialTheme.colorScheme.primary)
                Text(data.userId)
                Text(data.userName)
            }

            is Result.Error -> {
                val error = (loginState as Result.Error).exception
                Text(
                    text = error.message ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { viewModel.login(userId, password) },
            modifier = Modifier.fillMaxWidth(),
            enabled = userId.isNotEmpty() && password.isNotEmpty()
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(32.dp))

//        Button(
//            onClick = { viewModel.clearState() },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = loginState != Result.Init
//        ) {
//            Text("Clear State")
//        }
    }
}
