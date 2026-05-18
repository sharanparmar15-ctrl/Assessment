# Requirements — Android Developer Technical Assessment

## Overview
- Build a simple two-screen Android application that:

- Captures customer information via a form
Submits it to a mock REST API
Displays a success screen with the submitted details

## Screens
### Screen 1 — Customer Form

-  Form with 4 input fields
- Show a loading indicator on submit
POST data to mock API on submit

### Screen 2 — Success Page

- Shown after a successful HTTP 200/201 response
- Display a confirmation message
- Display a summary of the submitted details

## Validation Rules

| No | Field         | Input Type | Validation Rules                                                                                          |
|----|---------------|------------|-----------------------------------------------------------------------------------------------------------|
| 1  | Full Name     | Text       | Required · Minimum 2 characters · No numbers or special characters                                        |
| 2  | Email Address | Email      | Required · Must match standard email format (e.g. user@example.com) · Show inline error on invalid format |
| 3  | Phone Number  | Numeric    | Required · Digits only · Minimum 7 and maximum 15 digits · No spaces, dashes, or symbols                  |
| 4  | City          | Dropdown   | Required · Must select one of the four options · Free text entry not allowed                              |

## City Dropdown Options

The city dropdown contains the following hardcoded options:

- Dubai
- Abu Dhabi
- Sharjah
- Riyadh

## API Configuration

| Property | Value |
|----------|-------|
| Platform | MockAPI.io (free, no credit card required) |
| Resource | `/customers` |
| Method | `POST` |
| Endpoint | `https://6a09f38be7e3f433d4839d5d.mockapi.io/customers` |
| Expected Response | `HTTP 201 Created` |

## Evaluation Criteria

| Criteria | Weight |
|----------|--------|
| MVVM architecture implementation | 30% |
| Coroutines & async handling | 20% |
| Jetpack libraries usage | 20% |
| Code quality & readability | 15% |
| UI/UX and input validation | 10% |
| README & documentation | 5% |