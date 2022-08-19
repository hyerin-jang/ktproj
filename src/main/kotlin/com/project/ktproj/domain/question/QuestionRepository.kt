package com.project.ktproj.domain.question

import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Long> {

    fun findByType(type: QuestionType): List<Question>
}