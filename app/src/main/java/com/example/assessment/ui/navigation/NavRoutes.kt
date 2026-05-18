package com.example.assessment.ui.navigation

import com.example.assessment.ui.navigation.NavArgsConstants.CITY
import com.example.assessment.ui.navigation.NavArgsConstants.EMAIL
import com.example.assessment.ui.navigation.NavArgsConstants.NAME
import com.example.assessment.ui.navigation.NavArgsConstants.PHONE_NO


sealed class NavRoutes(val route: String) {
    data object Register : NavRoutes(NavRouteConstants.REGISTER.name)
    data object Success  : NavRoutes("${NavRouteConstants.SUCCESS.name}/{${NAME}}/{${EMAIL}}/{${PHONE_NO}}/{${CITY}}") {
        fun createRoute(
            name: String,
            email: String,
            phoneNo: String,
            city: String
        ) = "${NavRouteConstants.SUCCESS.name}/$name/$email/$phoneNo/$city"
    }
}