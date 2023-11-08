package com.example.github_request

import com.example.github_request.models.GithubModel
import com.example.github_request.services.GithubService
import com.example.github_request.utils.GithubConfig
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class GithubServiceTest(
    @Autowired
    private val githubConfig: GithubConfig
) {

    @Mock
    private lateinit var restTemplate : RestTemplate

    @InjectMocks
    private val githubService = GithubService(githubConfig)

    @Test
    fun shouldQueryGitHub() = runBlocking {
        val result = githubService.callGithubApiGraphQL()
        if (result != null) assertEquals("AnPTdev", result.viewer.login.toString())
    }

    @Test
    fun shouldQueryGitHubRepoSuccessfullyUsingRest() = run {
        val repo = "reporter"
        val owner = "snacker-tracker"
        val response = githubService.getRepoRest(owner, repo)
        val responseObject =  response
        assertEquals(repo, responseObject.name)
    }

    @Test
    fun `should return null when querying the wrong repo`() {
        val repo = "reporter"
        val owner = "snacker-tracker"
        val url = "https://api.github.com/repos/$owner/$repo"
//        val githubModel = GithubModel("Repo", true, OwnerModel("Login"), "Server", "Eng", false, 10)
        Mockito.`when`(
            restTemplate.getForEntity(url, GithubModel::class.java)
        ).thenReturn(ResponseEntity(null, HttpStatus.NOT_FOUND))

        val response = githubService.getRepoRest(owner, repo)
        assertEquals(null, response)
    }
}