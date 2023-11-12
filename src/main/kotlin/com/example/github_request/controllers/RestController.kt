package com.example.github_request.controllers

import com.example.Query
import com.example.github_request.models.GithubModel
import com.example.github_request.models.ResponseModel
import com.example.github_request.repositories.GithubRepository
import com.example.github_request.repositories.StudentRepository
import com.example.github_request.services.GithubService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController(
    @Autowired val githubService: GithubService,
    @Autowired val studentRepository: StudentRepository,
    @Autowired val githubRepository: GithubRepository
) {
    @GetMapping("/")
    fun root(): String {
        return "Hello world"
    }

    @GetMapping("/v1/graph")
    suspend fun graph(): ResponseModel<Query.Data> = runBlocking {
        val result = githubService.callGithubApiGraphQL()
        val response = ResponseModel(result, HttpStatus.OK)
        response
    }

    @GetMapping("/v1/{owner}/{repo}")
    fun getRepo(@PathVariable owner: String, @PathVariable repo: String): ResponseModel<GithubModel> {
        val result = githubService.getRepoRest(owner, repo)
        return ResponseModel(result, HttpStatus.OK)
    }

    @GetMapping("/v2")
    fun getAllRepo(): ResponseModel<MutableIterable<GithubModel>> {
        return ResponseModel(githubService.getAllRepo(), HttpStatus.OK)
    }
}

//SPRING DATA AND JPA
//H2
//Longterm:
// 2 rest enpoint: POST/Put and GET for githubModel using H2 GET all / GET one


/*
    Step 0: Try to connect to H2

    Step 1: Either one of these two endpoints, your choice
        // POST /v2/owner/repo/ {GithubModel} => Save in database
        // POST /v2/owner/ {GithubModel and use name from model} => Save in database

    Step 2:
        // GET /v2/owner/repo/ => GithubModel

    Step 3:
        // GET /v2/owner/ =>  List<GithubModel>
 */


//Service, Repository, Controller, Entity


// Using JPA, Entity & H2