package com.example.assessment.ui.feature.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.assessment.R
import com.example.assessment.data.model.CustomerRequest
import com.example.assessment.data.model.CustomerResponse
import com.example.assessment.ui.state.ApiState
import com.example.assessment.ui.common.CustomTextField
import com.example.assessment.ui.navigation.NavRoutes
import com.example.assessment.utils.ValidationUtils.validateEmail
import com.example.assessment.utils.ValidationUtils.validateName
import com.example.assessment.utils.ValidationUtils.validatePhoneNo


@Composable
fun RegisterScreen(navController : NavController) {
    val viewmodel : RegisterUserViewModel= hiltViewModel()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNo by remember { mutableStateOf("") }
    var isNameValid by remember { mutableStateOf(true) }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPhoneNoValid by remember { mutableStateOf(true) }
    var selectedCity by remember { mutableStateOf("") }
    val isFormValid by remember {
        derivedStateOf {
            name.isNotBlank() &&
                    email.isNotBlank() &&
                    phoneNo.isNotBlank() &&
                    selectedCity.isNotBlank() &&
                    isNameValid &&
                    isEmailValid &&
                    isPhoneNoValid
        }
    }
    val apiState by viewmodel.apiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val isLoading by remember {
        derivedStateOf { apiState is ApiState.Loading }
    }
    LaunchedEffect(apiState) {
        when (val state  = apiState) {
            is ApiState.Success<*> -> {
                val data = state.response as CustomerResponse
                navController.navigate(
                    NavRoutes.Success.createRoute(
                        name    = data.name,
                        email   = data.email,
                        phoneNo = data.phoneNo,
                        city    = data.city
                    )
                ) {
                    popUpTo(NavRoutes.Register.route) { inclusive = true }
                }
            }
            is ApiState.Error<*> -> {
                snackbarHostState.showSnackbar(
                    message = (state.error ?: "Something went wrong") as String,
                    duration = SnackbarDuration.Short
                )
            }
            else -> Unit
        }
    }
    Scaffold(snackbarHost = {SnackbarHost(snackbarHostState)}) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            CustomTextField(
                value = name,
                label = stringResource(R.string.full_name),
                isError = !isNameValid,
                errorMsg = if (!isNameValid) name.validateName() else "",
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    name = it
                    isNameValid = it.validateName().isEmpty()
                }
            )
            Spacer(Modifier.padding(top = 4.dp))
            CustomTextField(
                value = email,
                label = stringResource(R.string.email_address),
                isError = !isEmailValid,
                errorMsg = if (!isEmailValid) email.validateEmail() else "",
                keyboardType = KeyboardType.Email,
                onValueChange = {
                    email = it
                    isEmailValid = it.validateEmail().isEmpty()
                }
            )
            Spacer(Modifier.padding(top = 4.dp))
            CustomTextField(
                value = phoneNo,
                label = stringResource(R.string.phone_no),
                isError = !isPhoneNoValid,
                errorMsg = if (!isPhoneNoValid) phoneNo.validatePhoneNo() else "",
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    phoneNo = it
                    isPhoneNoValid = it.validatePhoneNo().isEmpty()
                }
            )
            Spacer(Modifier.padding(top = 4.dp))
            CountryDropDown(selectedCity = selectedCity, onCitySelected = {
                selectedCity = it
            })
            Spacer(Modifier.padding(top = 8.dp))
            Button(
                onClick = {
                    viewmodel.registerCustomer(CustomerRequest(name, email, phoneNo, selectedCity))
                },
                enabled = isFormValid && !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text(stringResource(R.string.submit))
                }
            }
        }
    }


}

@Composable
fun CountryDropDown(
    selectedCity: String,
    onCitySelected: (String) -> Unit
) {
    val countries = listOf("Dubai", "Abu Dhabi", "Sharjah", "Riyadh")
    var expanded by remember { mutableStateOf(false) }
    Box {
        OutlinedTextField(
            value = selectedCity,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.select_city))
            },
            isError = selectedCity.isEmpty() && expanded,
            supportingText = {
                if(selectedCity.isEmpty() && expanded){
                    Text(stringResource(R.string.city_required))
                }
            },
            modifier = Modifier
                .clickable {
                    expanded = true
                }
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { expanded = true }
        )


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {

            countries.forEach { city ->

                DropdownMenuItem(
                    text = {
                        Text(city)
                    },
                    onClick = {
                        onCitySelected(city)
                        expanded = false
                    }
                )
            }
        }
    }
}