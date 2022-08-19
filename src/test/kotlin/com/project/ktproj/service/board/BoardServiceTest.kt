package com.project.ktproj.service.board

import com.project.ktproj.domain.board.Board
import com.project.ktproj.domain.board.BoardRepository
import com.project.ktproj.domain.question.QuestionType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class BoardServiceTest @Autowired constructor(
    private val boardRepository: BoardRepository,
    private val boardService: BoardService,
) {

    @Test
    fun getBoards() {
        // given
        val board = boardRepository.saveAll(
            listOf(
                Board("작성자1", QuestionType.DEVELOPER),
                Board("작성자2", QuestionType.DEVELOPER)
            )
        )

        // when



        // then

    }

    @Test
    fun getBoard() {
    }

    @Test
    fun saveBoard() {
    }

    @Test
    fun updateBoard() {
    }

    @Test
    fun deleteBoard() {
    }
}