package com.project.ktproj.service.board

import com.project.ktproj.domain.board.Board
import com.project.ktproj.domain.board.BoardRepository
import com.project.ktproj.domain.question.QuestionType
import com.project.ktproj.dto.board.BoardCreateRequestDto
import com.project.ktproj.dto.board.BoardDeleteRequestDto
import com.project.ktproj.dto.board.BoardUpdateRequestDto
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class BoardServiceTest @Autowired constructor(
    private val boardRepository: BoardRepository,
    private val boardService: BoardService,
    private val passwordEncoder: BCryptPasswordEncoder,
) {
    @Test
    fun getBoards() {
        //given
        val boards = boardRepository.findAll()

        //when
        val result = boardService.getBoards()

        //then
        assertThat(boards).isEqualTo(result)
    }

    @Test
    fun getBoard() {
        //given
        val board = Board("tester1", "password1", QuestionType.WORKER, "answer1")
        val savedBoard = boardRepository.save(board)
        val id = savedBoard.id!!
        val expect = boardRepository.findByIdOrNull(savedBoard.id) ?: throw IllegalArgumentException()

        //when
        val result = boardService.getBoard(id)

        //then
        assertThat(expect.writer).isEqualTo(result.writer)
        assertThat(expect.type).isEqualTo(result.type)
        assertThat(expect.answer).isEqualTo(result.answer)
    }

    @Test
    fun saveBoard() {
        //given
        val request = BoardCreateRequestDto("tester1", "password1", QuestionType.WORKER, "answer1")
//        val board = Board(request.writer, request.password, request.type, request.answer)
//        val expect = boardRepository.save(board)

        //when
        val result = boardService.saveBoard(request)

        //then
        assertThat(result.writer).isEqualTo("tester1")
        assertThat(result.type).isEqualTo(QuestionType.WORKER)
        assertThat(result.answer).isEqualTo("answer1")

    }

    @Test
    fun updateBoard() {
        //given
        val request = BoardCreateRequestDto("tester1", "password1", QuestionType.WORKER, "answer1")
        val board = Board(request.writer, passwordEncoder.encode(request.password), request.type, request.answer)
        val savedBoard = boardRepository.save(board)
        val id = savedBoard.id!!

        val updatedDto = BoardUpdateRequestDto(id, "tester1", "password1", QuestionType.WORKER, "answer2")
        board.updateBoard(updatedDto.answer)

        val expect = boardRepository.findByIdOrNull(id) ?: throw IllegalArgumentException()

        //when
        val result = boardService.updateBoard(updatedDto)

        //then
        assertThat(expect.writer).isEqualTo(result.writer)
        assertThat(expect.type).isEqualTo(result.type)
        assertThat(expect.answer).isEqualTo(result.answer)
    }

    @Test
    fun deleteBoard() {
        //given
        val board = Board("tester1", passwordEncoder.encode("password1"), QuestionType.WORKER, "answer1")
        val savedBoard = boardRepository.save(board)
        val id = savedBoard.id!!
        val request = BoardDeleteRequestDto("tester1", "password1")

        //when
        boardService.deleteBoard(id, request)
        val expect = boardRepository.findByIdOrNull(id)

        //then
        assertThat(expect).isNull()
    }
}