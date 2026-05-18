package com.example.assessment.ui.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun CustomTextField(
    value: String,
    label: String,
    isError: Boolean = false,
    errorMsg: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        isError = isError,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        supportingText = {
            if (isError) {
                Text(text = errorMsg)
            }
        }

    )
}