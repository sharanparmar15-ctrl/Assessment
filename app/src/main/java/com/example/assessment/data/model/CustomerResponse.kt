package com.example.assessment.data.model

import com.google.gson.annotations.SerializedName

data class CustomerResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phoneNo") val phoneNo: String,
    @SerializedName("city") val city: String,
)