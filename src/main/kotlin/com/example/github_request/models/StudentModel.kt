package com.example.github_request.models

import jakarta.persistence.*

@Entity
@Table(name = "Student")
class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var name: String
)