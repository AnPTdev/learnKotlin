package com.example.github_request.repositories

import com.example.github_request.models.Student
import org.springframework.data.repository.CrudRepository

interface StudentRepository : CrudRepository<Student, Long> {
}