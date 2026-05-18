package com.example.assessment.data.network

import com.example.assessment.data.model.CustomerRequest
import com.example.assessment.data.model.CustomerResponse
import com.example.assessment.utils.ApiConstants
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(ApiConstants.CUSTOMERS)
    suspend fun registerCustomer(
        @Body request: CustomerRequest
    ): Response<CustomerResponse>
}