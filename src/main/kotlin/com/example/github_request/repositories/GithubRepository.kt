package com.example.github_request.repositories

import com.example.github_request.models.GithubModel
import org.springframework.data.repository.CrudRepository

interface GithubRepository: CrudRepository<GithubModel, Long> {
}