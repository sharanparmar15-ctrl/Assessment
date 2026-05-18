package com.example.assessment.ui.feature.Success

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.assessment.ui.navigation.NavArgsConstants.CITY
import com.example.assessment.ui.navigation.NavArgsConstants.EMAIL
import com.example.assessment.ui.navigation.NavArgsConstants.NAME
import com.example.assessment.ui.navigation.NavArgsConstants.PHONE_NO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SuccessViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val name  = savedStateHandle.getStateFlow(NAME,  "")
    val email = savedStateHandle.getStateFlow(EMAIL, "")
    val phone = savedStateHandle.getStateFlow(PHONE_NO, "")
    val city  = savedStateHandle.getStateFlow(CITY,  "")
}
