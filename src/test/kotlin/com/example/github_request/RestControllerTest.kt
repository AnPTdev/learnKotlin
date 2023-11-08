package com.example.github_request

import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class RestControllerTest(
    @Autowired
    val mockMvc: MockMvc
) {
    @Test
    fun shouldTouchServerSuccessfully() {
        mockMvc.get("/")
            .andExpect {
                status { isOk() }
                content { string("Hello world") }
            }
    }

    @Test
    fun shouldQueryRepoSuccessfully() {
        val repo = "reporter"
        val owner = "snacker-tracker"
        mockMvc.get("/v1/$owner/$repo")
            .andExpect {
                status { isOk() }
                jsonPath("$.name", Matchers.`is`(repo))
            }
    }
}