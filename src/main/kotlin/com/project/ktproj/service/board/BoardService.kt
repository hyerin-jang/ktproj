package com.project.ktproj.service.board

import com.project.ktproj.domain.board.Board
import com.project.ktproj.domain.board.BoardRepository
import com.project.ktproj.dto.board.BoardCreateRequestDto
import com.project.ktproj.dto.board.BoardResponseDto
import com.project.ktproj.dto.board.BoardUpdateRequestDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BoardService(
    private val boardRepository: BoardRepository,
) {

    fun getBoards(): List<BoardResponseDto> {
        val boards = boardRepository.findAll()

        return boards.map(BoardResponseDto::of)
    }

    fun getBoard(id: Long): BoardResponseDto {
        val board = boardRepository.findByIdOrNull(id) ?: throw IllegalArgumentException()
        return BoardResponseDto.of(board)
    }

    fun saveBoard(request: BoardCreateRequestDto): BoardResponseDto {
        val board = Board(request.writer, request.type, request.question)
        val savedBoard = boardRepository.save(board)
        return BoardResponseDto.of(savedBoard)
    }

    fun updateBoard(request: BoardUpdateRequestDto): BoardResponseDto {
        val board = boardRepository.findByIdOrNull(request.id) ?: throw IllegalArgumentException()
        board.updateBoard(request.question)
        return BoardResponseDto.of(board)
    }

    fun deleteBoard(id: Long) {
        val board = boardRepository.findByIdOrNull(id) ?: throw IllegalArgumentException()
        boardRepository.delete(board)
    }
}