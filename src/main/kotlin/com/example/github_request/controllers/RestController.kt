package com.example.github_request.controllers

import com.example.Query
import com.example.github_request.models.GithubModel
import com.example.github_request.models.ResponseModel2
import com.example.github_request.services.GithubService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController(
    @Autowired val githubService: GithubService
) {
    @GetMapping("/")
    fun root(): String {
        return "Hello world"
    }

    @GetMapping("/v1/graph")
    suspend fun graph(): ResponseModel2<Query.Data> = runBlocking {
        val result = githubService.callGithubApiGraphQL()
        val response = ResponseModel2(result, HttpStatus.OK)
        response
    }

    @GetMapping("/v1/{owner}/{repo}")
    fun getRepo(@PathVariable owner: String, @PathVariable repo: String): ResponseModel2<GithubModel> {
        val result = githubService.getRepoRest(owner, repo)
        return ResponseModel2(result, HttpStatus.OK)
    }
}

