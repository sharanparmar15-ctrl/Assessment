package com.example.assessment.ui.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment.data.model.CustomerRequest
import com.example.assessment.data.repository.RegisterUserRepository
import com.example.assessment.ui.state.UIState
import com.example.assessment.data.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(private val repo: RegisterUserRepository) :
    ViewModel() {

    private val _uiState =
        MutableStateFlow<UIState<*>>(
            UIState.Idle
        )

    val uiState = _uiState.asStateFlow()

    fun registerCustomer(request: CustomerRequest) {
        viewModelScope.launch {

            _uiState.value = UIState.Loading

            when (val response = repo.registerCustomers(request)) {

                is ApiResult.Success -> {
                    _uiState.value = UIState.Success(response.data)
                }

                is ApiResult.Error -> {
                    _uiState.value = UIState.Error(response.message)
                }
            }
        }
    }
}