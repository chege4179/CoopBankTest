package com.peterchege.coopbanktest.ui.screens.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peterchege.coopbanktest.R
import com.peterchege.coopbanktest.core.api.responses.LoginResponse
import com.peterchege.coopbanktest.ui.components.CoopLogo
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navigateToWelcomeScreen: (LoginResponse) -> Unit,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LoginScreenContent(
        uiState = uiState,
        onChangePassword = viewModel::onChangePassword,
        onChangeUsername = viewModel::onChangeUsername,
        onChangePasswordVisibility = viewModel::onChangePasswordVisibility,
        eventFlow = viewModel.eventFlow,
        loginUser = {
            viewModel.loginUser {
                navigateToWelcomeScreen(it)
            }
        }
    )


}

// State Hoist to enable easy previewing and testing
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreenContent(
    uiState: LoginScreenState,
    onChangePasswordVisibility: () -> Unit,
    onChangeUsername: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    eventFlow: SharedFlow<String>,
    loginUser: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = Unit) {
        eventFlow.collectLatest { event ->
            snackbarHostState.showSnackbar(
                message = event
            )
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.darker_green_color))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.darker_green_color))
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.3f)
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.dark_green_color)),
                ) {

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize(),

                        ) {
                        CoopLogo(
                            Modifier
                                .fillMaxWidth(0.5f)
                                .fillMaxHeight(0.4f)
                                .padding(top = 20.dp)
                                .offset(y = 20.dp),
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(
                            text = stringResource(id = R.string.welcome),
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold
                            ),
                            color = colorResource(id = R.color.light_green_color),
                            textAlign = TextAlign.Center
                        )
                    }

                }
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.coopbuilding),
                        contentDescription = "Coop building",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 80.dp, start = 24.dp, end = 24.dp),

                            ) {
                            Text(
                                text = "Use your credentials to log in",
                                modifier = Modifier
                                    .fillMaxWidth(),
                                style = TextStyle(
                                    fontSize = 22.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = colorResource(id = R.color.light_green_color),
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.padding(top = 20.dp))
                            OutlinedTextField(modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 12.dp)
                                .padding(vertical = 4.dp, horizontal = 10.dp),
                                value = uiState.username,
                                onValueChange = {
                                    onChangeUsername(it)
                                },
                                label = {
                                    Text(
                                        text = stringResource(id = R.string.username)
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.White,
                                    unfocusedBorderColor = Color.White,
                                    cursorColor = Color.White,
                                    focusedLabelColor = Color.White,
                                    unfocusedLabelColor = Color.White,
                                ),
                                textStyle = TextStyle(
                                    fontSize = 18.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White
                                ),
                                keyboardOptions = KeyboardOptions.Default,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "person icon",
                                        tint = Color.White
                                    )

                                }

                            )

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 12.dp)
                                    .padding(vertical = 8.dp, horizontal = 10.dp),
                                value = uiState.password,
                                onValueChange = {
                                    onChangePassword(it)
                                },
                                label = { Text(text = stringResource(id = R.string.password)) },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.White,
                                    unfocusedBorderColor = Color.White,
                                    cursorColor = Color.White,
                                    focusedLabelColor = Color.White,
                                    unfocusedLabelColor = Color.White,
                                ),
                                textStyle = TextStyle(
                                    fontSize = 18.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = stringResource(id = R.string.login),
                                        tint = Color.White
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = if (!uiState.isPasswordVisible)
                                            Icons.Filled.VisibilityOff
                                        else
                                            Icons.Filled.Visibility,
                                        contentDescription = if (!uiState.isPasswordVisible)
                                            stringResource(id = R.string.visibility_on_description)
                                        else
                                            stringResource(id = R.string.visibility_off_description),
                                        modifier = Modifier
                                            .size(26.dp)
                                            .clickable {
                                                onChangePasswordVisibility()
                                            },
                                        tint = Color.White,
                                    )
                                },
                                visualTransformation = if (uiState.isPasswordVisible)
                                    VisualTransformation.None
                                else
                                    PasswordVisualTransformation(),

                                )
                            Spacer(modifier = Modifier.padding(top = 20.dp))
                            Button(
                                onClick = {
                                    keyboardController?.hide()
                                    loginUser()

                                },
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(8.dp),
                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                enabled = !uiState.isLoading
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(48.dp)
                                        .background(
                                            color = colorResource(id = R.color.light_green_color),
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = if (uiState.isLoading)
                                            stringResource(id = R.string.loading)
                                        else
                                            stringResource(id = R.string.login),
                                        fontSize = 18.sp,
                                        color = colorResource(id = R.color.dark_green_color)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
