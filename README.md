# Customers App

Customers App is an Android application built using Jetpack Compose and MVVM architecture.

## Features

- Register customer
- Form validation
- City dropdown selection
- API integration using Retrofit
- State handling using StateFlow
- Loading/Error/Success handling
- Navigation between screens

## Tech Stack

- Kotlin
- Jetpack Compose
- MVVM
- StateFlow
- Retrofit
- Hilt
- Navigation Compose
- MockAPI

## API

POST API used:

https://6a09f38be7e3f433d4839d5d.mockapi.io
## Architecture

MVVM Architecture

UI(Composable Screens) -> ViewModel -> Repository -> API

## Screens

### Register Screen
- Name validation
- Email validation
- Phone validation
- City dropdown
- Submit button remains disabled until all validations pass.

### Success Screen
- Show Registration Successful text along with details we got from api response

