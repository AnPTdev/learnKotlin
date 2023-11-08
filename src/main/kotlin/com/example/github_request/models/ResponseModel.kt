package com.example.github_request.models

import org.springframework.http.HttpStatusCode

data class ResponseModel(
    val body: GithubModel?,
    val status: HttpStatusCode,
    val code: Int = status.value()
)

data class ResponseModel2<T>(
    val body: T?,
    val status: HttpStatusCode,
    val code: Int = status.value()
)