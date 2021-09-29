package com.upreality.expensesspringbackend.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthenticationRequest(
    @JsonProperty("username") val username: String,
    @JsonProperty("password") val password: String
)
