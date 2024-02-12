package com.peterchege.coopbanktest.ui.screens.welcome

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.peterchege.coopbanktest.core.api.responses.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _user = MutableStateFlow<LoginResponse?>(null)
    val user = _user.asStateFlow()


    fun updateUser(userLoggedIn:LoginResponse){
        _user.update {
            userLoggedIn
        }
    }


}