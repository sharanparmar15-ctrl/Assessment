package com.example.assessment.data.repository

import com.example.assessment.data.model.CustomerRequest
import com.example.assessment.data.model.CustomerResponse
import com.example.assessment.data.network.ApiResult
import com.example.assessment.data.network.ApiService
import com.example.assessment.data.network.NetworkHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUserRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val apiService: ApiService
) {
    suspend fun registerCustomers(request: CustomerRequest): ApiResult<CustomerResponse> {
        return networkHandler.safeApiCall {
            apiService.registerCustomer(request)
        }
    }
}