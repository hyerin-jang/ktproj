package com.project.ktproj.dto.board

import com.project.ktproj.domain.board.Board
import com.project.ktproj.domain.question.QuestionType

data class BoardDetailResponseDto(
    val id: Long?,

    val writer: String?,

    val type: QuestionType?,

    val answer: String?
) {

    companion object {
        fun of(board: Board): BoardDetailResponseDto {
            return BoardDetailResponseDto(
                id = board.id,
                writer = board.writer,
                type = board.type,
                answer = board.answer
            )
        }
    }
}