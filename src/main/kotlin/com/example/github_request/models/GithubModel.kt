package com.example.github_request.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubModel(
    var name: String,
    var private: Boolean,
    var owner: OwnerModel,
    var description: String,
    var language: String,
    var fork: Boolean,
    @JsonProperty("watchers_count") var watchersCount: Int
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class OwnerModel(
    var login: String
)
