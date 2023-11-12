package com.example.github_request.services

import com.apollographql.apollo3.ApolloClient
import com.example.Query
import com.example.github_request.utils.GithubConfig
import com.example.github_request.models.GithubModel
import com.example.github_request.repositories.GithubRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate


@Service
class GithubService(private val githubConfig: GithubConfig, @Autowired val githubRepository: GithubRepository) {


    private val token = githubConfig.token
    private val url = githubConfig.url


    private val restClient = RestTemplate()

    private val graphQLClient = ApolloClient.Builder().serverUrl("https://api.github.com/graphql")
        .addHttpHeader("Authorization", "Bearer $token").build()


    suspend fun callGithubApiGraphQL(): Query.Data? {
        val result = graphQLClient.query(Query()).execute()
        return result.data
    }

    fun getRepoRest(owner: String, repo: String): GithubModel? {
        val url = "$url/repos/$owner/$repo"
        val response = restClient.getForEntity(url, GithubModel::class.java)
        println("print $response")
        return response.body
    }

    fun getAllRepo(): MutableIterable<GithubModel> {
        return githubRepository.findAll()
    }
}


