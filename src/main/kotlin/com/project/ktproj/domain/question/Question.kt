package com.project.ktproj.domain.question

import javax.persistence.*

@Entity
class Question(

    @Enumerated(EnumType.STRING)
    val type: QuestionType,

    val q1: String,

    val q2: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long? = null
) {
}