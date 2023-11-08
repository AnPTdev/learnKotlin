package com.example.github_request

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class GithubRequestApplication

fun main(args: Array<String>) {
	runApplication<GithubRequestApplication>(*args)
}
