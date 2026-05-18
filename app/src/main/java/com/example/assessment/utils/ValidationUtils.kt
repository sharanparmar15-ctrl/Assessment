package com.example.assessment.utils

import android.util.Patterns

object ValidationUtils {
    fun String.validateName(): String {

        return when {

            this.isBlank() ->
                "Full Name is required"

            this.trim().length < 2 ->
                "Minimum 2 characters required"

            !this.all {
                it.isLetter() || it.isWhitespace()
            } ->
                "No numbers or special characters allowed"

            else -> ""
        }
    }

    fun String.validateEmail(): String {
        return when {
            this.isBlank() ->
                "Email is required"

            !Patterns.EMAIL_ADDRESS
                .matcher(this)
                .matches() ->

                "Please enter valid Email Address"

            else -> ""
        }
    }

    fun String.validatePhoneNo() : String{
        return when{
            this.isBlank() -> "Phone number is required"
            this.length < 7 -> "Minimum 7 digits required"
            this.length > 15 -> "Maximum 15 digits required"
            !this.all { it.isDigit() } -> "Should be a digit"
            else -> ""
        }
    }

}
