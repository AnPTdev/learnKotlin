package com.example.github_request.utils

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties(prefix = "github")
data class GithubConfig(
    val token: String,
    val url: String,
)

