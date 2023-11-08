package com.example.github_request.models

import org.springframework.http.HttpStatusCode

data class ErrorModel(
    private val _message: String?,
    val status: HttpStatusCode,
    val code: Int = status.value()
){
    val message: String
        get() = _message?: "something went wrong"
}

