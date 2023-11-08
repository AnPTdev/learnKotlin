package com.example.github_request.utils

import com.apollographql.apollo3.exception.ApolloHttpException
import com.example.github_request.models.ErrorModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(HttpClientErrorException::class)
    fun exceptionHandler(e: HttpClientErrorException): ResponseEntity<ErrorModel> {
        val error = ErrorModel(e.message, e.statusCode)
        return ResponseEntity(error, e.statusCode)
    }

    @ExceptionHandler(ApolloHttpException::class)
    fun apolo(e: ApolloHttpException): ResponseEntity<ErrorModel> {
        val error = ErrorModel(e.message, HttpStatus.valueOf(e.statusCode))
        return ResponseEntity(error, HttpStatus.valueOf(e.statusCode))
    }
}