package com.project.ktproj.dto.board

import com.project.ktproj.domain.question.Question
import com.project.ktproj.domain.question.QuestionType

data class BoardCreateRequestDto(
    val writer: String,
    val type: QuestionType,
    val question: MutableList<Question> = mutableListOf()
) {
}