package com.project.ktproj.dto.board

import com.project.ktproj.domain.board.Board
import com.project.ktproj.domain.question.QuestionType

data class BoardsResponseDto(
    val id: Long?,

    val writer: String,

    val type: QuestionType,

    val answer: String
) {

    companion object {
        fun of(board: Board): BoardsResponseDto {
            return BoardsResponseDto(
                id = board.id,
                writer = board.writer,
                type = board.type,
                answer = board.answer
            )
        }
    }
}