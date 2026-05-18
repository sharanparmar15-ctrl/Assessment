package com.example.assessment.ui.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment.data.model.CustomerRequest
import com.example.assessment.data.repository.RegisterUserRepository
import com.example.assessment.ui.state.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(private val repo: RegisterUserRepository) :
    ViewModel() {

    private val _apiState =
        MutableStateFlow<ApiState<*>>(
            ApiState.Idle
        )

    val apiState = _apiState.asStateFlow()

    fun registerCustomer(request: CustomerRequest) {
        try {
            viewModelScope.launch {
                _apiState.value = ApiState.Loading
                val response = withContext(Dispatchers.IO) {
                    repo.registerCustomers(request)
                }
                if (response.isSuccessful) {
                    _apiState.value = ApiState.Success(response.body())
                } else {
                    _apiState.value = ApiState.Error("Error")
                }

            }
        } catch (_: Exception) {
            _apiState.value = ApiState.Error("Something went Wrong")
        }
    }
}