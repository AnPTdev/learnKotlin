package com.example.github_request.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Github")
data class GithubModel(
    var name: String,
    var isPrivate: Boolean,
    @OneToOne(cascade = [CascadeType.ALL])
    var owner: OwnerModel,
    var description: String?,
    var language: String,
    var fork: Boolean,
    @JsonProperty("watchers_count") var watchersCount: Int,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
)

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class OwnerModel(
    var login: String,
    @JsonIgnore
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
)