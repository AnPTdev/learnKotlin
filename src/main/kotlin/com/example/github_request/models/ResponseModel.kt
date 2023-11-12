package com.example.github_request.models

import org.springframework.http.HttpStatusCode

data class ResponseModel<T>(
    val body: T?,
    val status: HttpStatusCode,
    val code: Int = status.value()
)