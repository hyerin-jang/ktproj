package com.project.ktproj.controller.board

import com.project.ktproj.dto.board.BoardCreateRequestDto
import com.project.ktproj.dto.board.BoardResponseDto
import com.project.ktproj.dto.board.BoardUpdateRequestDto
import com.project.ktproj.service.board.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping("/board")
    fun getBoards(): ResponseEntity<List<BoardResponseDto>> {
        val boards = boardService.getBoards()
        return ResponseEntity.ok(boards)
    }

    @GetMapping("/board/{boardId}")
    fun getBoard(@PathVariable boardId: Long): ResponseEntity<BoardResponseDto> {
        val board = boardService.getBoard(boardId)
        return ResponseEntity.ok(board)
    }

    @PostMapping("/board")
    fun saveBoard(request: BoardCreateRequestDto): ResponseEntity<BoardResponseDto> {
        val savedBoard = boardService.saveBoard(request)
        return ResponseEntity.ok(savedBoard)
    }

    @PutMapping("/board/{boardId}")
    fun updateBoard(@PathVariable boardId: Long, @RequestBody request: BoardUpdateRequestDto):
            ResponseEntity<BoardResponseDto> {

        val boardResponseDto = boardService.updateBoard(request)
        return ResponseEntity.ok(boardResponseDto)
    }

    @DeleteMapping("/board/{boardId}")
    fun deleteBoard(@PathVariable boardId: Long): ResponseEntity<Long> {
        boardService.deleteBoard(boardId)
        return ResponseEntity.ok(boardId)
    }

}