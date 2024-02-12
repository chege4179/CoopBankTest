package com.peterchege.coopbanktest.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.peterchege.coopbanktest.R
import com.peterchege.coopbanktest.core.api.responses.LoginResponse
import com.peterchege.coopbanktest.ui.components.CoopLogo

@Composable
fun WelcomeScreen(
    navigateToLoginScreen: () -> Unit,
    loggedInUser: LoginResponse?,
    viewModel: WelcomeScreenViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = loggedInUser) {
        loggedInUser?.let {
            viewModel.updateUser(it)
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        user?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.dark_green_color))
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Log Out"
                        )
                    }

                    val annotatedString = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            )
                        ) {
                            append("Log Out")
                        }
                        addStringAnnotation("navigate", "clickEvent", 0, "Log Out".length)
                    }

                    ClickableText(
                        text = annotatedString,
                        modifier = Modifier.padding(top = 14.dp, start = 12.dp),
                        onClick = { offset ->
                            annotatedString.getStringAnnotations(offset, offset + 1)
                                .firstOrNull { it.tag == "navigate" }
                                ?.let {
                                    navigateToLoginScreen()
                                }
                        }
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.25f)
                        .fillMaxWidth()
                        .padding()
                        .background(color = colorResource(id = R.color.darker_green_color)),
                    contentAlignment = Alignment.Center
                ) {

                    CoopLogo(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(0.5f),
                    )

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
                            .fillMaxWidth()
                            ,

                        ) {

                        Text(
                            text = buildAnnotatedString {
                                append("Welcome ")
                                withStyle(style = SpanStyle(color = colorResource(id = R.color.light_green_color), fontWeight = FontWeight.Bold)) {
                                    append(it.username)
                                }
                                append(" to the new Co-op Bank App!")
                            },
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(top = 14.dp),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            ),
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }

    }
}
