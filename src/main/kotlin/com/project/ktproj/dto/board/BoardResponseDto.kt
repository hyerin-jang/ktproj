package com.project.ktproj.dto.board

import com.project.ktproj.domain.board.Board
import com.project.ktproj.domain.question.Question
import com.project.ktproj.domain.question.QuestionType

data class BoardResponseDto(
    private val id: Long?,

    private val writer: String,

    private val type: QuestionType,

    private val question: List<Question>
) {

    companion object {
        fun of(board: Board): BoardResponseDto {
            return BoardResponseDto(
                id = board.id,
                writer = board.writer,
                type = board.type,
                question = board.question
            )
        }
    }
}