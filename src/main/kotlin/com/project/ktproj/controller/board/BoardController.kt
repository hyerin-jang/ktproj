package com.project.ktproj.controller.board

import com.project.ktproj.dto.board.*
import com.project.ktproj.service.board.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping("/board")
    fun getBoards(): ResponseEntity<List<BoardsResponseDto>> {
        val boards = boardService.getBoards()
        return ResponseEntity.ok(boards)
    }

    @GetMapping("/board/{boardId}")
    fun getBoard(@PathVariable boardId: Long): ResponseEntity<BoardDetailResponseDto> {
        return try {
            val board = boardService.getBoard(boardId)
            ResponseEntity.ok(board)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.ok(null)
        }
    }

    @PostMapping("/board")
    fun saveBoard(
        @Valid @RequestBody request: BoardCreateRequestDto,
        bindingResult: BindingResult
    ): ResponseEntity<BoardsResponseDto> {

        return try {
            if (bindingResult.hasErrors()) {
                throw RuntimeException()
            }
            val savedBoard = boardService.saveBoard(request)
            ResponseEntity.ok(savedBoard)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @PutMapping("/board/{boardId}")
    fun updateBoard(
        @PathVariable boardId: Long,
        @Valid @RequestBody request: BoardUpdateRequestDto,
        bindingResult: BindingResult
    ): ResponseEntity<BoardsResponseDto> {

        return try {
            if (bindingResult.hasErrors()) {
                throw RuntimeException()
            }
            val boardResponseDto = boardService.updateBoard(request)
            ResponseEntity.ok(boardResponseDto)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(null)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @DeleteMapping("/board/{boardId}")
    fun deleteBoard(
        @PathVariable boardId: Long,
        @Valid @RequestBody request: BoardDeleteRequestDto,
        bindingResult: BindingResult
    ): ResponseEntity<String> {

        return try {
            if (bindingResult.hasErrors()) {
                throw RuntimeException()
            }
            boardService.deleteBoard(boardId, request)
            ResponseEntity.ok("delete success")
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body("delete fail")
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body("delete fail")
        }
    }

}