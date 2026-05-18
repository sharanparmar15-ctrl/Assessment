package com.example.assessment.data.repository

import com.example.assessment.data.model.CustomerRequest
import com.example.assessment.data.model.CustomerResponse
import com.example.assessment.data.network.ApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUserRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun registerCustomers(request: CustomerRequest) : Response<CustomerResponse> {
        return apiService.registerCustomer(request)
    }
}