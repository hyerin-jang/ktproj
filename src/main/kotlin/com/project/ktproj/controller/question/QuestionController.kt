package com.project.ktproj.controller.question

import com.project.ktproj.domain.question.QuestionType
import com.project.ktproj.service.question.QuestionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class QuestionController(
    val questionService: QuestionService
) {

    @GetMapping("/{type}")
    fun getQuestions(@PathVariable type: QuestionType) {
        questionService.getQuestions(type)
    }
}