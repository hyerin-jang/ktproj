package com.project.ktproj.service.board

import com.project.ktproj.domain.board.Board
import com.project.ktproj.domain.board.BoardRepository
import com.project.ktproj.domain.question.QuestionType
import com.project.ktproj.dto.board.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Service
@Validated
@Transactional(readOnly = true)
class BoardService(
    private val boardRepository: BoardRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
) {

    fun getBoards(): List<BoardsResponseDto> {
        val boards = boardRepository.findAll()

        return boards.map(BoardsResponseDto::of)
    }

    fun getBoard(id: Long): BoardDetailResponseDto {
        val board = boardRepository.findByIdOrNull(id) ?: throw IllegalArgumentException()
        return BoardDetailResponseDto.of(board)
    }

    @Transactional
    fun saveBoard(request: BoardCreateRequestDto): BoardsResponseDto {
        val writerYn = writerCheck(request.writer!!)
        if (writerYn) {
            val encodedPassword = passwordEncoder.encode(request.password)
            val board = Board(request.writer, encodedPassword, request.type, request.answer)
            val savedBoard = boardRepository.save(board)
            return BoardsResponseDto.of(savedBoard)
        } else {
            throw RuntimeException()
        }
    }

    @Transactional
    fun updateBoard(request: BoardUpdateRequestDto): BoardsResponseDto {
        val passwordYn = passwordCheck(request.writer, request.password)
        if (passwordYn) {
            val board = boardRepository.findByIdOrNull(request.id) ?: throw IllegalArgumentException()
            board.updateBoard(request.answer)
            return BoardsResponseDto.of(board)
        } else {
            throw RuntimeException()
        }
    }

    @Transactional
    fun deleteBoard(id: Long, request: BoardDeleteRequestDto) {
        //TODO(id, password check)
        val passwordYn = passwordCheck(request.writer, request.password)
        if (passwordYn) {
            val board = boardRepository.findByIdOrNull(id) ?: throw IllegalArgumentException()
            boardRepository.delete(board)
        } else {
            throw RuntimeException()
        }
    }

    fun writerCheck(newWriter: String): Boolean {
        boardRepository.findByWriter(newWriter)?.writer ?: return true
        return false
    }

    fun passwordCheck(writer: String, password: String): Boolean {
        val encodedPassword = boardRepository.findByWriter(writer)!!.password
        return passwordEncoder.matches(password, encodedPassword)
    }
}